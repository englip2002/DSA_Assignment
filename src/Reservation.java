import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation implements Serializable {
    private String reservationID;
    private Account account;
    private LocalDateTime reserveTime;
    private LocalDateTime serveTime;
    private String contactNo;
    private String serveLocation;
    private String reservationStatus;
    private Cart cart;
    private static int IDcounter = 1;
    private static ListInterface<Reservation> ls = new LinkedList<Reservation>();

    public Reservation(Account account) {
        this.account = account;
        this.reservationStatus = null;
        cart = new Cart();
        reservationID = generareReservationID();
    }

    public void checkOut() {
        reserveTime = LocalDateTime.now();
        reservationStatus = "Pending Payment";
    }

    public String displayCart() {
        String str = "";
        for (int i = 0; i < cart.getItemCount(); i++) {
            str += String.format("%3d %s\n", (i + 1), cart.getFoodsInCart().getEntry(i).toString());
        }
        return str;
    }

    public void reserveDetails(String contactNo, String serveLocation, LocalDateTime serveTime) {
        this.contactNo = contactNo;
        this.serveLocation = serveLocation;
        this.serveTime = serveTime;
    }

    public void changeReservationStatus(String status) {
        this.reservationStatus = status;
    }

    public String generateBill() {
        // format the bill
        String str = "";
        str += account.toString() + "\n";
        str += String.format("%-5s %-10s %-10s %-10s %-10s\n", "No", "Dish Name",
                "Dish Price", "Quantity", "Subtotal(RM)");
        str += cart.toString();
        str += String.format("Total: %36.2f", cart.calculateTotal());
        return str;
    }

    public String toString() {
        // the format for reservation history
        // reservationID, accountID, contactNo, reserveTime, serveTime, serveLocation,
        // reservationStatus
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%-15s %-15s %-15s %-20s %-20s %-20s %-10s\n", reservationID, account.getAccountID(),
                contactNo,
                reserveTime.format(formatter), serveTime.format(formatter), serveLocation, reservationStatus);

    }

    // getter
    public Cart getCart() {
        return cart;
    }

    public Account getAccount() {
        return account;
    }

    public String getReservationID() {
        return reservationID;
    }

    public LocalDateTime getReserveDate() {
        return reserveTime;
    }

    public LocalDateTime getServeDate() {
        return serveTime;
    }

    private String generareReservationID() {
        return String.format("R%05d", IDcounter);
    }

    // setter
    public void setReservationStatus(String status) {
        this.reservationStatus = status;
    }

    // new
    public static ListInterface<Reservation> getReservationList() {
        if (ls != null) {
            return ls;
        } else {
            return null;
        }
    }

    public static void setReservationList(ListInterface<Reservation> list) {
        ls = list;
    }

    public void addReservation() {
        ls.add(this);
    }

    public void removeReservation() {
        for (int i = 0; i < ls.getNumberOfEntries(); i++) {
            if (reservationID.compareTo(ls.getEntry(i).getReservationID()) == 0) {
                ls.remove(i);
            }
        }
    }

}
