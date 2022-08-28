
/**
 *
 * @author Thong So Xue
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dish implements Serializable {
    // Class attributes
    private static int nextId = 0001;
    private int id;                     // ID
    private MenuItem food;                  // The food type of this dish
    private int serveQuantity;          // Serve quantity of this dish
    private LocalDateTime orderTime;    // The time that this dish was added to queue
    private LocalDateTime serveTime;    // The time that this dish is served out to the buffet
    private boolean foodFinished;       // To state if the food on the dish is finished or not
    private String status;              // notStarted / inQueue / served
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    // Constructor
    public Dish(MenuItem food) {
        this.food = food;
        this.id = nextId;
        nextId++;
        this.status = "notStarted";
    }
    
    
    // Methods
    public void queueDish() {
        status = "inQueue";
        orderTime = LocalDateTime.now();
    }
    
    public void serveDish() {
        serveTime = LocalDateTime.now();
        status = "served";
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public MenuItem getFood() {
        return food;
    }
    
    public int getServeQuantity() {
        return serveQuantity;
    }
    
    public LocalDateTime getServeTime() {
        return serveTime;
    }
    
    public boolean isServed() {
        return status.equals("served") || isCompleted();
    }
    
    public boolean isCompleted() {
        return status == "completed";
    }
    
    public boolean isFoodFinished() {
        return foodFinished;
    }
    
    public String getStatus() {
        return status;
    }
    
    
    @Override
    public String toString() {
        String orderTimeStr = "-";
        String serveTimeStr = "-";
        if (orderTime != null)
            orderTimeStr = orderTime.format(timeFormat);
        if (serveTime != null)
            serveTimeStr = serveTime.format(timeFormat);
        
        String foodFinishedStr = "Finished";
        if (!foodFinished)
            foodFinishedStr = "Not Finished";
        
        return " " + String.format("%04d", id) + "\t| " + 
                String.format(" %-20s ", food) + "\t|" + 
                String.format(" %-8s ", serveQuantity) + "|" + 
                String.format(" %-19s ", orderTimeStr) + "|" + 
                String.format(" %-19s ", serveTimeStr) + "|" + 
                String.format(" %-13s ", foodFinishedStr) + "|" + 
                String.format(" %-10s ", status) + "|";
    }
}
