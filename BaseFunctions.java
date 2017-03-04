import java.util.ArrayList;
import java.util.Scanner;

public class BaseFunctions {
	public static String moveToPoint(double x, double y, double dx, double dy, double a, double b) {
		double ax = (a - x) - dx;
		double ay = (b - y) - dy;
		return accelerate(Math.atan2(ay, ax) + Math.PI); // convert to 0 to 2pi
	}

	public static Point getNearestNeighbour(ArrayList<Point> unvisited, double x, double y, double dx, double dy) {
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
		scanner.next("STATUS_OUT");
		s.x = scanner.nextDouble();
		s.y = scanner.nextDouble();
		s.dx = scanner.nextDouble();
		s.dy = scanner.nextDouble();
		scanner.next("MINES");
		s.mines = new ArrayList<>(scanner.nextInt());
		for (int i = 0; i < s.mines.size(); i++) {
			scanner.next("--");
			s.mines.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
		}
		scanner.next("PLAYERS");
		s.players = new ArrayList<>(scanner.nextInt());
		for (int i = 0; i < s.players.size(); i++) {
			s.players.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
		}
		scanner.next("BOMBS");
		s.bombs = new ArrayList<>(scanner.nextInt());
		for (int i = 0; i < s.bombs.size(); i++) {
			s.bombs.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
		}
		return s;
	}

	public static String accelerate(Double rad) {
		return "ACCELERATE" + rad.toString() + " 1";
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
				scoreEntry.player = scanner.next("[^\\s-]+");
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
		String string = scanner.nextLine();
		Conf conf = parseConf(string);
		System.out.println(conf.scanDelay);
	}
}
