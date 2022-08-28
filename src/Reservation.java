import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private String reservationID;
    private Account account;
    private LocalDateTime reserveTime;
    private LocalDateTime serveTime;
    private String contactNo;
    private String serveLocation;
    private String reservationStatus;
    private Cart cart;
    private static int reservationCount = 0;
    private static int IDcounter = 0;

    public Reservation(Account account) {
        this.account = account;
        this.reservationStatus = null;
        cart = new Cart();
        reservationCount++;
        reservationID = generareReservationID();
    }

    public void checkOut() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        reserveTime = LocalDateTime.now();
        reserveTime.format(formatter);

        reservationStatus = "Pending Payment";

        // append the data to txt file
    }

    public Cart sortCart() {
        Cart sortedCart = new Cart();
        // get category then sort by using various array then append
        for (int i = 0; i < cart.getItemCount(); i++) {
            cart.getFoods().getEntry(i);
        }
        return sortedCart;
    }

    public String displayCart() {
        String temp = new String();
        for (int i = 0; i < cart.getItemCount(); i++) {
            temp += cart.getFoods().getEntry(i).toString();
        }
        return temp;
    }

    public void reserveDetails(String contactNo, String serveLocation, LocalDateTime serveTime) {
        this.contactNo = contactNo;
        this.serveLocation = serveLocation;

        serveTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.serveTime = serveTime;
    }

    public boolean cancelReservation() {
        reservationCount--;
        return true;
    }

    public void changeReservationStatus(String status) {
        this.reservationStatus = status;
    }

    public String generateBill() {
        // format the bill
        String str = "";
        str += account.toString() + "\n";
        str += cart.toString();
        str += cart.calculateTotal();
        return str;
    }

    public String toString() {
        // the format for reservation history
        // reservationID, accountID, contactNo, reserveTime, serveTime, serveLocation,
        // reservationStatus
        String.format("%10s %10s %15s %15s %15s %20s %10s\n", reservationID, account.getAccountID(), contactNo,
                reserveTime, serveTime, serveLocation, reservationStatus);
        return " ";
    }

    // getter
    public Cart getCart() {
        return cart;
    }

    public int getReservationCount(){
        return reservationCount;
    }

    private String generareReservationID() {
        return String.format("R%5d", IDcounter);
    }

    // read reservation

    // write reservation

}
