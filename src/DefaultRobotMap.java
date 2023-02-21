import java.util.ArrayList;
import java.util.List;

public class DefaultRobotMap implements RobotMap {

    private final int n;
    private final int m;
    private final List<NewRobot> newRobots;

    private int maxRobot;

    public List<NewRobot> getRobots() {
        return newRobots;

    }

    public DefaultRobotMap(int n, int m) throws RobotMapCreationException {
        if (n < 10 || m < 10 || n > 100 || m > 100) {
            throw new RobotMapCreationException("Некорректный размер карты");
        }

        this.n = n;
        this.m = m;
        this.newRobots = new ArrayList<>();
    }

    public DefaultRobotMap(int n, int m, int maxRobot) throws RobotMapCreationException, MaxRobotException {
        if (n < 10 || m < 10 || n > 100 || m > 100)
            throw new RobotMapCreationException("Некорректный размер карты");
        if(maxRobot < 0)
            throw new MaxRobotException("Количество роботов не может быть отрицательное число");

        this.n = n;
        this.m = m;
        this.newRobots = new ArrayList<>();
        this.maxRobot = maxRobot;
    }


    public void createRobot(Point point) throws RobotCreationException, MaxRobotException {
        final MapPoint robotPosition;
        try {
            validatePoint(point);
            robotPosition = new MapPoint(point.getX(), point.getY());
        } catch (PointValidationException e) {
            throw new RobotCreationException(e.getMessage());
        }

        NewRobot newRobot = new NewRobot(robotPosition);
        if (newRobots.size() > maxRobot)
            throw new MaxRobotException("Достигнуто максимальное количество роботов");
        newRobots.add(newRobot);
    }

    private void validatePoint(Point point) throws PointValidationException {
        validatePointIsFree(point);
    }

    private void validatePointIsFree(Point point) throws PointValidationException {
        for (NewRobot newRobot : newRobots) {
            if (point.equals(newRobot.getPoint())) {
                throw new PointValidationException("Позиция " + point + " занята другим роботом: " + newRobot);
            }
        }
    }

    public class NewRobot implements Robot{

        public static final Direction DEFAULT_DIRECTION = Direction.TOP;

        private static Long idSequence = 1L;
        private final Long id;
        private MapPoint point;
        private Direction direction;

        public NewRobot(MapPoint point) {
            this.id = idSequence++;
            this.point = point;
            this.direction = DEFAULT_DIRECTION;
        }

        public void move(int step) throws RobotMoveException {
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() - step, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + step);
                    case BOTTOM -> new MapPoint(point.getX() + step, point.getY());
                    case LEFT -> new MapPoint(point.getX(), point.getY() - step);
                };

                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
        }

        public void move() throws RobotMoveException {
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() - 1, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + 1);
                    case BOTTOM -> new MapPoint(point.getX() + 1, point.getY());
                    case LEFT -> new MapPoint(point.getX(), point.getY() - 1);
                };

                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
        }


        public void changeDirection(Direction direction) {
            this.direction = direction;

        }

        public MapPoint getPoint() {
            return point;

        }

        @Override
        public String toString() {
            return "Robot " + id + ", координаты " + point + ", направление " + direction;

        }
    }

    public class MapPoint extends Point {

        public MapPoint(int x, int y) throws PointValidationException {
            super(x, y);

            if (x < 10 || x > n || y < 10 || y > m) {
                throw new PointValidationException("Недопустимое значение Point: " + this);
            }
        }
    }
}
