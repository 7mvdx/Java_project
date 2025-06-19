package Project;

class Reservation {
    private String customerName;
    private String reservationTime;

    public Reservation(String customerName, String reservationTime) {
        this.customerName = customerName;
        this.reservationTime = reservationTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Time: " + reservationTime;
    }
}