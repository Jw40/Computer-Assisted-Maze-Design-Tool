package Controller;

public interface IMaze
{
    public void drawMaze();

    public void drawMazeTemplate();

    public Boolean searchMaze(Maze maze);

    public void addMaze();

    public void getMaze(Maze maze);


    public void editMaze(Maze maze);


    public void exportMaze();
}
