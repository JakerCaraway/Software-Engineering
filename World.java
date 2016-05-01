/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mjc53
 */
class World {

    //private ArrayList<Cell> World;
    private Cell Recursion1;
    private int sizeX, sizeY;
    private File worldFile;
    char[][] format;
    
    public World(File worldFile){
        this.worldFile = worldFile;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        //World = new ArrayList<>();
        CreateNewWorld();
    }
    
    public void CreateNewWorld()
    {        
        ReadWorld(worldFile);
        Recursion1 = new Cell(1,1);
        Recursion1.MakeMap(sizeX,sizeY);
        formatWorld(format);
    }
    
    public Cell getWorld(){
        return Recursion1;
    }
    
    static String getOtherColour(String colour) {
        if (colour == "red"){
            return "black";
        }else if (colour == "black") {
            return "red";
        } else return "none";
    }

    //not certain the numbers here work quite right have to test
    private void formatWorld(char[][] designs) {
        Cell currentCell = Recursion1;
        Cell nextCell = null;
        for (int y = 0; y<designs.length;y++){
            if (y < sizeY-1){                    
            if (y%2 !=0){
                nextCell = currentCell.getAdjacent(1);
            } else{
                nextCell = currentCell.getAdjacent(2);
            }
            }
            for (int x = 0; x<designs.length;x++){
                if (designs[x][y] == '#'){
                    currentCell.setRocky();
                }else if (designs[x][y] == '+'){
                    currentCell.setAntHill("Red");
                }else if (designs[x][y] == '-'){
                    currentCell.setAntHill("Black");
                }else if (('0' <= designs[x][y]) && (designs[x][y] >= '9')){
                    currentCell.setFood(Character.getNumericValue(designs[x][y]));
                if (x != designs.length -1)
                    currentCell = currentCell.getAdjacent(3);
            }
            
            if (y < sizeY-1){
                currentCell = nextCell;
            }
            }
        }
    }

    private void ReadWorld(File worldFile) {
        try {           
            FileReader fileReader;
            BufferedReader br = new BufferedReader( fileReader = new FileReader(worldFile)); 
            String line = br.readLine();
            String overall_line = "";
            while (line != null) { // null signals end of stream
                // process line
                if (line != null){
                    overall_line = overall_line + line + "\n";
                }
                line = br.readLine();                
            }
            br.close();             
            
            GetFormat(overall_line);
            /*if (!checkBrain(overall_line)) {
                System.out.println("Ant brain not compatible. REJECTED");
            }*/
        } catch (IOException exception) {
            System.out.println("File incorrect. Can't load data");
        }
    }
    
    public String GetCurrentWorld(){
        String output = "";
        Cell currentCell = Recursion1;
        Cell nextCell = null;
        for (int y = 0; y < sizeY; y++){
            output += "/n";
            if (y < sizeY-1){                    
            if (y%2 !=0){
                nextCell = currentCell.getAdjacent(1);
            } else{
                nextCell = currentCell.getAdjacent(2);
                output += " ";
            }
            }    
            for(int x = 0; x< sizeX; x++){
                output += currentCell.toString() + " ";
            }
            if (y < sizeY-1){
                currentCell = nextCell;
            }
        }
    }

    private void GetFormat(String overall_line) {
        //String[] splitFile =
        String whiteSpaceRemoved = overall_line.replaceAll("\\s+", "");
        String[] splitLines = whiteSpaceRemoved.split("\n");
        if (splitLines.length>3){
        sizeX = Integer.parseInt(splitLines[0]);
        sizeY = Integer.parseInt(splitLines[1]);
        format = new char[sizeX][sizeY];
        for (int i= 3; i<splitLines.length; i++){
            for (int j = 0; j<splitLines[i].length();j++){
                format[j][i] = splitLines[i].charAt(j);
                }
            }
        }
    }
}
