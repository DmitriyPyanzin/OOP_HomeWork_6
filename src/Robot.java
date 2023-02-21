public interface Robot {

    void move(int step) throws RobotMoveException;

    void move() throws RobotMoveException;

    DefaultRobotMap.MapPoint getPoint();

    void changeDirection(Direction direction);

}
