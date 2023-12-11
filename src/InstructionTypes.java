package src;
import java.util.ArrayList;
public class InstructionTypes {
    public static final ArrayList<String> rTypeInstructions = new ArrayList<>();

    public static final ArrayList<String> iTypeInstructions = new ArrayList<>();
    public static final ArrayList<String> sTypeInstructions = new ArrayList<>();
    public static final ArrayList<String> sbTypeInstructions = new ArrayList<>();
    public static final ArrayList<String> uTypeInstructions = new ArrayList<>();
    public static final ArrayList<String> ujTypeInstructions = new ArrayList<>();

    static{
        //R-Type instructions
        rTypeInstructions.add("add");
        rTypeInstructions.add("addw");
        rTypeInstructions.add("and");
        rTypeInstructions.add("or");
        rTypeInstructions.add("sll");
        rTypeInstructions.add("sllw");
        rTypeInstructions.add("slt");
        rTypeInstructions.add("sltu");
        rTypeInstructions.add("sra");
        rTypeInstructions.add("sraw");
        rTypeInstructions.add("srl");
        rTypeInstructions.add("srlw");
        rTypeInstructions.add("sub");
        rTypeInstructions.add("subw");
        rTypeInstructions.add("xor");
        rTypeInstructions.add("mul");
        rTypeInstructions.add("mulh");
        rTypeInstructions.add("mulhsu");
        rTypeInstructions.add("mulhu");
        rTypeInstructions.add("div");
        rTypeInstructions.add("divu");
        rTypeInstructions.add("rem");
        rTypeInstructions.add("remu");


        //I-Type instrutions
        iTypeInstructions.add("lb");
        iTypeInstructions.add("lh");
        iTypeInstructions.add("lw");
        iTypeInstructions.add("lbu");
        iTypeInstructions.add("lhu");
        iTypeInstructions.add("addi");
        iTypeInstructions.add("slti");
        iTypeInstructions.add("sltiu");
        iTypeInstructions.add("xori");
        iTypeInstructions.add("ori");
        iTypeInstructions.add("andi");
        iTypeInstructions.add("slli");
        iTypeInstructions.add("srli");
        iTypeInstructions.add("srai");
        iTypeInstructions.add("jalr");
        iTypeInstructions.add("beq");
        iTypeInstructions.add("bne");
        iTypeInstructions.add("blt");
        iTypeInstructions.add("bge");
        iTypeInstructions.add("bltu");
        iTypeInstructions.add("bgeu");
        iTypeInstructions.add("ecall");
        iTypeInstructions.add("ebreak");
        iTypeInstructions.add("csrrw");
        iTypeInstructions.add("csrrs");
        iTypeInstructions.add("csrrc");
        iTypeInstructions.add("csrrwi");
        iTypeInstructions.add("csrrsi");
        iTypeInstructions.add("csrrci");

        //S-Type instructions
        sTypeInstructions.add("sb");
        sTypeInstructions.add("sh");
        sTypeInstructions.add("sw");
        sTypeInstructions.add("sd");
        sTypeInstructions.add("fsd");
        sTypeInstructions.add("fsw");

        //SB-Type instructions
        sbTypeInstructions.add("beq");
        sbTypeInstructions.add("bne");
        sbTypeInstructions.add("blt");
        sbTypeInstructions.add("bge");
        sbTypeInstructions.add("bltu");
        sbTypeInstructions.add("bgeu");

        //U-Type instruction
        uTypeInstructions.add("auipc");
        uTypeInstructions.add("lui");

        //UJ-Type instruction
        ujTypeInstructions.add("jal");
    }
}
