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
  private HashMap<String, Integer> scores;
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
  

  public int getScore(Player x)
  {
    return x.getScore();
  }
  

  public static getRanking()
  {
      scores = new HashMap<String, Integer>();
      for (int i = 0; i < players.size(); i++ )
      {
         Player x = players.get(i);
         scores.put(x.getPlayerName(), getScores(x));
      }
      
      Map<String, Integer> playerByOrder = sortPlayer(scores);
  }
 
 private static Map<String, Integer> sortPlayer(Map<String, Integer> unsortedPlayer)
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
      
      for (scores entry : list)
      {
       playerByOrder.put(entry.getKey(), entry.getValue());
       
      }
      return playerByOrder;
  }
      
  
  public void displayRanking()
  {
      for (playerByOrder p : p.entrySet())
        {
            System.out.println("Player : " + entry.getKey() + " Score : "+ entry.getValue());
        }
  }
  
  
 
