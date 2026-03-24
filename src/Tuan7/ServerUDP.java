package Tuan7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ServerUDP {
    private int port;
    private int bufferSize;
    private DatagramPacket receivePacket;
    public ServerUDP(int port, int bufferSize){
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
    private String processData(String input){
        StringTokenizer st=new StringTokenizer(input," ");
        StringBuilder res=new StringBuilder();
        while(st.hasMoreTokens()){
            StringBuilder tmp=new StringBuilder(st.nextToken());
            res.append(tmp.reverse()).append(" ");
        }
        return res.toString();
    }
    public static void main(String [] args){
        ServerUDP server=new ServerUDP(1234,1024);
        server.start();
    }

}
