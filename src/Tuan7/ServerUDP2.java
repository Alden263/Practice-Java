package Tuan7;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ServerUDP2 {



        private int port;
        private int bufferSize;
        private DatagramPacket receivePacket;
        public ServerUDP2(int port, int bufferSize){
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
            StringBuilder finalres=new StringBuilder();
            String [] part=input.split(":");

            if(part.length<3){
                return "Sai format số";

            }
            String source=part[0].trim();
            String des=part[1].trim();
            String num=part[2].trim();
            String [] newdess=des.split(",");
            for(String newdes: newdess){
                 String y=newdes.trim();

                 String api= "https://networkcalc.com/api/binary/"+num+"?from="+source+"&to="+y;
                 try{
                     String result="";
                     Document doc= Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                     JSONObject json=new JSONObject(doc.text());
                     result=json.getString("converted");
                     if(finalres.length()>0){
                         finalres.append(" | ");
                     }
                     finalres.append(result);

                 }catch (IOException e){
                     System.out.println(e.getMessage());
                 }

            }
            return finalres.toString();




        }








        public static void main(String [] args){
            ServerUDP2 server=new ServerUDP2(1234,1024);
            server.start();
        }

}




