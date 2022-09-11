
/**
 * Author: KONG ZHI LIN
 * Description: Sub Client class --> To implement operation on entity and ADT 
 * In this class will have 2 separate own array set but each package has its own specific menu items tahat he owns
 */

import java.util.Scanner;

import java.io.Serializable;

public class MenuManager implements Serializable {

    // Class Attributes
    // File
    private FileHandler<SetInterface<Package>> menuFile;
    private FileHandler<SetInterface<MenuItem>> menuItemFile;
    // Individual and separate package array set
    private SetInterface<Package> packageSet;
    // Individual and separate menu item array set
    private SetInterface<MenuItem> menuItemSet;
    private Scanner scanner;

    // Constructor --> Initialize the packages and menu items
    public MenuManager() {

        menuFile = new FileHandler<>("Menu.dat");
        menuItemFile = new FileHandler<>("MenuItem.dat");

        // for read from constructor
        packageSet = new ArraySet<>();
        menuItemSet = new ArraySet<>();
        scanner = new Scanner(System.in);

        packageSet.add(new Package("Package A", 38.00, 3, "1 Appertizer, 1 Main Course, 1 Beverage"));
        packageSet.add(new Package("Package B", 58.60, 4, "1 Appertizer, 1 Main Course, 1 Beverage, 1 Dessert"));
        packageSet.add(new Package("Package C", 108.00, 10, "3 Appertizer, 2 Main Course, 2 Beverage, 2 Dessert"));
        packageSet.add(new Package("Package D", 120.50, 15, "3 Appertizer, 4 Main Course, 3 Beverage, 5 Dessert"));
        packageSet.add(new Package("Package E", 98.00, 7, "2 Appertizer, 3 Main Course, 2 Beverage"));
        packageSet.add(new Package("Package F", 10.50, 1, "1 Main Course"));

        menuItemSet.add(new MenuItem("Appertizer", "French Fries", "Hand cut wedges of Yukan Cold potatoes."));
        menuItemSet.add(new MenuItem("Main Course", "Spaghetti Marinara", "Spaghetti with seafood and tomato sauce."));
        menuItemSet.add(new MenuItem("Beverage", "Long Black", "2 shots of espresso and hot water."));
        menuItemSet.add(new MenuItem("Dessert", "Lime Pie", "Targy custard with graham crocker crust."));
        menuItemSet.add(new MenuItem("Beverage", "Ice Lemon Tea", "Tea with few slices of lemon."));
        menuItemSet.add(new MenuItem("Main Course", "Grilled chicken Chop",
                "Chicken chop with grilled and 2 flavor of sauce (Mushroom and black pepper)."));

        packageSet.getElementAtPos(0).addMenuItemToPackage(menuItemSet.getElementAtPos(0));
        packageSet.getElementAtPos(0).addMenuItemToPackage(menuItemSet.getElementAtPos(2));
        packageSet.getElementAtPos(1).addMenuItemToPackage(menuItemSet.getElementAtPos(3));
        packageSet.getElementAtPos(1).addMenuItemToPackage(menuItemSet.getElementAtPos(1));
        packageSet.getElementAtPos(1).addMenuItemToPackage(menuItemSet.getElementAtPos(2));
        packageSet.getElementAtPos(4).addMenuItemToPackage(menuItemSet.getElementAtPos(4));
        packageSet.getElementAtPos(3).addMenuItemToPackage(menuItemSet.getElementAtPos(0));
        packageSet.getElementAtPos(3).addMenuItemToPackage(menuItemSet.getElementAtPos(3));

        // write into file
        menuFile.write(packageSet);
        menuItemFile.write(menuItemSet);

    }

