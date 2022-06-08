package Testing;

import Controller.*;
import View.GUI;

import javax.swing.text.View;
import java.awt.*;

public class UnitTests {
    // Checks if the maze object has been saved.

    public GUI gui;

    {
        try {
            gui = new GUI();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    public Maze maze = new Maze();

  
    Maze maze1 = new Maze();
    Maze maze2 = new Maze(14,6);
    Maze maze3 = new Maze(50,50);
    Maze maze4 = new Maze(100,100);
    Maze maze5 = new Maze(500,500);
    Maze maze6 = new Maze(0,0);





    //we actaully need to put unit tests in here and keep them
    //i wrote a few before but now they are gone



}
