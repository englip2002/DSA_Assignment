/**
 *
 * @author User
 */
public class PaymentStack {
    private Payment p;
    private Stack<Payment> stackP = new Stack<>();
    private FileHandler files = new FileHandler("payment.dat");
    public PaymentStack(){
           
        
    }
    public void writeTF(){    
        files.write(stackP);
            
    }
    private void readFF(){
        stackP = (Stack) files.read();
        
    }
    public void addNew(Payment payment){
        stackP.push(payment);
        writeTF();
    }
    public void viewMostRecent(){
        readFF();
        stackP.peek();
    }
    public void removeMostRecent(){
        readFF();
        stackP.pop();
        writeTF();
    }
    public void viewSummary(){
        readFF();
        int i = 0;
        double totalProfit = 0;
        System.out.println("-------EAT 99 Catering Payment Report-------");
        System.out.println("sort by latest:");
        
        while (stackP.isEmpty() == false) {
            Payment temp = stackP.pop();
            i++;
            totalProfit = temp.getProfit();
            System.out.println(i + " | " + temp.toString());
            
        }
       
        System.out.println("Total Profit==========="+totalProfit);

    }
    public void viewOldFirst(){
        readFF();
        Stack<Payment> tempSt = new Stack<>();
        int j = 0;
        double totalProfit = 0;
        System.out.println("-------EAT 99 Catering Payment Report-------");
        System.out.println("sort by earliest:");
        
        while (stackP.isEmpty() == false) {
            tempSt.push(stackP.pop());
        }
        while(tempSt.isEmpty() == false){
            Payment temp = tempSt.pop();
            j++;
            totalProfit += temp.getProfit();
            System.out.println(j + " | " + temp.toString());
            
        }
       
        System.out.println("Total Profit==========="+totalProfit);

    }
    public void viewAllReceipt(){
        readFF();
        int i = 0;
        System.out.println("-------EAT 99 Catering Receipt List-------");
        System.out.println("");
        while(stackP.isEmpty() == false){
            i++;
            System.out.printf("---Receipt: %d------------------------------\n",i);
            stackP.pop().getReceipt();
        }
    }
    public void sortReceipt(Double ammount, String foodName){
        readFF();
        while(stackP.isEmpty() == false){
            Payment temp = stackP.pop();
            if(temp.getProfit() >= ammount ){
                if (temp.isEqualFoods(foodName)) {
                    temp.getReceipt();
                }
            }
            else{
                stackP.pop();
            }
        }
    }
}
