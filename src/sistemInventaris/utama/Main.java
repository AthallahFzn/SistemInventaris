package sistemInventaris.utama;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[][] users = new String[100][2];
        // String[]   item  = new String[100];

        int userCount = 0;
        // int itemCount = 0;

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
            System.out.print("\033[H\033[2J"); 
            System.out.flush();  
            switch (pilih) {
                case 1: //Daftar
                    System.out.print("Masukkan Username: ");
                    String username = input.nextLine();
                    System.out.print("Masukkan Password: ");
                    String password = input.nextLine();
                    users[userCount][0] = username;
                    users[userCount][1] = password;
                    userCount++;

                    System.out.println("|-----------------------|");
                    System.out.println("| Registrasi Berhasil ! |");

                    break;
                case 2: //Masuk
                    System.out.print("Masukkan Username: ");
                    username = input.nextLine();
                    System.out.print("Masukkan Password: ");
                    password = input.nextLine();
                    boolean login = false;
                    for (int i = 0; i < userCount; i++) {
                        if (users[i][0] != null && users[i][0].equals(username) && users[i][1] != null && users[i][1].equals(password)) {
                        System.out.println("|-----------------------|");
                        System.out.println("|     Login Berhasil    |"); 

                        login = true;
                        break;
                        }
                    }
                    if (!login) {
                        System.out.println("\n\n Login gagal! Periksa kembali USERNAME dan PASSWORD anda");
                    }
                    break;
                case 3: //Input Barang 

                    break;
                case 4: //Hapus Barang

                    break;
                case 5: //Cek Daftar Barang Masuk

                    break;
                case 6: //Cek Daftar Barang Keluar

                    break;
                
                case 7: //End Program
                    System.exit(0);
                    break;

                default:
                    System.out.println("\n\nPilihan tidak valid. Silakan pilih menu (1-7).\n\n");
                
                input.close();
            }
        }
        

    }
}