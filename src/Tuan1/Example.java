package Tuan1;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Example {
    public static void main(String[] args){
        Ex1();
        Ex2();
    }
    public static void Ex1(){
        try{
            String domain="sgu.edu.vn";
            InetAddress inet=InetAddress.getByName(domain);
            System.out.println("Domain "+domain+" có IP "+inet.getHostAddress());

        }catch (UnknownHostException e){
            System.err.println(e.getMessage());
        }
    }
    public static void Ex2(){
        try{
            String domain1="amazon.com";
            InetAddress[] inetAddList=InetAddress.getAllByName(domain1);
            for(InetAddress inetAdd: inetAddList){
                System.out.println(inetAdd.getHostAddress());
            }

        }catch (UnknownHostException e){
            System.err.println(e.getMessage());
        }
    }
}
