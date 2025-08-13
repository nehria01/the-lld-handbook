package MovieTicketBookingSystem.model;

public class Seat {
    public int id;
    public int theatreId;
    public int hallId;
    public int row;
    public int col;
    public boolean isAvailable;
    public double price;

    public Seat(int id, int theatreId, int hallId, int row, int col, double price) {
        this.id = id;
        this.theatreId = theatreId;
        this.hallId = hallId;
        this.row = row;
        this.col = col;
        this.isAvailable = true;
        this.price = price;
    }

    public String getSeatKey() {
        return theatreId + "_" + hallId + "_" + row + "_" + col;
    }
}
