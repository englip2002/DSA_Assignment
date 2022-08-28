

/**
 *
 * @author Thong So Xue
 */

import java.util.Scanner;
public class CateringModule {
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        FileHandler file = new FileHandler("cateringQueue.dat");
        CateringQueue catering = (CateringQueue) file.read();

        int menuChoice = 0;
        
        while (menuChoice != 5) {
            // Show options menu
            System.out.println("Choose an option: ");
            System.out.println("\t1) Order dish");
            System.out.println("\t2) Serve the next dish");
            System.out.println("\t3) Show all served dishes");
            System.out.println("\t4) Show all unserved dishes in queue");
            System.out.println("\t5) Quit to main menu");
            System.out.println("==============================");
            
            // Get choice of input
            try {
                menuChoice = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                menuChoice = 0;
            }
            
            // Run function based on selected option
            switch(menuChoice) {
                // Order dish, i.e. Add dish to ordering queue
                case 1:
                    // >> Ini waiting EngLip
                    // Add dish from a selected reservation
                    // OR
                    // Add ALL dishes from a selected reservation
                    Dish newDish = null;
                    catering.addDish(newDish);
                    enterToContinue();
                    break;
                    
                // Serve the next dish
                case 2:
                    Dish nextDish = catering.peekTopDish();
                    System.out.println("Are you sure you want to serve the following dish?");
                    System.out.println(nextDish);
                    System.out.print("Please enter Y/N: ");
                    String option = scanner.nextLine();
                    if (option.length() == 0 || (option.length() == 1 && Character.toLowerCase(option.charAt(0)) == 'y')) {
                        catering.serveTopDish();
                        System.out.println("The dish is served. ");
                    }
                    else {
                        System.out.println("Operation cancelled. ");
                    }
                    break;
                    
                // Show all the served dishes
                case 3:
                    catering.showServedDishes();
                    enterToContinue();
                    break;
                    
                // Show all the unserved dishes (All the dishes in queue)
                case 4:
                    catering.showDishesInQueue();
                    enterToContinue();
                    break;
                   
                // Quit to main menu
                case 5:
                    break;
                    
                // Error handling
                default:
                    System.out.println("Invalid input, please re-enter. ");
                    enterToContinue();
                    break;
            }
            
            // Save data to file
            if (menuChoice == 1 || menuChoice == 2) {
                file.write(catering);
            }
            
            System.out.println("\n\n");
        }
        
        // Quit catering module
        System.out.println("Quitting to main menu...");
    }
    
    
    private static void enterToContinue() {
        System.out.print("---< Press Enter to Continue >---");
        scanner.nextLine();
    }
}