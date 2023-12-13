package src;
import java.util.HashMap;

public class Memory {
    private HashMap<Integer, Byte> memory;

    public Memory() {
        this.memory = new HashMap<>();
    }

    public byte getByte(int address) {
        return memory.getOrDefault(address, (byte) 0);
    }

    public void storeByte(int address, byte value) {
        memory.put(address, value);
    }

    public short getHalfWord(int address) {
        short value = 0;
        for (int i = 0; i < 2; i++) {
            value |= (getByte(address + i) & 0xFF) << (8 * i);
        }
        return value;
    }

    public void storeHalfWord(int address, short value) {
        for (int i = 0; i < 2; i++) {
            storeByte(address + i, (byte) ((value >> (8 * i)) & 0xFF));
        }
    }

    public int getWord(int address) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (getByte(address + i) & 0xFF) << (8 * i);
        }
        return value;
    }

    public void storeWord(int address, int value) {
        for (int i = 0; i < 4; i++) {
            storeByte(address + i, (byte) ((value >> (8 * i)) & 0xFF));
        }
    }

    public String getString(int address) {
        StringBuilder sb = new StringBuilder();
        byte currentByte;
        while ((currentByte = getByte(address++)) != 0) {
            sb.append((char) currentByte);
        }
        return sb.toString();
    }

    public HashMap<Integer, Byte> getMemoryMap() {
        return memory;
    }
}