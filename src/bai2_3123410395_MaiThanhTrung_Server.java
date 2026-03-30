import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bai2_3123410395_MaiThanhTrung_Server {
    private int port;
    private int bufferSize;
    private DatagramPacket receivedPacket;
    public  bai2_3123410395_MaiThanhTrung_Server( int port, int bufferSize){
        this.port=port;
        this.bufferSize=bufferSize;

    }
    public void start(){
        try(DatagramSocket socket=new DatagramSocket(port)){
            while(true){
                String receiveData=receivedData(socket);
                System.out.println("Server nhận: "+receiveData);
                if(receiveData.equalsIgnoreCase("exit")){
                    System.out.println("Server đóng kết nối");
                    break;
                }
                sendData(socket,receiveData);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    private String receivedData (DatagramSocket socket) throws IOException {
        receivedPacket=new DatagramPacket(new byte[bufferSize],bufferSize);
        socket.receive(receivedPacket);
        byte [] receivedByte= Arrays.copyOf(receivedPacket.getData(),receivedPacket.getLength());
        return new String(receivedByte, StandardCharsets.UTF_8);
    }
    private void sendData(DatagramSocket socket, String receivedData) throws IOException{
        String response= processData(receivedData).toUpperCase();
        byte [] responseByte=response.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet=new DatagramPacket(responseByte,responseByte.length,receivedPacket.getAddress(),receivedPacket.getPort());
        socket.send(packet);

    }
    private String processData(String input){
        StringTokenizer st=new StringTokenizer(input," ");
        String function=st.nextToken();
        StringBuilder finalres=new StringBuilder();
        switch (function){
            case "weather":
                StringBuilder cityName=new StringBuilder();

                while(st.hasMoreTokens()){
                    cityName.append(st.nextToken());
                    if (st.hasMoreTokens()){
                        cityName.append(" ");
                    }

                }
                String completeCityName=cityName.toString();
                String apiWeather;



            case "convert":

                String base=st.nextToken();
                String des=st.nextToken();
                String num=st.nextToken();
                String apiConvert="https://networkcalc.com/api/binary/"+num+"?from="+base+"&to="+des;
                try{
                    Document doc= Jsoup.connect(apiConvert).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                    JSONObject json=new JSONObject(doc.text());
                    String res=json.get("converted").toString();
                    finalres.append("--> Kết quả đổi ").append(num).append(" từ cơ số ").append(base).append(" sang cơ số ").append(des).append(" có kết quả là ").append(res);

                }catch (IOException e){
                    System.out.println(e.getMessage());
                }










        }
        return finalres.toString();
    }
    public static void main (String [] args){
        bai2_3123410395_MaiThanhTrung_Server server=new bai2_3123410395_MaiThanhTrung_Server(1234,1024);
        server.start();
    }
}
