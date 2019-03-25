import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * url https://www.hackerrank.com/challenges/common-child
 * ABCDEF
 * FBDAMN
 * <p>
 * building matrix like
 * 0 F B D A M N
 * 0 0 0 0 0 0 0 0
 * A 0
 * B 0
 * C 0
 * D 0
 * E 0
 * F 0
 * <p>
 * first 0 is about need of previous values
 * if i = j then value(i,j) = i-1,j-1 + 1           | diagonal
 * else value(i,j) = max of (i,j-1)&(i-1,j)| left and upper values
 * ...
 */

public class CommonChild {
    // Complete the commonChild function below.
    private static int commonChild(String s1, String s2) {
        //reduce of iterations
        //make strings shorter
        //TreeMap to keep order
        Map<Integer, Character> map1 = new TreeMap<>();
        Map<Integer, Character> map2 = new TreeMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                char charOfS1 = s1.charAt(i);
                char charOfS2 = s2.charAt(j);
                if (charOfS1 == charOfS2) {
                    map1.put(i, charOfS1);
                    map2.put(j, charOfS2);
                }
            }
        }
        for (Map.Entry<Integer, Character> entry : map1.entrySet()) {
            stringBuilder.append(entry.getValue());
        }
        s1 = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Character> entry : map2.entrySet()) {
            stringBuilder.append(entry.getValue());
        }
        s2 = stringBuilder.toString();
        /*
        ------------------------------------------------
        */
        int lengthS1 = s1.length();
        int lengthS2 = s2.length();
        int[][] arr = new int[lengthS1+1][lengthS2+1];
        for (int i = 1; i <= lengthS1; i++) {
            for (int j = 1; j <= lengthS2; j++) {
                char charI = s1.charAt(i - 1); //from 0
                char charJ = s2.charAt(j - 1); //from 0
                arr[i][j] = (charI==charJ) ?
                             arr[i-1][j-1] + 1 :
                             Math.max(arr[i-1][j],arr[i][j-1]);
            }
        }

        return arr[lengthS1][lengthS2];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
