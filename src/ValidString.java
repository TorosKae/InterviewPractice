import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
public class ValidString {

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> cntFreqMap = new HashMap<>(); //frequency of frequency map =)
        int maxFreq = 0, minFreq = 0, cntMaxFreq = 0, cntMinFreq = 0;
        char[] charsArr = s.toCharArray();
        for (char curChar : charsArr) {

            int curFreq = freqMap.getOrDefault(curChar, 0) + 1;
            //max
            if (curFreq >= maxFreq) maxFreq = curFreq;

            freqMap.put(curChar, curFreq);
            cntFreqMap.put(curFreq, cntFreqMap.getOrDefault(curFreq, 0) + 1); //add cnt of curFreq frequency
            int prevFreq = curFreq - 1;
            if (cntFreqMap.containsKey(prevFreq)) {
                cntFreqMap.put(prevFreq, cntFreqMap.get(prevFreq) - 1); //remove an old cnt of curFreq frequency
                if (cntFreqMap.get(prevFreq) == 0) {
                    cntFreqMap.remove(prevFreq);
                }
            }
        }
        //min
        minFreq = maxFreq;
        for (Map.Entry<Integer, Integer> entry : cntFreqMap.entrySet()) {
            Integer key = entry.getKey();
            if (key < minFreq) minFreq = key;
        }
        cntMinFreq = cntFreqMap.get(minFreq);
        cntMaxFreq = cntFreqMap.get(maxFreq);
        System.out.println("freqMap " + freqMap.toString());
        System.out.println("cntFreqMap " + cntFreqMap.toString());
        System.out.println("minFreq" + minFreq);
        System.out.println("maxFreq" + maxFreq);
        System.out.println("cntMinFreq" + cntMinFreq);
        System.out.println("cntMaxFreq" + cntMaxFreq);

        //check
        if (minFreq == maxFreq) return "YES"; //all elements have the same freq
        if (cntMinFreq+cntMaxFreq!=freqMap.size()) return "NO";
        if (minFreq==1&&cntMinFreq==1) return "YES"; //delete one min element
        if ((maxFreq - 1 == minFreq) && cntMaxFreq == 1) return "YES"; //delete one max element
        return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        //bufferedWriter.write(result);
        //bufferedWriter.newLine();

        //bufferedWriter.close();
        System.out.println(result);
        scanner.close();
    }
}