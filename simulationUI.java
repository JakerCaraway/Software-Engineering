/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingWorker;

/**
 * A class that extends SwingWorker. This is used to allow the game simulation to be run on
 * a background thread.
 * @author oab24
 */
public class simulationUI extends SwingWorker<String, String>{
    private File worker_game_world_file;
    private Game worker_game_instance;
    private int worker_speedSlider;
    private boolean randomWorldsChecked;
    private Player p1;
    private Player p2;
    private GameInterface interface1;
   
    /**
     * used to get the speeds that the speed slider represents. This is because the actual values correspond to labels which are tricky to obtain
     * @return the speed that the slider variable represents.
     */
    private int getActualSpeed(int speed){
            switch (speed){
                    case 1: 
                        return 1;
                    case 2:
                        return 10;
                    case 3:
                        return 50;
                    case 4:
                        return 100;
                    case 5:
                        return 1000;
                    case 6:
                        return 10000;
                    default:
                        return 1;
            }
        }
    

    /**
     * Used to create a new instance of this worker. 
     * @param speed the speed that the game should run at, determined by the sliders
     * @param game_world_file the file the world is stored in. Can be null if the world will be random
     * @param randomWorldsChecked true if the box for random worlds is checked, false if not
     * @param p1 player 1
     * @param p2 player 2
     * @param interface1 the interface that is being passed in to allow for calls back
     */
    simulationUI(int speed, File game_world_file, boolean randomWorldsChecked, Player p1, Player p2, GameInterface interface1) {
        worker_game_world_file = game_world_file;
        worker_speedSlider = speed;
        this.randomWorldsChecked = randomWorldsChecked;
        this.p1 = p1;
        this.p2 = p2;
        this.interface1 = interface1;
        this.setProgress(1);
    }
    
    /**
     * runs the method to show the map. This will then send update signals to the UI to update. Also formats the 
     * world to the correct way for the UI to use
     * @return the string that makes up the world
     */
    private String showMap(){
        String s = worker_game_instance.getGameWorld().GetCurrentWorld();
        s = "<html>"+s+"<html>";
        String[] sA = s.split("\n");
        String s2 = "";
        for (int i =0; i < sA.length; i++){
            if (i%2== 0)                    
                s2 = s2 + sA[i] + "<br/>";
            else
                s2 = s2 + " " + sA[i] + "<br/>";
        }
        interface1.getLabel_worldDisplay().setText(s2);        
        return s2;
    }
    
    /**
     * sets up the game. This will initialise the world (randomising if needed) and the game class
     */
    private void setUpGame(){
        String s2 = null;
        World w= null;
        if (worker_game_world_file == null && randomWorldsChecked){
            w = new World(null);
            w.getRandomWorld(150, 150);
        } else if (worker_game_world_file != null)
            w = new World(worker_game_world_file);
        
        if (w != null){
            // create game
            worker_game_instance = new Game(w, p1, p2);
            //show the map
            showMap();
            // run the game several times
        } 
    }
    
    /**
     * used to get the game that is being used
     * @return the game that is running the simulation
     */
    public Game getSimuWorkerGame(){
        return worker_game_instance;
    }

    /**
     * Runs the simulation rounds. This will also update the label on the UI about what partition is running.
     * @param simulationUISpeed The speed the game is running at: this represents how large the partitions are for the 300,000 rounds
     */
    private void runSimulationSet(int simulationUISpeed){
        for (int i = 0; i < (300000/simulationUISpeed);i++ ){
            interface1.getLabel_roundNumber().setText("Now running partition "+ Integer.toString(i+1)+ " of "+ Integer.toString(300000/simulationUISpeed));
            worker_game_instance.runGame(simulationUISpeed);       
            boolean b = javax.swing.SwingUtilities.isEventDispatchThread();            
            System.out.println("The game has run round " + Integer.toString(i)+ " of " + Integer.toString(300000/simulationUISpeed));
            showMap();        
        } 
    }
    
    /**
     * used to run the game in the background method. This will control and run the other methods 
     * in this class
     * @return the string that represents the world.
     * @throws Exception 
     */
    @Override
    protected String doInBackground() throws Exception {
        this.setProgress(0);
        setUpGame();        
        System.out.println("Game set up");
        this.setProgress(1);        
        int simulationUISpeed= getActualSpeed(worker_speedSlider); 
        //this.setProgress(simulationUISpeed);
        runSimulationSet(simulationUISpeed);
        showMap();
        interface1.showWinner();
        return showMap();
    }    

    
}
