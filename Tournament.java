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
import java.util.Iterator;


public class Tournament {
 
 
  private Player player;
  private HashMap<String, Integer> playerScores;//Store player's name and scores
  private ArrayList<Player> players;
  private Game newGame;
  private World world;

  


//Create a tournament
  public Tournament(ArrayList<Player> player, World world)
  {  
       for(Player p : player )
       {
         this.world = world;
       }
       newGame = new Game();
  }
  
  public void startMatch(World w, Player 1, Player 2 )
  {
     newGame = new Game(w,1,2); 
     newGame.Run();
  }

 // Run a tounament
  public void runTournament()
  {
      for(Player a : players)
          for(Player b : players)
          {
             if(a != b)
             {
             startMatch(world,a,b);
             startMatch(world,b,a); //Create a second match with players playing different colour
             }
          }
      checkWinner();
                 
  }
  
//Get player's scores
  public int getScore(Player x)
  {
    return x.getScore();
  }
  
 
 //Create a comparator to compare players' scores
 private static Map<String, Integer> sortPlayers(Map<String, Integer> unsortedPlayer)
  {
      List<Entry<String, Integer>> list = new LinkedList<>(unsortedPlayer.entrySet());
      Collections.sort(list, new Comparator<Entry<String, Integer>>() {

          public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) 
         {
          return e2.getValue().compareTo(e1.getValue());
         }
      });
     
   
      Map<String, Integer> playerByOrder = new LinkedHashMap<>();
      
      for(Entry<String, Integer> entry : list)
      {
       playerByOrder.put(entry.getKey(), entry.getValue());
       
      }
      return playerByOrder;
  }
      
 //Check if there is only one winner
  public void checkWinner()
  {
    playerScores = new HashMap<>();
    for (Player x : players) 
    {
      playerScores.put(x.getName(), x.getScore());
    }
      
    Map<String, Integer> playerByOrder = sortPlayers(playerScores);

    int h = 0;//the number of player with highest scores
    String name = "";
    int highestScore = (Collections.max(playerScores.values())); 
    for (Entry<String, Integer> entry : playerScores.entrySet())
     {      
       if (entry.getValue() == highestScore)
         {
            h++;
            name = entry.getKey();
         } 
      }
   if (h != 1)
    {
      runTournament(); // No clear Winner, Run a new tournament
    }
   if (h == 1) // there is only one player with highest score
    {
        System.out.println("Winner: " + name + " !");
        for (Entry<String, Integer> entry: playerByOrder.entrySet())
        {
            System.out.println(playerByOrder);
        }
    }
  }
}
