package sistemInventaris.utama;

import java.util.Scanner;

public class tes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Jumlah Barang yang akan diinput: ");
        int total = Integer.parseInt(input.next());

        String[][] items = new String[total][3]; // Assuming each item has name, quantity, and price
        int itemcount = 0;

        for (int i = 0; i < total; i++) {
            System.out.println("Barang ke-" + (i + 1));

            System.out.print("Masukkan Nama Barang: ");
            String itemName = input.next();

            // Check if the item already exists in the inventory
            int existingIndex = findItemIndex(items, itemcount, itemName);

            if (existingIndex != -1) {
                // Item already exists, update the quantity
                System.out.print("Masukkan Jumlah Barang: ");
                int newItemQty = Integer.parseInt(input.next());
                int currentQty = Integer.parseInt(items[existingIndex][1]);
                items[existingIndex][1] = String.valueOf(currentQty + newItemQty);
            } else {
                // Item doesn't exist, add it as a new item
                System.out.print("Masukkan Jumlah Barang: ");
                String itemQty = input.next();
                System.out.print("Masukkan Harga Barang: ");
                String itemPrice = input.next();

                items[itemcount][0] = itemName;
                items[itemcount][1] = itemQty;
                items[itemcount][2] = itemPrice;
                itemcount++;
            }
        }

        // Print the inventory
        System.out.println("\nDaftar Barang:");
        for (int i = 0; i < itemcount; i++) {
            System.out.println("Nama: " + items[i][0] + ", Jumlah: " + items[i][1] + ", Harga: " + items[i][2]);
        }

        input.close();
    }

    // Helper method to find the index of an item in the array
    private static int findItemIndex(String[][] items, int itemcount, String itemName) {
        for (int i = 0; i < itemcount; i++) {
            if (itemName.equals(items[i][0])) {
                return i; // Item found, return its index
            }
        }
        return -1; // Item not found
    }
}
