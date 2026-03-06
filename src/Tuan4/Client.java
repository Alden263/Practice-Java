package Tuan4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        String host="localhost";
        int port=2000;
        try{
            Socket socket=new Socket(host,port);
            BufferedReader inStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outStream=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner=new Scanner(System.in);
            String input="";
            while(true){
                // Nhận dữ liệu từ bàn phím -> socket qua output stream
                System.out.print("Vui lòng nhập dữ liệu: ");
                input = scanner.nextLine();
                outStream.write(input + "\r\n");
                outStream.flush();
                // Chờ nhận phản hồi từ server qua input stream
                System.out.println("Server phản hồi: " + inStream.readLine());
                System.out.println("Server phản hồi: " + inStream.readLine());
                if (input.equals("bye")){
                    System.out.println("Client đóng kết nối!");
                    socket.close();
                }
            }





        }catch (IOException e){
            System.out.println("Lỗi kết nối đến server");
        }

    }
}
