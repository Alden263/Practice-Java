package Tuan3;

import java.net.InetAddress;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("nhap domain:");
            String domain = scanner.nextLine();
            if(domain.equals("exit")){
                break;
            }
            try {
                InetAddress inet = InetAddress.getByName(domain);
                System.out.println("Dia chi IP cua " + domain + " la: " + inet.getHostAddress());
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }
    }
}
