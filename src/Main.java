package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    int pc = 0;                     // instructions counter
    int prev;                     // Previous pc
    static int[] register = new int[32];        // RISC-V registeristers x0 to x31
    private Instruction[] instructions;  // Array of all instructions instructions
    private Memory memory;          // Memory byte array

    public int returnPcValue(){
        return pc;
    }
    /**
     * CPU constructor
     * Sets stack pointer to last address in memory (last index of byte array memory.getMemory()).
	 * Initializes memory and instructions to input parameters. 
     */
    public Main(Memory memory) {
        this.memory = memory;                      // Initialize Memory object
        //this.instructions = instructions;                 // Initialize array of Instruction objects
        //register[2] = memory.getMemory().length - 1; // Initialize stack pointer to point at last address. 

        ArrayList<String> machineLanguageLine = new ArrayList<>();
        try {
        //     System.out.println("What is the file path (Ex. tests\\dat_files\\addi_hazards.dat)");
        //     Scanner scan = new Scanner(System.in);
        //     String fileName = scan.nextLine();
            String fileName = "tests\\dat_files\\i_type.dat";
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            StringBuilder bitLine = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                bitLine.insert(0, line);
                if (bitLine.length() >= 32) {
                    machineLanguageLine.add(bitLine.substring(0, 32));
                    bitLine.setLength(0);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        this.instructions = new Instruction[machineLanguageLine.size()];

        for(int i = 0; i < machineLanguageLine.size(); i++){
            System.out.println("Binary form: " + machineLanguageLine.get(i));
            BigInteger big = new BigInteger(machineLanguageLine.get(i),2);
            Instruction instrt = new Instruction(big.intValue());
            instructions[i] = instrt;
            System.out.println("Assembly Form: " + instrt.bitToAssembly());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //Instruction[] instructions = new Instruction[4096];
        Memory memory = new Memory();
        Main main = new Main(memory);
        Scanner scnr = new Scanner(System.in);
        while(true) {
            System.out.println("Input a command:");
            String input = scnr.nextLine();
            if(input.equals("r")) {
                main.pc = 0;
                for(int i = 0; i < main.instructions.length; i++) {
                    main.runInstruction();
                }
                System.out.println(Arrays.toString(register));
                Arrays.fill(register, 0);
                main.pc = 0;
            }
            if(input.equals("s")) {
                main.runInstruction();
                System.out.println(Arrays.toString(register));
            }
            if(input.equals("pc")) {
                int pcVal = main.returnPcValue();
                System.out.println(pcVal);
            }

        }


        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
        // main.runInstruction();
        // System.out.println(Arrays.toString(register));
    }

    /**
     * Executes one instruction given by the Instruction array 'instructions' at index given by the instructions counter 'pc'. 
     * Uses the opcode field of the instruction to determine which type of instruction it is and call that method.
     */
    
    public void runInstruction() {
        prev = pc;
        Instruction inst = instructions[pc];

        //U-type instructions
        if(inst.opcode == 0b0110111) { //LUI
            register[inst.rd] = inst.imm;
            pc++;
            
        }
        if(inst.opcode == 0b0010111) { //AUIPC
            register[inst.rd] = (pc << 2) + inst.imm; // Shift pc because we count in 4 byte words
            pc++;
            
        }

        // J-type instruction
        if(inst.opcode == 0b1101111) { //JAL
            register[inst.rd] = (pc+1)<<2; // Store address of next instruction in bytes
            pc += inst.imm>>2;
            
        }    
        // I-type instructions
        if(inst.opcode == 0b1100111) { // JALR
            register[inst.rd] = (pc+1)<<2;
            pc = ((register[inst.rs1] + inst.imm) & 0xFFFFFFFE)>>2;
            
        }
        //B-type instructions
        if(inst.opcode == 0b1100011) { // BEQ / BNE / BLT / BGE / BLTU / BGEU
            bType(inst);
           
        }
        // if(inst.opcode == 0b0000011) {// LB / LH / LW / LBU / LHU
        //     iTypeLoad(inst);
        //     break;
        // }
        // //S-type instructions
        // if(inst.opcode == 0b0100011) {//SB / SH / SW
        //     sType(inst);
        //     break;
        // }
        if(inst.opcode == 0b0010011) {// ADDI / SLTI / SLTIU / XORI / ORI / ANDI / SLLI / SRLI / SRAI
            iTypeInteger(inst);
            
        }
        // R-type instructions
        if(inst.opcode == 0b0110011) {// ADD / SUB / SLL / SLT / SLTU / XOR / SRL / SRA / OR / AND
            rType(inst);
        }
        if(inst.opcode == 0b00000000){ // NOP
            NOP();
        }
        register[0] = 0; // x0 must always be 0
    }

    private void NOP() {
        System.out.println("End of program");
    }

    /**
     * Handles execution of r-Type instructions:
     * ADD / SUB / SLL / SLT / SLTU / XOR / SRL / SRA / OR / AND
     */
    private void rType(Instruction inst) {
        if(inst.funct3 == 0b000) {// ADD / SUB
            if(inst.funct7 == 0b0000000) { // ADD
                register[inst.rd] = register[inst.rs1] + register[inst.rs2];       
            }
            else if(inst.funct7 == 0b0100000) { // SUB
                register[inst.rd] = register[inst.rs1] - register[inst.rs2];
            
            }
           
        }
        if(inst.funct3 == 0b001) {// SLL
            register[inst.rd] = register[inst.rs1] << register[inst.rs2];
           
        }
        if(inst.funct3 == 0b010) {// SLT
            if (register[inst.rs1] < register[inst.rs2])
                register[inst.rd] = 1;
            else
                register[inst.rd] = 0;
       
        }
        if(inst.funct3 == 0b011) { // SLTU
            if (Integer.toUnsignedLong(register[inst.rs1]) < Integer.toUnsignedLong(register[inst.rs2]))
                register[inst.rd] = 1;
            else
                register[inst.rd] = 0;
      
        }
        if(inst.funct3 == 0b100) {// XOR
            register[inst.rd] = register[inst.rs1] ^ register[inst.rs2];
  
        }
        if(inst.funct3 == 0b101) {// SRL / SRA
            if(inst.funct7 == 0b0000000) {// SRL
                register[inst.rd] = register[inst.rs1] >>> register[inst.rs2];
               
            }
            if(inst.funct7 == 0b0100000) {// SRA
                register[inst.rd] = register[inst.rs1] >> register[inst.rs2];
           
            }
            
        }
        if(inst.funct3 == 0b110) {// OR
            register[inst.rd] = register[inst.rs1] | register[inst.rs2];
          
        }
        if(inst.funct3 == 0b111) {// AND
            register[inst.rd] = register[inst.rs1] & register[inst.rs2];
        
        }
        pc++;
    }

    /**
     * Handles execution of i-Type load instructions:
     * LB / LH / LW / LBU / LHU
     */
    // private void iTypeLoad(Instruction inst) {
    //     int addr = register[inst.rs1] + inst.imm; // Byte address

    //     if(inst.funct3 == 0b000) {// LB
    //         register[inst.rd] = memory.getByte(addr);
        
    //     }
    //     else if(inst.funct3 == 0b001) {// LH
    //         register[inst.rd] = memory.getHalfWord(addr);
           
    //     }
    //     else if(inst.funct3 == 0b010) {// LW
    //         register[inst.rd] = memory.getWord(addr);
         
    //     }
    //     else if(inst.funct3 == 0b100) {// LBU
    //         register[inst.rd] = memory.getByte(addr) & 0xFF; //Remove sign bits
            
    //     }
    //     else if(inst.funct3 == 0b101) {// LHU
    //         register[inst.rd] = memory.getHalfWord(addr) & 0xFFFF;
            
    //     }
    //     else {
          
    //     }
    //     pc++;
    // }

    /**
     * Handles execution of I-type integer instructions:
     * ADDI / SLTI / SLTIU / XORI / ORI / ANDI / SLLI / SRLI / SRAI
     */
    private void iTypeInteger(Instruction inst) {
        if(inst.funct3 == 0b000) {// ADDI
            register[inst.rd] = register[inst.rs1] + inst.imm;
            
        }
        if(inst.funct3 == 0b010) {// SLTI
            if(register[inst.rs1] < inst.imm)
                register[inst.rd] = 1;
            else
                register[inst.rd] = 0;
            
        } 
                
        if(inst.funct3 == 0b011){ // SLTIU
            if(Integer.toUnsignedLong(register[inst.rs1]) < Integer.toUnsignedLong(inst.imm))
                register[inst.rd] = 1;
            else
                register[inst.rd] = 0;

        } 
                
        if(inst.funct3 == 0b100){// XORI
            if ((register[inst.rs1] ^ inst.imm) != 0)
                register[inst.rd] = register[inst.rs1] ^ inst.imm;
            else
                register[inst.rd] = 0;
            
        } 
    

        if(inst.funct3 == 0b110){ // ORI
            if ((register[inst.rs1] | inst.imm) != 0)
            register[inst.rd] = register[inst.rs1] | inst.imm;
            else
                register[inst.rd] = 0;
           
        }
    
   
        if(inst.funct3 == 0b111){ // ANDI
            if ((register[inst.rs1] & inst.imm) != 0)
                register[inst.rd] = register[inst.rs1] & inst.imm;
            else
                register[inst.rd] = 0;
            
        }
                

        if(inst.funct3 == 0b001){ // SLLI
            if ((register[inst.rs1] << inst.imm) != 0)
                register[inst.rd] = register[inst.rs1] << (inst.imm & 0x1F);
            else
                register[inst.rd] = 0;
            
        }
    

        if(inst.funct3 == 0b101){ // SRLI / SRAI
            int ShiftAmt = inst.imm & 0x1F; // The amount of shifting done by SRLI or SRAI
            System.out.println(ShiftAmt);
            //int f7 = inst.imm & 0b1111111;
            //System.out.println(Integer.toBinaryString(inst.funct7));
            if(inst.funct7 == 0b0000000) {// SRLI
                System.out.println(ShiftAmt);
                register[inst.rd] = register[inst.rs1] >>> (inst.imm & 0x1F);
                
            }
            if(inst.funct7 == 0b0100000) { // SRAI
                register[inst.rd] = register[inst.rs1] >> (inst.imm & 0x1F);
                
            }
            
        }
                
        pc++;
    }

    /**
     * Handles the S-type instructions:
     * SB / SH / SW
     */
    // private void sType(Instruction inst) {
    //     int addr = register[inst.rs1] + inst.imm;
        
        
    //     if(inst.funct3 == 0b000){
    //         memory.storeByte(addr,(byte) register[inst.rs2]);
    //         break;
    //     }
    //     else if(inst.funct3 == 0b001){
    //         memory.storeHalfWord(addr, (short) register[inst.rs2]);
    //         break;
    //     }
    //     else if(inst.funct3 == 0b010){
    //         memory.storeWord(addr, register[inst.rs2]);
    //         break;
    //     }
        
    //     pc++;
    // }

    /**
     * Handles the B-type instructions:
     * BEQ / BNE / BLT / BGE / BLTU / BGEU
     */
    private void bType(Instruction inst) {
        int Imm = inst.imm >> 2; //We're counting in words instead of bytes
        
        if(inst.funct3 == 0b000){
            pc += (register[inst.rs1] == register[inst.rs2]) ? Imm : 1;
            
        }
        else if(inst.funct3 == 0b001){
            pc += (register[inst.rs1] != register[inst.rs2]) ? Imm : 1;
            
        }
        else if(inst.funct3 == 0b100){
            pc += (register[inst.rs1] < register[inst.rs2]) ? Imm : 1;
            
        }
        else if(inst.funct3 == 0b101){
            pc += (register[inst.rs1] >= register[inst.rs2]) ? Imm : 1;
            
        }
        else if(inst.funct3 == 0b110){
            pc += (Integer.toUnsignedLong(register[inst.rs1]) < Integer.toUnsignedLong(register[inst.rs2])) ? Imm : 1;
            
        }
        else if(inst.funct3 == 0b111){
            pc += (Integer.toUnsignedLong(register[inst.rs1]) >= Integer.toUnsignedLong(register[inst.rs2])) ? Imm : 1;
        }
    }

}