package Controller;

import java.awt.*;
import java.util.ArrayList;

public interface IMaze {
    /**
     * Saves this maze to a text file
     * @param path file path
     * @return true if completed without IO errors
     */
    boolean saveMaze (String path);



    /**
     * Set start of this maze
     * @param x row
     * @param y column
     */
    void setStart(int x, int y);



    /**
     * gets rows
     * @return rows
     */
    int getRows();

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



    void setLogo(Point newLogoPoint);
    void setStart(Point newStartPoint);

    void setGoal(Point newGoalPoint);

    ArrayList<Cell> getSolution();

    Point getCurrent();

    void setCurrent(Point current);

    void copyMazeObstacles(IMaze otherMaze, int iStart, int jStart);


    void addRow(IMaze oldMaze);

    void addColumn(IMaze oldMaze);

    void removeRow();

    void removeColumn();
}
