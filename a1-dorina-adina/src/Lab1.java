import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab1 {
    private static List<Integer> transformToInt(String[] params)
    {
        List<Integer> numberList = new ArrayList<>();

        for(int i = 0; i < params.length; i++) {
            try {
                numberList.add(Integer.parseInt(params[i]));
            } catch (NumberFormatException nfe) {
            }
        }
        return numberList;
    }

    public static void main(String[] args) {
        List<Integer> numere = new ArrayList<>();
        if(args.length == 0) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter string : ");
            String str = sc.nextLine();
            System.out.println("You entered string " + str);
            String[] arr = str.split(" ", str.length()-1);
            numere = transformToInt(arr);

        }
        else{
            numere = transformToInt(args);
        }
        int min = Integer.MAX_VALUE;
        for (int x : numere) {
            if (x < min) {
                min = x;
            }
        }
        System.out.println(min);
    }
}
