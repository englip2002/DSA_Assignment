
// ChiewHongKuang
import java.time.LocalDate;
import java.util.Scanner;

public class TableManager {

    private FileHandler<MapInterface<String, Table>> tablesFile;
    private MapInterface<String, Table> tableList;
    private MapInterface<String, Customer> customerList;
    private Scanner sc;

    public TableManager() {

        tablesFile = new FileHandler<>("Tables.dat");
        tableList = new HashMap<>();
        customerList = new HashMap<>();
        sc = new Scanner(System.in);
        tableList.put("TB00000", new Table("TB00000", "Package A: Westen Food", 9, LocalDate.of(2022, 9, 1)));
        tableList.put("TB00001", new Table("TB00001", "Package B: Chinese Food", 8, LocalDate.of(2022, 8, 31)));
        tableList.put("TB00002", new Table("TB00002", "Package C: Indian Food", 5, LocalDate.of(2022, 9, 3)));
        tableList.put("TB00003", new Table("TB00003", "Package D: Malay Food", 5, LocalDate.of(2022, 9, 4)));
        tableList.put("TB00004", new Table("TB00004", "Package E: Thai Food", 6, LocalDate.of(2022, 9, 1)));
        customerList.put("CT00000", new Customer("CT00000", "Amelia", "Watson", "Female", LocalDate.of(2000, 1, 6)));
        customerList.put("CT00001", new Customer("CT00001", "Ching", "Chong", "Male", LocalDate.of(1998, 12, 31)));
        customerList.put("CT00002", new Customer("CT00002", "Abdulah", "Hasan", "Male", LocalDate.of(2005, 6, 5)));
        customerList.put("CT00003", new Customer("CT00003", "Yukimura", "", "Sanada", LocalDate.of(1990, 4, 1)));
        customerList.put("CT00004", new Customer("CT00004", "Takahashi", "Rinka", "Female", LocalDate.of(20010, 7, 24)));
        // Insert Table 0's Customer List
        tableList.get("TB00000").getTableCustomers().put("CT00000", customerList.get("CT00000"));
        tableList.get("TB00000").getTableCustomers().put("CT00001", customerList.get("CT00001"));
        tableList.get("TB00000").getTableCustomers().put("CT00002", customerList.get("CT00002"));
        tableList.get("TB00000").getTableCustomers().put("CT00003", customerList.get("CT00003"));
        tableList.get("TB00000").getTableCustomers().put("CT00004", customerList.get("CT00004"));
        // Insert Table 1's Customer List
        tableList.get("TB00001").getTableCustomers().put("CT00000", customerList.get("CT00000"));
        tableList.get("TB00001").getTableCustomers().put("CT00001", customerList.get("CT00001"));
        tableList.get("TB00001").getTableCustomers().put("CT00004", customerList.get("CT00004"));
        // Insert Table 2's Customer List
        tableList.get("TB00002").getTableCustomers().put("CT00001", customerList.get("CT00001"));
        tableList.get("TB00002").getTableCustomers().put("CT00002", customerList.get("CT00002"));
        // Insert Table 3's Customer List
        tableList.get("TB00003").getTableCustomers().put("CT00001", customerList.get("CT00001"));
        tableList.get("TB00003").getTableCustomers().put("CT00003", customerList.get("CT00003"));
        tableList.get("TB00003").getTableCustomers().put("CT00004", customerList.get("CT00004"));
        tablesFile.write(tableList);
    }

    public void tableModule() {

        int selection;

        // Table Menu
        do {
            System.out.println("\nTable Module");
            System.out.println("============");
            System.out.println("1. Display Tables Details");
            System.out.println("2. Add New Customer");
            System.out.println("3. Create New Table");
            System.out.println("4. Edit Table Details");
            System.out.println("5. Delete Table");
            System.out.println("6. Visualize Seat Occupying Rate");
            System.out.print("Selection (-1 to exit) > ");
            selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 1:
                    displayTablesDetails();
                    break;
                case 2:
                    addNewCustomer();
                    break;
                case 3:
                    createNewTable();
                    break;
                case 4:
                    editTableDetails();
                    break;
                case 5:
                    deleteTable();
                    break;
                case 6:
                    visualizeSeatOccupyingRate();
                    break;
                case -1:
                    // Exit
                    break;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        } while (selection != -1);

        System.out.println("Return to last stage.");
    }

