package AntGameTest;
//import antBrain;
import AntGame.AntBrain;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author oab24
 */
public class AntBrain2Test {
    AntBrain test_brain;
    
    public AntBrain2Test() {
    }
    
    
    
    @Before
    public void setUp() {
        String string_dudBrain_simple = ("dummy");
        String string_dudBrain_blank = ("");
        String string_dudBrain = ("Move st1 st2\n dummy");
        test_brain = new AntBrain (string_dudBrain_simple);
    }
    
    /**
     *  tests overall ability to load both a string and from a file. Specific file paths are used here, so if used in a different place, the paths need changing 
     *  All tests passed when on original system (04/05/2016
     */
    @Test
    public void test_createBrains(){
        AntBrain brain_string = new AntBrain("move 1 2\nPickUp 1 3\nturn left 1\n");
        assertTrue(brain_string.getFiniteStateMachine().length > 0); // brain has an fsm which has characters in it
        File f = new File("N:\\Documents\\Second year\\Software engineering\\Software Engineering Cw\\src\\AntGame\\antBrainFileGood.txt");
        System.out.println("FILE EXISTS " +f.exists());
        try {
            brain_string = new AntBrain(f);// pass in a file
            assertTrue((brain_string.getFiniteStateMachine()).length >0);
            brain_string = new AntBrain(new File("N:\\Documents\\Second year\\Software engineering\\sample_ant.txt"));
            assertTrue((brain_string.getFiniteStateMachine().length)>0);
            
        } catch (IOException exception) {
            assertTrue(1==0);
        }
    }
    
    /**
     * tests for when the check function is passed an FSM with several lines of commands
     */
    @Test
    public void test_checkBrain(){
        String command = ("");
        assertFalse(test_brain.checkBrain(""));
        assertTrue(test_brain.checkBrain("move 1 2\nsense ahead 1 2 foe"));
        assertTrue(test_brain.checkBrain("move 1 2\nPickUp 1 3\nturn left 1\n"));
    }
    
    /**
     * checks if the syntax of several individual commands is correct
     */
    @Test
    public void test_checkLine(){
        assertFalse(test_brain.checkLine("dummy"));
        assertFalse(test_brain.checkLine("abc"));
        assertTrue(test_brain.checkLine("move 2 4")); // test simple instruction 
        assertTrue(test_brain.checkLine("move 1 2\n")); //testing new line
        assertTrue(test_brain.checkLine("MovE 1 2\n")); // testing case
        assertFalse(test_brain.checkLine("MovE 1 a\n")); // letter instead of state
        assertFalse(test_brain.checkLine("sense 1 2 marker\n")); // no last part of state
        assertFalse(test_brain.checkLine("sense ahead 1 2 marker 6\n")); // maker type is out of bounds
        assertFalse(test_brain.checkLine("sense 1 2 marker 5\n")); // no phrase ahead, so fail
        assertTrue(test_brain.checkLine("sense ahead 1 2 marker 5\n")); // passes
        assertFalse(test_brain.checkLine("sense ahead 1 2 marker \n")); // false, the array is out of bounds
        assertTrue(test_brain.checkLine("PickUp 1 3"));
        assertFalse(test_brain.checkLine(" move 1 2")); // fails as whitespace before the instruction
    }
    
    /**
     * checks if the numbers used in the state changes run in the correct ranges
     */
    @Test
    public void test_CheckNumbers(){
        assertFalse(test_brain.checkNumbers("", 0, 9999)); // empty
        assertFalse(test_brain.checkNumbers("a", 0, 9999));// wrong type of input
        assertFalse(test_brain.checkNumbers("5a", 0, 9999)); // partially correct input
        assertTrue(test_brain.checkNumbers("5", 0, 9999)); // normal input
        assertTrue(test_brain.checkNumbers("0", 0, 9999)); // edge cases
        assertFalse(test_brain.checkNumbers("-1", 0, 9999));
        assertTrue(test_brain.checkNumbers("9999", 0, 9999)); // 
        assertFalse(test_brain.checkNumbers("10000", 0, 9999));
        assertFalse(test_brain.checkNumbers("", 1, 7)); // new boundarys
        assertFalse(test_brain.checkNumbers("0", 1, 7)); // 
        assertTrue(test_brain.checkNumbers("1", 1, 7)); //
        assertTrue(test_brain.checkNumbers("5", 1, 7)); // 
        assertTrue(test_brain.checkNumbers("7", 1, 7)); //
        assertFalse(test_brain.checkNumbers("8", 1, 7)); // 
    }
}

