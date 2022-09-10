/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defaultPackage;


import java.io.Serializable;
import static java.lang.Math.*;



/**
 *- displayRecentPayment()	//(Staff) check recent payment details 
- paymentMef()		//calculate profit	
- profitReport()
    //system
 * @author User
 */
public class Payment implements Serializable{
    //attributes
    private double subTotal, total, pay, tax, change;
    private final double TAXFINAL = 0.22;
    private final Stack<String> stackPBackUp = new Stack<>();
    
    
    
    //constructor
    public Payment(double amountRecieved, Stack<String[]> st){
        String[] arrTemp;
        setPay(amountRecieved);
        while(st.isEmpty() == false){
            arrTemp = st.pop();
            stackPBackUp.push(String.format("%20.20s | %6.6s | %2.2s",arrTemp[0], arrTemp[1], arrTemp[2])); 
            subTotal += Double.parseDouble(arrTemp[1]) * Double.parseDouble(arrTemp[2]);
        }  
        calculate();
    
    }
    
    
    //utility methods
    private void setPay(double pay){
        this.pay = pay;
    }
    private double calculate(){
        tax = subTotal * TAXFINAL;
        if (tax  > 0) {
            
        }
        total = tax + subTotal;
        if (pay >= total) {
            if (pay % 100 > 49) {
                change = pay - ceil(total);
            }
            else{
                change = pay - floor(total);
            }
            
            return change;
        }
        else{
            return -100;
        }
    }
    
    
    //public methods
    public double getProfit(){
        return total;
    }
    public boolean isEqualFoods(String str){
        while(stackPBackUp.hasNext()){
            String arr = stackPBackUp.next();
            if (arr.contains(str)) {
                return true;
            }
        }
        return false;
        
    }
    public void getReceipt(){
        int i = 0;
        System.out.println("-------ABC Catering-------");
        System.out.println(" Order List: ");
        while(stackPBackUp.hasNext()){
            i++;
            System.out.println(i + stackPBackUp.next());
        }
        System.out.printf("======================================\n",subTotal);
        System.out.printf(" SubTotal---------RM %5.2f \n",subTotal);
        System.out.printf(" Service&Tax------RM %5.2f \n",tax);
        System.out.printf(" Total------------RM %5.2f \n",total);
        if (pay % 10 > 4) {
            System.out.printf(" RoundUp/Down-----RM %5.2f \n",ceil(total * 10) / 10);
        }
        else{
            System.out.printf(" RoundUp/Down-----RM %5.2f \n",floor(total * 10) / 10);
        }
        System.out.printf("======================================\n");
        System.out.printf(" AmountReceived---RM %5.2f \n",pay);
        
        System.out.printf(" Change-----------RM %5.2f \n",change);
    }
    
    @Override
    public String toString(){
        String str="";
        
        str += String.format(" | %7.2f | %7.2f | %7.2f | %7.2f | %7.2f |", subTotal,tax,total,pay,change);
        return str;
    }
}
