/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package invader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        PrintWriter pout = new PrintWriter(socket.getOutputStream());
        BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pout.println("WaterStreet 123456");
        pout.flush();

        int totalTime = 20 * 60;
        int elapsedTime = 0;
        int seenMine = 0;
        int visitedMine = 0;
        ArrayList<String> actions;
        while (elapsedTime < totalTime) {
            actions = Action.decideAction(elapsedTime, totalTime, seenMine, visitedMine);
            String scanResult = Action.executeAction(actions, pout, bin);
            if (scanResult != null) {}
            elapsedTime++;
        }



    }

}
