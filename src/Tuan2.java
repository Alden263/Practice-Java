import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tuan2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Bai1(input);



    }
    public static void Bai1(String s){
        try {
            String currentOps="+";
            double result = 0;
            StringTokenizer st = new StringTokenizer(s, "+-*/", true);

            while (st.hasMoreTokens()) {

                String token= st.nextToken().trim();
                if (IsOperator(token)){
                    currentOps=token;
                }
                else {
                    double nextnum = Double.parseDouble(token.trim());
                    switch (currentOps) {
                        case "+":
                            result+=nextnum;
                            break;
                        case "-":
                            result-=nextnum;
                            break;
                        case "*":
                            result *= nextnum;
                            break;
                        case "/":
                            result /= nextnum;
                            break;
                    }

                }
            }
            System.out.println(result);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }


    }
    public static boolean IsOperator(String s){
        return "+-*/".contains(s);
    }
    public static void Bai2(String s){
        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        StringTokenizer st2=new StringTokenizer(s," ");
        while (st2.hasMoreTokens()){
            String tmp=st2.nextToken();
            map.putIfAbsent(tmp.toLowerCase(),tmp);
        }

    }
}





