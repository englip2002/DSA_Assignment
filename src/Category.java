/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

/**
 *
 * @author User
 */
public class Category {
    private ArraySet<MenuItem> menuItems;
    private char categoryName;
    private static int counter = 0;

    public Category(char categoryName) {
        this.categoryName = categoryName;
        menuItems = new ArraySet<MenuItem>();
    }

    public char getCategoryName() {
        return categoryName;
    }

    public ArraySet<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        counter++;
    }

    public void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
        counter--;
    }

    // index = index of dishes in category
    // dish = new modified dishes
    public boolean modifyMenuItem(MenuItem menuItem, int index) {
        menuItems.remove(menuItems.getElement()[index - 1]);
        menuItems.add(menuItem);
        return true;
    }

    public int getCounter() {
        return counter;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < counter; i++) {
            MenuItem temp = menuItems.getElementAtPos(i);
            str+=String.format("%10s %20s %30s %5.2lf\n",temp.getDishID(), temp.getDishName(),temp.getFoodDescription(),temp.getDishPrice());
        }
        return str;
    }

}