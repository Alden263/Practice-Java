package Tuan3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Nhập vào tên domain hoặc exit để thoát: ");
        while(true){

            String s=scanner.nextLine();
            if(s.equalsIgnoreCase("exit")){
                break;
            }
            try{
                InetAddress inet01=InetAddress.getByName(s);
                System.out.println("Domain "+s+" có IP "+inet01.getHostAddress());

            }catch (UnknownHostException e){
                System.err.println(e.getMessage());
            }
        }


    }

}
