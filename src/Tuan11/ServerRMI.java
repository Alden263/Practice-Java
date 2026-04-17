package Tuan11;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main( String[] args){
        try{
            CaculatorService service=new CaculatorServiceImpl();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("CaculatorService",service);
            System.out.println("RMI Server đang chạy....");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
