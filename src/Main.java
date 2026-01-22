import java.util.Scanner;


import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Bai1(scanner);
        Bai2(scanner1);
    }


    public static void Bai1(Scanner scanner){
        long Tong=0;
        System.out.print("Nhập số nguyên dương n ");
        int n= scanner.nextInt();
        for(int i=1; i<=n; i++){
            Tong+=i;
        }
        System.out.println("Tổng các số nguyên dương tới n là " + Tong);

    }
    public static void Bai2(Scanner scanner1){
        System.out.print("Nhập số nguyên dương x ");
        int x=scanner1.nextInt();
        long TongUoc=0;
        if(x<3){
            System.out.println("Số chưa thỏa điều kiện");
            return;
        }
        for(int j=1; j<=x/2;j++){
            if(x%j==0){
                TongUoc+=j;
            }
        }
        System.out.println("Tổng các ước của n là " +TongUoc);
    }

}
