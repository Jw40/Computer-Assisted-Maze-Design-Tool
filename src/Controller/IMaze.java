package Controller;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface IMaze {
    /**
     * Saves this maze to a text file
     * @param path file path
     * @return true if completed without IO errors
     */
    boolean saveMaze (String path);


    /**
     * Set if a cell is obstacle
     * @param x row
     * @param y column
     * @param obstacle obstacle or not
     */
    void isObstacle(int x, int y, boolean obstacle);

    /**
     * Set start of this maze
     * @param x row
     * @param y column
     */
    void setStart(int x, int y);

    /**
     * Set goal of this maze
     * @param x row
     * @param y column
     */
    void setGoal(int x, int y);

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
    MazeBox[][] getMazeLogic();

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
    void setSolution(ArrayList<MazeBox> solution);

    /**
     * Sets all cells as obstacles
     */
    void blacken();

    /**
     * Clears this maze
     */
    void whiten();



    void setLogo(Point newLogoPoint);
    void setStart(Point newStartPoint);

    void setGoal(Point newGoalPoint);

    ArrayList<MazeBox> getSolution();

    Point getCurrent();

    void setCurrent(Point current);

    void copyMazeObstacles(Maze otherMaze, int iStart, int jStart);


    void addRow(Maze oldMaze);

    void addColumn(Maze oldMaze);

    void removeRow();

    void removeColumn();
}
