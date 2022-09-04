import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        FileHandler<ListInterface<Reservation>> reservationFile = new FileHandler<ListInterface<Reservation>>(
                "Reservations.dat");

        Account account = new Account("A0001", "1234", "Tan", "EngLip", "Male", LocalDate.of(2002, 11, 22), "Customer");

        ReservationModule(account);
    }

    public static void ReservationModule(Account account) {
        // read file when starting
        // reservation
        Reservation reservation = new Reservation(account);
        FileHandler reservationFile = new FileHandler("Reservations.dat");
        ListInterface<Reservation> reservationList = (ListInterface) reservationFile.read();
        // System.out.println(reservationList.getEntry(0).toString());

        // menu
        FileHandler<Menu> menuFile = new FileHandler<Menu>("Menu.dat");
        // Menu menu = (Menu) menuFile.read();

        // temporary menu
        Menu menu = new Menu();
        Category appertizer = new Category('A');
        menu.addCategory(appertizer);
        MenuItem cornSoup = new MenuItem('A', "Corn Soup", 2.3, "Good");
        menu.addMenuItem(cornSoup);

        int choice = 0;
        boolean dateValidity;

        // reservation input
        String contactNo, serveLocation;
        String serveDateString, serveTimeString;

        // reservation process
        int reservationProcessChoice;

        // menu input
        int categoryChoice = -1;
        int foodChoice = -1;
        int foodQuantity;
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
                    do {
                        System.out.println("\nReservation Process");
                        System.out.println("========================");
                        System.out.println("1. Enter reservation details");
                        System.out.println("2. Enter food into cart");
                        System.out.println("3. Remove food from cart");
                        System.out.println("4. View Cart");
                        System.out.println("5. CheckOut");
                        do {
                            System.out.print("Enter your choice: ");
                            reservationProcessChoice = scanner.nextInt();
                            if (reservationProcessChoice < 1 || reservationProcessChoice > 5) {
                                System.out.println("Invalid Choice!");
                            }
                        } while (reservationProcessChoice < 1 || reservationProcessChoice > 5);

                        switch (reservationProcessChoice) {
                            case 1:
                                scanner.nextLine();
                                do {
                                    System.out.print("Please enter your contact No (XXX-XXXXXXX): ");
                                    contactNo = scanner.nextLine();
                                } while (contactNo.length() != 11);

                                System.out.print("Please enter your serve location: ");
                                serveLocation = scanner.nextLine();

                                do {
                                    dateValidity = true;
                                    try {
                                        System.out.print("Enter reserve date (YYYY-MM-DD): ");
                                        serveDateString = scanner.next();

                                        System.out.print("Enter reserve time (HH:MM): ");
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

                                // input data into reservation
                                reservation.reserveDetails(contactNo, serveLocation, serveTime);
                                break;
                            case 2:
                                do {
                                    // enter menu
                                    do {
                                        MenuDriver.displayCategoryChoice();
                                        categoryChoice = scanner.nextInt();
                                        if (categoryChoice < 1
                                                || categoryChoice > menu.getMenuCategory().getNumberOfEntries()) {
                                            System.out.println("Invalid Choice!");

                                        } else {
                                            // print foods from category and enter food choice
                                            do {
                                                System.out.println(String.format("%-3s %-10s %-20s %-30s %-10s", "No",
                                                        "Dish ID", "Dish Name", "Dish Description", "Dish Price(RM)"));
                                                System.out.println(menu.getMenuCategory()
                                                        .getElementAtPos(categoryChoice - 1).toString());
                                                System.out.print("Enter your food choice:");
                                                foodChoice = scanner.nextInt();
                                                if (foodChoice > menu.getMenuCategory()
                                                        .getElementAtPos(categoryChoice - 1)
                                                        .getCounter()
                                                        || foodChoice < 1) {
                                                    System.out.println("invalid Choice!");
                                                }
                                            } while (foodChoice > menu.getMenuCategory()
                                                    .getElementAtPos(categoryChoice - 1).getCounter()
                                                    || foodChoice < 1);
                                        }
                                    } while (categoryChoice < 1
                                            || categoryChoice > menu.getMenuCategory().getNumberOfEntries());

                                    // enter quantity
                                    System.out.print("Please Enter Quantity:");
                                    foodQuantity = scanner.nextInt();

                                    // store into cart
                                    MenuItem temp = menu.getMenuCategory().getElementAtPos(categoryChoice - 1)
                                            .getMenuItems()
                                            .getElementAtPos(foodChoice - 1);
                                    reservation.getCart().addToCart(temp, foodQuantity);

                                    // display cart
                                    System.out.println("\nItems In Cart");
                                    System.out.println("================");
                                    System.out.println(String.format("%-5s %-10s %-10s %-10s %-10s", "No", "Dish Name",
                                            "Dish Price", "Quantity", "Subtotal(RM)"));
                                    System.out.println(reservation.getCart().toString());

                                    do {
                                        System.out.print("Continue Add Cart? (Y/N) ");
                                        cartContinue = scanner.next().charAt(0);
                                        cartContinue = Character.toUpperCase(cartContinue);
                                    } while (cartContinue != 'Y' && cartContinue != 'N');

                                } while (cartContinue == 'Y' || cartContinue == 'y');
                                break;
                            case 3:
                                // remove cart
                                do {
                                    System.out.println(String.format("%-5s %-10s %-10s %-10s %-10s", "No", "Dish Name",
                                            "Dish Price", "Quantity", "Subtotal(RM)"));
                                    System.out.println(reservation.getCart().toString());
                                    System.out.print("Enter the number you wish to remove: ");
                                    cartRemovePosition = scanner.nextInt();
                                    scanner.nextLine();

                                    if (cartRemovePosition > reservation.getCart().getItemCount()
                                            || cartRemovePosition <= 0) {

                                        System.out.println("Invalid Input!");
                                    } else {
                                        reservation.getCart().getFoodsInCart().remove(cartRemovePosition - 1);
                                        System.out.println("Removed Successfully");
                                        System.out.println("Press <Enter> to continue.");
                                        scanner.nextLine();
                                        scanner.nextLine();
                                    }
                                } while (cartRemovePosition > reservation.getCart().getItemCount()
                                        || cartRemovePosition <= 0);
                                break;
                            case 4:
                                // view cart
                                System.out.println(String.format("%-5s %-10s %-10s %-10s %-10s", "No", "Dish Name",
                                        "Dish Price", "Quantity", "Subtotal(RM)"));
                                System.out.print(reservation.getCart().toString());
                                System.out.println(String.format("%43.2f", reservation.getCart().calculateTotal()));
                                System.out.println("Press <Enter> to continue.");
                                scanner.nextLine();
                                scanner.nextLine();
                                break;

                            case 5:
                                // checkout
                                // confirm (print bill)
                                System.out.println("Bills");
                                System.out.println("===========");

                                System.out.println(reservation.generateBill());
                                System.out.println("Press <Enter> to continue.");
                                scanner.nextLine();
                                scanner.nextLine();

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
                                break;
                        }
                    } while (reservationProcessChoice != 5);
                    break;
                case 2:
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
                            System.out.print(String.format("%-3s %s", i, reserve.toString()));
                        }
                        System.out.println("Total Number of Reservation: " + reservationList.getNumberOfEntries());
                        System.out.println("Press <Enter> to continue.");
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                    break;
                case 3:
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

                        System.out.print("Please Enter your choice to remove (-1 to exit):");
                        removeChoice = scanner.nextInt();

                        if (removeChoice > reservationList.getNumberOfEntries() || removeChoice < 1) {
                            System.out.println("Invalid Choice!");
                        } else if (removeChoice == -1) {
                            System.out.println("Exited!");
                            System.out.println("Press <Enter> to continue.");
                            scanner.nextLine();
                        } else {
                            reservationList.remove(removeChoice - 1);
                            reservationFile.write(reservationList);
                        }
                    }
                    break;
                case 4:
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
                            if (reservations.getReserveDate().format(formatter).compareTo(searchTime.format(formatter)) == 0) {
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
                                System.out.println(String.format("%-3d %s", i, reservations.toString()));
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
                            if (reservations.getAccount().getFullName().compareToIgnoreCase(searchName)==0) {
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
}
