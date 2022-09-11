import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

/**
 * An entity class that represents a dish order or request in the Catering
 * Services System. The dish will contain various information including its ID,
 * the food it represents, the ordering date and time, the cooking date and
 * time, and a status word.
 * 
 * @author Thong So Xue
 */

public class Dish implements Serializable {
    /**
     * The ID to be assigned to the next created {@code Dish}.
     */
    private static int nextId = 1;

    /**
     * A unique ID assigned to every {@code Dish}.
     */
    private String id;

    /**
     * The food stored in this {@code Dish}.
     */
    private MenuItem food;

    /**
     * The date and time that this {@code Dish} was ordered and added to the waiting
     * queue.
     */
    private LocalDateTime orderDateTime;

    /**
     * The date and time that this {@code Dish} was added to a kitchen's cooking
     * queue to start cooking.
     */
    private LocalDateTime cookingDateTime;

    /**
     * Represents the status of this {@code Dish}. The possible statuses are:
     * "Waiting" - The dish is in the waiting queue
     * "Cooking" - The dish is in one of the kitchens' cooking queue
     * "Served" - The dish is served out to the customers, and is not in either
     * queues.
     */
    private String status;

    /**
     * A formatter used to standardize the date and time format of
     * {@code orderDateTime} and {@code cookingDateTime}.
     */
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Creates a new {@code Dish} with the given food / menuItem. The dish's ID is
     * automatically generated to avoid data redundancy. The dish's status will be
     * initialized to "Waiting", as all dishes created must be added to the waiting
     * queue.
     * 
     * @param menuItem the food to be stored into this {@code Dish}
     */
    public Dish(MenuItem menuItem) {
        this.food = menuItem;
        this.id = "D" + String.format("%03d", nextId);
        nextId++;
        this.status = "Waiting";
        this.orderDateTime = LocalDateTime.now();
    }

    /**
     * Change the status of this {@code Dish} to "Cooking". Also records the date
     * and time of cooking.
     */
    public void cookDish() {
        status = "Cooking";
        cookingDateTime = LocalDateTime.now();
    }

    /**
     * Change the status of this {@code Dish} to "Served".
     */
    public void serveDish() {
        status = "Served";
    }

    /**
     * Retrieves the ID of this {@code Dish}.
     * 
     * @return the ID of this {@code Dish}.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the food of this {@code Dish}.
     * 
     * @return the food of this {@code Dish}.
     */
    public MenuItem getFood() {
        return food;
    }

    /**
     * Retrieves the date and time of ordering or when the {@code Dish} was added to
     * the waiting queue.
     * 
     * @return date and time of ordering.
     */
    public LocalDateTime getOrderTime() {
        return orderDateTime;
    }

    /**
     * Retrieves the date and time of cooking or when the {@code Dish} was added to
     * one of the kitchens' cooking queue.
     * 
     * @return the date and time of cooking
     */
    public LocalDateTime getCookingTime() {
        return cookingDateTime;
    }

    /**
     * Retrieves a formatted string that represents the duration / time elapsed
     * since the time of ordering.
     * 
     * @return a string that represents the duration since the time of ordering.
     */
    public String getDurationSinceOrder() {
        Duration duration = Duration.between(orderDateTime, LocalDateTime.now());
        long minutesRemainder = duration.toMinutes() % 60;
        long secondsRemainder = duration.toSeconds() % 60;
        return duration.toHours() + " hrs " + minutesRemainder + " min " + secondsRemainder + " sec";
    }

    /**
     * Retrieves a formatted string that represents the duration / time elapsed
     * since the time of cooking.
     * 
     * @return a string that represents the duration since the time of cooking.
     */
    public String getDurationSinceCooking() {
        if (isCooking() || isServed()) {
            Duration duration = Duration.between(cookingDateTime, LocalDateTime.now());
            long minutesRemainder = duration.toMinutes() % 60;
            long secondsRemainder = duration.toSeconds() % 60;
            return duration.toHours() + " hrs " + minutesRemainder + " min " + secondsRemainder + " sec";
        }
        return "-";
    }

