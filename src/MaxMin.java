import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxMin {
    static int unfairness(int max, int min){
        return max-min;
    }
    // Complete the maxMin function below.
    static int maxMin(int k, int[] arr) {

        // 1) lets sort it first
        Arrays.sort(arr);
        // the start maximum
        int minUnfairness=arr[arr.length-1];
        // 2) move from the left and right simultaneously
        int iMin = 0;
        int iMax = k - 1;
        int jMin = arr.length - k;
        int jMax = arr.length - 1;

        while (iMax < jMax && iMin < jMin) {
            int unfairness = Integer.min(unfairness(arr[iMax],arr[iMin]),unfairness(arr[jMax],arr[jMin]));
            if(unfairness<=minUnfairness){
                minUnfairness = unfairness;
            }
            iMin++;iMax++;
            jMin--;jMax--;
        }
        return minUnfairness;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = maxMin(k, arr);
        System.out.println(result);
        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();*/

        scanner.close();
    }
}