import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class bai3_3123410395_MaiThanhTrung_Client {
    public static void main(String[] args){
        String host="localhost";
        int port=2000;
        try(Socket socket=new Socket(host,port);
            Scanner scanner=new Scanner(System.in);
            PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Đã kết nối tới Server. Nhập tin nhắn (nhập bye để thoát): ");
            String userInput;
            do {
                System.out.println("Client: ");
                userInput = scanner.nextLine();
                writer.println(userInput);
                String serverResponse = reader.readLine();
                System.out.println(serverResponse);
            } while (!"bye".equalsIgnoreCase(userInput.trim()));
        }catch(UnknownHostException ex){
            System.out.println("Không tìm thấy Server "+ex.getMessage());

        }catch (IOException e){
            System.out.println("Lỗi I/O"+e.getMessage());
        }
    }
}
