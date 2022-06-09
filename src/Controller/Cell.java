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
import java.awt.Rectangle;


 /**
 * Used to create an Individual maze cell, using java.awt.Rectangle
 */
public class Cell implements Comparable<Cell> {

     /**
      * single cell of a maze
      */

    public Rectangle cell;
     /**
      * x axis of cell
      */

    public int x;
     /**
      * y axis of cell
      */
    public int y;

     /**
      * variable to tell if a cell has been visited or not, used in solving
      */

    public boolean isVisited;
     /**
      * variable to check if cell is added
      */
    public boolean isAdded;

     /**
      * get the previous cell
      */
     
    public Cell previous;
     /**
      * check if a cell is a obstruct
      */
    protected boolean IsObstacle;

     /**
      * Set all variables of each cell to be false or null
      */
    public Cell()
    {
        isAdded = false;
        IsObstacle = false;
        isVisited = false;
        previous = null;
    }

    /**
     * @return IsObstacle which will either be true or false
     */
    public boolean isObstacle()
    {
        return IsObstacle;
    }

    /**
     * @param isObstacle bool which sets the cell to an obstacle or not
     * if @param isObstacle is true, the cell is Obstacle
     * otherwise false and the cell is not an Obstacle
     */
    public void SetIsObstacle(boolean isObstacle)
    {
        this.IsObstacle = isObstacle;
    }

    /**
     * @param cell the cell to be placed in the maze
     *        updates the this.cell variable
     */
    public void placeCell(Rectangle cell)
    {
        this.cell = cell;
    }

     /**
      * @return return the current cell
      */
    public Rectangle getCell()
    {
        return cell;
    }

     /**
      * @return return true if the cell has been visited in the search
      */
    public boolean isVisited()
    {
        return isVisited;
    }

     /**
      * @param o this cell
      * @return 0
      *  this method is to keep the compiler happy
      *  as it needs this method to implement the parent class
      */
     @Override
     public int compareTo(Cell o)
     {
         return 0;
     }
 }
