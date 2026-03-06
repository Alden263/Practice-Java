package Tuan4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server1 {
    public static void main (String []args){
        try(ServerSocket server1=new ServerSocket(2000)){
            HashMap<Integer,Integer> CountSNT=new HashMap<>();
            System.out.println("Server đang đợi kết nối tại port 2000");
            Socket socket1=server1.accept();
            System.out.println("Client " + socket1.getRemoteSocketAddress() + " đã kết nối.");
            BufferedReader inStream1=new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            BufferedWriter outStream1=new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
            String data;
            while((data=inStream1.readLine())!=null){
                System.out.println("Server nhận được "+data);
                int n=Integer.parseInt(data.trim());
                for(int i=2; i<=n/i;i++){
                    while(n%i==0){
                        CountSNT.put(i, CountSNT.getOrDefault(i,0)+1);
                        n/=i;
                    }
                }
                if(n>1){
                    CountSNT.put(n, CountSNT.getOrDefault(n,0)+1);
                }
                for(Integer key:CountSNT.keySet()){
                    String res = "";
                    res += "Kết quả " + key + "*" + CountSNT.get(key) + "\n";
                    outStream1.write(res);

                }
                outStream1.write("Done\n");
                outStream1.flush();




            }
            socket1.close();




        }catch (IOException e){
            System.out.println(e.getMessage());

        }

    }

}
