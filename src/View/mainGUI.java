package View;

import Controller.KidsMaze;

import javax.swing.*;
import java.awt.*;

import javax.swing.JFileChooser;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
        JMenu file, autogen, edit, database;
        JMenuItem open, save, saveAs, mazePref, export, exit, about, createNew;

        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        autogen = new JMenu("Auto Generate");
        createNew = new JMenuItem("Create New");
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
        mazeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    }

    public void createNew() {
        JFrame createNew = new JFrame("Create New Maze");
        createNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(createNew);
        createNew.setSize(800, 600);
        createNew.setLocationRelativeTo(null);
        createNew.setVisible(true);

    }

    public static void createUI(final JFrame frame) {
        JPanel createNew = new JPanel();
        createNew.setLayout(new BorderLayout());

        //Title - North
        //Replace Maze placeholder text with maze name
        JPanel mazetitle = new JPanel();
        JLabel titlelabel = new JLabel("Retrieve Maze Title and place here");
        mazetitle.add(titlelabel);
        GridBagConstraints c = new GridBagConstraints();

        //Body - ACTUAL MAZE - Center
        //Maze Render goes here
        JPanel mazebody = new JPanel();
        JLabel bodyplaceholder = new JLabel("Body Placeholder here");
        mazebody.add(bodyplaceholder);

        //Editor - East
        JPanel mazeeditor = new JPanel();
        JLabel editlabel = new JLabel("Maze Editor");
        mazeeditor.add(editlabel, BorderLayout.NORTH);

        //Preferences - West
        //TODO - Gridbagconstants can be declared when called in the "add" function. Does not need new definition before each element
        JPanel mazepreferences = new JPanel();
        mazepreferences.setLayout(new GridBagLayout());
        JLabel preflabel = new JLabel("Maze Preferences");
        c.gridwidth = GridBagConstraints.REMAINDER;
        mazepreferences.add(preflabel, c);
        c.gridwidth = GridBagConstraints.BOTH;
        c.weightx = 1.0;

        //Maze Name
        JLabel namelabel = new JLabel("Maze Name:");
        mazepreferences.add(namelabel, c);
        JTextField mazename = new JTextField("", 10);
        c.gridwidth = GridBagConstraints.REMAINDER;
        mazepreferences.add(mazename, c);

        //Maze Size
        JLabel sizelabel = new JLabel("Maze Size:");
        c.gridwidth = GridBagConstraints.BOTH;
        mazepreferences.add(sizelabel, c);
        JTextField mazeheight = new JTextField("", 3);
        mazepreferences.add(mazeheight, c);
        JLabel xlabel = new JLabel("x");
        mazepreferences.add(xlabel, c);
        JTextField mazewidth = new JTextField("", 3);
        mazepreferences.add(mazewidth, c);
        JLabel cellslabel = new JLabel("cells");
        c.gridwidth = GridBagConstraints.REMAINDER;
        mazepreferences.add(cellslabel, c);

        //Difficulty
        JLabel difficulty = new JLabel("Maze Difficulty:");
        c.gridwidth = GridBagConstraints.REMAINDER;
        mazepreferences.add(difficulty, c);
        JSlider difficultyslider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        c.gridwidth = GridBagConstraints.REMAINDER;
        mazepreferences.add(difficultyslider, c);

        //Kids Maze Button
        JToggleButton kidsmazebutton = new JToggleButton("Kids Maze");
        mazepreferences.add(kidsmazebutton, c);
        ItemListener kidslistener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                System.out.println("Kids Maze Selected");
            } else {
                System.out.println("Adults Maze Selected");
            }
        };
        kidsmazebutton.addItemListener(kidslistener);

        //Solve Button
        JToggleButton solvebutton = new JToggleButton("Solve");
        mazepreferences.add(solvebutton, c);
        ItemListener solveListener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                System.out.println("Route Displayed");
            } else {
                System.out.println("Route Removed");
            }
        };
        solvebutton.addItemListener(solveListener);

        //Construct ENTIRE
        createNew.add(mazetitle, BorderLayout.NORTH);
        createNew.add(mazebody, BorderLayout.CENTER);
        createNew.add(mazeeditor, BorderLayout.EAST);
        createNew.add(mazepreferences, BorderLayout.WEST);
        frame.getContentPane().add(createNew, BorderLayout.CENTER);
    }

    public void editor() {

    }

    public void database() {

    }

    public void about() {
        //Save As
        JOptionPane.showMessageDialog(null, "This CAB302 Major assessment was created by Group 5:  \nSam Pappalardo - N10469729 \nDeclan Roache - N10885081 \nYork Wang - N10867333 \nJames Wyatt - N8040478", "About", JOptionPane.PLAIN_MESSAGE);
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
