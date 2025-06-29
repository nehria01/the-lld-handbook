package ParkingLotSystem.spot;

public abstract class Spot {
    private SpotType spotType;
    private int rowNumber;
    private int colNumber;

    public Spot(SpotType spotType, int rowNumber, int colNumber) {
        this.spotType = spotType;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }
}
