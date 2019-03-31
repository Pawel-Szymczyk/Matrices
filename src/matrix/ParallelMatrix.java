/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;


// more to do:
// different size of matrices, not only 2x2, but aslo: 2x5, 5x3, etc

/**
 *
 * @author up750825
 */
public class ParallelMatrix extends Thread {
    
    int me ;
    final static int N = 1024; // size of Matrix       // N cannot be smaller than P 
    //final static int N2 = 2;
    final static int randomNumberScale = 100;           // if the scale is too large the nunmbers are out of memory scope
    
    final static int P = 16; // number of threads;
    
    static int matrix1[][] = new int[N][N];
    static int matrix2[][] = new int[N][N];
    
    static int matrix[][]=new int[N][N];
   
    final static int B = N / P ;  // thread block size
    
    public ParallelMatrix(int me) {
        this.me = me ;
    }
    
    public static void main(String[] args) throws Exception{
                
        // generate two random matrixes of size N
        matrix1 = matrixGenerator(N,N);
        matrix2 = matrixGenerator(N,N);
        
        printOutput(matrix1, "null");
        printOutput(matrix2, "null");
        
        
        //----------------------------------------------------------------------
        // Benchmarking test
        //
        long startTime = System.currentTimeMillis();

            ParallelMatrix [] threads = new ParallelMatrix [P] ;
            for(int me = 0 ; me < P ; me++) {
                threads [me] = new ParallelMatrix(me) ;
                threads [me].start() ;
            }
  
            for(int me = 0 ; me < P ; me++) {
                threads [me].join() ;
            }
            
            printOutput(matrix, "product");     
        
        long endTime = System.currentTimeMillis();
  
        System.out.println("Calculation completed in " +
                             (endTime - startTime) + " milliseconds");
        //----------------------------------------------------------------------
    }
    
    public void run() {

        int begin = me * B ;
        int end = begin + B ;
  
        // multiplication matrices...
        for (int i = begin; i < end; i++) {
           for (int j = 0; j < N; j++) {
               for (int k = 0; k < N; k++) {
                   matrix[i][j] = matrix[i][j] + matrix1[i][k] * matrix2[k][j];
               }
           }
        }
        
        
                
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
