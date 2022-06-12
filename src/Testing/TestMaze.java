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
    }

    @Test
    public void newMazeKidsMazeSize()
    {
        maze.whitenThisMaze();
        maze = new KidsMaze(14,10);
        assertEquals(140, maze.getRows() * maze.getColumns());

    }

    @Test
    public void testStartGoal()
    {
        assertEquals(null, getStart());
        maze.setStart(10,10);
        Point a = new Point(10,10);
        assertEquals(a, maze.getStart());
    }

    @AfterEach
    void tearDown() {
    }
}