import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        File input = new File("input.txt");
        try {
            Scanner scanner = new Scanner(input);
            File output = new File("output.txt");
            FileWriter fileWriter = new FileWriter(output);
            int[][] matrix = null;
            int w = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    w = 0;
                    if (matrix != null) {
                        double startTime = System.nanoTime();
                        int count = floydsAlgorithm(matrix);
                        double endTime = System.nanoTime();
                        fileWriter.write(count + " " + ((int) (endTime - startTime)) + " " + matrix.length * matrix.length + "\n");
                        matrix = null;
                    }

                } else {
                    String[] elements = line.split(" ");
                    if (matrix == null) {
                        int size = elements.length;
                        matrix = new int[size][size];
                        for (int i = 0; i < elements.length; ++i) {
                            if (w == i) {
                                matrix[w][i] = 0;
                            } else {
                                matrix[w][i] = Integer.parseInt(elements[i]);
                            }
                        }
                    } else {
                        for (int i = 0; i < elements.length; ++i) {
                            if (w == i) {
                                matrix[w][i] = 0;
                            } else {
                                matrix[w][i] = Integer.parseInt(elements[i]);
                            }
                        }
                    }
                    w++;
                }
            }
            fileWriter.close();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static int floydsAlgorithm(int[][] matrix){
        int n = matrix.length;
        int count = 0;
        for (int k = 0; k < n; ++k){
            for (int i = 0; i < n; ++i){
                for (int j = 0; j < n; ++j){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    count++;
                }
            }
        }
        return count;
    }
}
