package it.fragri;

/**
 * Class to test different multiplication algorithms' times and efficiency.
 * @author Francesco Grillo
 */
public class Multiplications{
    /** private constructor to prevent initialization */
    // private Multiplications(){}


    /**
     * Multiplicate two numbers by repeatedly adding \({@literal a}\) to the result, b times.
     * <p>T = O(n)
     * \({@literal |x|}\)
     * @param a multiplier
     * @param b multiplicand
     * @return product of a and b. \({@literal a*b}\)
     */
    public int repeatedAdditions(int a, int b){
        int prod = 0;
        while (b>0){
            prod += a;
            b--;
        }
        return prod;
    }

    /**
     * Multiplicate two numbers following this rule until \({@literal b > 0}\):
     * T = $\theta$(log_2(b))
     * <p> Theory: \({@literal a*b = 2a * \frac{1}{2}*b}\)
     * <p> With integers, the rule becomes as follows:
     * <p>\({@literal a*b =  \begin{cases} 2a * \frac{b}{2} \text{  if b is even}, \\ 2a * \frac{b-1}{2} + a \text{  if b is odd }\end{cases}} \)
     * <p> Basically it keeps doubling a and dividing by 2 b, only adding the current value of a to the result when b is odd.
     * @param a multiplier
     * @param b multiplicand
     * @return product of a and b. \({@literal a*b}\)
     */
    public int russianPeasant(int a, int b){
        int prod = 0;
        while (b>0){
            if (b%2 != 0){
                prod = prod+a;
            }
            b = b/2;
            a = a*2;
        }
        return prod;
    }
}