
/**
 * Author: KONG ZHI LIN
 * Description: Sub Client class --> To implement operation on entity and ADT 
 * In this class will have 2 separate own array set but each package has its own specific menu items tahat he owns
 */

import java.util.Scanner;
import java.io.Serializable;
import java.util.Iterator;

public class MenuManager implements Serializable {

    // Class Attributes
    // Individual and separate package array set
    private SetInterface<Package> packageSet = new ArraySet<>();
    // Individual and separate menu item array set
    private SetInterface<MenuItem> menuItemSet = new ArraySet<>();

    Scanner scanner = new Scanner(System.in);

    // Constructor --> Initialize the packages and menu items
    public MenuManager() {
        packageSet.add(new Package("Package A", 38.00,5, "1 Appertizer, 1 Main Course, 1 Beverage"));
        packageSet.add(new Package("Package B", 58.60,10, "1 Appertizer, 1 Main Course, 1 Beverage, 1 Dessert"));
        packageSet.add(new Package("Package C", 108.00,15, "1 Appertizer, 2 Main Course, 2 Beverage, 2 Dessert"));
        packageSet.add(new Package("Package D", 120.50,17, "3 Appertizer, 2 Main Course, 3 Beverage, 3 Dessert"));
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
        //scanner.nextLine();
        System.out.println("Please enter package price: RM ");
        double inputPackagePrice = scanner.nextDouble();

        System.out.println("Please enter menu item limit:  ");
        int menuItemLimit = scanner.nextInt();

        System.out.println("Please enter package descriptions: ");
        String inputPackageDescription = scanner.nextLine();
        // Clear buffer
        //scanner.nextLine();

        // Create new package information and add into the packageSet
        packageSet.add(new Package(inputPackageName, inputPackagePrice,menuItemLimit, inputPackageDescription));

    }

    public void removePackage() {
        System.out.println("\n");

        System.out.println("\t  REMOVE PACKAGE: ");
        System.out.println("\t=========================");

        // Step 1: Display the package
        System.out.println(displayPackage());

        scanner.nextLine();

        // Step 2: Let user to choose which package to remove
        System.out.print("Please enter Pacakge ID that you want remove: ");
        String inputRemovePackageID = scanner.nextLine();

        scanner.nextLine();

        // Use a temporay object to store that remove particular object
        Package temp = searchSpecificPackageByID(inputRemovePackageID);

        packageSet.remove(temp);
    }

    public void addNewMenuItem() {
        System.out.println("\n");
        System.out.println("\t    ADD NEW MENU ITEM: ");
        System.out.println("\t==========================");
        System.out.print("Please enter menu item name: ");
        String inputMenuItemName = scanner.nextLine();
        // Clear buffer
        // scanner.nextLine();
        System.out.print("Please enter menu item category: ");
        String inputMenuItemCategory = scanner.nextLine();

        System.out.print("Please enter menu item descriptions: ");
        String inputMenuItemDescription = scanner.nextLine();

        // Create new package information and add into the packageSet
        menuItemSet.add(new MenuItem(inputMenuItemCategory, inputMenuItemName, inputMenuItemDescription));
    }

    public void removeMenuItem() {
        System.out.println("\n");

        System.out.println("\t  REMOVE PACKAGE: ");
        System.out.println("\t=========================");

        // Step 1: Display the menu items
        System.out.println(displayMenuItems());

        scanner.nextLine();

        // Step 2: Let user to choose which menu item to remove
        System.out.print("Please enter Menu Item ID that you want remove: ");
        String inputRemoveMenuItemID = scanner.nextLine();

        scanner.nextLine();

        MenuItem temp = searchSpecificMenuItemByID(inputRemoveMenuItemID);

        menuItemSet.remove(temp);
    }

    // Assign menu item to package
    public void addMenuItemToPackage() {

        System.out.println("\n");

        // Display package first see the user want add menu items into which package
        System.out.println(displayPackage());
        System.out.println("\n");

        scanner.nextLine();
        System.out.println("Please enter the package ID that you want to add menu item: ");
        String inputPackageID = scanner.nextLine();

        scanner.nextLine();

        Package pckg = searchSpecificPackageByID(inputPackageID);

        // Search pacakge in existing package arraySet
        if (packageSet.contains(pckg)) {
            System.out.println(displayMenuItems());

            System.out.println("\n");
            System.out.println("Please enter the menu item ID you would like to add into the package: ");
            String inputMenuItemID = scanner.nextLine();

            MenuItem menuItem = searchSpecificMenuItemByID(inputMenuItemID);

            if (menuItemSet.contains(menuItem)) {
                pckg.addMenuItemToPackage(menuItem);
            } else {
                System.out.println("The package does not exist!");
            }
        } else {
            System.out.println("The package does not exist!");
        }

    }

    public void modifyPackage() {
        System.out.println("\n");
        System.out.println(displayPackage());
        System.out.println("\n");

        scanner.nextLine();
        System.out.println("Please enter the package ID that you want to add menu item: ");
        String inputPackageID = scanner.nextLine();

        scanner.nextLine();

        Package pckg = searchSpecificPackageByID(inputPackageID);

        System.out.println("\t  Modify Package");
        System.out.println("\t===================");

        System.out.println("1. Modify Pacakge Name");
        System.out.println("2. Modify Pacakge Price");
        System.out.println("3. Modify Pacakge Description");
        System.out.println("4. Modify All");
        System.out.println("Enter your choice: ");
        int inputModifyChoice = scanner.nextInt();

        scanner.nextLine();

        switch (inputModifyChoice) {
            case 1:
                System.out.println("Enetr new package name: ");
                String modifiedName = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enetr new package price: RM ");
                double modifiedPrice = scanner.nextDouble();
                break;
            case 3:
                System.out.println("Enetr new package description: ");
                String modifiedDescrip = scanner.nextLine();
                break;
            case 4:
                System.out.println("Enetr new package nane: ");
                double modifyName = scanner.nextDouble();

                System.out.println("Enetr new package price: RM ");
                double modifyPrice = scanner.nextDouble();

                System.out.println("Enetr new package description: ");
                String modifyDescrip = scanner.nextLine();
                break;
        }
        //pckg.modifyPackage(pckg.getPackageID(), modifiedName, modifiedPrice, modifiedDescp);

    }

    public String displayMenuItems() {
        String str = "";

        for (int i = 0; i < menuItemSet.getNumberOfEntries(); i++) {
            System.out.println("Menu Item" + (i + 1) + ":");
            str += menuItemSet.getElementAtPos(i).toString();
        }
        return str;
    }

    public String displayPackage() {
        String str = "";

        for (int i = 0; i < packageSet.getNumberOfEntries(); i++) {
            System.out.println("Pacakge" + (i + 1) + ":");
            str += packageSet.getElementAtPos(i).toString();
        }
        return str;
    }

    public Package searchSpecificPackageByID(String packageID) {

        SetInterface<Package> searchPackage = new ArraySet<Package>();
        Iterator iterator = searchPackage.iterator();

        while (iterator.hasNext()) {
            Package temp = (Package) iterator.next();
            if (temp.getPackageID().matches(packageID)) {
                return temp;
            }
        }
        return null;
    }

    public MenuItem searchSpecificMenuItemByID(String menuItemID) {

        SetInterface<MenuItem> searchMenuItem = new ArraySet<MenuItem>();
        Iterator iterator = searchMenuItem.iterator();

        while (iterator.hasNext()) {
            MenuItem temp = (MenuItem) iterator.next();
            if (temp.getMenuItemID().matches(menuItemID)) {
                return temp;
            }
        }
        return null;
    }
}
