// Save this as AirlineSystem.java

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class MuftiAirline {
    String passport;
    String name;
    String email;
    String destination;
    int seatNum;
    MuftiAirline next;
}

public class AirlineSystem {
    static MuftiAirline head = null;
    static int seatCounter = 1;
    static final int MAX_SEATS = 15;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n\n\t\t********************************************************************");
            System.out.println("\t\t                   Welcome to Airline System                       ");
            System.out.println("\t\t********************************************************************");
            System.out.println("\n\t\t Please enter your choice from below (1-4):");
            System.out.println("\t\t 1. Reservation");
            System.out.println("\t\t 2. Cancel");
            System.out.println("\t\t 3. Display Records");
            System.out.println("\t\t 4. Exit");
            System.out.print("\n\t\t Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    reserve(sc);
                    break;
                case 2:
                    cancel(sc);
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
            }

        } while (choice != 4);

        sc.close();
    }

    static void reserve(Scanner sc) {
        if (seatCounter > MAX_SEATS) {
            System.out.println("\n\t\tAll seats are full.");
            return;
        }

        MuftiAirline newPassenger = new MuftiAirline();

        System.out.print("\n\tEnter your passport number: ");
        newPassenger.passport = sc.nextLine().trim();

        if (findPassengerByPassport(newPassenger.passport) != null) {
            System.out.println("\n\tPassenger with this passport already exists.");
            return;
        }

        System.out.print("\tEnter your name: ");
        newPassenger.name = sc.nextLine().trim();

        System.out.print("\tEnter your email address: ");
        newPassenger.email = sc.nextLine().trim();

        System.out.print("\tEnter your destination: ");
        newPassenger.destination = sc.nextLine().trim();

        newPassenger.seatNum = seatCounter++;
        newPassenger.next = null;

        if (head == null) {
            head = newPassenger;
        } else {
            MuftiAirline temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newPassenger;
        }

        System.out.println("\n\tSeat booking successful!");
        System.out.println("\tYour seat number is: Seat A-" + newPassenger.seatNum);
    }

    static void cancel(Scanner sc) {
        if (head == null) {
            System.out.println("\n\tNo bookings found.");
            return;
        }

        System.out.print("\n\tEnter passport number to delete record: ");
        String passport = sc.nextLine().trim();

        if (head.passport.equals(passport)) {
            head = head.next;
            System.out.println("\n\tBooking has been deleted.");
            return;
        }

        MuftiAirline temp = head;
        while (temp.next != null) {
            if (temp.next.passport.equals(passport)) {
                temp.next = temp.next.next;
                System.out.println("\n\tBooking has been deleted.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("\n\tPassport number not found.");
    }

    static void display() {
        if (head == null) {
            System.out.println("\n\tNo bookings to display.");
            return;
        }

        MuftiAirline temp = head;
        while (temp != null) {
            System.out.println("\n\nPassport Number: " + temp.passport);
            System.out.println("Name           : " + temp.name);
            System.out.println("Email          : " + temp.email);
            System.out.println("Destination    : " + temp.destination);
            System.out.println("Seat Number    : A-" + temp.seatNum);
            System.out.println("---------------------------------------------------");
            temp = temp.next;
        }
    }

    static void saveToFile() {
        try (FileWriter writer = new FileWriter("mufti_records.txt")) {
            MuftiAirline temp = head;
            while (temp != null) {
                writer.write(String.format("%-6s %-15s %-15s %-15s\n",
                        temp.passport, temp.name, temp.email, temp.destination));
                temp = temp.next;
            }
            System.out.println("\n\tDetails saved to 'mufti_records.txt'");
        } catch (IOException e) {
            System.out.println("\n\tError saving to file: " + e.getMessage());
        }
    }

    static MuftiAirline findPassengerByPassport(String passport) {
        MuftiAirline temp = head;
        while (temp != null) {
            if (temp.passport.equals(passport))
                return temp;
            temp = temp.next;
        }
        return null;
    }
}
