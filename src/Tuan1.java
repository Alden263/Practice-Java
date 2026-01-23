import java.util.Scanner;
import java.util.HashMap;

import static java.lang.Math.sqrt;

public class Tuan1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Nhập số nguyên dương n ");
        int n= scanner.nextInt();
        if(n<0){
            System.out.println("Vui lòng nhập so nguyen duong");
            return;
        }
        Bai1(n);
        Bai2(n);
        Bai3(n);
        Bai4(n);
    }


    public static void Bai1(int n){
        long Tong=0;

        for(int i=1; i<=n; i++){
            Tong+=i;
        }
        System.out.println("Tổng các số nguyên dương tới n là " + Tong);

    }
    public static void Bai2(int n){

        long TongUoc=0;
        if(n<3){
            System.out.println("Số chưa thỏa điều kiện");
            return;
        }
        for(int j=1; j<n;j++){
            if(n%j==0){
                TongUoc+=j;
            }
        }
        System.out.println("Tổng các ước của n là " +TongUoc);
    }
    public static void Bai3(int n){


        long TongSNT=0;
        if(n<3){
            System.out.println("Số nhập không hợp lệ");
            return;
        }
       for(int i=2; i<=sqrt(n); i++){
           boolean laSNT=true;
           for(int j=2;j<sqrt(i);j++){
               if(i%j==0){
                   laSNT=false;
                   break;
               }
           }
          if(laSNT==true){
              TongSNT+=i;
          }

       }
        System.out.println("Tổng các số nguyen to toi n là "+TongSNT);

    }

    public static void Bai4(int n){
        HashMap<Integer, Integer> CountSNT = new HashMap<>();
        for(int i=2;i<=n/i;i++){
            while (n%i==0){
               CountSNT.put(i,CountSNT.getOrDefault(i,0)+1);
                n /=i;
            }
        }
        if(n>1){
            CountSNT.put(n, CountSNT.getOrDefault(n,0)+1);
        }

        for (Integer key : CountSNT.keySet()) {
            System.out.println("Kết quả " + key + " * " + CountSNT.get(key) );
        }

    }
}
