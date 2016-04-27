/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author wc87
 */
public class Tournament {
 
 
  private Player player;
  private HashMap<String, Integer> scores;
  private ArrayList<Player> players;
  
  private Game newGame;
  


//Create a tournament
  public Tournament(ArrayList<Player> players, ArrayList<String> playerName, ArrayList<AntBrain> brain)
  {  
   
   
   this();
   setPlayers(players, playerName, brain);
     
  }
  

  public void startMactch(Player 1, Player 2)
  {
    newGame = new Game();
    newGame.Run(1,2);

  }
  

  // Run a tounament
  public void runTournament()
  {
       for (int i = 1, i < players.size(); i++)
         {
           for (int j = 0, j < i, j++)
            {
              Player player1 = players.get(i);
              Player player2 = players.get(j); 
         
              startMatch(player1, player2);
         
            }
         }
         
        secondMatch();
        displayRanking();
          
  }
  
    //Create a second match with player playing diffenrent colour
  public void secondMatch()
  {
      for (int i = 1, i < players.size(); i++)
         {
           for (int j = 0, j < i, j++)
            {
              Player player1 = players.get(i);
              Player player2 = players.get(j); 
         
              startMatch(player2, player1);
         
            }
         }
      
  }

  public void setPlayers(ArrayList<Player> player, ArrayList<AntBrain> brain, ArayList<String> name)
  {
      this.player = player;
      for (int i = 0; i < player.size();i++)
      {
        player.setAntBrain(brain);
        player.setPlayerName(name);
        player.setScore(0);
      }
     
  }
  
  public HashMap<String, Integer> getScores()
  {
    return scores;
  }
          
  public int getScore(Player x)
  {
    return scores.get(x.getPlayerName());
  }
  

  
  public void getRanking()
  {
      
  }
  
  public void displayRanking()
  {
    
  }
  
  
 
