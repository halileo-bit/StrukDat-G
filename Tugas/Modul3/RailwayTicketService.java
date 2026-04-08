package Tugas.Modul3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class RailwayTicketService {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Data Structures requirement
        Queue<String> serviceQueue = new LinkedList<>();
        Stack<String> transactionHistory = new Stack<>();

        // Infinite loop to keep the console menu running
        while (true) {
            System.out.println("\n=== Railway Ticket Service ===");
            System.out.println("1. Add Passenger");
            System.out.println("2. Display Queue");
            System.out.println("3. Serve Passenger");
            System.out.println("4. Undo Last Transaction");
            System.out.print("Choose menu: ");

            // Validate that the user enters an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1:
                    // Create: Add passenger to queue
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    serviceQueue.add(name);
                    System.out.println("Passenger added to queue.");
                    break;

                case 2:
                    // Read: Display current queue
                    System.out.println("Current Queue:");
                    if (serviceQueue.isEmpty()) {
                        System.out.println("Queue is empty.");
                    } else {
                        int index = 1;
                        for (String passenger : serviceQueue) {
                            System.out.println(index + ". " + passenger);
                            index++;
                        }
                    }
                    break;

                case 3:
                    // Serve: FIFO process
                    if (serviceQueue.isEmpty()) {
                        System.out.println("No passengers in queue to serve.");
                    } else {
                        // poll() removes and returns the first element in the queue
                        String servedPassenger = serviceQueue.poll();
                        System.out.println("Serving passenger: " + servedPassenger);
                        
                        // push() adds it to the top of the history stack
                        transactionHistory.push(servedPassenger);
                        System.out.println("Transaction saved.");
                    }
                    break;

                case 4:
                    // Undo: LIFO process
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions to undo.");
                    } else {
                        // pop() removes and returns the most recent transaction
                        String undonePassenger = transactionHistory.pop();
                        System.out.println("Undo transaction for passenger: " + undonePassenger);
                        
                        // Add them back into the queue (they will go to the back of the line)
                        serviceQueue.add(undonePassenger);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                    break;
            }
        }
    }
}
