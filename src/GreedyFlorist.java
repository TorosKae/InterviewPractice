import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
/**
 * we have:
 * k people
 * c.length flowers
 * c array of flowers cost
 * when man buy new flower
 * florists add price as (count of purchase + 1) x Price
 * for first time (0+1) x Price
 * second (1 + 1) x Price
 * people want to minimise price, so
 * 1) we goes from the greatest price
 * 2) every man buy one flower (until reserve is decreased)
 * 3) when reserve is off, add costPenalty + 1, restore the reserve
 * <p>
 * 5 flowers 3 people
 * cost: [1 3 5 7 9]
 * reserve = 3, costPenalty = 1
 * 9: 1*9=9; reserve--
 * 7: 1*7=7; reserve--
 * 5: 1*5=5; reserve--
 * 3: reserve off; reserve = 3; costPenalty++; 2*3=6
 * 1: 2*1=2
 * answer = 2 + 6 + 5 + 7 + 9 = 29
 */
public class GreedyFlorist {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] c) {
        int minCost = 0;
        int reserve = k;
        int costPenalty = 1;
        //O(n)
        Arrays.sort(c);
        for (int i = c.length-1; i >= 0; i--) {
            if (reserve == 0) {
                reserve = k-1;
                costPenalty++;
            } else {
                reserve--;
            }
            //costPenalty = (c.length - i - 1)/k + 1;
            minCost+=costPenalty*c[i];
        }
        return minCost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
