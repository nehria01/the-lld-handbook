package ElevatorSystem;

import ElevatorSystem.dispatcher.Dispatcher;
import ElevatorSystem.dispatcher.NearestCarDispatcher;
import ElevatorSystem.elevator.ElevatorController;
import ElevatorSystem.enums.Direction;

public class ElevatorTestDrive {
    public static void main(String[] args) throws Exception {
        Dispatcher dispatcher = new NearestCarDispatcher();
        ElevatorController controller = new ElevatorController(20, 3, dispatcher);

        controller.handleHallCall(7, Direction.UP);
        controller.handleHallCall(15, Direction.DOWN);
        controller.handleHallCall(3, Direction.UP);

        controller.handleCarCall(0, 12);
    }
}
