package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.data.Contact;

import static java.nio.file.Files.write;

public class Main {

    public Main() throws IOException {

        String directory = "./src/data";
        String filename = "contactList.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);}
        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);}

        ArrayList<Contact> contacts = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean running = true;
//        Files.writeString(dataFile,"dave 123456789");

        while (running) {
            System.out.println("1. View Contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
//                    System.out.println("View contacts");
                    List<String> output = Files.readAllLines(dataFile);
                    System.out.println(output);
//                    for (int i = 0; i < output.size(); i++){
//                        System.out.println(output.get(i));
//                    }
                    break;
                case 2:
                    System.out.println("Add a new contact");
                    boolean addingContact =  true;
                    while (addingContact){
                        System.out.println("What is your contacts name?");
                        String val1 = input.next();
                        System.out.println("What is your contact's phone number?");
                        String val2 = input.next();
                        System.out.printf("Your contact is %s - %s? [y/n]",val1,val2);
                        String choice2 = input.next();
                        if (choice2.equalsIgnoreCase("y")){
                            Files.writeString(dataFile,(val1 + " " + val2 + "\n"), StandardOpenOption.APPEND);
                            addingContact = false;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Search a contact by name");
                    break;
                case 4:
                    System.out.println("Delete an existing contact");
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    continue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m1 = new Main();
    }
}