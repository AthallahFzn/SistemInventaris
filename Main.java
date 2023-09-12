import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[][] users = new String[100][2];
        String[]   item  = new String[100];

        int userCount = 0;
        int itemCount = 0;

        while (true) {
            System.out.println("|-----------------------|");
            System.out.println("|          Menu         |");
            System.out.println("|-----------------------|");
            System.out.println("| 1. Register           |");
            System.out.println("| 2. Login              |");
            System.out.println("| 3. Input Barang       |");
            System.out.println("| 4. Hapus Barang       |");
            System.out.println("| 5. Cek Barang Masuk   |");
            System.out.println("| 6. Cek Barang Keluar  |");
            System.out.println("| 7. Keluar             |");
            System.out.println("|-----------------------|");
            System.out.print("Pilih menu (1-7): ");
            
            int pilih = input.nextInt();
            input.nextLine();
            
            switch (pilih) {
                case 1:
                    System.out.print("Masukkan Username: ");
                    String username = input.nextLine();
                    System.out.print("Masukkan Password: ");
                    String password = input.SnextLine();
                    users[userCount][0] = username;
                    users[userCount][1] = password;
                    userCount++;
                    System.out.println("|-----------------------|");
                    System.out.println("| Registrasi Berhasil ! |");
                    break;
                // case 2:

                //     break;
                default:
                    System.out.println("\n\nPilihan tidak valid. Silakan pilih menu (1-7).\n\n");
            }
        }
        

    }
}