import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakeAnagrams {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int cnt = 0;
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        Map<String,Integer> mapA = new HashMap<>();
        Map<String,Integer> mapB = new HashMap<>();
        for (char s:charsA
             ) {
            String val = String.valueOf(s);
            if(mapA.containsKey(val)){
                int value = mapA.get(val);
                // Increment the times we've seen the string
                mapA.put(val, value + 1);    
            } else {
                mapA.put(val, 1);
            }
        }
        for (char s:charsB
                ) {
            String val = String.valueOf(s);
            if(mapB.containsKey(val)){
                int value = mapB.get(val);
                // Increment the times we've seen the string
                mapB.put(val, value + 1);
            } else {
                mapB.put(val, 1);
            }
        }
        //run through HashMap to calc difference
        for (Map.Entry<String, Integer> entry : mapA.entrySet()) {
            //intersection -  find the difference
            if (mapB.containsKey(entry.getKey())) {
                cnt += Math.abs(mapB.get(entry.getKey()) - entry.getValue());
            } else {
                //no in mapB, delete chars = frequency level
                cnt += entry.getValue();
            }
        }
        //find no in mapA to delete chars = frequency
        for (Map.Entry<String, Integer> entry : mapB.entrySet()) {
            //intersection -  find the difference
            if (!mapA.containsKey(entry.getKey())) {
                cnt += entry.getValue();
            }
        }
        return cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);
        System.out.println(res);
        //bufferedWriter.write(String.valueOf(res));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
