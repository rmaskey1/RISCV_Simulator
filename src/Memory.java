package src;

public class Memory {
    private int[] memory;

    public Memory(int memorySize){
        this.memory = new int[memorySize];
    }

    void storeWord(int addr, int data) {
        this.memory[addr] = data;
    }

    // Returns word from memory given by address
    // public int getWord(int addr){
    //     return (getHalfWord(addr+2) << 16) | (getHalfWord(addr) & 0xFFFF);
    // }

    public int[] getMemory() {
        return memory;
    }
}