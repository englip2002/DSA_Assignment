import java.io.Serializable;

/**
 * An entity class that represents a kitchen that will be receiving dish orders
 * from customers, and cook them in a queued order or First-Come-First-Serve
 * (FIFO) order. Each {@code Kitchen} will have its own cooking queue, with
 * their own maximum cooking queue capacity.
 * 
 * @author Thong So Xue
 */
public class Kitchen implements Serializable {
    /**
     * The ID that will be assigned to the next created {@code Kitchen}
     */
    private static int nextId = 1;

    /**
     * A unique ID assigned to every {@code Kitchen}.
     */
    private String id;

    /**
     * The name that represents this {@code Kitchen}.
     */
    private String name;

    /**
     * The maximum capacity of this {@code Kitchen}'s cooking queue.
     */
    private int maxCapacity;

    /**
     * The cooking queue of this {@code Kitchen}, which will be accepting
     * {@code Dish}es only.
     */
    private QueueInterface<Dish> cookingQueue;

    /**
     * Creates a new {@code Kitchen} with the given name and maximum cooking queue
     * capacity. The kitchen's ID is automatically generated to avoid data
     * redundancy.
     * 
     * @param name        the name of the kitchen
     * @param maxCapacity the maximum capacity of this kitchen's cooking queue
     */
    public Kitchen(String name, int maxCapacity) {
        cookingQueue = new LinkedQueue<Dish>();
        this.id = "K" + String.format("%02d", nextId);
        nextId++;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Adds a given {@code Dish} to the back of this kitchen's cooking queue, only
     * if the cooking queue have not reached the maximum capacity.
     * 
     * @param newDish the {@code Dish} to be added.
     * @return true if the given dish is successfully added, false if the cooking
     *         queue has reached maximum capacity.
     */
    public boolean queueDish(Dish newDish) {
        if (isCookingQueueFull()) {
            System.out.println("\tKitchen " + name + " is full and cannot accept more orders at the moment!");
            return false;
        }
        newDish.cookDish();
        cookingQueue.enqueue(newDish);
        return true;
    }

    /**
     * Removes and retrieves the next {@code Dish} to be served out from the cooking
     * queue, which will be the {@code Dish} located at the front of the queue.
     * 
     * @return the next {@code Dish} to be served, located at the front of the
     *         queue.
     */
    public Dish serveNextDish() {
        Dish d = cookingQueue.dequeue();
        d.serveDish();
        return d;
    }

    /**
     * Checks if the cooking queue is empty.
     * 
     * @return true if the cooking queue is empty, false if not.
     */
    public boolean isCookingQueueEmpty() {
        return cookingQueue.isEmpty();
    }

    /**
     * Checks if the cooking queue has reached its maximum capacity.
     * 
     * @return true if the cooking queue has reached its maximum capacity, false if
     *         not.
     */
    public boolean isCookingQueueFull() {
        return getAmountOfDishes() == maxCapacity;
    }

    /**
     * Displays the cooking queue graphically like a bar.
     * 
     * @return a string that represents the cooking queue graphically like a bar.
     */
    public String displayCookingQueue() {
        String result = "";
        for (int i = 0; i < getAmountOfDishes(); i++) {
            result += "X";
        }
        for (int i = 0; i < maxCapacity - getAmountOfDishes(); i++) {
            result += "o";
        }
        return result;
    }

    /**
     * Retrieves a formatted string that contains all the {@code Kitchen}'s
     * information, in a table row format.
     * 
     * @return a string formatted as a table row that contains all the
     *         {@code Kitchen}'s information
     */
    @Override
    public String toString() {
        return String.format(" %3s ", id) + "|" +
                String.format(" %-20s ", name) + "|" +
                String.format(" %15d ", maxCapacity) + "|" +
                " " + displayCookingQueue();
    }

    /**
     * Modifies the name of this {@code Kitchen}.
     * 
     * @param name the new name to be assigned to this {@code Kitchen}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifies the maximum cooking queue capacity of this {@code Kitchen}.
     * 
     * @param name the new maximum capacity to be assigned to this
     *             {@code Kitchen}'s cooking queue.
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Retrieves the ID of this {@code Kitchen}.
     * 
     * @return the ID of this {@code Kitchen}.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the name of this {@code Kitchen}.
     * 
     * @return the name of this {@code Kitchen}.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the maximum cooking queue capacity of this {@code Kitchen}.
     * 
     * @return the maximum cooking queue capacity of this {@code Kitchen}.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Retrieves the amount of dishes in the cooking queue of this {@code Kitchen}.
     * 
     * @return the amount of dishes in the cooking queue of this {@code Kitchen}.
     */
    public int getAmountOfDishes() {
        return cookingQueue.size();
    }

    /**
     * Retrieves the cooking queue of this {@code Kitchen}.
     * 
     * @return the cooking queue of this {@code Kitchen}.
     */
    public QueueInterface<Dish> getCookingQueue() {
        return cookingQueue;
    }

    /**
     * Retrieves the next {@code Dish} to be served out from the cooking queue / the
     * {@code Dish} at the front of the cooking queue, but doesn't removes it from
     * the cooking queue.
     * 
     * @return the next {@code Dish} to be served out from the cooking queue.
     */
    public Dish getNextDish() {
        return cookingQueue.getFront();
    }

    /**
     * Modifies the ID to be assigned to the next created {@code Kitchen}.
     * 
     * @param id the new ID to be assigned to the next created {@code Kitchen}.
     */
    public static void setNextId(int id) {
        Kitchen.nextId = id;
    }

    /**
     * Retrieves the ID to be assigned to the next create {@code Kitchen}.
     * 
     * @return the ID to be assigned to the next create {@code Kitchen}.
     */
    public static int getNextId() {
        return Kitchen.nextId;
    }
}
