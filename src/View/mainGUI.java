package View;

import Controller.IMaze;
import Controller.Maze;
import Controller.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class mainGUI {

    //we can have a main method in here to start the application I think
    //the GUI object will be called in that main method and then the application will run.


    public mainGUI() {
        //this creates a new maze object
        int size = Integer.valueOf(10);
        IMaze maze = new Maze(size, "MyFirstMaze", "Johnny Smith", LocalDate.now()); // Constructs the maze object
        try {




            //this creates the soultion path
            MazeSolver Solver = new MazeSolver();
            Solver.createPath(maze);


            JFrame frame = new JFrame("Maze");
            MazePanel panel = new MazePanel(maze); // Constructs the panel to hold the
            // maze
            JScrollPane scrollPane = new JScrollPane(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        } catch (NumberFormatException exception) {
            System.out.println("The input number for the maze size must be an integer");
        }

        //print maze details to console
        System.out.println(maze.toString());
    }
}
