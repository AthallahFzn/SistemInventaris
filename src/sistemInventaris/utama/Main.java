package sistemInventaris.utama;

import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[][] users = new String[100][2];
        String[][] items = new String[100][3];

        int userCount = 0;
        int itemcount = 0;
        boolean sudahMasuk = false;
        // System.out.println(String.format("%d", items[1][2]));
        while (true) {
            System.out.println("|-----------------------|");
            System.out.println("|       LOGIN FORM      |");
            System.out.println("|-----------------------|");
            System.out.println("| 1. Daftar             |");
            System.out.println("| 2. Masuk              |");
            System.out.println("| 3. Keluar             |");
            System.out.println("|-----------------------|");
            System.out.print("Pilih 1-3 : ");
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