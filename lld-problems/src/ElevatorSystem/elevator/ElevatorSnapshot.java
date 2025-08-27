package ElevatorSystem.elevator;

import ElevatorSystem.enums.CarState;
import ElevatorSystem.enums.Direction;

public class ElevatorSnapshot {
    private int id;
    private CarState carState;
    private Direction direction;
    private int currentFloor;
    private Integer nextUpRequest;
    private Integer nextDownRequest;
    private Integer pendingStopsCount;

    public ElevatorSnapshot(int id, CarState carState, Direction direction, int currentFloor, Integer nextUpRequest, Integer nextDownRequest, Integer pendingStopsCount) {
        this.id = id;
        this.carState = carState;
        this.direction = direction;
        this.currentFloor = currentFloor;
        this.nextUpRequest = nextUpRequest;
        this.nextDownRequest = nextDownRequest;
        this.pendingStopsCount = pendingStopsCount;
    }

    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public CarState getCarState() {
        return carState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Integer getNextUpRequest() {
        return nextUpRequest;
    }

    public Integer getNextDownRequest() {
        return nextDownRequest;
    }

    public int getPendingStopsCount() {
        return pendingStopsCount;
    }


    @Override
    public String toString() {
        return "ElevatorSnapshot{" +
                "id=" + id +
                ", carState=" + carState +
                ", direction=" + direction +
                ", currentFloor=" + currentFloor +
                ", nextUpRequest=" + nextUpRequest +
                ", nextDownRequest=" + nextDownRequest +
                '}';
    }
}
