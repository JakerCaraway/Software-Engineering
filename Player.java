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
    
    /**
     * creates a player class, which will store the player's name, score and the brain they have chosen
     * @param name the player's name
     * @param antBrainName the ant brain the player has chosen to use
     */
    public Player(String name, AntBrain antBrainName){
        this.name = name;
        this.antBrainName = antBrainName;        
        score = 0;
    }

    /**
     * gets the name of the player
     * @return String of the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * gets the score of the player
     * @return int score of the player
     */
    public int getScore() {
        return score;
    }
    
    /**
     * set score method. Should not be used to increase score normally. 
     * @param newScore the score to change the player's score to 
     */
    public void setScore(int newScore){
        score = newScore;
    }
    
    /**
     * method to increment the score of the player by 1 point
     */
    public void incrScore(){
        score++;
    }
    
    /**
     * sets the ant brain of the player. This is used to change the ant brain that a player started with
     * @param newAntBrain the new ant brain that is going to be used
     */
    public void setAntBrain(AntBrain newAntBrain){
        antBrainName = newAntBrain;
    }
    
    /**
     * gets the Ant brain that the player has stored
     * @return 
     */
    public AntBrain getAntBrain(){
        return antBrainName;
    }


}