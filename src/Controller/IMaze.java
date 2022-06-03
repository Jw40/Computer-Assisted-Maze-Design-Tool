package Controller;

import java.awt.*;
import java.util.ArrayList;

public interface IMaze {
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
     * Sets all cells as obstacles
     */
    void blackenThisMaze();

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

    void setLogo(int x, int y);

    int getLogoY();

    int getLogoX();
}
