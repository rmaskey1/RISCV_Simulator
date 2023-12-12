
public class Memory {
    private byte[] memory;

    public Memory(int memorySize){
        this.memory = new byte[memorySize];
    }

    void storeWord(int addr, int data) {
        memory[addr]    = (byte) ((data & 0x000000FF));
        memory[addr+1]  = (byte) ((data & 0x0000FF00) >>> 8);
        memory[addr+2]  = (byte) ((data & 0x00FF0000) >>> 16);
        memory[addr+3]  = (byte) ((data & 0xFF000000) >>> 24);
    }

    // Returns word from memory given by address
    public int getWord(int addr){
        return (getHalfWord(addr+2) << 16) | (getHalfWord(addr) & 0xFFFF);
    }

    public byte[] getMemory() {
        return memory;
    }
}