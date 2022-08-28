/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

/**
 *
 * @author User
 */
public class Category {
    private ArraySet<MenuItem> dishes;
    private char categoryName;
    private static int counter = 0;

    public Category(char categoryName) {
        this.categoryName = categoryName;
        dishes = new ArraySet<MenuItem>();
    }

    public char getCategoryName() {
        return categoryName;
    }

    public ArraySet<MenuItem> getDishes() {
        return dishes;
    }

    public void addDish(MenuItem dish) {
        dishes.add(dish);
        counter++;
    }

    public void removeDish(MenuItem dish) {
        dishes.remove(dish);
        counter--;
    }

    //index = index of dishes in category
    //dish = new modified dishes
    public boolean modifyMenuItem(MenuItem dish, int index) {
        dishes.remove(dishes.getElement()[index - 1]);
        dishes.add(dish);
        return true;
    }

    public int getCounter() {
        return counter;
    }

}