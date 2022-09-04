import java.io.Serializable;

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
public class Category implements Serializable {
    private SetInterface<MenuItem> menuItems;
    private char categoryID;
    private String categoryName;

    public Category(char categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        menuItems = new ArraySet<MenuItem>();
    }

    public String getCategoryName(){
        return categoryName;
    }

    public char getCategoryID() {
        return categoryID;
    }

    public SetInterface<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addToCategory(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void removeFromCategory(MenuItem menuItem) {
        menuItems.remove(menuItem);
    }

    // index = index of dishes in category
    // dish = new modified dishes
    public boolean modifyMenuItem(MenuItem menuItem, int index) {
        menuItems.remove(menuItems.getElementAtPos(index - 1));
        menuItems.add(menuItem);
        return true;
    }

    @Override
    public boolean equals(Object obj){
        
        if(obj instanceof Category){
            if(categoryID == ((Category) obj).getCategoryID()){
                return true;
            }
        }
        return false;
    }

}