public class RobotMapFactory {

    public RobotMap create(int n, int m) throws RobotMapCreationException {
        return new DefaultRobotMap(n, m);
    }
}
