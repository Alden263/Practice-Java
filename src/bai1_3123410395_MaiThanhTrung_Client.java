import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class bai1_3123410395_MaiThanhTrung_Client {



        private String host;
        private int port;
        public bai1_3123410395_MaiThanhTrung_Client( String host, int port){
            this.host=host;
            this.port=port;
        }
        public void start(){
            try(Socket socket=new Socket(host,port)){
                System.out.println("Đã kết nối đến Server "+socket.getRemoteSocketAddress());
                startCommunication(socket);

            }catch (IOException e){
                System.out.println("Lỗi không kết nối được đến Server");
            }
        }
        private void startCommunication(Socket socket){
            try(Scanner scanner=new Scanner(System.in);
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true)){
                String userInput;
                while (true){
                    System.out.print("Nhập dữ liệu: ");
                    userInput= scanner.nextLine();
                    writer.println(userInput);
                    String response;
                    while ((response=reader.readLine())!=null){
                        if(response.equals("<END>")){
                            break;

                        }
                        System.out.println(response);
                        if(response.equalsIgnoreCase("bye")){
                            System.out.println("Client gửi yêu cầu đóng kết nối");
                            break;
                        }
                    }
                }



            }catch(IOException e){
                System.out.println("Lỗi không giao tiếp được với server");

            }

        }
        public static void main(String [] args){
            bai1_3123410395_MaiThanhTrung_Client client=new bai1_3123410395_MaiThanhTrung_Client("localhost",2000);
            client.start();
        }
    }


