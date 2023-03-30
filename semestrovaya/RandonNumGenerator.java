import java.io.*;
import java.util.*;;

public class RandomNumGenerator{
    public static void main(String[] args){
        File file = new File("input.txt");
        try{
            FileWriter writer = new FileWriter(file);
            Random random = new Random();
            int numMatrices = new Random().nextInt(51) + 50;
            for (int i = 0; i < numMatrices; i++) {
                int size = new Random().nextInt(91) + 10;
                int[][] matrix = new int[size][size];
                for (int j = 0; j < size; j++){
                    for (int k = 0; k < size; k++){
                        matrix[j][k] = new Random().nextInt(9901) + 100;
                        writer.write(matrix[j][k] + " ");
                    }
                    writer.write("\n");
                }
                writer.write("\n");
            }
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
