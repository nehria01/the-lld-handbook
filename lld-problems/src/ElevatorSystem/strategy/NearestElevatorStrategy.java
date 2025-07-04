package ElevatorSystem.strategy;

import ElevatorSystem.elevator.Elevator;
import ElevatorSystem.elevator.Request;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.enums.ElevatorStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class NearestElevatorStrategy implements SchedulerStrategy{
    @Override
    public Elevator getOptimalElevator(List<Elevator> elevatorList, Request request) {
        Direction requestDirection = request.getSourceFloor() > request.getDestinationFloor() ? Direction.DOWN : Direction.UP;
        Elevator optimalElevator = null;
        //assign nearest idle elevators
        optimalElevator = elevatorList.stream()
                .filter(elevator -> elevator.getElevatorStatus().equals(ElevatorStatus.IDLE))
                .min(Comparator.comparing(elevator -> Math.abs(elevator.getCurrentFloor()- request.getSourceFloor())))
                .orElse(null);

        //assign nearest elevator moving in same direction
        if(Objects.isNull(optimalElevator)) {
            optimalElevator = elevatorList.stream()
                    .filter(elevator -> elevator.getElevatorDirection().equals(requestDirection))
                    .min(Comparator.comparing(elevator -> Math.abs(elevator.getCurrentFloor()- request.getSourceFloor())))
                    .orElse(null);
        }

        //assign any elevator
        if(Objects.isNull(optimalElevator)) {
            optimalElevator = elevatorList.stream()
                    .min(Comparator.comparing(elevator -> Math.abs(elevator.getCurrentFloor()- request.getSourceFloor())))
                    .orElse(null);
        }


        System.out.println("Elevator Status Snapshot:");
        System.out.println("Serving request " + request.getSourceFloor() + "-------" + request.getDestinationFloor());
        for (Elevator e : elevatorList) {
            System.out.println("Elevator " + e.getId() + " - Floor: " + e.getCurrentFloor()
                    + ", Status: " + e.getElevatorStatus()
                    + ", Direction: " + e.getElevatorDirection());
        }
        System.out.println("Selected Elevator: " + optimalElevator.getId());

        return optimalElevator;
    }
}
