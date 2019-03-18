import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class MinimumSwaps {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        int[] aSorted = arr.clone();
        Arrays.sort(aSorted);
        int diff = 0;
        for (int i = 0; i < arr.length; i++) {
            //System.out.println("arr[i] "+arr[i]+ " (i+1)"+(i+1));
            if(aSorted[i]!=arr[i]){
                for (int j = i+1; j < arr.length; j++) {
                    if (aSorted[i]==arr[j]){
                        int a = arr[j];
                        int b = arr[i];
                        arr[j] = b; arr[i] = a;
                        diff++;
                        break;
                    }
                }
                //System.out.println("diff "+diff);
            }
        }
        return diff;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);
        System.out.println(res);
        //bufferedWriter.write(String.valueOf(res));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
