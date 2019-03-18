import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.
util.concurrent.*;
import java.util.regex.*;
public class TwoStrings {
    // Complete the twoStrings function below.
    static String compare(String lessStr, String largeStr){
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < lessStr.length(); i++) {
            map.put(lessStr.substring(i,i+1),0);
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (largeStr.contains(pair.getKey())){
                return "YES";
            }
        }
        return "NO";
    }
    static String twoStrings(String s1, String s2) {
        /*сравнение через массив
        медленно
        char[] arrS1 = s1.toCharArray(); // Преобразуем строку str в массив символов (char)
        char[] arrS2 = s2.toCharArray(); // Преобразуем строку str в массив символов (char)
        // Вывод массива на экран
        for(int i = 0; i < arrS1.length; i++) {
            for (int j = 0; j < arrS2.length; j++) {
               if (arrS1[i]==arrS2[j]){
                   return "Yes";
               }
            }
        }
        return "No";*/
        /*сравнение черех подстроку по одному эелементу
        медленно
        for(int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.substring(i,i+1).equals(s2.substring(j,j+1))){
                    return "Yes";
                }
            }
        }
        return "No";*/
        //1 выбираем наименьшую строку, чтобы искать ее во второй
        //2 выбираем наибольшую подстроку
        //3 ищем подстроку п2 строки п1 в наибольшей строке
        if (s1.length()<=s2.length()){
            return compare(s1,s2);
        } else {
            return compare(s2,s1);
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);
            System.out.println(result);
            //bufferedWriter.write(result);
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
