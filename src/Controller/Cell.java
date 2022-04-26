package Controller;

public class Cell // Class representing a cell in a maze.
{
    int[] walls; // array representing north, south, east, west walls
    int visitedBy; // for running first breath search, saves the cell that
    // visited this cell

    public Cell()
    {
        walls = new int[4];
        visitedBy = -1;
    }
}
