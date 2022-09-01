public class Cart {
    private ListInterface<FoodInCart> foodsInCart;
    private int itemCount;

    public Cart() {
        foodsInCart = new LinkedList<FoodInCart>();
        itemCount = 0;
    }

    public void addToCart(MenuItem food, int quantity) {
        foodsInCart.add(new FoodInCart(food, quantity));
        itemCount++;
    }

    public void modifyCart(int modifyIndex, MenuItem modifiedFood, int modifiedQuantity) {
        foodsInCart.replace(modifyIndex, new FoodInCart(modifiedFood, modifiedQuantity));
    }

    public int getItemCount() {
        return itemCount;
    }

    public ListInterface<FoodInCart> getFoodsInCart() {
        return foodsInCart;
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += foodsInCart.getEntry(i).calculateSubtotal();
        }
        return total;
    }

    public String toString() {
        String str = "";
        int i = 0;
        for (FoodInCart foodInCart : foodsInCart) {
            i++;
            str += String.format("%-5d %-30s %-10.2f\n", (i), foodInCart.toString(), foodInCart.calculateSubtotal());
        }
        return str;
    }

}
