import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/special-palindrome-again
 * main condition: strArr[i - offset] == strArr[i - 1]
 *              && strArr[i + offset] == strArr[i - 1]
 * triangle function for repaet combinations: n*(n+1)/2
 */
public class SpecialPalindrome {

    // Complete the substrCount function below.
    private static long substrCount(int n, String s) {
        long counter = 0;
        int length = s.length();
        char[] strArr = s.toCharArray();
        /*the answer is:
        mnonopoo
        1) count of all letters - length of the string (m n o n o p o o)
        2) count of all variety of palindromes (non ono opo oo)*/
        for (int i = 0; i < length; i++) {
            // repeatable letters as oo
            int repeats = 0;
            while (i + 1 < length && strArr[i] == strArr[i + 1]) {
                repeats++;
                i++;
            }
            //triangle: all combinations of repaeted n*(n+1)/2
            //n = 5: 1 + 2 + 3 + 4 + 5
            counter += repeats * (repeats + 1) / 2;
            // if the current symbol is in the middle of palindrome, non
            int offset = 1;
            while ( i - offset >= 0 && //left boundary
                    i + offset < length && //right boundary
                    strArr[i - offset] == strArr[i - 1] &&
                    strArr[i + offset] == strArr[i - 1]) {
                counter++;
                offset++;
            }
        }
        //answer 1 + 2
        return  length + counter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}