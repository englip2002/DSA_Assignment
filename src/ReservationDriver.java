import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        ListInterface<Reservation> reservation = new LinkedList<Reservation>();

    }

    public String reservationHistory(ListInterface<Reservation> reservation) {
        String str = "";
        for (int i = 0; i < reservation.getNumberOfEntries(); i++) {
            str += reservation.getEntry(i).toString();
        }
        return str;
    }

    public void ReservationModule(Account account) {
        // read file
        FileHandler reservationFile = new FileHandler("Reservations.dat");
        ListInterface<Reservation> reservationList = (ListInterface) reservationFile.read();

        int choice = 0;
        boolean dateValidity;

        String contactNo, serveLocation;
        String serveDateString, serveTimeString;

        LocalDateTime serveTime = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Reservation Module");
        System.out.println("========================");
        System.out.println("1. Make Reservation for current account");
        System.out.println("2. View all reservation history");

        switch (choice) {
            case 1:
                Reservation reservation = new Reservation(account);

                // start input data
                if (account != null) {
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

                    // print menu

                    // let user to choose

                    // store into cart

                    // display cart

                    // modify cart

                    // confirm (print bill)

                    scanner.close();
                    // if confirmed, add into array and write into file, else discard
                    reservationList.add(reservation);
                    reservationFile.write(reservationList);

                } else {
                    scanner.close();
                }
            case 2:
                System.out.println(
                        String.format("%10s %10s %15s %15s %15s %20s %10s\n", "reservationID", "AccountID", "ContactNo",
                                "ReserveTime", "ServeTime", "ServeLocation", "ReservationStatus"));

                // for each loop
                for (Reservation reservation2 : reservationList) {
                    reservation2.toString();
                }

                // for loop
                // for (int i = 0; i < reservationList.getNumberOfEntries(); i++) {

                //     System.out.println(reservationList.getEntry(i).toString());
                // }

                System.out.println("Total Number of Reservation: " + reservationList.getNumberOfEntries());
        }
    }
}
