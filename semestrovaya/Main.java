import java.util.*;

public class Main{
    public static void main(String[] args){
        int[][] matrix = {{0, 10, 18, 20},
                {45, 0, 131, 43},
                {32, 1, 0, 6},
                {123, 66, 9, 0}};
        System.out.println(floydsAlgorithm(matrix));
        for (int[] array : matrix){
            System.out.println(Arrays.toString(array));
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
