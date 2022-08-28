//Tan Eng Lip
public class CateringTest {

    public static void main(String[] args) {
        ListInterface<Integer> str = new LinkedList<Integer>();
        System.out.println("empty: " + str.isEmpty());
        System.out.println("full: " + str.isFull());
//        str.add(1);
//        str.add(2);
//        str.add(3);
//        str.add(4);
//        str.add(5);
//        str.add(6);
        
        System.out.println(str.contains(1));
        System.out.println(str.toString());
    }
}
