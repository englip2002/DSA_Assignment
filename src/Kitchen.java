
/**
 *
 * @author Thong So Xue
 */

public class Kitchen implements Comparable<Kitchen> {
    private static int nextId = 1;
    private String id;
    private String name;
    private int maxCapacity;
    private QueueInterface<Dish> cookingQueue;
    
    public Kitchen(String id, String name, int maxCapacity) {
        cookingQueue = new LinkedQueue<Dish>();
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }
    
    public Kitchen(String name, int maxCapacity) {
        cookingQueue = new LinkedQueue<Dish>();
        this.id = "K" + String.format("%02d", nextId);
        nextId++;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }
    
    public boolean queueDish(Dish newDish) {
        if (isCookingQueueFull()) {
            System.out.println("\tKitchen " + name + " is full and cannot accept more orders at the moment!");
            return false;
        }
        newDish.cookDish();
        cookingQueue.enqueue(newDish);
        return true;
    }
    
    public Dish serveTopDish() {
        Dish d = cookingQueue.dequeue();
        d.serveDish();
        return d;
    }
    
    public boolean isCookingQueueEmpty() {
        return cookingQueue.isEmpty();
    }
    
    public boolean isCookingQueueFull() {
        return getAmountOfDishes() == maxCapacity;
    }
    
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
    
    @Override
    public String toString() {
        return  String.format(" %3s ", id) + "|" + 
                String.format(" %-20s ", name) + "|" + 
                String.format(" %15d ", maxCapacity) + "|" + 
                " " + displayCookingQueue();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public int getAmountOfDishes() {
        return cookingQueue.size();
    }
    
    public QueueInterface<Dish> getCookingQueue() {
        return cookingQueue;
    }
    
    public Dish getNextDish() {
        return cookingQueue.getFront();
    }

    @Override
    public int compareTo(Kitchen o) {
        return this.name.compareTo(o.name);
    }
}
