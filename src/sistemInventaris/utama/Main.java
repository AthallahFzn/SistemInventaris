package sistemInventaris.utama;

// import java.util.Arrays;
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
        // String[] itemName = new String[100];
        String[][] items = new String[20][3];
        // int[][] items2 = new int[100][2];

        int userCount = 0;
        int itemcount = 0;
        // int itemplus = 0;
        // int itemcount2 = 0;
        boolean sudahMasuk = false;
        // System.out.println(String.format("%d", items[1][2]));
        while (true) {
            System.out.println("|-------------------------|");
            System.out.println("|       LOGIN FORM        |");
            System.out.println("|-------------------------|");
            System.out.println("| 1. Daftar               |");
            System.out.println("| 2. Masuk                |");
            System.out.println("| 3. Input Data           |");
            System.out.println("| 4. Display Barang Masuk |");
            System.out.println("| 5. Laporan Barang       |");
            System.out.println("| 6. Hapus Barang         |");
            System.out.println("| 7. Keluar               |");
            System.out.println("|-------------------------|");
            System.out.print("Pilih 1-4 : ");
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
                            sudahMasuk = true;
                            System.out.println("Berhasil Login");
                        }
                    }
                    if (!sudahMasuk) {
                        System.out.println("Gagal Login");
                    }
                    break;
                case 3: // Input Barang
                    if (sudahMasuk) {
                        System.out.print("Masukkan Jumlah Barang yang akan diinput : ");
                        int jml = input.nextInt();
                        for (int i = 0; i < jml; i++) {
                            System.out.println("Barang ke-" + (i + 1));

                            System.out.print("Masukkan Nama Barang : ");
                            String namaBarang = input.next();

                            System.out.print("Masukkan Jumlah Barang : ");
                            String jmlBarang = input.next();
                            System.out.print("Masukkan Harga Barang : ");
                            String hargaBarang = input.next();

                            items[itemcount][0] = namaBarang;
                            items[itemcount][1] = jmlBarang;
                            items[itemcount][2] = hargaBarang;
                            itemcount++;
                        }
                        System.out.println(jml + " barang Berhasil di tambahkan");
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 4: // Display Barang masuk
                    if (sudahMasuk) {

                        System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
                        System.out.println("|-------------------------------------|");
                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null && items[i][1] != null && items[i][2] != null) {
                                System.out.println(
                                        "| " + items[i][0] + " | " + items[i][1] + " | " + items[i][2] + " | "
                                                + formattedDate + " |");
                            } else {
                                System.out.print("");
                            }
                        }
                        System.out.println("|-------------------------------------|");
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 5: // Laporan
                    if (sudahMasuk) {

                        System.out.println("|-------------------------------------|");
                        System.out.println("|           Laporan Keuangan          |");
                        System.out.println("|-------------------------------------|");
                        System.out.println("| Nama Barang | Qty | Harga | Tanggal |");
                        System.out.println("|-------------------------------------|");
                        int totalHarga = 0;

                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null && items[i][1] != null && items[i][2] != null) {
                                System.out.println(
                                        "| " + items[i][0] + " | " + items[i][1] + " | Rp." + items[i][2] + " | "
                                                + formattedDate + " |");

                                int quantity = Integer.parseInt(items[i][1]);
                                int price = Integer.parseInt(items[i][2]);
                                int totalItem = quantity * price;
                                totalHarga += totalItem;
                            } else {
                                System.err.print("");

                            }
                        }

                        System.out.println("|-------------------------------------|");
                        System.out.println("|Total: Rp." + totalHarga + ",00              |");
                        System.out.println("|-------------------------------------|");
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 6: // Hapus Barang
                    if (sudahMasuk) {
                        System.out.print("Masukkan Nama Barang yang akan dihapus: ");
                        String barangHapus = input.next();
                        boolean barangDitemukan = false;

                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null && items[i][0].equalsIgnoreCase(barangHapus)) {
                                // Barang ditemukan, hapus barang
                                items[i][0] = null;
                                items[i][1] = null;
                                items[i][2] = null;
                                barangDitemukan = true;
                                System.out.println("Barang berhasil dihapus.");
                                break; // Keluar dari loop setelah barang dihapus
                            }
                        }

                        if (!barangDitemukan) {
                            System.out.println("Barang tidak ditemukan.");
                        }
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 7: // Exit Program
                    if (sudahMasuk) {
                        System.exit(0);
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
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