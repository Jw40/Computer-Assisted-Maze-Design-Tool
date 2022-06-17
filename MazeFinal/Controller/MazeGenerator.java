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
import java.util.ArrayList;
import java.util.Collections;


/**
 * Used to generate the maze grid using a cell array
 * aMaze provides abstraction
 */
public class MazeGenerator {

    private int x, y; // current position
    private int start_x, start_y; // start position
    private int end_x, end_y; // end position
    private Cell[][] maze; // the maze boxes
    private final ArrayList<Cell> front;
    private final int width, height; // maze dimensions
    private int step; // generator step
    private final boolean classic; // if the generated maze is classic
    private final int vstep; // visit step, 2: classic, 1: randomized
    private final IMaze aMaze;


    /**
     * Constructor for MazeGenerator
     * Generates a new maze with given parameters
     * @param width maze columns
     * @param height maze rows
     * @param classic classic maze setting to ensure a classic type of maze
     * @param aMaze this maze to be generated
     */
    public MazeGenerator(int width, int height, boolean classic, IMaze aMaze) {
        this.classic = classic;
        this.aMaze = aMaze;
        if(classic){
            vstep = 2;
        }
        else{
            vstep = 1;
        }
        step = 0;
        end_x = -1;
        end_y = -1;
        if(width%2==0){
            width++;
        }
        if(height%2==0){
            height++;
        }
        this.width = width;
        this.height = height;
        front = new ArrayList<>();
        if(width<4 || height<4){
            return;
        }
        maze = new Cell[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                maze[i][j] = new Cell();
                maze[i][j].x = j;
                maze[i][j].y = i;
            }
        }
        start_x = rand(width-1);
        start_y = rand(height-1);
        x = start_x;
        y = start_y;

        addFront(start_x, start_y);
        int x = aMaze.getRows();
        int y = aMaze.getColumns();
        aMaze.setGoal(x-2, y-2);
        aMaze.setStart(1, 1);

    }

    /**
     * Returns the number of steps
     * @return number of steps
     */
    public int getSteps(){
        return step;
    }

    /**
     * Generates random int from 0 to ...
     * @return random int
     */
    private int rand(int to){
        if(classic) return 1+2*(int)(Math.random()*((to-1)/2+1));
        if(to % 2 == 0) to--;
        return (int)(Math.round(Math.random()*to));
    }

    /**
     * Performs next generation step
     * @param speed speed setting
     * @return true if step performed
     * @throws InterruptedException a
     */
    public boolean nextStep(int speed) throws InterruptedException{
        if(speed>0) Thread.sleep(speed);
        if(!front.isEmpty()){
            int k;
            k = front.size()-1; // DFS generation
            Cell box = front.get(k);
            front.remove(k);
            if(!canVisit(box.x, box.y)){
                return nextStep(0);
            }
            visit(box.x, box.y);

            ArrayList<Integer> directions = new ArrayList<>();
            directions.add(0); // right
            directions.add(1); // top
            directions.add(2); // bottom
            directions.add(3); // left

            Collections.shuffle(directions);

            for(int i=0;i<4;i++){
                int direction = directions.get(i);
                if(direction==0) addFront(x+vstep, y);
                else if(direction==1) addFront(x, y-vstep);
                else if(direction==2) addFront(x, y+vstep);
                else addFront(x-vstep, y);
            }
            return true;
        }
        out1:
        for(int i=height-1;i>=height-3;i--){
            for(int j=0;j<=2;j++){
                if(!maze[i][j].isObstacle()){
                    start_x = j;
                    start_y = i;
                    break out1;
                }
            }
        }
        out2:
        for(int i=0;i<3;i++){
            for(int j=width-1;j>=width-3;j--){
                if(!maze[i][j].isObstacle()){
                    end_x = j;
                    end_y = i;
                    break out2;
                }
            }
        }
        return false;
    }

    /**
     * Visits MazeBox in x, y position
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     * @return true if MazeBox is visited
     */
    private boolean visit(int x, int y){
        if(visited(x, y)){
            return false;
        }
        if(classic && maze[y][x].previous!=null){
            int wx, wy;
            wx = (maze[y][x].previous.x+x)/2; // wall x
            wy = (maze[y][x].previous.y+y)/2; // wall y
            maze[wy][wx].SetIsObstacle(false);
            // Code for GUI - make wx, wy white
            aMaze.getCellArray()[wy][wx].SetIsObstacle(false);
        }
        this.x = x;
        this.y = y;
        maze[y][x].isVisited = true;
        maze[y][x].SetIsObstacle(false);
        step++;
        aMaze.getCellArray()[y][x].SetIsObstacle(false);
        return true;
    }

    /**
     * Checks if MazeBox has been visited in x, y position
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     * @return true if MazeBox has been visited in x, y position
     */
    private boolean visited(int x, int y){
        return x>=0 && x<width && y>=0 && y<height && maze[y][x].isVisited;
    }

    /**
     * Checks if box can be visited in x, y position
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     * @return true if MazeBox can be visited in x, y position
     */
    private boolean canVisit(int x, int y){
        if(classic) return x>=0 && x<width && y>=0 && y<height && !maze[y][x].isVisited;

        int visitedNeighbors = 0;
        if(visited(x+1, y)) visitedNeighbors++; // right
        if(visited(x, y-1)) visitedNeighbors++; // top
        if(visited(x, y+1)) visitedNeighbors++; // bottom
        if(visited(x-1, y)) visitedNeighbors++; // left

        return x>=0 && x<width && y>=0 && y<height && !maze[y][x].isVisited && visitedNeighbors<2;
    }

    /**
     * Add Cell in x, y position to front set
     * @param x MazeBox x coordinate
     * @param y MazeBox y coordinate
     */
    private int addFront(int x, int y){
        if(canVisit(x, y)){
            front.add(maze[y][x]);
            maze[y][x].previous = maze[this.y][this.x];
            // code for GUI
            //aMaze.getMazeLogic()[y][x].setIsFront(true);
            return 1;
        }
        return 0;
    }

    /** could delete this if we don't want, no usages found
     * Gets generated maze
     * @return 2D array with maze (0: empty, 1: start, 2: end, 3: obstacle)
     */
    public int[][] getMaze(){
        int[][] out = new int[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(maze[i][j].isObstacle()) out[i][j] = 3;
                else if(j==start_x && i==start_y) out[i][j] = 1;
                else if(j==end_x && i==end_y) out[i][j] = 2;
                else out[i][j] = 0;
            }
        }
        return out;
    }
}
