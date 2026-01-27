import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
public class bai1 {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap vao chuoi: ");
        input = scanner.nextLine();
        double result = bai1(input);
        System.out.println(result);
    }
    public static double bai1(String input){
        try{
            StringTokenizer token = new StringTokenizer(input, "+-*/", true);
        int so1 = Integer.parseInt(token.nextToken());
        String toantu = token.nextToken();
        int so2 = Integer.parseInt(token.nextToken());
        return switch (toantu){
            case "+" -> so1 + so2;
            case "-" -> so1 - so2;
            case "*" -> so1 * so2;
            case "/" -> so1 / so2;
            default -> 0;
        };
        } catch (Exception e){
            System.out.println("Loi dinh dang.");
            return 0;
        }
    }
}