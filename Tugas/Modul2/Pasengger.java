package Tugas.Modul2;

import java.util.ArrayList;
import java.util.List;

// 1. Class Passenger
class Passenger {
    // Attributes
    private String name;
    private String email;
    private String phone;
    private int passengerId;
    private int age;

    // Static variable for auto-incrementing passengerId
    private static int idCounter = 1;

    // Constructor
    public Passenger(String name, String email, String phone, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        // Auto-increment automatically when a new object is created
        this.passengerId = idCounter++; 
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getPassengerId() { return passengerId; }
    // No setter for passengerId to maintain the integrity of the auto-increment

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // displayInfo()
    public void displayInfo() {
        System.out.println("ID: " + passengerId + " | Name: " + name + " | Age: " + age + 
                           " | Email: " + email + " | Phone: " + phone);
    }

    // toString()
    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + passengerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}

// 2. Class PassengerManager
class PassengerManager {
    // Data Structure
    private ArrayList<Passenger> passengers;

    public PassengerManager() {
        this.passengers = new ArrayList<>();
    }

    // Required Operations:

    // 1. Add Passenger
    public void addPassenger(Passenger p) {
        passengers.add(p);
        System.out.println("Added: " + p.getName());
    }

    // 2. Get Passenger (by index)
    public Passenger getPassenger(int index) {
        if (index >= 0 && index < passengers.size()) {
            return passengers.get(index);
        }
        System.out.println("Error: Index out of bounds.");
        return null;
    }

    // 3. Search (Linear Search by name)
    public List<Passenger> searchByName(String searchName) {
        List<Passenger> results = new ArrayList<>();
        for (Passenger p : passengers) {
            // Using equalsIgnoreCase for a more robust search
            if (p.getName().equalsIgnoreCase(searchName)) {
                results.add(p);
            }
        }
        return results;
    }

    // 4. Update (modify details at a specific index)
    public void updatePassenger(int index, String newName, String newEmail, String newPhone, int newAge) {
        if (index >= 0 && index < passengers.size()) {
            Passenger p = passengers.get(index);
            p.setName(newName);
            p.setEmail(newEmail);
            p.setPhone(newPhone);
            p.setAge(newAge);
            System.out.println("Passenger at index " + index + " updated successfully.");
        } else {
            System.out.println("Error: Index out of bounds. Update failed.");
        }
    }

    // 5a. Remove (by Index)
    public void removeByIndex(int index) {
        if (index >= 0 && index < passengers.size()) {
            Passenger removed = passengers.remove(index);
            System.out.println("Removed passenger at index " + index + ": " + removed.getName());
        } else {
            System.out.println("Error: Index out of bounds. Remove failed.");
        }
    }

    // 5b. Remove (by Passenger ID)
    public void removeById(int id) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getPassengerId() == id) {
                System.out.println("Removed passenger with ID " + id + ": " + passengers.get(i).getName());
                passengers.remove(i);
                return; // Exit once the unique ID is found and removed
            }
        }
        System.out.println("Error: Passenger with ID " + id + " not found.");
    }

    // 6. Display All
    public void displayAll() {
        System.out.println("\n--- All Registered Passengers ---");
        if (passengers.isEmpty()) {
            System.out.println("The system is currently empty.");
        } else {
            for (Passenger p : passengers) {
                p.displayInfo();
            }
        }
        System.out.println("---------------------------------\n");
    }
}

// Main class to run and test the implementation
public class Pasengger {
    public static void main(String[] args) {
        PassengerManager manager = new PassengerManager();

        System.out.println("=== Testing Passenger Management System ===");

        // 1. Add Passengers
        manager.addPassenger(new Passenger("Alice Smith", "alice@email.com", "555-0101", 28));
        manager.addPassenger(new Passenger("Bob Johnson", "bob@email.com", "555-0102", 34));
        manager.addPassenger(new Passenger("Charlie Brown", "charlie@email.com", "555-0103", 22));

        // 6. Display All
        manager.displayAll();

        // 2. Get Passenger
        System.out.println("Getting passenger at index 1:");
        Passenger p1 = manager.getPassenger(1);
        if (p1 != null) System.out.println(p1.toString());

        // 3. Search Passenger
        System.out.println("\nSearching for 'Alice Smith':");
        List<Passenger> searchResults = manager.searchByName("Alice Smith");
        for (Passenger p : searchResults) {
            p.displayInfo();
        }

        // 4. Update Passenger
        System.out.println("\nUpdating passenger at index 2 (Charlie Brown):");
        manager.updatePassenger(2, "Charles Brown", "charles@newemail.com", "555-9999", 23);
        manager.displayAll();

        // 5. Remove Passengers
        System.out.println("Removing passenger at index 0:");
        manager.removeByIndex(0);
        manager.displayAll();

        System.out.println("Removing passenger with ID 2 (Bob Johnson):");
        manager.removeById(2);
        manager.displayAll();
    }
}
