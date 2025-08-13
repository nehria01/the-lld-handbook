package MovieTicketBookingSystem.model;

public class Hall {
    public int id;
    public int theatreId;
    public int rows;
    public int cols;

    public Hall(int id, int theatreId, int rows, int cols) {
        this.id = id;
        this.theatreId = theatreId;
        this.rows = rows;
        this.cols = cols;
    }
}
