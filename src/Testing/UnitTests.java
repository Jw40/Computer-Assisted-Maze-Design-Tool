package Testing;

import Controller.*;

public class UnitTests
{
    // Checks if the maze object has been created.
    public Maze MazeCreated()
    {
        Maze testMaze = new Maze();
        return testMaze;
    }
    public Maze MazeCreatedv2()
    {
        Maze testMaze = new Maze(16, 16);
        return testMaze;
    }




}
