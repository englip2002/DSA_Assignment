

/**
 *
 * @author Thong So Xue
 */

import java.util.Scanner;
public class CateringModule {
    private CateringManager manager = new CateringManager();
    private Scanner scanner = new Scanner(System.in);
    
    public void runModule() {
        int menuChoice = 0;
        while (menuChoice != 8) {
            // Show options menu
            System.out.println("    --- Catering Module ---    ");
            System.out.println("==============================");
            System.out.println("  1) Add dish to waiting queue");
            System.out.println("  2) Assign next dish in queue to a kitchen");
            System.out.println("  3) Serve a dish from a kitchen");
            System.out.println("  4) Manage kitchen details");
            System.out.println("  5) Display waiting queue");
            System.out.println("  6) Display list of kitchens");
            System.out.println("  7) Search for a dish in queue");
            System.out.println("  8) Quit to main menu");
            System.out.println("==============================");
            System.out.print("Choose an option: ");
            
            // Get choice of input
            try {
                menuChoice = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                menuChoice = 0;
            }
            
            // Run function based on selected option
            switch(menuChoice) {
                // Add dish to waiting queue
                case 1:
                    manager.addDishToWaitingQueue();
                    enterToContinue();
                    break;
                // Assign next dish in queue to a kitchen
                case 2:
                    manager.assignNextDishToKitchen();
                    enterToContinue();
                    break;
                // Serve a dish from a kitchen
                case 3:
                    manager.serveDishFromKitchen();
                    enterToContinue();
                    break;
                // Manage Kitchen details (Add, edit, delete)
                case 4:
                    manager.manageKitchenDetails();
                    break;
                // Display waiting queue
                case 5:
                    manager.displayWaitingQueue();
                    enterToContinue();
                    break;
                // Display list of kitchens
                case 6:
                    manager.displayKitchens();
                    enterToContinue();
                    break;
                // Search for a dish in queue (Both waiting queue and kitchen's cooking queue)
                case 7:
                    manager.searchDishInQueue();
                    break;
                // Quit to main menu
                case 8:
                    break;
                default:
                    System.out.println("Invalid input, please re-enter. ");
                    enterToContinue();
                    break;
            }
            
            manager.saveToFile();
        }
        
        // Quit catering module
        System.out.println("Quitting to main menu...");
        scanner.close();
    }
    
    // Prompt the user to press enter to continue
    private void enterToContinue() {
        System.out.print("---< Press Enter to Continue >---");
        scanner.nextLine();
        System.out.println();
    }
    
    // Main method
    public static void main(String[] args) {
        new CateringModule().runModule();
    }
}