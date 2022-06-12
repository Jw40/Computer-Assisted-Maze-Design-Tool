package Controller;

import java.awt.*;
import java.util.ArrayList;

/**
 * To provide abstraction and inheritance for the maze class
 */
public interface IMaze {
    /**
     * @param x x pos
     * @param y y pos
     *          set start variable
     */
    void setStart(int x, int y);

    /**
     * @param x x pos
     * @param y y pos
     *          set goal variable
     */
    void setGoal(int x, int y);
    /**
     * Create a kids' maze with fewer rows
     */
    /*

    void KidMaze();
    */
    /**
     * Saves this maze to a text file
     * @param path file path
     */
    void saveMaze (String path);

    /**
     * gets rows
     * @return rows
     */
    int getRows();

    /**
     * @return Maze details to string
     */
    String ToString();

    /**
     * gets columns
     * @return columns
     */
    int getColumns();

    /**
     * get an array with all the maze's cells
     * @return 2d array
     */
    Cell[][] getCellArray();

    /**
     * gets maze start
     * @return maze start
     */
    Point getStart();

    /**
     * gets maze goal
     * @return maze goal
     */
    Point getGoal();
    /**
     * gets maze logo point
     * @return maze logo
     */
    Point getLogo();

    /**
     * sets current solution
     * @param solution new solution
     */
    void setSolution(ArrayList<Cell> solution);


    /**
     * blacken this individual cell of this maze, used for logo to blacken the cell underneath the logo
     * @param x pos x
     * @param y pos y
     *
     */
    void blackenThisCell(int x, int y);

    /**
     * Sets all cells as obstacles
     */
    void blackenThisMaze();

    /**
     * count the black tiles on the maze for solution metrics
     * @return the count of black tiles on the maze
     */
    int countBlacken();

    /**
     * Clears this maze
     */
    void whitenThisMaze();


    /**
     * @param newLogoPoint used to set the logo variable for the maze
     */
    void setLogo(Point newLogoPoint);

    /**
     * @param newStartPoint used to set the start variable for the maze
     */
    void setStart(Point newStartPoint);

    /**
     * @param newGoalPoint used to set the goal variable for the maze
     */
    void setGoal(Point newGoalPoint);

    /**
     * @return used to get the solution array for this maze
     */
    ArrayList<Cell> getSolution();

    /**
     * @return used to get the current point in the maze
     */
    Point getCurrent();

    /**
     * @param current used to set the current point in the maze
     */
    void setCurrent(Point current);

    /**
     * @param otherMaze .
     * @param iStart .
     * @param jStart .
     */
    void copyMazeObstacles(IMaze otherMaze, int iStart, int jStart);

    /**
     * @param oldMaze last maze in gui
     *                adds new row
     */
    void addRow(IMaze oldMaze);

    /**
     * @param oldMaze last maze in the gui
     *                adds new column
     */
    void addColumn(IMaze oldMaze);

    /**
     * remove a row from the maze
     */
    void removeRow();

    /**
     * remove a column from a maze
     */
    void removeColumn();

    String getMazeName();

    String getAuthorName();

    /**
     * @return return true if this is a kids maze
     */
    boolean getIsKidsMaze();

}
