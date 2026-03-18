package Tuan5;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {



        private int port;
        public Server2(int port){
            this.port=port;
        }
        public void start(){
            try(ServerSocket server=new ServerSocket(port)){
                System.out.println("Server đang lắng nghe tại port "+port);
                Socket socket=server.accept();
                handleClient(socket);


            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
        private void handleClient(Socket socket) {
            System.out.println("Đã chấp nhận kết nối từ client: " + socket.getRemoteSocketAddress());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
                String dataFromClient;
                while ((dataFromClient = reader.readLine()) != null) {
                    System.out.println("Server nhận: " + dataFromClient);
                    if (dataFromClient.equalsIgnoreCase("bye")) {
                        System.out.println("Server nhận yêu cầu đóng kết nối từ client.");
                        break;
                    }

                    String response = processData(dataFromClient);
                    writer.println(response);
                    writer.println("<END>"); // Báo client biết đã kết thúc gửi dữ liệu.
                }
            } catch (IOException e) {
                System.err.println("Lỗi kết nối từ client: " + e.getMessage());
            }
        }
        private String processData(String input) {


            double pi =0;
             try{
                 double count=0;
                 double data=Double.parseDouble(input);
                 double countIN=0;
                 if(data <100000){
                     return "Số không úng định dạng";
                 }
                 for(int i=0; i<=data;i++){
                     Double x=Math.random();
                     Double y=Math.random();
                     count++;
                     if(x*x+y*y<=1.0){
                         countIN++;

                     }
                      pi = (4*countIN)/count;


                 }


             } catch (Exception e){
                 System.err.println("Lỗi "+e.getMessage());
             }




            return "Server phản hồi: "+pi ;
        }

        public static void main(String[] args) {
            Server2 server = new Server2(2000);
            server.start();
        }
    }


