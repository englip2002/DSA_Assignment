
/**
 *
 * @author Thong So Xue
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Dish implements Serializable {
    // Class attributes
    private static int nextId = 1;      // Next ID to be assigned to the next created Dish
    private String id;                  // Unique ID of this Dish
    private MenuItem food;              // The food type of this dish
    private LocalDateTime orderDateTime;    // The time that this dish was added to the watiing queue
    private LocalDateTime cookingDateTime;  // The time that this dish was added to a kitchen's cooking queue
    private String status;              // Waiting / Cooking / Served
    
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    // Constructor
    public Dish(MenuItem menuItem) {
        this.food = menuItem;
        this.id = "D" + String.format("%03d", nextId);
        nextId++;
        this.status = "Waiting";
        this.orderDateTime = LocalDateTime.now();
    }
    
    // Methods
    public void cookDish() {
        status = "Cooking";
        cookingDateTime = LocalDateTime.now();
    }
    
    public void serveDish() {
        status = "Served";
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public MenuItem getFood() {
        return food;
    }
    
    
    public LocalDateTime getOrderTime() {
        return orderDateTime;
    }
    
    public LocalDateTime getCookingTime() {
        return cookingDateTime;
    }
    
    public String getDurationSinceOrder() {
        Duration duration = Duration.between(orderDateTime, LocalDateTime.now());
        long minutesRemainder = duration.toMinutes() % 60;
        long secondsRemainder = duration.toSeconds() % 60;
        return duration.toHours() + " hrs " +  minutesRemainder + " min " + secondsRemainder + " sec";
    }
    
    
    public String getDurationSinceCooking() {
        if (isCooking() || isServed()) {
            Duration duration = Duration.between(cookingDateTime, LocalDateTime.now());
            long minutesRemainder = duration.toMinutes() % 60;
            long secondsRemainder = duration.toSeconds() % 60;
            return duration.toHours() + " hrs " +  minutesRemainder + " min " + secondsRemainder + " sec";
        }
        return "-";
    }
    
    public boolean isCooking() {
        return status.equals("Cooking");
    }
    
    public boolean isServed() {
        return status.equals("Served");
    }
    
    public String getStatus() {
        return status;
    }
    
    @Override
    public String toString() {
        String orderTimeStr = "-";
        String cookingTimeStr = "-";
        if (orderDateTime != null)
            orderTimeStr = orderDateTime.format(timeFormat);
        if (cookingDateTime != null)
            cookingTimeStr = cookingDateTime.format(timeFormat);
        
        return " " + String.format("%4s", id) + "|" + 
                String.format(" %-20s ", food.getMenuItemName()) + "|" + 
                String.format(" %-19s ", orderTimeStr) + "|" + 
                String.format(" %-22s ", getDurationSinceOrder()) + "|" + 
                String.format(" %-19s ", cookingTimeStr) + "|" + 
                String.format(" %-22s ", getDurationSinceCooking()) + "|" + 
                String.format(" %-10s ", status) + "|";
    }
    
    
    public boolean orderDateIsBetween(LocalDate startDate, LocalDate endDate) {
        LocalDate orderDate = orderDateTime.toLocalDate();
        return (orderDate.isAfter(startDate) || orderDate.isEqual(startDate)) && 
                (orderDate.isBefore(endDate) || orderDate.isEqual(endDate));
    }
    
    public boolean cookingDateIsBetween(LocalDate startDate, LocalDate endDate) {
        if (!isCooking() && !isServed())
            return true;
        LocalDate cookingDate = cookingDateTime.toLocalDate();
        return (cookingDate.isAfter(startDate) || cookingDate.isEqual(startDate)) && 
                (cookingDate.isBefore(endDate) || cookingDate.isEqual(endDate));
    }
    
    public boolean orderTimeIsBetween(LocalTime startTime, LocalTime endTime) {
        LocalTime orderTime = orderDateTime.toLocalTime();
        return (orderTime.isAfter(startTime) || orderTime.equals(startTime)) && 
                (orderTime.isBefore(endTime) || orderTime.equals(endTime));
    }
    
    public boolean cookingTimeIsBetween(LocalTime startTime, LocalTime endTime) {
        if (!isCooking() && !isServed())
            return true;
        LocalTime cookingTime = cookingDateTime.toLocalTime();
        return (cookingTime.isAfter(startTime) || cookingTime.equals(startTime)) && 
                (cookingTime.isBefore(endTime) || cookingTime.equals(endTime));
    }
    
    public static void setNextId(int id) {
        Dish.nextId = id;
    }
    
    public static int getNextId() {
        return Dish.nextId;
    }
}
