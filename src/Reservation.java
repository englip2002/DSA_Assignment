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
    private Package choosenPackage;
    private ListInterface<FoodInCart> foodInCart;
    private static int IDcounter = 1;

    public Reservation(Account account) {
        this.account = account;
        this.reservationStatus = null;
        foodInCart = new LinkedList<FoodInCart>();
        reservationID = generareReservationID();
    }

    public void checkOut() {
        reserveTime = LocalDateTime.now();
        reservationStatus = "Pending Payment";
    }

    public String displayCart() {
        String str = "";
        for (int i = 0; i < foodInCart.getNumberOfEntries(); i++) {
            str += String.format("%3d %s\n", (i + 1), foodInCart.getEntry(i).toString());
        }
        return str;
    }

    // public void sortCart(){
    //     //4 type of food
    //     FoodInCart firstAppertizer=null;
    //     FoodInCart firstMain=null;
    //     FoodInCart firstBeverage=null;
    //     FoodInCart firstDessert=null;
    //     for (FoodInCart food : foodInCart) {
    //         if(firstAppertizer==null&&food.getFood().getMenuItemCategory()=="Appertizer"){
    //             firstAppertizer=food;
    //         }
    //         else if(firstMain==null&&food.getFood().getMenuItemCategory()=="Main Course"){
                
    //         }
    //     }
    // }

    public void reserveDetails(String contactNo, String serveLocation, LocalDateTime serveTime) {
        this.contactNo = contactNo;
        this.serveLocation = serveLocation;
        this.serveTime = serveTime;
    }

    public String generateBill() {
        // format the bill
        String str = "";
        str += account.toString() + "\n";
        str += choosenPackage.getPackageName()+"\n";
        str += String.format("%-5s %-10s %-10s\n", "No", "Dish Name",
             "Quantity");
        for (int i = 0; i < foodInCart.getNumberOfEntries(); i++) {
            str += foodInCart.getEntry(i).toString();
        }
        str += String.format("Total: %36.2f", choosenPackage.getPackagePrice());
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
    public ListInterface<FoodInCart> getFoodInCart() {
        return foodInCart;
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

    public Package getChoosenPackage(){
        return choosenPackage;
    }
    private String generareReservationID() {
        return String.format("R%05d", IDcounter);
    }

    // setter
    public void setReservationStatus(String status) {
        this.reservationStatus = status;
    }

    public void setPackageChoice(Package packageChoice){
        this.choosenPackage=packageChoice;
    }

}
