import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

// displayMenu(), sortMenuItem(), searchMenu(), 
//modifyMenuItem(), addMenuItem(), removeMenuItem(), 
//popularFood()  
/**
 * Author: KONG ZHI LIN Description: Store food and beverage
 *
 */
public class Menu implements Serializable {
    // Properties
    private SetInterface<Category> menuCategory;

    // Construtor
    public Menu() {
        menuCategory = new ArraySet<Category>();
    }

    public SetInterface<Category> getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(ArraySet<Category> menuCategory) {
        this.menuCategory = menuCategory;
    }

    // To add new Category of the menu
    public void addCategory(Category category) {
        menuCategory.add(category);
    }

    // User add menu item inside the array
    /*
     * Fixed indexed for Different Category
     * [0] == Appertizer
     * [1] == Main Course
     * [2] == Beverage
     * [3] == Dessert
     */
    public boolean classifyMenuItem(MenuItem menuItem) {
        switch (menuItem.getDishCategory()) {
            case 'A':
                Category tempA = ((Category) menuCategory.getElementAtPos(0));
                tempA.addMenuItem(menuItem);
                return true;
            case 'M':
                Category tempM = ((Category) menuCategory.getElementAtPos(1));
                tempM.addMenuItem(menuItem);
                return true;
            case 'B':
                Category tempB = ((Category) menuCategory.getElementAtPos(2));
                tempB.addMenuItem(menuItem);
                return true;
            case 'D':
                Category tempD = ((Category) menuCategory.getElementAtPos(3));
                tempD.addMenuItem(menuItem);
                return true;
            default:
                return false;

        }

    }

    // Remove menu item
    public boolean removeMenuItem(MenuItem menuItem) {
        switch (menuItem.getDishCategory()) {
            case 'A':
                Category tempA = ((Category) menuCategory.getElementAtPos(0));
                tempA.removeMenuItem(menuItem);
                return true;
            case 'M':
                Category tempM = ((Category) menuCategory.getElementAtPos(1));
                tempM.removeMenuItem(menuItem);
                return true;
            case 'B':
                Category tempB = ((Category) menuCategory.getElementAtPos(2));
                tempB.removeMenuItem(menuItem);
                return true;
            case 'D':
                Category tempD = ((Category) menuCategory.getElementAtPos(3));
                tempD.removeMenuItem(menuItem);
                return true;
            default:
                return false;
        }

    }

    // this method us used to print and get input from user of the modify menu item
    // detail
    public void modifyMenuItemDetail(int userCategoryChoice, int userDishChoice, char newCategory,
            String newName, double newPrice, String newDescription) {

        String oldDishID = menuCategory.getElementAtPos(userCategoryChoice - 1).getMenuItems()
                .getElementAtPos(userDishChoice - 1).getDishID();
        MenuItem modifiedMenu = new MenuItem(oldDishID, newCategory, newName, newPrice, newDescription);

        // direct modify item
        menuCategory.getElementAtPos(userCategoryChoice - 1).modifyMenuItem(modifiedMenu, userDishChoice - 1);
    }

    // Modify menu item
    public boolean displayMenuItemBasedCategory(int userCategoryChoice) {
        // int userDishChoice;
        // MenuItem modifiedMenu = new MenuItem();

        switch (userCategoryChoice) {
            case 1:
                displayMenuItem(userCategoryChoice - 1);
            case 2:
                displayMenuItem(userCategoryChoice - 1);
            case 3:
                displayMenuItem(userCategoryChoice - 1);
            case 4:
                displayMenuItem(userCategoryChoice - 1);

        }
        return true;

    }

    // To display specific category menu item
    public void displayMenuItem(int categoryIndex) {
        Category temp = menuCategory.getElementAtPos(categoryIndex);

        for (int i = 0; i < temp.getMenuItems().getNumberOfEntries(); i++) {
            System.out.println("1. " + temp.getMenuItems().getElementAtPos(i).toString());
        }
    }

    // Search method
    public boolean SearchMenuItem(int userCategoryChoice, String menuItemName) {

        Category temp = menuCategory.getElementAtPos(userCategoryChoice - 1);

        for (int i = 0; i < temp.getMenuItems().getNumberOfEntries(); i++) {
            if (temp.getMenuItems().getElementAtPos(i).getDishName() == menuItemName) {
                System.out.println("Dish is found!!");
                System.out.println(temp.getMenuItems().getElementAtPos(i).toString());
                return true;
            }

        }

        return false;
    }

    // Print the menu item
    // Example:
    // Main Course: //CategoryName()
    //
    // Dish ID : MM0001
    // Dish Name : Mushroom Chicken Chop Rice
    // Dish Price : RM 8.50
    // Dish Description : Chicken chop with mushroom sauce and white rice
    //
    // ::
    //
    // Beverage:
    //
    // Dish ID : MB0001
    // Dish Name : Ice Lemon Tea
    // Dish Price : RM 5.50
    // Dish Description : -
    //
    // Appertizer:
    //
    // Dish ID : MA0001
    // Dish Name : Banana Ice Cream Boat
    // Dish Price : RM 10.50
    // Dish Description : 2 flavor of ice cream and chocolate biscuits
    public String displayMenu() {
        String str = "";
        for (int i = 0; i < menuCategory.getNumberOfEntries(); i++) {
            if (menuCategory.getElementAtPos(i).getCategoryName() == 'M') {
                System.out.println("Main Course: \n");
            } else if (menuCategory.getElementAtPos(i).getCategoryName() == 'A') {
                System.out.println("Appertizer: \n");
            } else if (menuCategory.getElementAtPos(i).getCategoryName() == 'B') {
                System.out.println("Beverage: \n");
            } else {
                System.out.println("Dessert: \n");
            }
            for (int j = 0; j < menuCategory.getElementAtPos(i).getCounter(); j++) {
                str += menuCategory.getElementAtPos(i).getMenuItems().getElementAtPos(j).toString() + "\n";
            }
        }
        return str;
    }

}