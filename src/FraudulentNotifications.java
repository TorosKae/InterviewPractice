import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentNotifications {

    /*public static void countingSort( int[] input,int n)

    {
        int min=0,max=0;
        for (int i = 1; i < n; i++)
        {
            if (input[i] > max)
                max = input[i];
            if (input[i] < min)
                min = input[i];
        }
        int range = max - min + 1;
        int[] count = new int[range];
        //counts frequencies for each element
        for (int i = 0; i < n; i++)
            count[input[i] - min]++;
        // getting positions in final array
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];
        //copy to output array, preserving order of inputs with equal keys
        int j = 0;
        for (int i = 0; i < range; i++)
            while (j < count[i])
                input[j++] = i + min;
    }*/
    static double findMedian(TreeMap<Integer,Integer> arr, int length){
        double m=0;
        int tmp = 0;
        /*3 4 5 6
         3     6
         3
        we need 4
        so have to count needed frequency
         3 4 5 6
         3     6
         3     6
        we need 4 and 5 then (4+5)/2
        we'll turn it by calc into
         3 3 3 4 5 6 6 using tmp-1 index*/
        if(length%2==0){    //even
            //int idx=-1;
            double valLeft = 0;
            double valRight = 0;
            int weNeedLeft = length/2-1;
            //System.out.println("weNeedLeft "+weNeedLeft);
            int weNeedRight = length/2;
            //System.out.println("weNeedRight "+weNeedRight);
            for(Map.Entry e : arr.entrySet()){
                //idx++;
                //System.out.println(e.getKey()+" idx "+idx);
                tmp += (Integer) e.getValue(); //from 0
                //System.out.println("tmp "+tmp);
                if(valLeft==0&tmp-1>=weNeedLeft){ //tmp-1 is real full index
                    valLeft = (int) e.getKey();
                    //System.out.println("valLeft "+valLeft);
                }
                if(valLeft!=0&tmp-1>=weNeedRight){
                    valRight = (int) e.getKey();
                    //System.out.println("valRight "+valRight);
                    break;
                }
            }
            m = (valLeft + valRight)/2.0;
            //System.out.println("m "+m);
            //m=(arr[half-1]+arr[half])/2.0;
        }else{                  //odd
            int weNeed = (length-1)/2; //middle value
            //m=arr[(l-1)/2]; //middle value
            for(Map.Entry e : arr.entrySet()){
                tmp += (Integer) e.getValue();
                if(tmp-1>=weNeed){
                    m = (int) e.getKey();
                    break;
                }
            }
        }
        return m;
    }
    //add new element instead of gone element
    static TreeMap<Integer,Integer> updateArr(TreeMap<Integer,Integer> a, int del, int add){
//        int l = a.length;
//        for (int i = 0; i < l; i++) {
//           if(a[i]==del){
//               a[i]=add;
//           }
//        }
//        Arrays.sort(a);
        //add new value and remove the gone one
        Integer value = a.get(add);
        a.put(add, value==null ? 1 : value + 1);
        value = a.get(del) - 1;
        if(value<0){
            a.remove(del);
        }else {
            a.put(del, value);
        }

        return a;
    }
    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int notif = 0;
        //first statistics
        //System.arraycopy(expenditure,0,arr,0,d);

        //int[] arr = Arrays.copyOf(expenditure,d); //will sharing arr always sorted
        //Arrays.sort(arr);
        //create sorted map of values frequency
        //key is value in short expenditure
        //value is frequency
        TreeMap<Integer,Integer> arr = new TreeMap<>();
        for (int i = 0; i < d; i++) {
            Integer value = arr.get(expenditure[i]);
            arr.put(expenditure[i], value==null ? 1 : value + 1);
        }

        for (int i = d; i < expenditure.length; i++) {
            //notification conditions
            //System.out.println(expenditure[i]+" "+findMedian(arr)*2);
            if(expenditure[i]>=findMedian(arr,d)*2){
                notif++;
            }
            if(i<expenditure.length-1) {
                arr = updateArr(arr, expenditure[i - d], expenditure[i]);
            }
        }
        return notif;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
