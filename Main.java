import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Item {
    int value;
    int weight;
}

public class Main {
    static int c;
    static int n;
    static List<Item> items = new ArrayList<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFromFile(args[0]);
        // init result arr
        for (int i = 0; i < n; i++) {
            result.add(1);
        }
        // start brute
        System.out.println(bruteForce(n, items, c, 0));
        // print answer
        for (Integer index : result) {
            System.out.print(index);
        }
        System.out.println();
    }

    public static int bruteForce(int count_iterations, List<Item> items, int capacity, int iteration) {
        int in;
        int out;
        if (iteration == count_iterations || capacity <= 0) {
            return 0;
        }
        if (items.get(iteration).weight > capacity) {
            out = bruteForce(count_iterations, items, capacity, iteration + 1);
            return out;
        } else {
            in = bruteForce(count_iterations, items, capacity - items.get(iteration).weight, iteration + 1) + items.get(iteration).value;
            out = bruteForce(count_iterations, items, capacity, iteration + 1);
            int curr_item = Math.max(in, out);
            if (in < out && result.get(iteration) != 0) {
                result.set(iteration, 0);
                for (Integer index : result) {
                    System.out.print(index);
                }
                System.out.println();
            }
            return curr_item;
        }
    }


    public static void readFromFile(String path) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(path));
        String[] line = bfr.readLine().split(" ");
        if (line.length != 0) {
            c = Integer.parseInt(line[0]);
            n = Integer.parseInt(line[1]);
        }

        System.out.println("k = " + c + " n = " + n);
        String[] value = bfr.readLine().split(",");
        String[] weight = bfr.readLine().split(",");
        if (weight.length == value.length) {
            for (int i = 0; i < weight.length; i++) {
                Item item = new Item();
                item.weight = Integer.parseInt(weight[i]);
                item.value = Integer.parseInt(value[i]);
                items.add(item);
            }
        }
    }
}
