import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public Main() {
        HashMap<String, Contact> contacts = new HashMap<>();
        Scanner input = new Scanner(System.in);
        boolean running = true;

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
                    System.out.println("Choice 1");
                    break;
                case 2:
                    System.out.println("Choice 1");
                    break;
                case 3:
                    System.out.println("Choice 1");
                    break;
                case 4:
                    System.out.println("Choice 1");
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

    public static void main(String[] args) {
        Main m1 = new Main();
    }
}