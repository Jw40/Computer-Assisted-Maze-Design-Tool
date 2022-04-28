package Controller;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains Maze object properties and methods to create a maze. Initialises the maze currently takes place in the mainGUI class, which draw method is called in MazePanel class
 *
 */
public class Maze implements IMaze{


    /**
     *  maze square size
     */
    //class properties
    public static final int CELL_WIDTH = 20;

    /**
     * buffer between window edge and maze
     */
    public static final int MARGIN = 50;

    /**
     * size of maze solution dot
     */
    public static final int DOT_SIZE = 10;

    /**
     * space between wall and dot
     */
    public static final int DOT_MARGIN = 5;
    private int N;
    /**
     * array containing all the cells in the maze
     */
    private Cell[] cells;
    /**
     * array representing the unique path solution
     */
    public boolean[] path;

    final int NORTH = 0 ;
    final int SOUTH = 1 ;
    final int EAST = 2 ;
    final int WEST = 3 ;

    /**
     * Difficulty or "size" of a Kids maze (not yet implemented)
     */
//    public enum Difficulty{ Child, Easy, Intermediate, Hard};

    private String author;
    private String mazeName;
    private LocalDate dateCreated;
    private int mazeSizeX;
    private int mazeSizeY;
   // private Difficulty difficulty;
    private Logo adultLogo;

    /**
     * Used to create a maze object
     * @param size size of maze
     * @param mazeName name of maze
     * @param author author of maze
     * @param dateCreated creation date of maze
     */
    //constructor for the maze object
    public Maze(int size, String mazeName, String author, LocalDate dateCreated)//, Difficulty difficulty)
    {

        this.N = size;
        this.mazeName = mazeName;
        this.author = author;
        this.dateCreated = dateCreated;
        this.adultLogo = adultLogo;

        //this.difficulty = difficulty ;

        cells = new Cell[N * N]; // creates array of Cells

        for (int i = 0; i < N * N; i++) // initializes array with Cell objects
        {
            cells[i] = new Cell();
        }

        if(N > 0)
        {
            makeWalls(); // updates wall information inside each Cell object
            clearWalls(); // destoys wall until a maze is formed

            path = new boolean[N * N];
        }
    }

    /**
     *
     * @return the size of a maze to the maze solver class
     */
    public int getMazeSize()
    {
        return N;
    }

    /**
     *
     * @return the Cell array to the maze solver class
     */
    public Cell[] getMazeCells()
    {
        return cells;
    }

    /**
     * return path to MazeSolver
     */
    public boolean[] getPath()
    {
        return path;
    }



    /**
     *
     * @param g to draw the maze, used in the MazePanel class
     */
    //this is what draws the maze, called in the MazePanel GUI
    @Override
    public void draw(Graphics g) // draws a maze
   {
       g.setColor(Color.BLACK);
       for (int i = 0; i < N; i++)
       {
           int count = i;
           for (int j = 0; j < N; j++)
           {
               if (j != 0) {
                   count += N;
               }

               if (cells[count].walls[NORTH] != N * N) // if there exists a wall to the north
                {
                    g.drawLine((i * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN),
                            ((i + 1) * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN));
                }

               if (cells[count].walls[SOUTH] != N * N) // if there exists a wall to the
                    // south
                {
                    g.drawLine(i * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                                + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                                + MARGIN);
                }

               if (cells[count].walls[EAST] != N * N) // if there exists a wall to the
                    // east
                {
                    g.drawLine((i + 1) * CELL_WIDTH + MARGIN, j * CELL_WIDTH
                                + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                                + MARGIN);
                }

                if (cells[count].walls[WEST] != N * N) // if there exists a wall to the
                    // west
                {
                        g.drawLine(i * CELL_WIDTH + MARGIN, j * CELL_WIDTH + MARGIN, i
                                * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH + MARGIN);
                }
           }
       }
       g.setColor(Color.RED); // changes color to draw the dots
       for (int i = 0; i < N; i++)
       {
           int count = i;
           for (int j = 0; j < N; j++)
           {
               if (j != 0)
               {
                   count += N;
               }

               if (path[count] == true) // if cell is part of the path
               {
                   g.fillOval(i * CELL_WIDTH + MARGIN + DOT_MARGIN, j * CELL_WIDTH
                           + MARGIN + DOT_MARGIN, DOT_SIZE, DOT_SIZE); // paint a red
                   // circle in the
                   // cell

               }
           }
       }
   }

