package Testing;

import Controller.IMaze;
import Controller.MazeGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class TestMazeGenerator extends MazeGenerator {

    /**
     * DFS initialization
     *
     * @param width   maze width >= 4
     * @param height  maze height >= 4
     * @param classic
     * @param aMaze   this maze
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