/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 * This class imitates a cell of the game world. The purpose of the class is
 * to contain any information about the specific cell that it imitates. For 
 * example if it is a rock, contains food or contains an ant.
 * @author Jack
 */
public class Cell {
    private final int PheromoneNo = 12;
    
    private boolean Rocky;
    private int Food;
    private Ant Ant;
    private boolean[] Pheromones;
    private boolean RedAntHill;
    private boolean BlackAntHill;
    private Cell[] AdjacentCells;
    private int x,y;
    
    /**
     * This method initialises the values stored in the Cell class. This includes
     * setting the co-ordinates of the cell, setting rocky to false, setting food 
     * to 0, initialising the cell to not contain an ant, initialising the cell to
     * not contain any pheromones, initialising the cell to not be an ant hill and 
     * initialising the adjacent cells array.
     * @param x the x co-ordinate of the cell on the world
     * @param y the y co-ordinate of the cell on the world
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        Rocky = false;
        Food = 0;
        Ant = null;
        Pheromones = new boolean[12];
        for(int i = 0; i<PheromoneNo; i++){
            Pheromones[i] = false;
        }
        RedAntHill = false;
        BlackAntHill = false;
        AdjacentCells = new Cell[6];
    }
    
    /**
     * This method is used to toggle whether the cell is a rock or not
     */
    public void setRocky(){
        Rocky = !Rocky;
    }
    
    /**
     * This method is used to return if the cell is rocky or not
     * @return a boolean value indicating if the cell is a rock or not
     */
    public boolean getRocky(){
        return Rocky;
    }
    
    /**
     * This method sets the food variable to an inputted amount
     * @param amount the amount of food that the cell needs to hold
     */
    public void setFood(int amount){
        Food = amount;
    }
    
    /**
     * This method is used to return how much food the cell currently holds
     * @return number the food variable contains
     */
    public int getFood(){
        return Food;
    }
    
    /**
     * This method sets the ant variable to the Ant class inputted
     * @param ant an Ant class
     */
    public void setAnt(Ant ant){
        Ant = ant;
    }
    
    /**
     * This method returns the ant variable
     * @return ant variable
     */
    public Ant getAnt(){
        return Ant;
    }
    
    /**
     * This method sets the ant variable to null
     */
    public void removeAnt(){
        Ant = null;
    }
    
    /**
     * This method returns true if the aant variable contains a value, else false
     * @return a boolean value
     */
    public boolean isAnt(){
        return !(Ant == null);
    }
    
    /**
     * This method toggles a boolean value in the pheramones array depending
     * on the pheramone value and colour inputted
     * @param Pheromone number pheramone to toggle
     * @param Colour colour of the ant setting the pheramone, used to determine what set of 6 to toggle
     */
    public void setPheramone(int Pheromone,String Colour){
        if (Colour.equals("black"))
                Pheromone += 6;
        Pheromones[Pheromone] = !Pheromones[Pheromone];
    }
    
    /**
     * This method checks the pheramone array value at the value found
     * using the inputted pheromone value and colour
     * @param Pheromone number pheromone to check
     * @param colour of the ant, used to determine what set of 6 to check
     * @return a boolean value that the array place contains
     */
    public boolean checkPheromone(int Pheromone,String Colour){
        if (Colour.equals("black"))
                Pheromone += 6;
        return Pheromones[Pheromone];
    }
    
    /**
     * This method sets the ant hill value corresponding to the colour inputted to true
     * @param colour The colour ant hill to change to true
     */
    public void setAntHill(String colour){
        switch (colour) {
            case "red":
                RedAntHill = true;
                break;
            case "black":
                BlackAntHill = true;
                break;
        }
    }
    
    /**
     * This method checks the ant hill value corresponding to the inputted colour and outputs the
     * boolean value that it contains
     * @param colour the colour ant hill to check
     * @return a boolean value that either RedAntHill or BlackAntHill contains
     */
    public boolean checkAntHill(String colour){
        switch (colour) {
            case "red":
                return RedAntHill;
            case "black":
                return BlackAntHill;
        }
        return false;
    }
    
    /**
     * This method returns the cell that is adjacent to the current cell on the side inputted
     * @param side the number side to look at
     * @return the Cell stored in the adjacent array at position 'side'
     */
    public Cell getAdjacent(int side){
        return AdjacentCells[side];
    }
    
    /**
     * This method sets the value in the adjacentCells array at position 'side' inputted
     * to the Cell inputted
     * @param side The number side to change
     * @param adj The cell to input into the array
     */
    public void setAdjacent(int side, Cell adj){
        AdjacentCells[side] = adj;
    }
    

    /**
    * Recursive function for the creation of the world/map using a linked list structure(not my best idea ever)
    * @param SizeX total size of the world in the x axis
    * @param SizeY total size of the world in the y axis
    */
    public void MakeMap(int SizeX,int SizeY){
        if (x<SizeX && y<SizeY){
            Cell newCell0;
            Cell newCell1;
            Cell newCell2;
            Cell newCell3;
            if (y>1){
                newCell0 = getAdjacent(5).getAdjacent(1);          
            } else {
                newCell0 = new Cell(x+1,y);
            }
            setAdjacent(0, newCell0);
            newCell0.setAdjacent(3, this);  
            if (((y%2) == 0)){ //&& (x == 1)){
                newCell2 = new Cell(x,y+1);
                
                setAdjacent(2, newCell2);
                newCell2.setAdjacent(5, this);
                
                if (x>1){
                    newCell3 = getAdjacent(3);
                    newCell3.setAdjacent(1, newCell2);
                    newCell2.setAdjacent(4, newCell3);
                }
                newCell0.MakeMap(SizeX, SizeY);
                if (x == 1){
                    newCell2.MakeMap(SizeX, SizeY);
                }
            }else{
                newCell1 = new Cell(x,y+1);
                setAdjacent(1, newCell1);
                newCell1.setAdjacent(4, this);
            
                newCell0.setAdjacent(2, newCell1);
                newCell1.setAdjacent(5, newCell0);
                newCell0.MakeMap(SizeX, SizeY);
                if (x == 1){
                    newCell1.MakeMap(SizeX, SizeY);
                }
            }
        } else {
            if(x == SizeX){
            
            if (y%2 == 0){
                Cell newCell5 = new Cell(x,y+1);
                setAdjacent(2, newCell5);
                newCell5.setAdjacent(5, this);
                if (x>1){
                    Cell newCell6 = getAdjacent(3);
                    newCell6.setAdjacent(1, newCell5);
                    newCell5.setAdjacent(4, newCell6);
                }
                }else{
                Cell newCell4 = new Cell(x,y+1);
                setAdjacent(1, newCell4);
                newCell4.setAdjacent(4, this);
            }
            }
            if((y == SizeY) && (x < SizeX)){
                Cell newCell7;
                if (y>1){
                newCell7 = getAdjacent(5).getAdjacent(1);          
            } else {
                newCell7 = new Cell(x+1,y);
            }
            setAdjacent(0, newCell7);
            newCell7.setAdjacent(3, this);
            newCell7.MakeMap(SizeX, SizeY);
            }
        }
    }
    
    /**
    * overrided toString method to allow for correct representation of the map/world
    * @return the string representation of the cell(its most important feature) 
    */
    @Override
    public String toString(){
        if (getRocky()){
            return "#";
        } else if (isAnt()){
            if (getAnt().getColour() == "red"){
                return "r";
            } else {
                return "b";
            }
        } else if (getFood() > 0){
            return "" + getFood();
        } else if (RedAntHill){
            return "+";
        } else if (BlackAntHill){
            return "-";
        } else {
            return ".";
        }
    }
}