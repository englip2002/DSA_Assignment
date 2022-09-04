/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

/**
 Author: KONG ZHI LIN
 Description: Store food and beverage 
 */
public class MenuItem {
    
    //Properties
    private char dishCategory; //M = main course , B = beverage, A = Appertizer, D = Dessert
    private String dishName;
    private String dishID; 
    private double dishPrice;
    private String dishDescription;
    private static int dishCounter = 1; //  Number to improve the index
    
    //Constructor
    public MenuItem(){
        this.dishCategory = ' ';
        this.dishName = "";
        this.dishPrice = 0.0;
        this.dishDescription = "";
        this.dishID = "";
    }
    
    public MenuItem(char dishCategory, String dishName, double dishPrice, String dishDescription){
        this.dishCategory = dishCategory;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
        this.dishID = generateDishID();
        dishCounter++;
    }

    public MenuItem(String dishID, char dishCategory, String dishName, double dishPrice, String dishDescription){
        this.dishCategory = dishCategory;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
        this.dishID = dishID;
        dishCounter++;
    }
    
    //Accessors & Mutators 
    
     public String getDishName(){
        return dishName;
    }
    
    public double getDishPrice(){
        return dishPrice;
    }
    
    public String getFoodDescription(){
        return dishDescription;
    }
    
    public char getDishCategory(){
        return dishCategory;
    }

    public String getDishID() {
        return dishID;
    }
    
    public void setDishName(String dishName){
        this.dishName = dishName;
    }
    
    public void setDishPrice(double dishPrice){
        this.dishPrice = dishPrice;
    }
    
    public void setDishDescription(String dishDescription){
        this.dishDescription = dishDescription;
    }
    
    public void setDishCategory(char dishCategory){
        this.dishCategory = dishCategory;
    }
    
    private String generateDishID(){
        switch (dishCategory) {
            case 'A':
                return String.format("A%5d", dishCounter);
            case 'M':
                return String.format("M%5d", dishCounter);
            case 'B':
                return String.format("B%5d", dishCounter);
            default:
                return String.format("D%5d", dishCounter);
        }
    }
    
    public String toString(){
        return "Dish ID           : " + dishID + "\n" +
               "Dish Name         : " + dishName + "\n" + 
               "Dish Price        : " + dishPrice + "\n" + 
               "Dish Description  : " + dishDescription + "\n";
    }
    
    @Override
    public boolean equals(Object obj){
        System.out.println("B2");
        if(obj instanceof Category){
            if(dishID.equals(((MenuItem) obj).getCategoryID())){
                return true;
            }
        }
        return false;
    }


}