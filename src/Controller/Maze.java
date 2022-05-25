package Controller;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private static final int CELL_WIDTH = 20;

    /**
     * buffer between window edge and maze
     */
    private static final int MARGIN = 50;

    /**
     * size of maze solution dot
     */
    private static final int SOLUTION_DOT_SIZE = 10;

    /**
     * space between wall and dot
     */
    private static final int DOT_MARGIN = 5;
    private int MazeSize;
    /**
     * array containing all the cells in the maze
     */
    private Cell[] cells;
    /**
     * array representing the unique path solution
     */
    private boolean[] path;

    private final int NORTH = 0 ;
    private final int SOUTH = 1 ;
    private final int EAST = 2 ;
    private final int WEST = 3 ;

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
     *
     *Disclaimer - This piece of source code was referenced
     *  * and is used for development purposes and testing,
     *  * this code will not be used in its current state in the final submission.
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    //constructor for the maze object
    public Maze(int size, String mazeName, String author, LocalDate dateCreated)//, Difficulty difficulty)
    {

        this.MazeSize = size;
        this.mazeName = mazeName;
        this.author = author;
        this.dateCreated = dateCreated;
        this.adultLogo = adultLogo;

        //this.difficulty = difficulty ;

        cells = new Cell[MazeSize * MazeSize]; // creates array of Cells

        for (int i = 0; i < MazeSize * MazeSize; i++) // initializes array with Cell objects
        {
            cells[i] = new Cell();
        }

        if(MazeSize > 0)
        {
            makeWalls(); // updates wall information inside each Cell object
            clearWalls(); // destoys wall until a maze is formed

            path = new boolean[MazeSize * MazeSize];
        }
        //else throw exception maze size not big enough
    }

    /**
     * returns the name of this Maze object
     * @return maze name
     */
    public String getMazeName()
    {
        return this.mazeName;
    }

    /**
     * returns the author of this Maze object
     * @return the author of this Maze object
     */
    public String getAuthorName()
    {
        return this.author;
    }


    /**
     * return this datacreated of this Maze object
     * @return this datecreated of this Maze object
     */
    public LocalDate getDateCreated()
    {
        return this.dateCreated;
    }

    /**
     *
     * @return the size of a maze to the maze solver class
     */
    public int getMazeSize()
    {
        return MazeSize;
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
     *
     *Disclaimer - This piece of source code was referenced
     *  * and is used for development purposes and testing,
     *  * this code will not be used in its current state in the final submission.
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    //this is what draws the maze, called in the MazePanel GUI
    @Override
    public void draw(Graphics g) // draws a maze
    {

        int count = 0;
        g.setColor(Color.BLACK);
        for (int i = 0; i < MazeSize; i++) {
            count = i;
            for (int j = 0; j < MazeSize; j++) {
                if (j != 0) {
                    count += MazeSize;
                }

                if (cells[count].cellWalls[NORTH] != MazeSize * MazeSize) // if there exists a wall to the north
                {
                    g.drawLine((i * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN),
                            ((i + 1) * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN));
                }

                if (cells[count].cellWalls[SOUTH] != MazeSize * MazeSize) // if there exists a wall to the
                // south
                {
                    g.drawLine(i * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN);
                }

                if (cells[count].cellWalls[EAST] != MazeSize * MazeSize) // if there exists a wall to the
                // east
                {
                    g.drawLine((i + 1) * CELL_WIDTH + MARGIN, j * CELL_WIDTH
                            + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN);
                }

                if (cells[count].cellWalls[WEST] != MazeSize * MazeSize) // if there exists a wall to the
                // west
                {
                    g.drawLine(i * CELL_WIDTH + MARGIN, j * CELL_WIDTH + MARGIN, i
                            * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH + MARGIN);
                }
            }
        }
/*
       ArrayList<Integer> xPointsList = new ArrayList<Integer>();
       ArrayList<Integer> yPointsList = new ArrayList<Integer>();
       int[]xPoints = new int[count];
       int[] yPoints = new int[count];
       int totalPoints = 0;
       int nPoints;
       g.setColor(Color.RED); // changes color to draw the dots
       for (int i = 0; i < MazeSize; i++)
       {
           count = i;
           for (int j = 0; j < MazeSize; j++)
           {
               if (j != 0)
               {
                   count += MazeSize;
               }

               if (path[count] == true) // if cell is part of the path
               {
                   int x = i * CELL_WIDTH + MARGIN + DOT_MARGIN;
                   int y = j * CELL_WIDTH + MARGIN + DOT_MARGIN;
                   xPointsList.add(x);
                   yPointsList.add(y);
                   totalPoints++;


                   g.drawLine(
                           i * CELL_WIDTH + MARGIN + DOT_MARGIN, //x1 - the first point's x coordinate.
                           j * CELL_WIDTH + MARGIN + DOT_MARGIN, //y1 - the first point's y coordinate.

                           i * CELL_WIDTH + MARGIN + DOT_MARGIN * 2 ,//x2 - the second point's x coordinate.
                           j * CELL_WIDTH + MARGIN + DOT_MARGIN * 2) ;//y2 - the second point's y coordinate.

                   // paint a red
                   // circle in the
                   // cell

               }
           }

       }

       int[] xPointsArray = xPointsList.stream()
               .mapToInt(Integer::intValue)
               .toArray();

       int[] yPointsArray = yPointsList.stream()
               .mapToInt(Integer::intValue)
               .toArray();



       int[] yPointsArray = yPointsList.stream().mapToInt(i -> i).toArray();
       int[] xPointsArray = xPointsList.stream().mapToInt(i -> i).toArray();


       for(int i = 1; i < yPointsList.size(); i++)
       {
           g.drawLine(xPointsArray[i-1], yPointsArray[i-1], xPointsArray[i],yPointsArray[i]);
       }

*/
        //g.drawPolyline(yPointsArray, xPointsArray, xPointsArray.length );
        DrawSolution(g);

    }

    public void DrawSolution(Graphics g){
        if (g != null) {
            int count = 0;
            g.setColor(Color.RED); // changes color to draw the dots
            for (int i = 0; i < MazeSize; i++) {
                count = i;
                for (int j = 0; j < MazeSize; j++) {
                    if (j != 0) {
                        count += MazeSize;
                    }
                    boolean checker = false;
                    if (path[count] == true) // if cell is part of the path
                    {
                        g.fill3DRect(i * CELL_WIDTH + MARGIN + DOT_MARGIN, j * CELL_WIDTH
                                + MARGIN + DOT_MARGIN, 10, 10, true);
                    }
                }
            }
        }
    }
    /**
     * used to make the walls of a maze in a grid like fashion
     *
     *Disclaimer - This piece of source code was referenced
     *  * and is used for development purposes and testing,
     *  * this code will not be used in its current state in the final submission.
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
   //this is used to create all cells in the maze aka the walls roofs and floors
    @Override
    public void makeWalls() // fills wall information in Cells, -1 represents a
    // border wall
    {
        for (int i = 0; i < MazeSize * MazeSize; i++) // set north,south,east,west walls
        {
            cells[i].cellWalls[NORTH] = i - MazeSize;
            cells[i].cellWalls[SOUTH] = i + MazeSize;
            cells[i].cellWalls[EAST] = i + 1;
            cells[i].cellWalls[WEST] = i - 1;
        }

        for (int i = 0; i < MazeSize; i++)
        {
            cells[i].cellWalls[NORTH] = -1; // set in border north cells, north wall to -1
            cells[MazeSize * MazeSize - i - 1].cellWalls[SOUTH] = -1; // set in border south cells, south
            // wall to -1
        }
        for (int i = 0; i < MazeSize * MazeSize; i += MazeSize)
        {
            cells[MazeSize * MazeSize - i - 1].cellWalls[EAST] = -1; // set in border east cells, east
            // wall to -1
            cells[i].cellWalls[WEST] = -1; // set in border west cells, west wall to -1
        }
    }

    /**
     * used after make walls to clear random walls in the maze
     *
     *Disclaimer - This piece of source code was referenced
     *  * and is used for development purposes and testing,
     *  * this code will not be used in its current state in the final submission.
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    @Override
    public void clearWalls() // destroys walls with a modified version of
    // Kruskal's algorithm
    {
        int NumElements = MazeSize * MazeSize;

        DisjSets ds = new DisjSets(NumElements); // creates a disjoint set to
        // represent cells
        for (int k = 0; k < MazeSize * MazeSize; k++)
        {
            ds.find(k); // adds each cell to a single set
        }

        Random generator = new Random();
        while (ds.allConnected() == false) // while not all the elements in the
        // set are connected
        {
            int cell1 = generator.nextInt(MazeSize * MazeSize); // pick a random cell
            int wall = generator.nextInt(4);

            int cell2 = cells[cell1].cellWalls[wall]; // pick a second random cell

            if (cell2 != -1 && cell2 != MazeSize * MazeSize) // if there exists a wall between
            // these two cells
            {
                if (ds.find(cell1) != ds.find(cell2)) // if cells do not belong to
                // the same set
                {
                    cells[cell1].cellWalls[wall] = MazeSize * MazeSize; // destroy the wall between
                    // these two cells. N*N will
                    // represent no wall

                    if (wall == NORTH || wall == EAST)
                    {
                        cells[cell2].cellWalls[wall + 1] = MazeSize * MazeSize;
                    }
                    if (wall == SOUTH || wall == WEST)
                    {
                        cells[cell2].cellWalls[wall - 1] = MazeSize * MazeSize;
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
        return new Dimension(MazeSize * CELL_WIDTH + MARGIN * 2, MazeSize * CELL_WIDTH + MARGIN
                * 2);
    }

    /**
     *
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
        return "Controller.Maze Name: " + mazeName + " Author: " + author + " Date Created: " + dateCreated.toString() + " Size: " + MazeSize;
    }
}
