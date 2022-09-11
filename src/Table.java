
// ChiewHongKuang
import java.io.Serializable;
import java.time.LocalDate;

public class Table implements Serializable {

    private String tableId;
    private String packageServed;
    private int numberOfSeats;
    private LocalDate reservedDate;
    private MapInterface<String, Customer> tableCustomers;

    public Table(String tableId, String packageServed, int numberOfSeats, LocalDate reservedDate) {
        this.tableId = tableId;
        this.packageServed = packageServed;
        this.numberOfSeats = numberOfSeats;
        this.reservedDate = reservedDate;
        this.tableCustomers = new HashMap<>();
    }

    public Table(String tableId, String packageServed, int numberOfSeats, LocalDate reservedDate, MapInterface<String, Customer> tableCustomers) {
        this.tableId = tableId;
        this.packageServed = packageServed;
        this.numberOfSeats = numberOfSeats;
        this.reservedDate = reservedDate;
        this.tableCustomers = tableCustomers;
    }

    public String getTableId() {
        return tableId;
    }

    public String getPackageServed() {
        return packageServed;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public boolean isReserved() {
        return reservedDate != null;
    }

    public MapInterface<String, Customer> getTableCustomers() {
        return tableCustomers;
    }

    public void setTableCustomers(MapInterface<String, Customer> tableCustomers) {
        this.tableCustomers = tableCustomers;
    }

    public Customer addCustomer(Customer c) {
        return tableCustomers.put(c.getCustomerId(), c);
    }

    public Customer removeCustomer(Customer c) {
        return tableCustomers.remove(c.getCustomerId());
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
        return tableId.equals(((Table) o).getTableId());
    }

    public String tableInfo() {
        String tableDetails = "Table ID: " + tableId + "\nPackage Served: " + packageServed + "\nNumber of Seat: " + numberOfSeats + "\nReserved Date: " + reservedDate + "\nReserved: ";
        if (isReserved()) {
            tableDetails += "Yes";
        } else {
            tableDetails += "No";
        }
        tableDetails += "\nCustomer List:\n";
        if (tableCustomers.size() != 0) {
            Customer[] c = new Customer[tableCustomers.size()];
            System.arraycopy(tableCustomers.values(), 0, c, 0, tableCustomers.size());
            for (Customer each : c) {
                tableDetails += each.customerInfo() + "\n";
            }
        } else {
            tableDetails += "No Customer\n";
        }
        return tableDetails;
    }

    @Override
    public String toString() {
        return String.valueOf(numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(tableId.substring(1));
    }
}
