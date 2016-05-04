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
import java.util.Random;

/**
 *
 * @author mjc53
 */
class World {

    private Cell Recursion1;
    public int sizeX, sizeY;
    private File worldFile;
    char[][] format;
    String[] splitLines;
    
    public World(File worldFile){
        this.worldFile = worldFile;
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
    
    public String getOtherColour(String colour) {
        if (colour == "red"){
            return "black";
        }else if (colour == "black") {
            return "red";
        } else return "none";
    }
    
    public String getRandomWorld(int x, int y){
        String output;
        output = x + "\n";
        output += y + "\n";
        Random rn = new Random();
        
        //each food blob run one at a time assign to a set of clean co-ordinate 10 in size
        //assign to each a box and spawn at square points and remove a number once used
        //then assign anthills a 2 by 2 cell
        //cells numbered 0 - 14
        
        int redAntSector = rn.nextInt(24);
        int blackAntSector = rn.nextInt(24);
        
        boolean[] usedSectors = new boolean[24];
        int[] foodSector = new int[11];
        
        for (int i = 0; i<24;i++)
            usedSectors[i] = true;
        
        usedSectors[redAntSector] = false;
        
        while (usedSectors[blackAntSector] == false){
            blackAntSector = rn.nextInt(24);
        }
        usedSectors[blackAntSector] = false;
        
        for (int i = 0; i<11; i++){
            foodSector[i] = rn.nextInt(24);
            while (usedSectors[foodSector[i]] == false){
                foodSector[i] = rn.nextInt(24);
            }
            usedSectors[foodSector[i]] = false;
        }
        
        int redAnt_hill_x = (redAntSector%5) * 30;
        int redAnt_hill_y = (redAntSector/5) *30;
        
        int blackAnt_hill_x = (blackAntSector%5) * 30;
        int blackAnt_hill_y = (blackAntSector/5) * 30;
        
        int[] foodSectors_x = new int[11];
        int[] foodSectors_y = new int[11];
        
        for (int i = 0; i<11; i++){
            foodSectors_x[i] = (foodSector[i]%5) * 30;
            foodSectors_y[i] = (foodSector[i]/5) * 30; 
        }
        
        
        /*
               int blackAnt_hill_x = rn.nextInt(x-15);
        int blackAnt_hill_y = rn.nextInt(y-15);
        
        int[] foodBlobs_x = new int[11];
        int[] foodBlobs_y = new int[11];
        for (int i = 0; i<11; i++){
            foodBlobs_x[i] = rn.nextInt(x-7) + 1;
            foodBlobs_y[i] = rn.nextInt(x-7) + 1;
            while (((foodBlobs_x[i] >= blackAnt_hill_x-8) && (foodBlobs_x[i] <= blackAnt_hill_x + 14 ))  ||
                    ((foodBlobs_y[i] >= blackAnt_hill_x-8) && (foodBlobs_x[i] <= blackAnt_hill_y + 14 )) ||
                    ((foodBlobs_x[i] >= redAnt_hill_x-8) && (foodBlobs_x[i] <= redAnt_hill_x + 14 )) ||
                    ((foodBlobs_y[i] >= redAnt_hill_x-8) && (foodBlobs_x[i] <= redAnt_hill_y + 14 ))
                    ) 
                    
                foodBlobs_x[i] = rn.nextInt(x-7) + 1;
                foodBlobs_y[i] = rn.nextInt(x-7) + 1;
            }           
        
        while ((blackAnt_hill_x >= redAnt_hill_x-15) && (blackAnt_hill_x <= redAnt_hill_x + 14))
             blackAnt_hill_x = rn.nextInt(x-15);
        while ((blackAnt_hill_y >= redAnt_hill_y-15) && (blackAnt_hill_y <= redAnt_hill_y + 14))
             blackAnt_hill_y = rn.nextInt(y-15);
        */
        char[][] outputArray = new char[y][x];
        
        for(int i = 0; i<y; i++){
            for(int j = 0; j<x; j++){
                if ((i == 0) || (i == y-1) || (j == 0)||(j == x-1)){
                    outputArray[i][j] = '#';
                }
                if (((i == redAnt_hill_y + 1)||(i == redAnt_hill_y + 13)) && (j >= redAnt_hill_x + 4) && (j <= redAnt_hill_x + 10)){
                    outputArray[i][j] = '+';
                }
                if (((i == redAnt_hill_y + 2)||(i == redAnt_hill_y + 12)) && (j >= redAnt_hill_x + 4) && (j <= redAnt_hill_x + 11)){
                    outputArray[i][j] = '+';
                }
                if (((i == redAnt_hill_y + 3)||(i == redAnt_hill_y + 11)) && (j >= redAnt_hill_x + 3) && (j <= redAnt_hill_x + 11)){
                    outputArray[i][j] = '+';
                }
                if (((i == redAnt_hill_y + 4)||(i == redAnt_hill_y + 10)) && (j >= redAnt_hill_x + 3) && (j <= redAnt_hill_x + 12)){
                    outputArray[i][j] = '+';
                }
                if (((i == redAnt_hill_y + 5)||(i == redAnt_hill_y + 9)) && (j >= redAnt_hill_x + 2) && (j <= redAnt_hill_x + 12)){
                    outputArray[i][j] = '+';
                }
                if (((i == redAnt_hill_y + 6)||(i == redAnt_hill_y + 8)) && (j >= redAnt_hill_x + 2) && (j <= redAnt_hill_x + 13)){
                    outputArray[i][j] = '+';
                }
                if ((i == redAnt_hill_y + 7) && ((j >= redAnt_hill_x + 1) && (j <= redAnt_hill_x + 13))){
                    outputArray[i][j] = '+';
                }
                if ((i == 0) || (i == y-1) || (j == 0)||(j == x-1)){
                    outputArray[i][j] = '#';
                }
                
                if (((i == blackAnt_hill_y + 1)||(i == blackAnt_hill_y + 13)) && (j >= blackAnt_hill_x + 4) && (j <= blackAnt_hill_x + 10)){
                    outputArray[i][j] = '-';
                }
                if (((i == blackAnt_hill_y + 2)||(i == blackAnt_hill_y + 12)) && (j >= blackAnt_hill_x + 4) && (j <= blackAnt_hill_x + 11)){
                    outputArray[i][j] = '-';
                }
                if (((i == blackAnt_hill_y + 3)||(i == blackAnt_hill_y + 11)) && (j >= blackAnt_hill_x + 3) && (j <= blackAnt_hill_x + 11)){
                    outputArray[i][j] = '-';
                }
                if (((i == blackAnt_hill_y + 4)||(i == blackAnt_hill_y + 10)) && (j >= blackAnt_hill_x + 3) && (j <= blackAnt_hill_x + 12)){
                    outputArray[i][j] = '-';
                }
                if (((i == blackAnt_hill_y + 5)||(i == blackAnt_hill_y + 9)) && (j >= blackAnt_hill_x + 2) && (j <= blackAnt_hill_x + 12)){
                    outputArray[i][j] = '-';
                }
                if (((i == blackAnt_hill_y + 6)||(i == blackAnt_hill_y + 8)) && (j >= blackAnt_hill_x + 2) && (j <= blackAnt_hill_x + 13)){
                    outputArray[i][j] = '-';
                }
                if ((i == blackAnt_hill_y + 7) && ((j >= blackAnt_hill_x + 1) && (j <= blackAnt_hill_x + 13))){
                    outputArray[i][j] = '-';
                }
                for (int g = 0; g< foodSector.length; g++){
                        if (((i == foodSectors_y[g]+1)||(i == foodSectors_y[g]+2)) && ((j >= foodSectors_x[g]+1) && (j <= foodSectors_x[g]+5))){
                            outputArray[i][j] = '5';
                        }
                        if (((i == foodSectors_y[g]+3)||(i == foodSectors_y[g]+4)) && ((j >= foodSectors_x[g]+2) && (j <= foodSectors_x[g]+6))){
                            outputArray[i][j] = '5';
                        }
                        if ((i == foodSectors_y[g]+5) && ((j >= foodSectors_x[g]+3) && (j <= foodSectors_x[g]+7))){
                            outputArray[i][j] = '5';
                        }
                    }
                
                if (outputArray[i][j] == '\u0000'){
                    outputArray[i][j] = '.';
                }   
            }
            }
        int rock_x;
        int rock_y;
        int rockCount = 14;
        while(rockCount != 0){
            rock_x = rn.nextInt(x-2) + 1;
            rock_y = rn.nextInt(y-2) + 1;
            if (outputArray[rock_x][rock_y] == '.'){
                outputArray[rock_x][rock_y] = '#';
                rockCount--;
            }
        }
        for (int i = 0; i <y; i++){
            for(int j = 0; j<x; j++){
                //System.out.println("y:" + i);
                //System.out.println("x:" + j);                
                output += outputArray[i][j] + " ";
            } 
            output += '\n';
        }
        
        return output;
    }
    
    //not certain the numbers here work quite right have to test
    private void formatWorld(char[][] designs) {
        Cell currentCell = Recursion1;
        Cell nextCell = null;
        for (int y = 0; y<designs.length;y++){
            if (y < sizeY-1){                    
            if (y%2 ==0){
                nextCell = currentCell.getAdjacent(1);
            } else{
                nextCell = currentCell.getAdjacent(2);
            }
            }
            
            for (int x = 0; x<designs.length;x++){
                if (designs[x][y] == '#'){
                    currentCell.setRocky();
                }else if (designs[x][y] == '+'){
                    currentCell.setAntHill("red");
                }else if (designs[x][y] == '-'){
                    currentCell.setAntHill("black");
                }else if (Character.isDigit(designs[x][y])){
                    currentCell.setFood(Character.getNumericValue(designs[x][y]));
                    }
                if (x != designs.length -1){
                    currentCell = currentCell.getAdjacent(0);
                }
            }
            
            if (y < sizeY-1){
                currentCell = nextCell;
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
        } catch (IOException exception) {
            System.out.println("File incorrect. Can't load data");
        }
    }
    
    public String GetCurrentWorld(){
        String output = "";
        Cell currentCell = Recursion1;
        Cell nextCell = null;
        for (int y = 0; y < sizeY; y++){
            output += "\n";
            if (y < sizeY-1){                    
            if (y%2 ==0){
                nextCell = currentCell.getAdjacent(1);
            } else{
                nextCell = currentCell.getAdjacent(2);
                output += " ";
            }
            }    
            for(int x = 0; x< sizeX; x++){
                output += currentCell.toString() + " ";
                currentCell = currentCell.getAdjacent(0);
            }
            if (y < sizeY-1){
                currentCell = nextCell;
            }
        }
        return output;
    }

    private void GetFormat(String overall_line) {
        splitLines = overall_line.split("\n");
        for(int i = 0; i<splitLines.length; i++){
            splitLines[i] = splitLines[i].replaceAll("\\s+", "");
        }
        if (splitLines.length>3){
        sizeX = Integer.parseInt(splitLines[0]);
        sizeY = Integer.parseInt(splitLines[1]);
        format = new char[sizeX][sizeY];
        for (int i= 2; i<splitLines.length; i++){
            for (int j = 0; j<splitLines[i].length();j++){
                    format[j][i-2] = splitLines[i].charAt(j);
                }
            }
        }
    }
}
