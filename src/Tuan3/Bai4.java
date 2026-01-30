package Tuan3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Bai4 {
    public static void main(String[] args){
        try{
            InetAddress local=InetAddress.getLocalHost();
            String localIP=local.getHostAddress();
            String subnet=localIP.substring(0,localIP.lastIndexOf(".")+1);
            for(int i=1; i<=256;i++){
                String host=subnet+i;
                InetAddress inet01=InetAddress.getByName(host);
                System.out.println("Checking IP "+host);
                try{
                    if(inet01.isReachable(500)){
                        System.out.println("==>" +host+" is online");
                    }else {
                        System.out.println(" ");
                    }

                }catch (IOException e){
                    System.err.println(e.getMessage());
                }

            }
            System.out.println(localIP);

        }catch (UnknownHostException e){
            System.err.println(e.getMessage());
        }


    }
}
