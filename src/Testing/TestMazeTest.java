package Testing;

import Controller.Cell;
import Controller.IMaze;
import Controller.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMazeTest extends Maze {

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
        assertTrue(a[0][0].isObstacle());
    }

    @Test
    public void testMazeSize()
    {
        assertEquals(100, maze.getRows() * maze.getColumns());
    }

    @Test
    public void newMaze()
    {
        maze.whitenThisMaze();
        maze = new Maze(50,50);
        assertEquals(2500, maze.getRows() * maze.getColumns());
    }


    @AfterEach
    void tearDown() {
    }
}