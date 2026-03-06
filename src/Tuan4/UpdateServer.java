package Tuan4;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class UpdateServer {
    private int port;
    public UpdateServer(int port){
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
            Random random=new Random();
            int Randomnumber=random.nextInt(100);
            while ((dataFromClient = reader.readLine()) != null) {
                System.out.println("Server nhận: " + dataFromClient);
                int num=Integer.parseInt(dataFromClient);
                if (dataFromClient.equalsIgnoreCase("bye")) {
                    System.out.println("Server nhận yêu cầu đóng kết nối từ client.");
                    break;
                }


                String response = processData(dataFromClient,Randomnumber);
                writer.println(response);
                writer.println("<END>"); // Báo client biết đã kết thúc gửi dữ liệu.
            }
        } catch (IOException e) {
            System.err.println("Lỗi kết nối từ client: " + e.getMessage());
        }
    }
    private String processData(String input,int Randomnum) {
        int num = Integer.parseInt(input.trim());
        while(num!=Randomnum){
            if(num<Randomnum){
                return("kết quả lớn hơn "+num+"\n");
            }else{
                return("Kết quả nhỏ hơn "+num+"\n");
            }

        }
        return("Bạn đoán chính xác");
//        StringBuilder response = new StringBuilder(input);
//        return "Server phản hồi: " + response.reverse().toString();
    }

    public static void main(String[] args) {
        UpdateServer server = new UpdateServer(2000);
        server.start();
    }
}
