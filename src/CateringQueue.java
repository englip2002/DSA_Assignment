
/**
 *
 * @author Thong So Xue
 */

import java.io.Serializable;
import java.util.LinkedList;

public class CateringQueue implements Serializable {
    // Class Attributes
    private QueueInterface<Dish> cateringQueue;
    private LinkedList<Dish> servedDishes;
    
    // Constructors
    public CateringQueue() {
        cateringQueue = new LinkedQueue<>();
        servedDishes = new LinkedList<>();
    }
    public QueueInterface getQueue() {
        return cateringQueue;
    }
    
    // Catering Queue Methods
    public void addDish(Dish newDish) {
        newDish.queueDish();
        cateringQueue.enqueue(newDish);
    }
    
    public Dish serveTopDish() {
        Dish serving = cateringQueue.dequeue();
        serving.serveDish();
        servedDishes.add(serving);
        return serving;
    }
    
    public Dish peekTopDish() {
        return cateringQueue.getFront();
    }
    
    // Reporting methods
    public void showServedDishes() {
        if (servedDishes.isEmpty()) {
            System.out.println("----- < There are no dishes in queue > -----");
        }
        else {
            for (Dish each: servedDishes) {
                System.out.println(each);
            }
        }
    }
    
    public void showDishesInQueue() {
        System.out.println(" ID\t| " +
                String.format(" %-20s ", "Food") +  
                "\t| Quantity | " + 
                String.format("%-19s", "Order Time") + " | " + 
                String.format("%-19s", "Serve Time") + 
                " | Food Finished | " + 
                String.format("%-10s", "Status") + " |");
        
        if (cateringQueue.isEmpty()) {
            System.out.println("----- < There are no dishes in queue > -----");
        }
        else {
            for (Dish each: cateringQueue) {
                System.out.println(each);
            }
        }
    }
    
    public void generateCateringReport() {
        // feeling lazy
    }
}
