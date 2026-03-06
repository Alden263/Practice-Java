package Tuan4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Tạo một socket để kết nối đến server
            Socket client = new Socket("localhost", 12345);
            System.out.println("Đã kết nối đến server.");

            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new java.io.OutputStreamWriter(client.getOutputStream()));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("vui long nhap du lieu: ");
                String input = scanner.nextLine();
                out.write(input+"\r\n");
                out.flush();
                //kiem tra
                if (input.equals("bye")) {
                    System.out.println("Đã ngắt kết nối.");
                    break;
                }
                // Nhận phản hồi từ server
                String dataFromServer = in.readLine();
                System.out.println("Phản hồi từ server: " + dataFromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
