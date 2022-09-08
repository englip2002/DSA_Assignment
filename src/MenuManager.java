
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
    // File
    private FileHandler menuFile;
    // Individual and separate package array set
    private SetInterface<Package> packageSet;
    // Individual and separate menu item array set
    private SetInterface<MenuItem> menuItemSet;
    private Scanner scanner;

    // Constructor --> Initialize the packages and menu items
    public MenuManager() {

        menuFile = new FileHandler("Menu.dat");
        packageSet = new ArraySet<Package>();
        menuItemSet = new ArraySet<MenuItem>();
        scanner = new Scanner(System.in);

        packageSet.add(new Package("Package A", 38.00, 3, "1 Appertizer, 1 Main Course, 1 Beverage"));
        packageSet.add(new Package("Package B", 58.60, 4, "1 Appertizer, 1 Main Course, 1 Beverage, 1 Dessert"));
        packageSet.add(new Package("Package C", 108.00, 10, "3 Appertizer, 2 Main Course, 2 Beverage, 2 Dessert"));
        packageSet.add(new Package("Package D", 120.50, 15, "3 Appertizer, 4 Main Course, 3 Beverage, 5 Dessert"));
        menuItemSet.add(new MenuItem("Appertizer", "French Fries", "Hand cut wedges of Yukan Cold potatoes."));
        menuItemSet.add(new MenuItem("Main Course", "Spaghetti Marinara", "Spaghetti with seafood and tomato sauce."));
        menuItemSet.add(new MenuItem("Beverage", "Long Black", "2 shots of espresso and hot water."));
        menuItemSet.add(new MenuItem("Dessert", "Lime Pie", "Targy custard with graham crocker crust."));

        menuFile.write(packageSet);

    }

    public void menuModule() {
        int choice;

        do {
            System.out.println("\t  Menu Module");
            System.out.println("\t=================");
            System.out.println("1. Add New Package");
            System.out.println("2. Remove Package");
            System.out.println("3. Display Package");
            System.out.println("4. Add New Menu Item");
            System.out.println("5. Remove Menu Item");
            System.out.println("6. Display Package");
            System.out.println("7. Add Menu Item into Package");
            System.out.println("8. Modify Package");
            System.out.println("9. Search Package");
            System.out.print("Enter your choice(1 - 8): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewPackage();
                    break;
                case 2:
                    removePackage();
                    break;
                case 3:
                    displayPackage();
                    break;
                case 4:
                    addNewMenuItem();
                    break;
                case 5:
                    removeMenuItem();
                    break;
                case 6:
                    displayPackage();
                    break;
                case 7:
                    continueAddMenuItemToPackage();
                    break;
                case 8:
                    modifyPackage();
                    break;
                case 9:
                    searchPackage();
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice != -1);
    }

    public void addNewPackage() {
        System.out.println("\n");
        System.out.println("\t  ADD NEW PACKAGE: ");
        System.out.println("\t=======================");
        System.out.print("Please enter package name: ");
        String inputPackageName = scanner.nextLine();
        // Clear buffer
        // scanner.nextLine();
        System.out.print("Please enter package price: RM ");
        double inputPackagePrice = scanner.nextDouble();

        System.out.print("Please enter menu item limit:  ");
        int menuItemLimit = scanner.nextInt();

        System.out.print("Please enter package descriptions: ");
        String inputPackageDescription = scanner.nextLine();
        // Clear buffer
        scanner.nextLine();

        Package pckg = new Package(inputPackageName, inputPackagePrice, menuItemLimit, inputPackageDescription);

        char choice;
        do {
            System.out.print("Do you want to add menu item(s) inside the package (Y: yes N: no)? ");
            choice = scanner.nextLine().charAt(0);

            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    pckg = addMenuItemToPackage(pckg);
                    break;
                case 'N':
                    break;
                default:
                    System.out.println("Invalid input!!");
                    break;
            }
        } while (Character.toUpperCase(choice) != 'N');

        packageSet.add(pckg);

        System.out.println("You had added successfully!!");

        displayPackage();
    }

    public void removePackage() {
        System.out.println("\n");

        System.out.println("\t  REMOVE PACKAGE: ");
        System.out.println("\t======================");

        // Step 1: Display the package
        displayPackage();

        // Step 2: Let user to choose which package to remove
        System.out.print("Please enter Pacakge ID that you want remove: ");
        String inputRemovePackageID = scanner.nextLine();

        // Use a temporay object to store that remove particular object
        Package temp = searchSpecificPackageByID(inputRemovePackageID);
        //System.out.println(temp);

        packageSet.remove(temp);

        displayPackage();
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
        displayMenuItems();

        scanner.nextLine();

        // Step 2: Let user to choose which menu item to remove
        System.out.print("Please enter Menu Item ID that you want remove: ");
        String inputRemoveMenuItemID = scanner.nextLine();

        scanner.nextLine();

        MenuItem temp = searchSpecificMenuItemByID(inputRemoveMenuItemID);

        menuItemSet.remove(temp);
    }

    public void continueAddMenuItemToPackage() {
        System.out.println("\n");

        // Display package first see the user want add menu items into which package
        displayPackage();
        System.out.println("\n");

        scanner.nextLine();
        System.out.println("Please enter the package ID that you want to add menu item: ");
        String inputPackageID = scanner.nextLine();

        scanner.nextLine();

        Package pckg = searchSpecificPackageByID(inputPackageID);

        addMenuItemToPackage(pckg);
    }

    // Assign menu item to package
    private Package addMenuItemToPackage(Package pckg) {

        // Search pacakge in existing package arraySet
        if (packageSet.contains(pckg)) {

            // Check the limit of the menu item in the package
            if (pckg.getAllMenuPackage().getNumberOfEntries() > pckg.getMenuItemLimit()) {
                System.out.println("Cannot add menu item into the package since reached the volume limit!");
            } else {
                System.out.println("\n");
                displayMenuItems();
                System.out.println("\nPlease enter the menu item ID you would like to add into the package: ");
                String inputMenuItemID = scanner.nextLine();

                MenuItem menuItem = searchSpecificMenuItemByID(inputMenuItemID);

                if (menuItemSet.contains(menuItem)) {
                    pckg.addMenuItemToPackage(menuItem);
                } else {
                    System.out.println("The package does not exist!");
                }
            }
        } else {
            System.out.println("The package does not exist!");

        }

        return pckg;

    }

    public void modifyPackage() {
        System.out.println("\n");
        displayPackage();
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
                System.out.println("Enter new package name: ");
                String modifiedName = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enter new package price: RM ");
                double modifiedPrice = scanner.nextDouble();
                break;
            case 3:
                System.out.println("Enter new package description: ");
                String modifiedDescrip = scanner.nextLine();
                break;
            case 4:
                System.out.println("Enter new package nane: ");
                double modifyName = scanner.nextDouble();

                System.out.println("Enter new package price: RM ");
                double modifyPrice = scanner.nextDouble();

                System.out.println("Enter new package description: ");
                String modifyDescrip = scanner.nextLine();
                break;
        }
        // pckg.modifyPackage(pckg.getPackageID(), modifiedName, modifiedPrice,
        // modifiedDescp);

    }

    public void displayMenuItems() {
        System.out.println("\t  MENU ITEMS DETAILS");
        System.out.println("\t=========================");

        if (menuItemSet != null) {
            for (int i = 0; i < menuItemSet.getNumberOfEntries(); i++) {
                System.out.println((i + 1) + ":");
                System.out.println(menuItemSet.getElementAtPos(i).toString());
            }
            System.out.println("Total number of menu items: " + menuItemSet.getNumberOfEntries());
        } else {
            System.out.println("No menu item exist!");
        }
    }

    public void displayPackage() {
        System.out.println("\t  PACKAGE DETAILS");
        System.out.println("\t======================");

        packageSet = (SetInterface<Package>) menuFile.read();

        if (packageSet != null) {

            for (int i = 0; i < packageSet.getNumberOfEntries(); i++) {
                System.out.println((i + 1) + ":");
                System.out.println(packageSet.getElementAtPos(i).toString());
            }
            System.out.println("\nTotal number of package: " + packageSet.getNumberOfEntries());
        } else {
            System.out.println("No Package exist!");
        }
    }

    public void searchPackage() {

    }

    public Package searchSpecificPackageByID(String packageID) {

        int i = 0;
        for (Package pckg : packageSet) {
            i++;
            if (pckg.getPackageID().equals(packageID)) {
                return pckg;
            }
        }

        // while (iterator.hasNext()) {
        //     Package temp = (Package) iterator.next();
        //     if (temp.getPackageID().matches(packageID)) { // equals not match (match is use regex)
        //         return temp;
        //     }
        // }
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
