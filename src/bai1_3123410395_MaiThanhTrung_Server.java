import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class bai1_3123410395_MaiThanhTrung_Server {



        private int port;
        public bai1_3123410395_MaiThanhTrung_Server(int port){
            this.port=port;
        }
        public void start(){
            try(ServerSocket server=new ServerSocket(port)){
                System.out.println("Server đang lăng nghe tại port "+port);
                Socket socket=server.accept();
                handleClient(socket);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        private void handleClient(Socket socket){
            System.out.println("Server đã chấp nhận kết nối "+socket.getRemoteSocketAddress());
            try(BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true); Scanner scanner=new Scanner(System.in)){
                String dataFromClient;
                while((dataFromClient= reader.readLine())!=null){
                    System.out.println("Server nhận "+dataFromClient );
                    if(dataFromClient.equalsIgnoreCase("bye")){
                        System.out.println("Server nhận yêu cầu đóng kết nối từ Client");
                        break;
                    }
                    String response=processData(dataFromClient);
                    writer.println(response);
                    writer.println("<END>");
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        private String processData(String input){
            int startpoint=input.lastIndexOf("-p")+2;
            int endpoint=input.lastIndexOf(".html");
            String ProductID=input.substring(startpoint,endpoint);
            String apiGetProduct="https://tiki.vn/api/v2/products/";
            String apiReviewProduct="https://tiki.vn/api/v2/reviews?product_id="+ProductID+"&limit10";
            StringBuilder result=new StringBuilder();
            try{
                Document doc= Jsoup.connect(apiGetProduct +ProductID).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                JSONObject jsonProduct=new JSONObject(doc.text());
                String productName= jsonProduct.getString("name");
                long productPrice=jsonProduct.getLong("price");
                Document doc2=Jsoup.connect(apiReviewProduct).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                result.append("Tên sản phầm là ").append(productName).append(" có giá là ").append(productPrice).append("\n");
                JSONObject reviewProduct=new JSONObject(doc2.text());
                Double reviewAverage= reviewProduct.getDouble("rating_average");
                Double totalReview=reviewProduct.getDouble("reviews_count");
                result.append("Sản phầm có điểm là ").append(reviewAverage).append(" với tổng số lượt review là ").append(totalReview).append("\n");
                result.append("Sản phẩm có 10 bình luận đầu tiên bao gồm ").append("\n");
                JSONArray reviewProductArray=reviewProduct.getJSONArray("data");
                for(int i=0;i<reviewProductArray.length();i++){
                    JSONObject item=reviewProductArray.getJSONObject(i);
                    String reviewContent= item.getString("content");

                    if(!reviewContent.isEmpty()){
                        result.append("+").append(reviewContent).append("\n");
                    }

                }

                return result.toString();

            }catch (Exception e){

                return "Lỗi tra cứu thống tin, vui lòng thử lại";
            }


        }
        public static void main(String []args){
            bai1_3123410395_MaiThanhTrung_Server server=new bai1_3123410395_MaiThanhTrung_Server(2000);
            server.start();
        }
    }



