
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {

        TableManager tableManager = new TableManager();
        MenuManager menuManager = new MenuManager();
        Scanner sc = new Scanner(System.in);
        int selection;
        do {
            System.out.println("\nEAT 99 Catering System");
            System.out.println("======================");
            System.out.println("1. Table Module");
            System.out.println("2. Menu Module");
            System.out.println("3. Reservation Module");
            System.out.println("4. Catering Module");
            System.out.println("5. Payment Module");
            System.out.print("Selection (-1 to exit) > ");
            selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 1:
                    // Table Module
                    tableManager.tableModule();
                    break;
                case 2:
                    // Menu Module
                    menuManager.menuModule();
                    break;
                case 3:
                    // Reservation Module
                    break;
                case 4:
                    // Catering Module
                    break;
                case 5:
                    // Payment Module
                    PaymentDriver.clientPayment();                    
                    break;
                case -1:
                    // Exit
                    break;
                default:
                    System.out.println("Invalid selection! Please try again.");
                    break;
            }
        } while (selection != -1);

        System.out.println("System terminated. Thanks for your using!");
        sc.close();
    }
}
