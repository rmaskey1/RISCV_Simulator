package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class instructionReader {
    public static String determineInstruction(String str) {
        //Write given instuction to assembly.asm
        writeInstructionToFile(str);

        for(int i = 0; i < InstructionTypes.rTypeInstructions.size(); i++){
            if(str.equals(InstructionTypes.rTypeInstructions.get(i))){
                return "R-Type";
            }
            if(str.equals(InstructionTypes.iTypeInstructions.get(i))){
                return "I-Type";
            }
            if(str.equals(InstructionTypes.sTypeInstructions.get(i))){
                return "S-Type";
            }
            if(str.equals(InstructionTypes.sbTypeInstructions.get(i))){
                return "SB-Type";
            }
            if(str.equals(InstructionTypes.uTypeInstructions.get(i))){
                return "U-Type";
            }
            if(str.equals(InstructionTypes.ujTypeInstructions.get(i))){
                return "UJ-Type";
            }
        }
        return "INVALID INSTRUCTION";
    }

    public static void writeInstructionToFile(String str) {

        try {
            File f1 = new File("assembly.asm");
            if(!f1.exists()) {
                f1.createNewFile();
            }
  
            FileWriter fileWritter = new FileWriter(f1.getName(),true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(str);
            bw.close();
            System.out.println("Instruction Added");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
