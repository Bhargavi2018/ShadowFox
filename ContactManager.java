import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

class Contact {
    private String name;
    private String phone;
    private String email;

    // Constructor
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone + " | Email: " + email;
    }
}

public class ContactManager {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch(choice) {
                    case 1: addContact(); break;
                    case 2: viewContacts(); break;
                    case 3: updateContact(); break;
                    case 4: deleteContact(); break;
                    case 5: running = false; break;
                    default: System.out.println("Invalid choice!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.next(); // clear invalid input
            }
        }
        System.out.println("Exiting Contact Manager. Bye!");
    }

    private static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        String phone;
        while(true) {
            System.out.print("Enter Phone (numbers only): ");
            phone = sc.nextLine();
            if(phone.matches("\\d+")) break;
            System.out.println("Invalid phone number!");
        }

        String email;
        while(true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if(Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) break;
            System.out.println("Invalid email!");
        }

        // Check for duplicate phone
        for(Contact c : contacts) {
            if(c.getPhone().equals(phone)) {
                System.out.println("Contact with this phone number already exists!");
                return;
            }
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if(contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("\n--- Contact List ---");
        for(Contact c : contacts) {
            System.out.println(c);
        }
    }

    private static void updateContact() {
        System.out.print("Enter Phone number of contact to update: ");
        String phone = sc.nextLine();

        for(Contact c : contacts) {
            if(c.getPhone().equals(phone)) {
                System.out.print("Enter new Name (leave blank to keep current): ");
                String name = sc.nextLine();
                if(!name.isEmpty()) c.setName(name);

                System.out.print("Enter new Email (leave blank to keep current): ");
                String email = sc.nextLine();
                if(!email.isEmpty()) c.setEmail(email);

                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact not found!");
    }

    private static void deleteContact() {
        System.out.print("Enter Phone number of contact to delete: ");
        String phone = sc.nextLine();

        for(Contact c : contacts) {
            if(c.getPhone().equals(phone)) {
                contacts.remove(c);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }
        System.out.println("Contact not found!");
    }
}