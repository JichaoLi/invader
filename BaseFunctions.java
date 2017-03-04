import java.util.ArrayList;
import java.util.Scanner;

public class BaseFunctions {

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

	public String accelerate(Double rad) {
		return "ACCELERATE" + rad.toString() + " 1";
	}

	public String bomb(Double x, Double y) {
		return "BOMB " + x.toString() + " " + y.toString();
	}

	public String scan(Double x, Double y) {
		return "SCAN " + x.toString() + " " + y.toString();
	}

	public String ScoreBoard() {
		return "SCOREBOARD";
	}

	public ArrayList<ScoreEntry> parseScoreBoard(String str) {
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
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// System.out.println(scanner.next("MINES"));
	// }
}
