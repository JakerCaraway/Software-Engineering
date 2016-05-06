package AntGame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mjc53
 */
public class Ant {
    private String colour;
    boolean alive;
    Cell position;
    World world;
    private int food;
    private int state;
    private int rest;
    private int direction;
    
    /**
     * Constructor of an Any
     * @param colour colour of the ant
     * @param world the world in which the ant will be found
     * @param position the current cell the ant is in
     */
    public Ant(String colour,World world,Cell position){
        this.world = world;
        this.colour = colour;
        this.position = position;
        alive = true;
        food = 0;
        rest = 0;
        direction = 0;
    }
    
    /**
     * check if there is food not amount
     * @return if there is food on the ant. 
     */
    public boolean getFood(){
        return (food>0);
    }
    
    /**
     * acessor for colour of the ant
     * @return the colour of the ant 
     */
    public String getColour(){
        return colour;
    }
    
    /**
     * moves the ant in the current direction the ant it facing
     */
    public void move(){
        position = position.getAdjacent(direction);
    }
    
    /**
     * senses in the desired direction for a specific condition in the next cell
     * @param Direction which adjacent cell to check
     * @param Condition the condition the ant is searching for
     * @return whether the condition found to be true in the specific cell 
     */
    public boolean sense(int Direction, String Condition){
        Cell checkCell = position.getAdjacent(Direction);
        
        if (Condition.equals("Food")){
            if (checkCell.getFood()>0)
                return true;
            else
                return false;
        } 
        
        if (Condition.equals("Rocky")){
            return checkCell.getRocky();
        }
        
        if (Condition.equals("Enemy")){
            if (checkCell.getAnt() != null){
                return (!(checkCell.getAnt().getColour().equals(colour)));
            } else return false;
        } 
        
        if (Condition.equals("EnemyWithFood")){
            if (checkCell.getAnt() != null){
                return (!(checkCell.getAnt().getColour().equals(colour)) && checkCell.getAnt().getFood());
            } else return false;
        } 
        
        if (Condition.equals("FriendWithFood")){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour().equals(colour) && checkCell.getAnt().getFood());
            } else return false;
        }
        
        if (Condition.equals("Friend")){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour().equals(colour));
            } else return false;
        }
        
        if (Condition.equals("Home")){
            return (checkCell.checkAntHill(colour));
        }
        
        if (Condition.equals("FoeHome"))
            return (checkCell.checkAntHill(world.getOtherColour(colour)));
        
        return false;
    }
    
    /**
     * Check for ant's colours pheramone in a cell
     * @param Direction cell to check
     * @param Pheramone pheramone to look for (only applies to friendly pheramones
     * @return wether the desired pheramone was found in the adjacent cell
     */
    public boolean sensePheramone(int Direction, int Pheramone){
        Cell checkCell = position.getAdjacent(Direction);
        return checkCell.checkPheromone(Pheramone,colour);
    }
    
    /**
     * Checks generaly for the presence of enemy pheraones in an adjacent cell
     * @param Direction the cell to check
     * @return if cell has the pheramone or not
     */
    public boolean senseEnemyPheramone(int Direction){
        Cell checkCell = position.getAdjacent(Direction);  
        boolean output = false;
        int count = 0;
        while ((output == false) && (count <=6)){
            output = checkCell.checkPheromone(count,world.getOtherColour(colour));
            count++;
        }
        return false;
    }
    
    /**
     * removes a pheramone belonging to the ants team in the current cell of the ant
     * @param pheramone the specific pheramone to remove
     * @param colour the ants team colour
     * @return if the desired pheramone was found in the current cell
     */
    public boolean deletePheramone(int pheramone, String colour){
        if (position.checkPheromone(pheramone, colour)==true){ 
            position.setPheramone(pheramone, colour);
            return true;
        } else return false;
    }
    
    /**
     * The ant places the desired pheramone in its current cell
     * @param pheramone the pheramone to put down
     * @param colour team of the ant
     * @return if the desired pheramone wasn't in discovered in the cell
     */
    public boolean setPheramone(int pheramone, String colour){
        if (position.checkPheromone(pheramone, colour)==false){ 
            position.setPheramone(pheramone, colour);
            return true;
        } else return false;
    }
    
    /**
     *  the ant will pick up any/all of the food found in the current cell;
     */
    public void PickUpFood(){
        food = position.getFood();
        position.setFood(0);
    }
    
    /**
     * the ant puts down all its food on the current cell 
     */
    public void PutDownFood(){
        position.setFood(food+position.getFood());
    }
    
    /**
     * is the ant alive or not
     * @return returns state of the ant 
     */
    public boolean getAlive(){
        return alive;
    }
    
    /**
     * murder the ant
     */
    public void kill(){
        alive = false;
    }
    
    /**
     * gets the current cell the ant is on
     * @return the current cell 
     */
    public Cell getPosition(){
        return position;
    }

    /**
     * accessor for the ants state in the FSM
     * @return the state the ant is in 
     */
    public int getState() {
        return state;
    }
    
    /**
    * Mutator for the state of the ant
    * @param state the state of the ant in the FSM  
    */
    public void setState(int state) {
        this.state = state;
    }
    /**
     * Accessor for the amount of time the ant has to rest for
     * @return the amount of rest left
     */
    public int getRest() {
        return rest;
    }

    /**
     * Sets the ants rest count back to full rest time
     */
    public void setRest() {
        rest = 14;
    }
    
    /**
     * decreases amount of rest by 1 each time it is called
     */
    public void decrRest() {
        if (rest > 0){
            rest -= 1;
        }
    }
    
    /**
     * Accessor for the direction the ant is facing
     * @return the current direction as a number
     */
    public int getDirection() {
        return direction;
    }

    /**
     * rotates the ant to the right 1
     */
    public void setDirectionR() {
        direction += 1;
        if (direction == 6){
            direction = 0;
        }
    }
    
    /**
     * rotates the ant to the left 1
     */
    public void setDirectionL(){
        direction -= 1;
        if (direction == -1){
            direction = 5;
        }
    }

    /**
     * mutator to change the ants position 
     * @param position the cell the ant is on
     */
    public void setPosition(Cell position) {
        this.position = position;
    }
}