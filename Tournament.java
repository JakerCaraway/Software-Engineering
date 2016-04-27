/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /**
 *
 * @author wc87
 */
package AntGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Tournament {
 
 
  private Player player;
  private HashMap<String, Integer> playerScores;//Store player's name and scores
  private ArrayList<Player> players;
  private Game newGame;
  


//Create a tournament
  public Tournament(ArrayList<Player> players, ArrayList<String> playerName, ArrayList<AntBrain> brain)
  {  
   
   
    this();
    setPlayers(players, playerName, brain);
     
  }
  
  
//Start a match with one player playing red, the other playing black
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
              startMatch(player2, player1); //Create a second match with players playing different colour
         
            }
         }
        
        getRanking(); // get ranking and to see if threre is a winner
        displayRanking();
          
  }

// Create a player, set player name and Ant Brain
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
  
//Get player's scores
  public int getScore(Player x)
  {
    return x.getScore();
  }
  
//Get the ranking of the tournament and to find if there is a clear winner
//If not, run a new tournament
  public static getRanking()
  {
      playerScores = new HashMap<String, Integer>();
      for (int i = 0; i < players.size(); i++ )
      {
         Player x = players.get(i);
         playerScores.put(x.getPlayerName(), getScores(x));
      }
      
      Map<String, Integer> playerByOrder = sortPlayers(playerScores);
      //Here I need to do more coding to find if there is a clear winner
      //
  }
 
 //Create a comparator to compare players' scores
 private static Map<String, Integer> sortPlayers(Map<String, Integer> unsortedPlayer)
  {
      List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortedPlayer.entrySet());
      Collections.sort(playerByOrder, new Comparator<Entry<String, Integer>>())
      {
        public int compare(Entry<String, Integer> e1, Entry<String,Integer> e2)
        {
          return e2.getValue.compareTo(e1.getValue())
        }
      }
      Map<String, Integer> playerByOrder = new LinkedHashMap<String, Integer>();
      
      for (Entry<String, Integer> entry : list)
      {
       playerByOrder.put(entry.getKey(), entry.getValue());
       
      }
      return playerByOrder;
  }
      
  //Display the final ranking and winner
  public void displayRanking()
  {
      for (Entry<String, Integer> entry: playerByOrder.entrySet())
        {
            System.out.println(sortPlayers(playerScores)));
        }
  }
  
  
 
