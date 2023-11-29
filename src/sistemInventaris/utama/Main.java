package sistemInventaris.utama;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main
 */
public class Main {

    static Scanner input = new Scanner(System.in).useDelimiter("\r\n|\n");
    static LocalDate date = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static String formattedDate = date.format(formatter);
    static String[][] users = new String[20][2];
    static String[][] deletedItems = new String[20][4];
    static String[][] items = new String[20][3];
    static int deletedItemCount = 0;
    static int userCount = 0;
    static int itemcount = 0;
    static boolean loggedIn = false;
    static String inputUsername;
    static String inputPassword;
    static int choose;

    public static void main(String[] args) {

        while (true) {
            menu();
            switch (choose) {
                case 1: // Daftar
                    signUp();
                    break;
                case 2: // Masuk
                    login();
                    break;
                case 3: // Input Barang
                    inputBarang();
                    break;
                case 4: // Display Barang masuk
                    displayBarangMasuk();
                    break;
                case 5: // Laporan
                    report();
                    break;
                case 6: // Hapus Barang
                    deleteItems();
                    break;
                case 7: // Display Barang Keluar
                    displayBarangKeluar();
                    break;
                case 8: // Logout
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout Berhasil");
                    } else {
                        System.out.println("Anda belum login!");
                    }
                    break;
                case 9: // Exit Program
                    System.out.println("Terimakasih telah menggunakan program kami~");
                    System.exit(0);
                    break;
                default:
                    System.out.println("pilih menu yang benar");
                    break;
            }
        }

    }

    static void signUp() {
        System.out.print("Masukkan Username: ");
        inputUsername = input.next();
        System.out.print("Masukkan Password: ");
        inputPassword = input.next();
        users[userCount][0] = inputUsername;
        users[userCount][1] = inputPassword;
        userCount++;
        System.out.println("Berhasil Daftar");
    }

    static void login() {
        System.out.print("Masukkan Username: ");
        inputUsername = input.next();
        System.out.print("Masukkan Password: ");
        inputPassword = input.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i][0].equals(inputUsername) && users[i][1].equals(inputPassword)) {
                loggedIn = true;
                System.out.println("Berhasil Login");
            }
        }
        if (!loggedIn) {
            System.out.println("Gagal Login");
        }
    }

    static void inputBarang() {
        if (loggedIn) {
            System.out.print("Masukkan Jumlah Barang yang akan diinput: ");
            int total = Integer.parseInt(input.next());
            for (int i = 0; i < total; i++) {
                System.out.println("Barang ke-" + (i + 1));

                System.out.print("Masukkan Nama Barang: ");
                String itemsName = input.next();
                int existingIndex = findItemIndex(items, itemcount, itemsName);
                if (existingIndex != -1) {
                    System.out.print("Masukkan Jumlah Barang: ");
                    int newItemQty = Integer.parseInt(input.next());
                    int currentQty = Integer.parseInt(items[existingIndex][1]);
                    items[existingIndex][1] = String.valueOf(currentQty + newItemQty);
                } else {
                    System.out.print("Masukkan Jumlah Barang: ");
                    String itemQty = input.next();
                    System.out.print("Masukkan Harga Barang: ");
                    String itemsPrice = input.next();

                    items[itemcount][0] = itemsName;
                    items[itemcount][1] = itemQty;
                    items[itemcount][2] = itemsPrice;
                    itemcount++;
                }
            }
            System.out.println(total + " barang Berhasil di tambahkan");
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void displayBarangMasuk() {
        if (loggedIn) {
            System.out.println("|-------------------------------------|");
            System.out.println("|             Barang Masuk            |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
            System.out.println("|-------------------------------------|");
            for (int i = 0; i < items.length; i++) {

                if (items[i][0] != null && items[i][1] != null && items[i][2] != null) {
                    System.out.println(
                            "| " + items[i][0] + " | " + items[i][1] + " | Rp." + items[i][2] + " | "
                                    + formattedDate + " |");
                } else {
                    System.out.print("");
                }
            }
            System.out.println("|-------------------------------------|");
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void report() {
        if (loggedIn) {

            System.out.println("|-------------------------------------|");
            System.out.println("|           Laporan Keuangan          |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
            System.out.println("|-------------------------------------|");
            int priceTotal = 0;

            for (int i = 0; i < items.length; i++) {
                if (items[i][0] != null && items[i][1] != null && items[i][2] != null) {
                    System.out.println(
                            "| " + items[i][0] + " | " + items[i][1] + " | Rp." + items[i][2] + " | "
                                    + formattedDate + " |");

                    int quantity = Integer.parseInt(items[i][1]);
                    int price = Integer.parseInt(items[i][2]);
                    int itemsTotal = quantity * price;
                    priceTotal += itemsTotal;
                } else {
                    System.err.print("");
                }
            }

            System.out.println("|-------------------------------------|");
            System.out.println("|Total: Rp." + priceTotal + ",00              |");
            System.out.println("|-------------------------------------|");
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void deleteItems() {
        if (loggedIn) {

            System.out.print("Masukkan Nama Barang yang akan diambil: ");
            String itemTaken = input.next();
            System.out.print("Masukkan Jumlah Barang yang akan diambil: ");
            int takenQty = input.nextInt();
            System.out.print("Alasan barang dihapus(Catatan): ");
            String reason = input.next();

            boolean itemsFound = false;
            for (int i = 0; i < items.length; i++) {
                if (items[i][0] != null && items[i][0].equalsIgnoreCase(itemTaken)) {
                    int itemQty = Integer.parseInt(items[i][1]);

                    if (itemQty >= takenQty) {
                        items[i][1] = String.valueOf(itemQty - takenQty);

                        deletedItems[deletedItemCount][0] = items[i][0];
                        deletedItems[deletedItemCount][1] = String.valueOf(takenQty);
                        deletedItems[deletedItemCount][2] = items[i][2];
                        deletedItems[deletedItemCount][3] = String.valueOf(reason);
                        deletedItemCount++;

                        itemsFound = true;

                        System.out.println("Barang berhasil diambil.");
                    } else {
                        System.out.println("Jumlah barang yang tersedia tidak mencukupi.");
                    }
                }
            }
            if (!itemsFound) {
                System.out.println("Barang tidak ditemukan.");
            }
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void displayBarangKeluar() {
        if (loggedIn) {
            System.out.println("|----------------------------------------------|");
            System.out.println("|           Barang yang Telah Keluar           |");
            System.out.println("|----------------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Tanggal | Alasan |");
            System.out.println("|----------------------------------------------|");
            for (int i = 0; i < deletedItems.length; i++) {
                if (deletedItems[i][0] != null) {
                    System.out.println(
                            "| " + deletedItems[i][0] + " | " + deletedItems[i][1] + " | Rp. "
                                    + deletedItems[i][2] + " |" + formattedDate + " |"
                                    + deletedItems[i][3] + " |");
                }
            }
            System.out.println("|-------------------------------------|");
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void menu() {
        System.out.println("|--------------------------|");
        System.out.println("|       LOGIN FORM         |");
        System.out.println("|--------------------------|");
        System.out.println("| 1. Daftar                |");
        System.out.println("| 2. Masuk                 |");
        System.out.println("| 3. Input Data            |");
        System.out.println("| 4. Display Barang Masuk  |");
        System.out.println("| 5. Laporan Barang        |");
        System.out.println("| 6. Hapus Barang          |");
        System.out.println("| 7. Display Barang Keluar |");
        System.out.println("| 8. Logout                |");
        System.out.println("| 9. Keluar                |");
        System.out.println("|--------------------------|");
        System.out.print("Pilih 1-9: ");
        choose = Integer.parseInt(input.next());
    }

    private static int findItemIndex(String[][] items, int itemcount, String itemName) {
        for (int i = 0; i < itemcount; i++) {
            if (itemName.equals(items[i][0])) {
                return i;
            }
        }
        return -1;
    }
}
// for (String[] i: users) {
// for (String j: i) {
// System.out.println(j);
// }
// }