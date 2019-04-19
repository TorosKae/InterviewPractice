import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/luck-balance
 * we have i conquests
 * and k constraints to loose
 * when you loose - you increase luck
 * you can loose only k of the important conquests (1)
 * when win luck decreased by value of conquest
 * k = 3
 * 5 1
 * 2 1
 * 1 1
 * 8 1
 * 10 0
 * 5 0
 * 10+5 (o importance) is guaranteed luck result + 15
 * find k maximum values. it's 8, 5, 2. Result + 15
 * decrease of luck after wining -1
 * max Luck is 29
 */
public class LuckBalance {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        int result = 0;
        /*
         [i][0] - value of luck in i conquest
         [i][1] - importance of i conquest
         when [i][1] = 0 res + luck [i][0]
         when [i][1] = 1 and is add max Values + luck [i][0], k--
            others i res--
         k times we choose max of [][1]
        */
        Integer[] sortedArr = new Integer[contests.length];

        for (int i = 0; i < contests.length; i++) {
            if(contests[i][1]==0){
                result+=contests[i][0];
                sortedArr[i] = 0;
                //System.out.println("result + "+contests[i][0]);
            } else {
                sortedArr[i] = contests[i][0];
            }
        }
        Arrays.sort(sortedArr,Collections.reverseOrder());
        int idx = 1;
        for (int i = 0; i < sortedArr.length; i++) {
            Integer c =sortedArr[i];
            //first k values to increase luck
            if (k - idx >= 0){
                result+=c;
                //System.out.println("result + "+c);
            } else { //values not in max k values, it decrease luck for 1
                result-=c;
                //System.out.println("result - "+1);
            }
            idx++;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
