package Testing;

import Controller.Cell;
import Controller.IMaze;
import Controller.KidsMaze;
import Controller.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestMaze extends Maze {

    IMaze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze( 10, 10);
    }

    @Test
    public void testBlackenMaze()
    {
        maze.blackenThisMaze();
        Cell[][] a = maze.getCellArray();
        for(int i = 0; i < a.length; i++)
        {
            assertTrue(a[i][i].isObstacle());
        }
    }

    @Test
    public void testGetMazeDetails()
    {
        maze.getMazeName();
        maze.getAuthorName();
        maze.getIsKidsMaze();


    }

    @Test
    public void testWhiteMaze(){
        maze.whitenThisMaze();
        int counter = 0;
        Cell[][] a = maze.getCellArray();
        for(int i = 0; i < a.length; i++)
        {
            if(a[i][i].isObstacle())
            {
                counter++;
            }
        }
        assertEquals(0, counter);
    }

    @Test
    public void testMazeSize()
    {
        assertEquals(100, maze.getRows() * maze.getColumns());
    }

    @Test
    public void newMazeSize()
    {
        maze.whitenThisMaze();
        maze = new Maze(50,50);
        assertEquals(2500, maze.getRows() * maze.getColumns());
    }

    @Test
    public void newMazeWithName()
    {
        maze = new Maze(10,10, "John Smith", "myMaze", LocalDate.now());
        String a = maze.getMazeName();
        assertEquals( "John Smith", maze.getAuthorName());
        assertEquals( "myMaze", maze.getMazeName());
        System.out.println(maze.ToString());
    }

    @Test
    public void newMazeKidsMazeSize()
    {
        maze.whitenThisMaze();
        maze = new KidsMaze(14,10);
        assertEquals(140, maze.getRows() * maze.getColumns());

    }

    @Test
    public void testStart()
    {
        assertEquals(null, maze.getStart());
        maze.setStart(10,10);
        Point a = new Point(10,10);
        assertEquals(a, maze.getStart());
    }

    @Test
    public void testGoal()
    {
        assertEquals(null, maze.getGoal());
        maze.setGoal(20,20);
        Point a = new Point(20,20);
        assertEquals(a, maze.getGoal());
    }

    @Test
    public void testLogo()
    {
        assertEquals(null, maze.getLogo());
        Point a = new Point(30,30);
        maze.setLogo(a);
        Point b = new Point(30,30);
        assertEquals(b, maze.getLogo());
    }

    @Test
    public void testSaveMaze()
    {
        maze.saveMaze("/dummypath/dummypath/");
    }

    @Test
    public void testSaveMazev2()
    {
        maze.saveMaze("Desktop");
    }

    @Test
    public void testSaveMazev3()
    {
        maze.saveMaze("");
    }
    @Test
    public void testRowsColumns()
    {
        assertEquals(10, maze.getColumns());
        assertEquals(10, maze.getRows());
    }

    @Test
    public void testBlackenCell()
    {
        maze.whitenThisMaze();
        maze = new Maze(10,10);
        maze.blackenThisCell(8,8);
        Cell[][] a = maze.getCellArray();
        assertTrue( a[8][8].isObstacle());
    }

    @Test
    public void testCountBlacken()
    {
        maze = new Maze(10,10);
        maze.blackenThisMaze();
        assertEquals(100, maze.countBlacken());
    }

    @Test
    public void testAddRow()
    {
        IMaze thisMaze = new Maze(10,10);
        maze.addRow((thisMaze));
        assertEquals(110, maze.getRows()*maze.getColumns());
    }

    @Test
    public void testAddColumn(){
        IMaze thisMaze = new Maze(10,10);
        maze.addColumn((thisMaze));
        assertEquals(110, maze.getRows()*maze.getColumns());
    }

    @Test
    public void testRemoveColumn(){
        maze.removeColumn();
        assertEquals(90, maze.getRows()*maze.getColumns());
    }

    @Test
    public void testRemoveRow(){
        maze.removeRow();
        assertEquals(90, maze.getRows()*maze.getColumns());
    }

    @AfterEach
    void tearDown() {
    }
}