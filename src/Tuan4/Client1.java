package Tuan4;

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {
    public static void main (String []args){
        String host="localhost";
        int port=2000;
        try(Socket socket1=new Socket(host,port)){
            BufferedReader inStream1=new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            BufferedWriter outStream1=new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
            Scanner scanner=new Scanner(System.in);
            int n;
            while (true){
                System.out.print("Nhập số nguyên dương n: ");
                n=scanner.nextInt();
                outStream1.write(n +"\n");
                outStream1.flush();
                System.out.println("Server phản hồi: " );
                String response="";
                while ((response = inStream1.readLine()) != null) {

                    if (response.equals("Done")) {
                        break; // Server báo đã gửi xong kết quả của số này, dừng đọc!
                    }
                    System.out.println("  " + response); // In ra kết quả (VD: Kết quả 2*2)
                }


            }



        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
