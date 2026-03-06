package Tuan3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Bai4 {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("sgu.edu.vn", 443);
            String myIP = socket.getLocalAddress().getHostAddress();
            String networkId  = myIP.split("\\.")[0] + "." + myIP.split("\\.")[1] + "." + myIP.split("\\.")[2];
            for( int i=1; i<=254; i++){
                InetAddress inet = InetAddress.getByName( networkId + "." + i);
                    if (inet.isReachable(1000)) 
                        System.out.println("IP " + inet.getHostAddress() + " is online");
                            
                    else
                        System.out.println("IP " + inet.getHostAddress() + " is not online");
                    }

        }catch (IOException e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
