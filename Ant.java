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
class Ant {
    private String colour;
    boolean alive;
    Cell position;
    World world;
    private int food;
    private int state;
    private int rest;
    
    public Ant(String colour,World world,Cell position){
        this.world = world;
        this.colour = colour;
        this.position = position;
        alive = true;
        food = 0;
        rest = 0;
    }
    
    public boolean getFood(){
        return (food>0);
    }
    
    public String getColour(){
        return colour;
    }
    
    public void move(int Direction){
        position = position.getAdjacent(Direction);
    }
    
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
    
    public boolean sensePheramone(int Direction, int Pheramone){
        Cell checkCell = position.getAdjacent(Direction);
        return checkCell.checkPheromone(Pheramone,colour);
    }
    
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
    
    public boolean deletePheramone(int pheramone, String colour){
        if (position.checkPheromone(pheramone, colour)==true){ 
            position.setPheramone(pheramone, colour);
            return true;
        } else return false;
    }
    
    public boolean setPheramone(int pheramone, String colour){
        if (position.checkPheromone(pheramone, colour)==false){ 
            position.setPheramone(pheramone, colour);
            return true;
        } else return false;
    }
    
    public void PickUpFood(){
        food = position.getFood();
    }
    
    public void PutDownFood(){
        position.setFood(food+position.getFood());
    }
    
    public boolean getAlive(){
        return alive;
    }
    
    public void kill(){
        alive = false;
    }
    
    public Cell getPosition(){
        return position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public int getRest() {
        return rest;
    }

    public void setRest() {
        rest = 14;
    }
    
    public void decrRest() {
        if (rest > 0){
            rest -= 1;
        }
    }
}
