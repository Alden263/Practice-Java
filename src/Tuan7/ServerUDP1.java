package Tuan7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServerUDP1 {
        private int port;
        private int bufferSize;
        private DatagramPacket receivePacket;
        public ServerUDP1(int port, int bufferSize){
            this.port=port;
            this.bufferSize=bufferSize;
        }
        public void start(){
            try(DatagramSocket socket=new DatagramSocket(port)){
                while(true){
                    String receiveData=receiveData(socket);
                    System.out.println("Server nhận : "+receiveData);
                    if(receiveData.equalsIgnoreCase("exit")){
                        System.out.println("Server đóng kết nối");
                        break;
                    }
                    sendData(socket,receiveData);
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        private String receiveData(DatagramSocket socket) throws IOException{
            receivePacket=new DatagramPacket(new byte[bufferSize],bufferSize);
            socket.receive(receivePacket);
            byte [] receiveByte= Arrays.copyOf(receivePacket.getData(),receivePacket.getLength());
            return new String(receiveByte, StandardCharsets.UTF_8);
        }
        private void sendData(DatagramSocket socket, String receiveData) throws IOException{

            String response=processData(receiveData).toUpperCase();
            byte [] responseByte=response.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet= new DatagramPacket(responseByte, responseByte.length,receivePacket.getAddress(),receivePacket.getPort());
            socket.send(packet);
        }
        private int sumDigit(int num){
            int sum=0;
            while(num >0){
                sum+=num%10;
                num/=10;
            }
            return sum;
        }

        private String processData(String input){
            int number=Integer.parseInt(input.trim());
            File input1 =new File("src/Tuan7/data.txt");
            int count=0;
            try{
                Scanner readFile=new Scanner(input1);
                while(readFile.hasNextLine()){
                    String line=readFile.nextLine().trim();
                    if(line.isEmpty()){
                        continue;
                    }
                    int tempnum=Integer.parseInt(line);
                    System.out.println(tempnum);
                    if(number==sumDigit(tempnum)){
                        count++;

                    }

                }
                return "Có "+count+ " số có tổng các chữ số bằng "+number;

            }catch (Exception e){
                System.out.println(e.getMessage());
                return "lỗi";
            }




        }
        public static void main(String [] args){
            ServerUDP1 server=new ServerUDP1(1234,1024);
            server.start();
        }

    }


