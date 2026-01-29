import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bai4{
    public static void main(String[] args){
        long n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so tien can rut: ");
        n = sc.nextInt();
        ruttien(n);
    }
    public static void ruttien(long n){
        try{
            BufferedReader br = new BufferedReader(new FileReader("ATM.txt"));
            LinkedHashMap<Long, Integer> mapatm = new LinkedHashMap<>();
            LinkedHashMap<Long, Integer> result = new LinkedHashMap<>();
            String line;
            while((line = br.readLine()) != null){
                StringTokenizer token = new StringTokenizer(line, ";");
                long key = Integer.parseInt(token.nextToken());
                int value = Integer.parseInt(token.nextToken());
                mapatm.putIfAbsent(key, value);
            }
            for(long key : mapatm.keySet()){
                while(n >= key && mapatm.get(key) > 0){
                    n -= key;
                    mapatm.put(key, mapatm.get(key) - 1);
                    result.put(key, result.getOrDefault(key, 0) + 1);
                }
            }
            if (n == 0){
                System.out.println("Cac menh gia duoc rut: ");
                for(long key : result.keySet()){
                    System.out.println(key + ";" + result.get(key));
                }
            } else{
                System.out.println("Khong the rut so tien nay");
            }
        } catch (Exception e){
            System.out.println("Loi doc file.");
        }
    }
}