package ElevatorSystem.strategy;

import ElevatorSystem.elevator.Elevator;
import ElevatorSystem.elevator.Request;

import java.util.List;

public interface SchedulerStrategy {
    Elevator getOptimalElevator(List<Elevator> elevatorList, Request request);
}
