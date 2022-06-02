package View;

import Controller.IMaze;
import Controller.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class CreateGUI extends Component {
    public void createNew() {
        JFrame createNew = new JFrame("Create New Maze");
        createNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI();
        createNew.setSize(800, 600);
        createNew.setLocationRelativeTo(null);
        createNew.setVisible(true);
    }
private JPanel mazebody;

    public static void createUI() {
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
        mazebody.add(createNew, BorderLayout.CENTER);
    }

    public void autoGenerate() {
        //this creates a new maze object
        int mazeSize = Integer.valueOf(10);
        IMaze maze = new Maze(mazeSize, "MyFirstMaze", "Johnny Smith", LocalDate.now()); // Constructs the maze object
        try {

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
    public void export() throws AWTException {
        // This saves in a directory of choice using a file chooser.
        // TODO - Change mazewindow to jpanel of the maze when it's implimented
        Point p = mazebody.getLocationOnScreen();
        Dimension dim = mazebody.getSize();
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
            Point p = mazebody.getLocationOnScreen();
            Dimension dim = mazebody.getSize();
            Rectangle rect = new Rectangle(p, dim);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(rect);
            ImageIO.write(image, "jpg", new File(fileName + ".jpg"));
        }

    }

