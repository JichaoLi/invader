import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BaseFunctions {
	public static String moveToPoint(double x, double y, double dx, double dy, double a, double b) {
		double r1 = Math.sqrt((a - x) * (a - x) + (b - y) * (b - y));
		double r2 = Math.sqrt(dx * dx + dy * dy);
		double ax = (a - x) / r1 - dx / r2;
		double ay = (b - y) / r1 - dy / r2;
		double theta = Math.atan2(ay, ax);
		return accelerate(theta >= 0 ? theta : 2 * Math.PI + theta); // convert
																		// to 0
																		// to
																		// 2pi
	}

	public static Point getNearestNeighbour(Iterable<Point> unvisited, double x, double y, double dx, double dy) {
		double distance = Double.MAX_VALUE;
		Point p = null;
		for (Point point : unvisited) {
			double a = point.x;
			double b = point.y;
			double d = (x - a) * (x - a) + (y - b) * (y - b);
			if (d < distance) {
				p = point;
				distance = d;
			}
		}
		return p;
	}

	public static Status parseStatus(String status) {
		Status s = new Status();
		Scanner scanner = new Scanner(status);
		try {
			scanner.next("STATUS_OUT");
		} catch (Exception e) {
			return null;
		}
		s.x = scanner.nextDouble();
		s.y = scanner.nextDouble();
		s.dx = scanner.nextDouble();
		s.dy = scanner.nextDouble();
		scanner.next("MINES");
		s.mines = new ArrayList<>();
		s.capturedByOthers = new HashSet<>();
		int ms = scanner.nextInt();
		for (int i = 0; i < ms; i++) {
			String owner = scanner.next("[^\\s]+");
			Point point = new Point(scanner.nextDouble(), scanner.nextDouble());
			if (!owner.equals(Conf.myName)) {
				s.capturedByOthers.add(point);
			}
			s.mines.add(point);
			System.out.println(s.mines.size());
		}
		scanner.next("PLAYERS");
		s.players = new ArrayList<>();
		int ps = scanner.nextInt();
		for (int i = 0; i < ps; i++) {
			double x = scanner.nextDouble();
			double y = scanner.nextDouble();
			scanner.nextDouble();
			scanner.nextDouble();
			s.players.add(new Point(x, y));
		}
		scanner.next("BOMBS");
		s.bombs = new ArrayList<>();
		int bs = scanner.nextInt();
		for (int i = 0; i < bs; i++) {
			s.bombs.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
		}
		return s;
	}

	public static String accelerate(Double rad) {
		return "ACCELERATE " + rad.toString() + " 1";
	}

	public static String bomb(Double x, Double y) {
		return "BOMB " + x.toString() + " " + y.toString();
	}

	public static String scan(Double x, Double y) {
		return "SCAN " + x.toString() + " " + y.toString();
	}

	public static String ScoreBoard() {
		return "SCOREBOARD";
	}

	public static ArrayList<ScoreEntry> parseScoreBoard(String str) {
		Scanner scanner = new Scanner(str);
		scanner.next("SCOREBOARD_OUT");
		ArrayList<ScoreEntry> entries = new ArrayList<>();
		while (true) {
			ScoreEntry scoreEntry = new ScoreEntry();
			try {
				scoreEntry.player = scanner.next("[^\\s]+");
			} catch (Exception e) {
				break;
			}
			scoreEntry.score = scanner.nextInt();
			scoreEntry.mines = scanner.nextInt();
			entries.add(scoreEntry);
		}
		return entries;

	}

	public static Conf parseConf(String str) {
		Conf conf = new Conf();
		Scanner scanner = new Scanner(str);
		scanner.next("CONFIGURATIONS_OUT");
		scanner.next("[^\\s-]+");
		conf.mapWidth = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.mapHeight = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.captureRadius = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.visionRadius = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.friction = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.brakeFriction = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.bombPlaceRadius = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.bombEffectRadius = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.bombDelay = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.bombPower = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.scanRadius = scanner.nextDouble();
		scanner.next("[^\\s-]+");
		conf.scanDelay = scanner.nextDouble();
		return conf;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Status status = parseStatus(scanner.nextLine());
		System.out.println(status.x);
	}
}
