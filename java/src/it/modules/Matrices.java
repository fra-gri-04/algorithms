package it.modules;

/**
 * Class to test different matrix algorithms' times and efficiency.
 * 
 * represents matrices of NxN dimensions as a vector of N*N elements:
 * M (2x2) => int[] matrix = new int[] {M[1,1], M[1,2], M[2,1], M[2,2]} 
 * 
 * @author Francesco Grillo
 */
public class Matrices {
    public Matrices() {
    }
    /* Matrix dimension with which every matrix is instantiated. 
    * Keep it a power of 2 to for optimal strassen's algorithm execution.
    */
    public static int MATRIX_DIM = 4;

    /**
     * Standard matrix multiply rows times columns
     * every cell C[i,j] contains the sum of the i row and the j column elements
     * such as
     * C[i,j] = a[i,0] * b[0,j] + a[i,1] * b[1,j] + ... + a[i, N] * b[N,j]
     * @param A int[dim*dim] square matrix 
     * @param B int[dim*dim] square matrix 
     * @return \({@literal C = A \times B }\)
     */
    public int[] matMultiply(int[] A , int[] B, int dim){
        int[] C = new int[dim*dim];
        for (int i=0; i<dim; i++){
            for (int j=0; j<dim; j++){
                int value = 0;
                for (int k=0; k<dim; k++){
                    int A_pos = dim * i + k;
                    int B_pos = dim * k + j;
                    value += A[A_pos] * B[B_pos];
                }
                C[dim * i + j] = value;
            }
        }
        return C;
    }
    private int[] add(int[] A, int[] B, int dim){
        if (dim == 1)
            return new int[]{A[0] + B[0]};
        int[] C = new int[dim*dim];
        for (int i=0; i<dim; i++)
            for (int j=0; j<dim; j++)
                C[i*dim + j] = A[i*dim + j] + B[i*dim + j];
        return C;
    }
    private int[] sub(int[] A, int[] B, int dim){
        if (dim == 1)
            return new int[]{A[0] - B[0]};

        int[] C = new int[dim*dim];
        for (int i=0; i<dim; i++)
            for (int j=0; j<dim; j++)
                C[i*dim + j] = A[i*dim + j] - B[i*dim + j];
        return C;
    }

