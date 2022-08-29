/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defaultPackage;

/**
 *
 * @author User
 */
public class PaymentStack {
    private Stack<Payment> stackP = new Stack<Payment>();
    private Stack stackPBackUp;
    private FileHandler files = new FileHandler("payment.dat");
    public PaymentStack(){
        readFF();
       
        
    }
    private void readFF(){
        stackP = (Stack)files.read();

    }
    public void addNew(Payment payment){
        stackP.push(payment);
        files.write( stackP);
    }
    public void viewMostRecent(){
        stackP.peek();
    }
    public void viewSummary(){
        double totalProfit = 0;
        System.out.println("-------EAT 99 Catering Payment Report-------");
        System.out.println("no.|OrderDetails                                                          |Subtotal|Tax   |Total  |AmountRecieved|Change  |");
        
        for (int i = 0; i < stackP.howMuch(); i++) {
            stackP.next().toString();
        }
        for (int i = 0; i < stackP.howMuch(); i++) {
            totalProfit += stackP.next().getTotal();
        }
        System.out.println("Total Profit==========="+totalProfit);

    }
   
}
