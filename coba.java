import java.util.Scanner;

public class coba {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        String[] usernames = new String[100];
        String[] passwords = new String[100];

        int totalUsers = 0;

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Silakan pilih tindakan:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan username: ");
                    String newUsername = scanner.nextLine();
                    
                
                    boolean isUsernameExists = false;
                    for (int i = 0; i < totalUsers; i++) {
                        if (usernames[i].equals(newUsername)) {
                            isUsernameExists = true;
                            break;
                        }
                    }
                    
                    if (isUsernameExists) {
                        System.out.println("Username sudah digunakan. Silakan pilih username lain.");
                    } else {
                        System.out.print("Masukkan password: ");
                        String newPassword = scanner.nextLine();
                        
                    
                        usernames[totalUsers] = newUsername;
                        passwords[totalUsers] = newPassword;
                        totalUsers++;
                        System.out.println("Registrasi berhasil.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();

                    boolean isLoggedIn = false;

                
                    for (int i = 0; i < totalUsers; i++) {
                        if (usernames[i].equals(username) && passwords[i].equals(password)) {
                            isLoggedIn = true;
                            break;
                        }
                    }

                    if (isLoggedIn) {
                        System.out.println("Login berhasil. Selamat datang, " + username + "!");
                    } else {
                        System.out.println("Login gagal. Periksa username dan password Anda.");
                    }
                    break;

                case 3:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }

        scanner.close();
    }
    }
