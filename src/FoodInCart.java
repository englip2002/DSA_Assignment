/**
 *
 * @author Tan Eng Lip
 */
import java.io.Serializable;

public class FoodInCart implements Serializable{
    private MenuItem food;
    private int quantity;

    public FoodInCart(MenuItem food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public MenuItem getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return String.format("%-20s %-10d\n", food.getMenuItemName(), quantity);
    }

}
