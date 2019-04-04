package matrix;

/**
 * @author up750825
 */
public class Matrix {
    
    public Matrix() {}
        
    public static void main(String[] args) {}
    
    /**
     * Performs matrices addition
     * @param matrix1 - 2D array
     * @param matrix2 - 2D array
     * @return matrix
     */
    public int[][] matrixSum(int[][] matrix1, int[][] matrix2) { 
       
        int rows = matrix1.length;
        int columns = matrix2[0].length;
        
        int matrix[][]=new int[rows][columns];
      
        for (int i = 0; i < rows; i++) {
           for (int j = 0; j < columns; j++) {
               matrix[i][j] = matrix1[i][j] + matrix2[i][j];
           }
        }
        
        return matrix;
    }
    
    /**
     * Performs matrices subtraction 
     * @param matrix1 - 2D array
     * @param matrix2 - 2D array
     * @return matrix
     */
    public int[][] matrixSubtract(int[][] matrix1, int[][] matrix2) { 
       
        int rows = matrix1.length;
        int columns = matrix2[0].length;
        
        int matrix[][]=new int[rows][columns];
      
        for (int i = 0; i < rows; i++) {
           for (int j = 0; j < columns; j++) {
               matrix[i][j] = matrix1[i][j] - matrix2[i][j];
           }
        }
        
        return matrix;
    }
    
    /**
     * Performs matrices multiplication
     * @param matrix1 - 2D array
     * @param matrix2 - 2D array
     * @return matrix
     */
    public int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
        
        int matrix1Row = matrix1.length;
        int matrix1Col = matrix1[0].length;
        int matrix2Col = matrix2[0].length;
        
        int matrix[][]=new int[matrix1Row][matrix2Col];
        
        for (int i = 0; i < matrix1Row; i++) {
           for (int j = 0; j < matrix2Col; j++) {
               for (int k = 0; k < matrix1Col; k++) {
                   matrix[i][j] = matrix[i][j] + matrix1[i][k] * matrix2[k][j];
               }
           }
        }
        
        return matrix;
    }
    
    
//    public int[][] matrixDivide(int[][] matrix1, int[][] matrix2) {
//
//        int rows = matrix1.length;
//        int columns = matrix2[0].length;
//        
//        int matrix[][]=new int[rows][columns];
//      
//        for (int i = 0; i < rows; i++) {
//           for (int j = 0; j < columns; j++) {
//               matrix[i][j] = matrix1[i][j] / matrix2[i][j];
//           }
//        }
//        
//        return matrix;
//    }
     
}
