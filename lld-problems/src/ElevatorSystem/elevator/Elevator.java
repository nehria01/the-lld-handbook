package ElevatorSystem.elevator;

import ElevatorSystem.enums.CarState;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.request.CarCommand;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Elevator implements Runnable {
    private final int id;
    private final int minFloor;
    private final int maxFloor;
    private final AtomicReference<CarState> state = new AtomicReference<>(CarState.IDLE);
    private int currentFloor;
    private Direction direction = Direction.NONE;

    private final NavigableSet<Integer> upRequests  = new TreeSet<>();
    private final NavigableSet<Integer> downRequests = new TreeSet<>(Comparator.reverseOrder());
    private final BlockingQueue<CarCommand> commands = new LinkedBlockingQueue<>();

    public Elevator(int id, int minFloor, int maxFloor, int startFloor) {
        this.id = id;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = startFloor;
    }

    public int getId() {
        return id;
    }

    public void submit(CarCommand carCommand) {
        this.commands.add(carCommand);
    }

    public ElevatorSnapshot getSnapshot() {
        return new ElevatorSnapshot(id, state.get(), direction, currentFloor,
                upRequests.isEmpty() ? null : upRequests.first(),
                downRequests.isEmpty() ? null : downRequests.first(), upRequests.size() + downRequests.size());
    }

    @Override
    public void run() {
        System.out.println("Elevator Car :: " + this.id + " started");
        try {
            while(true) {
                if(!hasPendingStops()) {
                    CarCommand cm = commands.take();
                    handle(cm);
                }
                serviceRequests();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void serviceRequests() throws InterruptedException {
        if(!hasPendingStops()) {
            state.set(CarState.IDLE);
            return;
        }

        if(direction == Direction.UP || direction == Direction.NONE && !upRequests.isEmpty()) {
            direction = Direction.UP;
            Integer nextFloor = upRequests.pollFirst();
            moveTo(nextFloor);
            openAndCloseDoor();
            if(upRequests.isEmpty() && !downRequests.isEmpty()) direction = Direction.DOWN;
        } else {
            direction = Direction.DOWN;
            Integer nextFloor = downRequests.pollFirst();
            moveTo(nextFloor);
            openAndCloseDoor();
            if(downRequests.isEmpty() && !upRequests.isEmpty()) direction = Direction.UP;
        }
    }

    private void moveTo(Integer targetFloor) throws InterruptedException {
        while(currentFloor != targetFloor) {
            System.out.println("Elevator Car :: " + this.id + " at " + currentFloor);
            currentFloor += targetFloor > currentFloor ? 1 : -1;
            Thread.sleep(50);
            if(direction == Direction.UP) {
                if (upRequests.remove(currentFloor)) openAndCloseDoor();
            } else {
                if(downRequests.remove(currentFloor)) openAndCloseDoor();;
            }
        }
    }
    private void handle(CarCommand carCommand) throws InterruptedException {
        System.out.println("Elevator Car :: " + this.id + " handling command " + carCommand);
        switch(carCommand.getType()) {
            case ADD_STOP -> addStop(((CarCommand.AddStop)(carCommand)).getFloor());
            case EMERGENCY_STOP -> emergencyStop();
            case GO_MAINTENANCE -> toMaintenance();
            case RESUME -> resumeFromMaintenance();
        }
    }
    private boolean hasPendingStops() {
        return !upRequests.isEmpty() || !downRequests.isEmpty();
    }

    private void openAndCloseDoor() throws InterruptedException {
        state.set(CarState.DOOR_OPEN);
        System.out.println("Elevator Car :: " + this.id + " door opened at " + currentFloor);
        Thread.sleep(500);
        System.out.println("Elevator Car :: " + this.id + " door closed at " + currentFloor);
        state.set(hasPendingStops()? CarState.MOVING : CarState.IDLE);
    }

    private void addStop(int floor) throws InterruptedException {
        if(floor<minFloor || floor>maxFloor) return;
        if(state.get() == CarState.MAINTENANCE) return;
        if(floor == currentFloor && state.get() != CarState.MOVING) {
            openAndCloseDoor();
            return;
        }
        if(floor>currentFloor) upRequests.add(floor);
        else downRequests.add(floor);
        if(state.get() == CarState.IDLE) {
            direction = (floor > currentFloor) ? Direction.UP : Direction.DOWN;
        }
    }

    private void emergencyStop() {
        upRequests.clear(); downRequests.clear();
        direction = Direction.NONE;
        state.set(CarState.DOOR_OPEN);
    }

    private void toMaintenance() {
        upRequests.clear(); downRequests.clear();
        state.set(CarState.MAINTENANCE);
        direction = Direction.NONE;
    }

    private void resumeFromMaintenance() {
        state.set(CarState.IDLE);
    }
}
