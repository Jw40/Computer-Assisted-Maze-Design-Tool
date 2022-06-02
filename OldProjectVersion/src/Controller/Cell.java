package Controller;

/**
 *
 * Represents the cell in a maze with an array representing North South East West of each wall as well as a variable for each one visited
 */
public class Cell // Class representing a cell in a maze.
{
    int[] cellWalls;
    int visitedBy;
    /**
     * Constructor for Cell, create new walls array
     */
    public Cell()
    {
        cellWalls = new int[4];
        visitedBy = -1;
    }
}
