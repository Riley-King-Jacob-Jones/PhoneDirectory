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

public class Main {

//    public ArrayList<Contact> parseToContacts(Path filename) throws IOException {
//        List<String> lines = Files.readAllLines(filename);
//        ArrayList<Contact> contacts = new ArrayList<>();
//        for (int i = 0 ; i < lines.size(); i ++ ){
//            String [] parts = lines.get(i).split(" ");
//            contacts.add(new Contact(parts[1],Integer.parseInt((parts[2]))));
//        }
//        return contacts;
//    }

    private List<String> output;

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
        output = Files.readAllLines(dataFile);

        while (running) {
            System.out.println("1. View Contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            int choice = input.nextInt();
            String choice2;
            switch (choice) {
                case 1:
                    output = Files.readAllLines(dataFile);
                    System.out.println("Name | Phone number");
                    System.out.println("---------------");
                    for (int i = 0; i < output.size(); i++){
                        String[] broken = output.get(i).split(" ");
                        System.out.printf("%s | %s\n",broken[0],broken[1]);
                    }
//                    System.out.println(output);
//                    System.out.println();
                    break;
                case 2:
                    boolean addingContact =  true;
                    while (addingContact){
                        System.out.println("What is your contacts name?");
                        String val1 = input.next().toLowerCase();
                        System.out.println("What is your contact's phone number?");
                        String val2 = input.next().toLowerCase();
                        System.out.printf("Your contact is %s - %s? [y/n]",val1,val2);
                        choice2 = input.next();
                        if (choice2.equalsIgnoreCase("y")){
                            Files.writeString(dataFile,(val1 + " " + val2 + "\n"), StandardOpenOption.APPEND);
                            addingContact = false;
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Please enter the name to search for");
                    choice2 = input.next();
                    choice2 = choice2.toLowerCase();
                    for (int i = 0; i < output.size(); i++){
                        if (output.get(i).contains(choice2)){
                            System.out.println(output.get(i));
                        }
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Please enter the name of the contact you wish to delete");
                    choice2 = input.next();
                    choice2 = choice2.toLowerCase();
                    for (int i = 0; i < output.size(); i++){
                        if (output.get(i).contains(choice2)){
                            output.remove(i);
                            Files.write(dataFile, output);

//                            System.out.println(output.get(i));
                        }
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    System.out.println();
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