    private void displayTablesDetails() {

        // Display Table Details
        System.out.println("\nTables Details");
        System.out.println("==============");

        tableList = (MapInterface<String, Table>) tablesFile.read();

        if (tableList.size() != 0) {
            int sumOfCustomer = 0;
            Table[] t = new Table[tableList.size()];
            System.arraycopy(tableList.values(), 0, t, 0, tableList.size());

            for (Table each : t) {
                System.out.println(each.tableInfo());
                System.out.println("********************\n");
                sumOfCustomer += each.getTableCustomers().size();
            }

            System.out.println("Number of Tables: " + t.length);
            System.out.println("Number of Customers' Reservations: " + sumOfCustomer + "\n");
        } else {
            System.out.println("No Table Detail.");
        }
    }

    private void addNewCustomer() {

        // Add New Customer
        char choice;

        do {
            System.out.println("\nAdd New Customer");
            System.out.println("================");
            System.out.print("Add a new customer? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    System.out.print("Enter first name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = sc.nextLine();

                    boolean isGender = false;
                    String gender;

                    do {
                        System.out.print("Enter gender (Male / Female / Other): ");
                        gender = sc.nextLine();

                        if (gender.equals("Male") || gender.equals("Female") || gender.equals("Other")) {
                            isGender = true;
                        } else {
                            System.out.println("Wrong gender! Please try again.");
                        }
                    } while (!isGender);

                    System.out.print("Enter year of birth: ");
                    int yob = sc.nextInt();
                    System.out.print("Enter month of birth: ");
                    int mob = sc.nextInt();
                    System.out.print("Enter day of birth: ");
                    int dayob = sc.nextInt();
                    sc.nextLine();
                    LocalDate dob = LocalDate.of(yob, mob, dayob);

                    Customer c = new Customer(String.format("CT%05d", customerList.size()), firstName, lastName, gender, dob);

                    char confirmChoice;

                    do {
                        System.out.print("Confirm to add? (Y = yes, N = no) > ");
                        confirmChoice = sc.next().charAt(0);
                        sc.nextLine();

                        switch (Character.toUpperCase(confirmChoice)) {
                            case 'Y':
                                customerList.put(c.getCustomerId(), c);
                                tablesFile.write(tableList);

                                System.out.println("\nAdded Customer");
                                System.out.println("==============");
                                System.out.println(customerList.get(c.getCustomerId()).customerInfo());
                                break;
                            case 'N':
                                System.out.println("Add discarded!");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while (Character.toUpperCase(confirmChoice) != 'Y' && Character.toUpperCase(confirmChoice) != 'N');
                    break;
                case 'N':
                    System.out.println("Access denied.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'N');
    }

    private void createNewTable() {

        // Create New Table
        char choice;

        do {
            System.out.println("\nCreate New Table");
            System.out.println("================");
            System.out.print("Create a new table? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    System.out.print("Enter package served: ");
                    String packageServed = sc.nextLine();

                    int numberOfSeats = inputNumberOfSeats();

                    LocalDate dor = inputReservedDate();

                    Table t = new Table(String.format("TB%05d", tableList.size()), packageServed, numberOfSeats, dor);

                    t = inputTableCustomers(t);

                    char confirmChoice;

                    do {
                        System.out.print("Confirm to craete? (Y = yes, N = no) > ");
                        confirmChoice = sc.next().charAt(0);
                        sc.nextLine();

                        switch (Character.toUpperCase(confirmChoice)) {
                            case 'Y':
                                tableList.put(t.getTableId(), t);
                                tablesFile.write(tableList);

                                System.out.println("\nCreated Table");
                                System.out.println("=============");
                                System.out.println(tableList.get(t.getTableId()).tableInfo());
                                break;
                            case 'N':
                                System.out.println("Creation discarded!");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while (Character.toUpperCase(confirmChoice) != 'Y' && Character.toUpperCase(confirmChoice) != 'N');
                    break;
                case 'N':
                    System.out.println("Access denied.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'N');
    }

    private void editTableDetails() {

        // Edit Table Details
        char choice;

        do {
            System.out.println("\nEdit Table Details");
            System.out.println("==================");
            System.out.print("Edit a table? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    String tableId = inputTableID();
                    String packageServed = tableList.get(tableId).getPackageServed();
                    int numberOfSeats = tableList.get(tableId).getNumberOfSeats();
                    LocalDate dor = tableList.get(tableId).getReservedDate();
                    MapInterface<String, Customer> tableCustomers = tableList.get(tableId).getTableCustomers();
                    Table t;

                    int selection;
                    boolean isValid = false;

                    do {
                        System.out.println("\nEdit Options");
                        System.out.println("============");
                        System.out.println("1. Package Served");
                        System.out.println("2. Number of Seats");
                        System.out.println("3. Reserved Date");
                        System.out.println("4. Table Customers");
                        System.out.println("5. All of the above");
                        System.out.print("Selection > ");
                        selection = sc.nextInt();
                        sc.nextLine();

                        if (selection == 1 || selection == 2 || selection == 3 || selection == 4 || selection == 5) {
                            if (selection == 1 || selection == 5) {
                                System.out.print("Enter package served: ");
                                packageServed = sc.nextLine();
                            }

                            if (selection == 2 || selection == 5) {
                                numberOfSeats = inputNumberOfSeats(tableCustomers.size());
                            }

                            if (selection == 3 || selection == 5) {
                                dor = inputReservedDate();
                            }

                            if (selection == 4 || selection == 5) {
                                t = new Table(tableId, packageServed, numberOfSeats, dor, new HashMap<>());

                                char editChoice;

                                do {
                                    System.out.print("Use a new reserved customer list? (Y = yes, N = no) > ");
                                    editChoice = sc.next().charAt(0);
                                    sc.nextLine();

                                    switch (Character.toUpperCase(editChoice)) {
                                        case 'Y':
                                            t = inputTableCustomers(t);
                                            tableCustomers = t.getTableCustomers();
                                            break;
                                        case 'N':
                                            System.out.println("Pervious customer list is retained.");
                                            break;
                                        default:
                                            System.out.println("Invalid choice! Please try again.");
                                            break;
                                    }
                                } while (Character.toUpperCase(editChoice) != 'Y' && Character.toUpperCase(editChoice) != 'N');
                            }

                            isValid = true;
                        } else {
                            System.out.println("Invalid selection! Please try again.");
                        }
                    } while (!isValid);

                    t = new Table(tableId, packageServed, numberOfSeats, dor, tableCustomers);

                    char confirmChoice;

                    do {
                        System.out.print("Confirm to edit? (Y = yes, N = no) > ");
                        confirmChoice = sc.next().charAt(0);
                        sc.nextLine();

                        switch (Character.toUpperCase(confirmChoice)) {
                            case 'Y':
                                Table oldTable = tableList.put(t.getTableId(), t);
                                tablesFile.write(tableList);

                                System.out.println("\nTable Details (Before Edit)");
                                System.out.println("===========================");
                                System.out.println(oldTable.tableInfo());
                                System.out.println("\nTable Details (After Edit)");
                                System.out.println("==========================");
                                System.out.println(tableList.get(t.getTableId()).tableInfo());
                                break;
                            case 'N':
                                System.out.println("Edit discarded!");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while (Character.toUpperCase(confirmChoice) != 'Y' && Character.toUpperCase(confirmChoice) != 'N');
                    break;
                case 'N':
                    System.out.println("Access denied.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }

        } while (Character.toUpperCase(choice)
                != 'N');
    }

    private void deleteTable() {

        // Delete Table
        char choice;

        do {
            System.out.println("\nDelete Table");
            System.out.println("============");
            System.out.print("Delete a table? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    String tableId = inputTableID();

                    char confirmChoice;

                    do {
                        System.out.print("Confirm to delete? (Y = yes, N = no) > ");
                        confirmChoice = sc.next().charAt(0);
                        sc.nextLine();

                        switch (Character.toUpperCase(confirmChoice)) {
                            case 'Y':
                                Table deletedTable = tableList.remove(tableId);
                                tablesFile.write(tableList);

                                System.out.println("\nDeleted Table");
                                System.out.println("=============");
                                System.out.println(deletedTable.tableInfo());
                                break;

                            case 'N':
                                System.out.println("Deletion discarded!");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while (Character.toUpperCase(confirmChoice) != 'Y' && Character.toUpperCase(confirmChoice) != 'N');
                    break;
                case 'N':
                    System.out.println("Access denied.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'N');
    }

    private void visualizeSeatOccupyingRate() {

        // Visualize Seat Occupying Rate
        System.out.println("\nVisualize Seat Occupying Rate");
        System.out.println("=============================");

        tableList = (MapInterface<String, Table>) tablesFile.read();

        if (tableList.size() != 0) {
            Table[] t = new Table[tableList.size()];
            System.arraycopy(tableList.values(), 0, t, 0, tableList.size());
            int[] barValues = new int[10];
            int emptyCount = 0;
            String spaces = "    ";
            String bar = "[=====]";
            String noBar = "       ";

            for (Table each : t) {
                int index = (int) java.lang.Math.ceil((double) each.getTableCustomers().size() / each.getNumberOfSeats() * 10) - 1;

                if (index < 0) {
                    emptyCount += 1;
                } else {
                    barValues[index] += 1;
                }
            }

            int largestValue = 0;

            for (int i = 0; i < barValues.length; i++) {
                if (barValues[i] > largestValue) {
                    largestValue = barValues[i];
                }
            }

            System.out.println("                                                Bar Chart of Seat Occupying Rate (%)");
            System.out.println(" Number of Table");
            System.out.println("        ^");
            System.out.println("        |");

            for (int i = largestValue; i > 0; i--) {
                String line = spaces + String.format("%3d", i) + " +" + spaces;

                for (int j = 0; j < barValues.length; j++) {
                    if (barValues[j] == i) {
                        line += bar;
                        barValues[j] -= 1;
                    } else {
                        line += noBar;
                    }

                    line += spaces;
                }

                System.out.println(line);
            }

            System.out.println("--------+-------+----------+----------+----------+----------+----------+----------+----------+----------+----------+-------> Seat Occupying Rate (%)");
            System.out.println("      0 |     >0~10     >10~20     >20~30     >30~40     >40~50     >50~60     >60~70     >70~80     >80~90     >90~100");
            System.out.println("        |\n");
            System.out.println("\t                    Table of Seat Occupying Rate (%)");
            System.out.println("\t++==========++==================++==============++====================++");
            System.out.println("\t|| Table ID || No. of Customers || No. of Seats || Occupying Rate (%) ||");
            System.out.println("\t++==========++==================++==============++====================++");
            for (Table each : t) {
                System.out.println(String.format("\t|| %-8s ||        %2d        ||      %2d      ||       %6.2f       ||", each.getTableId(), each.getTableCustomers().size(), each.getNumberOfSeats(), (double) each.getTableCustomers().size() / each.getNumberOfSeats() * 100));
            }
            System.out.println("\t++==========++==================++==============++====================++\n");
            System.out.println("\tNote: Empty tables will be treated as invalid data.");
            System.out.println("\tNumber of empty table: " + emptyCount);
        } else {
            System.out.println("No Table.");
        }
    }

    private void displayTableList() {

        // Display Table List
        System.out.println("\nTable List");
        System.out.println("==========");

        tableList = (MapInterface<String, Table>) tablesFile.read();

        if (tableList.size() != 0) {
            Table[] t = new Table[tableList.size()];
            System.arraycopy(tableList.values(), 0, t, 0, tableList.size());

            for (Table each : t) {
                System.out.println(each.getTableId() + ": " + each.getPackageServed() + " (" + each.getTableCustomers().size() + " / " + each.getNumberOfSeats() + ")");
            }

            System.out.println("Number of Tables: " + t.length + "\n");
        } else {
            System.out.println("No Table.");
        }
    }

    private void displayCustomerList() {

        // Display Table List
        System.out.println("\nCustomer List");
        System.out.println("=============");

        if (customerList.size() != 0) {
            Customer[] c = new Customer[customerList.size()];
            System.arraycopy(customerList.values(), 0, c, 0, customerList.size());

            for (Customer each : c) {
                System.out.println(each.getCustomerId() + ": " + each.getFullName());
            }

            System.out.println("Number of Customers: " + c.length + "\n");
        } else {
            System.out.println("No Customer.");
        }
    }

    private String inputTableID() {

        String tableId;

        displayTableList();
        System.out.print("Enter table id: ");
        tableId = sc.nextLine();

        while (!tableList.containsKey(tableId)) {
            System.out.println("Invalid table id! Please try again.");
            System.out.print("Enter table id: ");
            tableId = sc.nextLine();
        }

        return tableId;
    }

    private int inputNumberOfSeats() {

        int numberOfSeats;
        boolean isValid = false;

        do {
            System.out.print("Enter number of seats (maximum seats = 10): ");
            numberOfSeats = sc.nextInt();
            sc.nextLine();

            if (numberOfSeats < 1) {
                System.out.println("Minimum number of seats is 1! Please try again.");
            } else if (numberOfSeats > 10) {
                System.out.println("Maximum number of seats is 10! Please try again.");
            } else {
                isValid = true;
            }
        } while (!isValid);

        return numberOfSeats;
    }

    private int inputNumberOfSeats(int minSeats) {

        int numberOfSeats;
        boolean isValid = false;

        do {
            System.out.print("Enter number of seats (maximum seats = 10): ");
            numberOfSeats = sc.nextInt();
            sc.nextLine();

            if (numberOfSeats < minSeats) {
                System.out.println(String.format("Minimum number of seats is %d! Please try again.", minSeats));
            } else if (numberOfSeats > 10) {
                System.out.println("Maximum number of seats is 10! Please try again.");
            } else {
                isValid = true;
            }
        } while (!isValid);

        return numberOfSeats;
    }

    private LocalDate inputReservedDate() {

        char choice;
        LocalDate dor = LocalDate.now();

        do {
            System.out.print("Specify a reservation date? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    System.out.print("Enter year of reserved: ");
                    int yor = sc.nextInt();
                    System.out.print("Enter month of reserved: ");
                    int mor = sc.nextInt();
                    System.out.print("Enter day of reserved: ");
                    int dayor = sc.nextInt();
                    sc.nextLine();
                    dor = LocalDate.of(yor, mor, dayor);
                    break;
                case 'N':
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N');

        return dor;
    }

    private Table inputTableCustomers(Table t) {

        String customerId;
        char choice;

        do {
            System.out.print("Add a customer to the table? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    displayCustomerList();
                    System.out.print("Enter customer id: ");
                    customerId = sc.nextLine();

                    if (customerList.containsKey(customerId)) {
                        if (!t.getTableCustomers().containsKey(customerId)) {
                            t.addCustomer(customerList.get(customerId));
                            System.out.println("Customer: " + customerList.get(customerId).getFullName() + " (" + customerId + ") is added.");
                        } else {
                            System.out.println("Customer id already exist! Please try again.");
                        }
                    } else {
                        System.out.println("Invalid customer id! Please try again.");
                    }
                    break;
                case 'N':
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'N' && t.getNumberOfSeats() > t.getTableCustomers().size());

        return t;
    }
}
