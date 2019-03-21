import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


/**
 * https://www.hackerrank.com/challenges/crush/
 * There is array of 0 length = n
 * there are some quarries like
 * from a to b add x value
 * find the maximum value
 *
 *
 * We will count summ in input (+summ)
 * and last on output b+1 (-summ)
 * f.e. 00000
 * 1) 11100 a(1)=1 a(4)=-1
 * 2) 01100 a(2)=1 a(4)=-2
 * 3) 00111 a(3)=1
 * 111-20
 * 1+1+1 = 3max
 * 3-2=1 not max
 * max = 3
 *
 */
public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    private static long arrayManipulation(int n, int[][] queries) {
        Long[] arr = new Long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = 0L;
        }
        Long max = 0L;

        for (int[] query : queries) {
            int a = query[0];
            int b = query[1];
            Long sum = Integer.toUnsignedLong(query[2]);
            //a-1 as in quarries index goes from 1
            arr[a - 1] += sum;
            if (b < n) {
                arr[b] -= sum;
            }
            System.out.print((a) + "=" + arr[a - 1] + " " + (b) + "=" + arr[b]);
            System.out.println();
        }
        Long tmp = 0L;
        for (int i = 0; i < n; i++) {
            tmp += arr[i];
            System.out.println(tmp);
            if (tmp.compareTo(max)>0) {
                max = tmp;
            }
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();
        System.out.println(result);
        scanner.close();
    }
}