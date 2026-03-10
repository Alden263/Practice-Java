package Tuan4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.StringTokenizer;

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
            StringTokenizer st =new StringTokenizer(input.trim());
            if(st.countTokens()!=3){
                return "Phép toán không đúng format";
            }
            try{
                int A=Integer.parseInt(st.nextToken());
                String op=st.nextToken();
                int B=Integer.parseInt(st.nextToken());
                if(!op.equals("+")&&!op.equals("-")&&!op.equals("*")&&!op.equals("/")){
                    return "Toán tử không hợp lệ";
                }
                return calculate(A, op ,B);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }


            return("Bạn đoán chính xác");
//        StringBuilder response = new StringBuilder(input);
//        return "Server phản hồi: " + response.reverse().toString();
        }
        private static String calculate( int a, String operate ,int b){
            switch (operate){
                case "+": return String.valueOf(a+b);
                case "-": return String.valueOf(a-b);
                case "*": return String.valueOf(a*b);
                case "/":
                    if(b==0){
                        return "không thể chia cho 0";
                    }
                    return String.valueOf(a/b);
                default:return "Lỗi ";
            }

        }

        public static void main(String[] args) {
            Server2 server = new Server2(2000);
            server.start();
        }
    }


