import java.io.*;
import java.util.*;

public class RandomNumGenerator {
    public static void main(String[] args) {
        File file = new File("input.txt");
        Random random = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < 10000) {
            int num = random.nextInt(10001);
            set.add(num);
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (int num : set) {
                writer.write(num + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

