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
    private String[] RedFSM;
    private String[] BlackFSM;
    private RandomInt NumberGen;
    private int RoundCount;

    public Game(World GameWorld, Player RedPlayer, Player BlackPlayer) {
        this.RedPlayer = RedPlayer;
        this.BlackPlayer = BlackPlayer;
        RedAntBrain = this.RedPlayer.getAntBrain();
        BlackAntBrain = this.BlackPlayer.getAntBrain();
        Ants = new Ant[antNo];
        this.GameWorld = GameWorld;
        RedFSM = RedAntBrain.getFiniteStateMachine();
        BlackFSM = BlackAntBrain.getFiniteStateMachine();
        RoundCount = 0;
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

    public int getRoundCount(){
        return RoundCount;
    }

    public String getWinner(){
        if (RedPlayer.getScore() > BlackPlayer.getScore()){
            return "red";
        } else if (BlackPlayer.getScore() > RedPlayer.getScore()){
            return "black";
        } else {
            return "draw";
        }
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
                Ants[i].getPosition().setFood(3);
                Ants[i].getPosition().removeAnt();
                Ants[i].kill();
            }
        }
    }

    public void generateAnts() {
        int antCount = 0;
        Cell[] map;
        map = GameWorld.getWorld();
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

    public void tallyScore() {
        Cell[] map;
        map = GameWorld.getWorld();
        int redFood = 0;
        int blackFood = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i].checkAntHill("black")) {
                blackFood += map[i].getFood();
            } else if (map[i].checkAntHill("red")) {
                redFood += map[i].getFood();
            }
        }
        RedPlayer.setScore(redFood);
        BlackPlayer.setScore(blackFood);
    }
    
    public void run() {
        String currentColour;
        String[] currentFSM;
        int currentState;
        Ant currentAnt;
        String[] currentCommands;
        String command;
        int direction;
        boolean found;
        String foeColour;

        generateAnts();
        for (int i = 0; i < rounds; i++) {
            for (int j = 0; i < antNo; i++) {
                currentAnt = Ants[j];
                currentAnt.decrRest();
                if (currentAnt.getRest() == 0 && currentAnt.getAlive()) {
                    currentColour = currentAnt.getColour();
                    if (currentColour.equals("red")) {
                        foeColour = "black";
                        currentFSM = RedFSM;
                    } else {
                        foeColour = "red";
                        currentFSM = BlackFSM;
                    }
                    currentState = currentAnt.getState();
                    currentCommands = currentFSM[currentState].split("/s");
                    command = currentCommands[0];

                    switch (command) {
                        case "move": //(state, state)
                            //check space in front for objects
                            //if clear, move and change state to first one
                            //else state equal to 2nd one
                            //set rest on ant
                            direction = currentAnt.getDirection();
                            if (!currentAnt.getPosition().getAdjacent(direction).isAnt() && !currentAnt.getPosition().getAdjacent(direction).getRocky()){
                                currentAnt.getPosition().getAdjacent(direction).setAnt(currentAnt); // remove from cell
                                currentAnt.getPosition().removeAnt();
                                currentAnt.setPosition(currentAnt.getPosition().getAdjacent(direction));
                                currentAnt.setRest();
                                currentAnt.setState(Integer.parseInt(currentCommands[1]));
                            } else {
                                currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            }
                            
                            break;
                        case "sense": //(direction, state, state, condition)
                            //load cell in the direction
                            //switch case for conditions
                            //check using cell class to see if condition is correct
                            //state 1 if true
                            //else state 2
                            found = false;
                            direction = currentAnt.getDirection();
                            Cell sensedCell = currentAnt.getPosition().getAdjacent(direction);
                            String condition = currentCommands[3];
                            switch (condition){
                                case "friend":
                                    if (currentAnt.getPosition().getAdjacent(direction).isAnt() && currentAnt.getColour().equals(currentAnt.getPosition().getAdjacent(direction).getAnt().getColour())){
                                        found = true;
                                    }
                                    break;
                                case "foe":
                                    if (currentAnt.getPosition().getAdjacent(direction).isAnt() && !currentAnt.getColour().equals(currentAnt.getPosition().getAdjacent(direction).getAnt().getColour())){
                                        found = true;
                                    }
                                    break;
                                case "friendwithfood":
                                    if (currentAnt.getPosition().getAdjacent(direction).isAnt() && (currentAnt.getColour().equals(currentAnt.getPosition().getAdjacent(direction).getAnt().getColour())) && currentAnt.getPosition().getAdjacent(direction).getAnt().getFood()){
                                        found = true;
                                    }
                                    break;
                                case "foewithfood":
                                    if (currentAnt.getPosition().getAdjacent(direction).isAnt() && (!currentAnt.getColour().equals(currentAnt.getPosition().getAdjacent(direction).getAnt().getColour())) && currentAnt.getPosition().getAdjacent(direction).getAnt().getFood()){
                                        found = true;
                                    }
                                    break;
                                case "food":
                                    if (currentAnt.getPosition().getAdjacent(direction).getFood() > 0){
                                        found = true;
                                    }
                                    break;
                                case "rock":
                                    if (currentAnt.getPosition().getAdjacent(direction).getRocky()){
                                        found = true;
                                    }
                                    break;
                                case "marker":
                                    if (currentAnt.getPosition().getAdjacent(direction).checkPheromone(Integer.parseInt(currentCommands[4]), currentColour)){
                                        found = true;
                                    }
                                    break;
                                case "foemarker":
                                    if (currentAnt.getPosition().getAdjacent(direction).checkPheromone(Integer.parseInt(currentCommands[4]), foeColour)){
                                        found = true;
                                    }
                                    break;
                                case "home":
                                    if (currentAnt.getPosition().getAdjacent(direction).checkAntHill(currentColour)){
                                        found = true;
                                    }
                                    break;
                                case "foehome":
                                    if (currentAnt.getPosition().getAdjacent(direction).checkAntHill(foeColour)){
                                        found = true;
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if (found){
                                currentAnt.setState(Integer.parseInt(currentCommands[1]));
                            } else {
                                currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            }
                            break;
                        case "mark": //(marker, state)
                            //change current cell marker inputted to true
                            //state = state 1
                            currentAnt.getPosition().setPheramone(Integer.parseInt(currentCommands[1]), currentColour);
                            currentAnt.setState(Integer.parseInt(currentCommands[2]));                            
                            break;
                        case "unmark": //(marker,state)
                            //change current cell marker inputted to false
                            //state = state 1
                            currentAnt.getPosition().setPheramone(Integer.parseInt(currentCommands[1]), currentColour);
                            currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            break;
                        case "pickup": //(state, state)
                            //check for food
                            //if true pick up and go to state 1
                            //if false go to state 2
                            if (currentAnt.getPosition().getFood() > 0){
                                currentAnt.PickUpFood();
                                currentAnt.setState(Integer.parseInt(currentCommands[1]));
                            } else {
                                currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            }
                            break;
                        case "drop": //(state)
                            //drop food held
                            //state = state 1
                            currentAnt.PutDownFood();
                            currentAnt.setState(Integer.parseInt(currentCommands[1]));
                            break;
                        case "turn": //(left or right, state)
                            //check if left or right is inputted
                            //direction + 1 if right
                            //direction -1 if left
                            //loop if < 0 or > 5
                            String lr = currentCommands[1];
                            switch (lr){
                                case "left":
                                    currentAnt.setDirectionL();
                                    break;
                                case "right":
                                    currentAnt.setDirectionR();
                                    break;
                                default:
                                    break;
                            }
                            currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            break;
                        case "flip": //(integer, state, state)
                            //get a random number 0 to integer inputted
                            //if 0 then state = state 1
                            //else state = state 2
                            int rand = NumberGen.generateRand(Integer.parseInt(currentCommands[1]));
                            if (rand == 0){
                                currentAnt.setState(Integer.parseInt(currentCommands[2]));
                            } else { 
                                currentAnt.setState(Integer.parseInt(currentCommands[3]));
                            }
                            break;
                        default:
                            break;
                    }
                }
                Ants[j] = currentAnt;
            }
            checkDeadAnts();
            RoundCount++;
        }
        tallyScore();
    }

}

//split("\s") used to split strings into words in a new string array 
