package MovieTicketBookingSystem.model;

public class Theatre {
    public int id;
    public String name;
    public String location;
    public int adminId;

    public Theatre(int id, String name, String location, int adminId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.adminId = adminId;
    }
}
