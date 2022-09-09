
// ChiewHongKuang
import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    private String customerId;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;

    public Customer(String customerId, String firstName, String lastName, String gender, LocalDate dob) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String customerInfo() {
        return "Customer ID: " + customerId + "\nName: " + getFullName() + "\nGender: " + gender + "\nDate of Birth: " + dob + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        return customerId.equals(((Customer) o).getCustomerId());
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(customerId.substring(1));
    }
}
