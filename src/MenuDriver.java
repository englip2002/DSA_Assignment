import java.util.Scanner;

public class MenuDriver {
    public static void main(String[] args) {

        SetInterface<Category> menu = new Category();
        Scanner scanner = new Scanner(System.in);

        displayCategoryMenuChoice();
        int userCategoryChoice = scanner.nextInt();

        // Pass the user category choose
        modifyMenuItem(userCategoryChoice);
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

        modifyMenuItemDetail(userCategoryChoice, userDishChoice, newCategory, newName, newPrice, newDescription);

        scanner.close();
    }

    // to display a category menu for user
    public static void displayCategoryMenuChoice() {

        System.out.println("Please select the categories: ");
        System.out.println("1. Appertizer");
        System.out.println("2. Main Course");
        System.out.println("3. Beverage");
        System.out.println("4. Dessert");
        System.out.print("Enter your choice: ");

    }
}
