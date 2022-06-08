package Testing;

import Controller.IMaze;
import Controller.MazeSolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class TestMazeSolver extends MazeSolver {
    
    IMaze maze;
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



    @AfterEach
    void tearDown() {
    }
}