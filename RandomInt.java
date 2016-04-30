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
 * Write a description of class randGen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomInt
{
    // instance variables - replace the example below with your own
    private BigInteger seed;
    private int count;

    /**
     * Constructor for objects of class randGen
     */
    public RandomInt()
    {
        // initialise instance variables
        seed = new BigInteger("12345");
        count = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int generateRand(int m)
    {
        int number = 1000;
        BigInteger n = new BigInteger(Integer.toString(m));
        BigInteger d = seed;
        BigInteger[] s = new BigInteger[number];
        BigInteger[] x = new BigInteger[number-4];
        BigInteger out;
        
        
        for (int i = 0; i<number;i++){
            s[i] = d;
            d = (d.multiply(new BigInteger("22695477"))).add(new BigInteger("1"));
        }
        for (int i = 0; i<number-4;i++){
            x[i] = (s[i+4].divide(new BigInteger("65536"))).mod(new BigInteger("16384"));
        }
        out = x[count].mod(n);
        count ++;
        
        return out.intValue();
    }
    
}