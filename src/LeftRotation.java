import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation
 *
 * Arrays: left rotation
 * by 1 elemens all elements shift to the left
 * in fact 1 element from left goes to the end of queue
 * f.e. 1 2 3 4 5 (4 rotation)
 * 23451 -> 34512 -> 45123 -> 51234
 *
 * for every element we could calc the exact index
 * -----------------
 * (i + n - d) % n
 * i - current index
 * n - length
 * d - shift
 * % - remainder controls
 * if element goes out of right boundary > length
 * -----------------
 */
public class LeftRotation {
    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int n = a.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int aItem = a[i];
            //calc index at once
            //where current element will be
            arr[(i+n-d)%n] = aItem;
        }
        return arr;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            //bufferedWriter.write(String.valueOf(result[i]));
            System.out.println(String.valueOf(result[i]));
            if (i != result.length - 1) {
                //bufferedWriter.write(" ");
                System.out.println(" ");
            }
        }

        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}


