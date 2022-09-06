import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        Account account = new Account("A0001", "1234", "Tan", "EngLip", "Male", LocalDate.of(2002, 11, 22), "Customer");

        ReservationModule(account);
    }

    public static void ReservationModule(Account account) {
        // read file when starting
        // reservation
        Reservation reservation = new Reservation(account);
        FileHandler reservationFile = new FileHandler("Reservations.dat");
        ListInterface<Reservation> reservationList;
        // System.out.println(reservationList.getEntry(0).toString());

        // menu
        // FileHandler packageFile = new FileHandler("Package.dat");
        // SetInterface<Package> packageSet = (SetInterface)packageFile.read();

        // temporary package
        SetInterface<Package> packageSet = new ArraySet<Package>();
        Package package1 = new Package("Package1", 125.00, "Cheap");
        MenuItem cornSoup = new MenuItem("Appertizer", "Corn Soup", "Deli");
        package1.addMenuItemToPackage(cornSoup);
        packageSet.add(package1);

        int choice = 0;
        boolean dateValidity;

        // reservation input
        String contactNo, serveLocation;
        String serveDateString, serveTimeString;

        // reservation process
        int reservationProcessChoice;

        // menu input
        int packageChoice = -1;
        int menuItemChoice = -1;
        int menuItemQuantity;
        int totalMenuItemChoosen = 0;
        Character cartContinue = 'N';
        int cartRemovePosition;

        // remove
        int removeChoice = -1;

        // search
        int searchChoice;
        String searchDateString;
        String searchName;
        LocalDateTime searchTime = LocalDateTime.now();
        boolean searchFlag = false;

        LocalDateTime serveTime = null;
        Scanner scanner = new Scanner(System.in);
        do {
            reservationList = (ListInterface) reservationFile.read();
            System.out.println("\nReservation Module");
            System.out.println("========================");
            System.out.println("1. Make Reservation for current account");
            System.out.println("2. View all reservation history");
            System.out.println("3. Remove Reservation");
            System.out.println("4. Search reservation");
            System.out.println("5. Exit");
            do {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > 4);

            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    // view all reservation history
                    if (reservationList == null || reservationList.getNumberOfEntries() == 0) {
                        System.out.println("No Reservation Stored!");
                        System.out.println("Press <Enter> to continue.");
                        scanner.nextLine();
                        scanner.nextLine();
                    } else {
                        System.out.print(
                                String.format("%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", "No", "ReservationID",
                                        "AccountID",
                                        "ContactNo",
                                        "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                        int i = 0;
                        for (Reservation reserve : reservationList) {
                            i++;
                            System.out.print(String.format("%-3s %-115s", i, reserve.toString()));
                        }
                        System.out.println("Total Number of Reservation: " + reservationList.getNumberOfEntries());
                        System.out.println("Press <Enter> to continue.");
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    // remove reservation history
                    if (reservationList == null || reservationList.getNumberOfEntries() == 0) {
                        System.out.println("No Reservation Stored!");
                        System.out.println("Press <Enter> to continue.");
                        scanner.nextLine();
                        scanner.nextLine();
                    } else {
                        System.out.print(
                                String.format("%-3s %-15s %-15s %-15s %-15s %-20s %-20s %-10s\n", "No", "ReservationID",
                                        "AccountID",
                                        "ContactNo",
                                        "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                        // for each loop
                        int i = 0;
                        for (Reservation reserve : reservationList) {
                            i++;
                            System.out.print(String.format("%-3d %s", i, reserve.toString()));
                        }
                        do {
                            System.out.print("Please Enter your choice to remove (-1 to exit):");
                            removeChoice = scanner.nextInt();

                            if (removeChoice == -1) {
                                System.out.println("Exited!");
                                System.out.println("Press <Enter> to continue.");
                                scanner.nextLine();
                            } else if (removeChoice > reservationList.getNumberOfEntries() || removeChoice < 1) {
                                System.out.println("Invalid Choice!");
                                System.out.println("Press <Enter> to continue.");
                                scanner.nextLine();
                            } else {
                                reservationList.remove(removeChoice - 1);
                                reservationFile.write(reservationList);
                            }

                        } while (removeChoice > reservationList.getNumberOfEntries()
                                || (removeChoice <= 0 && removeChoice != -1));
                    }
                    break;
                case 4:
                    searchFlag = false;
                    // Search by date, Name
                    System.out.println("Search");
                    System.out.println("===========");
                    System.out.println("1. By Reserve Date");
                    System.out.println("2. By Serve Date");
                    System.out.println("3. By Name");
                    do {
                        System.out.print("Enter your choice: ");
                        searchChoice = scanner.nextInt();
                        if (searchChoice < 1 || searchChoice > 3) {
                            System.out.println("Invalid Choice!");
                        }
                    } while (searchChoice < 1 || searchChoice > 3);

                    if (searchChoice == 1) {
                        do {
                            dateValidity = true;
                            try {
                                System.out.print("Enter date (YYYY-MM-DD): ");
                                searchDateString = scanner.next();

                                // formatting the entered and store in serveDateandTime
                                searchTime = LocalDateTime.of(LocalDate.parse(searchDateString),
                                        LocalTime.now());

                            } catch (DateTimeParseException e) {
                                dateValidity = false;
                            }

                            // print error message
                            if (dateValidity == false)
                                System.out.println("Invalid Date Format! Please Re-enter.\n");

                        } while (dateValidity == false);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        // print result
                        searchFlag = false;
                        int i = 0;
                        System.out.println(
                                String.format("%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", "No", "ReservationID",
                                        "AccountID",
                                        "ContactNo",
                                        "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));
                        for (Reservation reservations : reservationList) {
                            if (reservations.getReserveDate().format(formatter)
                                    .compareTo(searchTime.format(formatter)) == 0) {
                                i++;
                                System.out.println(String.format("%-3d %s", i, reservations.toString()));
                                searchFlag = true;
                            }
                        }
                    } else if (searchChoice == 2) {
                        do {
                            dateValidity = true;
                            try {
                                System.out.print("Enter date (YYYY-MM-DD): ");
                                searchDateString = scanner.next();

                                // formatting the entered and store in serveDateandTime
                                searchTime = LocalDateTime.of(LocalDate.parse(searchDateString),
                                        LocalTime.now());

                            } catch (DateTimeParseException e) {
                                dateValidity = false;
                            }

                            // print error message
                            if (dateValidity == false)
                                System.out.println("Invalid Date Format! Please Re-enter.\n");

                        } while (dateValidity == false);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        // print result
                        searchFlag = false;
                        int i = 0;
                        System.out.println(
                                String.format("%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", "No", "ReservationID",
                                        "AccountID",
                                        "ContactNo",
                                        "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                        for (Reservation reservations : reservationList) {
                            if (reservations.getReserveDate().format(formatter)
                                    .compareTo(searchTime.format(formatter)) == 0) {
                                i++;
                                System.out.println(String.format("%-3d %-115s", i, reservations.toString()));
                                searchFlag = true;
                            }
                        }
                    } else if (choice == 3) {
                        System.out.print("Enter Search Name: ");
                        searchName = scanner.nextLine();

                        // print result
                        searchFlag = false;
                        int i = 0;
                        System.out.println(
                                String.format("%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", "No", "ReservationID",
                                        "AccountID",
                                        "ContactNo",
                                        "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                        for (Reservation reservations : reservationList) {
                            if (reservations.getAccount().getFullName().compareToIgnoreCase(searchName) == 0) {
                                i++;
                                System.out.println(String.format("%-3d %s", i, reservations.toString()));
                                searchFlag = true;
                            }
                        }
                    }

                    if (searchFlag == false) {
                        System.out.println("No Record Found!");
                    }

                    System.out.println("Press <Enter> to continue.");
                    scanner.nextLine();
                    scanner.nextLine();
            }
        } while (choice != 5);

        // input to file when module end
        scanner.close();
        reservationFile.write(reservationList);
    }

    public static String viewCart(Reservation reservation) {
        String str = "";
        str += "\nItems In Cart\n";
        str += "================\n";
        str += String.format("%-5s %-20s %-10s\n", "No", "Dish Name",
                 "Quantity");
        for (int i = 0; i < reservation.getFoodInCart().getNumberOfEntries(); i++) {
            str += String.format("%-5d %-30s\n", (i + 1), reservation.getFoodInCart().getEntry(i).toString());
        }
        str += String.format("Total: %0.2f\n", reservation.getChoosenPackage().getPackagePrice());
        return str;
    }
}
