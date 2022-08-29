
// ChiewHongKuang
import java.io.Serializable;
import java.time.LocalDate;

public class Account implements Serializable {

    private String accountID;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;
    private String accountType;
    private boolean isLogin;
    private LinkedList<Reservation> reservationList;
    private static int noOfCustomer = 0;
    private static int noOfAdmin = 0;
    private static int iDCount = 0;

    public Account(String accountID, String password, String firstName, String lastName, String gender, LocalDate dob, String accountType) {
        this.accountID = accountID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.accountType = accountType;
        isLogin = false;
        reservationList = null;
        if (this.accountType.matches("Admin")) {
            noOfAdmin++;
        } else {
            noOfCustomer++;
        }
        iDCount++;
    }

    public Account(String accountID, String password, String firstName, String lastName, String gender, LocalDate dob, String accountType, boolean isLogin, LinkedList<Reservation> reservationList) {
        this.accountID = accountID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.accountType = accountType;
        this.isLogin = isLogin;
        this.reservationList = reservationList;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAccountType() {
        return accountType;
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public LinkedList<Reservation> getReservationList() {
        return reservationList;
    }

    public static int getNoOfAdmin() {
        return noOfAdmin;
    }

    public static void setNoOfAdmin(int newNoOfAdmin) {
        noOfAdmin = newNoOfAdmin;
    }

    public static int getNoOfCustomer() {
        return noOfCustomer;
    }

    public static void setNoOfCustomer(int newNoOfCustomer) {
        noOfCustomer = newNoOfCustomer;
    }

    public static int getIDCount() {
        return iDCount;
    }

    public void logIn() {
        isLogin = true;
    }

    public void logOut() {
        isLogin = false;
    }

    public boolean isAdmin() {
        return accountType.matches("Admin");
    }

    public boolean validatePassword(String inputPassword) {
        return inputPassword.matches(password);
    }

    @Override
    public String toString() {
        String accountDetails = "Account ID: " + accountID + "\nName: " + getFullName() + "\nGender: " + gender + "\nDate of Birth: " + dob + "\nAccount Type: " + accountType + "\nReservation List: ";
        if (reservationList != null) {
            for (int i = 0; i < reservationList.getNumberOfEntries(); i++) {
                if (reservationList.getEntry(i).getAccount().getAccountID().matches(accountID)) {
                    accountDetails += reservationList.getEntry(i).getReservationID() + "\n";
                }
            }
        } else {
            accountDetails += "No Reservation";
        }
        return accountDetails;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(accountID.substring(1));
    }
}
