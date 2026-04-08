package Codelab.Modul3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueCodelab {
    public static void main(String[] args) {

        // ************************************************************
        // BAGIAN 1: QUEUE DEMONSTRATION (FIFO)
        // ************************************************************
        // Queue digunakan untuk antrian pelayanan di stasiun
        // Penumpang yang datang pertama akan dilayani pertama
        // ************************************************************

        // TODO 1: Inisialisasi Queue untuk antrian pelayanan
        // Clue: Queue adalah interface, implementasinya menggunakan LinkedList
        Queue<String> serviceQueue = new LinkedList<>();

        // TODO 2: Menambahkan penumpang ke antrian menggunakan add()
        // Tambahkan 3 penumpang: Andi, Titan, Rapi
        serviceQueue.add("Andi");
        serviceQueue.add("Titan");
        serviceQueue.add("Rapi");

        // Menampilkan antrian awal
        System.out.println("Initial Service Queue:");
        System.out.println(serviceQueue);

        // TODO 3: Melayani penumpang (mengambil dari depan antrian) menggunakan poll()
        String servedPassenger = serviceQueue.poll();
        System.out.println("Serving passenger: " + servedPassenger);

        // Menampilkan antrian setelah pelayanan
        System.out.println("Queue after serving:");
        System.out.println(serviceQueue);

        // TODO 4: Melihat penumpang di depan antrian tanpa menghapus menggunakan peek()
        String nextPassenger = serviceQueue.peek();
        System.out.println("Next passenger to serve: " + nextPassenger);

        System.out.println();

        // ************************************************************
        // BAGIAN 2: STACK DEMONSTRATION (LIFO)
        // ************************************************************

        // TODO 5: Inisialisasi Stack untuk riwayat transaksi
        Stack<String> transactionHistory = new Stack<>();

        // TODO 6: Menambahkan transaksi ke stack menggunakan push()
        // Tambahkan 3 transaksi: Transaction-1, Transaction-2, Transaction-3
        transactionHistory.push("Transaction-1");
        transactionHistory.push("Transaction-2");
        transactionHistory.push("Transaction-3");

        // Menampilkan riwayat transaksi
        System.out.println("Transaction History:");
        System.out.println(transactionHistory);

        // TODO 7: Membatalkan transaksi terakhir (undo) menggunakan pop()
        String lastTransaction = transactionHistory.pop();
        System.out.println("Undo last transaction: " + lastTransaction);

        // Menampilkan riwayat setelah undo
        System.out.println("Transaction History after undo:");
        System.out.println(transactionHistory);

        // TODO 8: Melihat transaksi terakhir tanpa menghapus menggunakan peek()
        String topTransaction = transactionHistory.peek();
        System.out.println("Current top transaction: " + topTransaction);

        // TODO 9: Mengecek apakah stack kosong menggunakan isEmpty()
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history.");
        } else {
            System.out.println("Transaction history is not empty. Size: " + transactionHistory.size());
        }
    }
}
