package src;
public class Instruction {
    int instruction, opcode, rd, rs1, rs2, funct3, funct7, imm, shamt;
    boolean noRd = false;
    boolean sType = false;
    boolean ecall = false;
    String assemblyString;

   
    public Instruction(int instruction) {
        this.instruction = instruction;
        this.opcode = instruction & 0x7F;           // First 7 bits
        this.rd = (instruction >> 7) & 0x1F;        // bits 11 to 7
        this.funct3 = (instruction >> 12) & 0x7;    // bits 14 to 12
        this.rs1 = (instruction >> 15) & 0x1F;      // bits 19 to 15
        this.rs2 = (instruction >> 20) & 0x1F;      // bits 24 to 20

        // J-Type
        if (opcode == 0b1101111)  {
            this.imm = getImmJ(instruction);
               
        }

        // I-Type
        else if (opcode == 0b1100111 || opcode == 0b0000011 || opcode == 0b0010011 ) { 
            this.imm = (instruction >> 20);
            this.funct7 = (instruction >> 25) & 0x7F;
        }

        // R-Type
        else if (opcode == 0b0110011) { 
            this.funct7 = (instruction >> 25) & 0x7F;
            
        }

        // S-Type
        else if (opcode == 0b0100011) {
            imm = (((instruction >> 20) & 0xFFFFFFE0) |
                ((instruction >>> 7) & 0x0000001F));
                 
        }

        // B-Type
        else if (opcode == 0b1100011) {
            this.imm = getImmB(instruction);
                 
        }

        // U-Type
        else if (opcode == 0b0110111 || opcode == 0b0010111) { 
            this.imm = instruction & 0xFFFFF000;
                
        }

        this.assemblyString = bitToAssembly(); // String version of assembly code
    }


    //extract imm for B type
    private int getImmB (int instruction) {
        return ((((((instruction >>> 7) & 0x0000001F)|(instruction >> 20) & 0xFFFFFFE0)) & 0xFFFFF7FE)
                | (((((instruction >> 20) & 0xFFFFFFE0) | ((instruction >>> 7) & 0x0000001F)) & 0x00000001) << 11));
    }

    //extract imm for J type
    private int getImmJ(int instruction) {
        int b12to19 = (instruction>>12) & 0xFF; // Bits 12 to 19 of immediate (12 to 19 of instruction)
        int b11 = (instruction>>20) & 0x1;      // Bit 11 of immediate (20th bit of instruction)
        int b1to10 = (instruction>>21) & 0x3FF; // Bit 1 to 10 of immediate (21 to 30 of instruction)
        int b20 = (instruction>>31);            // Bit 20 of immediate (MSB of instruction)
        return (b20 << 20 | b12to19 << 12 | b11 << 11 | b1to10 << 1);
    }

