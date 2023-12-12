package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class machineToAssembly {
    public static void machineToAssembly(){
        ArrayList<String> machineLanguageLine = new ArrayList<>();
        try {
            System.out.println("What is the file path (Ex. C:\\Users\\Name\\downloads\\Simulator_tests\\zip\\addi_hazards.dat)");
            Scanner scan = new Scanner(System.in);
            String fileName = scan.nextLine();

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
            System.out.println("Assembly Form " + bitToAssembly(machineLanguageLine.get(i)));
            System.out.println("");
        }

    }

    public static String bitToAssembly(String doubleWord){
        return null;
    }



    public static void main(String[] args){
        machineToAssembly();
    }

}
