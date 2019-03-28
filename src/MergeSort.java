import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/ctci-merge-sort
 * 2 1 3 1 2 count of swaps = 4
 * we have: 58431627
 * divide 5843 1627
 * 58 43 16 27
 * 5 8 4 3 1 6 2 7
 * 58 34 16 27
 * for two parts 58(i) and 34(j)
 * we have aux [0 0 0 0]
 * left > mid :
 * 5>3 => 38 54
 *
 */
public class MergeSort {
    private static long cnt = 0;

    private static long countInversions(int[] arr) {
        int[] aux = arr.clone();
        countInversions(arr, 0, arr.length - 1, aux);
        return cnt;
    }

    private static void countInversions(int[] arr, int left, int right, int[] aux) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        //divide left side
        countInversions(aux, left, mid, arr);
        //divide right side
        countInversions(aux, mid + 1, right, arr);
        //merge split left and right sides
        merge(arr, left, mid, right, aux);
        //aux and arr changes
        //so we on every iteration have newest aux and for update arr
    }

    private static void merge(int[] arr, int leftStart, int mid, int rightEnd, int[] aux) {
        int i = leftStart, j = mid + 1, ind = leftStart;
        //go from left to mid
        //and from mid to end
        // simultaneously
        while (i <= mid || j <= rightEnd) {
            //left out of boundaries
            //finish swaps
            if (i > mid) {
                arr[ind++] = aux[j++];
            } else if (j > rightEnd) {
                //right out of boundaries
                //finish swaps
                arr[ind++] = aux[i++];
            //no changes, just update curr value of arr
            } else if (aux[i] <= aux[j]) {
                arr[ind++] = aux[i++];
            } else {
                //swaps
                arr[ind++] = aux[j++];
                //main idea it's need mid + 1 - i swaps to change an order
                cnt += mid + 1 - i;
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);
            cnt = 0;
            System.out.println(result);
            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
