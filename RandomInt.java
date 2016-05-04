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
    private BigInteger seed;
    private int count;
    private BigInteger d;

    /**
     * Constructor for objects of class randGen
     */
    public RandomInt()
    {
        d = new BigInteger("12345");
        count = 0;
        for(int i = 0;i<4;i++){
            d = (d.multiply(new BigInteger("22695477"))).add(new BigInteger("1"));
        }
    }

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