import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bai2 {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap vao chuoi: ");
        input = scanner.nextLine();
        String result = bai2(input);
        System.out.println(result);
    }
    public static String bai2(String input){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        StringTokenizer token = new StringTokenizer(input, " ");
        while(token.hasMoreTokens()){
            String key = token.nextToken();
            map.putIfAbsent(key.toLowerCase(), key);
        }
        String result = "";
        for(String key : map.keySet()){
            result += map.get(key) + " ";
        }
        return result.trim();
    }
}
