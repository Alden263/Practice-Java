package Tuan7;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ServerUDP3 {







        private int port;
        private int bufferSize;
        private DatagramPacket receivePacket;
        public ServerUDP3(int port, int bufferSize){
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
            StringBuilder respones=new StringBuilder();

            StringTokenizer st=new StringTokenizer(input, " ");
            while(st.hasMoreTokens()){
                String location= URLEncoder.encode(st.nextToken(),StandardCharsets.UTF_8);
                String time=st.nextToken();
                String api= "https://api.tomorrow.io/v4/weather/forecast?location="+location+"&timesteps="+time+"&apikey=aLBPESiQGnesp1WKJF8VbQb9rb6oTheX";
                try{
                    Document doc =Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                    JSONObject json=new JSONObject(doc.text());
                    JSONObject timeline=json.getJSONObject("timelines");
                    Double temp= timeline.getJSONArray("daily").getJSONObject(0).getJSONObject("values").getDouble("temperatureAvg");
                    respones.append("Địa điểm ").append(location).append(" có nhiệt độ là ").append(temp);


                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

            }
            return respones.toString();


        }








        public static void main(String [] args){
            ServerUDP3 server=new ServerUDP3(1234,1024);
            server.start();
        }

    }







