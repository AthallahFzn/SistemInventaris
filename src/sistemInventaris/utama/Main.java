package sistemInventaris.utama;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String[][] users = new String[20][2];
        String[][] deletedItems = new String[20][3];
        String[][] items = new String[20][3];

        int deletedItemCount = 0;
        int userCount = 0;
        int itemcount = 0;
        boolean loggedIn = false;
        while (true) {
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
            System.out.println("| 8. Hapus Barang          |");
            System.out.println("| 9. Keluar                |");
            System.out.println("|--------------------------|");
            System.out.print("Pilih 1-9 : ");
            int pilih = input.nextInt();
            input.nextLine();
            switch (pilih) {
                case 1: // Daftar
                    System.out.print("Masukkan Username : ");
                    String username = input.nextLine();
                    System.out.print("Masukkan Password : ");
                    String password = input.nextLine();
                    users[userCount][0] = username;
                    users[userCount][1] = password;
                    userCount++;
                    System.out.println("Berhasil Daftar");
                    break;
                case 2: // Masuk
                    System.out.print("Masukkan Username : ");
                    username = input.nextLine();
                    System.out.print("Masukkan Password : ");
                    password = input.nextLine();

                    for (int i = 0; i < userCount; i++) {
                        if (users[i][0].equals(username) && users[i][1].equals(password)) {
                            loggedIn = true;
                            System.out.println("Berhasil Login");
                        }
                    }
                    if (!loggedIn) {
                        System.out.println("Gagal Login");
                    }
                    break;
                case 3: // Input Barang
                    if (loggedIn) {
                        System.out.print("Masukkan Jumlah Barang yang akan diinput : ");
                        int jml = input.nextInt();
                        for (int i = 0; i < jml; i++) {
                            System.out.println("Barang ke-" + (i + 1));

                            System.out.print("Masukkan Nama Barang : ");
                            String itemsName = input.next();

                            System.out.print("Masukkan Jumlah Barang : ");
                            String itemQty = input.next();
                            System.out.print("Masukkan Harga Barang : ");
                            String itemsPrice = input.next();

                            items[itemcount][0] = itemsName;
                            items[itemcount][1] = itemQty;
                            items[itemcount][2] = itemsPrice;
                            itemcount++;
                        }
                        System.out.println(jml + " barang Berhasil di tambahkan");
                    } else if (!loggedIn) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 4: // Display Barang masuk
                    if (loggedIn) {

                        System.out.println("| Nama Barang | Qty | Harga | Tanggal | Admin |");
                        System.out.println("|---------------------------------------------|");
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
                    break;
                case 5: // Laporan
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
                    break;
                case 6: // Hapus Barang
                    if (loggedIn) {
                        System.out.print("Masukkan Nama Barang yang akan diambil: ");
                        String itemTaken = input.next();
                        System.out.print("Masukkan Jumlah Barang yang akan diambil: ");
                        int takenQty = input.nextInt();
                        boolean itemsFound = false;

                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null && items[i][0].equalsIgnoreCase(itemTaken)) {
                                int itemQty = Integer.parseInt(items[i][1]);

                                if (itemQty >= takenQty) {
                                    items[i][1] = String.valueOf(itemQty - takenQty);

                                    deletedItems[deletedItemCount][0] = items[i][0];
                                    deletedItems[deletedItemCount][1] = String.valueOf(takenQty);
                                    deletedItems[deletedItemCount][2] = items[i][2];
                                    deletedItemCount++;

                                    itemsFound = true;
                                    System.out.println("Barang berhasil diambil.");
                                } else {
                                    System.out.println("Jumlah barang yang tersedia tidak mencukupi.");
                                }
                                break;
                            }
                        }
                        if (!itemsFound) {
                            System.out.println("Barang tidak ditemukan.");
                        }
                    } else if (!loggedIn) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 7: // Display Barang Keluar
                    if (loggedIn) {
                        System.out.println("|-------------------------------------|");
                        System.out.println("|       Barang yang Telah Keluar      |");
                        System.out.println("|-------------------------------------|");
                        System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
                        System.out.println("|-------------------------------------|");
                        for (int i = 0; i < deletedItems.length; i++) {
                            if (deletedItems[i][0] != null) {
                                System.out.println(
                                        "| " + deletedItems[i][0] + " | " + deletedItems[i][1] + " | "
                                                + deletedItems[i][2] + " |" + formattedDate + " |");
                            }
                        }
                        System.out.println("|-------------------------------------|");
                    } else if (!loggedIn) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 8: // Logout
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout Berhasil");
                    } else {
                        System.out.println("Anda belum login!");
                    }
                    break;
                // case 9: // Exit Program
                //     if (loggedIn) {
                //         System.exit(0);
                //     } else if (!loggedIn) {
                //         System.out.println("Login terlebih dahulu");
                //     }
                //     break;
                default:
                    System.out.println("pilih menu yang benar");
                    break;
            }
        }

    }
}
// for (String[] i : users) {
// for (String j : i) {
// System.out.println(j);
// }
// }