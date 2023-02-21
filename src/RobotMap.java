import java.util.List;

public interface RobotMap {

    List<DefaultRobotMap.Robot> getRobots();

    void createRobot(Point point) throws RobotCreationException, MaxRobotException;

}
