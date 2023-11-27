package sistemInventaris.utama;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class tes {

    static Scanner input = new Scanner(System.in).useDelimiter("\r\n|\n");
    static LocalDate date = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static String formattedDate = date.format(formatter);

    static String[][] users = new String[20][2];
    static String[][] deletedItems = new String[20][3];
    static String[][] items = new String[20][3];

    static int deletedItemCount = 0;
    static int userCount = 0;
    static int itemCount = 0;
    static boolean loggedIn = false;

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = Integer.parseInt(input.next());
            handleMenuChoice(choice);
        }
    }

    static void displayMenu() {
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
    }

    static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                inputData();
                break;
            case 4:
                displayBarangMasuk();
                break;
            case 5:
                laporanBarang();
                break;
            case 6:
                hapusBarang();
                break;
            case 7:
                displayBarangKeluar();
                break;
            case 8:
                logout();
                break;
            case 9:
                exitProgram();
                break;
            default:
                System.out.println("Pilih menu yang benar");
                break;
        }
    }

    static void registerUser() {
        System.out.print("Masukkan Username: ");
        String username = input.next();
        System.out.print("Masukkan Password: ");
        String password = input.next();
        users[userCount][0] = username;
        users[userCount][1] = password;
        userCount++;
        System.out.println("Berhasil Daftar");
    }

    static void loginUser() {
        System.out.print("Masukkan Username: ");
        String username = input.next();
        System.out.print("Masukkan Password: ");
        String password = input.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i][0].equals(username) && users[i][1].equals(password)) {
                loggedIn = true;
                System.out.println("Berhasil Login");
            }
        }
        if (!loggedIn) {
            System.out.println("Gagal Login");
        }
    }

    static void inputData() {
        if (loggedIn) {
            System.out.print("Masukkan Jumlah Barang yang akan diinput: ");
            int total = Integer.parseInt(input.next());
            for (int i = 0; i < total; i++) {
                System.out.println("Barang ke-" + (i + 1));

                System.out.print("Masukkan Nama Barang: ");
                String itemsName = input.next();

                System.out.print("Masukkan Jumlah Barang: ");
                String itemQty = input.next();
                System.out.print("Masukkan Harga Barang: ");
                String itemsPrice = input.next();

                items[itemCount][0] = itemsName;
                items[itemCount][1] = itemQty;
                items[itemCount][2] = itemsPrice;
                itemCount++;
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

    static void laporanBarang() {
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

    static void hapusBarang() {
        // Implement hapus barang logic here
    }

    static void displayBarangKeluar() {
        // Implement display barang keluar logic here
    }

    static void logout() {
        // Implement logout logic here
    }

    static void exitProgram() {
        System.out.println("Terimakasih telah menggunakan program kami~");
        System.exit(0);
    }
}
