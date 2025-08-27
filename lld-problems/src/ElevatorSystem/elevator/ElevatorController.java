package ElevatorSystem.elevator;

import ElevatorSystem.dispatcher.Dispatcher;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.request.CarCommand;
import ElevatorSystem.request.HallRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ElevatorController {
    private final List<Elevator> cars;
    private final Dispatcher dispatcher;

    public ElevatorController(int floors, int carCount, Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.cars = new ArrayList<>(carCount);
        for (int i = 0; i < carCount; i++) {
            int randomFloor = ThreadLocalRandom.current().nextInt(1, floors + 1);
            Elevator car = new Elevator(i, 1, floors, randomFloor);
            cars.add(car);
            new Thread(car, "elevator-car-" + i).start();
        }
    }

    public void handleHallCall(int floor, Direction dir) {
        int carId = dispatcher.assignHallRequest(new HallRequest(floor, dir), snapshots());
        cars.get(carId).submit(new CarCommand.AddStop(floor));
    }

    public void handleCarCall(int carId, int floor) {
        cars.get(carId).submit(new CarCommand.AddStop(floor));
    }

    private List<ElevatorSnapshot> snapshots() {
        List<ElevatorSnapshot> list = new ArrayList<>(cars.size());
        for (Elevator c: cars) list.add(c.getSnapshot());
        return list;
    }

}
