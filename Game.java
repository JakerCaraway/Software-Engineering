/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 *
 * @author jb555
 */
public class Game {

    private static int antNo = 254;
    private static int rounds = 300000;
    private static int antsOrRocksToKill = 5;

    private AntBrain RedAntBrain;
    private AntBrain BlackAntBrain;
    private Player RedPlayer;
    private Player BlackPlayer;
    private Ant[] Ants;
    private World GameWorld;

    public Game(AntBrain RedAntBrain, AntBrain BlackAntBrain, World GameWorld, Player RedPlayer, Player BlackPlayer) {
        this.RedAntBrain = RedAntBrain;
        this.BlackAntBrain = BlackAntBrain;
        this.RedPlayer = RedPlayer;
        this.BlackPlayer = BlackPlayer;
        Ants = new Ant[antNo];
        this.GameWorld = GameWorld;
    }

    public AntBrain getRedAntBrain() {
        return RedAntBrain;
    }

    public AntBrain getBlackAntBrain() {
        return BlackAntBrain;
    }

    public Player getRedPlayer() {
        return RedPlayer;
    }

    public Player getBlackPlayer() {
        return BlackPlayer;
    }

    public Ant[] getAnts() {
        return Ants;
    }

    public World getGameWorld() {
        return GameWorld;
    }

    public void setAnts(Ant[] Ants) {
        this.Ants = Ants;
    }

    public void checkDeadAnts() {
        boolean isDead;
        int deadCount;
        Cell currentCell;
        Ant adjAnt;
        for (int i = 0; i < Ants.length; i++) {
            isDead = false;
            deadCount = 0;
            if (Ants[i].getAlive()) {
                for (int j = 0; j < 6; j++) {
                    currentCell = Ants[i].getPosition().getAdjacent(j);
                    if (currentCell.isAnt()) {
                        adjAnt = currentCell.getAnt();
                        if (!(Ants[i].getColour().equals(adjAnt.getColour()))) {
                            deadCount++;
                        }
                    } else if (currentCell.getRocky()) {
                        deadCount++;
                    }
                }
            } else {
                isDead = true;
            }

            if (deadCount >= antsOrRocksToKill) {
                isDead = true;
            }

            if (isDead) {
                Ants[i].kill();
                if (Ants[i].getColour().equals("Red")){
                    BlackPlayer.incrScore();
                } else {
                    RedPlayer.incrScore();
                }
            }
        }
    }

    public void generateAnts() {
        int antCount = 0;
        Cell[] map;
        map = GameWorld.getMap();
        for (int i = 0; i < map.length; i++) {
            if (map[i].checkAntHill("black")) {
                Ant newAnt; 
                newAnt = new Ant("black", GameWorld, map[i]);
                map[i].setAnt(newAnt);
                Ants[antCount] = newAnt;
                antCount++;
            } else if (map[i].checkAntHill("red")) {
                Ant newAnt = new Ant("red", GameWorld, map[i]);
                map[i].setAnt(newAnt);
                Ants[antCount] = newAnt;
                antCount++;
            }
        }

    }

    public void run() {
        generateAnts();
        for (int i = 0; i < rounds; i++) {
            for (int j = 0; i < antNo; i++) {
                String currentColour;
                currentColour = Ants[j].getColour();
                if (currentColour.equals("red")){
                    
                    
                    
                } else {
                    
                    
                    
                }
                
                
                //Do the next command
            }
            checkDeadAnts();
        }
    }

}
