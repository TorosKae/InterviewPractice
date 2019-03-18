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

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long res = 0L;

        Map<Long,Long> a2 = new HashMap<>(); //expected
        Map<Long,Long> a3 = new HashMap<>(); //check fill of triple
        Iterator last = arr.listIterator();
        for (int i = 0; i < arr.size(); i++) {
            long curVal = arr.get(i);
            System.out.println("смотрим "+curVal);
            //if already there is full triplet, counting
            res += a3.getOrDefault(curVal,0L);

            if (i==arr.size()-1){
                return res;
            }
            System.out.println("res = "+res);
            //if was suggested current value (waiting)
            //fill 3rd array with curValue, accounting other values of curVal key
            if (a2.containsKey(curVal)){
                a3.put(curVal*r,a3.getOrDefault(curVal*r,0L)+a2.get(curVal));
                System.out.println("a3 "+a3.toString());
            }
            //suggested 2nd value
            a2.put(curVal*r, a2.getOrDefault(curVal*r,0L)+1);
            System.out.println("a2 "+a2.toString());
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
