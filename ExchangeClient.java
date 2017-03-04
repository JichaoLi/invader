/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

        if (args.length < 4) {
            //host : codebb.cloudapp.net , port : 17429, user : WaterStreet, password : 123456
            System.out.println("Usage: \nclientTask <host> <port> <user> <password>");

        }

        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        PrintWriter pout = new PrintWriter(socket.getOutputStream());
        BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pout.println(args[2] + " " + args[3]);

        const int totalTime = 20 * 3;
        int elapsedTime = 0;
        int seenMine = 0;
        int visitedMine = 0;



        pout.println("ACCELERATE 2 1");
        pout.flush();
        String line;
        while ((line = bin.readLine()) != null) {
            System.out.println(line);
        }
    }

}
