package Tuan4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2000);
            System.out.println("Server đang lắng nghe ở port 2000");
            Socket socket = server.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " đã kết nối.");
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String data;
            while ((data = inStream.readLine()) != null) {
                if ((data.trim().equals("bye"))) {
                    outStream.write("Server đã đóng kết nối");
                    outStream.flush();

                    break;
                }
                System.out.println("Server đã nhận: " + data);

                StringBuilder reverse = new StringBuilder(data);
                outStream.write("Số đảo ngược "+reverse.reverse().toString()+"\n");
                try {
                    int n = Integer.parseInt(data.trim());
                    if (IsPerfect(n)) {
                        outStream.write("Số " + n + " là số hoàn hảo\n");
                    } else {
                        outStream.write("Số hoàn hảo gần nhất là " + findNextPerfect(n) + "\n");
                    }

                    outStream.flush();
                } catch (NumberFormatException e) {
                    // Nếu gửi chữ (không phải số), Server sẽ bỏ qua phần kiểm tra này
                }
            }


                socket.close();
                server.close();

            }catch(IOException e){
                System.out.println("Lỗi kết nối client");
            }
        }

    public static boolean IsPerfect(int n){
        if(n<6){
            return false;
        }
        int sum=1;
        for(int i=2; i*i<=n; i++ ){
            if(n%i==0){
                sum+=i;
                if(i*i!=n){
                    sum+=n/i;
                }
            }
        }
        return sum==n;
    }
    public static int findNextPerfect(int n){
        int s=n+1;
        while(true){
            if(IsPerfect(s)){
                return s;
            }
            s++;
        }
    }
}
