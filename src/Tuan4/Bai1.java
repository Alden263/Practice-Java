package Tuan4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Bai1 {
        public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Server đã sẵn sàng, đang chờ kết nối...");
            while (true) {
                Socket client = server.accept();
                System.out.println("Đã kết nối với client: " + client.getRemoteSocketAddress());

                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new java.io.OutputStreamWriter(client.getOutputStream()));

                String dataFromClient ;
                while ((dataFromClient = in.readLine()) != null) {
                    if (dataFromClient.equals("bye")) {
                        System.out.println("Client đã ngắt kết nối: " + client.getRemoteSocketAddress());
                        break;
                    }
                    System.out.println("Nhận dữ liệu từ client: " + dataFromClient);
                    // Gửi phản hồi lại cho client
                    String reversed = new StringBuilder(dataFromClient).reverse().toString();
                    out.write(reversed + "\r\n");
                    out.flush();
                }
            }
            
        } catch (IOException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}
