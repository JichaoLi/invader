import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Action {

    public static ArrayList<String> decideAction(int elapsedTime, int totalTime, int seenMine, int visitedMine) {
        if (seenMine < 10) {
            /*
                if we have seen less than 10, we do ..
             */

        } else if (seenMine > 10 && visitedMine < seenMine) {
            /*
                if we've seen a lot mine but visited only a few, we do ..
             */

        } else if (elapsedTime > totalTime / 2) {
            /*
                if visitedMine > seenMine and we have less than half the total time, we do ..
             */

        }
        return null;
    }


    public static String executeAction(ArrayList<String> action, PrintWriter pout, BufferedReader bin) throws IOException{

        String scanResult = null;
        for (int i = 0; i < action.size(); i++) {
            pout.println(action.get(i));
            pout.flush();
            String result;
            if ((result = bin.readLine()) != null) {
                System.out.println(result);
                if (BaseFunctions.parseStatus(result) != null) {
                    scanResult = result;
                }
            }
        }
        return scanResult;
    }

}
