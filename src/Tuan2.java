import java.io.File;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.*;

public class Tuan2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        String input2="Dai hoc Sai Gon   la mot trong nhung truong dai hoc lau doi nhat sai  gon";
//
//        Bai1(input);
//        Bai2(input2);
        String input3=scanner.nextLine();
        Bai3("src/dictionary.txt",input3);



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
        for(String key: map.keySet()){
            System.out.print(map.get(key)+" ");
        }


    }
    public static void Bai3(String filePath,String word){
        File Dictionary= new File(filePath);
        HashMap<String,String> Dic=new HashMap<>();
        try(Scanner reader= new Scanner(Dictionary)){
            while (reader.hasNextLine()){
                String data=reader.nextLine();
                StringTokenizer st3=new StringTokenizer(data,";");

                while (st3.hasMoreTokens()){
                    String English=st3.nextToken().trim().toLowerCase();
                    String Vietnam=st3.nextToken().trim().toLowerCase();
                    Dic.put(English,Vietnam);
                    Dic.put(Vietnam,English);

                }

            }

        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        boolean found=false;
        for(Map.Entry<String,String> entry: Dic.entrySet()){
            if(entry.getValue().equals(word)){
                found=true;
                System.out.println("Từ tiếng anh tương ứng là "+entry.getKey());
                break;

            }
        }
        if(!found&&Dic.containsKey(word)){
            System.out.println("Từ tiếng việt là "+Dic.get(word));
            found=true;
        }




        if (!found) System.out.println("Không tìm thấy từ này.");



    }
}





