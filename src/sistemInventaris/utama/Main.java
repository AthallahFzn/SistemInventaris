package sistemInventaris.utama;

import java.util.Arrays;
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
    static String[][] deletedItems = new String[20][5];
    static String[][] items = new String[20][4];
    static int deletedItemCount = 0;
    static int userCount = 0;
    static int itemcount = 0;
    static boolean loggedIn = false;
    static String inputUsername;
    static String inputPassword;
    static int choose;
    static String reason;

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
                case 6: // Hapus Barang Dijual
                    deleteItems();
                    break;
                case 7: // Display Barang Dijual
                    displayDeletedItems();
                    break;
                case 8:
                    displayFilteredInput();
                    break;
                case 9:
                    searchItems();
                    break;
                case 10:// Logout
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout Berhasil");
                    } else {
                        System.out.println("Anda belum login!");
                    }
                    break;
                case 11: // Exit Program
                    System.out.println("Terimakasih telah menggunakan program kami~");
                    System.exit(0);
                    break;
                default:
                    System.out.println("pilih menu yang benar");
                    break;
            }
        }
    }

    static void signUp() { // nomor 1
        System.out.print("Masukkan Username: ");
        inputUsername = input.next();
        System.out.print("Masukkan Password: ");
        inputPassword = input.next();
        users[userCount][0] = inputUsername;
        users[userCount][1] = inputPassword;
        userCount++;
        System.out.println("Berhasil Daftar");
    }

    static void login() { // nomor 2
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

    static void inputBarang() { // nomor 3
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

    static void displayBarangMasuk() { // nomor 4
         if (loggedIn) {
            
            itemcount = 0;
            for (String[] item : items) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null) {
                    itemcount++;
                }
            }
        
            String[][] tableBuilder = new String[itemcount + 1][4];
            tableBuilder[0] = new String[]{"Nama", "Qty", "Price", "Date"};
            
            int i = 1;
            for (String[] item : items) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null) {
                    tableBuilder[i] = new String[]{item[0], item[1], item[2], formattedDate};
                    i++;
                }
            }
        
            printTableFromArrays(tableBuilder);
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void report() { // nomor 5
        if (loggedIn) {

            System.out.println("|-------------------------------------|");
            System.out.println("| Laporan Keuangan |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
            System.out.println("|-------------------------------------|");
            long priceTotal = 0;
            for (int i = 0; i < items.length; i++) {
                if (items[i][0] != null && items[i][1] != null && items[i][2] != null) {
                    System.out.println(
                            "| " + items[i][0] + " | " + items[i][1] + " | Rp." + items[i][2] + " | "
                                    + formattedDate + " |");

                    long quantity = Integer.parseInt(items[i][1]);
                    long price = Integer.parseInt(items[i][2]);
                    long itemsTotal = quantity * price;
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

    static void deleteItems() { // nomor 6
        if (loggedIn) {
            System.out.print("Masukkan Jumlah Barang yang akan dihapus: ");
            int total = Integer.parseInt(input.next());
            for (int a = 0; a < total; a++) { // outer loop
                System.out.println("Barang ke-" + (a + 1));
                System.out.print("Masukkan Nama Barang yang akan diambil: ");
                String itemTaken = input.next();
                System.out.print("Masukkan Jumlah Barang yang akan diambil: ");
                int takenQty = input.nextInt();
                System.out.print("Masukkan Alasan Penghapusan (Terjual/Rusak): ");
                reason = input.next();

                boolean itemsFound = false;
                for (int i = 0; i < items.length; i++) { // inner loop
                    if (items[i][0] != null && items[i][0].equalsIgnoreCase(itemTaken)) {
                        int itemQty = Integer.parseInt(items[i][1]);

                        if (itemQty >= takenQty) {
                            items[i][1] = String.valueOf(itemQty - takenQty);

                            deletedItems[deletedItemCount][0] = items[i][0];
                            deletedItems[deletedItemCount][1] = String.valueOf(takenQty);
                            deletedItems[deletedItemCount][2] = items[i][2];
                            deletedItems[deletedItemCount][3] = formattedDate;
                            if (reason.equalsIgnoreCase("rusak")) {
                                deletedItems[deletedItemCount][4] = "Rusak";
                            } else if (reason.equalsIgnoreCase("terjual")) {
                                deletedItems[deletedItemCount][4] = "Terjual";
                            } else {
                                deletedItems[deletedItemCount][4] = "Lainnya";
                            }
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
            }
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void displayDeletedItems() { // nomor 7
        if (loggedIn) {
            
            itemcount = 0;
            for (String[] item : deletedItems) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null && item[4] != null) {
                    itemcount++;
                }
            }
        
            String[][] tableBuilder = new String[itemcount + 1][5];
            tableBuilder[0] = new String[]{"Nama", "Qty", "Price", "Date", "Reason"};
            
            int i = 1;
            for (String[] deletedItem : deletedItems) {
                if (deletedItem != null && deletedItem[0] != null && deletedItem[1] != null && deletedItem[2] != null && deletedItem[4] != null) {
                    tableBuilder[i] = new String[]{deletedItem[0], deletedItem[1], "Rp." + deletedItem[2], deletedItem[3], deletedItem[4]};
                    i++;
                }
            }
        
            printTableFromArrays(tableBuilder);
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    static void menu() {
        System.out.println("|--------------------------|");
        System.out.println("|        LOGIN FORM        |");
        System.out.println("|--------------------------|");
        System.out.println("| 1. Sign Up               |");
        System.out.println("| 2. Log in                |");
        System.out.println("| 3. Input Data            |");
        System.out.println("| 4. Display Items         |");
        System.out.println("| 5. Report                |");
        System.out.println("| 6. Hapus Barang          |");
        System.out.println("| 7. Display Outgoing items|");
        System.out.println("| 8. Filtered Items        |");
        System.out.println("| 9. Searching             |");
        System.out.println("| 10. Logout               |");
        System.out.println("| 11. Exit                 |");
        System.out.println("|--------------------------|");
        System.out.print("Pilih 1-11: ");
        choose = Integer.parseInt(input.next());
    }

    static int findItemIndex(String[][] items, int itemcount, String itemName) {
        for (int i = 0; i < itemcount; i++) {
            if (itemName.equals(items[i][0])) {
                return i;
            }
        }
        return -1;
    }

    static void displayFilteredItems(String filterType) {
        String title = "";
        if ("Rusak".equalsIgnoreCase(filterType)) {
            title = "Barang Rusak";
        } else if ("Terjual".equalsIgnoreCase(filterType)) {
            title = "Barang Terjual";
        } else {
            System.out.println("Filter type not recognized.");
            return;
        }

        System.out.println("|-------------------------------------|");
        System.out.println("|           " + title + "            |");
        System.out.println("|-------------------------------------|");
        System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
        System.out.println("|-------------------------------------|");
        for (int i = 0; i < deletedItems.length; i++) {
            if (deletedItems[i][0] != null && filterType.equalsIgnoreCase(deletedItems[i][4])) {
                System.out.println("| " + deletedItems[i][0] + " | " + deletedItems[i][1] + " | Rp."
                        + deletedItems[i][2] + " | " + deletedItems[i][3] + " |");
            }
        }
        System.out.println("|-------------------------------------|");
    }

    static void displayFilteredInput() {
        System.out.print("Filter Item (Rusak/Terjual):");
        String reason = input.next();
        displayFilteredItems(reason);
    }

    static void searchItems() {
        if (loggedIn) {
            System.out.print("Masukkan nama barang yang dicari: ");
            String searchTerm = input.next();

            System.out.println("|-------------------------------------|");
            System.out.println("|           Hasil Pencarian           |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
            System.out.println("|-------------------------------------|");

            boolean itemFound = false;
            for (String[] item : items) {
                if (item[0] != null && item[0].equals(searchTerm)) {
                    System.out.println(
                            "| " + item[0] + " | " + item[1] + " | Rp." + item[2] + " | " + formattedDate + " |");
                    itemFound = true;
                }
            }

            if (!itemFound) {
                System.out.println("Barang tidak ditemukan.");
            }
            System.out.println("|-------------------------------------|");
        } else {
            System.out.println("Login terlebih dahulu.");
        }
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        return sum;
    }

    public static void printTableFromArrays(String[][] tableBuilder) {
        int[] columnWidth = new int[tableBuilder[0].length];
        for (String[] builder : tableBuilder) {
            for (int i = 0; i < columnWidth.length; i++) {
                if (columnWidth[i] < builder[i].length()) {
                    columnWidth[i] = builder[i].length();
                }
            }
        }

        System.out.println("+" + "-".repeat(sum(columnWidth) + 2 + ((columnWidth.length - 1) * 3)) + "+");
        for (int i = 0; i < tableBuilder.length; i++) {
            for (int j = 0; j < tableBuilder[i].length; j++) {
                System.out.print("| ");
                System.out.print(tableBuilder[i][j]);
                System.out.print(" ".repeat(columnWidth[j] - tableBuilder[i][j].length() + 1));
            }
            System.out.println("|");
            if (i == 0) {
                System.out.println("+" + "-".repeat(sum(columnWidth) + 2 + ((columnWidth.length - 1) * 3)) + "+");
            }
        }
        System.out.println("+" + "-".repeat(sum(columnWidth) + 2 + ((columnWidth.length - 1) * 3)) + "+");
    }

}
