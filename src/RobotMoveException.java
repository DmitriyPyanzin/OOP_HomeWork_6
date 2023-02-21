public class RobotMoveException extends Exception {

    private final DefaultRobotMap.Robot robot;

    public RobotMoveException(String message, DefaultRobotMap.Robot robot) {
        super(message);
        this.robot = robot;
    }

    public DefaultRobotMap.Robot getRobot() {
        return robot;

    }
}