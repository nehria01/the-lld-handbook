package ElevatorSystem.request;

import ElevatorSystem.enums.Direction;

public class HallRequest {
    private int floor;
    private Direction direction;

    public HallRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "HallRequest{" +
                "floor=" + floor +
                ", direction=" + direction +
                '}';
    }
}
