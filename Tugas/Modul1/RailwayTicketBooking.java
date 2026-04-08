package Tugas.Modul1;

import java.util.Scanner;

// Requirement 2: Define an enum named TicketClass
enum TicketClass {
    ECONOMY, BUSINESS, EXECUTIVE
}

// Requirement 1: Generic class representing a train passenger
class Passenger<T> {
    private String name;
    private T identityNumber; // Generic type T for flexible ID types

    public Passenger(String name, T identityNumber) {
        this.name = name;
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public T getIdentityNumber() {
        return identityNumber;
    }
}

// Requirement 1: Generic class representing a train ticket
class Ticket<T> {
    private String bookingCode;
    private Passenger<T> passenger;
    private TicketClass ticketClass;

    public Ticket(String bookingCode, Passenger<T> passenger, TicketClass ticketClass) {
        this.bookingCode = bookingCode;
        this.passenger = passenger;
        this.ticketClass = ticketClass;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public Passenger<T> getPassenger() {
        return passenger;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }
}

public class RailwayTicketBooking {

    // Requirement 3: Implement at least one method that uses wildcards (<?>)
    // This allows the method to accept a Ticket with ANY generic type (Integer, Long, String, etc.)
    public static void displayTicketInfo(Ticket<?> ticket) {
        System.out.println("\n=== Ticket Information ===");
        System.out.println("Booking Code     : " + ticket.getBookingCode());
        System.out.println("Passenger Name   : " + ticket.getPassenger().getName());
        
        // Dynamically grab the class name of whatever type the Identity Number currently is
        Object id = ticket.getPassenger().getIdentityNumber();
        System.out.println("Identity Type    : " + id.getClass().getSimpleName());
        System.out.println("Identity Number  : " + id);
        System.out.println("Ticket Class     : " + ticket.getTicketClass());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Railway Ticket Booking ===");
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Identity Number: ");
        String idInput = scanner.nextLine();

        System.out.print("Enter Booking Code: ");
        String bookingCode = scanner.nextLine();

        System.out.println("\nSelect Ticket Class:");
        System.out.println("1. ECONOMY");
        System.out.println("2. BUSINESS");
        System.out.println("3. EXECUTIVE");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        TicketClass ticketClass = switch (choice) {
            case 1 -> TicketClass.ECONOMY;
            case 2 -> TicketClass.BUSINESS;
            case 3 -> TicketClass.EXECUTIVE;
            default -> TicketClass.ECONOMY; // Fallback
        };

        /* * Logic to determine if the ID should be an Integer or a Long
         * to match the assignment's expected output dynamically.
         */
        try {
            // First, try to parse it as a standard Integer
            Integer intId = Integer.parseInt(idInput);
            Passenger<Integer> passenger = new Passenger<>(name, intId);
            Ticket<Integer> ticket = new Ticket<>(bookingCode, passenger, ticketClass);
            displayTicketInfo(ticket);
            
        } catch (NumberFormatException e) {
            try {
                // If it's too big for an Integer, try parsing as a Long
                Long longId = Long.parseLong(idInput);
                Passenger<Long> passenger = new Passenger<>(name, longId);
                Ticket<Long> ticket = new Ticket<>(bookingCode, passenger, ticketClass);
                displayTicketInfo(ticket);
                
            } catch (NumberFormatException ex) {
                // Fallback to String if it contains letters/characters
                Passenger<String> passenger = new Passenger<>(name, idInput);
                Ticket<String> ticket = new Ticket<>(bookingCode, passenger, ticketClass);
                displayTicketInfo(ticket);
            }
        }
        
        scanner.close();
    }
}