
/**
 * Author: KONG ZHI LIN
 * Description: Sub Client class --> To implement operation on entity and ADT 
 * In this class will have 2 separate own array set but each package has its own specific menu items tahat he owns
 */

import java.util.Scanner;

import java.util.Iterator;

public class MenuManager {

    // Class Attributes
    // Individual and separate package array set
    private SetInterface<Package> packageSet = new ArraySet<>();
    // Individual and separate menu item array set
    private SetInterface<MenuItem> menuItemSet = new ArraySet<>();

    Scanner scanner = new Scanner(System.in);

    // Constructor --> Initialize the packages and menu items
    public MenuManager() {
        packageSet.add(new Package("Package A", 38.00, "1 Appertizer, 1 Main Course, 1 Beverage"));
        packageSet.add(new Package("Package B", 58.60, "1 Appertizer, 1 Main Course, 1 Beverage, 1 Dessert"));
        packageSet.add(new Package("Package C", 108.00, "1 Appertizer, 2 Main Course, 2 Beverage, 2 Dessert"));
        packageSet.add(new Package("Package D", 120.50, "3 Appertizer, 2 Main Course, 3 Beverage, 3 Dessert"));
        menuItemSet.add(new MenuItem("Appertizer", "French Fries", "Hand cut wedges of Yukan Cold potatoes."));
        menuItemSet.add(new MenuItem("Main Course", "Spaghetti Marinara", "Spaghetti with seafood and tomato sauce."));
        menuItemSet.add(new MenuItem("Beverage", "Long Black", "2 shots of espresso and hot water."));
        menuItemSet.add(new MenuItem("Dessert", "Lime Pie", "Targy custard with graham crocker crust."));
    }

    public void addNewPackage() {
        System.out.println("\n");
        System.out.println("\t  ADD NEW PACKAGE: ");
        System.out.println("\t=======================");
        System.out.println("Please enter package name: ");
        String inputPackageName = scanner.nextLine();
        // Clear buffer
        scanner.nextLine();
        System.out.println("Please enter package price: RM ");
        double inputPackagePrice = scanner.nextDouble();
        System.out.println("Please enter package descriptions: ");
        String inputPackageDescription = scanner.nextLine();
        // Clear buffer
        scanner.nextLine();

        // Create new package information and add into the packageSet
        packageSet.add(new Package(inputPackageName, inputPackagePrice, inputPackageDescription));

    }

    public void removePackage() {
        System.out.println("\n");

    }

    // Assign menu item to package
    public void addMenuItemToPackage() {
        System.out.println("\n");

    }

    public String displayMenuItems() {
        return "";
    }

}
