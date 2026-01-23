import java.util.Scanner;
import java.util.StringTokenizer;

public class Tuan2 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        double result=0;
        StringTokenizer st=new StringTokenizer(input,"+-*/",true);
        if(st.hasMoreTokens()){
            result=Double.parseDouble(st.nextToken().trim());
        }
        while (st.hasMoreTokens()){
            String operation=st.nextToken().trim();
            if(st.hasMoreTokens()){
                double nextnum=Double.parseDouble(st.nextToken().trim());
                switch (operation){
                    case "+": result+=nextnum;
                        break;
                    case "-": result-=nextnum;
                        break;
                    case "*": result*=nextnum;
                        break;
                    case "/": result/=nextnum;
                        break;
            }





                System.out.println(result);
        }






    }


}}



