import java.util.List;

public interface RobotMap {

    List<DefaultRobotMap.NewRobot> getRobots();

    void createRobot(Point point) throws RobotCreationException, MaxRobotException;

}
