package Controller;

import java.awt.*;

public interface IMaze
{
    int  getMazeSize();

    Cell[] getMazeCells();

    void draw(Graphics g);

    void makeWalls();

    void clearWalls();

    Dimension windowSize();

    void getMaze(Maze maze);

    String toString();


    void drawMazeTemplate();


    boolean[] getPath();
}
