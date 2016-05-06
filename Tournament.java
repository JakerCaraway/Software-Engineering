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
  private HashMap<String, Integer> playerScores;
  private ArrayList<Player> players;
  private World world;

  /**
   * This constructor of Tournament is used to add a new player and a new ant world for tournament. 
   * @param player a new player
   * @param world a new ant world
   */
  public Tournament(ArrayList<Player> player, World world){  
       players = player;
       this.world = world;
   }
  
    /**
     * gets the ArrayList of the players
     * @return An ArrayList of the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Run a tournament between players.
     */
    public void runTournament(){
        
      for(Player a : players)
          for(Player b : players)
          {
             if(a != b)
             {
                 System.out.println("Match between " + a.getName()+ " and " + b.getName() +" now starting");
                 Game game1 = new Game(world,a,b);
                 Game game2 = new Game(world,b,a); 
                 game1.run();
                 System.out.println("Match between " + a.getName()+ " and " + b.getName() +" now switching places");
                 game2.run();
                 System.out.println("Match between " + a.getName()+ " and " + b.getName() +" now finished");
             }
          }
      checkWinner();
    }
    
    /**
     * gets the score of the player from Player class
     * @return the int score of the player
     */
     public int getScore(Player x){
      
      return x.getScore();
    }
     
     /**
     * Create a comparator to compare scores of the players
     * @return The HashMap of players name with scores in descending order
     */
    private static Map<String, Integer> sortPlayers(Map<String, Integer> unsortedPlayer){
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
      
    /**
     * Check if there is only one player with highest score.
     */
    public void checkWinner(){
      
      playerScores = new HashMap<>();
        for (Player x : players) 
         {
           playerScores.put(x.getName(), x.getScore());
         }
      
      Map<String, Integer> playerByOrder = sortPlayers(playerScores);

      int h = 0;
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
         runTournament(); 
        }
      if (h == 1) 
       {
          System.out.println("Winner: " + name + " !");
          for (Entry<String, Integer> entry: playerByOrder.entrySet())
          {
            System.out.println(playerByOrder);
          }
        }
    }
}
