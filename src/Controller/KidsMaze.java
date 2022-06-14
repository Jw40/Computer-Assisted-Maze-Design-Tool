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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * KidsMaze class uses an array of cells Cell[][] to construct a maze using one of 4 contractors.
 * A KidsMaze can be created with just rows and columns as a parameter or with an author name, maze name and creation date.
 *
 */
public class KidsMaze implements  IMaze
{

    private int rows;//rows of this maze
    private int columns;//columns of this maze
    private Cell[][] cellArray;//maze data

    private Point start;//starting point
    /**
     * goal point
     */
    private Point goal;//goal point
    private Point current;//current point
    private ArrayList<Cell> solution;//current solution
    private Point logo;//logo point
    /**
     * author of the maze
     */
    public String authorName;
    /**
     * name of the maze
     */
    public String mazeName;
    /**
     * creation date of the maze
     */
    public LocalDate creationDate;
    /**
     * last edit date
     */
    public LocalDate editDate;//last edit date
    /**
     * setting for kids maze or false if adult maze
     */
    protected boolean iskidsmaze;

    //Constructor 1
    /**
     * initially set all variables to 0 or null
     */
    public KidsMaze()
    {
        rows = 0;
        columns = 0;
        cellArray = null;
        start = null;
        goal = null;
        logo = null;
        current = null;
        solution = null;
        iskidsmaze = false;
    }

    //Constructor 2

    /**
     *      * Builds a new empty maze of specific dimensions
     *      * @param rows number of rows in the maze
     *      * @param columns number of columns in the maze
     *      *                add maze with author name
     * @param rows x
     * @param columns y
     * @param authorName name of author
     * @param mazeName name of maze
     * @param creationDate creation date of maze
     */
    public KidsMaze(int rows, int columns, String authorName, String mazeName, LocalDate creationDate)
    {
        this.creationDate = creationDate;
        this.mazeName = mazeName;
        this.authorName = authorName;
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
        iskidsmaze = true;
    }
    //Constructor 3
    /**
     * Builds a new empty maze of specific dimensions
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     */