    public void menuModule() {
        int choice;

        do {
            System.out.println("\t   Menu Module");
            System.out.println("\t==================");
            System.out.println("1. Add New Package");
            System.out.println("2. Remove Package");
            System.out.println("3. Display Package");
            System.out.println("4. Add New Menu Item");
            System.out.println("5. Remove Menu Item");
            System.out.println("6. Display Menu Item");
            System.out.println("7. Add Menu Item into Package");
            System.out.println("8. Remove Menu Item from Package");
            System.out.println("9. Modify Package");
            System.out.println("10. Modify Menu Item");
            System.out.println("11. Search Package");
            System.out.print("Enter your choice(-1 exit): ");
            choice = scanner.nextInt();
            scanner.nextLine();
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
                    displayMenuItems();
                    break;
                case 7:
                    continueAddMenuItemToPackage();
                    break;
                case 8:
                    removeMenuItemFromPackage();
                    break;
                case 9:
                    modifyPackage();
                    break;
                case 10:
                    modifyMenuItem();
                    break;
                case 11:
                    searchPackage();
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
            menuFile.write(packageSet);
            menuItemFile.write(menuItemSet);
        } while (choice != -1);
    }

    public void addNewPackage() {

        char continueChoice;
        double inputPackagePrice;

        do {
            System.out.println("\n");
            System.out.println("\t  ADD NEW PACKAGE: ");
            System.out.println("\t=======================");
            System.out.print("Please enter package name: ");
            String inputPackageName = scanner.nextLine();
            // Clear buffer
            // scanner.nextLine();

            do {
                System.out.print("Please enter package price: RM ");
                inputPackagePrice = scanner.nextDouble();

                if (inputPackagePrice <= 0.00) {
                    System.out.println("Price must be more than RM 0 ! Please enter again!");
                }
            } while (inputPackagePrice <= 0);

            System.out.print("Please enter menu item limit:  ");
            int menuItemLimit = scanner.nextInt();

            // Clear buffer
            scanner.nextLine();

            System.out.print("Please enter package description: ");
            String inputPackageDescription = scanner.nextLine();

            if (checkPackageName(inputPackageName)) {
                System.out.println("Failed to add! This Pacakge had exist!");
            } else {
                Package pckg = new Package(inputPackageName, inputPackagePrice, menuItemLimit, inputPackageDescription);
                char choice;
                do {
                    System.out.print("Do you want to add menu item(s) inside the package (Y: yes N: no)? ");
                    choice = scanner.nextLine().charAt(0);

                    switch (Character.toUpperCase(choice)) {
                        case 'Y':
                            pckg = addMenuItemToPackage(pckg);
                            packageSet.add(pckg);
                            break;
                        case 'N':
                            packageSet.add(pckg);
                            break;
                        default:
                            System.out.println("Invalid input!!");
                            break;
                    }
                } while (Character.toUpperCase(choice) != 'N');
                menuFile.write(packageSet);
                System.out.println("You had added successfully!!");
            }

            do {
                System.out.print("\nDo you want to continue add new package (Y = yes N = no): ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');
    }

    public void removePackage() {

        char continueChoice;

        do {
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

            if (temp != null) {
                packageSet.remove(temp);
                System.out.println("You had remove successfully!");
            } else {
                System.out.println("Removed Failed!");
            }

            do {
                System.out.print("\nDo you want to continue remove package(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');
    }

    public void addNewMenuItem() {

        char continueChoice;

        do {
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

            if (checkMenuItemName(inputMenuItemName)) {
                System.out.println("Failed to add! This menu item had exist!");
            } else {
                // Create new package information and add into the packageSet
                menuItemSet.add(new MenuItem(inputMenuItemCategory, inputMenuItemName, inputMenuItemDescription));
                System.out.println("You had added successfully!");
            }

            do {
                System.out.print("\nDo you want to continue add menu item(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');
    }

    public void removeMenuItem() {

        char continueChoice;

        do {
            System.out.println("\n");

            System.out.println("\t  REMOVE MENU ITEM: ");
            System.out.println("\t=========================");

            // Step 1: Display the menu items
            displayMenuItems();

            // Step 2: Let user to choose which menu item to remove
            System.out.print("Please enter Menu Item ID that you want remove: ");
            String inputRemoveMenuItemID = scanner.nextLine();

            MenuItem temp = searchSpecificMenuItemByID(inputRemoveMenuItemID);

            if (temp != null) {
                menuItemSet.remove(temp);
                System.out.println("You had remove successfully!");
            } else {
                System.out.println("Remove Failed!");
            }

            do {
                System.out.print("\nDo you want to continue remove menu item(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');
    }

    public void continueAddMenuItemToPackage() {
        Package pckg = null;
        System.out.println("\n");

        // Display package first see the user want add menu items into which package
        displayPackage();
        System.out.println("\n");

        do {
            System.out.print("Please enter the package ID that you want to add menu item: ");
            String inputPackageID = scanner.nextLine();
            pckg = searchSpecificPackageByID(inputPackageID);

            if (!packageSet.contains(pckg)) {
                System.out.println("Invalid Package ID!");
            }

        } while (!packageSet.contains(pckg));

        addMenuItemToPackage(pckg);
    }

    // Assign menu item to package
    private Package addMenuItemToPackage(Package pckg) {
        // Check the limit of the menu item in the package
        if (pckg.getAllPackageMenuItems().getNumberOfEntries() > pckg.getMenuItemLimit()) {
            System.out.println("Cannot add menu item into the package since reached the volume limit!");
        } else {
            System.out.println("\n");
            displayMenuItems();
            System.out.print("\nPlease enter the menu item ID you would like to add into the package: ");
            String inputMenuItemID = scanner.nextLine();

            MenuItem menuItem = searchSpecificMenuItemByID(inputMenuItemID);

            if (menuItemSet.contains(menuItem)) {
                if (pckg.getAllPackageMenuItems().contains(menuItem)) {
                    System.out.println("Cannot add again because this menu item had inside the package!");
                    // System.out.println("Please select again the menu item: ");
                } else {
                    pckg.addMenuItemToPackage(menuItem);
                }
            } else {
                System.out.println("The menu item does not exist!");
            }
        }

        return pckg;

    }

    public void removeMenuItemFromPackage() {

        String inputMenuItemID;
        String inputPackageID;

        char continueChoice;

        do {
            displayPackage();
            System.out.print("Please enter package ID that you would like to remove menu item: ");
            inputPackageID = scanner.nextLine();

            Package temp = searchSpecificPackageByID(inputPackageID);

            if (temp.getAllPackageMenuItems() != null) {
                temp.toString();
                System.out.print("Please enter menu item ID that you would like remove from this package: ");
                inputMenuItemID = scanner.nextLine();
                temp = searchMenuItemInPackageByID(inputMenuItemID, temp);
                temp.toString();
            } else {
                System.out.println("There do not have menu item inside the package!");
            }
            do {
                System.out.print("\nDo you want to continue remove menu item from package(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');

    }

    private Package searchMenuItemInPackageByID(String inputMenuItemID, Package pckg) {

        MenuItem menuItem;

        for (int i = 0; i < pckg.getAllPackageMenuItems().getNumberOfEntries(); i++) {
            if (pckg.getAllPackageMenuItems().getElementAtPos(i).getMenuItemID().equals(inputMenuItemID)) {
                menuItem = pckg.getAllPackageMenuItems().getElementAtPos(i);
                pckg.removeMenuItemFromPackage(menuItem);
                return pckg;
            }
        }
        return null;

    }

    public void modifyPackage() {

        char continueChoice;
        double modifyPrice, modifiedPrice;

        do {
            System.out.println("\n");
            displayPackage();
            System.out.println("\n");

            System.out.print("Please enter the package ID that you want to modify: ");
            String inputPackageID = scanner.nextLine();

            Package pckg = searchSpecificPackageByID(inputPackageID);

            displayModifyPackageTable();
            int inputModifyChoice = scanner.nextInt();

            scanner.nextLine();

            switch (inputModifyChoice) {
                case 1:
                    System.out.print("Enter new package name: ");
                    String modifiedName = scanner.nextLine();
                    pckg.setPackageName(modifiedName);
                    break;
                case 2:
                    do {
                        System.out.print("Enter new package price: RM ");
                        modifiedPrice = scanner.nextDouble();
                        scanner.nextLine();
                        if (modifiedPrice <= 0) {
                            System.out.println("Price must be more than RM 0 ! Please enter again!");
                        }
                    } while (modifiedPrice <= 0);
                    pckg.setPackagePrice(modifiedPrice);
                    break;
                case 3:
                    System.out.print("Enter new package description: ");
                    String modifiedDescrip = scanner.nextLine();
                    pckg.setPackageDescription(modifiedDescrip);
                    break;
                case 4:
                    System.out.print("Enter new menu item limit: ");
                    int modifiedLimit = scanner.nextInt();
                    scanner.nextLine();
                    pckg.setMenuItemLimit(modifiedLimit);
                    break;
                case 5:
                    System.out.print("Enter new package name: ");
                    String modifyName = scanner.nextLine();
                    pckg.setPackageName(modifyName);

                    do {
                        System.out.print("Enter new package price: RM ");
                        modifyPrice = scanner.nextDouble();
                        if (modifyPrice <= 0) {
                            System.out.println("Price must be more than RM 0 ! Please enter again!");
                        }
                    } while (modifyPrice <= 0);
                    pckg.setPackagePrice(modifyPrice);

                    System.out.print("Enter new menu item limit: ");
                    int modifyLimit = scanner.nextInt();
                    pckg.setMenuItemLimit(modifyLimit);

                    scanner.nextLine();

                    System.out.print("Enter new package description: ");
                    String modifyDescrip = scanner.nextLine();
                    pckg.setPackageDescription(modifyDescrip);

                    break;
            }
            do {
                System.out.print("\nDo you want to continue modify package(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');

    }

    public void modifyMenuItem() {

        char continueChoice;

        do {
            System.out.println("\n");
            displayMenuItems();
            System.out.println("\n");

            System.out.print("Please enter the menu item ID that you want to modify:");
            String inputMenuItemID = scanner.nextLine();

            MenuItem menuItem = searchSpecificMenuItemByID(inputMenuItemID);

            displayModifyMenuItemTable();
            int inputModifyChoice = scanner.nextInt();

            scanner.nextLine();

            switch (inputModifyChoice) {
                case 1:
                    System.out.print("Enter new menu item name: ");
                    String modifiedName = scanner.nextLine();
                    menuItem.setMenuItemName(modifiedName);
                    break;
                case 2:
                    System.out.print("Enter new category name: ");
                    String modifiedCategory = scanner.nextLine();
                    menuItem.setMenuItemCategory(modifiedCategory);
                    break;
                case 3:
                    System.out.print("Enter new menu item description: ");
                    String modifiedDescrip = scanner.nextLine();
                    menuItem.setMenuItemDescription(modifiedDescrip);
                    break;
                case 4:
                    System.out.print("Enter new menu item name: ");
                    String modifyName = scanner.nextLine();
                    menuItem.setMenuItemName(modifyName);

                    System.out.print("Enter new category name: ");
                    String modifyCategory = scanner.nextLine();
                    menuItem.setMenuItemCategory(modifyCategory);

                    System.out.print("Enter new menu item description: ");
                    String modifyDescrip = scanner.nextLine();
                    menuItem.setMenuItemDescription(modifyDescrip);
                    break;
            }
            do {
                System.out.print("\nDo you want to continue modify menu item(Y = yes N = no)? ");
                continueChoice = scanner.nextLine().charAt(0);
                if (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N') {
                    System.out.println("You had key in invalid input. Please key in again!");
                }
            } while (Character.toUpperCase(continueChoice) != 'Y' && Character.toUpperCase(continueChoice) != 'N');
        } while (Character.toUpperCase(continueChoice) != 'N');

    }

    private void displayModifyPackageTable() {
        System.out.println("\t  MODIFY PACKAGE: ");
        System.out.println("\t===================");

        System.out.println("1. Modify Pacakge Name");
        System.out.println("2. Modify Pacakge Price");
        System.out.println("3. Modify Pacakge Description");
        System.out.println("4. Modify Menu Item Limit");
        System.out.println("5. Modify All");
        System.out.print("Enter your choice: ");
    }

    private void displayModifyMenuItemTable() {
        System.out.println("\t  MODIFY MENU ITEM: ");
        System.out.println("\t=======================");

        System.out.println("1. Modify Menu Item Name");
        System.out.println("2. Modify Menu Item Category");
        System.out.println("3. Modify Menu Item Description");
        System.out.println("4. Modify All");
        System.out.print("Enter your choice: ");
    }

    public void displayMenuItems() {
        System.out.println("\t  MENU ITEMS DETAILS: ");
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
        System.out.println("\t  PACKAGE DETAILS: ");
        System.out.println("\t======================");

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

    // havent finish
    public void searchPackage() {

        double minInputPrice, maxInputPrice;
        String packageName, menuItemName;
        Package pckg;

        displaySearchTable();
        int choice;
        do {
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1:
                scanner.nextLine();
                System.out.print("Enter Package Name: ");
                packageName = scanner.nextLine();
                pckg = searchPackageByName(packageName);
                System.out.println("\n");
                if (pckg != null) {
                    System.out.println("Package had founded!!\n");
                    System.out.println(pckg.toString());
                } else {
                    System.out.println("Cannot found package!");
                }
                break;
            case 2:
                do {
                    System.out.print("Enetr the price range (Minimum): RM ");
                    minInputPrice = scanner.nextDouble();

                    do {
                        System.out.print("Enetr the price range (Maximum): RM ");
                        maxInputPrice = scanner.nextDouble();
                        if (minInputPrice > maxInputPrice) {
                            System.out.println("Maximum price range must bigger than the minimum price range!");
                        }
                    } while (minInputPrice > maxInputPrice);
                    if (maxInputPrice <= 0 && minInputPrice <= 0) {
                        System.out.println("Price must more than RM 0 ! Please enter agian. ");
                    }
                } while (maxInputPrice <= 0 && minInputPrice <= 0);
                System.out.println("\n");
                searchPackageByPrice(maxInputPrice, minInputPrice);
                break;
            case 3:
                scanner.nextLine();
                System.out.print("Enter menu item name: ");
                menuItemName = scanner.nextLine();
                searchMenuItemByNameInPackage(menuItemName);
                break;
            case 4:
                System.out.println("\n");
                do {
                    System.out.print("Enetr the price range (Minimum): RM ");
                    minInputPrice = scanner.nextDouble();

                    do {
                        System.out.print("Enetr the price range (Maximum): RM ");
                        maxInputPrice = scanner.nextDouble();
                        if (minInputPrice > maxInputPrice) {
                            System.out.println("Maximum price range must bigger than the minimum price range!");
                        }
                    } while (minInputPrice > maxInputPrice);
                    if (maxInputPrice <= 0 && minInputPrice <= 0) {
                        System.out.println("Price must more than RM 0 ! Please enter agian. ");
                    }
                } while (maxInputPrice <= 0 && minInputPrice <= 0);

                System.out.print("Enter the menu item limit (Minimum): ");
                int minMenuItemLimit = scanner.nextInt();

                System.out.print("Enter the menu item limit (Maximum): ");
                int maxMenuItemLimit = scanner.nextInt();

                scanner.nextLine();
                System.out.print("Enter the menu item category: ");
                char inputMenuItemCategory = scanner.nextLine().charAt(0);

                filterPackage(maxInputPrice, minInputPrice, maxMenuItemLimit, minMenuItemLimit, inputMenuItemCategory);
                System.out.println("\n");

                break;

            default:
                System.out.println("Invalid input!");

        }

    }

    private void displaySearchTable() {

        System.out.println("\n");
        System.out.println("\t  Search Package: ");
        System.out.println("\t===================");

        System.out.println("1. Search By Package Name");
        System.out.println("2. Search By Package Price Range");
        System.out.println("3. Search By Menu Item Name");
        System.out.println("4. Filter Suitable Package");
        System.out.print("Enter your choice (1 - 4): ");
    }

    private Package searchSpecificPackageByID(String packageID) {
        for (Package pckg : packageSet) {
            if (pckg.getPackageID().equalsIgnoreCase(packageID)) {
                return pckg;
            }
        }
        System.out.println("Package do not exist!");
        return null;
    }

    private Package searchPackageByName(String packageName) {
        for (Package pckg : packageSet) {
            if (pckg.getPackageName().equalsIgnoreCase(packageName)) {
                return pckg;
            }
        }
        return null;
    }

    private boolean checkMenuItemName(String menuItemName) {
        for (MenuItem menuItem : menuItemSet) {
            if (menuItem.getMenuItemName().equalsIgnoreCase(menuItemName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPackageName(String packageName) {
        for (Package pckg : packageSet) {
            if (pckg.getPackageName().equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    private void searchPackageByPrice(double maxInputPrice, double minInputPrice) {

        int totalNumber = 0;
        for (Package pckg : packageSet) {
            if (pckg.getPackagePrice() <= maxInputPrice && pckg.getPackagePrice() >= minInputPrice) {
                totalNumber++;
                System.out.println(pckg.toString());

            }
        }

        System.out.println("Total package found: " + totalNumber);
    }

    private void searchMenuItemByNameInPackage(String menuItemName) {
        int totalNumber = 0;

        for (int i = 0; i < packageSet.getNumberOfEntries(); i++) {
            boolean searchFlag = false;
            for (int j = 0; j < packageSet.getElementAtPos(i).getAllPackageMenuItems().getNumberOfEntries(); j++) {
                if (packageSet.getElementAtPos(i).getAllPackageMenuItems().getElementAtPos(j).getMenuItemName()
                        .equals(menuItemName)) {
                    searchFlag = true;
                    totalNumber++;
                }
            }
            if (searchFlag) {
                System.out.println(packageSet.getElementAtPos(i).toString());
            }
        }

        System.out.println("Total package found: " + totalNumber);

    }

    private MenuItem searchSpecificMenuItemByID(String menuItemID) {
        for (MenuItem menuItem : menuItemSet) {
            if (menuItem.getMenuItemID().equalsIgnoreCase(menuItemID)) {
                return menuItem;
            }
        }
        System.out.println("Menu Item do not exist!");
        return null;
    }

    public void filterPackage(double maxInputPrice, double minInputPrice, int maxMenuItemLimit, int minMenuItemLimit,
            char menuItemCategory) {

        int totalNumber = 0;
        boolean searchFlag = false;

        for (int i = 0; i < packageSet.getNumberOfEntries(); i++) {
            if (packageSet.getElementAtPos(i).getAllPackageMenuItems() != null) {
                if (packageSet.getElementAtPos(i).getPackagePrice() <= maxInputPrice
                        && packageSet.getElementAtPos(i).getPackagePrice() >= minInputPrice) {
                    if (packageSet.getElementAtPos(i).getMenuItemLimit() <= maxMenuItemLimit
                            && packageSet.getElementAtPos(i).getMenuItemLimit() >= minMenuItemLimit) {
                        for (int j = 0; j < packageSet.getElementAtPos(i).getAllPackageMenuItems()
                                .getNumberOfEntries(); j++) {
                            if (packageSet.getElementAtPos(i).getAllPackageMenuItems().getElementAtPos(j)
                                    .getMenuItemCategory().charAt(0) == menuItemCategory) {
                                searchFlag = true;
                                totalNumber++;
                            }
                        }
                    } else
                        searchFlag = false;
                } else
                    searchFlag = false;
            } else
                searchFlag = false;
            if (searchFlag) {
                System.out.println(packageSet.getElementAtPos(i).toString());
            }
        }
        System.out.println("Total package found: " + totalNumber);
        System.out.println("\n");
    }

}
