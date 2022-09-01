import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        FileHandler<ListInterface<Reservation>> reservationFile = new FileHandler<ListInterface<Reservation>>(
                "Reservations.dat");
        // ListInterface<Reservation> reservationList = (ListInterface<Reservation>)
        // reservationFile.read();

    }

    public String reservationHistory(ListInterface<Reservation> reservation) {
        String str = "";
        for (Reservation tempReservation : reservation) {
            tempReservation.toString();
        }
        return str;
    }

    public void ReservationModule(Account account) {
        // read file when starting
        // reservation
        FileHandler<ListInterface<Reservation>> reservationFile = new FileHandler<ListInterface<Reservation>>(
                "Reservations.dat");
        ListInterface<Reservation> reservationList = (ListInterface<Reservation>) reservationFile.read();
        // menu
        FileHandler<Menu> menuFile = new FileHandler<Menu>("Menu.dat");
        Menu menu = (Menu) menuFile.read();

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
        Character cartRemove = 'N';
        int cartRemovePosition;

        // remove
        int removeChoice = -1;

        LocalDateTime serveTime = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Reservation Module");
            System.out.println("========================");
            System.out.println("1. Make Reservation for current account");
            System.out.println("2. View all reservation history");
            System.out.println("3. Remove Reservation");
            System.out.println("4. Exit");
            do {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > 4);

            switch (choice) {
                case 1:
                    Reservation reservation = new Reservation(account);
                    do {
                        char confirmation = 'N';

                        System.out.println("Reservation Process");
                        System.out.println("========================");
                        System.out.println("1. Enter reservation details");
                        System.out.println("2. Enter food into cart");
                        System.out.println("3. Remove food from cart");
                        System.out.println("4. CheckOut Cart");
                        do {
                            System.out.print("Enter your choice: ");
                            reservationProcessChoice = scanner.nextInt();
                            if (reservationProcessChoice < 1 || reservationProcessChoice > 4) {
                                System.out.println("Invalid Choice!");
                            }
                        } while (reservationProcessChoice < 1 || reservationProcessChoice > 4);

                        switch (reservationProcessChoice) {
                            case 1:
                                do {
                                    scanner.next();
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
                            case 2:
                                do {
                                    // enter menu
                                    do {
                                        MenuDriver.displayCategoryChoice();
                                        categoryChoice = scanner.nextInt();
                                        if (categoryChoice < 1 || categoryChoice > 4) {
                                            System.out.println("Invalid Choice!");
                                        } else {
                                            do {
                                                System.out
                                                        .println(menu.getMenuCategory().getElementAtPos(categoryChoice)
                                                                .toString());
                                                System.out.print("Enter your food choice:");
                                                foodChoice = scanner.nextInt();
                                                if (foodChoice > menu.getMenuCategory().getElementAtPos(categoryChoice)
                                                        .getCounter()
                                                        || foodChoice < 1) {
                                                    System.out.println("invalid Choice!");
                                                }
                                            } while (foodChoice > menu.getMenuCategory().getElementAtPos(categoryChoice)
                                                    .getCounter()
                                                    || foodChoice < 1);
                                        }
                                    } while (categoryChoice < 1 || categoryChoice > 4);

                                    // enter quantity
                                    System.out.print("Please Enter Quantity:");
                                    foodQuantity = scanner.nextInt();

                                    // store into cart
                                    MenuItem temp = menu.getMenuCategory().getElementAtPos(categoryChoice)
                                            .getMenuItems()
                                            .getElementAtPos(foodChoice);
                                    reservation.getCart().addToCart(temp, foodQuantity);
                                    // display cart
                                    System.out.println(reservation.getCart().toString());

                                    do {
                                        System.out.println("Continue Add Cart? (Y/N) ");
                                        cartContinue = scanner.next().charAt(0);
                                        cartContinue=Character.toUpperCase(cartContinue);
                                    } while (cartContinue != 'Y' && cartContinue != 'N');

                                } while (cartContinue == 'Y' || cartContinue == 'y');

                            case 3:
                                // remove cart
                                System.out.println("Do you want to remove cart?(Y/N) ");
                                cartRemove = scanner.next().charAt(0);
                                if (cartRemove == 'Y' || cartRemove == 'y') {
                                    do {
                                        System.out.println(reservation.getCart().toString());
                                        System.out.print("Enter the number you wish to remove: ");
                                        cartRemovePosition = scanner.nextInt();
                                        if (cartRemovePosition > reservation.getCart().getFoodsInCart()
                                                .getNumberOfEntries()
                                                || cartRemovePosition <= 0) {

                                            System.out.println("Invalid Input!");
                                        } else {
                                            reservation.getCart().getFoodsInCart().remove(cartRemovePosition - 1);
                                            System.out.println("Removed Successfully");
                                            System.out.println("Press <Enter> to continue.");
                                            scanner.next();
                                        }
                                    } while (cartRemovePosition > reservation.getCart().getFoodsInCart()
                                            .getNumberOfEntries()
                                            || cartRemovePosition <= 0);
                                }
                            case 4:
                                // confirm (print bill)
                                System.out.println(reservation.generateBill());
                                System.out.println("Press <Enter> to continue.");
                                scanner.next();

                                scanner.close();
                                // if confirmed, add into array and write into file, else discard
                                if (confirmation == 'Y') {
                                    reservationList.add(reservation);
                                }

                        }
                    } while (reservationProcessChoice != 4);
                case 2:
                    System.out.println(
                            String.format("%10s %10s %15s %15s %15s %20s %10s\n", "reservationID", "AccountID",
                                    "ContactNo",
                                    "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                    // for each loop
                    for (Reservation reserve : reservationList) {
                        reserve.toString();
                    }

                    System.out.println("Total Number of Reservation: " + reservationList.getNumberOfEntries());

                case 3:
                    System.out.println(
                            String.format("%10s %10s %15s %15s %15s %20s %10s\n", "reservationID", "AccountID",
                                    "ContactNo",
                                    "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                    // for each loop
                    for (Reservation reserve : reservationList) {
                        reserve.toString();
                    }
                    System.out.println("Please Enter your choice to remove (-1 to exit):");
                    removeChoice = scanner.nextInt();

                    if (removeChoice > reservationList.getNumberOfEntries() || removeChoice < 1) {
                        System.out.println("Invalid Choice!");
                    } else if (removeChoice == -1) {
                        System.out.println("Exited!");
                    } else {
                        reservationList.remove(removeChoice - 1);
                    }
            }
        } while (choice != 4);

        //input to file when module end
        reservationFile.write(reservationList);
    }
}
