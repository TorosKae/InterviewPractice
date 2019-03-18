import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class MinimumBrides {
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int ans = 0;
        for (int i = q.length - 1; i >= 0; i--) {
            //System.out.println(q[i] + " - "+(i + 1));
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            System.out.println(q[i]);
            //max(0, для первого элемента
            //-2 One person can bribe at most two others
            for (int j = Math.max(0, q[i] - 2); j < i; j++) {
                System.out.println(q[j] +">"+ q[i]);
                if (q[j] > q[i]) {ans++;
                System.out.println("        ans="+ans);}
            }
        }
        System.out.println(ans);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}


