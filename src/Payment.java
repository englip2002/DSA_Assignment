/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defaultPackage;


import static java.lang.Math.*;

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
    private final double TAXFINAL = 0.22;
    private final Stack<String> stackPBackUp = new Stack<>();
    
    Scanner sc = new Scanner(System.in);
    
    //constructor
    public Payment(double amountRecieved, String foodName[], double foodPrice[], int quantity[]){
        String temp;
        for (int i = 0; i < foodPrice.length; i++) {
            subTotal += foodPrice[i] * quantity[i];
        }   
        pay = amountRecieved;
        
        for (int i = 0; i < foodPrice.length; i++) {
            temp = String.format(" %15.15s | %7.7s | %2.2s",foodName[i], foodPrice[i], quantity[i]);  
            stackPBackUp.push(temp);
        }   
        calculate();
    
    }
    
    
    //utility methods
    private final double calculate(){
        tax = subTotal * TAXFINAL;
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
    public String toString(){
        String str="";
        while(stackPBackUp.hasNext()){
            System.out.println(stackPBackUp.next());
        }
        str += subTotal + tax + total + pay + change;
        return str;
    }
}
