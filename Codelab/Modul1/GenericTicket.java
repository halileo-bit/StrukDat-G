public class GenericTicket<T> {

    private T bookingCode;
    private String passengerName;

    public GenericTicket(T bookingCode, String passengerName) {
        this.bookingCode = bookingCode;
        this.passengerName = passengerName;
    }

    public T getBookingCode() {
        return bookingCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void displayTicket() {
        System.out.println("=== Railway Ticket Information ===");
        System.out.println("Booking Code       : " + bookingCode);
        System.out.println("Passenger Name     : " + passengerName);
        System.out.println("Booking Code Type: " + bookingCode.getClass().getSimpleName());
        System.out.println(); // Added for spacing between outputs
    }

    public static void main(String[] args) {
        GenericTicket<String> ticket1 = new GenericTicket<>("KA-001", "Andi");
        ticket1.displayTicket();
        GenericTicket<Integer> ticket2 = new GenericTicket<>(1002, "Budi");
        ticket2.displayTicket();
    }
}

