package Controller;

import java.awt.*;

/**
 * Abstract Class which holds the method calls for the Maze and KidsMaze classes, for the purpose of Abstraction.
 */
public interface IMaze
{
    /**
     * Used for maze solver class to get the current maze size
     * @return the size of a maze to the maze solver class
     */
    int  getMazeSize();


    /**
     * Used to return the Cell array to the maze solver class
     * @return  the Cell array to the maze solver class
     */
    Cell[] getMazeCells();


    /**
     * Used in the MazePanel class to draw the maze in this current version
     * @param g to draw the maze, used in the MazePanel class
     */
    void draw(Graphics g);


    /**
     * used to make the walls of a maze in a grid like fashion
     */
    void makeWalls();


    /**
     * used after make walls to clear random walls in the maze
     */
    void clearWalls();


    /**
     * Used to set the dimension for the J panel
     * @return  the ideal size of the window (for ScrollPanes)
     */
    Dimension windowSize();


    /**
     * return the current maze
     */
    void getMaze();


    /**
     * Used for debugging to print to the console
     * @return string to console of new maze object (used for debugging)
     */
    String toString();


    /**
     * draw a new maze template
     */
    void drawMazeTemplate();


    /**
     * Used to return the path from the maze class
     * @return get the path from the Maze class for the MazeSolver class
     *
     *
     */
    boolean[] getPath();

    void DrawSolution(Graphics g);
}
