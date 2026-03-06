package Tuan3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Bai3 {
    public static void main(String[] args) {
        String ip = "101.99.57.35";
        try {
            InetAddress inet = InetAddress.getByName(ip);
            try {
                if (inet.isReachable(1000)) 
                    System.out.println("IP " + ip + " is reachable");
                
                else
                    System.out.println("IP " + ip + " is not reachable");
            } catch(IOException e){
                System.out.println("IP " + ip + " is not reachable");
            }
        } catch (UnknownHostException e){
            System.out.println("IP " + ip + " is not reachable");
        }

    }
}
