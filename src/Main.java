import java.util.Scanner;
import java.util.HashMap;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Nhập số nguyên dương n ");
        long n= scanner.nextLong();
        if(n<0){
            System.out.println("Vui lòng nhập so nguyen duong");
            return;
        }
        Bai1(n);
        Bai2(n);
        Bai3(n);
        Bai4(n);
    }


    public static void Bai1(long n){
        long Tong=0;

        for(int i=1; i<=n; i++){
            Tong+=i;
        }
        System.out.println("Tổng các số nguyên dương tới n là " + Tong);

    }
    public static void Bai2(long n){

        long TongUoc=0;
        if(n<3){
            System.out.println("Số chưa thỏa điều kiện");
            return;
        }
        for(int j=1; j<=n/2;j++){
            if(n%j==0){
                TongUoc+=j;
            }
        }
        System.out.println("Tổng các ước của n là " +TongUoc);
    }
    public static void Bai3(long n){


        long TongSNT=0;
        if(n<3){
            System.out.println("Số nhập không hợp lệ");
            return;
        }
       for(int i=2; i<=n; i++){
           boolean laSNT=true;
           for(int j=2;j<i;j++){
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

    public static void Bai4(long n){


        HashMap<Long, Long> CountSNT = new HashMap<Long,Long>();
        for(long i=2;i<=n;i++){
            if(n%i==0){
                long count=0;
                while (n%i==0){
                    count++;
                    n /=i;
                }
                CountSNT.put(i,count);
            }

        }
        for (Long key : CountSNT.keySet()) {
            long value = CountSNT.get(key);
                System.out.println("Kết quả " + key + " * " + value );
        }

    }
}
