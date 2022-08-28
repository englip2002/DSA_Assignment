import java.util.Scanner;

public class ReservationDriver {
    public static void main(String[] args) {
        ListInterface<Reservation> reservation = new LinkedList<Reservation>();
        
    }

    public String reservationHistory(ListInterface<Reservation> reservation) {
        String str = "";
        for(int i=0;i<reservation.getNumberOfEntries();i++){
            str += reservation.getEntry(i).toString();
        }
        return str;
    }

    public Reservation ReservationProcess(Account account){
        Reservation reservation;
        String contactNo, serveLocation;
        int serveDay,serveMonth,serveYear,serveMin,serveHour;
        Scanner scanner = new Scanner(System.in);
        if(account!=null){
            reservation = new Reservation(account);
            System.out.print("Please enter your contact No: ");
            contactNo=scanner.nextLine();
            System.out.print("Please enter your serve location: ");
            serveLocation=scanner.nextLine();
            System.out.println("Please enter your serve date (dd/MM/yyyy): ");
            String serveDateString = scanner.nextLine();
            String[] splitdate = serveDateString.split("/");
            
        }
        else{
            return null;
        }
    }
}
