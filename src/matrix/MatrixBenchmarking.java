package matrix;

import java.util.ArrayList;
import java.util.Arrays;


public class MatrixBenchmarking {
    
    final static int N = 4;
    final static double randomNumberScale = 10;        
//    final static ArrayList<Integer> runs = new ArrayList<Integer>(Arrays.asList(2,4,8,16,32));
    final static ArrayList<Integer> runs = new ArrayList<Integer>(Arrays.asList(2,4));
    final static String calculationsType = "addition";  //multiplication, addition, subtraction, dividing

    static int count = 0;
    static int[][] matrix1;
    static int[][] matrix2;
    static int[][] Sequential_matrix;
    static float sequential_time = 0;
        
    public static void main(String[] args) throws Exception{

        System.out.println("*******************************************************************");
        System.out.println("Calculations Type: " + calculationsType);
        System.out.println("Matrix size N x N: " + N);
        System.out.println("Max Generated No.: " + randomNumberScale);

        matrix1 = matrixGenerator(N,N);
        matrix2 = matrixGenerator(N,N);
        
        System.out.println("*******************************************************************");
        printOutput(matrix1, "Matrix 1");
        printOutput(matrix2, "Matrix 2");
        
        // Calculate sequential first.
        calculateSequential(calculationsType);
    }
    
    /**
     * Performs sequential calculations.
     * @param type type of performed calculations: multiplication, addition, subtraction, dividing
     * @throws InterruptedException 
     */
    public static void calculateSequential(String type) throws InterruptedException{
        System.out.println("*******************************************************************");
        System.out.println("Sequential: ");

        long startTime = System.currentTimeMillis();

        Matrix SeqeuntialMatrix = new Matrix();
        switch(type) {
            case "multiplication": 
                Sequential_matrix = SeqeuntialMatrix.matrixMultiplication(matrix1, matrix2);
                break;
            case "addition": 
                Sequential_matrix = SeqeuntialMatrix.matrixSum(matrix1, matrix2);
                break;
            case "subtraction":
                Sequential_matrix = SeqeuntialMatrix.matrixSubtract(matrix1, matrix2);
                break;
            case "dividing": 
               // Sequential_matrix = SeqeuntialMatrix.matrixDivide(matrix1, matrix2);
                break;
        }
        
        long endTime = System.currentTimeMillis();
        sequential_time =  (endTime - startTime);
        
        printOutput(Sequential_matrix, type);

        System.out.printf("Calculation completed in %.0f milliseconds", sequential_time);
        System.out.println("");
        
        // Calculate parallel after sequential.
        calculateParallel(runs.get(count), calculationsType);
        
    }

    /**
     * Performs parallel calculations based on threads.
     * @param no_threads - number of threads 
     * @param type - type of performed calculations: multiplication, addition, subtraction, dividing
     * @throws InterruptedException 
     */
    public static void calculateParallel(int no_threads, String type) throws InterruptedException{

        System.out.println("*******************************************************************");
        System.out.println("No. of Threads: " + runs.get(count));

        int matrix[][] = new int[N][N];

        long startTime = System.currentTimeMillis();

            ParallelMatrix [] threads = new ParallelMatrix[no_threads];
            for(int me = 0 ; me < no_threads ; me++) {
                threads [me] = new ParallelMatrix(me,N,no_threads,matrix1,matrix2,matrix, type) ;
                threads [me].start() ;
            }

            for(int me = 0 ; me < no_threads ; me++) {
                threads [me].join();
            }

        long endTime = System.currentTimeMillis();
        
        float parallel_time = (endTime - startTime);
        
        printOutput(matrix, type);

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
            calculateParallel(runs.get(count), calculationsType);
        }
    }
        
    /**
     * Generate random matrix (2D arrays)
     * @param rowsNo - matrix number of rows
     * @param colNo - matrix number of columns
     * @return matrix
     */
    public static int[][] matrixGenerator(int rowsNo, int colNo) {
        
        int newMatrix[][] = new int[rowsNo][colNo];
        
        for (int i=0; i<newMatrix.length; i++) {
            for (int j=0; j<newMatrix[i].length; j++) {
                newMatrix[i][j] = (int) (Math.random()*randomNumberScale);
            } 
        }
        return newMatrix;
    }
    
    /**
     * Print out matrix.
     * @param matrix - matrix (2D array)
     * @param type - type of performed calculations: multiplication, addition, subtraction, dividing
     */
    public static void printOutput(int[][] matrix, String type) {
        
        System.out.printf("%s of A and B is: ", type);
        System.out.println("");
        for (int i = 0; i < matrix.length; i++) {
           for (int j = 0; j < matrix[0].length; j++) {
               System.out.print(matrix[i][j] + " ");                 
           }
           System.out.println();
        }
    }
}