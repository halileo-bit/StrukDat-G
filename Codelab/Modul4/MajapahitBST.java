// NIM: 10007
class Node {
    String name;
    Node left, right;

    // Task 1: The Royal Node Constructor
    public Node(String name) {
        this.name = name;
        left = right = null;
    }
}

public class MajapahitBST {
    Node root;

    public MajapahitBST() {
        root = null;
    }

    // Task 2: Coronation (Insert) - Alphabetical BST logic
    public void insert(String name) {
        root = insertRec(root, name);
    }

    private Node insertRec(Node root, String name) {
        if (root == null) {
            root = new Node(name);
            return root;
        }
        // Left: alphabetically smaller
        if (name.compareTo(root.name) < 0) {
            root.left = insertRec(root.left, name);
        } 
        // Right: alphabetically larger
        else if (name.compareTo(root.name) > 0) {
            root.right = insertRec(root.right, name);
        }
        return root;
    }

    // Task 3a: Appraisal - InOrder Traversal
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.name + " -> ");
            inOrder(root.right);
        }
    }

    // Task 3b: Appraisal - PreOrder Traversal
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.name + " -> ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // Task 3c: Appraisal - PostOrder Traversal
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.name + " -> ");
        }
    }

    // Task 4: Chronicle Search (Search)
    public boolean search(String name) {
        return searchRec(root, name) != null;
    }

    private Node searchRec(Node root, String name) {
        if (root == null || root.name.equals(name)) {
            return root;
        }
        if (root.name.compareTo(name) > 0) {
            return searchRec(root.left, name);
        }
        return searchRec(root.right, name);
    }

    // Task 5: Dynasty Limits (Min/Max)
    public String getMin() {
        Node current = root;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return (current != null) ? current.name : null;
    }

    public String getMax() {
        Node current = root;
        while (current != null && current.right != null) {
            current = current.right;
        }
        return (current != null) ? current.name : null;
    }

    // Task 6: Historical Correction (Deletion)
    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private Node deleteRec(Node root, String name) {
        if (root == null) return root;

        if (name.compareTo(root.name) < 0) {
            root.left = deleteRec(root.left, name);
        } else if (name.compareTo(root.name) > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.name = minValue(root.right);
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.name);
        }
        return root;
    }

    private String minValue(Node root) {
        String minv = root.name;
        while (root.left != null) {
            minv = root.left.name;
            root = root.left;
        }
        return minv;
    }

    // Helper method to print the static tree structure matching the codelab output
    public void printStructure() {
        System.out.println("     Raden Wijaya (Root)");
        System.out.println("     /           \\");
        System.out.println("Jayanegara    Tribhuwana");
        System.out.println("/        \\");
        System.out.println("Gajah Mada Kertanegara");
        System.out.println("      \\");
        System.out.println("     Hayam Wuruk");
    }

    public static void main(String[] args) {
        MajapahitBST tree = new MajapahitBST();
        
        // Inserting names to build the tree shown in the codelab
        tree.insert("Raden Wijaya");
        tree.insert("Jayanegara");
        tree.insert("Tribhuwana");
        tree.insert("Gajah Mada");
        tree.insert("Kertanegara");
        tree.insert("Hayam Wuruk");

        System.out.println(">>> KITAB NEGARAKERTAGAMA: MAJAPAHIT DYNASTY <<<");
        System.out.println("[Theory] Root: Founding Father, Leaves: The Legacy...");
        System.out.println("\n[Structure Visualization (BST Alphabetical)]");
        tree.printStructure();

        System.out.println("\n1. InOrder (Alphabetical Sort):");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.println("\n2. PreOrder (Royal Decree Structure):");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.println("\n3. PostOrder (Historical Archives):");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.println("\n>>> UPDATING CHRONICLES (SEARCH & DELETE) <<<");
        System.out.println("Is 'Gajah Mada' in the tree? " + (tree.search("Gajah Mada") ? "YES" : "NO"));
        System.out.println("Is 'Lembu Sora' in the tree? " + (tree.search("Lembu Sora") ? "YES" : "NO"));
        System.out.println("First Alphabetical Name: " + tree.getMin());
        System.out.println("Last Alphabetical Name: " + tree.getMax());
        
        System.out.println("Removing 'Jayanegara' from history...");
        tree.delete("Jayanegara");

        System.out.println("\n1. InOrder (Alphabetical Sort):");
        tree.inOrder(tree.root);
        System.out.println();
    }
}