    /**
     * used to make the walls of a maze in a grid like fashion
     */
   //this is used to create all cells in the maze aka the walls roofs and floors
    @Override
    public void makeWalls() // fills wall information in Cells, -1 represents a
    // border wall
    {
        for (int i = 0; i < N * N; i++) // set north,south,east,west walls
        {
            cells[i].walls[NORTH] = i - N;
            cells[i].walls[SOUTH] = i + N;
            cells[i].walls[EAST] = i + 1;
            cells[i].walls[WEST] = i - 1;
        }

        for (int i = 0; i < N; i++)
        {
            cells[i].walls[NORTH] = -1; // set in border north cells, north wall to -1
            cells[N * N - i - 1].walls[SOUTH] = -1; // set in border south cells, south
            // wall to -1
        }
        for (int i = 0; i < N * N; i += N)
        {
            cells[N * N - i - 1].walls[EAST] = -1; // set in border east cells, east
            // wall to -1
            cells[i].walls[WEST] = -1; // set in border west cells, west wall to -1
        }
    }

    /**
     * used after make walls to clear random walls in the maze
     */
    @Override
    public void clearWalls() // destroys walls with a modified version of
    // Kruskal's algorithm
    {
        int NumElements = N * N;

        DisjSets ds = new DisjSets(NumElements); // creates a disjoint set to
        // represent cells
        for (int k = 0; k < N * N; k++)
        {
            ds.find(k); // adds each cell to a single set
        }

        Random generator = new Random();
        while (ds.allConnected() == false) // while not all the elements in the
        // set are connected
        {
            int cell1 = generator.nextInt(N * N); // pick a random cell
            int wall = generator.nextInt(4);

            int cell2 = cells[cell1].walls[wall]; // pick a second random cell

            if (cell2 != -1 && cell2 != N * N) // if there exists a wall between
            // these two cells
            {
                if (ds.find(cell1) != ds.find(cell2)) // if cells do not belong to
                // the same set
                {
                    cells[cell1].walls[wall] = N * N; // destroy the wall between
                    // these two cells. N*N will
                    // represent no wall

                    if (wall == NORTH || wall == EAST)
                    {
                        cells[cell2].walls[wall + 1] = N * N;
                    }
                    if (wall == SOUTH || wall == WEST)
                    {
                        cells[cell2].walls[wall - 1] = N * N;
                    }

                    ds.union(ds.find(cell1), ds.find(cell2)); // make a union of the
                    // set of these two cells, through which a path has just been
                    // created
                }
            }
        }
    }

    /**
     * set window size demension for the maze panel
     * @return the ideal size of the window (for ScrollPanes)
     *
     */

    @Override
    public Dimension windowSize() // returns the ideal size of the window (for
    // JScrollPanes)
    {
        return new Dimension(N * CELL_WIDTH + MARGIN * 2, N * CELL_WIDTH + MARGIN
                * 2);
    }

    /**
     *
     * draw a new maze template
     */
    public void drawMazeTemplate()
    {
        return;
    }

    /**
     *
     * return the current maze
     */
    //not sure if this is needed here because we dont create the maze object here we crate it in the mainGUI
    public void getMaze()
    {
        return;
    }

    /**
     *
     * @return string to console of new maze object (used for debugging)
     */
    //can be used for debugging i guess
    public String toString() {//overriding the toString() method
        return "Controller.Maze Name: " + mazeName + " Author: " + author + " Date Created: " + dateCreated.toString() + " Size: " + N;
    }
}
