package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class machineToAssembly {
    public static void machineAssembly(){
        ArrayList<String> machineLanguageLine = new ArrayList<>();
        try {
            // System.out.println("What is the file path (Ex. tests/dat_files/r_type.dat");
            // Scanner scan = new Scanner(System.in);
            // String fileName = scan.nextLine();
            String fileName = "tests/dat_files/r_type.dat";

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

        for(int i = 0; i < machineLanguageLine.size(); i++){
            System.out.println("Binary form " + machineLanguageLine.get(i));
            //System.out.println("Assembly Form " + bitToAssembly(machineLanguageLine.get(i)));
            System.out.println("");
        }

    }
/* 
    public String bitToAssembly(){
        String instruction = "", arg1 = "", arg2 = "", arg3 = "";
        switch(opcode){
        // R-type instructions
        if(opcode == 0b0110011) // ADD / SUB / SLL / SLT / SLTU / XOR / SRL / SRA / OR / AND
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", rs1);
            arg3 = String.format("x%d", rs2);

            if(funct3 == 0b000) {// ADD / SUB  
                if(funct7 == 0b0000000) {// ADD
                    instr = "add";
                    break;
                }
                if(funct7 == 0b0100000) {// SUB
                    instr = "sub";
                    break;
                }
                break;
            }
            if(funct3 == 0b001) {// SLL
                instr = "sll";
                break;
            }
            if(funct3 == 0b010) {// SLT
                instr = "slt";
                break;
            }
            if(funct3 == 0b011) {// SLTU
                instr = "sltu";
                break;
            }
            if(funct3 == 0b100) {// XOR
                instr = "xor";
                break;
            }
            if(funct3 == 0b101) {// SRL / SRA
                if(funct7 == 0b0000000) {// SRL
                    instr = "srl";
                    break;
                }
                if(funct7 == 0b0100000) {// SRA
                    instr = "sra";
                    break;
                }
                break;
            }
            if(funct3 == 0b110) {// OR
                instr = "or";
                break;
            }
            if(funct3 == 0b111) {// AND
                instr = "and";
            }
            break;
        if(opcode == 0b1101111) {//JAL
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", imm);
            instr = "jal";
            break;
        }
        if(opcode == 0b1100111) {// JALR
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", imm);
            instr = "jalr";
            break;
        }
        if(opcode == 0b0000011) {// LB / LH / LW / LBU / LHU
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d(x%d)", imm, rs1);
            
            if(funct3 == 0b000){
                instr = "lb";
                break;
            }else if(funct3==0b001){
                instr = "lh";
                break;
            }else if(funct3==0b010){
                instr = "lw";
                break;
            }else if(funct3==0b100){
                instr = "lbu";
                break;
            }else if(funct3==0b101){
                instr = "lhu";
                break;
            }
            break;
        }
        if(opcode == 0b0010011) {// ADDI / SLTI / SLTIU / XORI / ORI / ANDI / SLLI / SRLI / SRAI
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", rs1);
            arg3 = String.format("%d", imm);

            if(funct3 == 0b000) {// ADDI
                instr = "addi";
                break;
            }
            if(funct3 == 0b010) {// SLTI
                instr = "slti";
                break;
            }
            if(funct3 == 0b011) {// SLTIU
                instr = "sltiu";
                break;
            }
            if(funct3 == 0b100) {// XORI
                instr = "xori";
                break;
            }
            if(funct3 == 0b110) {// ORI
                instr = "ori";
                break;
            }
            if(funct3 == 0b111) {// ANDI
                instr = "andi";
                break;
            }
            if(funct3 == 0b001) {// SLLI
                instr = "slli";
                break;
            }
            if(funct3 == 0b101) // SRLI / SRAI
                if(funct7 == 0b0000000) {// SRLI
                    instr = "srli";
                    break;
                }
                if(funct7 == 0b0100000) {// SRAI
                    instr = "srai";
                    break;
                }
                break;
            }
            break;
        }
        if(opcode == 0b0001111) {// FENCE / FENCE.I
            switch(funct3){
                case 0b000: // FENCE
                    instr = "fence";
                    break;
                case 0b001: // FENCE.I
                    instr = "fence.i";
                    break;
            }
            break;
        }
        if(opcode == 0b1110011) {// ECALL / EBREAK / CSRRW / CSRRS / CSRRC / CSRRWI / CSRRSI / CSRRCI
            if(funct3 == 0b000) { // ECALL / EBREAK
                if(imm == 0b000000000000) {// ECALL
                    instr = "ecall";
                    ecall = true;
                    break;
                }
                if(imm == 0b000000000001) {// EBREAK
                    instr = "ebreak";
                    break;
                }
                break;
            }
            if(funct3 == 0b001) {// CSRRW
                instr = "csrrw";
                break;
            }
            if(funct3 == 0b010) {// CSRRS
                instr = "csrrs";
                break;
            }
            if(funct3 == 0b011) {// CSRRC
                instr = "csrrc";
                break;
            }
            if(funct3 == 0b101) {// CSRRWI
                instr = "csrrwi";
                break;
            }
            if(funct3 == 0b110) {// CSRRSI
                instr = "csrrsi";
                break;
            }
            if(funct3 == 0b111) {// CSRRCI
                instr = "csrrci";
                break;
            }
            noRd = true;
            break;
        }
      
        
        // S-type instructions
        if(opcode == 0b0100011) { // SB / SH / SW
            arg1 = String.format("x%d", rs2);
            arg2 = String.format("%d(x%d)", imm, rs1);
            
            if (funct3 == 0b000) { // SB
                if ("sb" != null)
                    instr = "sb";
                else
                    instr = "";
            } else if (funct3 == 0b001) { // SH
                if ("sh" != null)
                    instr = "sh";
                else
                    instr = "";
            } else if (funct3 == 0b010) { // SW
                if ("sw" != null)
                    instr = "sw";
                else
                    instr = "";
            } else {
                // If none of the cases match, you may want to handle this scenario.
                // For example, setting instr to a default value or handling an error.
            }

            noRd = true;
            sType = true;
        }



        //B-type instructions
        if(opcode == 0b1100011) // BEQ / BNE / BLT / BGE / BLTU / BGEU
            arg1 = String.format("x%d", rs1);
            arg2 = String.format("x%d", rs2);
            arg3 = String.format("%d", imm);
            
            
            if(funct3 == 0b000){
                instr = "beq";
                break;
            } else if(funct3 == 0b001){
                instr = "bne";
                break;
            }else if(funct3 == 0b100){
                instr = "blt";
                    break;
            }else if(funct3 == 0b101){
                instr = "bge";
                    break;
            }else if(funct3 == 0b110){
                instr = "bltu";
                    break;
            }else if(funct3 == 0b111){
                instr = "blgeu";
                    break;
            }

            noRd = true;
            break;

        //U-type instructions
        if(opcode == 0b0110111) {//LUI
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d", imm >>> 12);
            instr = "lui";
            break;
        }
        if(opcode == 0b0010111) {//AUIPC
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d", imm >>> 12);
            instr = "auipc";
            break;
        }
        else {
            return String.format("Unrecognized: 0x%08x", instruction);
        }
        return String.format("%s %s %s %s", instr, arg1, arg2, arg3);
    }
*/
    public static void main(String[] args){
        machineAssembly();
    }

}
