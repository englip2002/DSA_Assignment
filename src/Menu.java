/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

import java.util.Scanner;

// displayMenu(), sortMenuItem(), searchMenu(), 
//modifyMenuItem(), addMenuItem(), removeMenuItem(), 
//popularFood()  
/**
 * Author: KONG ZHI LIN Description: Store food and beverage
 *
 */
public class Menu {
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
    public boolean addMenuItem(MenuItem menuItem) {
        switch (menuItem.getDishCategory()) {
            case 'A':
                menuCategory.getElement()[0].addMenuItem(menuItem);
                return true;
            case 'M':
                menuCategory.getElement()[1].addMenuItem(menuItem);
                return true;
            case 'B':
                menuCategory.getElement()[2].addMenuItem(menuItem);
                return true;
            case 'D':
                menuCategory.getElement()[3].addMenuItem(menuItem);
                return true;
            default:
                return false;
        }

    }

    // Remove menu item
    public boolean removeMenuItem(MenuItem menuItem) {
        switch (menuItem.getDishCategory()) {
            case 'A':
                menuCategory.getElement()[0].removeMenuItem(menuItem);
                return true;
            case 'M':
                menuCategory.getElement()[1].removeMenuItem(menuItem);
                return true;
            case 'B':
                menuCategory.getElement()[2].removeMenuItem(menuItem);
                return true;
            case 'D':
                menuCategory.getElement()[3].removeMenuItem(menuItem);
                return true;
            default:
                return false;
        }

    }

    // this method us used to print and get input from user of the modify menu item
    // detail
    public static void modifyMenuItemDetail(int userCategoryChoice, int userDishChoice, char newCategory,
            String newName, double newPrice, String newDescription) {

        String oldDishID = menuCategory.getElement()[userCategoryChoice - 1].getMenuItems().getElement()[userDishChoice
                - 1]
                .getDishID();
        MenuItem modifiedMenu = new MenuItem(oldDishID, newCategory, newName, newPrice, newDescription);

        // direct modify item
        menuCategory.getElement()[userCategoryChoice - 1].modifyMenuItem(modifiedMenu, userDishChoice - 1);
    }

    // Modify menu item
    public static boolean modifyMenuItem(int userCategoryChoice) {
        int userDishChoice;
        MenuItem modifiedMenu = new MenuItem();

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
    public static void displayMenuItem(int categoryIndex) {
        Category temp = menuCategory.getElement()[categoryIndex];

        for (int i = 0; i < temp.getMenuItems().getNumberOfEntries(); i++) {
            System.out.println("1. " + temp.getMenuItems().getElement()[i].toString());
        }
    }

    // Search method
    public boolean SearchMenuItem() {

        Scanner sc = new Scanner(System.in);

        displayCategoryMenuChoice();
        int userCategoryChice = sc.nextInt();

        System.out.print("Please enter the name of the dish: ");
        String dishName = sc.nextLine();

        Category temp = menuCategory.getElement()[userCategoryChice - 1];

        for (int i = 0; i < temp.getMenuItems().getNumberOfEntries(); i++) {
            if (temp.getMenuItems().getElement()[i].getDishName() == dishName) {
                System.out.println("Dish is found!!");
                System.out.println(temp.getMenuItems().getElement()[i].toString());
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
            if (menuCategory.getElement()[i].getCategoryName() == 'M') {
                System.out.println("Main Course: \n");
            } else if (menuCategory.getElement()[i].getCategoryName() == 'A') {
                System.out.println("Appertizer: \n");
            } else if (menuCategory.getElement()[i].getCategoryName() == 'B') {
                System.out.println("Beverage: \n");
            } else {
                System.out.println("Dessert: \n");
            }
            for (int j = 0; j < menuCategory.getElement()[i].getCounter(); j++) {
                str += menuCategory.getElement()[i].getMenuItems().getElement()[j].toString() + "\n";
            }
        }
        return str;
    }

}