package Controller;

import java.time.LocalDate;


public class KidsMaze implements IMaze {

    //need to add getters and setter here

    public enum Difficulty{ Easy, Intermediate };

    private String author;
    private String mazeName;
    private LocalDate dateCreated;
    private int mazeSizeX;
    private int mazeSizeY;
    private KidsMaze.Difficulty difficulty;
    private Logo kidsLogo;


    public KidsMaze(String mazeName, String author, LocalDate dateCreated, int mazeSizeX, int mazeSizeY, Difficulty difficulty) {
        this.mazeName = mazeName;
        this.author = author;
        this.dateCreated = dateCreated;
        this.mazeSizeX = mazeSizeX;
        this.mazeSizeY = mazeSizeY;
        this.difficulty = difficulty;
    }

    public void drawMaze()
    {
        //draw kids maze
        return;
    }

    public void drawMazeTemplate()
    {
        //draw kids maze template
        return;
    }

    public Boolean searchMaze(Maze maze)
    {
        return true;

    }
    public void addMaze()
    {
        return;
    }
    public void getMaze(Maze maze)
    {
        return;
    }

    public void editMaze(Maze maze)
    {
        return;

    }

    public void exportMaze()
    {
        return;

    }
}
