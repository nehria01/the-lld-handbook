package ElevatorSystem.elevator;

import ElevatorSystem.enums.Direction;
import ElevatorSystem.enums.ElevatorStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Elevator implements Runnable{
    private int id;
    private int currentFloor;
    private Direction elevatorDirection;
    private ElevatorStatus elevatorStatus;
    private ArrayList<Request> allRequests;
    private int numActiveRequest;

    public Elevator(int id) {
        this.id = id;
        this.numActiveRequest = 0;
        this.currentFloor = 0;
        this.elevatorDirection = Direction.UP;
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.allRequests = new ArrayList<>();
    }

    private Request getNextRequest() {
        Request nextRequest = allRequests.stream().
                filter(req ->
                (elevatorDirection.equals(Direction.UP) && req.getSourceFloor() >= currentFloor) ||
                (elevatorDirection.equals(Direction.DOWN) && req.getSourceFloor() <= currentFloor))
                .min(Comparator.comparing(req -> Math.abs(currentFloor - req.getSourceFloor())))
                .orElse(null);
        return nextRequest;
    }

    private void serveRequest(Request request) {
        System.out.println("Elevator :: " + id +" processing request from floor " + request.getSourceFloor()
        + "  to " + request.getDestinationFloor());
        moveToFloor(request.getSourceFloor());
        System.out.println("Elevator :: " + id +" Picked up passenger from floor "+ currentFloor);

        moveToFloor(request.getDestinationFloor());
        System.out.println("Elevator :: " + id +" Dropped passenger at floor " + currentFloor);
        allRequests.remove(request);
        numActiveRequest--;
        elevatorStatus = ElevatorStatus.IDLE;
    }

    private void moveToFloor(int targetFloor) {
        while(currentFloor != targetFloor) {
            if(currentFloor < targetFloor)
                currentFloor++;
            else currentFloor--;

            System.out.println("Elevator " +id + " at floor " + currentFloor);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private synchronized void processRequests() {
        while(true) {
            try {
                //no request - set status idle and sleep
                if(allRequests.isEmpty()) {
                    this.elevatorStatus = ElevatorStatus.IDLE;
                    wait();
                }
                //get next request
                Request nextRequest = getNextRequest();
                if(Objects.isNull(nextRequest)) {
                    //no request in current direction change direction
                    this.elevatorDirection = this.elevatorDirection.equals(Direction.UP) ? Direction.DOWN : Direction.UP;
                    continue;
                }
                serveRequest(nextRequest);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void run() {
        System.out.println("Starting elevator with id :: " + id);
        processRequests();
    }

    public synchronized void addRequest(Request request) {
        allRequests.add(request);
        if(elevatorStatus.equals(ElevatorStatus.IDLE))
            elevatorStatus = ElevatorStatus.MOVING;
        notifyAll();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getElevatorDirection() {
        return elevatorDirection;
    }

    public ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }

    public int getId() {
        return id;
    }
}
