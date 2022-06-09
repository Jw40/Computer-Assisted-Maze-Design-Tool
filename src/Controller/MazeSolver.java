/*
 * The MIT License
 *
 * Copyright 2015 Chris Samarinas
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
import java.util.ArrayList;
import java.util.Collections;

/**
 * Maze Solver used to find the path from the start to the goal
 * uses a breathd first search
 * returns the the GUI class to display to the screen
 */
public class MazeSolver {

    protected int x, y; // current position
    /**
     * end pos x
     */
    protected int end_x;
    /**
     * end pos y
     */
    protected int end_y; // end position
    protected Cell[][] maze; // the maze boxes
    protected int width, height; // maze dimensions
    protected int step; // solver step
    protected ArrayList<Cell> solution; // maze solution
    protected int maxFront; // max front set size
    protected IMaze mazeData;

    private ArrayList<Cell> front;
    private boolean randomStep;
    private boolean dfs;


    /**
     * Create maze from input
     * @param mazeInput 2D array with 0 and 1 for obstacles
     * @param mazeData this maze data
     */
    public MazeSolver(int[][] mazeInput, IMaze mazeData)
    {
        x= -1;
        y = -1;
        end_x = -1;
        end_y = -1;
        maxFront = 0;
        solution = new ArrayList<>();
        step = 0;
        width = mazeInput[0].length;
        height = mazeInput.length;
        maze = new Cell[height][width];
        this.mazeData = mazeData;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                maze[i][j] = new Cell();
                maze[i][j].SetIsObstacle(mazeInput[i][j]==3);
                maze[i][j].x = j;
                maze[i][j].y = i;
                if(mazeInput[i][j]==1){
                    x = j;
                    y = i;
                }else if(mazeInput[i][j]==2){
                    end_x = j;
                    end_y = i;
                }
            }
        }
    }


    /**
     * BFS initialization
     * initalize variables
     */
    public void MazeSolverBFSInit()
    {
        front = new ArrayList<>();
        this.randomStep = false;
        this.dfs = false;
        addFront(x, y);
    }


    /**
     * Perform the next step of search
     * @param speed in milliseconds
     * @return true if step performed
     * @throws java.lang.InterruptedException a
     */
    public boolean nextStep(int speed) throws InterruptedException
    {
        if(speed>0) Thread.sleep(speed);
        if(!front.isEmpty()){
            Cell box;
            if(dfs){
                box = front.get(front.size()-1);
                front.remove(front.size()-1);
            }else{
                box = front.get(0);
                front.remove(0);
            }

            if(box.isVisited){
                return nextStep(0);
            }

            visit(box.x, box.y);

            if(isSolved()){
                return false;
            }

            ArrayList<Integer> directions = new ArrayList<>();
            directions.add(0); // right
            directions.add(1); // top
            directions.add(2); // bottom
            directions.add(3); // left
            if(randomStep){
                Collections.shuffle(directions);
            }

            for(int i=0;i<4;i++){
                int direction = directions.get(i);
                if(direction==0) addFront(x+1, y);
                else if(direction==1) addFront(x, y-1);
                else if(direction==2) addFront(x, y+1);
                else addFront(x-1, y);
            }
            return true;
        }
        return false;
    }


    /**
     * Add MazeBox in x, y position to front set
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     */
    protected void addFront(int x, int y)
    {
        if(validPosition(x, y))
        {
            if(maze[y][x].isAdded)
            {
                return;
            }
            maze[y][x].isAdded = true;
            if(step>0)
            {
                maze[y][x].previous = maze[this.y][this.x];
            }
            front.add(maze[y][x]);
            int fsize = front.size();
            if(fsize>maxFront){
                maxFront = fsize;
            }
        }
    }


    /**
     * Visit MazeBox in x, y position
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     * @return true if MazeBox is visited
     */
    protected boolean visit(int x, int y)
    {
        if(!validPosition(x, y)){
            return false;
        }
        this.x = x;
        this.y = y;
        maze[y][x].isVisited = true;
        step++;
        //GUI
        mazeData.getCellArray()[y][x].isVisited = true;
        mazeData.setCurrent(new Point(y, x));
        return true;
    }


    /**
     * If MazeBox can be visited in x, y position
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     * @return true if MazeBox can be visited in x, y position
     */
    protected boolean validPosition(int x, int y)
    {
        return x>=0 && x<width && y>=0 && y<height && !maze[y][x].isObstacle();
    }


    /**
     * Get current maze solution
     * @return ArrayList with current MazeBox solution (MazeBox items)
     */
    public ArrayList<Cell> getSolution()
    {
        solution.clear();
        if(step==0) return null;
        Cell box = maze[y][x];
        int c = 0;
        while(c<2){
            solution.add(box);
            if(box!=null) box = box.previous;
            if(box==null || box.previous==null){
                c++;
            }
        }
        Collections.reverse(solution);
        return solution;
    }


    /**
     * Checks if the maze is solved
     * @return true if solution is found
     */
    public boolean isSolved()
    {
        return x==end_x && y==end_y;
    }


    /**
     * Returns the number of steps
     * @return number of steps
     */
    public int getSteps()
    {
        return step;
    }

}
