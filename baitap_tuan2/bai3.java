import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class bai3 {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap vao chuoi: ");
        input = scanner.nextLine();
        String result = bai3(input);
        System.out.println(result);
    }
    public static String bai3(String input){
        try{
            BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            String line;
            while((line = br.readLine()) != null){
                String content = line;
                StringTokenizer token = new StringTokenizer(content, ":");
                String a = token.nextToken();
                String b = token.nextToken();
                map.putIfAbsent(a, b);
            }
            for(String word : map.keySet()){
                if(input.trim().toLowerCase().equals(word.trim().toLowerCase())){
                    return map.get(word);
                }
                else if(input.trim().toLowerCase().equals(map.get(word).trim().toLowerCase())){
                    return word;
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "Khong tim thay";
    }
}
