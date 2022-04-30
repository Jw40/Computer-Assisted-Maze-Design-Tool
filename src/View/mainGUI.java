package View;

import javax.swing.*;
import java.awt.*;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


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
    public void OpenFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            try {
                JPanel panel = new JPanel();
                panel.setBounds(400, 50, 1000, 1400);
                BufferedImage img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                JLabel pic = new JLabel(new ImageIcon(img));
                panel.add(pic);
                mazeWindow.add(panel);
                mazeWindow.setSize(400, 400);
                mazeWindow.setLayout(null);
                mazeWindow.setVisible(true);


            } catch (IOException ignored) {
            }

        }

    }

    /**
     * Used to create the drop down menu in the GUI
     */
    public void createDropDownMenu() {
        JMenu file, autogen, createNew, edit, database;
        JMenuItem open, save, saveAs, mazePref, export, exit, about;

        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        autogen = new JMenu("Auto Generate");
        createNew = new JMenu("Create New");
        edit = new JMenu("Editor");
        database = new JMenu("Database");

        about = new JMenuItem("About");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        mazePref = new JMenuItem("Maze Preferences");
        export = new JMenuItem("Export Maze");
        exit = new JMenuItem("Exit");

        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(mazePref);
        file.add(export);
        file.add(exit);

        mb.add(file);
        mb.add(autogen);
        mb.add(createNew);
        mb.add(edit);
        mb.add(database);
        mb.add(about);

        mazeWindow.setJMenuBar(mb);
        mazeWindow.setSize(400, 400);
        mazeWindow.setLayout(null);
        mazeWindow.setVisible(true);

        open.addActionListener(e -> OpenFileChooser());
        saveAs.addActionListener(e -> {
            try {
                saveAs();
            } catch (AWTException ex) {
                throw new RuntimeException(ex);
            }
        });
        save.addActionListener(e -> {
            try {
                save();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        mazePref.addActionListener(e -> mazePreferences());
        export.addActionListener(e -> export());
        autogen.addActionListener(e -> autoGenerate());
        createNew.addActionListener(e -> createNew());
        edit.addActionListener(e -> editor());
        about.addActionListener(e -> about());
        exit.addActionListener(e -> System.exit(0));
    }

    /**
     * Used to create new GUI
     */
    public void createNewWindow() {
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

    public void saveAs() throws AWTException {
        // This saves in a directory of choice using a file chooser.
        // TODO - Change mazewindow to jpanel of the maze when it's implimented
        Point p = mazeWindow.getLocationOnScreen();
        Dimension dim = mazeWindow.getSize();
        Rectangle rect = new Rectangle(p, dim);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(rect);
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setDialogTitle("Choose a location to save maze");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(image, "jpg", new File(fileToSave.getAbsolutePath() + ".jpg"));
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void captureScreen(String fileName) throws Exception {
        // TODO - Change mazewindow to jpanel of the maze when it's implimented
        Point p = mazeWindow.getLocationOnScreen();
        Dimension dim = mazeWindow.getSize();
        Rectangle rect = new Rectangle(p, dim);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(rect);
        ImageIO.write(image, "jpg", new File(fileName + ".jpg"));
    }

    public void save() throws Exception {
        // This saves in the Project directory, as a JPG, with the filename as the current absolute time.
        String timestamp = "" + System.currentTimeMillis();
        captureScreen(timestamp);
    }

    public void mazePreferences() {
        //Preferences
    }

    public void export() {
        //Export
    }

    public void autoGenerate() {
        //Save As
    }

    public void createNew() {
        //Save As
    }

    public void editor() {
        //Save As
    }

    public void database() {

        //Save As
    }

    public void about() {
        //Save As

        JOptionPane.showMessageDialog(null, """
        This CAB302 Major assessment was created by Group 5: 
        Sam Pappalardo - N10469729
        Declan Roache - N10885081
        York Wang - N10867333
        James Wyatt - N8040478""","About" , JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * this may be redundant
     */

    public mainGUI() {
        createNewWindow();

        //write GUI code here
    }



    /*
    public mainGUI() {
        //this creates a new maze object
        int mazeSize = Integer.valueOf(10);
        IMaze maze = new Maze(mazeSize, "MyFirstMaze", "Johnny Smith", LocalDate.now()); // Constructs the maze object
        try {
            MazeSolver Solver = new MazeSolver();
            Solver.createMazePath(maze);
            JFrame frame = new JFrame("Maze");
            MazePanel panel = new MazePanel(maze);
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
