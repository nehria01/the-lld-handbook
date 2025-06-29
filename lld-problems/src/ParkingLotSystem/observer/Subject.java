package ParkingLotSystem.observer;

public interface Subject {
    public void registerObserver(ParkingLevelObserver parkingLotObserver);
    public void removeObserver(ParkingLevelObserver parkingLevelObserver);
    public void notifyObservers();
}
