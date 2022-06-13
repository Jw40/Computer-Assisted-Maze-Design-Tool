package Testing;

import Controller.IMaze;
import Controller.Maze;
import Controller.MazeGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMazeGenerator extends MazeGenerator {

    /**
     * DFS initialization
     *
     * @param width   maze width >= 4
     * @param height  maze height >= 4
     * @param classic classic maze type
     * @param aMaze   this maze
     */
    public TestMazeGenerator(int width, int height, boolean classic, IMaze aMaze) {
        super(width, height, classic, aMaze);
    }
    Maze maze = new Maze();
    @BeforeEach
    void setUp() {

    }

    @Test
    public void testMaze()
    {

    }

    @AfterEach
    void tearDown() {
    }
}