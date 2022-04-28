package Controller;

/**
 *
 * Represents the cell in a maze with an array representing North South East West of each wall as well as a variable for each one visited
 */
public class Cell // Class representing a cell in a maze.
{
    int[] walls; // array representing north, south, east, west walls
    int visitedBy; // for running first breath search, saves the cell that
    // visited this cell

    /**
     * Constructor for Cell, create new walls array and set vistedBy to -1
     */
    public Cell()
    {
        walls = new int[4];
        visitedBy = -1;
    }
}
