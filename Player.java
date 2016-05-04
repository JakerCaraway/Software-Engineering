/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 *
 * @author oab24
 */
public class Player {
    private AntBrain antBrainName;
    private int score;
    private String name;
    
    public Player(String name, AntBrain antBrainName){
        this.name = name;
        this.antBrainName = antBrainName;        
        score = 0;
    }

    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int newScore){
        score = newScore;
    }
    
    public void incrScore(){
        score++;
    }
    
    public void setAntBrain(AntBrain newAntBrain){
        antBrainName = newAntBrain;
    }
    
    public AntBrain getAntBrain(){
        return antBrainName;
    }


}