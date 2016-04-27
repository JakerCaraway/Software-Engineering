/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run.microbial;

/**
 *
 * @author oab24
 */
public class Player {
    private AntBrain antBrainName;
    private int score;
    
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
