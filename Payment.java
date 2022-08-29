/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defaultPackage;


import java.util.Scanner;

/**
 *- displayRecentPayment()	//(Staff) check recent payment details 
- paymentMef()		//calculate profit	
- profitReport()
    //system
 * @author User
 */
public class Payment {
    //attributes
    private double subTotal, total, pay, tax, change;
    private Cart cart;
    private Account acc;
    private final double TAXFINAL = 0.22;
    private Stack<Payment> stackP = new Stack<Payment>();
    private Stack stackPBackUp;
    private FileHandler files = new FileHandler("payment.dat");
    private Payment payment;
    Scanner sc = new Scanner(System.in);
    
    //constructor
    public Payment(double amountRecieved, Cart whatFood, Account account){
        
    do {
        System.out.print("Pay : ");
        setPay(sc.nextDouble());
        setCart(whatFood);
        getTotal();
        if (calculateChange() != -100) {
            System.out.print("\nPayment Success");
            break;
        }
        System.out.println("Insufficient Money Recieved, Please Try Again");
    } while (calculateChange() == -100) ;
    
    
    }
    
    
    //utility methods
    private double getSubTotal(){
        subTotal = cart.calculateTotal();
        return subTotal;
    }
    public double getTotal(){
        total = calculateTax() + getSubTotal();
        return total;
    }
    public double getPay(){
        return pay;
    }
    private void setPay(double pay){
        this.pay = pay;
    }
    private void setCart(Cart cart){
        this.cart = cart;
    }
    private double calculateTax(){
        tax = subTotal * TAXFINAL;
        return tax; 
    }     
    private double calculateChange(){
        if (pay >= total) {
            change = pay - total;
            return change;
        }
        else{
            return -100;
        }
        
    }
    
    public void getReceipt(){
        System.out.println("-------ABC Catering-------");
        for(int i=0;i<cart.getItemCount();i++){
            System.out.printf("%3d %12s %5.2lf\n", (i+1),cart.getFoods().getEntry(i).toString(),cart.getFoods().getEntry(i).calculateSubtotal());
        }
        System.out.println("");
        System.out.printf("SubTotal---------RM.2%lf",subTotal);
        System.out.printf("Service&Tax------RM.2%lf",tax);
        System.out.printf("Total------------RM.2%lf",total);
        System.out.printf("----------------------------------------");
        System.out.printf("AmountReceived---RM.2%lf",pay);
        System.out.printf("Change-----------RM.2%lf",change);
    }
    public String toString(){
        String str="";
        for(int i=0;i<cart.getItemCount();i++){
            str+=String.format("%3d %12s %5.2lf", (i+1),cart.getFoods().getEntry(i).toString(),cart.getFoods().getEntry(i).calculateSubtotal());
        }
        str += subTotal + tax + total + pay + change;
        return str;
    }
}
