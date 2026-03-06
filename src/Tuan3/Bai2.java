package Tuan3;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Bai2("src/Tuan3/domain.txt");
    }
    
    public static void Bai2(String filepath){
        File domain= new File(filepath);
        try(Scanner reader= new Scanner(domain)){
            while (reader.hasNextLine()){
                String line= reader.nextLine();
                try{
                    InetAddress inet = InetAddress.getByName(line);
                    System.out.println("Dia chi IP cua " + line + " la: " + inet.getHostAddress());
                }catch(UnknownHostException e){
                    System.err.println(e.getMessage());
                }
                
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }   
    }
}