    /**
     * Converts the instruction to an assembly string
     */
    public String bitToAssembly() {
        String instr = "", arg1 = "", arg2 = "", arg3 = "";

        // R-type instructions
        if (opcode == (0b0110011)) {
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", rs1);
            arg3 = String.format("x%d", rs2);
            if (funct3 == 0b000) {
                if(funct7 == 0b0000000){ // ADD
                    instr = "add";
                    
                }
                else if (funct7 == 0b0100000) {//SUB
                    instr = "sub";
                    
                }
            }
            else if (funct3 == 0b001) { // SLL
                instr = "sll";
            }
            else if (funct3 == 0b010) { // SLT
                instr = "slt";
            }

            else if (funct3 == 0b011) {  //SLTU
                instr = "sltu";
            }

            else if (funct3 == 0b100) { //XOR
                instr = "xor";
            }  
            else if (funct3 == 0b101) { 
                if(funct7 == 0b0000000 ){ // SRL
                    instr = "srl";
                    
                }
                else if (funct7 == 0b0100000) { // SRA
                    instr = "sra";
                    
                }
                
            }

            else if (funct3 == 0b110) { // OR
                instr = "or";
            }
            else if (funct3 == 0b111) {   // and
                instr = "and";
            }
            
            
        }   
            
        else if (opcode == 0b1101111) { //JAL
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", imm);
            instr = "jal";
            
        }
        else if (opcode == 0b1100111) { // JALR
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", imm);
            instr = "jalr";
            
        }
        else if (opcode == 0b0000011) {
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d(x%d)", imm, rs1);
            if (funct3 == 0b000){      // LB
                instr = "lb";
                
            }
            else if (funct3 == 0b001) { // LH
                    instr = "lh";
                    
            }
            
            else if (funct3 == 0b010) {// LW
                    instr = "lw";
                    
            }   
                    
            else if (funct3 == 0b100) { // LBU
                    instr = "lbu";
                
            }
            else if (funct3 == 0b101) { // LHU
                    instr = "lhu";
                    
            }
            
        }


        else if (opcode == 0b0010011) {
            arg1 = String.format("x%d", rd);
            arg2 = String.format("x%d", rs1);
            arg3 = String.format("%d", (imm));
                if (funct3 == 0b000){ // ADDI
                    instr = "addi";
                    
                }
                    
                else if (funct3 == 0b010){ // SLTI
                    instr = "slti";
                    
                }
                else if (funct3 == 0b011){ // SLTIU
                    instr = "sltiu";
                    
                }
                    
                else if (funct3 == 0b100){ // XORI
                    instr = "xori";
                    
                }
                else if (funct3 == 0b110){ // ORI
                    instr = "ori";
                
                }
                    
                else if (funct3 == 0b111){ // ANDI
                    instr = "andi";
                    
                }
                    
                else if (funct3 == 0b001){ // SLLI
                    instr = "slli";
                    
                }
                    
                else if (funct3 == 0b101){
                    if (funct7 == 0b0000000){ // SRLI
                        instr = "srli";
                        arg3 = String.format("%d", (imm & 0x1F));
                    }
                    else if (funct7 == 0b0100000){ // SRAI
                        instr = "srai";
                        arg3 = String.format("%d", (imm & 0x1F));
                    }
                
                }
            
        }
        //S-type instructions
        else if (opcode == 0b0100011) {
            arg1 = String.format("x%d", rs2);
            arg2 = String.format("%d(x%d)", imm, rs1);
            
            if(funct3 == 0b000){ //SB
                instr = "sb";
            
            }
            else if(funct3 == 0b001){ //SH
                instr = "sh";
                
                    
            }
            else if(funct3 == 0b010){ //SW
                instr = "sw";
                
            }
            noRd = true;
            sType = true;
            
        }
        //B-type instructions
        else if (opcode == 0b1100011) {
            arg1 = String.format("x%d", rs1);
            arg2 = String.format("x%d", rs2);
            arg3 = String.format("%d", imm);
            
            
            if(funct3 == 0b000){ //BEQ
                instr = "beq";
                
            }
            else if(funct3 == 0b001){ //BNE
                instr = "bne";
                
            }
            else if(funct3 == 0b100){ //BLT
                instr = "blt";
                
            }
            else if(funct3 == 0b101){ //BGE
                instr = "bge";
                
            }
            else if(funct3 == 0b110){ //BLTU
                instr = "bltu";
            
            }
            else if(funct3 == 0b111){ //BLGEU
                instr = "blgeu";
            
            }

            
            noRd = true;
        
        }

        //U-type instructions
        else if (opcode == 0b0110111) { //LUI
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d", imm >>> 12);
            instr = "lui";
            
        }

        else if (opcode == 0b0010111) {//AUIPC
            arg1 = String.format("x%d", rd);
            arg2 = String.format("%d", imm >>> 12);
            instr = "auipc";
            
        }
        else {
            return String.format("Unrecognized: 0x%08x", instruction);
        }
    
        return String.format("%s %s %s %s", instr, arg1, arg2, arg3);
    }
}
