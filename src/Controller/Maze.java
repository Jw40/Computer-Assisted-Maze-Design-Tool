/*
 * The MIT License
 *
 * Copyright 2015 Chris Darisaplis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
    Disclaimer - This source code has been modified/referenced from
                   the following project:

                    Title of program: Laby
                    Author(s): Chris Darisaplis, Chris Samarinas
                    Date: 05/12/2015
                    Code version: 1.2
                    Type: Source Code
                    Web address: https://github.com/algoprog/Laby
 */

package Controller;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze implements  IMaze
{

    private int rows;//rows of this maze
    private int columns;//columns of this maze
    private Cell[][] cellArray;//maze data

    private Point start;//starting point
    private Point goal;//goal point
    private Point current;//current point
    private ArrayList<Cell> solution;//current solution
    private Point logo;//logo point

    //Constructor 1
    /**
     * initially set all variables to 0 or null
     */
    public Maze()
    {
        rows = 0;
        columns = 0;
        cellArray = null;
        start = null;
        goal = null;
        logo = null;
        current = null;
        solution = null;
    }

    //Constructor 2
    /**
     * Builds a new empty maze of specific dimensions
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     */
    public Maze(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        cellArray = new Cell[rows][columns];
        for (int i = 0;i< rows;i++)
        {
            for (int j = 0;j< columns;j++)
            {
                cellArray[i][j] = new Cell();
            }
        }
        start = null;
        goal = null;
        current = null;
        solution = null;
        logo = null;
    }

    //Constructor 3
    /**
     * Builds a new maze from a text file
     * @param path file path else throws IOException e
     */
    public Maze (String path)
    {
        this();
        try (Scanner scanner = new Scanner(new File(path)))
        {
            int input;
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            this.rows = rows;
            this.columns = columns;

            cellArray = new Cell[rows][columns];
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < columns; j++)
                {
                    cellArray[i][j] = new Cell();
                }
            }
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < columns; j++)
                {
                    if (scanner.hasNextInt())
                    {
                        input = scanner.nextInt();
                        if (input == 0)
                        {
                            cellArray[i][j].thisCellIsObstacle(false);
                        }
                        else if (input == 1)
                        {
                            cellArray[i][j].thisCellIsObstacle(false);
                            start = new Point(i, j);
                        }
                        else if (input == 2)
                        {
                            cellArray[i][j].thisCellIsObstacle(false);
                            goal = new Point(i, j);
                        }
                        else
                        {
                            cellArray[i][j].thisCellIsObstacle(true);
                        }
                    }
                }
            }
        } catch (IOException e)
        {
            System.out.println("File Error - Input issue!");
        }
    }


    /**
     * @param path file path
     * @return if true no errors, else throw Exception e
     */
    public boolean saveMaze (String path)
    {
        try (PrintWriter printer = new PrintWriter(new FileWriter(path)))
        {
            printer.println(rows);
            printer.println(columns);
            for (int i = 0; i< rows; i++)
            {
                for (int j = 0; j< columns; j++)
                {
                    if (start != null && start.x == i && start.y == j)
                    {
                        printer.print("1 ");
                    }
                    else if (goal != null && goal.x == i && goal.y == j)
                    {
                        printer.print("2 ");
                    }
                    else if (logo != null && logo.x == i && logo.y == j)
                    {
                        printer.print("4 ");
                    }
                    else if (cellArray[i][j].isObstacle())
                    {
                        printer.print("3 ");
                    }
                    else
                    {
                        printer.print("0 ");
                    }
                }
                printer.println();
            }
        }catch (Exception e)
        {
            System.out.println("Output issue!");
            return false;
        }
        return true;
    }


    /**
     * Set start of this maze
     * @param x row
     * @param y column
     */
    public void setStart(int x, int y)
    {
        if (start != null)
        {
            start.x = x;
            start.y = y;
        }
        else
        {
            start = new Point(x, y);
        }
    }


    /** set the logo
     * @param x .
     * @param y .
     */
    /*
    public void setLogo(int x, int y)
    {
        if (logo != null){
            logo.x = x;
            logo.y = y;
        }
        else{
            logo = new Point(x, y);
        }
    }
     */

    /**
     * gets rows
     * @return rows
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * gets columns
     * @return columns
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * get an array with all the maze's cells
     * @return 2d array
     */
    public Cell[][] getCellArray()
    {
        return cellArray;
    }

    /**
     * gets maze start
     * @return maze start
     */
    public Point getStart()
    {
        return start;
    }

    /**
     * gets maze goal
     * @return maze goal
     */
    public Point getGoal()
    {
        return goal;
    }

    public Point getLogo()
    {
        return logo;
    }

    /**
     * sets current solution
     * @param solution new solution
     */
    public void setSolution(ArrayList<Cell> solution)
    {
        this.solution = solution;
    }

    /**
     * Sets all cells as obstacles
     */
    public void blackenThisMaze()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j].thisCellIsObstacle(true);
            }
        }
        start = null;
        goal = null;
    }

    /**
     * Clears this maze
     */
    public void whitenThisMaze()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns ; j++)
            {
                cellArray[i][j].thisCellIsObstacle(false);
            }
        }
        start = null;
        goal = null;
        logo = null;
    }


    public void setStart(Point newStartPoint)
    {
        start = newStartPoint;
    }

    public void setLogo(Point newLogoPoint)
    {
        logo = newLogoPoint;
    }

    public void setGoal(Point newGoalPoint)
    {
        goal = newGoalPoint;
    }

    public ArrayList<Cell> getSolution()
    {
        return solution;
    }

    public Point getCurrent()
    {
        return current;
    }

    public void setCurrent(Point current)
    {
        this.current = current;
    }

    public void copyMazeObstacles(IMaze otherMaze, int iStart, int jStart)
    {
        for (int i = 0; i< rows; i++)
        {
            for (int j = 0; j< columns; j++)
            {
                if (i + iStart>= otherMaze.getRows() || j + jStart>= otherMaze.getColumns() ||
                        i + iStart<0 || j + jStart< 0)
                {
                    cellArray[i][j].thisCellIsObstacle(false);
                }
                else
                {
                    cellArray[i][j].thisCellIsObstacle(otherMaze.getCellArray()[i + iStart]
                            [j + jStart].isObstacle());
                }
            }
        }
        if (otherMaze.getStart() != null && start == null)
        {
            start = new Point(otherMaze.getStart().x, otherMaze.getStart().y);
        }
        else if (otherMaze.getStart() == null)
        {
            start = null;
        }
        if (otherMaze.getGoal() != null && goal == null)
        {
            goal = new Point(otherMaze.getGoal().x, otherMaze.getGoal().y);
        }
        else if (otherMaze.getGoal() == null)
        {
            goal = null;
        }
        if (otherMaze.getLogo() != null && logo == null)
        {
            logo = new Point(otherMaze.getLogo().x, otherMaze.getLogo().y);
        }
        else if (otherMaze.getLogo() == null)
        {
            logo = null;
        }
    }


    public void addRow(IMaze oldMaze)
    {
        rows++;
        cellArray = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j] = new Cell();
            }
        }
        copyMazeObstacles(oldMaze, 0, 0);
    }

    public void addColumn(IMaze oldMaze)
    {
        columns++;
        cellArray = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j] = new Cell();
            }
        }
        copyMazeObstacles(oldMaze, 0, 0);
    }

    public void removeRow()
    {
        Maze temp = new Maze(rows, columns);
        temp.copyMazeObstacles(this, 0, 0);
        rows--;
        cellArray = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j] = new Cell();
            }
        }
        if (start != null && start.x >= rows)
        {
            temp.setStart(null);
            setStart(null);
        }
        if (goal != null && goal.x >= rows)
        {
            temp.setGoal(null);
            setGoal(null);
        }
        copyMazeObstacles(temp, 0, 0);
    }

    public void removeColumn(){
        Maze temp = new Maze(rows, columns);
        temp.copyMazeObstacles(this, 0, 0);
        columns--;
        cellArray = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j] = new Cell();
            }
        }
        if (goal != null && goal.y>= columns){
            temp.setGoal(null);
            setGoal(null);
        }
        if (start != null && start.y>= columns){
            temp.setStart(null);
            setStart(null);
        }
        copyMazeObstacles(temp, 0, 0);
    }
}
