

/**
 *
 * @author Thong So Xue
 */

/*
Client class of Catering Module

*/

import java.util.Scanner;

public class CateringManager {
    // Waiting queue: 
    // - New dish ordered will be placed in this queue first
    // - Dishes need to be removed from waiting queue 
    //   and placed at a kitchen's cooking queue before serving
    private QueueInterface<Dish> waitingQueue;
    
    // Kitchens list: 
    // - List of kitchens in the system, each kitchen have its own cooking queue
    private ListInterface<Kitchen> kitchens;
    
    // Menu set: 
    // - Contains dummy data for menu items, used in adding new dish
    private SetInterface<MenuItem> menuItemSet;
    
    // Scanner object
    private Scanner scanner = new Scanner(System.in);
    
    // File handler
    private FileHandler waitingQueueFile = new FileHandler("catering_waitingQueue.dat");
    private FileHandler kitchensFile = new FileHandler("catering_kitchens.dat");
    
    // Constructor
    public CateringManager() {
        // Load data from file
        try {
            readFromFile();
        }
        catch (Exception e) {
            waitingQueue = new LinkedQueue<Dish>();
            kitchens = new LinkedList<Kitchen>();
        }
        
        // Initialize menu
        menuItemSet = new ArraySet<MenuItem>();
        initializeMenu();
    }    
    
    
    // ================
    // PUBLIC METHODS
    
