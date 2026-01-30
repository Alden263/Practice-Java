package Tuan3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bai2 {
    public static void main(String[] args){
        ReadFileDomain("src/Tuan3/domain.txt");


    }
    public static void ReadFileDomain(String filePath){
        File Domain=new File(filePath);
        try(Scanner reader=new Scanner(Domain)){
            while(reader.hasNextLine()){
                String Name= reader.nextLine();
                try {
                    InetAddress inet01=InetAddress.getByName(Name);
                    System.out.println("Domain "+Name+"có IP là "+inet01.getHostAddress());

                }catch (UnknownHostException e){
                    System.err.println(e.getMessage());
                }


            }

        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }


    }
}
