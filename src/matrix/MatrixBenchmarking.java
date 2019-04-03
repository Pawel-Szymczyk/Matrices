/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;


import java.util.ArrayList;
import java.util.Arrays;


public class MatrixBenchmarking {
    
    
    final static int N = 1024;
    final static double randomNumberScale = 100;           

    static int count = 0;
    static ArrayList<Integer> runs = new ArrayList<Integer>(Arrays.asList(2,4,8,16,32));
    static int[][] matrix1;
    static int[][] matrix2;
    static int[][] Sequential_matrix;
    static float sequential_time = 0;
        
    public static void main(String[] args) throws Exception{

           System.out.println("*******************************************************************");
        System.out.println("Matrix size N x N: " + N);
        System.out.println("Max Generated No.: " + randomNumberScale);

        matrix1 = matrixGenerator(N,N);
        matrix2 = matrixGenerator(N,N);
        
        calculateSequential();
        
    }
    
    
     public static void calculateSequential() throws InterruptedException{
           System.out.println("*******************************************************************");
        System.out.println("Sequential: ");

        long startTime = System.currentTimeMillis();

        Sequential_matrix = matrixMultiplication(matrix1, matrix2);

        
         long endTime = System.currentTimeMillis();
         sequential_time =  (endTime - startTime);

        System.out.printf("Calculation completed in %.0f milliseconds", sequential_time);
        System.out.println("");
        calculateParallel(runs.get(count));
        
     }

    
        public static void calculateParallel(int no_threads) throws InterruptedException{

           System.out.println("*******************************************************************");
           System.out.println("No. of Threads: " + runs.get(count));
            
           int matrix[][] = new int[N][N];
           
           long startTime = System.currentTimeMillis();

               ParallelMatrix [] threads = new ParallelMatrix[no_threads];
               for(int me = 0 ; me < no_threads ; me++) {
                   threads [me] = new ParallelMatrix(me,N,no_threads,matrix1,matrix2,matrix) ;
                   threads [me].start() ;
               }

               for(int me = 0 ; me < no_threads ; me++) {
                   threads [me].join();
               }


           long endTime = System.currentTimeMillis();

           float parallel_time = (endTime - startTime);
           
           if(Arrays.deepEquals(Sequential_matrix,matrix)){
               System.out.printf("Calculation completed in %.0f milliseconds", parallel_time);
               System.out.println("");

               float parallel_speed_up = sequential_time/parallel_time;
               System.out.printf("Parallel Speed_ Up %.2f", parallel_speed_up);
                System.out.println("");
           }else{
               System.out.println("Parallel Matrix NOT equal Sequential Matrix");
           }
           
            count++;           
           if(count <= runs.size()-1){
            calculateParallel(runs.get(count));
           }
          
        }
        
        
    public static int[][] matrixGenerator(int rowsNo, int colNo) {
        
        int newMatrix[][] = new int[rowsNo][colNo];
        
        for (int i=0; i<newMatrix.length; i++) {
            for (int j=0; j<newMatrix[i].length; j++) {
                newMatrix[i][j] = (int) (Math.random()*randomNumberScale);
            } 
        }
        return newMatrix;
    }
    
    
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
    
}