    // [ Option 1 ]
    // Add dish to waiting queue
    public void addDishToWaitingQueue() {
        displayMenuItems();
        
        int choice = 0;
        while (choice == 0) {
            System.out.print("Select a dish to add to waiting queue: "); 
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                choice = 0;
            }
            
            if (choice == 0) {
                System.out.println("Invalid input, please re-enter. ");
                enterToContinue();
            }
        }
        Dish selectedDish = new Dish(menuItemSet.getElementAtPos(choice - 1));
        waitingQueue.enqueue(selectedDish);
        System.out.println("\nDish added to the waiting queue!");
        System.out.println("Your dish ID is " + selectedDish.getId() + ". ");
    }
    
    // [ Option 2 ]
    // Assign next dish in queue to a kitchen
    public void assignNextDishToKitchen() {
        // Return to menu if the waiting queue or the kitchen list is empty
        if (waitingQueue.isEmpty()) {
            System.out.println("There are no dishes in the waiting queue!");
            return;
        }
        else if (kitchens.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Display the next dish in the waiting queue
        waitingQueueTableHeading();
        System.out.println(waitingQueue.getFront());
        System.out.println("This is the next dish to be assigned to a kitchen. ");
        enterToContinue();
        
        while (true) {
            // Prompt to choose a kitchen to cook this next dish
            int chosenIndex = searchKitchenById("Enter the ID of the kitchen to cook this dish: ");
            Kitchen chosenKitchen = kitchens.getEntry(chosenIndex);
            
            // If the chosen kitchen is full, 
            // Prompt to enter another kitchen
            if (chosenKitchen.isCookingQueueFull()) {
                System.out.println("That kitchen has already reached max capacity! ");
                System.out.println("Please choose another kitchen. ");
                enterToContinue();
            }
            // Else, assign the next dish in waiting queue to the selected kitchen
            else {
                chosenKitchen.queueDish(waitingQueue.dequeue());
                System.out.println("Dish assigned to this kitchen successfully. ");
                return;
            }
        }
    }
    
    // [ Option 3 ]
    // Serve a dish from a kitchen
    public void serveDishFromKitchen() {
        // Return to menu if the kitchen list is empty
        if (kitchens.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Prompt to choose a kitchen to serve its next dish
        int chosenIndex = searchKitchenById("Enter the ID of the kitchen to serve its next dish: ");
        Kitchen chosenKitchen = kitchens.getEntry(chosenIndex);
        
        // Display the next dish to be served
        waitingQueueTableHeading();
        System.out.println(chosenKitchen.getNextDish());

        // Double confirm for dequeueing
        System.out.println("Are you sure you want to serve this dish? ");
        System.out.print("Press enter to confirm, or 'N' to cancel. ");
        String inputConfirm = scanner.nextLine();
        if (inputConfirm.toUpperCase().equals("N")) {
            System.out.println("Operation cancelled. ");
        }
        else {
            chosenKitchen.serveTopDish();
            System.out.println("The next dish is now served. ");
            enterToContinue();
        }
    }
    
    // [ Option 4 ]
    // Manage Kitchen details (Add, edit, delete kitchen)
    public void manageKitchenDetails() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\t    --- Manage Kitchen Details ---    ");
            System.out.println("\t==============================");
            System.out.println("\t  1) Add new kitchen");
            System.out.println("\t  2) Edit existing kitchen details");
            System.out.println("\t  3) Remove a kitchen");
            System.out.println("\t  4) Return to previous menu");
            System.out.println("\t==============================");
            System.out.print("\t Choose an operation: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                choice = 0;
            }
            
            System.out.println();
            
            switch(choice) {
                case 1:
                    addKitchen();
                    enterToContinue();
                    break;
                case 2:
                    editKitchens();
                    enterToContinue();
                    break;
                case 3:
                    removeKitchen();
                    enterToContinue();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid input, please re-enter. ");
                    enterToContinue();
                    break;
            }
        }
        
    }
    
    // [ Option 5 ]
    // Display waiting queue
    public void displayWaitingQueue() {
        System.out.println("\t\t--- Waiting Queue ---");
        waitingQueueTableHeading();
        for (Dish each: waitingQueue) {
            System.out.println(each);
        }
    }
    
    // [ Option 6 ]
    // Display list of kitchens
    public void displayKitchens() {
        System.out.println("\t\t--- List of Kitchens ---");
        kitchensListTableHeading();
        for (Kitchen each: kitchens) {
            System.out.println(each);
        }
    }
    
    // [ Option 7 ]
    // Search for a dish in queue (Both waiting queue and kitchen's cooking queue)
    public void searchDishInQueue() {
        // Return immediately if there are no dishes in queue        
        if (waitingQueue.isEmpty() && !kitchens.isEmpty() && isAllKitchenQueuesEmpty()) {
            System.out.println("There are no dishes in queue!");
            return;
        }
        
        // Prompt user to enter dish ID
        boolean inputValid = false;
        String dishID;
        do {
            System.out.print("Enter the Dish ID you wish to search for: ");
            dishID = scanner.nextLine().toUpperCase();
            if (dishID.length() == 4 && dishID.charAt(0) == 'D') {
                inputValid = true;
            }
        }while (!inputValid);
        
        // Search in waiting queue
        boolean foundInWaitingQueue = false;
        if (!waitingQueue.isEmpty()) {
            // Loop the queue to find the dish's position in the waiting queue
            int pos = 0;
            for (Dish each: waitingQueue) {
                // If the dish is found in the waiting queue, 
                if (each.getId().equals(dishID)) {
                    foundInWaitingQueue = true;
                    
                    // Display dish info
                    waitingQueueTableHeading();
                    System.out.println(each + "\n");
                    
                    // Display graph of waiting queue
                    displayGraph(pos, waitingQueue.size(), waitingQueue.size());
                    System.out.println("\nYour dish is still in the waiting queue, at the " + cardinalToOrdinal(pos + 1) + " position. ");
                    
                    // Break the loop if the dish is found
                    break;
                }
                pos++;
            }
        }
        
        // Search in all the waiting queues
        if (!isAllKitchenQueuesEmpty()) {
            for (Kitchen k : kitchens) {
                int pos = 0;
                for (Dish d: k.getCookingQueue()) {
                    
                    // If the dish is found
                    if (d.getId().equals(dishID)) {
                        // Display dish info
                        waitingQueueTableHeading();
                        System.out.println(d + "\n");
                        
                        // Display graph of cooking queue
                        System.out.println(k.getName() + "'s Cooking Queue");
                        displayGraph(pos, k.getAmountOfDishes(), k.getMaxCapacity());
                        System.out.println("Your dish is in the cooking queue, at the " + cardinalToOrdinal(pos + 1) + " position. ");
                    }
                }
            }
        }
    }
    
    
    // Read waitingQueue and kitchens from .dat file
    public void readFromFile() {
        waitingQueue = (QueueInterface) waitingQueueFile.read();
        kitchens = (ListInterface) kitchensFile.read();
    }
    
    // Write the waitingQueue and kitchens object into their respective .dat file
    public void saveToFile() {
        waitingQueueFile.write(waitingQueue);
        kitchensFile.write(kitchens);
    }
    
    // =======================================
    // PRIVATE METHODS / UTILITY METHODS
    
    // [ Option 4 -> Option 1 ]
    // Add new kitchen to list
    private void addKitchen() {
        // Prompt to enter new kitchen's details
        System.out.print("Enter the new kitchen's name: ");
        String name = scanner.nextLine();
        int cap = -1;
        while (cap < 0) {
            System.out.print("Enter the new kitchen's maximum cooking queue capacity: ");
            try {
                cap = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                cap = -1;
            }
            
            if (cap < 0) {
                System.out.println("Invalid input, please enter a value >= 0. ");
            }
        }
        
        // Add kitchen to list
        kitchens.add(new Kitchen(name, cap));
        System.out.println("Kitchen added successfully!");
    }
    
    // [ Option 4 -> Option 2 ]
    // Edit an existing kitchen's details
    private void editKitchens() {
        // Return to menu if the kitchen list is empty
        if (kitchens.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Prompt to choose a kitchen to edit
        int chosenIndex = searchKitchenById("Enter the kitchen ID you want to edit: ");
        
        // Prompt to enter updated kitchen details
        System.out.print("Enter the kitchen's new name: ");
        String name = scanner.nextLine();
        
        int cap = -1;
        while (cap < 0) {
            System.out.print("Enter the kitchen's new maximum cooking queue capacity: ");
            try {
                cap = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                cap = -1;
            }

            if (cap < 0) {
                System.out.println("Invalid input, please enter a value >= 0. ");
            }
        }
        
        // Update the selected kitchen 
        kitchens.getEntry(chosenIndex).setName(name);
        kitchens.getEntry(chosenIndex).setMaxCapacity(cap);
        
        System.out.println("Kitchen edited successfully. ");
    }
    
    // [ Option 4 -> Option 1 ]
    // Remove a kitchen from list
    private void removeKitchen() {
        // Return to menu if the kitchen list is empty
        if (kitchens.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Prompt to choose a kitchen to remove
        int chosenIndex = searchKitchenById("Enter the kitchen ID you want to remove: ");
        
        // Double confirm for deletion
        System.out.println("Are you sure you want to remove this kitchen? ");
        System.out.print("Type the ID again to remove: ");
        String inputConfirm = scanner.nextLine().toUpperCase();
        if (inputConfirm.equals(kitchens.getEntry(chosenIndex).getId())) {
            kitchens.remove(chosenIndex);
            System.out.println("Kitchen removed successfully. ");
        }
        else {
            System.out.println("Operation cancelled. ");
        }
    }
    
    // Prompts the user to enter a kitchen ID, 
    // and returns the index of the kitchen found in list
    // Includes error handling
    private int searchKitchenById(String question) {        
        while (true) {
            
            displayKitchens();
            System.out.print(question);
            String inputID = scanner.nextLine().toUpperCase();
            System.out.println();
            
            for (int i = 0; i < kitchens.getNumberOfEntries(); i++) {
                if (kitchens.getEntry(i).getId().equals(inputID)) {
                    return i;
                }
            }
            
            System.out.println("Kitchen with the following ID \"" + inputID + "\" is not found. ");
            System.out.println("Please re-enter. \n");
            enterToContinue();
        }
    }
    
    // Prompt user to press enter to continue
    private void enterToContinue() {
        System.out.print("\t---< Press Enter to Continue >---");
        scanner.nextLine();
        System.out.println();
    }
    
    // Display the table heading for the waiting queue
    private void waitingQueueTableHeading() {
        System.out.println("  ID | " + 
                String.format("%-20s ", "Dish name") + "|" + 
                String.format(" %-19s ", "Order time") + "|" + 
                String.format(" %-19s ", "Cooking time") + "|" + 
                String.format(" %-22s ", "Waited since order") + "|" + 
                String.format(" %-22s ", "Waited since cooking") + "|" + 
                String.format(" %-10s ", "Status") + "|");
    }
    
    // Display the table heading for the kitchens list
    private void kitchensListTableHeading() {
        System.out.println(
                String.format(" %3s ", "ID") + "|" + 
                String.format(" %-20s ", "Kitchen name") + "|" + 
                String.format(" %-15s ", "Max capacity") + "|" + 
                " Dishes in queue (X=occupied, o=empty) "
        );
    }
    
    // Called in constructor, to initialize the menuItemSet
    private void initializeMenu() {
        menuItemSet.add(new MenuItem("Appertizer", "Soup Soup", "Soup but twice the spice and umami."));
        menuItemSet.add(new MenuItem("Appertizer", "Vegan Salad", "Made for vegans only."));
        menuItemSet.add(new MenuItem("Main Course", "Tasty Steak", "Don't order if you can't handle beef."));
        menuItemSet.add(new MenuItem("Main Course", "Less Tasty Steak", "One of those lower quality foods in RPGs, like why? "));
        menuItemSet.add(new MenuItem("Beverage", "Green Tea", "Plain old classic green tea, can't went wrong with this."));
        menuItemSet.add(new MenuItem("Beverage", "Apple Juice", "An apple a day keeps the doctor away."));
        menuItemSet.add(new MenuItem("Dessert", "Sour Donuts", "People even enjoy donuts with green poop colors."));
        menuItemSet.add(new MenuItem("Dessert", "McD Ice cream", "For once their ice cream machine isn't broken."));
    }
    
    // Display the menu items in a table form
    private void displayMenuItems() {
        System.out.println("  " + String.format("Menu ID", "%8s") + " | " + 
                String.format("Menu Item Name", "%20s"));

        for (int i = 0; i < menuItemSet.getNumberOfEntries(); i++) {
            MenuItem item = menuItemSet.getElementAtPos(i);
            System.out.println((i + 1) + ") " + String.format(item.getMenuItemID(), "%8s") + " : " + 
                    String.format(item.getMenuItemName(), "%20s"));
        }
    }
    
    // Check if all kitchen's cooking queue are empty
    // Returns true if all cooking queues are empty
    // Returns false if at least one kitchen's cooking queue has at least one dish in its cooking queue
    private boolean isAllKitchenQueuesEmpty() {
        boolean result = true;
        for (Kitchen each : kitchens) {
            if (!each.isCookingQueueEmpty()) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    // Prints a collection as a graph
    // Also highlights an element in the graph, denoted by the variable 'highlight'
    private void displayGraph(int highlight, int fillSize, int totalSize) {
        for (int i = 0; i < highlight; i++) 
            System.out.print(" ");
        System.out.println("v");

        for (int i = 0; i < highlight; i++) 
            System.out.print("x");
        System.out.print("X");
        for (int i = 0; i < fillSize - highlight; i++) 
            System.out.print("x");
        for (int i = 0; i < totalSize - fillSize; i++)
            System.out.print("o");

        System.out.println();
        for (int i = 0; i < highlight; i++) 
            System.out.print(" ");
        System.out.println("^");
    }
    
    // Converts a cardinal integer to an ordinal number string
    // e.g. Converts 1 to 1st, 2 to 2nd, 3 to 3rd, 4 to 4th, etc
    private String cardinalToOrdinal(int n) {
        String nStr = Integer.toString(n);
        switch (n) {
            case 1:
                nStr += "st";
                break;
            case 2:
                nStr += "nd";
                break;
            case 3:
                nStr += "rd";
                break;
            default:
                nStr += "th";
                break;
        }
        return nStr;
    }
}
