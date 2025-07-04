package ElevatorSystem;

import ElevatorSystem.elevator.ElevatorService;

public class ElevatorTestDrive {
    public static void main(String[] args) throws InterruptedException {
        ElevatorService service = new ElevatorService(4); // 2 elevators, capacity 3

       //TEST 1: Single Request
        service.requestElevator(0, 5);
        Thread.sleep(2500);
        //TEST 2: Multiple Requests in Same Direction
        service.requestElevator(1, 6);
        service.requestElevator(2, 7);
        Thread.sleep(2500);
        //TEST 3: Requests in Opposite Directions
        service.requestElevator(8, 2);  // Elevator will go DOWN
        service.requestElevator(3, 9);  // Elevator should go UP
        Thread.sleep(2500);
        //TEST 4: Request While Elevators Are Busy
        service.requestElevator(4, 10);
        Thread.sleep(2500);
        //TEST 5: Load Balancing Between Elevators
        service.requestElevator(0, 2);
        service.requestElevator(3, 1);
        service.requestElevator(5, 9);
        service.requestElevator(6, 0);
    }
}
