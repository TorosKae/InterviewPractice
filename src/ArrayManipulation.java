import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        Long[] arr = new Long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = 0L;
        }
        Long max = 0L;

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            Long sum = Integer.toUnsignedLong(queries[i][2]);
            arr[a-1] += sum;
            if (b < n) { //n-1 тк от нуля
                arr[b] -= sum;
            }
            System.out.print((a)+"="+arr[a-1]+" "+(b)+"="+arr[b]);
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

    public static void main(String[] args) throws IOException {
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