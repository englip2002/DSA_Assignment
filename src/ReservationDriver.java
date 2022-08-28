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

    public Reservation ReservationProcess(Account account) {
        boolean dateValidity;
        Reservation reservation;
        String contactNo, serveLocation;
        String serveDateString, serveTimeString;

        int serveDay, serveMonth, serveYear, serveMin, serveHour;
        LocalDateTime serveTime = null;
        Scanner scanner = new Scanner(System.in);

        if (account != null) {
            reservation = new Reservation(account);
            System.out.print("Please enter your contact No: ");
            contactNo = scanner.nextLine();
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
                    serveTime = LocalDateTime.of(LocalDate.parse(serveDateString), LocalTime.parse(serveTimeString));

                } catch (DateTimeParseException e) {
                    dateValidity = false;
                }

                // print error message

                if (dateValidity == false)
                    System.out.println("Invalid Date Format! Please Re-enter.\n");

            } while (dateValidity == false);

            //input data into reservation
            reservation.reserveDetails(contactNo, serveLocation, serveTime);

            //print menu

            //let user to choose

            //store into cart

            //confirm (print bill)

            scanner.close();
            //return Reservation
            return reservation;
        } else {
            scanner.close();
            return null;
        }
    }
}
