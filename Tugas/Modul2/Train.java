package Tugas.Modul2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 1. Class Schedule
class Schedule {
    // Attributes
    private int scheduleId;
    private String trainCode;
    private String trainName;
    private String origin;
    private String destination;
    private String departureTime;
    private double baseFare;

    // Static variable for auto-incrementing scheduleId
    private static int idCounter = 1;

    // Constructor
    public Schedule(String trainCode, String trainName, String origin, 
                    String destination, String departureTime, double baseFare) {
        this.scheduleId = idCounter++; // Auto-incremented unique ID
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.baseFare = baseFare;
    }

    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    // No setter for scheduleId to maintain uniqueness

    public String getTrainCode() { return trainCode; }
    public void setTrainCode(String trainCode) { this.trainCode = trainCode; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public double getBaseFare() { return baseFare; }
    public void setBaseFare(double baseFare) { this.baseFare = baseFare; }

    // displayInfo()
    public void displayInfo() {
        System.out.printf("ID: %d | Code: %s | Name: %s | Route: %s -> %s | Time: %s | Fare: $%.2f%n",
                scheduleId, trainCode, trainName, origin, destination, departureTime, baseFare);
    }
}

// 2. Class ScheduleManager
class ScheduleManager {
    // Data Structure
    private LinkedList<Schedule> schedules;

    public ScheduleManager() {
        this.schedules = new LinkedList<>();
    }

    // 1. Add First/Last
    public void addFirst(Schedule s) {
        schedules.addFirst(s);
        System.out.println("Added to beginning: " + s.getTrainCode());
    }

    public void addLast(Schedule s) {
        schedules.addLast(s);
        System.out.println("Added to end: " + s.getTrainCode());
    }

    // 2. Remove First/Last
    public void removeFirst() {
        if (!schedules.isEmpty()) {
            Schedule removed = schedules.removeFirst();
            System.out.println("Removed from beginning: " + removed.getTrainCode());
        } else {
            System.out.println("List is empty. Cannot remove first.");
        }
    }

    public void removeLast() {
        if (!schedules.isEmpty()) {
            Schedule removed = schedules.removeLast();
            System.out.println("Removed from end: " + removed.getTrainCode());
        } else {
            System.out.println("List is empty. Cannot remove last.");
        }
    }

    // 3. Search Routes (by Origin OR Destination)
    public List<Schedule> searchRoutes(String location) {
        List<Schedule> foundRoutes = new ArrayList<>();
        for (Schedule s : schedules) {
            if (s.getOrigin().equalsIgnoreCase(location) || s.getDestination().equalsIgnoreCase(location)) {
                foundRoutes.add(s);
            }
        }
        return foundRoutes;
    }

    // 4. Iterator Traversal
    public void displayAll() {
        System.out.println("\n--- Train Schedules ---");
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }
        
        // Using an Iterator to traverse and display the list
        Iterator<Schedule> iterator = schedules.iterator();
        while (iterator.hasNext()) {
            Schedule s = iterator.next();
            s.displayInfo();
        }
        System.out.println("-----------------------\n");
    }

    // 5. Cleanup: Remove specific schedule using Iterator
    public void removeScheduleById(int targetId) {
        Iterator<Schedule> iterator = schedules.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Schedule s = iterator.next();
            if (s.getScheduleId() == targetId) {
                // IMPORTANT: Using iterator.remove() prevents ConcurrentModificationException
                iterator.remove(); 
                System.out.println("Successfully removed Schedule ID: " + targetId);
                found = true;
                break; // Assuming IDs are unique, we can stop searching
            }
        }

        if (!found) {
            System.out.println("Schedule ID " + targetId + " not found.");
        }
    }
}

// Main class to test the implementation
public class Train {
    public static void main(String[] args) {
        ScheduleManager manager = new ScheduleManager();

        System.out.println("=== Testing Train Schedule System ===");

        // Testing 1: Add First/Last
        manager.addLast(new Schedule("TRN002", "Express Blue", "Chicago", "New York", "10:00", 120.50));
        manager.addLast(new Schedule("TRN003", "Night Owl", "New York", "Boston", "22:30", 65.00));
        // Add an immediate departure to the front of the queue
        manager.addFirst(new Schedule("TRN001", "Morning Sunrise", "Boston", "Chicago", "06:00", 150.00));

        // Testing 4: Iterator Traversal
        manager.displayAll();

        // Testing 3: Search Routes
        System.out.println("Searching for routes involving 'New York':");
        List<Schedule> nyRoutes = manager.searchRoutes("New York");
        for (Schedule s : nyRoutes) {
            s.displayInfo();
        }
        System.out.println();

        // Testing 2: Remove First/Last
        manager.removeFirst(); // Removes TRN001
        manager.removeLast();  // Removes TRN003
        manager.displayAll();

        // Let's add a few more to test cleanup
        manager.addLast(new Schedule("TRN004", "Silver Bullet", "Denver", "Seattle", "14:00", 210.00));
        manager.addLast(new Schedule("TRN005", "Coastal Link", "Seattle", "Portland", "16:00", 45.00));
        manager.displayAll();

        // Testing 5: Cleanup (Iterator safe removal)
        System.out.println("Performing cleanup of Schedule ID 4...");
        manager.removeScheduleById(4); 
        manager.displayAll();
    }
}