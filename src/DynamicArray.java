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

public class DynamicArray {

    // Complete the dynamicArray function below.
    static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        System.out.println("кол-во массивов " + n);
        System.out.println("кол-во запросов " + queries.size());
        List<Integer> printArr = new ArrayList<>(); //массив для вывода ответа
        int lastAnswer = 0;
        //создаем массивы/посследовательности по кол-ву n
        ArrayList<ArrayList<Integer>> b = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            b.add(new ArrayList<Integer>());
        }

        for (ListIterator<List<Integer>> iter = queries.listIterator(); iter.hasNext(); ) {
            List<Integer> list = iter.next();
            System.out.println("querry #" + iter.nextIndex());
            System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
            //получаем индекс нужного массива (x XOR lastAnswer) mod n
            int t = (list.get(1) ^ lastAnswer) % n;
            //первый тип запроса
            if (list.get(0).equals(1)) {
                b.get(t).add(list.get(2));
            }
            //второй тип запроса
            if (list.get(0).equals(2)) {
                int idx = list.get(2) % b.get(t).size(); //индекс элемента y%[размер массива]
                lastAnswer = b.get(t).get(idx);
                printArr.add(lastAnswer);
            }
        }

        return printArr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nq = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nq[0]);

        int q = Integer.parseInt(nq[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = dynamicArray(n, queries);
        System.out.println(result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
                + "\n");
//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}