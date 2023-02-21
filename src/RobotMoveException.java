public class RobotMoveException extends Exception {

    private final DefaultRobotMap.NewRobot newRobot;

    public RobotMoveException(String message, DefaultRobotMap.NewRobot newRobot) {
        super(message);
        this.newRobot = newRobot;
    }

    public DefaultRobotMap.NewRobot getRobot() {
        return newRobot;

    }
}