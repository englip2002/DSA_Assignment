/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

/**
 * Author: KONG ZHI LIN
 * Description: Store food and beverage
 */
public class MenuItem {

    // Properties
    private char menuItemCategory; // M = main course , B = beverage, A = Appertizer, D = Dessert
    private String menuItemName;
    private String menuItemID;
    private double menuItemPrice;
    private String menuItemDescription;
    private static int dishCounter = 1; // Number to improve the index

    // Constructor
    public MenuItem() {
        this.menuItemCategory = ' ';
        this.menuItemName = "";
        this.menuItemPrice = 0.0;
        this.menuItemDescription = "";
        this.menuItemID = "";
    }

    public MenuItem(char menuItemCategory, String menuItemName, double menuItemPrice, String menuItemDescription) {
        this.menuItemCategory = menuItemCategory;
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.menuItemDescription = menuItemDescription;
        this.menuItemID = generateDishID();
        dishCounter++;
    }

    public MenuItem(String menuItemID, char menuItemCategory, String menuItemName, double menuItemPrice, String menuItemDescription) {
        this.menuItemCategory = menuItemCategory;
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.menuItemDescription = menuItemDescription;
        this.menuItemID = menuItemID;
        dishCounter++;
    }

    // Accessors & Mutators
    public String getDishName() {
        return menuItemName;
    }

    public double getDishPrice() {
        return menuItemPrice;
    }

    public String getFoodDescription() {
        return menuItemDescription;
    }

    public char getDishCategory() {
        return menuItemCategory;
    }

    public String getDishID() {
        return menuItemID;
    }

    public void setDishName(String dishName) {
        this.menuItemName = dishName;
    }

    public void setDishPrice(double dishPrice) {
        this.menuItemPrice = dishPrice;
    }

    public void setDishDescription(String dishDescription) {
        this.menuItemDescription = dishDescription;
    }

    public void setDishCategory(char dishCategory) {
        this.menuItemCategory = dishCategory;
    }

    private String generateDishID() {
        if (menuItemCategory == 'A') {
            return String.format("A%05d", dishCounter);
        } else if (menuItemCategory == 'M') {
            return String.format("M%05d", dishCounter);
        } else if (menuItemCategory == 'B') {
            return String.format("B%05d", dishCounter);
        } else
            return String.format("D%05d", dishCounter);
    }

    public String toString() {
        return  "Dish ID           : " + menuItemID + "\n" +
                "Dish Name         : " + menuItemName + "\n" +
                "Dish Price        : " + menuItemPrice + "\n" +
                "Dish Description  : " + menuItemDescription + "\n";
    }

}