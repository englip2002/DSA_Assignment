/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.Scanner;

// displayMenu(), sortMenuItem(), searchMenu(), 
//modifyMenuItem(), addMenuItem(), removeMenuItem(), 
//popularFood()  
/**
 * Author: KONG ZHI LIN Description: Store food and beverage
 *
 */
public class Menu {

    //Properties
    private SetInterface<Category> dishCategory;
    SetInterface<MenuItem> menuItem;

    //Construtor
    public Menu() {
        dishCategory = new ArraySet<Category>();
    }

    public SetInterface<Category> getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(ArraySet<Category> dishCategory) {
        this.dishCategory = dishCategory;
    }
    
    //To add the Cateory into the menu (Separate the category) 
     public void addCategory(Category category) {
        dishCategory.add(category);
    }
    
    //User add dish
    public boolean addDish(MenuItem dishItem) {
        if (dishItem.getDishCategory() == 'A') {
            dishCategory.getElement()[0].addDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'M') {
            dishCategory.getElement()[1].addDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'B') {
            dishCategory.getElement()[2].addDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'D') {
            dishCategory.getElement()[3].addDish(dishItem);
            return true;
        } else {
            return false;
        }

    }

    //Remove menu item
     public boolean removeDish(MenuItem dishItem) {
        if (dishItem.getDishCategory() == 'A') {
            dishCategory.getElement()[0].removeDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'M') {
            dishCategory.getElement()[1].removeDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'B') {
            dishCategory.getElement()[2].removeDish(dishItem);
            return true;
        } else if (dishItem.getDishCategory() == 'D') {
            dishCategory.getElement()[3].removeDish(dishItem);
            return true;
        } else {
            return false;
        }

    }
    
    //modify (Category also got one modify method)
     /*
     Step 1 : Print category to user to choose
     2: Print categoty der food
     3: choose 
     4: index 
     */
     
     public void DisplayMenuChoice(){
        
        System.out.println("Please select the categories: ");
        System.out.println("1. Appertizer");
        System.out.println("2. Main Course");
        System.out.println("3. Beverage");
        System.out.println("4. Dessert");
        System.out.print("Choice: ");
    }
    
    //Modify menu item
    public boolean ModifyMenuItem() {
        DisplayMenuChoice();
        
        Scanner sc = new Scanner(System.in);
        
        int choice = sc.nextInt();
        
        if(choice == 1) { //Print Appertizer dishes 
            dishCategory.getElement()[0].getDishes().getElement()[0].ModifyMenuItem(); //menu item array
        }
            
        }
        
//    
    private void displayDish(SetInterface<Category> dishCategory){
        for(int i = 0; i < dishCategory.getElement()[i].getCounter(); i++){
                System.out.println(dishCategory.getElement()[i].getDishCategory().toString()); //Got problem
            }
    }
    
    public SetInterface<MenuItem> SearchMenuItem(MenuItem dishItem){
        
    }

    //Searching Menu item item binary Search? 
    //Sorted or unsorted 
//    public Set<MenuItem> searchItemByName(String dishName){
//        for(int i = 0; i < menuItem.  )
//    }
    
     
     
     
     //Search by Name 
     

    

    
    
    //Print the menu item 
//Example: 
//Main Course: //CategoryName()
//
//Dish ID           : MM0001
//Dish Name         : Mushroom Chicken Chop Rice
//Dish Price        : RM 8.50
//Dish Description  : Chicken chop with mushroom sauce and white rice 
//
//::
//
//Beverage:
//
//Dish ID           : MB0001
//Dish Name         : Ice Lemon Tea
//Dish Price        : RM 5.50
//Dish Description  : - 
//
//Appertizer:
//
//Dish ID           : MA0001
//Dish Name         : Banana Ice Cream Boat
//Dish Price        : RM 10.50
//Dish Description  : 2 flavor of ice cream and chocolate biscuits 
    public String displayMenu() {
        String str = "";
        for (int i = 0; i < dishCategory.getNumberOfEntries(); i++) {
            if (dishCategory.getElement()[i].getCategoryName() == 'M') {
                System.out.println("Main Course: \n");
            }
            else if(dishCategory.getElement()[i].getCategoryName() == 'A'){
                System.out.println("Appertizer: \n");
            }
            else if (dishCategory.getElement()[i].getCategoryName() == 'B') {
                System.out.println("Beverage: \n");
            }
            else {
                System.out.println("Dessert: \n");
            }
            for (int j = 0; j < dishCategory.getElement()[i].getCounter(); j++) {
                str += dishCategory.getElement()[i].getDishCategory().getElement()[j].toString() + "\n";
            }
        }
        return str;
    }

}
