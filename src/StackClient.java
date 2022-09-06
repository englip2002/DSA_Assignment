/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class StackClient {
    public static void main(String[] args) {
        StackInterface<String> st = new Stack<>();
        st.push("adadad");
        st.push("ad11dad");
        st.push("adsdsd");
        st.push("acac");
       
        while(st.hasNext()){
            System.out.print(st.next()+ " ");
        }
        while(!st.isEmpty()){
            System.out.print(st.pop()+ " ");
            System.out.print(st.howMuch() + "\n");
        }  
        
    }
}
