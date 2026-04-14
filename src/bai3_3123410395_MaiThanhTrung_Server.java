import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class bai3_3123410395_MaiThanhTrung_Server {
    private  static final int port=2000;
    private static  final int N_Thread=10;
    public static void main (String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(N_Thread);
        System.out.println("Threadpool đang hoạt động với "+N_Thread+" thread");
        try(ServerSocket server=new ServerSocket(port)){
            System.out.println("Server ang lăng nghe trên cổng "+port);
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                System.out.println("Đang shutdowm ExecutorsService....");
                shutdownExecutor(executorService);
                System.out.println("Đã shutdown Executor");

            }));
            while(!executorService.isShutdown()){
                try{
                    System.out.println("Đang chờ client kết nối....");
                    Socket client= server.accept();
                    System.out.println("Client mới đã kết nối "+client.getInetAddress().getHostAddress());
                    bai3_3123410395_MaiThanhTrung_ClientHandler clientHandler=new bai3_3123410395_MaiThanhTrung_ClientHandler(client);
                    executorService.execute(clientHandler);

                }catch (IOException e){
                    if(executorService.isShutdown()){
                        System.out.println("Server socket đã đóng do executor đã shutdown");
                        break;
                    }
                    System.out.println("Lỗi kết nối client "+e.getMessage());
                }
            }
        }catch (IOException ex){
            System.out.println("Lỗi khi khởi động Server"+ex.getMessage());
            shutdownExecutor(executorService);

        }finally {
            if(executorService.isTerminated()){
                System.out.println("Thực hiện shutdown cuỗi cùng cho executor");
                shutdownExecutor(executorService);
            }
            System.out.println("Server đã dừng");
        }
    }
    public static  void shutdownExecutor(ExecutorService executorService){
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
                executorService.shutdownNow();
                if(!executorService.awaitTermination(60,TimeUnit.SECONDS)){
                    System.out.println("Executor đã dừng hẳn");
                }

            }
        }catch (InterruptedException ie){
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
