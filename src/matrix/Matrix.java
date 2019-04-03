/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author up750825
 */
public class Matrix {

    static int N1 = 10;
    static int N2 = 10;
    static int randomNumberScale = 10;
    
    
    public static void main(String[] args) {
        
        // test matrices
        //int a[][]={{1,1},{2,2}};    
        //int b[][]={{1,1},{2,2}};   
        
        // generate two random matrixes of size N
        int matrix1[][]= matrixGenerator(N1,N2);
        int matrix2[][]= matrixGenerator(N2,N1);
        
        
        
        printOutput(matrix1, "null");
        printOutput(matrix2, "null");
        // comment
        //----------------------------------------------------------------------
        // Benchmarking test
        //
        long startTime = System.currentTimeMillis();

            int[][] matrix = matrixMultiplication(matrix1, matrix2);
            printOutput(matrix, "product");
        
        long endTime = System.currentTimeMillis();
  
        System.out.println("Calculation completed in " +
                             (endTime - startTime) + " milliseconds");
        //----------------------------------------------------------------------
        
//        int[][] matrixSum = matrixSum(matrix1, matrix2);
//        printOutput(matrixSum, "sum");
//        
//        int[][] matrixSubtract = matrixSubtract(matrix1, matrix2);
//        printOutput(matrixSubtract, "subtract");
    }
    
    // generate a matrix
    public static int[][] matrixGenerator(int rowsNo, int colNo) {
        
        int newMatrix[][] = new int [rowsNo][colNo];
        for (int i=0; i<newMatrix.length; i++) {
            for (int j=0; j<newMatrix[i].length; j++) {
                newMatrix[i][j] = (int) (Math.random()*randomNumberScale);
            } 
        }
        return newMatrix;
    }
    
    // add matrices
    public static int[][] matrixSum(int[][] matrix1, int[][] matrix2) { 
       
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
    
    //subtract matrices
    public static int[][] matrixSubtract(int[][] matrix1, int[][] matrix2) { 
       
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
    
    // multiple matrices
    public static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
        
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
    
    // dividing matrices
    //    public static int[][] matrixDivide(int[][] matrix1, int[][] matrix2) {
    //
    //        return matrix;
    //    }
    
    // print out output
    public static void printOutput(int[][] matrix, String operationType) {
        
        switch(operationType) {
            case "product":
                System.out.println("Product of A and B is");
                break;
            case "sum": 
                System.out.println("Sum of A and B is");
                break;
            case "subtract": 
                System.out.println("Subtraction of A and B is");
                break;
            case "null":
                System.out.println("Matrix is");
                break;
        }
        
        for (int i = 0; i < matrix.length; i++) {
           for (int j = 0; j < matrix[0].length; j++) {
               System.out.print(matrix[i][j] + " ");                 
           }
           System.out.println();
        }
    }
    
    
    
}
