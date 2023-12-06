package sistemInventaris.utama;

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
    static String[][] users = new String[20][3];
    static String[][] deletedItems = new String[20][6];
    static String[][] items = new String[20][4];
    static int deletedItemCount = 0;
    static int userCount = 0;
    static int itemcount = 0;
    static boolean loggedIn = false;
    static String inputUsername;
    static String inputPassword;
    static int choose;
    static String reason;
    static String validation;
    static long priceTotal = 0;

    public static void main(String[] args) {
        while (true) {
            loginForm();
            switch (choose) {
                case 1:
                    String role = login();
                    if (!role.isEmpty()) {
                        switch (role.toLowerCase()) {
                            case "admin":
                                adminMenu();
                                break;
                            case "owner":
                                ownerMenu();
                                break;
                            case "user":
                                userMenu();
                                break;
                            default:
                                System.out.println("Invalid role.");
                                System.exit(0);
                                break;
                        }
                    }
                    break;
                case 2:
                    signUp();
                    break;
                case 3: // Exit Program
                    System.out.println("Thanks for your job.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid chooice.");
                    break;
            }
        }
    }

    static void signUp() {
        System.out.print("Enter Username: ");
        inputUsername = input.next();
        System.out.print("Enter Password: ");
        inputPassword = input.next();
        System.out.print("Enter Role (admin, owner, user): ");
        String inputRole = input.next();

        users[userCount][0] = inputUsername;
        users[userCount][1] = inputPassword;
        users[userCount][2] = inputRole;
        userCount++;

        System.out.println("Sign up successfully.");
    }

    static String login() {
        System.out.print("Enter username: ");
        inputUsername = input.next();
        System.out.print("Enter password: ");
        inputPassword = input.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i][0].equals(inputUsername) && users[i][1].equals(inputPassword)) {
                loggedIn = true;
                System.out.println("Login successfully.");
                return users[i][2];
            }
        }
        System.out.println("Login failed.");
        return "";
    }

    static void inputItems() {
        if (loggedIn) {
            // System.out.print("Enter items quantity you want input: ");
            // int total = Integer.parseInt(input.next());
            // for (int i = 0; i < total; i++) {
            // System.out.println("Item number-" + (i + 1));

            System.out.print("Enter item name: ");
            String itemsName = input.next();
            int existingIndex = findItemIndex(items, itemcount, itemsName);
            if (existingIndex != -1) {
                System.out.print("Enter item quantity: ");
                int newItemQty = Integer.parseInt(input.next());
                int currentQty = Integer.parseInt(items[existingIndex][1]);
                items[existingIndex][1] = String.valueOf(currentQty + newItemQty);
            } else {
                System.out.print("Enter quantity item: ");
                String itemQty = input.next();
                System.out.print("Enter item price: ");
                String itemsPrice = input.next();

                items[itemcount][0] = itemsName;
                items[itemcount][1] = itemQty;
                items[itemcount][2] = itemsPrice;
                itemcount++;
            }
            // }
            System.out.println("item successfully added");
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void displayItems() {
        if (loggedIn) {

            itemcount = 0;
            for (String[] item : items) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null) {
                    itemcount++;
                }
            }

            String[][] tableBuilder = new String[itemcount + 1][4];
            tableBuilder[0] = new String[] { "Item name", "Qty", "Price", "Date", "User" };

            int i = 1;
            for (int j = 0; j < items.length; j++) {
                if (items[j] != null && items[j][0] != null && items[j][1] != null && items[j][2] != null) {
                    String userName = (j < users.length && users[j] != null && users[j][0] != null) ? users[j][0] : "";
                    tableBuilder[i] = new String[] { items[j][0], items[j][1], "Rp." + items[j][2], formattedDate,
                            userName };
                    i++;
                }
            }

            printTableFromArrays(tableBuilder);
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void report() {
        if (loggedIn) {

            itemcount = 0;
            for (String[] item : items) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null) {
                    itemcount++;
                }
            }
            String[][] tableBuilder = new String[itemcount + 1][4];
            tableBuilder[0] = new String[] { "Item name", "Qty", "Price", "Date", "User" };

            int i = 1;
            for (int j = 0; j < items.length; j++) {
                if (items[j] != null && items[j][0] != null && items[j][1] != null && items[j][2] != null) {
                    String userName = (j < users.length && users[j] != null && users[j][0] != null) ? users[j][0] : "";
                    tableBuilder[i] = new String[] { items[j][0], items[j][1], "Rp." + items[j][2], formattedDate,
                            userName };
                    i++;
                    long quantity = Integer.parseInt(items[j][1]);
                    long price = Integer.parseInt(items[j][2]);
                    long itemsTotal = quantity * price;
                    priceTotal += itemsTotal;
                }
            }

            printTableFromArrays(tableBuilder);

            System.out.println("Total: Rp." + priceTotal + ",00");
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void deleteItems() {
        if (loggedIn) {
            System.out.print("Enter quantity item you want delete: ");
            int total = Integer.parseInt(input.next());
            for (int a = 0; a < total; a++) { // outer loop
                System.out.println("Barang ke-" + (a + 1));
                System.out.print("Enter item name: ");
                String itemTaken = input.next();
                System.out.print("Enter quantity item: ");
                int takenQty = input.nextInt();
                System.out.print("Enter item status (Terjual/Rusak): ");
                reason = input.next();
                System.out.print("Note: ");
                String note = input.next();

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
                            deletedItems[deletedItemCount][5] = note;
                            if (reason.equalsIgnoreCase("rusak")) {
                                deletedItems[deletedItemCount][4] = "Rusak";
                            } else if (reason.equalsIgnoreCase("terjual")) {
                                deletedItems[deletedItemCount][4] = "Terjual";
                            } else {
                                deletedItems[deletedItemCount][4] = "Lainnya";
                            }
                            deletedItemCount++;
                            itemsFound = true;
                            System.out.println("Item taken.");
                        } else {
                            System.out.println("item quantity not enough.");
                        }
                    }
                }
                if (!itemsFound) {
                    System.out.println("item not found.");
                }
            }
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void displayDeletedItems() {
        if (loggedIn) {

            itemcount = 0;
            for (String[] item : deletedItems) {
                if (item != null && item[0] != null && item[1] != null && item[2] != null && item[4] != null) {
                    itemcount++;
                }
            }

            String[][] tableBuilder = new String[itemcount + 1][6];
            tableBuilder[0] = new String[] { "Item name", "Qty", "Price", "Date", "Status", "Catatan" };

            int i = 1;
            for (String[] deletedItem : deletedItems) {
                if (deletedItem != null && deletedItem[0] != null && deletedItem[1] != null && deletedItem[2] != null
                        && deletedItem[4] != null && deletedItem[5] != null) {
                    tableBuilder[i] = new String[] { deletedItem[0], deletedItem[1], "Rp." + deletedItem[2],
                            deletedItem[3], deletedItem[4], deletedItem[5] };
                    i++;
                }
            }

            printTableFromArrays(tableBuilder);
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void employee() {
        if (loggedIn) {

            userCount = 0;
            for (String[] user : users) {
                if (user != null && user[0] != null && user[1] != null && user[2] != null) {
                    userCount++;
                }
            }

            String[][] tableBuilder = new String[userCount + 1][3];
            tableBuilder[0] = new String[] { "Employee name", "Role" };

            int i = 1;
            for (String[] user : users) {
                if (user != null && user[0] != null && user[1] != null && user[2] != null) {
                    tableBuilder[i] = new String[] { user[0], user[2] };
                    i++;
                }
            }

            printTableFromArrays(tableBuilder);
        } else if (!loggedIn) {
            System.out.println("please login first.");
        }
    }

    static void adminMenu() {
        while (loggedIn) {
            System.out.println("+--------------------------+");
            System.out.println("|        Admin Menu        |");
            System.out.println("|--------------------------|");
            System.out.println("| 1. Input Data            |");
            System.out.println("| 2. Display Items         |");
            System.out.println("| 3. Report                |");
            System.out.println("| 4. Delete Items          |");
            System.out.println("| 5. Display Outgoing items|");
            System.out.println("| 6. Filtered Items        |");
            System.out.println("| 7. Searching             |");
            System.out.println("| 8. Logout                |");
            System.out.println("+--------------------------+");
            System.out.print("pilih: ");
            choose = Integer.parseInt(input.next());
            switch (choose) {
                case 1:
                    inputItems();
                    break;
                case 2:
                    displayItems();
                    break;
                case 3:
                    report();
                    break;
                case 4:
                    deleteItems();
                    break;
                case 5:
                    displayDeletedItems();
                    break;
                case 6:
                    displayFilteredInput();
                    break;
                case 7:
                    searchItems();
                    break;
                case 8:
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout successfully");
                    } else {
                        System.out.println("please login first");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;

            }
        }

    }

    static void ownerMenu() {
        while (loggedIn) {

            System.out.println("+--------------------------+");
            System.out.println("|        Owner Menu        |");
            System.out.println("|--------------------------|");
            System.out.println("| 1. Input Data            |");
            System.out.println("| 2. Display Items         |");
            System.out.println("| 3. Report                |");
            System.out.println("| 4. Delete Items          |");
            System.out.println("| 5. Display Outgoing items|");
            System.out.println("| 6. Filtered Items        |");
            System.out.println("| 7. Searching             |");
            System.out.println("| 8. Employee              |");
            System.out.println("| 9. Logout                |");
            System.out.println("+--------------------------+");
            System.out.print("pilih: ");
            choose = Integer.parseInt(input.next());
            switch (choose) {
                case 1:
                    inputItems();
                    break;
                case 2:
                    displayItems();
                    break;
                case 3:
                    report();
                    break;
                case 4:
                    deleteItems();
                    break;
                case 5:
                    displayDeletedItems();
                    break;
                case 6:
                    displayFilteredInput();
                    break;
                case 7:
                    searchItems();
                    break;
                case 8:
                    employee();
                    break;
                case 9:
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout successfully");
                    } else {
                        System.out.println("please login first.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;

            }
        }
    }

    static void userMenu() {
        while (loggedIn) {
            System.out.println("+--------------------------+");
            System.out.println("|         User Menu        |");
            System.out.println("|--------------------------|");
            System.out.println("| 1. Display Items         |");
            System.out.println("| 2. Report                |");
            System.out.println("| 3. Display Outgoing items|");
            System.out.println("| 4. Filtered Items        |");
            System.out.println("| 5. Searching             |");
            System.out.println("| 6. Logout                |");
            System.out.println("+--------------------------+");
            System.out.print("pilih: ");
            choose = Integer.parseInt(input.next());
            switch (choose) {
                case 1:
                    displayItems();
                    break;
                case 2:
                    report();
                    break;
                case 3:
                    displayDeletedItems();
                    break;
                case 4:
                    displayFilteredInput();
                    break;
                case 5:
                    searchItems();
                    break;
                case 6:
                    if (loggedIn) {
                        loggedIn = false;
                        System.out.println("Logout successfully");
                    } else {
                        System.out.println("please login first.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;

            }
        }
    }

    static void loginForm() {
        System.out.println("+--------------------------+");
        System.out.println("|        LOGIN FORM        |");
        System.out.println("|--------------------------|");
        System.out.println("| 1. Log in                |");
        System.out.println("| 2. Sign up               |");
        System.out.println("| 3. Exit                  |");
        System.out.println("+--------------------------+");
        System.out.print("Pilih 1-3: ");
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

        System.out.println("+-------------------------------------+");
        System.out.println("    |           " + title + "            |");
        System.out.println("|-------------------------------------|");
        System.out.println("| Item name | Qty | Price | Date      |");
        System.out.println("+-------------------------------------+");
        for (int i = 0; i < deletedItems.length; i++) {
            if (deletedItems[i][0] != null && filterType.equalsIgnoreCase(deletedItems[i][4])) {
                System.out.println("| " + deletedItems[i][0] + " | " + deletedItems[i][1] + " | Rp."
                        + deletedItems[i][2] + " | " + deletedItems[i][3] + " |");
            }
        }
        System.out.println("+-------------------------------------+");
    }

    static void displayFilteredInput() {
        System.out.print("Filter Item (Rusak/Terjual):");
        String reason = input.next();
        displayFilteredItems(reason);
    }

    static void searchItems() {
        if (loggedIn) {
            System.out.print("Enter Item name yang dicari: ");
            String searchTerm = input.next();

            System.out.println("+-------------------------------------+");
            System.out.println("|            Search Result            |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Item name | Qty | Price | Date      |");
            System.out.println("+-------------------------------------+");

            boolean itemFound = false;
            for (String[] item : items) {
                if (item[0] != null && item[0].equals(searchTerm)) {
                    System.out.println(
                            "| " + item[0] + " | " + item[1] + " | Rp." + item[2] + " | " + formattedDate + " |");
                    itemFound = true;
                }
            }

            if (!itemFound) {
                System.out.println("Item not found.");
            }
            System.out.println("+-------------------------------------+");
        } else {
            System.out.println("please login first..");
        }
    }

    static int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        return sum;
    }

    static void printTableFromArrays(String[][] tableBuilder) {
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
