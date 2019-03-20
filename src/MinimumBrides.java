import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/new-year-chaos
 * ------------------------------------------------------
 * 1 person could swap forward for 2 position
 * queue      2 1 5 3 4
 * norm queue 1 2 3 4 5
 * answer 3:
 * 1 2 3 5 4
 * 1 2 5 3 4
 * 2 1 5 3 4
 * ------------------------------------------------------
 * 1) how far the element from it legal position:
 * a(i) - (i + 1) > 2 (>2 too chaotic)
 * 2) go forward from 2 elements back
 *    for 2143(4) from 5..4
 *    when 5 > 4 ans++
 *    3 < 4 no swap
 *    from Math.max(0, q[i] - 2) to i
 */

public class MinimumBrides {
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int ans = 0;
        //go from end
        for (int i = q.length - 1; i >= 0; i--) {
            //swap is more then 2
            //how far the element from it legal position
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            System.out.println(q[i]);

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


