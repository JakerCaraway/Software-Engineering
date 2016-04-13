import java.util.*;
/**
 * Write a description of class World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World
{
    // instance variables - replace the example below with your own
    private ArrayList<Hex> World;
    private Hex Recursion1;
    
    /**
     * Constructor for objects of class World
     */
    public World()
    {
        // initialise instance variables
        int sizeX = 150;
        int sizeZ = 150;
        World = new ArrayList<>();
    }

    public void CreateNewWorld()
    {
        for (int i = 0 ; i <150; i++){
            for(int j = 0; j<150; j++){
                World.add(new Hex(i,j));
            }
        }
        
        Recursion1 = new Hex(0,0);
        Recursion1.MakeMap();
        
    }
}
