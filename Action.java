import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Action {

    public static ArrayList<String> decideAction(int elapsedTime, int totalTime, int seenMine, int visitedMine, Status status) {
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
        ArrayList<String> testStrings = new ArrayList<>();
        testStrings.add(BaseFunctions.moveToPoint(status.x,status.y,status.dx,status.dy, 2500,2500));
        testStrings.add(BaseFunctions.moveToPoint(status.x,status.y,status.dx,status.dy, 7500,2500));
        testStrings.add(BaseFunctions.moveToPoint(status.x,status.y,status.dx,status.dy, 7500,7500));
        testStrings.add(BaseFunctions.moveToPoint(status.x,status.y,status.dx,status.dy, 2500,7500));

        return testStrings;
    }


    public static String executeAction(ArrayList<String> action, DataOutputStream outToServer, BufferedReader inFromServer) throws IOException{

        String scanResult = null;
        for (int i = 0; i < action.size(); i++) {
            outToServer.writeBytes(action.get(i) + '\n');
            String result;
            if ((result = inFromServer.readLine()) != null) {
                System.out.println(result);
                if (BaseFunctions.parseStatus(result) != null) {
                    scanResult = result;
                }
            }
        }
        return scanResult;
    }

}
