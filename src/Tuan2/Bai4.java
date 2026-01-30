package Tuan2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bai4 {
    public static void main(String[] args){

        LinkedHashMap<Long,Integer> ATM=new LinkedHashMap<>();
        LinkedHashMap<Long,Integer> result=new LinkedHashMap<>();

        File ATMFile=new File("src/Tuan2/ATM.txt");
        while(true){
            System.out.println("\nNhập số tiền muốn rút hoặc exit để thoát ");
            Scanner console=new Scanner(System.in);
            String command=console.nextLine();

            if(command.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Nhập số tiền muốn rút");
            Scanner scanner=new Scanner(System.in);
            long n=scanner.nextLong();
            try(Scanner reader=new Scanner(ATMFile)){
                while (reader.hasNextLine()){
                    String data=reader.nextLine();
                    StringTokenizer st=new StringTokenizer(data,";");
                    long price= Long.parseLong(st.nextToken());
                    int quantity= Integer.parseInt(st.nextToken());
                    ATM.putIfAbsent(price,quantity);
                }
                for(long key: ATM.keySet()){
                    while(n>=key&&ATM.get(key)>0){
                        n-=key;
                        ATM.put(key,ATM.get(key)-1);
                        result.put(key,result.getOrDefault(key,0)+1);

                    }
                }
                if(n==0){
                    System.out.print("Các menh gia duoc rut là ");
                    for(long key: result.keySet()){
                        System.out.print(" "+key+" * "+result.get(key));
                    }
                }else{
                    System.out.println("Không thể rút số tiền này");
                }

            }catch (FileNotFoundException e){
                System.err.print(e.getMessage());
            }

        }


    }





}
