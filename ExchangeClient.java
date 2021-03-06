/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package invader;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author atamarkin2
 */
public class ExchangeClient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        //auto login
        Socket socket = new Socket("codebb.cloudapp.net", 17429);
        DataOutputStream outToServer =
                new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String sentence = "WaterStreet 123456";
        outToServer.writeBytes(sentence + '\n');

        int totalTime = 20 * 60;
        int elapsedTime = 0;
        int seenMine = 0;
        int visitedMine = 0;
        ArrayList<String> actions;
        while (elapsedTime < totalTime) {
            outToServer.writeBytes("STATUS" + '\n');
            String statusResult = inFromServer.readLine();
            System.out.println(statusResult);
            Status status = BaseFunctions.parseStatus(statusResult);
            actions = Action.decideAction(elapsedTime, totalTime, seenMine, visitedMine, status);
            String scanResult = Action.executeAction(actions, outToServer, inFromServer);
            if (scanResult != null) {}
            elapsedTime++;
        }

    }

}
