
/**
 * Write a description of class Hex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hex
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private Hex[] Directions;
    private Boolean Ant;
    private Boolean[] Pheramones;
    
    /**
     * Constructor for objects of class Hex
     */
    public Hex(int x, int y)
    {
        // initialise instance variables
        this.x = x;
        this.y = y;
        Pheramones = new Boolean[12];
        Directions = new Hex[6];
        Ant = false;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Hex adjacent_cell(int d)
    {
        // put your code here
    return Directions[d];
    }
    
    public void setDir(int d, Hex adj){
        Directions[d]= adj;
    }
    
    public void MakeMap(){
        if (y<150 && x<150){        
            Hex newHex0 = new Hex(x+1,y);
            setDir(0,newHex0);
            newHex0.setDir(3,this);
            Hex newHex1 = new Hex(x+1,y+1);
            setDir(1,newHex1);
            newHex1.setDir(4,this);
            newHex0.setDir(2,newHex1);
            if (y % 2 != 0){
                newHex1 = new Hex(x,y+1);
                setDir(2,newHex1);
                newHex1.setDir(5,this);
                
            }
            newHex0.MakeMap();
            if (x==0){
            newHex1.MakeMap();
            }
        }
    }
}
