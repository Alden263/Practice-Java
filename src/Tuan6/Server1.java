package Tuan6;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server1 {

        private int port;
        public Server1(int port){
            this.port=port;
        }
        public void start(){
            try(ServerSocket server=new ServerSocket(port)){
                System.out.println("Server đang lăng nghe tại port "+port);
                Socket socket=server.accept();
                handleClient(socket);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        private void handleClient(Socket socket){
            System.out.println("Server đã chấp nhận kết nối "+socket.getRemoteSocketAddress());
            try(BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true); Scanner scanner=new Scanner(System.in)){
                String dataFromClient;
                while((dataFromClient= reader.readLine())!=null){
                    System.out.println("Server nhận "+dataFromClient );
                    if(dataFromClient.equalsIgnoreCase("bye")){
                        System.out.println("Server nhận yêu cầu đóng kết nối từ Client");
                        break;
                    }
                    String response=processData(dataFromClient);
                    writer.println(response);
                    writer.println("<END>");
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        private String processData(String input){
            String apiMath="https://api.mathjs.org/v4/?expr=";
            try{


                String doc=Jsoup.connect(apiMath+URLEncoder.encode(input,"UTF-8")).method(Connection.Method.GET).ignoreContentType(true).execute().body();


                return doc;

            }catch (Exception e){

                return "Lỗi tra cứu thống tin, vui lòng thử lại";
            }


        }
        public static void main(String []args){
            Server1 server=new Server1(2000);
            server.start();
        }
    }



