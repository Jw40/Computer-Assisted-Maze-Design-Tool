import Controller.IMaze;
import Controller.Maze;
import Controller.MazeGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMazeGenerator extends MazeGenerator {
    IMaze maze = new Maze(10,10);
    MazeGenerator generator;

    TestMazeGenerator mazeGenerator = new TestMazeGenerator(maze.getRows(), maze.getColumns(), true,maze);

    /**
     * Constructor for MazeGenerator
     * Generates a new maze with given parameters
     *
     * @param width   maze columns
     * @param height  maze rows
     * @param classic classic maze setting to ensure a classic type of maze
     * @param aMaze   this maze to be generated
     */
    public TestMazeGenerator(int width, int height, boolean classic, IMaze aMaze) {
        super(width, height, classic, aMaze);


    }


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
}