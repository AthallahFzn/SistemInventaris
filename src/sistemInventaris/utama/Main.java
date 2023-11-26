import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static String username; 
    public static String password; 
    
    public static LocalDate date = LocalDate.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String formattedDate = date.format(formatter);
    
    public static String[][] deletedItems = new String[20][4];
    public static String[][] items = new String[20][4];
   
        
    public static int deletedItemCount = 0;
    public static int itemcount = 0;
    public static boolean loggedIn = false;
    
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in).useDelimiter("\r\n|\n");
    
    System.out.println("Welcome to the Restaurant Inventory !");
    System.out.println();

    System.out.println("Daftar Akun");
    daftar();
    System.out.println("Masuk Akun");
    masuk();

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
            System.out.println("| 8. Logout                |");
            System.out.println("| 9. Keluar                |");
            System.out.println("|--------------------------|");
            
            System.out.print("Pilih 1-9: ");
            int choose = input.nextInt();
        
            switch (choose) {
                case 1 : //daftar akun
                daftar();
                break;

                case 2://login akun
                masuk();
                break;

                case 3://input barang
                inputBarang();
                break;

                case 4://display barang masuk
                displayBarangMasuk();
                break;

                case 5://laporan barang yang tersedia
                laporanBarang();
                break;

                case 6://hapus barang
                hapusBarang();
                break;
                
                case 7://display barang yang di hapus & keluar
                displayBarangKeluar();
                break;

                case 8://logout dari akun
                logout();
                break;

                case 9://keluar dari program
                keluar();
                break;

                default:
                System.out.println("Pilihan anda tidak ada di menu, silahkan pilih lagi!");
                break;
            } 
        }
}
    public static void daftar(){ //daftar akun
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n|\n");

        System.out.print("Masukkan username: ");
        username = sc.nextLine();
        
        System.out.print("Masukkan password: ");
        password = sc.nextLine();

        System.out.println("Berhasil Daftar");
        System.out.println();
    }

    public static void masuk(){ //masuk akun
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n|\n");

        if (username == null || password == null) {
            System.out.println("Belum terdaftar. Silahkan daftar terlebih dahulu");
            return; 
        } 
        System.out.print("Masukkan username: ");
        String inputUsername = sc.nextLine();

        System.out.print("Masukkan password: ");
        String inputPassword = sc.nextLine();

        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            System.out.println("Berhasil Masuk");
            loggedIn = true;
        } else {
            System.out.println("Username atau password salah");
        }
        System.out.println();
        }

    public static void inputBarang() {
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n|\n");
        if (loggedIn) {
        System.out.print("Masukkan Jumlah Barang yang akan diinput: ");
            int total = Integer.parseInt(sc.next());
            
            for (int i = 0; i < total; i++) {
                System.out.println("Barang ke-" + (i + 1));

                System.out.print("Masukkan Nama Barang: ");
                String itemsName = sc.next();

                System.out.print("Masukkan Jumlah Barang: ");
                String itemQty = sc.next();
                System.out.print("Masukkan Harga Barang: ");
                String itemsPrice = sc.next();

                items[itemcount][0] = itemsName;
                items[itemcount][1] = itemQty;
                items[itemcount][2] = itemsPrice;
                itemcount++;
            }
            System.out.println(total + " barang Berhasil di tambahkan");
        } else if (!loggedIn){
            System.out.println("Login terlebih dahulu!");
        }
        System.out.println();
    } 

    public static void displayBarangMasuk() {
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
        System.out.println();
    }

    public static void laporanBarang() {
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
        }
        else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
        System.out.println();
    }

    public static void hapusBarang(){
        Scanner input = new Scanner(System.in).useDelimiter("\r\n|\n");
            if (loggedIn) {
                System.out.println("_____Alasan Barang Dihapus_____");
                System.out.println("1. Kadaluarsa                  ");
                System.out.println("2. Digunakan untuk penjualan   ");
                System.out.println("3. Basi                        ");
                System.out.println("4. Berjamur                    ");

                System.out.print("Masukkan Nama Barang yang akan diambil: ");
                String itemTaken = input.next();
                System.out.print("Masukkan Jumlah Barang yang akan diambil: ");
                int takenQty = input.nextInt();
                System.out.print("Alasan barang dihapus: ");
                int reason = input.nextInt();
                switch (reason) {
                    case 1:
                        System.out.println("Barang sudah kadaluarsa");
                        break;
                    
                    case 2: 
                        System.out.println("Barang digunakan untuk penjualan");
                        break;

                    case 3:
                        System.out.println("Barang basi");
                        break;

                    case 4:
                    System.out.println("Barang berjamur");
                    break;

                    default:
                    System.out.println("Pilihan menu tersebut tidak ada");
                    break;
                } System.out.println();

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
    
    public static void displayBarangKeluar(){
        if (loggedIn) {
            System.out.println("|---------------------------------------------|");
            System.out.println("|           Barang yang Telah Keluar          |");
            System.out.println("|---------------------------------------------|");
            System.out.println("| Nama Barang | Qty | Harga | Alasan | Tanggal|");
            System.out.println("|---------------------------------------------|");
            for (int i = 0; i < deletedItems.length; i++) {
                if (deletedItems[i][0] != null) {
                    System.out.println(
                            "| " + deletedItems[i][0] + " | " + deletedItems[i][1] + " | "
                                    + deletedItems[i][2] + " | " + deletedItems[i][3] + " | " + formattedDate + " |");
                }
            }
            System.out.println("|-------------------------------------|");
        } else if (!loggedIn) {
            System.out.println("Login terlebih dahulu");
        }
    }

    public static void logout(){
        if (loggedIn) {
            loggedIn = false;
            System.out.println("Logout Berhasil");
        } else {
            System.out.println("Anda belum login!");
        }
    }

    public static void keluar(){
        System.out.println("Terimakasih telah menggunakan program kami~");
        System.exit(0);
    }
}
