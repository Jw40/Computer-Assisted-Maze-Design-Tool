package Controller;

import java.awt.*;
import java.time.LocalDate;
import java.util.Random;


/**
 * Certain elements of the Adult maze will be different to the kids maze, this includes size, difficulty and what techniques are used to indicate the start and end of the maze.
 * Therefore this class will manage those elements and provide a maze object that will be suited for children
 */
public class KidsMaze implements IMaze {

    /**
     * maze square size
     */
    private static final int CELL_WIDTH = 20;

    public static final int cell_width(){
        return CELL_WIDTH;
    }
    /**
     * buffer between window edge and maze
     */
    private static final int MARGIN = 50;
    /**
     * size of maze solution dot
     */
    private static final int DOT_SIZE = 10;
    /**
     * space between wall and dot
     */
    private static final int DOT_MARGIN = 5;
    private int N;
    /**
     * array containing all the cells in the maze
     */
    private Cell[] cells;
    /**
     *  array representing the unique path solution
     */
    public boolean[] path;

    final int NORTH = 0 ;
    final int SOUTH = 1 ;
    final int EAST = 2 ;
    final int WEST = 3 ;

    /**
     * Difficulty or "size" of a Kids maze (not yet implemented)
     */
//    public enum Difficulty{ Easy, Intermediate };

    private String author;
    private String mazeName;
    private LocalDate dateCreated;
    private int mazeSizeX;
    private int mazeSizeY;
    //private KidsMaze.Difficulty difficulty;
    private Logo kidsLogo;


    /**
     * Used to create a KidsMaze object
     * @param mazeName name of maze
     * @param author author of maze
     * @param dateCreated date created
     * @param size size of maze
     *
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    public KidsMaze(String mazeName, String author, LocalDate dateCreated, int size)
    {
        this.N = size;
        this.mazeName = mazeName;
        this.author = author;
        this.dateCreated = dateCreated;

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
     * @return the size of a kidsmaze to the maze solver class
     */
    @Override
    public int getMazeSize() {
        return this.N;
    }

    /**
     *
     * @return the Cell array to the maze solver class
     */
    @Override
    public Cell[] getMazeCells() {
        return new Cell[0];
    }



    /**
     * @param g to draw the maze, used in the MazePanel class
     *
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
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

                if (cells[count].cellWalls[NORTH] != N * N) // if there exists a wall to the north
                {
                    g.drawLine((i * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN),
                            ((i + 1) * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN));
                }

                if (cells[count].cellWalls[SOUTH] != N * N) // if there exists a wall to the
                // south
                {
                    g.drawLine(i * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN);
                }

                if (cells[count].cellWalls[EAST] != N * N) // if there exists a wall to the
                // east
                {
                    g.drawLine((i + 1) * CELL_WIDTH + MARGIN, j * CELL_WIDTH
                            + MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
                            + MARGIN);
                }

                if (cells[count].cellWalls[WEST] != N * N) // if there exists a wall to the
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
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    //this is used to create all cells in the maze aka the walls roofs and floors
    public void makeWalls() // fills wall information in Cells, -1 represents a
    // border wall
    {
        for (int i = 0; i < N * N; i++) // set north,south,east,west walls
        {
            cells[i].cellWalls[NORTH] = i - N;
            cells[i].cellWalls[SOUTH] = i + N;
            cells[i].cellWalls[EAST] = i + 1;
            cells[i].cellWalls[WEST] = i - 1;
        }

        for (int i = 0; i < N; i++)
        {
            cells[i].cellWalls[NORTH] = -1; // set in border north cells, north wall to -1
            cells[N * N - i - 1].cellWalls[SOUTH] = -1; // set in border south cells, south
            // wall to -1
        }
        for (int i = 0; i < N * N; i += N)
        {
            cells[N * N - i - 1].cellWalls[EAST] = -1; // set in border east cells, east
            // wall to -1
            cells[i].cellWalls[WEST] = -1; // set in border west cells, west wall to -1
        }
    }

    /**
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    //this used to randomly knock down some of the walls in the maze
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

            int cell2 = cells[cell1].cellWalls[wall]; // pick a second random cell

            if (cell2 != -1 && cell2 != N * N) // if there exists a wall between
            // these two cells
            {
                if (ds.find(cell1) != ds.find(cell2)) // if cells do not belong to
                // the same set
                {
                    cells[cell1].cellWalls[wall] = N * N; // destroy the wall between
                    // these two cells. N*N will
                    // represent no wall

                    if (wall == NORTH || wall == EAST)
                    {
                        cells[cell2].cellWalls[wall + 1] = N * N;
                    }
                    if (wall == SOUTH || wall == WEST)
                    {
                        cells[cell2].cellWalls[wall - 1] = N * N;
                    }

                    ds.union(ds.find(cell1), ds.find(cell2)); // make a union of the
                    // set of these two cells, through which a path has just been
                    // created
                }
            }
        }
    }

    //set window size demension for the maze panel
    public Dimension windowSize() // returns the ideal size of the window (for
    // JScrollPanes)
    {
        return new Dimension(N * CELL_WIDTH + MARGIN * 2, N * CELL_WIDTH + MARGIN
                * 2);
    }

    public void drawMazeTemplate()
    {
        return;
    }

    @Override
    public boolean[] getPath() {
        return new boolean[0];
    }


    public void getMaze()
    {
        return;
    }


    public String toString() {//overriding the toString() method
        return "Controller.Maze Name: " + mazeName + " Author: " + author + " Date Created: " + dateCreated.toString() + " Size: " + N;
    }







}
