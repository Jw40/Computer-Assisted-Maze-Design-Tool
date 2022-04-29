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

    /**
     * variable to store the Jframe for maze displayfg
     */
    JFrame mazeWindow = new JFrame("Maze Generator");

    /**
     * Used to open a file in the GUI
     */
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

    /**
     * Used to create the drop down menu in the GUI
     */
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

    /**
     * Used to create new GUI
     */
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

    /**
     * this may be redundant
     */
    public mainGUI()
    {
        createNewWindow();

        //write GUI code here
    }



    /**
     *
     * Used to create a mainGUI object, still under implementation
     */
    /*
    public mainGUI() {
        //this creates a new maze object
        int mazeSize = Integer.valueOf(10);
        IMaze maze = new Maze(mazeSize, "MyFirstMaze", "Johnny Smith", LocalDate.now()); // Constructs the maze object
        try {
            //this creates the soultion path
            MazeSolver Solver = new MazeSolver();
            Solver.createPath(maze);
            JFrame mainJframe = new JFrame("Maze");
            MazePanel mazePanel = new MazePanel(maze); // Constructs the mazePanel to hold the
            // maze
            JScrollPane scrollPane = new JScrollPane(mazePanel);
            mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainJframe.setSize(1000, 800);
            mainJframe.add(scrollPane, BorderLayout.CENTER);
            mainJframe.setVisible(true);
        //print maze details to console
        System.out.println(maze.toString());
    }
    */
}
