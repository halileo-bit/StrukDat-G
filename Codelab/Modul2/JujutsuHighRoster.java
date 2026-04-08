package Codelab.Modul2;

import java.util.ArrayList;

public class JujutsuHighRoster {
    public static void main(String[] args) {
        ArrayList<String> sorcerers = new ArrayList<>();

        sorcerers.add("Itadori");
        sorcerers.add("Fushiguro");
        sorcerers.add("Kugisaki");
        System.out.println("First Years Assembled: " + sorcerers);

        sorcerers.add(0, "Okkotsu");
        System.out.println("After Yuta joins: " + sorcerers);

        String studentAtIndex2 = sorcerers.get(2);
        System.out.println("Student at index 2 is: " + studentAtIndex2);

        sorcerers.set(1, "Ryomen Sukuna");
        System.out.println("Oh no, Itadori switched!: " + sorcerers);

        sorcerers.remove(3); 
        System.out.println("After the Shibuya Incident: " + sorcerers);

        System.out.println("Remaining students: " + sorcerers.size());
        if (!sorcerers.isEmpty()) {
            System.out.println("The fight continues!");
        }

        sorcerers.clear();
        System.out.println("Post-Culling Game Status: " + sorcerers);
    }
}