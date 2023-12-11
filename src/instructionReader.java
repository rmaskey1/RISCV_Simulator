package src;
import src.InstructionTypes;


public class instructionReader {

    public static String determineInstruction(String str) {
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
}
