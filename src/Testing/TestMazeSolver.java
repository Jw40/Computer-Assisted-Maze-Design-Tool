package Testing;

import Controller.IMaze;
import Controller.Maze;
import Controller.MazeSolver;
import View.MazePanel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMazeSolver extends MazeSolver {
    Maze maze = new Maze(20,20);
    MazePanel mazePanel = new MazePanel(maze);
    MazeSolver  solver = new MazeSolver(mazePanel.getMazeData(), maze);

    /**
     * Create maze from input
     *
     * @param mazeInput 2D array with 0 and 1 for obstacles
     * @param mazeData
     */
    public TestMazeSolver(int[][] mazeInput, IMaze mazeData) {
        super(mazeInput, mazeData);
    }

    @BeforeEach
    void setUp() {


    }

    @Test
    public void TestSolver()
    {
        solver.getSolution();
        assertEquals(false, solver.isSolved() );
    }



    @AfterEach
    void tearDown() {
    }
}