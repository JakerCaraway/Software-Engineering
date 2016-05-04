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
    
    public boolean isAnt(){
        return !(Ant == null);
    }
    
    public void setPheramone(int Pheromone,String Colour){
        if (Colour.equals("black"))
                Pheromone += 6;
        Pheromones[Pheromone] = !Pheromones[Pheromone];
    }
    
    public boolean checkPheromone(int Pheromone,String Colour){
        if (Colour.equals("black"))
                Pheromone += 6;
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
    
    public void setAdjacent(int side, Cell adj){
        AdjacentCells[side] = adj;
    }
    
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