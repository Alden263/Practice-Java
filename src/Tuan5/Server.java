package Tuan5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Server {
    private int port;
    public Server(int port){
        this.port=port;
    }
    public void start(){
        try(ServerSocket server=new ServerSocket(port)){
            System.out.println("Server đang lắng nghe tại port"+port);
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
        String url = "http://ip-api.com/json/"+input+"?fields=status,message,country,regionName,continent,city,query,status";
        try{
            Document doc = Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
            JSONObject json = new JSONObject(doc.text());// chuyển JSON dạng text -> Object nhờ thư viện org.json
            if(json.get("status").equals("fail")){
                return "Địa chỉ ip không hợp ";
            }
            String continent=json.get("continent").toString();


        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        StringBuilder response = new StringBuilder(input);
        return "Server phản hồi: " + response.reverse().toString();
    }

    public static void main(String[] args) {
        Server server = new Server(2000);
        server.start();
    }
}
