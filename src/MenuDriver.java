
import java.util.Scanner;

public class MenuDriver {
    public static void main(String[] args) {

        FileHandler <Menu> menuFile = new FileHandler <Menu> ("Menu.dat");

        // SetInterface<Category> menu = new ArraySet<Category>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        //SetInterface<Menu> st = new ArraySet<>();
        menu.addCategory(new Category('A'));
        menu.addCategory(new Category('M'));
        menu.addCategory(new Category('B'));
        menu.addCategory(new Category('D'));

    
        //Initialized some menu items inside to the menu 
        menu.classifyMenuItem(new MenuItem('A', "French Fries", 5.00, "Hand cut wedges of Yukan Cold potatoes."));
        menu.classifyMenuItem(new MenuItem('A', "Stuffed Mushrooms", 9.00, "Mushrooms caps baked with a filling cheese."));
        menu.classifyMenuItem(new MenuItem('M', "Spaghetti Marinara", 15.50, "Spaghetti with seafood and tomato sauce."));
        menu.classifyMenuItem(new MenuItem('M', "Pork Rib", 30.80, "BBQ Pork Rib, homemmade fies and green salad."));
        menu.classifyMenuItem(new MenuItem('B', "Ice Lemon Tea", 6.90, "Tea with few slices lemon."));
        menu.classifyMenuItem(new MenuItem('B', "Long Black", 10.50, "2 shots of espresso and hot water."));
        menu.classifyMenuItem(new MenuItem('D', "Cheese Cake", 9.50, "Cheese cake with burnt and topped with caramel chocolate curls."));
        menu.classifyMenuItem(new MenuItem('D', "Lime Pie", 10.80, "Targy custard with graham crocker crust."));
        

        menuFile.write(menu);

        //menu.displayMenu(); //Cannot display

        displayMenuTableChoice();
        int userChoice = scanner.nextInt();

        
            if (userChoice == 1) {
                // add item
                char menuItemCategory = 'A';
                String menuItemName;
                double menuItemPrice;
                String menuItemDescription;

                int categoryChoice = 0;

                // enter add category (display category choice)
                do {
                    displayCategoryChoice();
                    categoryChoice = scanner.nextInt();
                    // M = main course , B = beverage, A = Appertizer, D = Dessert
                    switch (categoryChoice) {
                        case 1:
                            menuItemCategory = 'A';
                            break;
                        case 2:
                            menuItemCategory = 'M';
                            break;
                        case 3:
                            menuItemCategory = 'B';
                            break;
                        case 4:
                            menuItemCategory = 'D';
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } while (categoryChoice < 1 || categoryChoice > 4);


                System.out.println("\n");

                // clear buffer
                scanner.nextLine();
                
                // input data
                System.out.print("Enter Menu Item Name: ");
                menuItemName = scanner.nextLine();

                System.out.print("Enter Menu Item Price: ");
                menuItemPrice = scanner.nextDouble();

                // clear buffer
                scanner.nextLine();

                System.out.print("Enter Menu Item Description: ");
                menuItemDescription = scanner.nextLine();

                System.out.print("\n");

                // pass into a temp menuItem
                MenuItem temp = new MenuItem(menuItemCategory, menuItemName, menuItemPrice, menuItemDescription);

                // pass to add
                menu.classifyMenuItem(temp);

                menu.displayMenuItem(categoryChoice -1);

            } else if (userChoice == 2) {
                // remove item
                displayCategoryChoice();

                int categoryChoice = 0;

                // enter add category (display category choice)

                displayCategoryChoice();
                categoryChoice = scanner.nextInt();

                System.out.print("\n");

                menu.displayMenuItemBasedCategory(categoryChoice);
                System.out.println("Please select which dish you would like to remove!");
                System.out.print("Enter your choice: ");
                int userDishChoice = scanner.nextInt();

                char menuCategory;
                String menuName, menuDescription;
                double menuPrice;

                menuCategory = 

                MenuItem temp = new MenuItem()


            } else if (userChoice == 3) {

                // read file when starting
                // reservation
                FileHandler reservationFile = new FileHandler("Reservations.dat");
                ListInterface<Reservation> reservationList = (ListInterface) reservationFile.read();

                // display menu item
                menu = (Menu) menuFile.read();

                menu.displayMenu();

            } else if (userChoice == 4) {
                // modify menu item
                displayCategoryChoice();
                int userCategoryChoice = scanner.nextInt();

                // Pass the user category choose
                menu.displayMenuItemBasedCategory(userCategoryChoice);
                // display the category dish at the modify method (menu class)
                System.out.println("Please select the dish you would like to modify! ");
                System.out.print("Enter your choice: ");
                int userDishChoice = scanner.nextInt();

                System.out.println("Please enter new informations of dish: ");
                System.out.print("Dish Category: ");
                char newCategory = scanner.next().charAt(0);
                System.out.println("Dish Name: ");
                String newName = scanner.nextLine();
                System.out.println("Dish Price: ");
                double newPrice = scanner.nextDouble();
                System.out.println("Dish Description: ");
                String newDescription = scanner.nextLine();

                menu.modifyMenuItemDetail(userCategoryChoice, userDishChoice, newCategory, newName, newPrice,
                        newDescription);
            } else if (userChoice == 5) {
                // Search menu items
                displayCategoryChoice();
                int userCategoryChoice = scanner.nextInt();

                System.out.print("Please enter the name of the menu item: ");
                String menuItemName = scanner.nextLine();

                menu.SearchMenuItem(userCategoryChoice, menuItemName);

            } else {
                System.out.println("You had key in invalid number!!");
                System.out.print("Please enter your choice again: ");
                int userSecondChoice = scanner.nextInt();
                displayMenuTableChoice();
            }

            System.out.println("Do you want to continue('N' to exit): ");
            char continueChoice = scanner.nextLine().charAt(0);


            scanner.close();
        
    }

    // to display a category menu for user
    public static void displayMenuTableChoice() {

        System.out.println("           Menu         ");
        System.out.println("------------------------");
        System.out.println("1. Add Menu Item(s)");
        System.out.println("2. Remove Menu Item(s)");
        System.out.println("3. Display Menu Items(s)");
        System.out.println("4. Modify Menu Item(s)");
        System.out.println("5. Search Menu Items(s)");
        System.out.print("Enter your choice(1-5): ");

    }

    public static void displayCategoryChoice() {
        System.out.print("\n");
        System.out.println("Please select the categories: ");
        System.out.println("1. Appertizer");
        System.out.println("2. Main Course");
        System.out.println("3. Beverage");
        System.out.println("4. Dessert");
        System.out.print("Enter your choice: ");
    }

    public static MenuItem getRemovMenuItem(int menuItemIndex){
        MenuItem menuItem = new MenuItem();

        for(int i = 0; i < )

        return menuItem;
    }

}
