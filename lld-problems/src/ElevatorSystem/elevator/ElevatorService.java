package ElevatorSystem.elevator;

import ElevatorSystem.strategy.NearestElevatorStrategy;
import ElevatorSystem.strategy.SchedulerStrategy;

import java.util.ArrayList;
import java.util.Objects;

public class ElevatorService {
    private ArrayList<Elevator> elevators;
    private SchedulerStrategy schedulerStrategy;

    public ElevatorService(int numElevators) {
        schedulerStrategy = new NearestElevatorStrategy();
        this.elevators = new ArrayList<>();
        for(int i=0;i<numElevators;i++) {
            Elevator elevator = new Elevator(i+1);
            elevators.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(int source, int destination) {
        Request request = new Request(source, destination);
        Elevator elevator = schedulerStrategy.getOptimalElevator(this.elevators, request);
        if(Objects.nonNull(elevator))
                elevator.addRequest(request);
        else
            System.out.println("Cannot serve the request at this time");
    }

    void setSchedulerStrategy(SchedulerStrategy schedulerStrategy) {
        this.schedulerStrategy = schedulerStrategy;
    }
}
