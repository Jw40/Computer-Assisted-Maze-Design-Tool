package Controller;

import java.awt.*;

public interface IMaze
{

    /**
     * @return the size of a maze to the maze solver class
     */
    int  getMazeSize();


    /**
     * @return the Cell array to the maze solver class
     */
    Cell[] getMazeCells();


    /**
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
     * @return the ideal size of the window (for ScrollPanes)
     */
    Dimension windowSize();


    /**
     * return the current maze
     */
    void getMaze();


    /**
     * @return string to console of new maze object (used for debugging)
     */
    String toString();


    /**
     * draw a new maze template
     */
    void drawMazeTemplate();


    /**
     * @return
     */
    boolean[] getPath();
}
