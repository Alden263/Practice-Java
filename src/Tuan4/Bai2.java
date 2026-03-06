package Tuan4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Bai2 {
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
                    if (isPerfect(Long.parseLong(dataFromClient))) {
                        out.write(dataFromClient + " la so hoan hao\r\n");
                        out.flush();
                    } else {
                        long nextPerfect = findNextPerfect(Long.parseLong(dataFromClient));
                        out.write(dataFromClient + " khong phai la so hoan hao, so hoan hao tiep theo la: " + nextPerfect + "\r\n");
                        out.flush();
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    public static boolean isPerfect(long n) {
        if (n < 6) return false;
        long sum = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) {
                    sum += n / i;
                }
            }
        }
        return sum == n;
    }
    public static long findNextPerfect(long n) {
        long next = n + 1;
        while (!isPerfect(next)) {
            next++;
        }
        return next;
    }
}
