public class FoodInCart {
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

    public double calculateSubtotal(){
        return food.getPrice()*quantity;
    }

    public String toString() {
        return String.format("%10s %5d", food.getName(),quantity);
    }

}
