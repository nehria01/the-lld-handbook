package ElevatorSystem.request;

public abstract class CarCommand {
    public enum Type {ADD_STOP, EMERGENCY_STOP, GO_MAINTENANCE, RESUME}
    public abstract Type getType();

    public static class AddStop extends CarCommand {
        private int floor;
        public AddStop(int floor) {
            this.floor = floor;
        }
        public int getFloor() {
            return this.floor;
        }

        @Override
        public Type getType() {
            return Type.ADD_STOP;
        }

        @Override
        public String toString() {
            return "AddStop{" +
                    "floor=" + floor +
                    '}';
        }
    }

    public static class EmergencyStop extends CarCommand {

        @Override
        public Type getType() {
            return Type.EMERGENCY_STOP;
        }
    }

    public static class GoMaintenance extends  CarCommand {

        @Override
        public Type getType() {
            return Type.GO_MAINTENANCE;
        }
    }

    public static class Resume extends CarCommand {

        @Override
        public Type getType() {
            return Type.RESUME;
        }
    }

}
