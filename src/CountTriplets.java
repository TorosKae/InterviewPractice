import java.io.*;
import java.io.*;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * https://www.hackerrank.com/challenges/count-triplets-1
 * geom progression is b1, bn = b(n-1)*r
 * have queue 1 3 9 9 27 81 | 3
 * expMap is map of expected values by fact, f.e. for 3 it's 9
 * resMap is map to count result expected values in potentials
 *  we close expected val if it's te same as current
 *  and wait for new values based on count of expected current val
 *
 * 1: expMap (3,1)
 * 3: resMap (9,1)
 *    expMap (9,1)
 * 9: resMap (27,1)
 *    expMap (27,1)
 * 9: resMap (27,2)
 *    expMap (27,2)
 * 27:resMap (81,2) !!resMap <> expMap
 *    expMap (81,1)
 * 81:resMap (81*2,1)
 *    expMap (81*2,1)
 *
 * Count resMap values:
 * (9,1)(27,2)(81,2)(81*2,1) = 6
 */
public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long res = 0L;

        Map<Long,Long> expMap = new HashMap<>(); //expected
        Map<Long,Long> resMap = new HashMap<>(); //check fill of triple

        for (int i = 0; i < arr.size(); i++) {
            long curVal = arr.get(i);
            System.out.println("смотрим "+curVal);
            //if already there is full triplet, counting
            res += resMap.getOrDefault(curVal,0L);

            if (i==arr.size()-1){
                return res;
            }
            System.out.println("res = "+res);
            //if was suggested current value (waiting)
            //fill 3rd array with curValue, accounting other values of curVal key
            if (expMap.containsKey(curVal)){
                resMap.put(curVal*r,resMap.getOrDefault(curVal*r,0L)+expMap.get(curVal));
                System.out.println("ares "+resMap.toString());
            }
            //suggested 2nd value
            expMap.put(curVal*r, expMap.getOrDefault(curVal*r,0L)+1);
            System.out.println("aexp "+expMap.toString());
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);
        System.out.println(ans);
  //      bufferedWriter.write(String.valueOf(ans));
  //      bufferedWriter.newLine();

       // bufferedReader.close();
        //bufferedWriter.close();
    }
}
