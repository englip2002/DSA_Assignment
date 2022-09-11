

/**
 *
 * @author Thong So Xue
 */

/*
Client class of Catering Module

*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CateringManager {
    // Waiting queue: 
    // - New dish ordered will be placed in this queue first
    // - Dishes need to be removed from waiting queue 
    //   and placed at a kitchen's cooking queue before serving
    private QueueInterface<Dish> waitingQueue;
    
    // Kitchens list: 
    // - List of kitchensList in the system, each kitchen have its own cooking queue
    private ListInterface<Kitchen> kitchensList;
    
    // Menu set: 
    // - Contains dummy data for menu items, used in adding new dish
    private SetInterface<MenuItem> menuItemSet;
    
    // Scanner object
    private Scanner scanner = new Scanner(System.in);
    
    // File handler
    private FileHandler<QueueInterface<Dish>> waitingQueueFile = new FileHandler<QueueInterface<Dish>>("src/catering_waitingQueue.dat");
    private FileHandler<ListInterface<Kitchen>> kitchensFile = new FileHandler<ListInterface<Kitchen>>("src/catering_kitchens.dat");
    private FileHandler<Integer> nextDishIdFile = new FileHandler<Integer>("src/catering_nextDishId.dat");
    
    // Constructor
    public CateringManager() {
        // Load waitingQueue and kitchensList data from file
        readFromFile();
        
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
        else if (kitchensList.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Display the next dish in the waiting queue
        dishesQueueTableHeading();
        System.out.println(waitingQueue.getFront());
        System.out.println("This is the next dish to be assigned to a kitchen. ");
        enterToContinue();
        
        while (true) {
            // Prompt to choose a kitchen to cook this next dish
            int chosenIndex = searchKitchenById("Enter the ID of the kitchen to cook this dish: ");
            Kitchen chosenKitchen = kitchensList.getEntry(chosenIndex);
            
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
        if (kitchensList.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Prompt to choose a kitchen to serve its next dish
        int chosenIndex = searchKitchenById("Enter the ID of the kitchen to serve its next dish: ");
        Kitchen chosenKitchen = kitchensList.getEntry(chosenIndex);
        
        // Display the next dish to be served
        dishesQueueTableHeading();
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
        // Return to menu if the waiting queue is empty
        if (waitingQueue.isEmpty()) {
            System.out.println("There are no dishes in the waiting queue!");
            return;
        }
        
        System.out.println("\t\t--- Waiting Queue ---");
        dishesQueueTableHeading();
        int count = 0;
        for (Dish each: waitingQueue) {
            System.out.println(each);
            count++;
        }
        System.out.println("\t\t---< " + count + " records found! >---");
    }
    
    // [ Option 6 ]
    // Display list of kitchensList
    private void displayKitchensList() {
        // Return to menu if the kitchensList list is empty
        if (kitchensList.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        System.out.println("\t\t--- Kitchens List ---");
        kitchensListTableHeading();
        int count = 0;
        for (Kitchen each: kitchensList) {
            System.out.println(each);
            count++;
        }
        System.out.println("\t\t---< " + count + " records found! >---");
    }
    
    public void displayKitchensAndCookingQueues() {        
        // Return to menu if the kitchen list is empty
        if (kitchensList.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        while (true) {
            // Prompt to choose a kitchen to view its cooking queue
            int chosenIndex = searchKitchenById("Enter a kitchen ID to view its cooking queue. \n( Leave blank to exit ): ", true);
            if (chosenIndex == -1)
                return;
            Kitchen chosenKitchen = kitchensList.getEntry(chosenIndex);
            
            // Display the cooking queue
            System.out.println("[ " + chosenKitchen.getName() + "'s Cooking Queue ]");
            int count = 0;
            for (Dish each: chosenKitchen.getCookingQueue()) {
                if (count == 0)
                    dishesQueueTableHeading();
                System.out.println(each);
                count++;
            }
            if (count == 0) 
                System.out.println("\t\t---< No records found! >---");
            else
                System.out.println("\t\t---< " + count + " records found! >---");
            
            System.out.println("");
            enterToContinue();
        }
    }
    
    // [ Option 7 ]
    // Search for a dish in queue (Both waiting queue and kitchen's cooking queue)
    public void promptSearchDish() {
        String inputID = "", inputFood = "";
        LocalDate[] orderDates = { null, null };
        LocalDate[] cookingDates = { null, null };
        LocalTime[] orderTimes = { null, null };
        LocalTime[] cookingTimes = { null, null };
        String inputStatus = "";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        int menuOption = 0;
            
        while (menuOption != 10) {
            
            // Prompt user to choose a search parameter to edit
            do {
                // Parse and format strings to be displayed in menu options
                String orderDateStr = formatDateParameter(orderDates[0], orderDates[1], dateFormat);
                String orderTimeStr = formatTimeParameter(orderTimes[0], orderTimes[1], timeFormat);
                String cookingDateStr = formatDateParameter(cookingDates[0], cookingDates[1], dateFormat);
                String cookingTimeStr = formatTimeParameter(cookingTimes[0], cookingTimes[1], timeFormat);                
                
                System.out.println("======[ Current Parameters ]======");
                System.out.println("  1) Dish ID        : " + inputID);
                System.out.println("  2) Food name      : " + inputFood);
                System.out.println("  3) Order date     : " + orderDateStr);
                System.out.println("  4) Order time     : " + orderTimeStr);
                System.out.println("  5) Cooking date   : " + cookingDateStr);
                System.out.println("  6) Cooking time   : " + cookingTimeStr);
                System.out.println("  7) Status         : " + inputStatus);
                System.out.println("  8) CLEAR ALL PARAMETERS");
                System.out.println("  9) START SEARCHING");
                System.out.println("  10) BACK TO MAIN MENU");

                System.out.println("\n( Leave blank to ignore the parameter )");
                System.out.print("Choose an option to edit the parameter: ");
                
                try {
                    menuOption = Integer.parseInt(scanner.nextLine());
                }
                catch (Exception e) {
                    menuOption = 0;
                }
                
                if (menuOption < 1 || menuOption > 10) {
                    menuOption = 0;
                    System.out.println("\t< Invalid input, please try again. >");
                    enterToContinue();
                }
            } while (menuOption == 0);
            
            System.out.println();
            
            switch(menuOption) {
                // Invalid input
                case 0:
                    break;
                // 1) Dish ID
                case 1:
                    System.out.print("Enter the Dish ID you wish to search for: ");
                    inputID = scanner.nextLine();
                    break;
                // 2) Food name
                case 2:
                    System.out.print("Enter the Food name you wish to search for: ");
                    inputFood = scanner.nextLine();
                    break;
                // 3) Order date
                case 3:
                    searchByDate(orderDates, dateFormat);
                    break;
                // 4) Order time
                case 4:
                    searchByTime(orderTimes, timeFormat);
                    break;
                // 5) Cooking date
                case 5: 
                    searchByDate(cookingDates, dateFormat);
                    break;
                // 6) Cooking time
                case 6: 
                    searchByTime(cookingTimes, timeFormat);
                    break;
                // 7) Status
                case 7: 
                    System.out.print("Enter the Status you wish to search for: ");
                    inputStatus = scanner.nextLine();
                    break;
                // 8) CLEAR ALL PARAMETERS
                case 8: 
                    inputID = "";
                    inputFood = "";
                    orderDates[0] = null;
                    orderDates[1] = null;
                    orderTimes[0] = null;
                    orderTimes[1] = null;
                    cookingDates[0] = null;
                    cookingDates[1] = null;
                    cookingTimes[0] = null;
                    cookingTimes[1] = null;
                    inputStatus = "";
                    System.out.println("\t< Cleared all parameters! >");
                    break;
                // 9) START SEARCHING
                case 9:
                    searchDishByParams(inputID, inputFood, 
                            orderDates[0], orderDates[1], 
                            orderTimes[0], orderTimes[1], 
                            cookingDates[0], cookingDates[1], 
                            cookingTimes[0], cookingTimes[1], 
                            inputStatus);
                    break;
                // 10) BACK TO MAIN MENU
                case 10:
                    break;
            }
        }
    }
    
    
    // Read waitingQueue and kitchensList from .dat file
    public void readFromFile() {
        waitingQueue = (QueueInterface<Dish>) waitingQueueFile.read(false);
        if (waitingQueue == null)
            waitingQueue = new LinkedQueue<Dish>();
        
        kitchensList = (ListInterface<Kitchen>) kitchensFile.read(false);
        if (kitchensList == null)
            kitchensList = new LinkedList<Kitchen>();
        
        Integer tempNextId = (Integer) nextDishIdFile.read(false);
        if (tempNextId == null)
            tempNextId = 1;
        Dish.setNextId(tempNextId);
        
    }
    
    // Write the waitingQueue and kitchensList object into their respective .dat file
    public void saveToFile() {
        waitingQueueFile.write(waitingQueue);
        kitchensFile.write(kitchensList);
        nextDishIdFile.write(Dish.getNextId());
    }
    
    // =======================================
    // PRIVATE METHODS / UTILITY METHODS
    
    // [ Option 4 -> Option 1 ]
    // Add new kitchen to list
    private void addKitchen() {
        boolean found = true;
        String name;
        do {
            // Prompt to enter kitchen name
            System.out.print("Enter the new kitchen's name: ");
            name = scanner.nextLine();

            // Check if this kitchen name is already in the system
            for (Kitchen k: kitchensList) {
                if (k.getName().equalsIgnoreCase(name)) {
                    found = true;
                    boolean invalidInput = true;
                    do {
                        // Double confirm if name clashes
                        System.out.println("\t< \"" + name + "\" is already in our system! Are you sure you want to continue? >");
                        System.out.print("( Type 'YES' to continue, or leave blank to cancel. ): ");
                        String confirm = scanner.nextLine();
                        if (confirm.equals("")) {
                            System.out.println("\t< Add operation cancelled. >");
                            return;
                        }
                        else if (confirm.equalsIgnoreCase("YES")) {
                            invalidInput = false;
                        }
                        else {
                            System.out.println("< Invalid input, please try again. >");
                        }
                    } while (invalidInput);
                    break;
                }
            }
        } while (!found);
        
        // Prompt to enter the maximum cooking queue capacity
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
        kitchensList.add(new Kitchen(name, cap));
        System.out.println("Kitchen added successfully!");
    }
    
    // [ Option 4 -> Option 2 ]
    // Edit an existing kitchen's details
    private void editKitchens() {
        // Return to menu if the kitchen list is empty
        if (kitchensList.isEmpty()) {
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
        kitchensList.getEntry(chosenIndex).setName(name);
        kitchensList.getEntry(chosenIndex).setMaxCapacity(cap);
        
        System.out.println("Kitchen edited successfully. ");
    }
    
    // [ Option 4 -> Option 1 ]
    // Remove a kitchen from list
    private void removeKitchen() {
        // Return to menu if the kitchen list is empty
        if (kitchensList.isEmpty()) {
            System.out.println("There are no kitchens recorded in the system!");
            return;
        }
        
        // Prompt to choose a kitchen to remove
        int chosenIndex = searchKitchenById("Enter the kitchen ID you want to remove: ");
        
        // Double confirm for deletion
        System.out.println("Are you sure you want to remove this kitchen? ");
        System.out.print("Type the ID again to remove: ");
        String inputConfirm = scanner.nextLine().toUpperCase();
        if (inputConfirm.equals(kitchensList.getEntry(chosenIndex).getId())) {
            kitchensList.remove(chosenIndex);
            System.out.println("Kitchen removed successfully. ");
        }
        else {
            System.out.println("Operation cancelled. ");
        }
    }
    
    // Prompts the user to enter a kitchen ID, 
    // and returns the index of the kitchen found in list
    // Includes error handling
    private int searchKitchenById(String question, boolean allowBlank) {        
        while (true) {
            
            displayKitchensList();
            System.out.print(question);
            String inputID = scanner.nextLine().toUpperCase();
            if (allowBlank && inputID.equals(""))
                return -1;
            
            System.out.println();
            
            for (int i = 0; i < kitchensList.getNumberOfEntries(); i++) {
                if (kitchensList.getEntry(i).getId().equals(inputID)) {
                    return i;
                }
            }
            
            System.out.println("Kitchen with the following ID \"" + inputID + "\" is not found. ");
            System.out.println("Please re-enter. \n");
            enterToContinue();
        }
    }
    
    private int searchKitchenById(String question) {
        return searchKitchenById(question, false);
    }
    
    // Prompt user to press enter to continue
    private void enterToContinue() {
        System.out.print("\t---< Press Enter to Continue >---");
        scanner.nextLine();
        System.out.println("");
    }
    
    // Display the table heading for the waiting queue
    private void dishesQueueTableHeading() {
        System.out.println("  ID | " + 
                String.format("%-20s ", "Dish name") + "|" + 
                String.format(" %-19s ", "Order time") + "|" + 
                String.format(" %-22s ", "Waited since order") + "|" + 
                String.format(" %-19s ", "Cooking time") + "|" + 
                String.format(" %-22s ", "Waited since cooking") + "|" + 
                String.format(" %-10s ", "Status") + "|");
    }
    
    // Display the table heading for the kitchensList list
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
    
    private String formatDateParameter(LocalDate start, LocalDate end, DateTimeFormatter formatter) {
        String result = "";
        if (start != null) {
            if (start == LocalDate.MIN) 
                result += "Past";
            else
                result = start.format(formatter);

            if (end == LocalDate.MAX)
                result += " - Future";
            else if (!start.equals(end)) {
                result += " - " + end.format(formatter);
            }
        }
        return result;
    }
    
    private String formatTimeParameter(LocalTime start, LocalTime end, DateTimeFormatter formatter) {
        String result = "";
        if (start != null) {
            if (start == LocalTime.MIN) 
                result += start.format(formatter);
            else
                result = start.format(formatter);

            if (!start.equals(end))
                result += " - " + end.format(formatter);
        }
        return result;
    }
    
    private void searchByDate(LocalDate dates[], DateTimeFormatter formatter) {
        // Choose date input option
        boolean invalidInput;
        int orderDateOption = 0;
        do {
            System.out.println("\t1) Search by specific date");
            System.out.println("\t2) Search by a range of date");
            System.out.print("Please choose a method of searching: ");
            try {
                orderDateOption = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                orderDateOption = 0;
            }

            if (orderDateOption < 1 || orderDateOption > 2) {
                orderDateOption = 0;
                System.out.println("\t< Invalid input, please try again.  >");
                enterToContinue();
            }
        } while (orderDateOption == 0);

        // Enter specific date or date range
        switch (orderDateOption) {
            case 1:
                invalidInput = true;
                do {
                    System.out.print("Enter the Order date you wish to search for (dd/MM/yyyy): ");
                    try {
                        dates[0] = LocalDate.parse(scanner.nextLine(), formatter);
                        invalidInput = false;
                    }
                    catch (Exception e) {
                        System.out.println("\t< Invalid input, please try again. >");
                        enterToContinue();
                    }
                } while (invalidInput);
                dates[1] = dates[0];
                break;
            case 2:
                // Prompt start date
                invalidInput = true;
                do {
                    System.out.println("Enter the STARTING DATE RANGE for the Order date you wish to search for (dd/MM/yyyy)");
                    System.out.print("( Leave blank to ignore starting date ): ");
                    try {
                        String inputStr = scanner.nextLine();
                        if (inputStr.equals("")) 
                            dates[0] = LocalDate.MIN;
                        else
                            dates[0] = LocalDate.parse(inputStr, formatter);
                        invalidInput = false;
                    }
                    catch (Exception e) {
                        System.out.println("\t< Invalid input, please try again. >");
                        enterToContinue();
                    }
                } while (invalidInput);

                // Prompt end date
                invalidInput = true;
                do {
                    System.out.println("Enter the ENDING DATE RANGE for the Order date you wish to search for (dd/MM/yyyy)");
                    System.out.print("( Leave blank to ignore ending date ): ");
                    String inputStr = scanner.nextLine();
                    if (inputStr.equals("")) {
                        dates[1] = LocalDate.MAX;
                        invalidInput = false;
                    }
                    else {
                        try {
                            LocalDate tempDate = LocalDate.parse(inputStr, formatter);
                            if (tempDate.isBefore(dates[0])) {
                                System.out.println("\t< Invalid input, end date must not be earlier start date. >");
                                enterToContinue();
                            }
                            else {
                                dates[1] = tempDate;
                                invalidInput = false;
                            }
                        }
                        catch (Exception e) {
                            System.out.println("\t< Invalid input, please try again. >");
                            enterToContinue();
                        }
                    }
                } while (invalidInput);
                break;
        }
    }
    
    
    
    private void searchByTime(LocalTime times[], DateTimeFormatter formatter) {
        // Choose time input option
        boolean invalidInput;
        int orderTimeOption = 0;
        do {
            System.out.println("\t1) Search by specific time");
            System.out.println("\t2) Search by a range of time");
            System.out.print("Please choose a method of searching: ");
            try {
                orderTimeOption = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                orderTimeOption = 0;
            }

            if (orderTimeOption < 1 || orderTimeOption > 2) {
                orderTimeOption = 0;
                System.out.println("\t< Invalid input, please try again.  >");
                enterToContinue();
            }
        } while (orderTimeOption == 0);

        // Enter specific time or time range
        switch (orderTimeOption) {
            case 1:
                invalidInput = true;
                do {
                    System.out.print("Enter the Order time you wish to search for (HH:MM:SS): ");
                    try {
                        times[0] = LocalTime.parse(scanner.nextLine(), formatter);
                        invalidInput = false;
                    }
                    catch (Exception e) {
                        System.out.println("\t< Invalid input, please try again. >");
                        enterToContinue();
                    }
                } while (invalidInput);
                times[1] = times[0];
                break;
            case 2:
                // Prompt start time
                invalidInput = true;
                do {
                    System.out.println("Enter the STARTING TIME RANGE for the Order time you wish to search for (HH:MM:SS): ");
                    System.out.print("( Leave blank to ignore starting time ): ");
                    try {
                        String inputStr = scanner.nextLine();
                        if (inputStr.equals("")) 
                            times[0] = LocalTime.MIN;
                        else
                            times[0] = LocalTime.parse(inputStr, formatter);
                        invalidInput = false;
                    }
                    catch (Exception e) {
                        System.out.println("\t< Invalid input, please try again. >");
                        enterToContinue();
                    }
                } while (invalidInput);

                // Prompt end time
                invalidInput = true;
                do {
                    System.out.println("Enter the ENDING TIME RANGE for the Order time you wish to search for (HH:MM:SS): ");
                    System.out.print("( Leave blank to ignore ending time ): ");
                    String inputStr = scanner.nextLine();
                    if (inputStr.equals("")) {
                        times[1] = LocalTime.MAX;
                        invalidInput = false;
                    }
                    else{
                        try {
                            LocalTime timeTemp = LocalTime.parse(inputStr, formatter);
                            if (timeTemp.isBefore(times[0])) {
                                System.out.println("\t< Invalid input, end time must not be earlier than start time. >");
                                enterToContinue();
                            }
                            else {
                                times[1] = timeTemp;
                                invalidInput = false;
                            }
                        }
                        catch (Exception e) {
                            System.out.println("\t< Invalid input, please try again. >");
                            enterToContinue();
                        }
                    }
                } while (invalidInput);
                break;
        }
    }
    
    private void searchDishByParams(String id, String foodName, 
            LocalDate orderDateStart, LocalDate orderDateEnd, 
            LocalTime orderTimeStart, LocalTime orderTimeEnd, 
            LocalDate cookingDateStart, LocalDate cookingDateEnd, 
            LocalTime cookingTimeStart, LocalTime cookingTimeEnd, 
            String status) {
        
        // Include all dates if no dates are given
        if (orderDateStart == null && orderDateEnd == null) {
            orderDateStart = LocalDate.MIN;
            orderDateEnd = LocalDate.MAX;
        }
        if (orderTimeStart == null && orderTimeEnd == null) {
            orderTimeStart = LocalTime.MIN;
            orderTimeEnd = LocalTime.MAX;
        }
        if (cookingDateStart == null && cookingDateEnd == null) {
            cookingDateStart = LocalDate.MIN;
            cookingDateEnd = LocalDate.MAX;
        }
        if (cookingTimeStart == null && cookingTimeEnd == null) {
            cookingTimeStart = LocalTime.MIN;
            cookingTimeEnd = LocalTime.MAX;
        }
        
        // Search in the waiting queue
        boolean foundOnce = false;
        int count = 0;
        for (Dish d: waitingQueue) {
            if (isMatchingDish(d, id, foodName, orderDateStart, orderDateEnd, 
            orderTimeStart, orderTimeEnd, cookingDateStart, cookingDateEnd, 
            cookingTimeStart, cookingTimeEnd, status)) {
                if (count == 0) {
                    System.out.println("[ Main Waiting Queue ]");
                    dishesQueueTableHeading();
                }
                System.out.println(d);
                count++;
            }
        }
        if (count > 0) {
            System.out.println("\t\t---< " + count + " records found! >---");
            System.out.println();
            enterToContinue();
            foundOnce = true;
        }
        
        // Search in the kitchensList
        for (Kitchen each: kitchensList) {
            count = 0;
            for (Dish d : each.getCookingQueue()) {
                if (isMatchingDish(d, id, foodName, orderDateStart, orderDateEnd, 
                orderTimeStart, orderTimeEnd, cookingDateStart, cookingDateEnd, 
                cookingTimeStart, cookingTimeEnd, status)) {
                    if (count == 0) {
                        System.out.println("[ " + each.getName() + "'s Cooking Queue ]");
                        dishesQueueTableHeading();
                    }
                    System.out.println(d);
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("\t\t---< " + count + " records found! >---");
                System.out.println();
                enterToContinue();
                foundOnce = true;
            }
        }
        
        if (!foundOnce) {
            System.out.println("\t\t---< There are no records found >---");
        }
    }
    
    private boolean isMatchingDish(Dish d, String id, String foodName, 
            LocalDate orderDateStart, LocalDate orderDateEnd, 
            LocalTime orderTimeStart, LocalTime orderTimeEnd, 
            LocalDate cookingDateStart, LocalDate cookingDateEnd, 
            LocalTime cookingTimeStart, LocalTime cookingTimeEnd, 
            String status) {
        boolean compareId = id == "" ? true : d.getId().toUpperCase().contains(id.toUpperCase());
        boolean compareFood = foodName == "" ? true : d.getFood().getMenuItemName().toUpperCase().contains(foodName.toUpperCase());
        boolean compareOrderDate = d.orderDateIsBetween(orderDateStart, orderDateEnd);
        boolean compareOrderTime = d.orderTimeIsBetween(orderTimeStart, orderTimeEnd);
        boolean compareCookingDate = d.cookingDateIsBetween(cookingDateStart, cookingDateEnd);
        boolean compareCookingTime = d.cookingTimeIsBetween(cookingTimeStart, cookingTimeEnd);
        boolean compareStatus = status == "" ? true : d.getStatus().toUpperCase().contains(status.toUpperCase());
        return (compareId && compareFood && compareOrderDate && compareOrderTime
                    && compareCookingDate && compareCookingTime && compareStatus);
    }
}
