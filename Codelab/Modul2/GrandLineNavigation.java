package Codelab.Modul2;

class Island {
    String name;
    Island next;

    // 1. Map Structure: Node Constructor
    Island(String name) {
        this.name = name;
        this.next = null;
    }
}

public class GrandLineNavigation {
    private Island head;

    // 1. Chart Course: Add to the end
    public void addIsland(String name) {
        Island newIsland = new Island(name);
        if (head == null) {
            head = newIsland;
            return;
        }
        Island current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newIsland;
    }

    // 2. Buster Call: Delete an island
    public void busterCall(String name) {
        if (head == null) return;

        if (head.name.equals(name)) {
            head = head.next;
            return;
        }

        Island current = head;
        while (current.next != null && !current.next.name.equals(name)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next; // Re-link previous to next
        }
    }

    // 3. Logbook: Traverse and print
    public void printLogbook() {
        System.out.print("Grand Line Route: ");
        Island current = head;
        while (current != null) {
            System.out.print(current.name + (current.next != null ? " -> " : " (End)"));
            current = current.next;
        }
        System.out.println();
    }

    // 4. Check: Search for an island
    public boolean isIslandOnRoute(String name) {
        Island current = head;
        while (current != null) {
            if (current.name.equals(name)) return true;
            current = current.next;
        }
        return false;
    }

    // 5. Adventure Count: Tally up
    public int countIslands() {
        int count = 0;
        Island current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        GrandLineNavigation route = new GrandLineNavigation();
        
        route.addIsland("Alabasta");
        route.addIsland("Skypiea");
        route.addIsland("Water 7");
        route.addIsland("Enies Lobby");

        route.printLogbook();
        System.out.println("Visited Fishman Island? " + route.isIslandOnRoute("Fishman Island"));

        System.out.println("Buster Call initiated on Enies Lobby!");
        route.busterCall("Enies Lobby");

        route.printLogbook();
        System.out.println("Total Islands visited: " + route.countIslands());
    }
}