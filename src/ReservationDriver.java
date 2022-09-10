import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        ReservationModule();
    }

    public static void ReservationModule() {
        // dummy customer
        Customer customer = new Customer("A0001", "Tan", "EngLip", "Male", LocalDate.of(2002, 11, 22));
        // read file when starting
        // reservation
        Reservation reservation;
        FileHandler reservationFile = new FileHandler("Reservations.dat");
        ListInterface<Reservation> reservationList = (ListInterface) reservationFile.read();

        if (reservationList == null || reservationList.getNumberOfEntries() == 0) {
            reservation = new Reservation(customer);
        } else {
            reservation = new Reservation(customer, getLastReservationID(reservationList));
        }

        // package
        FileHandler packageFile = new FileHandler("Menu.dat");
        SetInterface<Package> packageSet = (SetInterface) packageFile.read();

        // temporary package
        // SetInterface<Package> packageSet = new ArraySet<Package>();
        // Package package1 = new Package("package1", 125, 5, "cheap");
        // MenuItem cornSoup = new MenuItem("Appertizer", "Corn Soup", "Deli");
        // package1.addMenuItemToPackage(cornSoup);
        // packageSet.add(package1);

        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            // update the list every time loop
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
                scanner.nextLine();
            } while (choice < 1 || choice > 5);

            switch (choice) {
                case 1:
                    // make reservation
                    makeReservation(scanner, reservationFile, reservationList, packageSet, reservation);
                    break;
                case 2:
                    // view all reservation history
                    viewReservationHistory(scanner, reservationList);
                    break;
                case 3:
                    // remove reservation history
                    removeReservation(scanner, reservationFile, reservationList);
                    break;
                case 4:
                    // Search by date, Name
                    searchReservation(scanner, reservationList);
                    break;

                case 5:
                    System.out.println("Exited!!");
                    pressEnterToContinue(scanner);
                    break;
            }

        } while (choice != 5);

        // input to file when module end
        scanner.close();
        reservationFile.write(reservationList);
    }

    public static void makeReservation(Scanner scanner, FileHandler reservationFile,
            ListInterface<Reservation> reservationList,
            SetInterface<Package> packageSet, Reservation reservation) {
        boolean dateValidity;

        // reservation input
        String contactNo, serveLocation;
        String serveDateString, serveTimeString;
        LocalDateTime serveTime = null;

        // reservation process
        int reservationProcessChoice;

        // menu input
        int packageChoice = -1;
        int menuItemChoice = -1;
        int menuItemQuantity;
        int totalMenuItemChoosen = 0;
        int cartRemovePosition;

        do {
            System.out.println("\nReservation Process");
            System.out.println("========================");
            System.out.println("1. Enter reservation details");
            System.out.println("2. Enter food into cart");
            System.out.println("3. Remove food from cart");
            System.out.println("4. View Cart");
            System.out.println("5. CheckOut");
            System.out.println("6. Exit");

            do {
                System.out.print("Enter your choice: ");
                reservationProcessChoice = scanner.nextInt();
                scanner.nextLine();
                if (reservationProcessChoice < 1 || reservationProcessChoice > 6) {
                    System.out.println("Invalid Choice!");
                }
            } while (reservationProcessChoice < 1 || reservationProcessChoice > 6);

            switch (reservationProcessChoice) {
                case 1:
                    do {
                        System.out.print("Please enter your contact No (XXX-XXXXXXX): ");
                        contactNo = scanner.nextLine();
                    } while (contactNo.length() != 11);

                    System.out.print("Please enter your serve location: ");
                    serveLocation = scanner.nextLine();

                    do {
                        dateValidity = true;
                        try {
                            System.out.print("Enter serve date (YYYY-MM-DD): ");
                            serveDateString = scanner.next();

                            System.out.print("Enter serve time (HH:MM): ");
                            serveTimeString = scanner.next();

                            // formatting the entered and store in serveDateandTime
                            serveTime = LocalDateTime.of(LocalDate.parse(serveDateString),
                                    LocalTime.parse(serveTimeString));

                        } catch (DateTimeParseException e) {
                            dateValidity = false;
                        }

                        // print error message
                        if (dateValidity == false)
                            System.out.println("Invalid Date Format! Please Re-enter.\n");

                    } while (dateValidity == false);

                    // enter food package choice
                    do {
                        System.out.println("\nEnter Package: ");
                        // print category choice
                        System.out.print(String.format("%-3s %-15s %-10s\n", "No", "PackageName",
                                "Price(RM)"));
                        for (int i = 0; i < packageSet.getNumberOfEntries(); i++) {
                            System.out.print(String.format("%-3d %-15s %-10.2f\n", (i + 1),
                                    packageSet.getElementAtPos(i).getPackageName(),
                                    packageSet.getElementAtPos(i).getPackagePrice()));
                        }
                        System.out.print("Enter package choice: ");
                        packageChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (packageChoice < 1
                                || packageChoice > packageSet.getNumberOfEntries()) {
                            System.out.println("Invalid Choice!");

                        }
                        // validation for package choice
                    } while (packageChoice < 1
                            || packageChoice > packageSet.getNumberOfEntries());

                    // input data into reservation
                    reservation.reserveDetails(contactNo, serveLocation, serveTime);
                    // set package in reservation
                    reservation.setPackageChoice(packageSet.getElementAtPos(packageChoice - 1));
                    break;

                case 2:
                    // check if the quantity exceed the limit
                    if (reservation.getChoosenPackage() == null) {
                        System.out.println("\nPlease choose package in reservation detail section!");
                        pressEnterToContinue(scanner);

                    } else if (totalMenuItemChoosen < reservation.getChoosenPackage().getMenuItemLimit()) {
                        // enter menu item choice
                        do {
                            // print foods from package and enter food choice
                            System.out
                                    .print("\n" + reservation.getChoosenPackage().printMenuItemInPackage());

                            System.out.print("Enter your food choice:");
                            menuItemChoice = scanner.nextInt();

                            if (menuItemChoice > reservation.getChoosenPackage().getAllPackageMenuItems()
                                    .getNumberOfEntries()
                                    || menuItemChoice < 1) {
                                System.out.println("invalid Choice!");
                            }
                        } while (menuItemChoice > reservation.getChoosenPackage()
                                .getAllPackageMenuItems().getNumberOfEntries() || menuItemChoice < 1);

                        // enter quantity
                        System.out.print("Please Enter Quantity:");
                        menuItemQuantity = scanner.nextInt();
                        totalMenuItemChoosen += menuItemQuantity;

                        while (totalMenuItemChoosen > reservation.getChoosenPackage().getMenuItemLimit()) {
                            System.out.println(
                                    "Total quantity (" + totalMenuItemChoosen + ") exceeded limit! ("
                                            + reservation.getChoosenPackage().getMenuItemLimit() + ")");
                            totalMenuItemChoosen -= menuItemQuantity;
                            System.out.print("Please re-enter Quantity:");
                            menuItemQuantity = scanner.nextInt();
                            totalMenuItemChoosen += menuItemQuantity;
                        }

                        // store into cart
                        MenuItem temp = packageSet.getElementAtPos(packageChoice - 1)
                                .getAllPackageMenuItems()
                                .getElementAtPos(menuItemChoice - 1);
                        reservation.getFoodInCart().add(new FoodInCart(temp, menuItemQuantity));

                        // display cart
                        System.out.println(viewCart(reservation));
                        pressEnterToContinue(scanner);

                    } else {
                        System.out.println("The total quantity of menu item has reached the limit ("
                                + reservation.getChoosenPackage().getMenuItemLimit() + ")");
                        pressEnterToContinue(scanner);

                    }

                    break;
                case 3:
                    // remove cart
                    // print item in cart
                    System.out.println(viewCart(reservation));

                    do {
                        // user enter choices to remove
                        System.out.print("Enter the number you wish to remove(-1 to exit): ");
                        cartRemovePosition = scanner.nextInt();
                        scanner.nextLine();

                        if (cartRemovePosition == -1) {
                            System.out.println("Exited!");
                            pressEnterToContinue(scanner);

                        } else if (cartRemovePosition > reservation.getFoodInCart().getNumberOfEntries()
                                || cartRemovePosition < 1) {
                            System.out.println("Invalid Input!");
                            pressEnterToContinue(scanner);

                        } else {
                            // update the totalMenuItemChoosen
                            totalMenuItemChoosen -= reservation.getFoodInCart()
                                    .getEntry(cartRemovePosition - 1).getQuantity();
                            // remove fron list
                            reservation.getFoodInCart().remove(cartRemovePosition - 1);
                            System.out.println("Removed Successfully");
                            pressEnterToContinue(scanner);

                        }

                        // validate remove choice
                    } while (cartRemovePosition > reservation.getFoodInCart().getNumberOfEntries()
                            || (cartRemovePosition < 1 && cartRemovePosition != -1));
                    break;
                case 4:
                    // view cart
                    System.out.print(viewCart(reservation));
                    pressEnterToContinue(scanner);

                    break;

                case 5:
                    // checkout
                    // confirm (print bill)
                    System.out.println("\nBills");
                    System.out.println("===========");
                    System.out.println(reservation.generateBill());
                    pressEnterToContinue(scanner);

                    reservation.checkOut();
                    // add into array and write into file, else discard
                    if (reservationList == null) {
                        ListInterface<Reservation> temp = new LinkedList<Reservation>();
                        temp.add(reservation);
                        reservationFile.write(temp);

                    } else {
                        reservationList.add(reservation);
                        reservationFile.write(reservationList);
                    }
                    // clear memory
                    reservation = new Reservation(reservation.getCustomer());
                    break;
                case 6:
                    System.out.println("\nExited!");
                    pressEnterToContinue(scanner);
                    break;

            }
        } while (reservationProcessChoice != 5 && reservationProcessChoice != 6);
    }

    public static void removeReservation(Scanner scanner, FileHandler reservationFile,
            ListInterface<Reservation> reservationList) {
        int removeChoice = -1;
        if (reservationList == null || reservationList.getNumberOfEntries() == 0) {
            System.out.println("No Reservation Stored!");
            pressEnterToContinue(scanner);

        } else {
            System.out.println(printReservationList(reservationList));
            do {
                System.out.print("Please Enter your choice to remove: ");
                removeChoice = scanner.nextInt();
                scanner.nextLine();

                if (removeChoice > reservationList.getNumberOfEntries()
                || removeChoice < 1) {
                    System.out.println("Invalid Choice!");
                } else {
                    reservationList.remove(removeChoice - 1);
                    reservationFile.write(reservationList);
                    System.out.println("Removed Successfully!");
                    pressEnterToContinue(scanner);

                }

            } while (removeChoice > reservationList.getNumberOfEntries()
                    || removeChoice < 1);
        }
    }

    public static void viewReservationHistory(Scanner scanner, ListInterface<Reservation> reservationList) {
        int displayChoice = 0;

        if (reservationList == null || reservationList.getNumberOfEntries() == 0) {
            System.out.println("No Reservation Stored!");
            pressEnterToContinue(scanner);

        } else {
            // sort
            System.out.println("\nDisplay by");
            System.out.println("========================");
            System.out.println("1. Reservation Date");
            System.out.println("2. Serve Date");
            System.out.println("3. Default");
            System.out.println("4. Exit");
            do {
                System.out.print("Enter your choice: ");
                displayChoice = scanner.nextInt();
                scanner.nextLine();

                switch (displayChoice) {
                    case 1:
                    case 2:
                        ListInterface<Reservation> temp = sortReservation(reservationList, displayChoice);
                        System.out.println(printReservationList(temp));
                        pressEnterToContinue(scanner);
                        break;
                    case 3:
                        // display by default
                        System.out.println(printReservationList(reservationList));
                        pressEnterToContinue(scanner);
                        break;
                    case 4:
                        System.out.println("Exited!!");
                        pressEnterToContinue(scanner);
                        break;
                    default:
                        System.out.println("Invalid Choice!!");
                        break;
                }
            } while (displayChoice != 4 && (displayChoice < 1 || displayChoice > 4));
        }
    }

    public static void searchReservation(Scanner scanner, ListInterface<Reservation> reservationList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean searchFlag = false;
        String searchReserveDate;
        String searchServeDate;
        String searchAccID;

        // change to search by 3 parameter different combination different result
        System.out.println("\nSearch");
        System.out.println("===========");
        System.out.print("Enter Reserve Date (YYYY-MM-DD) (<Enter> to skip) ");
        searchReserveDate = scanner.nextLine();
        System.out.print("Enter Serve Date (YYYY-MM-DD) (<Enter> to skip) ");
        searchServeDate = scanner.nextLine();

        System.out.print("Enter Account ID (<Enter> to skip) ");
        searchAccID = scanner.nextLine();

        System.out.println(String.format("\n%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s", "No",
                "ReservationID",
                "AccountID",
                "ContactNo",
                "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

        if (searchReserveDate.length() == 0 && searchServeDate.length() == 0 && searchAccID.length() == 0) {
            System.out.println("No reservation record found!!");
        } else if (searchReserveDate.length() == 0 && searchServeDate.length() == 0 && searchAccID.length() != 0) {
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getCustomer().getCustomerId().compareToIgnoreCase(searchAccID) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else if (searchReserveDate.length() == 0 && searchServeDate.length() != 0 && searchAccID.length() == 0) {
            LocalDate serveDate = LocalDate.parse(searchServeDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getServeDate().format(formatter)
                        .compareTo(serveDate.format(formatter)) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else if (searchReserveDate.length() == 0 && searchServeDate.length() != 0 && searchAccID.length() != 0) {
            LocalDate serveDate = LocalDate.parse(searchServeDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getServeDate().format(formatter)
                        .compareTo(serveDate.format(formatter)) == 0
                        && reservations.getCustomer().getCustomerId().compareToIgnoreCase(searchAccID) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else if (searchReserveDate.length() != 0 && searchServeDate.length() == 0 && searchAccID.length() == 0) {
            LocalDate reserveDate = LocalDate.parse(searchReserveDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getReserveDate().format(formatter)
                        .compareTo(reserveDate.format(formatter)) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else if (searchReserveDate.length() != 0 && searchServeDate.length() == 0 && searchAccID.length() != 0) {
            LocalDate reserveDate = LocalDate.parse(searchReserveDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getReserveDate().format(formatter)
                        .compareTo(reserveDate.format(formatter)) == 0
                        && reservations.getCustomer().getCustomerId().compareToIgnoreCase(searchAccID) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else if (searchReserveDate.length() != 0 && searchServeDate.length() != 0 && searchAccID.length() == 0) {
            LocalDate reserveDate = LocalDate.parse(searchReserveDate);
            LocalDate serveDate = LocalDate.parse(searchServeDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getReserveDate().format(formatter)
                        .compareTo(reserveDate.format(formatter)) == 0
                        && reservations.getServeDate().format(formatter)
                                .compareTo(serveDate.format(formatter)) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        } else {
            LocalDate reserveDate = LocalDate.parse(searchReserveDate);
            LocalDate serveDate = LocalDate.parse(searchServeDate);
            int i = 0;
            for (Reservation reservations : reservationList) {
                if (reservations.getReserveDate().format(formatter)
                        .compareTo(reserveDate.format(formatter)) == 0
                        && reservations.getServeDate().format(formatter)
                                .compareTo(serveDate.format(formatter)) == 0
                        && reservations.getCustomer().getCustomerId().compareToIgnoreCase(searchAccID) == 0) {
                    i++;
                    System.out.println(String.format("%-3d %s", i, reservations.toString()));
                    searchFlag = true;
                }
            }
        }

        if (searchFlag == false) {
            System.out.println("No reservation record found!!");
        }
        pressEnterToContinue(scanner);
    }

    public static String viewCart(Reservation reservation) {
        String str = "";
        String appertizeStr = "";
        String mainStr = "";
        String beverageStr = "";
        String dessertStr = "";
        str += "\nItems In Cart\n";
        str += "================\n";
        str += String.format("%-3s %-20s %-10s\n", "No", "Dish Name",
                "Quantity");
        // sort by food type
        for (int i = 0; i < reservation.getFoodInCart().getNumberOfEntries(); i++) {
            if (reservation.getFoodInCart().getEntry(i).getFood().getMenuItemCategory()
                    .compareToIgnoreCase("Appertizer") == 0) {
                appertizeStr += String.format("%-3d %-30s\n",
                        (i + 1), reservation.getFoodInCart().getEntry(i).toString());
            } else if (reservation.getFoodInCart().getEntry(i).getFood().getMenuItemCategory()
                    .compareToIgnoreCase("Main Course") == 0) {
                mainStr += String.format("%-3d %-30s\n", (i + 1), reservation.getFoodInCart().getEntry(i).toString());
            } else if (reservation.getFoodInCart().getEntry(i).getFood().getMenuItemCategory()
                    .compareToIgnoreCase("Beverage") == 0) {
                beverageStr += String.format("%-3d %-30s\n",
                        (i + 1), reservation.getFoodInCart().getEntry(i).toString());
            } else {
                dessertStr += String.format("%-3d %-30s\n",
                        (i + 1), reservation.getFoodInCart().getEntry(i).toString());
            }
        }
        str += "\nAppertizer\n" + "-----------------\n" + appertizeStr + "\nMain Course\n" + "-----------------\n"
                + mainStr
                + "\nBeverage\n" + "-----------------\n" + beverageStr + "\nDessert\n" + "-----------------\n"
                + dessertStr;
        str += String.format("Total Price: %.2f\n", reservation.getChoosenPackage().getPackagePrice());
        return str;
    }

    private static ListInterface<Reservation> sortReservation(ListInterface<Reservation> reservationList,
            int displayChoice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (displayChoice == 1) {
            // sort by reserve date
            // insertion sort
            int indexOfSmallest = 0;
            for (int i = 0; i < reservationList.getNumberOfEntries() - 1; i++) {
                for (int j = i; j < reservationList.getNumberOfEntries(); j++) {
                    if (reservationList.getEntry(j).getReserveDate().format(formatter)
                            .compareTo(
                                    reservationList.getEntry(indexOfSmallest).getReserveDate().format(formatter)) < 0) {
                        indexOfSmallest = j;
                    }
                }
                // swap
                Reservation temp = reservationList.getEntry(i);
                reservationList.replace(i, reservationList.getEntry(indexOfSmallest));
                reservationList.replace(indexOfSmallest, temp);
            }
        } else {
            // sort by serve date
            int indexOfSmallest = 0;
            for (int i = 0; i < reservationList.getNumberOfEntries() - 1; i++) {
                for (int j = i; j < reservationList.getNumberOfEntries(); j++) {
                    if (reservationList.getEntry(j).getServeDate()
                            .compareTo(
                                    reservationList.getEntry(indexOfSmallest).getServeDate()) < 0) {

                        indexOfSmallest = j;
                    }
                }
                // swap
                Reservation temp = reservationList.getEntry(i);
                reservationList.replace(i, reservationList.getEntry(indexOfSmallest));
                reservationList.replace(indexOfSmallest, temp);
            }
        }
        return reservationList;
    }

    private static String printReservationList(ListInterface<Reservation> reservationList) {
        String str = "";
        str += String.format("%-3s %-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", "No", "ReservationID",
                "AccountID",
                "ContactNo",
                "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus");

        int i = 0;
        for (Reservation reserve : reservationList) {
            i++;
            str += String.format("%-3s %-115s", i, reserve.toString());
        }
        str += ("Total Number of Reservation: " + reservationList.getNumberOfEntries());
        return str;

    }

    private static int getLastReservationID(ListInterface<Reservation> reservationList) {
        String lastReservationID = reservationList.getEntry(reservationList.getNumberOfEntries() - 1)
                .getReservationID();
        lastReservationID = lastReservationID.substring(1, 6);
        return Integer.parseInt(lastReservationID);
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press <Enter> to continue.");
        scanner.nextLine();
    }
}
