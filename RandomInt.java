/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 *
 * @author jb555
 */
import java.math.*;
/**
 * This class is used to create a random number using a seed specified inside the
 * constructor using various formulas.
 * @author Jack
 */
public class RandomInt
{
    private int count;
    private BigInteger d;

    /**
     * This method initialises the values stored in the RandomInt class. This includes
     * the seed (d) and then changes it according to the specification given using 
     * multiplication and addition 4 times.
     */
    public RandomInt()
    {
        d = new BigInteger("12345");
        count = 0;
        for(int i = 0;i<4;i++){
            d = (d.multiply(new BigInteger("22695477"))).add(new BigInteger("1"));
        }
    }

    /**
     * This method is used to generate a new random number between 0 and m-1. It uses 
     * more maths according to the specification to change the d variable which is used
     * to calculate the output.
     * @param m the maximum random number to be found + 1
     * @return a random integer
     */ 
    public int generateRand(int m)
    {
        int number = 1000;
        BigInteger n = new BigInteger(Integer.toString(m));
        BigInteger num = d;
        BigInteger out;
        
        out = (num.divide(new BigInteger("65536"))).mod(new BigInteger("16384"));
        
        out = out.mod(n);
        
        num = (num.multiply(new BigInteger("22695477"))).add(new BigInteger("1"));
       
        d = num;
        return out.intValue();
    }
    
}