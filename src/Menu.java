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
    // Properties
    private SetInterface<Category> dishCategory;
    SetInterface<MenuItem> menuItem;

    // Construtor
    public Menu() {
        dishCategory = new ArraySet<Category>();
    }

    public SetInterface<Category> getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(ArraySet<Category> dishCategory) {
        this.dishCategory = dishCategory;
    }

    // To add the Cateory into the menu (Separate the category)
    public void addCategory(Category category) {
        dishCategory.add(category);
    }

    // User add dish
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

    // Remove menu item
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

   //to display a category menu for user
    private void displayCategoryMenuChoice() {
        
            System.out.println("Please select the categories: ");
            System.out.println("1. Appertizer");
            System.out.println("2. Main Course");
            System.out.println("3. Beverage");
            System.out.println("4. Dessert");
            System.out.print("Enetr your choice: ");
        
    }
    
    // this method us used to print and get input from user of the modify menu item detail
    private MenuItem modifyMenuItemDetail(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter new informations of dish: ");
        System.out.print("Dish Category: ");
        char newCategory = sc.next().charAt(0);
        System.out.println("Dish Name: ");
        String newName = sc.nextLine();
        System.out.println("Dish Price: ");
        double newPrice = sc.nextDouble();
        System.out.println("Dish Description: ");
        String newDescription = sc.nextLine();
        
         MenuItem modifiedMenu = new MenuItem(newCategory, newName, newPrice, newDescription);
         
         return modifiedMenu;
    }

    // Modify menu item
    public boolean modifyMenuItem() {
        int userDishChoice;
        Scanner sc = new Scanner(System.in);
        MenuItem modifiedMenu = new MenuItem();

        displayCategoryMenuChoice();
        int userCategoryChoice = sc.nextInt();
        
        if (userCategoryChoice == 1) { // Print Appertizer dishes
            System.out.println("Please select the dish you would like to modify: ");
            displayMenuItem(userCategoryChoice- 1);
            System.out.print("Enter your choice: ");
            userDishChoice = sc.nextInt();
            modifiedMenu = modifyMenuItemDetail();
            
            String newID = modifiedMenu.getDishID();  //id how to store back??
            dishCategory.getElement()[0].modifyMenuItem(modifiedMenu, userDishChoice);
        }
        else if (userCategoryChoice == 2) { 
            System.out.println("Please select the dish you would like to modify: ");
            displayMenuItem(userCategoryChoice- 1);
            System.out.print("Enter your choice: ");
            userDishChoice = sc.nextInt();
            modifiedMenu = modifyMenuItemDetail();
            
            String newID = modifiedMenu.getDishID();  //id how to store back??
            dishCategory.getElement()[1].modifyMenuItem(modifiedMenu, userDishChoice);
        }
        else if (userCategoryChoice == 3) {
            System.out.println("Please select the dish you would like to modify: ");
            displayMenuItem(userCategoryChoice- 1);
            System.out.print("Enter your choice: ");
            userDishChoice = sc.nextInt();
            modifiedMenu = modifyMenuItemDetail();
            
            String newID = modifiedMenu.getDishID();  //id how to store back??
            dishCategory.getElement()[2].modifyMenuItem(modifiedMenu, userDishChoice);
        }
        else if (userCategoryChoice == 4) {
            System.out.println("Please select the dish you would like to modify: ");
            displayMenuItem(userCategoryChoice- 1);
            System.out.print("Enter your choice: ");
            userDishChoice = sc.nextInt();
            modifiedMenu = modifyMenuItemDetail();
            
            String newID = modifiedMenu.getDishID();  //id how to store back??
            dishCategory.getElement()[3].modifyMenuItem(modifiedMenu, userDishChoice);
        }

        sc.close();
        return true;

    }
    
    //To display specific category menu item
    private void displayMenuItem(int categoryIndex) {
        Category temp = dishCategory.getElement()[categoryIndex];

        for (int i = 0; i < temp.getDishes().getNumberOfEntries(); i++) {
            System.out.println("1. " + temp.getDishes().getElement()[i].toString());
        }
    }

    //Search method 
    public boolean SearchMenuItem() {
        
        Scanner sc = new Scanner(System.in);
        
        displayCategoryMenuChoice();
        int userCategoryChice = sc.nextInt();
        
        System.out.print("Please enter the name of the dish: ");
        String dishName = sc.nextLine();
        
        Category temp = dishCategory.getElement()[userCategoryChice - 1];
        
        for (int i = 0; i < temp.getDishes().getNumberOfEntries(); i++) {
            if(temp.getDishes().getElement()[i].getDishName() == dishName){
                System.out.println("Dish is found!!");
                System.out.println(temp.getDishes().getElement()[i].toString());
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
        for (int i = 0; i < dishCategory.getNumberOfEntries(); i++) {
            if (dishCategory.getElement()[i].getCategoryName() == 'M') {
                System.out.println("Main Course: \n");
            } else if (dishCategory.getElement()[i].getCategoryName() == 'A') {
                System.out.println("Appertizer: \n");
            } else if (dishCategory.getElement()[i].getCategoryName() == 'B') {
                System.out.println("Beverage: \n");
            } else {
                System.out.println("Dessert: \n");
            }
            for (int j = 0; j < dishCategory.getElement()[i].getCounter(); j++) {
                str += dishCategory.getElement()[i].getDishes().getElement()[j].toString() + "\n";
            }
        }
        return str;
    }

}