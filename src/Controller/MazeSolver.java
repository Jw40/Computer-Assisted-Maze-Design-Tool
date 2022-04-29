package Controller;

/**
 * Contains methods that will allow the user to derive a solution (if one exists) for the maze they create and display the path form them.
 */
public class MazeSolver {

    final int NORTH = 0 ;
    final int SOUTH = 1 ;
    final int EAST = 2 ;
    final int WEST = 3 ;

    /**
     * used to create the solution path and is called in the main GUI to add the current solution to the maze object
     * @param thisMaze IMaze object
     *
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     */
    public void createMazePath(IMaze thisMaze) // finds a path in the maze
    {
        int mazeSize = thisMaze.getMazeSize();
        if( mazeSize != 1) //if maze is not of size 1
        {
            depthSearch(0, thisMaze); // executes a first breath search starting on the top
            // left cell
            thisMaze.getPath()[0] = true; // path starts on top left cell
            thisMaze.getPath()[mazeSize * mazeSize - 1] = true; // path ends on bottom right cell
            int current = thisMaze.getMazeCells()[mazeSize * mazeSize - 1].visitedBy; // start on the last, bottom
            // right cell
            while (current != 0) // follows the path back to the starting cell
            {
                thisMaze.getPath()[current] = true;
                current = thisMaze.getMazeCells()[current].visitedBy;
            }
        }
        else // if maze is of size 1
        {
            thisMaze.getPath()[0] = true ;
        }
        thisMaze.getMazeCells()[0].cellWalls[WEST] = mazeSize * mazeSize; // destroys west wall on top left cell
        thisMaze.getMazeCells()[mazeSize * mazeSize - 1].cellWalls[EAST] = mazeSize * mazeSize; // destroys east wall on bottom right
        // cell
    }


    /**
     * this is a helped function for createPath
     * @param cell int
     * @param thisMaze maze object
     *
     *Disclaimer - This piece of source code was referenced:
     *                 Author: irealva
     *                 Date: 4/9/2014
     *                 Title of program: maze-gui
     *                 Code version: unknown
     *                 Type: Source code
     *                 Web address: https://github.com/irealva/maze-gui
     *
     */
    public void depthSearch(int cell, IMaze thisMaze) // executes a first breath search to find
    // a path in the maze
    {
        int N = thisMaze.getMazeSize();
        Cell startCell = thisMaze.getMazeCells()[cell]; // current cell being checked

        for (int i = 0; i < 4; i++) // check if there is a path north, south,
        // east, or west
        {
            int adjacent = -1;

            if (startCell.cellWalls[i] == N * N) // if there is no wall in north,
            // south, east or west direction
            {
                if (i == NORTH)
                {
                    adjacent = cell - N;
                }
                if (i == SOUTH)
                {
                    adjacent = cell + N;
                }
                if (i == EAST)
                {
                    adjacent = cell + 1;
                }
                if (i == WEST)
                {
                    adjacent = cell - 1;
                }

                if (thisMaze.getMazeCells()[adjacent].visitedBy == -1)
                {
                    thisMaze.getMazeCells()[adjacent].visitedBy = cell; // update information to
                    // store which cell has visited this one
                    depthSearch(adjacent, thisMaze);
                }
            }
        }
    }

    /**
     * Used to display the solution mertrics on the screen (not yet implemented)
     */
    public void dispalySolutionMetrics()
    {
        return;
    }

    /**
     * Used to display Solution calculation on the screen (not yet implemented)
     */
    public void displaySolutionCalc()
    {
        return;
    }

    /**
     * Used to toggle the Solution path on the screen (not yet implemented)
     */
    public void toggleSolutionPath()
    {
        return;
    }
}
