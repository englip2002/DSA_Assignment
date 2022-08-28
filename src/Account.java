// ChiewHongKuang

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;

public class Account {

    private String accountID;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;
    private String accountType;
    private boolean isLogin;
    private LocalDateTime loginTime;
    private LinkedList<Reservation> reservationList;
    private static int noOfCustomer = 0;
    private static int noOfAdmin = 0;

    public Account(String accountID, String password, String firstName, String lastName, String gender, LocalDate dob, String accountType) {
        this.accountID = accountID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.accountType = accountType;
        isLogin = false;
        loginTime = null;
        reservationList = null;
        if (this.accountType.matches("Admin")) {
            noOfAdmin++;
        } else {
            noOfCustomer++;
        }
    }

    public String getAccountID() {
        return accountID;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void logIn() {
        isLogin = true;
        loginTime = LocalDateTime.now();
    }

    public long logOut() {
        return Duration.between(loginTime, LocalDateTime.now()).toMinutes();
    }

    public void resetPassword(String newPassword) {
        password = newPassword;
    }

    public boolean validateLogin(String inputPassword) {
        return inputPassword.matches(password);
    }

    public String toAccountDetails() {
        String accountDetails = "Account ID: " + accountID + "\nName: " + getFullName() + "\nGender: " + gender + "\nDate of Birth: " + dob + "\nAccount Type: " + accountType + "\nReservation List: ";
        if (reservationList != null) {
/*            for (int i = 0; i < reservationList.getNumberOfEntries(); i++) {
                if (reservationList.getEntry(i).getAccount().getAccountID().matches(accountID)) {
                    accountDetails += reservationList.getEntry(i).getReservationID() + "\n";
                }
            }*/
        } else {
            accountDetails += "No Reservation";
        }
        return accountDetails;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(accountID.substring(1));
    }

}
