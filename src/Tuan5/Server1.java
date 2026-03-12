package Tuan5;

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

            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
