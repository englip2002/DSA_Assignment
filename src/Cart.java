public class Cart {
    private ListInterface<FoodInCart> foods;
    private int itemCount;

    public Cart(){
        foods = new LinkedList<FoodInCart>();
        itemCount=0;
    }

    public void addToCart(MenuItem food, int quantity){
        FoodInCart tempFood = new FoodInCart(food, quantity);
        foods.add(tempFood);
        itemCount++;
    }

    public void modifyCart(int modifyIndex, MenuItem modifiedFood, int modifiedQuantity){
        FoodInCart tempFood = new FoodInCart(modifiedFood, modifiedQuantity);
        foods.replace(modifyIndex, tempFood);
    }

    public void deleteDish(int deleteDishIndex){
        foods.remove(deleteDishIndex);
    }

    public int getItemCount(){
        return itemCount;
    }

    public ListInterface<FoodInCart> getFoods() {
        return foods;
    }


}
