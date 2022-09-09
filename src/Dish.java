
/**
 *
 * @author Thong So Xue
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Dish implements Serializable {
    // Class attributes
    private static int nextId = 1;      // Next ID to be assigned to the next created Dish
    private String id;                  // Unique ID of this Dish
    private MenuItem food;              // The food type of this dish
    private LocalDateTime orderTime;    // The time that this dish was added to the watiing queue
    private LocalDateTime cookingTime;  // The time that this dish was added to a kitchen's cooking queue
    private String status;              // Waiting / Cooking / Served
    
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    // Constructor
    public Dish(MenuItem menuItem) {
        this.food = menuItem;
        this.id = "D" + String.format("%03d", nextId);
        nextId++;
        this.status = "Waiting";
        this.orderTime = LocalDateTime.now();
    }
    
    // Methods
    public void cookDish() {
        status = "Cooking";
        cookingTime = LocalDateTime.now();
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
        return orderTime;
    }
    
    public LocalDateTime getCookingTime() {
        return cookingTime;
    }
    
    public String getDurationSinceOrder() {
        Duration duration = Duration.between(orderTime, LocalDateTime.now());
        long secondsRemainder = duration.toSeconds() % 60;
        return duration.toMinutes() + " minutes " + secondsRemainder + " seconds";
    }
    
    
    public String getDurationSinceCooking() {
        if (inCookingQueue() || isServed()) {
            Duration duration = Duration.between(cookingTime, LocalDateTime.now());
            long secondsRemainder = duration.toSeconds() % 60;
            return duration.toMinutes() + " minutes " + secondsRemainder + " seconds";
        }
        return "-";
    }
    
    public boolean inCookingQueue() {
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
        if (orderTime != null)
            orderTimeStr = orderTime.format(timeFormat);
        if (cookingTime != null)
            cookingTimeStr = cookingTime.format(timeFormat);
        
        return " " + String.format("%4s", id) + "|" + 
                String.format(" %-20s ", food.getMenuItemName()) + "|" + 
                String.format(" %-19s ", orderTimeStr) + "|" + 
                String.format(" %-19s ", cookingTimeStr) + "|" + 
                String.format(" %-22s ", getDurationSinceOrder()) + "|" + 
                String.format(" %-22s ", getDurationSinceCooking()) + "|" + 
                String.format(" %-10s ", status) + "|";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return this.id.equals(((Dish) o).getId());
    }
    
    
}