    /**
     * Checks if the status of the {@code Dish} is "Cooking".
     * 
     * @return true if the status of the {@code Dish} is "Cooking", false if not.
     */
    public boolean isCooking() {
        return status.equals("Cooking");
    }

    /**
     * Checks if the status of the {@code Dish} is "Served".
     * 
     * @return true if the status of the {@code Dish} is "Served", false if not.
     */
    public boolean isServed() {
        return status.equals("Served");
    }

    /**
     * Retrieves the status of the {@code Dish}.
     * 
     * @return the status of the {@code Dish}.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Retrieves a formatted string that contains all the {@code Dish}'s
     * information, in a table row format.
     * 
     * @return a string formatted as a table row that contains all the
     *         {@code Dish}'s information
     */
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

    /**
     * Checks if the date of order is between the two given dates.
     * 
     * @param startDate the starting date of the range of dates
     * @param endDate   the ending date of the range of dates
     * @return true if this dish's date of ordering is between {@code startDate} and
     *         {@code endDate}
     */
    public boolean orderDateIsBetween(LocalDate startDate, LocalDate endDate) {
        LocalDate orderDate = orderDateTime.toLocalDate();
        return (orderDate.isAfter(startDate) || orderDate.isEqual(startDate)) &&
                (orderDate.isBefore(endDate) || orderDate.isEqual(endDate));
    }

    /**
     * Checks if the date of cooking is between the two given dates.
     * 
     * @param startDate the starting date of the range of dates
     * @param endDate   the ending date of the range of dates
     * @return true if this dish's date of cooking is between {@code startDate} and
     *         {@code endDate}
     */
    public boolean cookingDateIsBetween(LocalDate startDate, LocalDate endDate) {
        if (!isCooking() && !isServed())
            return true;
        LocalDate cookingDate = cookingDateTime.toLocalDate();
        return (cookingDate.isAfter(startDate) || cookingDate.isEqual(startDate)) &&
                (cookingDate.isBefore(endDate) || cookingDate.isEqual(endDate));
    }

    /**
     * Checks if the time of order is between the two given times.
     * 
     * @param startTime the starting time of the range of times
     * @param endTime   the ending time of the range of times
     * @return true if this dish's time of ordering is between {@code startTime} and
     *         {@code endTime}
     */
    public boolean orderTimeIsBetween(LocalTime startTime, LocalTime endTime) {
        LocalTime orderTime = orderDateTime.toLocalTime();
        return (orderTime.isAfter(startTime) || orderTime.equals(startTime)) &&
                (orderTime.isBefore(endTime) || orderTime.equals(endTime));
    }

    /**
     * Checks if the time of cooking is between the two given times.
     * 
     * @param startTime the starting time of the range of times
     * @param endTime   the ending time of the range of times
     * @return true if this dish's time of cooking is between {@code startTime} and
     *         {@code endTime}
     */
    public boolean cookingTimeIsBetween(LocalTime startTime, LocalTime endTime) {
        if (!isCooking() && !isServed())
            return true;
        LocalTime cookingTime = cookingDateTime.toLocalTime();
        return (cookingTime.isAfter(startTime) || cookingTime.equals(startTime)) &&
                (cookingTime.isBefore(endTime) || cookingTime.equals(endTime));
    }

    /**
     * Modifies the ID to be assigned to the next created {@code Dish}.
     * 
     * @param id the new ID to be assigned to the next created {@code Dish}.
     */
    public static void setNextId(int id) {
        Dish.nextId = id;
    }

    /**
     * Retrieves the ID to be assigned to the next create {@code Dish}.
     * 
     * @return the ID to be assigned to the next create {@code Dish}.
     */
    public static int getNextId() {
        return Dish.nextId;
    }
}
