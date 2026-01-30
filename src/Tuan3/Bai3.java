package Tuan3;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args){
        ReadFileIP("src/Tuan3/IP.txt");

    }
    public static void ReadFileIP(String filePath){
        File IP=new File(filePath);
        try(Scanner reader=new Scanner(IP)){
            while (reader.hasNextLine()){
                String data= reader.nextLine();
                try{
                    InetAddress inet01=InetAddress.getByName(data);

                    if(inet01.isReachable(1000)){
                        System.out.println("IP "+data+" is reachable");
                    }else {
                        System.out.println("IP "+data+" is not reachable");
                    }
                }catch (UnknownHostException e){
                    System.out.println("Lỗi chương trình: "+e.getMessage());

                }
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
