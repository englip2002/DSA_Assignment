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
        return String.format("%-10s %-10d", food.getMenuItemName(), quantity);
    }

}
