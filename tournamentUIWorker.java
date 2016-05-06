/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.io.File;
import java.util.ArrayList;
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
public class tournamentUIWorker extends SwingWorker<String, String>{
    private World game_world;
    private ArrayList<Player> playersCompeting;
    private TournamentInterface interface1;
    private Tournament tournament1;
    private boolean[] competed;
    

    /**
     * constructor for the simulationUI_testing. This will allow the world to be updated
     * @param game_world the world that is going to be used for the tournament
     * @param players the list of players that are competing in the tournament
     * @param interface1 the tournament interface that called this background method
     */
    tournamentUIWorker( World game_world, ArrayList<Player> players, TournamentInterface interface1) {
        playersCompeting = players;
        this.game_world = game_world;
        this.interface1 = interface1;
        this.setProgress(1);
        competed = new boolean[players.size()];
        for (int i = 0; i < players.size(); i++){
            competed[i] = false;
        }
    }
    
    
    /**
     * used to run the tournament in the background method. This will control and run the other methods 
     * in this class
     * @return the string that represents the world.
     * @throws Exception 
     */
    @Override
    protected String doInBackground() throws Exception {
        tournament1 = new Tournament(playersCompeting, game_world);
        System.out.println("Game set up");
        tournament1.getPlayers();
        int i = 0;
        for (Player a : playersCompeting) {
            for (Player b : playersCompeting) {
                if (a != b && !competed[playersCompeting.indexOf(b)]) {
                    System.out.println("Match between " + a.getName() + " and " + b.getName() + " now starting");
                    Game game1 = new Game(game_world, a, b);
                    Game game2 = new Game(game_world, b, a); //Create a second match with players playing different colour
                    interface1.getLabel_competing().setText("<html>Players Competing:<br/>" + a.getName() + " Vs " + b.getName() + "<html>");
                    game1.run();
                    showWinner(game1, a,b);
                    System.out.println("Match between " + a.getName() + " and " + b.getName() + " now switching places");
                    interface1.getLabel_competing().setText("<html>Players Competing:<br/>" + b.getName() + " Vs " + a.getName() + "<html>");
                    game2.run();
                    showWinner(game1,b,a);
                    System.out.println("Match between " + a.getName() + " and " + b.getName() + " now finished");
                    
                }
            }
            competed[i] = true;
            i++;
        }
        
        interface1.getLabel_competing().setText("<html>TOURNAMENT FINISHED<br/><html>");
        interface1.getLabel_winnerOfMatch().setText("");
        interface1.updateWinnerLabel();
        return "";
    }
    
    /**
     * method to show which player won the previous match. This will show up on the UI
     * @param game_instance1 the game that is currently running
     * @param pa player a
     * @param pb player b
     */
    private void showWinner(Game game_instance1, Player pa, Player pb){
        String s = game_instance1.getWinner();
        if (s.equals("red")){
            pa.incrScore();
            pa.incrScore();
            interface1.getLabel_winnerOfMatch().setText(pa.getName()+" won");
        } else if (s.equals("black")){
            pb.incrScore();
            pb.incrScore();
            interface1.getLabel_winnerOfMatch().setText(pb.getName()+" won");
        }else {
            interface1.getLabel_winnerOfMatch().setText("Draw");
        }        
    }

    
}
