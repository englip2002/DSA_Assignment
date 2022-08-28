public class ReservationDriver {
    public static void main(String[] args) {
        ListInterface<Reservation> reservation = new LinkedList<Reservation>();

        Menu menu = new Menu();
        MenuItem food = new MenuItem("sss", 1.0, "a");

        for (int i = 0; i < menu.getMenuCategory().getNumberOfEntries(); i++) {
            if (food.getCategory() == menu.getMenuCategory().getEntry(0).getCategoryName()) {
                menu.getMenuCategory().getEntry(0).addFood(food);
            }
        }
    }

    public String reservationHistory(ListInterface<Reservation> reservation) {
        reservation.getEntry(0).toString();
        return " ";
    }
}
