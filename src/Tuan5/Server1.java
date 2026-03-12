package Tuan5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private int port;
    public Server1(int port){
        this.port=port;
    }
    public void start(){
        try(ServerSocket server=new ServerSocket(port)){
            System.out.println("Server đang đợi kết nối tại port "+port);
            Socket socket1=server.accept();
            handleClient(socket1);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    private void handleClient(Socket socket){
        System.out.println("Đã chấp nhận kết nối của client " +socket.getRemoteSocketAddress());
        try (BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);){
            String dataFromClient;
            while((dataFromClient=reader.readLine())!=null){
                System.out.println("Server đã nhận "+dataFromClient);
                if(dataFromClient.equalsIgnoreCase("bye")){
                    System.out.println("Client yêu cầu đóng kết nối");;
                    break;
                }
                String response=processdata(dataFromClient);
                writer.println(response);
                writer.println("<END>");

            }

        }catch (IOException e){
            System.err.println("Lỗi kết nối từ Client "+e.getMessage());
        }
    }
    private String processdata(String input){
        try{
            String url= "https://masothue.com/Search/?q="+input.trim()+"&type=auto&force-search=1";
            String newurl= Jsoup.connect(url).method(Connection.Method.GET).followRedirects(true).execute().url().toString();
            if(newurl.equals(url)){
                System.out.println("Không tìm thấy thông tin cá nhân ");
                return "Không tìm thấy thông tin cá nhân";
            }
            Document doc= Jsoup.connect(newurl).method(Connection.Method.GET).followRedirects(true).execute().parse();
            Elements info= doc.getElementsByAttributeValue("class","copy");
            String fullName=info.get(0).text();
            String address=info.get(2).text();
            return fullName +"---"+address;

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        return "lỗi";

    }
    public static void main(String []args){
        Server1 server=new Server1(2000);
        server.start();
    }
}
