/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 * This class contains the code to run the game using all of the other classes.
 * The purpose of this class is to initialise the world and then run both ant 
 * brains on each teams ants and move them for the inputted amount of turns.
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
    public Ant[] Ants;
    private World GameWorld;
    private String[] RedFSM;
    private String[] BlackFSM;
    private RandomInt NumberGen;
    private int RoundCount;
    private int BlackScore;
    private int RedScore;
    
    /**
     * This method initialises the values stored inside the Game class. This includes
     * setting both players to the inputted players, setting both ant brains to the 
     * brains stored in each player class, initialising the ant array, setting the world 
     * to the inputted world, settinf the finite state machines to the ones contained
     * in the ant brains, initialising the round count to 0 and initialising the random 
     * number generator.
     * @param GameWorld The world class that stores the map needed for the current game
     * @param RedPlayer The player class for the red player of the current game
     * @param BlackPlayerThe player class for the black player of the current game
     */
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
        NumberGen = new RandomInt();
    }

    /**
     * This method returns the ant brain stored in the redAntBrain variable
     * @return the ant brain currently stored in redAntBrain
     */
    public AntBrain getRedAntBrain() {
        return RedAntBrain;
    }
    
    /**
     * This method returns the ant brain stored in the blackAntBrain variable
     * @return the ant brain currently stored in blackAntBrain
     */
    public AntBrain getBlackAntBrain() {
        return BlackAntBrain;
    }
    
    /**
     * This method returns the player stored in the redPlayer variable
     * @return the player currently stored in redPlayer
     */
    public Player getRedPlayer() {
        return RedPlayer;
    }
    
    /**
     * This method returns the player stored in the blackPlayer variable
     * @return the player currently stored in blackPlayer
     */
    public Player getBlackPlayer() {
        return BlackPlayer;
    }

    /**
     * This method returns the array of Ant classes contained in ants
     * @return the array of Ant classes in ants
     */
     
    public Ant[] getAnts() {
        return Ants;
    }

    /**
     * This method returns the world stored in GameWorld
     * @return the world in GameWorld
     */
    public World getGameWorld() {
        return GameWorld;
    }

    /**
     * This method sets the array ants to the Ant array inputted
     * @param Ants an array of ants that you want the Game class to hold
     */
    public void setAnts(Ant[] Ants) {
        this.Ants = Ants;
    }

    /**
     * This method returns the current round that the game is on
     * @return the value stored in thge RoundCount variable
     */
    public int getRoundCount(){
        return RoundCount;
    }

    /**
     * This method returns the colour of the player who has the highest score or 'draw' if the players have the same score
     * @return the colour of the team with the highest score variable or 'draw'
     */
    public String getWinner(){
        if (RedScore > BlackScore){
            return "red";
        } else if (BlackScore > RedScore){
            return "black";
        } else {
            return "draw";
        }
    }

    /**
     * This method checks to see if the ants are surrounded by 5 or more ants,
     * if so the ant variable alive is changed to false and the ant is changed
     * to food on the cell that it was currently occupying.
     */
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

    /**
     * This method fills the Cells that either redAntHill or blackAntHill are true
     * with ants of the same colour and adds them to the Ants array
     */
    public void generateAnts() {
        int antCount = 0;
        Cell currentCell = GameWorld.getWorld();
        Cell nextCell = null;
        for (int y = 0; y < GameWorld.sizeY; y++){
            if (y < GameWorld.sizeY-1){                    
                if (y%2 ==0){
                    nextCell = currentCell.getAdjacent(1);
                } else{
                    nextCell = currentCell.getAdjacent(2);
                }
            }    
            for(int x = 0; x< GameWorld.sizeX; x++){                
                if (currentCell.checkAntHill("black")) {
                    Ant newAnt;
                    newAnt = new Ant("black", GameWorld, currentCell);
                    currentCell.setAnt(newAnt);
                    Ants[antCount] = newAnt;
                    antCount++;
                } else if (currentCell.checkAntHill("red")) {
                    Ant newAnt = new Ant("red", GameWorld, currentCell);
                    currentCell.setAnt(newAnt);
                    Ants[antCount] = newAnt;
                    antCount++;
                }
                try {
                    currentCell = currentCell.getAdjacent(0);
                } catch (NullPointerException e){
                    //System.out.println("end of line");
                }
            }
            if (y < GameWorld.sizeY-1){
                currentCell = nextCell;
            }
        }
    }

    /**
     * This method sets the score of each team to the amount of food stored in the cells
     * that correspond to the teams anthill
     */
    public void tallyScore() {
       
        int redFood = 0;
        int blackFood = 0;
        
        Cell currentCell = GameWorld.getWorld();
        Cell nextCell = null;
        for (int y = 0; y < GameWorld.sizeY; y++){
            if (y < GameWorld.sizeY-1){                    
                if (y%2 ==0){
                    nextCell = currentCell.getAdjacent(1);
                } else{
                    nextCell = currentCell.getAdjacent(2);
                }
            }    
            for(int x = 0; x< GameWorld.sizeX; x++){
                if (currentCell.checkAntHill("black")) {
                    blackFood += currentCell.getFood();
                } else if (currentCell.checkAntHill("red")) {
                    redFood += currentCell.getFood();
                }
            }
            if (y < GameWorld.sizeY-1){
                currentCell = nextCell;
            }
        }
        RedScore = redFood;
        BlackScore = blackFood;
    }
    
    /**
     * This method runs the runGame class for the amount of rounds set by the
     * static variable rounds stored in the Game class
     */
    public void run(){
        runGame(rounds);
    }
    
    /**
     * This method runs the runGame class for an amount of times corresponding
     * to the sections value inputted
     * @param the number of sections that the game is running in
     */
    public void runSection(int sections){
        int iterations;
        iterations = rounds/sections;
        runGame(iterations);
    }
    
    /**
     * This method gets the instructions stored in each antbrain for each ant at each round
     * and then executes them, the for loop runs for as many rounds as inputted.
     * @param the number of rounds that the for loop should run for
     */
    public void runGame(int iterations) {
        String currentColour;
        String[] currentFSM;
        int currentState;
        Ant currentAnt;
        String[] currentCommands;
        String command;
        int direction;
        boolean found;
        String foeColour;
        if (Ants[0] == null)
            generateAnts();        
        for (int i = 0; i < iterations; i++) {
            //System.out.println("Game round "+ Integer.toString(i) + " Starting");
            for (int j = 0; j < antNo; j++) {
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
                    if (currentState == -1)
                        System.out.println("pause");
                    currentCommands = currentFSM[currentState].split("\\s");
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
                            int m = Integer.parseInt(currentCommands[1]);
                            int rand = NumberGen.generateRand(m);
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