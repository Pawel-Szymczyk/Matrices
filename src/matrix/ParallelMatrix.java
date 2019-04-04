
package matrix;

/**
 * @author up750825
 */
public class ParallelMatrix extends Thread {
    
    int me ;
    
    int matrix1[][];
    int matrix2[][];
    int matrix[][];
    
    int N = 0;
    int B = 0; // thread block size
    
    String calculationsType = "";  //
    
    public ParallelMatrix(int me,int n,int p,int m1[][] ,int m2[][],int res[][], String type) {
        B = n/p;
        this.me = me ;
        
        matrix1 = m1;
        matrix2 = m2;
        N = n;
        matrix = res;
        
        calculationsType = type;
    }
 
    public void run() {

        int begin, end;
        
        begin = me * B ;
        end = begin + B ;
          
        switch(calculationsType) {
            case "multiplication":
                // multiplication matrices...
                for (int i = begin; i < end; i++) {
                   for (int j = 0; j < N; j++) {
                       for (int k = 0; k < N; k++) {
                           matrix[i][j] = matrix[i][j] + matrix1[i][k] * matrix2[k][j];
                       }
                   }
                };
                break;
            case "addition": 
                for (int i = begin; i < end; i++) {
                    for (int j = 0; j < N; j++) {
                        matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                    }
                }
                break;
            case "subtraction":
                for (int i = begin; i < end; i++) {
                    for (int j = 0; j < N; j++) {
                        matrix[i][j] = matrix1[i][j] - matrix2[i][j];
                    }
                }
                break;
            case "dividing": 
//                for (int i = begin; i < end; i++) {
//                    for (int j = 0; j < N; j++) {
//                        matrix[i][j] = matrix1[i][j] / matrix2[i][j];
//                    }
//                }
                break;
        }
               
    }


}
