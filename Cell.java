/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant.game;

/**
 *
 * @author jb555
 */
public class Cell {
    private final int PheromoneNo = 6;
    
    private boolean Rocky;
    private int Food;
    private Ant Ant;
    private boolean[] Pheromones;
    private boolean RedAntHill;
    private boolean BlackAntHill;
    private Cell[] AdjacentCells;
    
    
    public Cell(Cell[] AdjacentCells){
        Rocky = false;
        Food = 0;
        Ant = null;
        Pheromones = new boolean[6];
        for(int i = 0; i<PheromoneNo; i++){
            Pheromones[i] = false;
        }
        RedAntHill = false;
        BlackAntHill = false;
        this.AdjacentCells = AdjacentCells;
    }
    
    public void setRocky(){
        Rocky = !Rocky;
    }
    
    public boolean getRocky(){
        return Rocky;
    }
    
    public void setFood(int amount){
        Food = amount;
    }
    
    public int getFood(){
        return Food;
    }
    
    public void setAnt(Ant ant){
        Ant = ant;
    }
    
    public Ant getAnt(){
        return Ant;
    }
    
    public void removeAnt(){
        Ant = null;
    }
    
    public void setPheramone(int Pheromone){
        Pheromones[Pheromone] = !Pheromones[Pheromone];
    }
    
    public boolean checkPheromone(int Pheromone){
        return Pheromones[Pheromone];
    }
    
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
    
    public boolean checkAntHill(String colour){
        switch (colour) {
            case "red":
                return RedAntHill;
            case "black":
                return BlackAntHill;
        }
        return false;
    }
    
    public Cell getAdjacent(int side){
        return AdjacentCells[side];
    }
}
