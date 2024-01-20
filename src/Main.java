import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);

//        System.out.println("Enter file path:");
        Scanner fileScanner = new Scanner(new File(args[0]));

        Catalogue catalogue = new Catalogue(fileScanner);

        while (true) {
            System.out.println("""
                    Please make your choice:\s
                    1 – Print all bikes & protein shakes
                    2 – Add a new protein shake
                    3 – Propose a random protein shake to the customer
                    4 – Show bikes that train a specific muscle group
                    5 – Write to file
                    6 – Stop the program
                    """);

            int choice = inputScanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(catalogue);
                    break;
                case 2:
                    catalogue.addNewShake(inputScanner);
                    break;
                case 3:
                    catalogue.offerRandomShake();
                    break;
                case 4:
                    catalogue.searchMuscleGroup(inputScanner);
                    break;
                case 5:
                    FileWriter fileWriter = new FileWriter("output.txt");
                    catalogue.writeOutput(fileWriter);
                    fileWriter.flush();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }
}
