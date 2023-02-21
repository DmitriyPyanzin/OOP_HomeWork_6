public class RobotMapFactory {

    public RobotMap create(int n, int m) throws RobotMapCreationException {
        return new DefaultRobotMap(n, m);
    }

    public RobotMap create(int n, int m, int max) throws RobotMapCreationException, MaxRobotException {
        return new DefaultRobotMap(n, m, max);
    }
}
