import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class ReadInstruction {

    public static void readInstruction() {
        ArrayList<String> lines = new ArrayList<>();
        try {

            System.out.println("What is the file name");
            Scanner scan = new Scanner(System.in);
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lines.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {

        }


    }

    public static void exectuteInstruction(String instruction){
        String instructionType = " "; //Call the methods that vivek wrote
        switch (instructionType) {

            case "R-Type":

                break;

            case "I-Type":



            case "S-Type":



            case "SB-Type":



            case "U-Type":



            case "UJ-Type":
        }
    }
}
