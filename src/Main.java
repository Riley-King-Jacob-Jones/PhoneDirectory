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
    private final Scanner input = new Scanner(System.in);

    //  .txt file -> List of contact objects (String name, int phoneNumber)
    public ArrayList<Contact> parseToContacts(Path filename) throws IOException {
        List<String> lines = Files.readAllLines(filename);
        ArrayList<Contact> contacts = new ArrayList<>();
        for (int i = 0 ; i < lines.size(); i ++ ){
            String[] parts = lines.get(i).split(" ");
//            System.out.printf("%s %s \n", parts[0],parts[1]);
            contacts.add(new Contact(parts[0],parts[1]));
        }
        return contacts;
    }

    public String contactsToString(List<Contact> contacts){
        String results = "";
            for (Contact contact : contacts){
                results += contact.toString() + "\n";
            }
        return results;
    }

    //  Default display, basic input handling
    public int mainMenu(){
        System.out.println("1. View Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");

        return input.nextInt();
    }

    public Main() throws IOException {

        //  File existence verification
        String directory = "./src/data";
        String filename = "contactList.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);}
        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);}

        //  Generate editable list of contact objects
        List<Contact> contacts = parseToContacts(dataFile);
//        contactsToString(contacts);
        boolean running = true;
        int choice;
        String choice2;

        while (running) {
            choice = mainMenu();
            switch (choice) {
                case 1:
                    //  VIEW CONTACTS
                    contacts = parseToContacts(dataFile);
                    System.out.printf("%10s | %10s","Name","Number\n");
                    System.out.println("  ---------------------- ");
                    for (int i = 0; i < contacts.size(); i++){
                        System.out.println(contacts.get(i).toStringFormatted());
                    }
                    System.out.println();
                    break;

                case 2:
                    //  ADD CONTACTS
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
                    //  SEARCH CONTACTS
                    System.out.println("Please enter the name to search for");
                    choice2 = input.next();
                    choice2 = choice2.toLowerCase();
                    boolean found = false;
                    for (int i = 0; i < contacts.size(); i++){
                        if (contacts.get(i).getName().contains(choice2)) {
                            System.out.println(contacts.get(i).toStringFormatted());
                            found = true;
                        }
                        //  If no contact was found
                        if (i == contacts.size()-1 && ! found){
                            System.out.println("i == contacts.size()");
                            System.out.printf("Sorry no contact named %s was found", choice2);
                        }
                    }
                    System.out.println();
                    break;

                case 4:
                    //  DELETE CONTACTS
                    System.out.println("Please enter the name of the contact you wish to delete");
                    choice2 = input.next();
                    choice2 = choice2.toLowerCase();
                    for (int i = 0; i < contacts.size(); i++){
                        // TODO: Are you sure you wish to delete Name | Number?
                        if (contacts.get(i).getName().contains(choice2)){
                            System.out.printf("Are you sure you want to delete\n%s?\n[y/N]",contacts.get(i).toStringFormatted());
                            choice2 = input.next();
                            if (choice2.equalsIgnoreCase("y") || choice2.equalsIgnoreCase("yes")){
                                contacts.remove(i);
                                Files.writeString(dataFile, contactsToString(contacts));
                            }
                        }
                    }
                    System.out.println();
                    break;

                case 5:
                    //  QUIT PROGRAM
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}