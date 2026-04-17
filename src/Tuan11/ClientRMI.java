package Tuan11;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    public static void main (String[] args){
        try{
            Registry registry= LocateRegistry.getRegistry("localhost",1099);
            CaculatorService service=(CaculatorService) registry.lookup("CaculatorService");
            System.out.println("1000+199= "+service.add(1000,199));
            System.out.println("200/5= "+service.divide(200,5));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
