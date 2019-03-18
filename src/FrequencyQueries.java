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

public class FrequencyQueries {

    static Map<Integer,Integer> updFreqMap(Map<Integer,Integer> freqMap,int newVal,int direction){
        //direction -1 for increament frequency, so previous value of frequency is decreament
        //+1 for decreament frequency, old largest frequency is decreament
        //add frequency of element
        if (newVal!=0){
        int newFreqVal = freqMap.getOrDefault(newVal,0)+1;
        //increament of cur frequensy
        freqMap.put(newVal,newFreqVal);}
        //decreament of previous
        int oldFreqVal = freqMap.getOrDefault(newVal+direction,0)-1;
        if (oldFreqVal<=0){
            freqMap.remove(newVal+direction);
        } else freqMap.put(newVal+direction,oldFreqVal);
        System.out.println("f: "+freqMap.toString());
        return freqMap;
    }
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(int[][] queries) {
        List<Integer> list = new ArrayList<>();
        //first - the element
        //second - the frequency
        Map<Integer,Integer> map = new HashMap<>();
        Map<Integer,Integer> freqMap = new HashMap<>();
        for (int[] q:queries) {
            Integer qType = q[0];
            Integer element = q[1];
            if(qType.equals(1)){
                System.out.println("add "+element);
                //add element
                int newVal = map.getOrDefault(element,0)+1;
                map.put(element,newVal);
                System.out.println("map "+map.toString());
                //add frequency of element
                freqMap = updFreqMap(freqMap,newVal,-1);
                continue;
            }
            if(qType.equals(2)&&map.containsKey(element)){
                System.out.println("del "+element);
                //int newVal = map.getOrDefault(element,1)-1;
                int newVal = map.get(element)-1;
                map.put(element,newVal);
                if (newVal<=0){
                    map.remove(element);
                }
                System.out.println("map "+map.toString());
                freqMap = updFreqMap(freqMap,newVal,+1);
                continue;
            }
            if(qType.equals(3)){
                Integer frequency = 0;
                if (freqMap.containsKey(element)){
                    frequency = 1;
                }
                list.add(frequency);
                System.out.println("map "+map.toString());
                System.out.println(frequency);
            }

        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] query = bufferedReader.readLine().split(" ");
            queries[i][0] = Integer.parseInt(query[0]);
            queries[i][1] = Integer.parseInt(query[1]);
        }

        List<Integer> ans = freqQuery(queries);
        //System.out.println(ans);
//        bufferedWriter.write(
//                ans.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );

        bufferedReader.close();
 //       bufferedWriter.close();
    }
}