    /** 
     * Strassen Multiply
     * @require a dimension of a power of 2
     * @param A int[MATRIX_DIM*MATRIX_DIM] square matrix 
     * @param B int[MATRIX_DIM*MATRIX_DIM] square matrix 
     * @return \({@literal C = A \times B }\)
     * 
     * https://en.wikipedia.org/wiki/Strassen_algorithm
     * 
     * calculate calculate M1,M2, ..., M7 
     * int m1 = (a1 + a4) * (b1 + b4);
     * int m2 = (a3 + a4) * b1;
     * int m3 = a1 * (b2 - b4);
     * int m4 = a4 * (b3 - b1);
     * int m5 = (a1 + a2) * b4;
     * int m6 = (a3 - a1) * (b1 + b2);
     * int m7 = (a2 - a4) * (b3 + b4);
     * 
     * int[] C = new int[] { m1 + m4 - m5 + m7, m3 + m5, m2 + m4, m1-m2 + m3 + m6 };
     */
    public int[] strassenMatMultiply(int[] A, int[] B, int dim){
        // until A and B are 2x2: (2x2 = [0,1,2,3])
        int new_dim = dim / 2 * 2 < dim ? (dim+1)/2 : dim/2;
        if (new_dim >= 2){
            /* 
            * divide matrix in 4 quarters of dim/2 elements
            * every quarter has an integer number of elements called new_dim
            * new_dim * 2 must be >= MATRIX_DIM, in order to avoid losing matrix cells.
            * the new arrays are initiated full of zeros.
            */
            
            int[] a1 = new int[new_dim * new_dim];
            int[] b1 = new int[new_dim * new_dim];
            int[] a2 = new int[new_dim * new_dim];
            int[] b2 = new int[new_dim * new_dim];
            int[] a3 = new int[new_dim * new_dim];
            int[] b3 = new int[new_dim * new_dim];
            int[] a4 = new int[new_dim * new_dim];
            int[] b4 = new int[new_dim * new_dim];

            /* 
             * If the current index points to the left-top half, fills a1 and b1, if it is on the right-top fills a2 and b2 and so on.
             */
            for (int i=0; i < dim; i++){
                for (int j=0; j < dim; j++){
                    int i_local = i % new_dim;
                    int j_local = j % new_dim;
                    int partition_pos = i_local * new_dim + j_local;
                    int origin_pos = i * dim + j;

                    if (i < new_dim)
                        if (j < new_dim){
                            a1[partition_pos] = A[origin_pos];
                            b1[partition_pos] = B[origin_pos];

                        }else{
                            a2[partition_pos] = A[origin_pos];
                            b2[partition_pos] = B[origin_pos];
                        }
                    else
                        if (j < new_dim){
                            a3[partition_pos] = A[origin_pos];
                            b3[partition_pos] = B[origin_pos];

                        }else{
                            a4[partition_pos] = A[origin_pos];
                            b4[partition_pos] = B[origin_pos];
                        }
                }
            }
            // for a clearer view, read the function comment
            int[] m1 = strassenMatMultiply(add(a1, a4, new_dim), add(b1, b4, new_dim), new_dim);
            int[] m2 = strassenMatMultiply(add(a3, a4, new_dim), b1, new_dim);
            int[] m3 = strassenMatMultiply(a1, sub(b2, b4, new_dim), new_dim);
            int[] m4 = strassenMatMultiply(a4, sub(b3, b1, new_dim), new_dim);
            int[] m5 = strassenMatMultiply(add(a1, a2, new_dim), b4, new_dim);
            int[] m6 = strassenMatMultiply(sub(a3, a1, new_dim), add(b1, b2, new_dim), new_dim);
            int[] m7 = strassenMatMultiply(sub(a2, a4, new_dim), add(b3, b4, new_dim), new_dim);

            // int[] C = new int[] { m1 + m4 - m5 + m7, m3 + m5, m2 + m4, m1-m2 + m3 + m6 };
            int[] c1 = sub(add(m1, m4, new_dim), add(m5, m7, new_dim), new_dim);
            int[] c2 = add(m3, m5, new_dim);
            int[] c3 = add(m2, m4, new_dim);
            int[] c4 = add(sub(m1, m2, new_dim), add(m3, m6, new_dim), new_dim);

            // let's put c1,c2,c3,c4 into C
            int[] C = new int[dim*dim];
            
            for (int i = 0; i < new_dim; i++)
                for (int j=0; j < new_dim; j++){
                    // top-left:
                    C[i*dim + j] = c1[i*new_dim+j];
                    // top-right:
                    C[i*dim + new_dim + j] = c2[i*new_dim+j];
                    // bottom-left:
                    C[(i+new_dim)*dim + j] = c3[i*new_dim+j];
                    // bottom-right:
                    C[(i+new_dim) * dim + j + new_dim] = c4[i*new_dim+j];
                }

            return C;
        }
        int m1 = (A[0] + A[3]) * (B[0] + B[3]);
        int m2 = (A[2] + A[3]) * B[0];
        int m3 = A[0] * (B[1] - B[3]);
        int m4 = A[3] * (B[2] - B[0]);
        int m5 = (A[0] + A[1]) * B[3];
        int m6 = (A[2] - A[0]) * (B[0] + B[1]);
        int m7 = (A[1] - A[3]) * (B[2] + B[3]);
        int[] C = new int[] { m1 + m4 - m5 + m7, m3 + m5, m2 + m4, m1 - m2 + m3 + m6 };
        
        // every position of C is determined by strassen
        return C;
    }

    static void printMatrix(int[] m, int dim){
        for (int i=0; i < dim * dim; i++){
            System.out.print(m[i]+" ");
            if (((i+1) % dim )== 0) System.out.println();
        }
        System.out.println();
    }

    static void test(){
        Matrices m = new Matrices();
        int[] A = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        
        System.out.println("Matrix multiply: ");
        printMatrix(m.matMultiply(A,A, MATRIX_DIM), MATRIX_DIM);       
        System.out.println("Strassen multiply: ");
        printMatrix(m.strassenMatMultiply(A,A, MATRIX_DIM), MATRIX_DIM);       
    }
    public static void main(String[] args) {
        test();
    }
}