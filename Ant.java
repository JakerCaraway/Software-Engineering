package AntClass;

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
    
    public Ant(String colour,World world){
        this.world = world;
        this.colour = colour;
        alive = true;
        food = 0;
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
        
        if (Condition == "Food"){
            if (checkCell.getFood()>0)
                return true;
            else
                return false;
        } 
        
        if (Condition == "Rocky"){
            return checkCell.getRocky();
        }
        
        if (Condition == "Enemy"){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour() != colour);
            } else return false;
        } 
        
        if (Condition == "EnemyWithFood"){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour() != colour && checkCell.getAnt().getFood());
            } else return false;
        } 
        
        if (Condition == "FriendWithFood"){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour() == colour && checkCell.getAnt().getFood());
            } else return false;
        }
        
        if (Condition == "Friend"){
            if (checkCell.getAnt() != null){
                return (checkCell.getAnt().getColour() == colour);
            } else return false;
        }
        
        if (Condition == "Home"){
            return (checkCell.checkAntHill(colour));
        }
        
        if (Condition == "FoeHome")
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
}
