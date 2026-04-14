import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class bai3_3123410395_MaiThanhTrung_ClientHandler implements Runnable {
    private Socket client;
    public bai3_3123410395_MaiThanhTrung_ClientHandler(Socket socket){
        this.client=socket;
    }
    public void run(){
        String threadName=Thread.currentThread().getName();
        System.out.println("["+threadName+"] Bắt đầu xử lý client "+client.getInetAddress().getHostAddress());
        try(Socket autoCloseSocket=this.client;
            InputStream input =client.getInputStream();
            BufferedReader reader= new BufferedReader(new InputStreamReader(input));
            OutputStream output=client.getOutputStream();
            PrintWriter writer=new PrintWriter(output,true)){
            String clientMessage;

            while((clientMessage=reader.readLine())!=null){
                System.out.println("["+threadName+"] nhận từ client "+client.getInetAddress().getHostAddress()+" : "+clientMessage);




                if("bye".equalsIgnoreCase(clientMessage.trim())){
                    System.out.println("[ "+threadName+"] Client "+client.getInetAddress().getHostAddress()+" yêu cầu ngắt kết nối");
                    break;
                }
                String result=processData(clientMessage);
                writer.println(result);




            }

        }catch(IOException e){
            System.out.println("[ "+threadName+"] Lỗi I/O với client "+(client !=null ? client.getInetAddress().getHostAddress():"unknown" )+":"+e.getMessage()) ;
            System.out.println("[ "+threadName+"] Kết thúc xử lý client "+(client!=null ? client.getInetAddress().getHostAddress():"unknown "));

        }
    }
    public String processData(String input){
        StringTokenizer token=new StringTokenizer(input," ");
        String function=token.nextToken();
        StringBuilder finalres=new StringBuilder();
        switch(function){
            case "weather":
                StringBuilder cityName=new StringBuilder();
                while (token.hasMoreTokens()){
                    cityName.append(token.nextToken());
                    if(token.hasMoreTokens()){
                        cityName.append(" ");
                    }
                }
                String completeCityName=cityName.toString();
                String encodeCity= URLEncoder.encode(completeCityName, StandardCharsets.UTF_8);
                String apiWeather= "https://api.tomorrow.io/v4/weather/realtime?location="+encodeCity+"&units=metric&apikey=h0sc3cGlCQzfjGkMv24zQt18VB0bP6Hv";
                try{
                    Document doc= Jsoup.connect(apiWeather).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                    JSONObject jsonWeather=new JSONObject(doc.text());
                    JSONObject location= jsonWeather.getJSONObject("location");
                    String cityDetail=location.getString("name");
                    JSONObject data=jsonWeather.getJSONObject("data");
                    JSONObject value=data.getJSONObject("values");
                    Double temperature= value.getDouble("temperature");
                    finalres.append("--> Chức năng tra cứu nhiệt độ ").append(cityDetail).append(" có nhiệt độ là ").append(temperature).append(" độ C");

                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
                return finalres.toString();
            case "calc":
                String math=token.nextToken();
                String encodeMath=URLEncoder.encode(math,StandardCharsets.UTF_8);
                String apiCalculate= "https://api.mathjs.org/v4/?expr="+encodeMath;
                try{
                    String doc=Jsoup.connect(apiCalculate).method(Connection.Method.GET).ignoreContentType(true).execute().body();
                    finalres.append("--> Kết quả phép tính là: ").append(doc);

                }catch (IOException e){
                    System.out.println("Lỗi không kết nối api"+e.getMessage());
                }
                return finalres.toString();



            default:
                finalres.append("Lỗi dữ liệu đầu vào ");

        }

        return finalres.toString();
    }
}
