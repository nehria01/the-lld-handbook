package ElevatorSystem.dispatcher;

import ElevatorSystem.elevator.ElevatorSnapshot;
import ElevatorSystem.enums.CarState;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.request.HallRequest;

import java.util.List;

public class NearestCarDispatcher implements Dispatcher{
    @Override
    public int assignHallRequest(HallRequest hallRequest, List<ElevatorSnapshot> elevatorSnapshots) {
        System.out.println("Finding best elevator car for hallRequest :: " + hallRequest);
        int bestCar = -1;
        double bestCost = Double.POSITIVE_INFINITY;

        for(ElevatorSnapshot car : elevatorSnapshots) {
            double carCost = cost(hallRequest, car);
            if(carCost < bestCost) {
                bestCost = carCost;
                bestCar = car.getId();
            }
            System.out.println("Request :: " + hallRequest + " cost for car " + car.getId() +" " +carCost);
        }
        System.out.println("Request " + hallRequest + " assigned to Car " + bestCar);
        return bestCar;
    }

    private double cost(HallRequest request, ElevatorSnapshot car) {
        int distance = Math.abs(request.getFloor() - car.getCurrentFloor());

        double directionPenalty = 0.0;
        if (car.getDirection() == Direction.NONE) {
            directionPenalty = -0.5;
        } else {
            boolean directionsAligned = (car.getDirection() == request.getDirection()) &&
                    ((car.getDirection() == Direction.UP && car.getCurrentFloor() <= request.getFloor())
                            || (car.getDirection() == Direction.DOWN && car.getCurrentFloor() >= request.getFloor()));
            directionPenalty = directionsAligned ? 0.0 : 2.0;
        }

        double statePenalty = (car.getCarState() == CarState.MAINTENANCE) ? 1e6 :
                (car.getCarState() == CarState.IDLE ? 0.0 : 0.5);

        double loadPenalty = car.getPendingStopsCount() * 2.0;

        return distance + directionPenalty + statePenalty + loadPenalty;
    }
}
