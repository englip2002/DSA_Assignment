/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private static int dishCounter = 0001; //  Numner to improve the index
    
    //Constructor
    public MenuItem(){
        this.dishCategory = ' ';
        this.dishName = "";
        this.dishPrice = 0.0;
        this.dishDescription = "";
        dishCounter++;
    }
    
    public MenuItem(char dishCategory, String dishName, double dishPrice, String dishDescription){
        this.dishCategory = dishCategory;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
        dishCounter++;
    }
    
    //Accessors & Mutators 
    
     public String getDishName(){
        return dishName;
    }
    
    public String getDishID(){
        return dishID;
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
    
    public void setDishName(){
        this.dishName = dishName;
    }
    
    public void setDishPrice(){
        this.dishPrice = dishPrice;
    }
    
    public void setDishDescription(){
        this.dishDescription = dishDescription;
    }
    
    public void setDishCategory(){
        this.dishCategory = dishCategory;
    }
    
    private String generateDishID(){
        if(dishCategory == 'M'){
            return String.format("M%5d", dishCounter);
        }
        else if (dishCategory == 'B'){
            return String.format("B%5d", dishCounter);
        }
        else if (dishCategory == 'A'){
            return String.format("A%5d", dishCounter);
        }
        else 
            return String.format("D%5d", dishCounter);
    }
    
    public boolean ModifyMenuItem(){
        return true;
    }
    
    public String toString(){
        return "Dish Name         : " + dishName + "\n" + 
               "Dish Price        : " + dishPrice + "\n" + 
               "Dish Description  : " + dishDescription + "\n";
    }
    

}
