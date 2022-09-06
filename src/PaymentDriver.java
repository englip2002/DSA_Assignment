/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defaultPackage;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class PaymentDriver {
    
    public static void main(String[] args) {
        String names[] = {"Curry Chicken Chop","Four Season Vegetables","Aromatic Rice","Fried Crispy Chicken","Ice Lemon Tea"};
        double prices[] = {12.50,5.50,6.70,3,2.50};
        int qty[] = {3,6,5,12,12};
        Payment p = new Payment(280,names,prices,qty);
        PaymentStack stp = new PaymentStack(p);
        
        p.getReceipt();
        
        stp.viewSummary();
    }
}
