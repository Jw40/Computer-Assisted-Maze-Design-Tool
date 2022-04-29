package View;

import Controller.IMaze;
import Controller.Maze;
import Controller.MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.time.LocalDate;

/**
 * Responsible for displaying the GUI elements that the user will be interacting with inclusive of buttons, panels, and search/data insertion elements.
 * This will also be responsible for the general appearance and apply to the user-friendliness of the project
 */
public class mainGUI extends Component {

    JFrame mazeWindow = new JFrame("Maze Generator");

    //we can have a main method in here to start the application I think
    //the GUI object will be called in that main method and then the application will run.

    public void OpenFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            try
            {
                JPanel panel = new JPanel();
                panel.setBounds(400, 50, 1000, 1400);
                BufferedImage img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                JLabel pic = new JLabel(new ImageIcon(img));
                panel.add(pic);
                mazeWindow.add(panel);
                mazeWindow.setSize(400, 400);
                mazeWindow.setLayout(null);
                mazeWindow.setVisible(true);


            }
            catch (IOException e) {}

        }

    }
    public void createDropDownMenu()
    {
        JMenu file, autogen, createNew, edit, database;
        JMenuItem open, save, saveAs, mazePref, export, exit;

        JMenuBar mb=new JMenuBar();
        file=new JMenu("File");
        autogen=new JMenu("Auto Generate");
        createNew=new JMenu("Create New");
        edit=new JMenu("Editor");
        database=new JMenu("Database");


        open=new JMenuItem("Open");
        save=new JMenuItem("Save");
        saveAs=new JMenuItem("Save As");
        mazePref=new JMenuItem("Maze Preferences");
        export=new JMenuItem("Export Maze");
        exit=new JMenuItem("Exit");

        file.add(open); file.add(save); file.add(saveAs);
        file.add(mazePref); file.add(export); file.add(exit);

        mb.add(file); mb.add(autogen);mb.add(createNew);mb.add(edit);mb.add(database);
        mazeWindow.setJMenuBar(mb);
        mazeWindow.setSize(400,400);
        mazeWindow.setLayout(null);
        mazeWindow.setVisible(true);


        open.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenFileChooser();
            }
        });

        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
    public void createNewWindow()
    {

        JFrame.setDefaultLookAndFeelDecorated(true);
        //Create and set up the window.
        mazeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        mazeWindow.setPreferredSize(new Dimension(300, 100));
        mazeWindow.setLocation(new Point(200, 200));
        mazeWindow.pack();
        mazeWindow.setVisible(true);
        createDropDownMenu();

    }
    public mainGUI()
    {
        createNewWindow();

        //write GUI code here
    }



    /**
     * Used to create a mainGUI object
     */
    /*
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
    */
}
