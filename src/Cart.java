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

    public void deleteFoodInCart(int deleteFoodIndex){
        foods.remove(deleteFoodIndex);
        itemCount--;
    }

    public int getItemCount(){
        return itemCount;
    }

    public ListInterface<FoodInCart> getFoods() {
        return foods;
    }

    public double calculateTotal(){
        double total = 0;
        for(int i =0;i<itemCount;i++){
            total+=foods.getEntry(i).calculateSubtotal();
        }
        return total;
    }

    public String toString(){
        String str="";
        for(int i=0;i<itemCount;i++){
            str+=String.format("%3d %s %3d %5.2lf\n", (i+1),foods.getEntry(i).toString(),foods.getEntry(i).getQuantity(),foods.getEntry(i).calculateSubtotal());
        }
        return str;
    }


}
