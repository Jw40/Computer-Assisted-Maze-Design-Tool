package Controller;

import java.awt.*;
import java.util.ArrayList;

/**
 * To provide abstraction and inheritance for the maze class
 */
public interface IMaze {
    /**
     * set start variable
     * @param x x pos
     * @param y y pos
     *
     */
    void setStart(int x, int y);

    /**
     * set goal variable
     * @param x x pos
     * @param y y pos
     *
     */
    void setGoal(int x, int y);

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

    String saveMazeToString();

    /**
     * Maze details to string, used to print to console to verify details
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
     * point in which the logo is being set
     * @param newLogoPoint used to set the logo variable for the maze
     */
    void setLogo(Point newLogoPoint);

    /**
     * point in which the start icon is set
     * @param newStartPoint used to set the start variable for the maze
     */
    void setStart(Point newStartPoint);

    /**
     * point in which the goal icon is set
     * @param newGoalPoint used to set the goal variable for the maze
     */
    void setGoal(Point newGoalPoint);

    /**
     * reference to the maze data, cell array
     * @return used to get the solution array for this maze
     */
    ArrayList<Cell> getSolution();

    /**
     * reference to the current cell in the maze
     * @return used to get the current point in the maze
     */
    Point getCurrent();

    /**
     * reference to the current cell in the maze
     * @param current used to set the current point in the maze
     */
    void setCurrent(Point current);

    /**
     * reference to the old maze
     * @param otherMaze the old maze
     * @param iStart x cord start of new
     * @param jStart y cord start of new
     */
    void copyMazeObstacles(IMaze otherMaze, int iStart, int jStart);

    /**
     * add a row to the old maze
     * @param oldMaze last maze in gui
     *                adds new row
     */
    void addRow(IMaze oldMaze);

    /**
     * add a colum to the old maze
     * @param oldMaze last maze in the gui
     *                adds new column
     */
    void addColumn(IMaze oldMaze);

    /**
     *
     * remove a row from the maze
     */
    void removeRow();

    /**
     * remove a column from a maze
     */
    void removeColumn();

    /**
     * get the name of this maze
     * @return maze name
     */
    String getMazeName();

    /**
     * get the author name of this maze
     * @return this maze author name
     */
    String getAuthorName();

    /**
     * reference to if this is a kids maze or not
     * @return return true if this is a kids maze
     */
    boolean getIsKidsMaze();

}
