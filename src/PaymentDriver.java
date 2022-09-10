/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author User
 */
public class PaymentDriver {
    
    public static void clientPayment() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        int input,addno = 1,input2;
        Stack<String[]> payment1 = new Stack<>();
        Stack<String[]> payment2 = new Stack<>();
        Stack<String[]> payment3 = new Stack<>();
        Stack<String[]> payment4 = new Stack<>();
        Stack<String[]> payment5 = new Stack<>();
        String[] detailArr11 = {"Curry Chicken Soup", "12.50", "12"};
        String[] detailArr12 = {"Curry Chicken Soup", "12.50", "20"};
        String[] detailArr13 = {"Curry Chicken Soup", "12.50", "30"};
        String[] detailArr21 = {"Four Season Vegetables", "5.50", "7"};
        String[] detailArr22 = {"Four Season Vegetables", "5.50", "10"};
        String[] detailArr23 = {"Four Season Vegetables", "5.50", "15"};
        String[] detailArr31 = {"Aromatic Rice", "1.70", "10"};
        String[] detailArr32 = {"Aromatic Rice", "1.70", "25"};
        String[] detailArr33 = {"Aromatic Rice", "1.70", "17"};
        String[] detailArr41 = {"Fried Crispy Chicke", "5.50", "35"};
        String[] detailArr42 = {"Fried Crispy Chicke", "5.50", "10"};
        String[] detailArr43 = {"Fried Crispy Chicke", "5.50", "21"};
        String[] detailArr51 = {"Ice Lemon Tea", "2.50", "30"};
        String[] detailArr52 = {"Ice Lemon Tea", "2.50", "20"};
        String[] detailArr53 = {"Ice Lemon Tea", "2.50", "20"};
        String[] detailArr61 = {"Tasty Syrup", "1.50", "15"};
        String[] detailArr62 = {"Tasty Syrup", "1.50", "28"};
        String[] detailArr63 = {"Tasty Syrup", "1.50", "28"};
        
        
        payment1.push(detailArr11);
        payment1.push(detailArr21);
        payment1.push(detailArr31);
        payment1.push(detailArr41);
        payment1.push(detailArr51);
        
        payment2.push(detailArr12);
        payment2.push(detailArr22);
        payment2.push(detailArr32);
        payment2.push(detailArr42);
        payment2.push(detailArr52);
        
        payment3.push(detailArr12);
        payment3.push(detailArr23);
        payment3.push(detailArr31);
        payment3.push(detailArr43);
        payment3.push(detailArr61);
        
        payment4.push(detailArr12);
        payment4.push(detailArr22);
        payment4.push(detailArr33);
        payment4.push(detailArr41);
        payment4.push(detailArr62);
        
        payment5.push(detailArr12);
        payment5.push(detailArr23);
        payment5.push(detailArr31);
        payment5.push(detailArr41);
        payment5.push(detailArr63);
        
        
        
        
        
        Payment p = new Payment(580,payment1);
        Payment p2 = new Payment(600,payment2);
        Payment p3 = new Payment(600,payment3);
        Payment p4 = new Payment(695,payment4);
        Payment p5 = new Payment(715,payment5);
        var stp = new PaymentStack();
        do{
            System.out.println("=This is the Payment menu===========");
            System.out.println("=press keys to select options");
            System.out.println("=1. add new Record");
            System.out.println("=2. view all record(most recent)");
            System.out.println("=3. view all record(least recent)");
            System.out.println("=4. delete records");
            System.out.println("=5. view receipt");
            System.out.println("=6. filter receipt");
            System.out.println("===========-1. to exit===");

            input = sc.nextInt();
            switch(input){
                case 1:
                    switch(addno){
                        case 1:
                            stp.addNew(p);
                            addno = 2;
                            break;
                        case 2:
                            stp.addNew(p2);
                            addno = 3;
                            break;
                        case 3:
                            stp.addNew(p3);
                            addno = 4;
                            break;
                        case 4:
                            stp.addNew(p4);
                            addno = 5;
                            break;
                        case 5:
                            stp.addNew(p5);
                            addno = 1;
                            break;
                    }
                    break;
                case 2:
                    stp.viewSummary();
                    break;
                case 3:
                    stp.viewOldFirst();
                    break;
                case 4:
                    stp.removeMostRecent();
                    break;
                case 5:
                    stp.viewAllReceipt();
                    break;
                case 6:
                    System.out.println("=Enter filter");
                    System.out.println("=1. profit more than");
                    Double ID = sc2.nextDouble();
                    System.out.println("=2. has this food/ has this food with a price of/ food quantity exceeding amount of");
                    String food = sc3.nextLine();
                    stp.sortReceipt(ID,food);
                    break;
            }
        
        }while(input != -1);
        
        
        
    }
}
