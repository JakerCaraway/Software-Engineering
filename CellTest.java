/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import AntGame.Cell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setRocky method, of class Cell.
     */
    @Test
    public void testSetGetRocky() {
        Cell instance = new Cell(1,1);
        instance.setRocky();
        assertTrue(instance.getRocky());
    }

    

    /**
     * Test of setFood method, of class Cell.
     */
    @Test
    public void testSetGetFood() {
        int amount = 2;
        Cell instance = new Cell(1,1);
        instance.setFood(amount);
        assertEquals(instance.getFood(), 2);
    }

   

    /**
     * Test of setAnt method, of class Cell.
     */
    @Test
    public void testSetGetAnt() {
        World world = null;
        Cell instance = new Cell(1,1);
        Ant ant = new Ant("red", world, instance);
        instance.setAnt(ant);
        assertEquals(instance.getAnt(), ant);
    }

     

    /**
     * Test of removeAnt method, of class Cell.
     */
    @Test
    public void testRemoveAnt() {
        World world = null;
        Cell instance = new Cell(1,1);
        Ant ant = new Ant("red", world, instance);
        instance.setAnt(ant);
        instance.removeAnt();
        assertNull(instance.getAnt());
    }

    /**
     * Test of isAnt method, of class Cell.
     */
    @Test
    public void testIsAnt() {
        World world = null;
        Cell instance = new Cell(1,1);
        Ant ant = new Ant("red", world, instance);
        assertFalse(instance.isAnt());
        instance.setAnt(ant);
        assertTrue(instance.isAnt());
    }

    /**
     * Test of setPheramone method, of class Cell.
     */
    @Test
    public void testSetCheckPheramone() {
        int Pheromone = 1;
        String Colour = "red";
        Cell instance = new Cell(1,1);
        assertFalse(instance.checkPheromone(Pheromone, Colour));
        instance.setPheramone(Pheromone, Colour);
        assertTrue(instance.checkPheromone(Pheromone, Colour));
    }

    /**
     * Test of setAntHill method, of class Cell.
     */
    @Test
    public void testSetCheckAntHill() {
        String colour = "red";
        Cell instance = new Cell(1,1);
        assertFalse(instance.checkAntHill(colour));
        instance.setAntHill(colour);
        assertTrue(instance.checkAntHill(colour));
    }

    

    /**
     * Test of getAdjacent method, of class Cell.
     */
    @Test
    public void testSetGetAdjacent() {
        System.out.println("getAdjacent");
        int side = 0;
        Cell instance = new Cell(1,1);
        Cell instance2 = new Cell(2,1);
        instance.setAdjacent(side, instance2);
        Cell result = instance.getAdjacent(side);
        assertEquals(instance2, result);
    }

}
