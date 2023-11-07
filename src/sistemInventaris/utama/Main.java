package sistemInventaris.utama;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[][] users = new String[100][2];
        String[] itemName = new String[100];
        // int[][] items2 = new int[100][2];

        int userCount = 0;
        int itemcount = 0;
        // int itemcount2 = 0;
        boolean sudahMasuk = false;
        // System.out.println(String.format("%d", items[1][2]));
        while (true) {
            System.out.println("|-----------------------|");
            System.out.println("|       LOGIN FORM      |");
            System.out.println("|-----------------------|");
            System.out.println("| 1. Daftar             |");
            System.out.println("| 2. Masuk              |");
            System.out.println("| 3. Input Data         |");
            System.out.println("| 4. Keluar             |");
            System.out.println("|-----------------------|");
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
                case 2:
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
                case 3:
                    if (sudahMasuk) {
                        System.out.print("Masukkan Jumlah Barang yang akan diinput : ");
                        int jml = input.nextInt();
                        for (int i = 0; i < jml; i++) {
                            System.out.println("Barang ke-" + (i + 1));

                            System.out.print("Masukkan Nama Barang : ");
                            input.next();
                            String namaBarang = input.nextLine();

                            System.out.print("Masukkan Jumlah Barang : ");
                            int jmlBarang = Integer.parseInt(input.nextLine());

                            System.out.print("Masukkan Harga Barang : ");
                            int hargaBarang = Integer.parseInt(input.nextLine());

                            System.out.print("Masukkan Tanggal : ");
                            String date = input.nextLine();

                            itemName[itemcount] = namaBarang;

                            itemcount++;
                        }
                        System.out.println(jml + " barang Berhasil di tambahkan");
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                case 4:
                    for (int i = 0; i < users.length; i++) {
                        for (int j = 0; j < users[i].length; j++) {
                            System.out.println(users[i][j]);
                        }                        
                    }
                case 5:
                    if (sudahMasuk) {
                        System.exit(0);
                    } else if (!sudahMasuk) {
                        System.out.println("Login terlebih dahulu");
                    }
                    break;
                default:
                    // System.out.println("pilih menu yang benar");
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