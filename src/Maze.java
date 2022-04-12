import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    public enum Difficulty{ Child, Easy, Intermediate, Hard};

    private String author;
    private String mazeName;
    private LocalDate dateCreated;
    private int mazeSizeX;
    private int mazeSizeY;
    private Difficulty difficulty;

    Maze(String mazeName, String author, LocalDate dateCreated, int mazeSizeX, int mazeSizeY, Difficulty difficulty)
    {
        this.mazeName = mazeName;
        this.author = author;
        this.dateCreated = dateCreated;
        this.mazeSizeX = mazeSizeX;
        this.mazeSizeY = mazeSizeY;
        this.difficulty = difficulty ;
    }

    public String toString() {//overriding the toString() method
        return "Maze Name: " + mazeName + " Author: " + author + " Date Created: " + dateCreated.toString() + " MazeX: " + mazeSizeX + " MazeY: " + mazeSizeY + " Difficulty: " + difficulty;
    }

    public List<Maze> GetMazes()
    {
        List<Maze> mazeList = new ArrayList<Maze>();
        return mazeList;
    }


}