    public KidsMaze(int rows, int columns)
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
        iskidsmaze = true;
    }

    //Constructor 4
    /**
     * Builds a new maze from a text file
     * @param path file path else throws IOException e
     */
    public KidsMaze (String path)
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
                            cellArray[i][j].SetIsObstacle(false);
                        }
                        else if (input == 1)
                        {
                            cellArray[i][j].SetIsObstacle(false);
                            start = new Point(i, j);
                        }
                        else if (input == 2)
                        {
                            cellArray[i][j].SetIsObstacle(false);
                            goal = new Point(i, j);
                        }
                        else
                        {
                            cellArray[i][j].SetIsObstacle(true);
                        }
                    }
                }
            }

        } catch (IOException e)
        {
            System.out.println("File Error - Input issue!");
        }
    }

    @Override
    public String getMazeName()
    {
        return this.mazeName;
    }
    @Override
    public String getAuthorName()
    {
        return this.authorName;
    }

    public boolean getIsKidsMaze()
    {
        return iskidsmaze;
    }

    /**
     * @param x x cord on maze
     * @param y y cord on maze
     *          set the start point for the maze with x,y
     */
    @Override
    public void setStart(int x, int y) {
        start = new Point(x,y);
    }

    /**
     * @param x x cord on maze
     * @param y y cord on maze
     *          set the goal point for the maze with x,y
     */
    @Override
    public void setGoal(int x, int y) {
        goal = new Point(x,y);
    }

    /**
     * @param path file path
     *             save the maze as a txt file
     */
    @Override
    public void saveMaze (String path)
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
        }
    }

    /**
     * @return Maze details to string
     */
    @Override
    public String ToString()
    {
        return "Maze Name: " + mazeName + " Author Name: " + authorName + " Creation Date: " + creationDate;
    }

    /**
     * gets rows
     * @return rows
     */
    @Override
    public int getRows()
    {
        return rows;
    }

    /**
     * saves maze as a string to insert into the database
     * @return maze to string
     */
    @Override
    public String saveMazeToString ()
    {
        String MazeData = "";
        try (PrintWriter printer = new PrintWriter(new FileWriter(MazeData)))
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
                MazeData = printer.toString();
                System.out.println((MazeData));
            }
        }catch (Exception e)
        {
            System.out.println("Output issue!");
        }
        return MazeData;
    }

    /**
     * gets columns
     * @return columns
     */
    @Override
    public int getColumns()
    {
        return columns;
    }


    /**
     * get an array with all the maze's cells
     * @return 2d array
     */
    @Override
    public Cell[][] getCellArray()
    {
        return cellArray;
    }


    /**
     * gets maze start
     * @return maze start
     */
    @Override
    public Point getStart()
    {
        return start;
    }

    /**
     * gets maze goal
     * @return maze goal
     */
    @Override
    public Point getGoal()
    {
        return goal;
    }

    /**
     * @param newStartPoint used to set the start variable for the maze
     */
    @Override
    public void setStart(Point newStartPoint)
    {
        start = newStartPoint;
    }

    /**
     * @param newGoalPoint used to set the goal variable for the maze
     */
    @Override
    public void setGoal(Point newGoalPoint)
    {
        goal = newGoalPoint;
    }
    /**
     * @return the logo
     */
    @Override
    public Point getLogo()
    {
        return logo;
    }

    /**
     * @param newLogoPoint used to set the logo variable for the maze
     */
    @Override
    public void setLogo(Point newLogoPoint)
    {
        logo = newLogoPoint;
    }

    /**
     * sets current solution
     * @param solution new solution
     */
    @Override
    public void setSolution(ArrayList<Cell> solution)
    {
        this.solution = solution;
    }


    /**
     * @return used to get the solution array for this maze
     */
    @Override
    public ArrayList<Cell> getSolution()
    {
        return solution;
    }

    /**
     * @return used to get the current point in the maze
     */
    @Override
    public Point getCurrent()
    {
        return current;
    }

    /**
     * @param current used to set the current point in the maze
     */
    @Override
    public void setCurrent(Point current)
    {
        this.current = current;
    }

    /**
     * Sets a single cell a as obstacles
     */
    @Override
    public void blackenThisCell(int x, int y)
    {
        cellArray[x][y].SetIsObstacle(true);
    }

    /**
     * Sets all cells as obstacles
     */
    @Override
    public void blackenThisMaze()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                cellArray[i][j].SetIsObstacle(true);
            }
        }
        start = null;
        goal = null;
    }
    /**
     * Counts non-traversable cells
     */
    @Override
    public int countBlacken(){
        int count = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++){

                if (cellArray[i][j].IsObstacle) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Clears this maze
     */
    @Override
    public void whitenThisMaze()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns ; j++)
            {
                cellArray[i][j].SetIsObstacle(false);
            }
        }
        start = null;
        goal = null;
        logo = null;
    }


    /**
     * @param otherMaze .
     * @param iStart .
     * @param jStart .
     */
    @Override
    public void copyMazeObstacles(IMaze otherMaze, int iStart, int jStart)
    {
        for (int i = 0; i< rows; i++)
        {
            for (int j = 0; j< columns; j++)
            {
                if (i + iStart>= otherMaze.getRows() || j + jStart>= otherMaze.getColumns() ||
                        i + iStart<0 || j + jStart< 0)
                {
                    cellArray[i][j].SetIsObstacle(false);
                }
                else
                {
                    cellArray[i][j].SetIsObstacle(otherMaze.getCellArray()[i + iStart]
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

    //used for building a new maze
    /**
     * @param oldMaze last maze in gui
     *                adds new row
     */
    @Override
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

    /**
     * @param oldMaze last maze in the gui
     *                adds new column
     */
    @Override
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

    /**
     * remove a row from the maze
     */
    @Override
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

    /**
     * remove a column from a maze
     */
    @Override
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
