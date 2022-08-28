public class Cart {
    private ListInterface<FoodInCart> foodsInCart;
    private int itemCount;

    public Cart(){
        foodsInCart = new LinkedList<FoodInCart>();
        itemCount=0;
    }

    public void addToCart(MenuItem food, int quantity){
        FoodInCart tempFood = new FoodInCart(food, quantity);
        foodsInCart.add(tempFood);
        itemCount++;
    }

    public void modifyCart(int modifyIndex, MenuItem modifiedFood, int modifiedQuantity){
        FoodInCart tempFood = new FoodInCart(modifiedFood, modifiedQuantity);
        foodsInCart.replace(modifyIndex, tempFood);
    }

    public void deleteFoodInCart(int deleteFoodIndex){
        foodsInCart.remove(deleteFoodIndex);
        itemCount--;
    }

    public int getItemCount(){
        return itemCount;
    }

    public ListInterface<FoodInCart> getFoods() {
        return foodsInCart;
    }

    public double calculateTotal(){
        double total = 0;
        for(int i =0;i<itemCount;i++){
            total+=foodsInCart.getEntry(i).calculateSubtotal();
        }
        return total;
    }

    public String toString(){
        String str="";
        for(int i=0;i<itemCount;i++){
            str+=String.format("%3d %s %3d %5.2lf\n", (i+1),foodsInCart.getEntry(i).toString(),foodsInCart.getEntry(i).getQuantity(),foodsInCart.getEntry(i).calculateSubtotal());
        }
        return str;
    }


}
