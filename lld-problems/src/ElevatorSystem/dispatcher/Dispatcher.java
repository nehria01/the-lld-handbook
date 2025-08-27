package ElevatorSystem.dispatcher;

import ElevatorSystem.elevator.ElevatorSnapshot;
import ElevatorSystem.request.HallRequest;

import java.util.List;

public interface Dispatcher {
    int assignHallRequest(HallRequest hallRequest, List<ElevatorSnapshot> elevatorSnapshots);
}
