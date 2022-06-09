/*
 * The MIT License
 *
 * Copyright 2015 Chris Darisaplis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
    Disclaimer - This source code has been modified/referenced from
                   the following project:

                    Title of program: Laby
                    Author(s): Chris Darisaplis, Chris Samarinas
                    Date: 05/12/2015
                    Code version: 1.2
                    Type: Source Code
                    Web address: https://github.com/algoprog/Laby

 */

package View;
import Controller.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * main Gui class
 * to display to the screen
 */
public class GUI extends Component {


    private final ImageIcon startIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            "/Icons/start.png")));
    private final ImageIcon logoIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            "/Icons/logo.png")));
    private final ImageIcon goalIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            "/Icons/goal.png")));


    private final JFrame mainFrame;//main window
    /**
     * displays maze
     */
    private MazePanel mazePanel;
    private boolean saved;//maze saved
    private String directory;//save directory
    private IMaze maze;//open maze, holds static maze data
    private MazeSolver solver;//solves the open maze
    private Thread runThread;//runs to solve the maze
    private MazeGenerator generator;//generates maze
    private boolean generatorMode;//to check whether to reset on File menu click

    /**
     * Constructor, builds GUI
     */
    public GUI() {
        saved = true;
        directory = null;
        solver = null;
        generatorMode = false;

        //remove this to change the style of the GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Using Java default look&feel");
        }

        //main frame in the gui
        mainFrame = new JFrame("Maze Generator");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout(15, 10));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //create a maze base 16,16
        maze = new Maze(18, 18);
        mazePanel = new MazePanel(maze);
        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(mazePanel);

        //Menu bar start
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem newMaze = new JMenuItem("New Maze...");
        JMenuItem openMaze = new JMenuItem("Open Maze...");
        JMenuItem saveMazeAs = new JMenuItem("Save As...");
        JMenuItem saveMaze = new JMenuItem("Save");
        JMenuItem importImage = new JMenuItem("Import Logo...");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem clear = new JMenuItem("Clear Maze");

        fileMenu.add(newMaze);
        fileMenu.add(openMaze);
        fileMenu.addSeparator();
        fileMenu.add(saveMaze);
        fileMenu.add(saveMazeAs);
        fileMenu.add(importImage);
        fileMenu.addSeparator();
        fileMenu.add(clear);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        //menu bar end

        //menu bar action listeners
        saveMazeAs.addActionListener(e -> saveAs());

        saveMaze.addActionListener(e -> save());

        exit.addActionListener(e -> {
            savePrompt(mainFrame);
            if (runThread != null){
                runThread.interrupt();
            }
            mainFrame.dispose();
        });

        importImage.addActionListener(e -> ImageImporter());

        clear.addActionListener(e -> {
            mazePanel.getMaze().whitenThisMaze();
            mazePanel.repaint();
        });



        //Side panel buttons
        JButton generateNewMazeButton = new JButton("Generate New Maze");
        JButton generateNewKidsButton = new JButton("Kids Maze Template");
        JButton generateNewAdultButton = new JButton("Adult Maze Template");
        JButton solveButton = new JButton("Solve Maze");
        JButton resetButton = new JButton("Reset Solution Path");
        JButton clearButton = new JButton("Clear Maze");
        generateNewMazeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateNewMazeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        generateNewKidsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateNewKidsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        generateNewAdultButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateNewAdultButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        resetButton.setEnabled(false);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        solveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        solveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        clearButton.setEnabled(true);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField AuthorField = new JTextField("", 1);
        AuthorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel AuthorLabel = new JLabel("Author Name:");
        JTextField MazeName = new JTextField("", 1);
        MazeName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JLabel MazeLabel = new JLabel("Maze Name:");
        JTextField CreatedText = new JTextField("", 1);
        CreatedText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        CreatedText.setEnabled(false);
        CreatedText.setText(LocalDate.now().toString());
        JLabel CreatedLabel = new JLabel("Created on:");
        JButton SaveToDatabaseButton = new JButton("Save to Database");
        SaveToDatabaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        SaveToDatabaseButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JButton SaveImage = new JButton("Save Maze as image");
        SaveImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        SaveImage.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        //Solution Metrics
        JPanel randomPanel = new JPanel(new BorderLayout());
        randomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        JLabel statusLabel = new JLabel("Status: Not Started");
        statusLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel totalCellsLabel = new JLabel("Total Cells: 0");
        totalCellsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel countBlackenLabel = new JLabel("Cell Walls: 0");
        countBlackenLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel percentageTaversable = new JLabel("Maze traversable: 0");
        percentageTaversable.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel percentageTraversedOptimal = new JLabel("Traversable in solution: 0");
        percentageTraversedOptimal.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel percentageDeadEnds = new JLabel("Ending in dead ends: 0");
        percentageDeadEnds.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel solutionLabel = new JLabel("Cells visited for solution: 0");
        solutionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JPanel infoPanel = new JPanel();
        JPanel percentagePanel = new JPanel();
        percentagePanel.setLayout(new BoxLayout(percentagePanel, BoxLayout.Y_AXIS));
        JPanel stepPanel = new JPanel();
        stepPanel.setLayout(new BoxLayout(stepPanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(statusLabel);
        stepPanel.add(totalCellsLabel);
        stepPanel.add(countBlackenLabel);
        stepPanel.add(solutionLabel);
        percentagePanel.add(percentageTaversable);
        percentagePanel.add(percentageTraversedOptimal);
        percentagePanel.add(percentageDeadEnds);
        percentagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        percentagePanel.setBorder(BorderFactory.createTitledBorder(infoPanel.getBorder(),
                "Percentage"));
        stepPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        stepPanel.setBorder(BorderFactory.createTitledBorder(infoPanel.getBorder(),
                "Step"));
        stepPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        percentagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        infoPanel.add(percentagePanel);
        infoPanel.add(stepPanel);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.setBorder(BorderFactory.createTitledBorder(infoPanel.getBorder(),
                "Metrics"));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        //Edit Panel - Start Goal Logo
        JPanel editPanel = new JPanel(new GridLayout(1, 3));
        JLabel startLabel = new JLabel(startIcon);
        JLabel goalLabel = new JLabel(goalIcon);
        JLabel logoLabel = new JLabel(logoIcon);
        editPanel.add(startLabel);
        editPanel.add(goalLabel);
        editPanel.add(logoLabel);
        editPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        editPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        editPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Color.BLACK), "Set start point, end point and logo"));

        // Run Panel and Command Panel
        JPanel runPanel = new JPanel();
        runPanel.setLayout(new BoxLayout(runPanel, BoxLayout.Y_AXIS ));
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        runPanel.add(randomPanel);
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(generateNewMazeButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(generateNewAdultButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(generateNewKidsButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(clearButton);

        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(solveButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(resetButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        runPanel.add(AuthorLabel);
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        AuthorField.setSize(500,500);
        runPanel.add(AuthorField);

        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(MazeLabel);
        runPanel.add(MazeName);
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(CreatedLabel);
        runPanel.add(CreatedText);
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(SaveToDatabaseButton);
        runPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        runPanel.add(SaveImage);
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        runPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        runPanel.setBorder(BorderFactory.createTitledBorder(runPanel.getBorder(),
                "Maze Generator and Solver"));
        commandPanel.add(Box.createVerticalGlue());
        commandPanel.add(runPanel);
        commandPanel.add(infoPanel);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        commandPanel.add(editPanel);
        commandPanel.add(Box.createVerticalGlue());
        commandPanel.setBorder(new EmptyBorder(5, 15, 5, 15));


        generateNewAdultButton.addActionListener(e -> {
            clearMaze();
            mazePanel.getMaze().whitenThisMaze();
            mazePanel.repaint();
            maze = new Maze(18, 18);
            mazePanel.setMaze((maze));
            clearButton.setEnabled((true));
            //mazePanel.repaint();
        });

        generateNewKidsButton.addActionListener(e -> {
            clearMaze();
            mazePanel.getMaze().whitenThisMaze();
            mazePanel.repaint();
            maze.KidMaze();
            mazePanel.setMaze((maze));
            clearButton.setEnabled((true));
            int x = maze.getRows();
            int y = maze.getColumns();


            //mazePanel.repaint();
        });

        //
        // Generate, Solve and Reset Buttons ACTION LISTENERS
        generateNewMazeButton.addActionListener(e -> {
                maze = mazePanel.getMaze();

                statusLabel.setText("Status: Generating...");
                generatorMode = true;
                //random.setEnabled(true);
                resetButton.setEnabled(false);
                solveButton.setEnabled(true);
                clearButton.setEnabled((true));
                generateNewMazeButton.setEnabled(true);
                mazePanel.blacken();
                generator = new MazeGenerator(mazePanel.getMaze().getColumns(),
                            mazePanel.getMaze().getRows(), true,
                            mazePanel.getMaze());
            int x = maze.getRows();
            int y = maze.getColumns();

            mazePanel.drawLogo();

                maze.setGoal(x - 2, y - 2);
                maze.setStart(1, 1);

                mazePanel.repaint();
                runThread = new Thread(() -> {
                    try {

                        while(generator.nextStep(0)){
                            mazePanel.repaint();
                        }
                    } catch (InterruptedException e1) {
                        System.out.println("Interrupted");
                    }
                    statusLabel.setText("Status: Maze generated!");
                    mazePanel.repaint();
                });
            mazePanel.repaint();

                runThread.start();
        });

        SaveImage.addActionListener(e -> {

                Point p = mainPanel.getLocationOnScreen();
                Dimension dim = mainPanel.getSize();
                Rectangle rect = new Rectangle(p, dim);
            Robot robot;
            try {
                robot = new Robot();
            } catch (AWTException ex) {
                throw new RuntimeException(ex);
            }
            BufferedImage image = robot.createScreenCapture(rect);

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    fileChooser.setDialogTitle("Choose a location to save maze");
                    int result = fileChooser.showSaveDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try {
                            ImageIO.write(image, "jpg", new File(fileToSave.getAbsolutePath() + ".jpg"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    }


                });

        solveButton.addActionListener(e -> {
            if (canSolve()){
                statusLabel.setText("Status: Running...");

                resetButton.setEnabled(true);
                solveButton.setEnabled(true);
                clearButton.setEnabled((true));
                generateNewAdultButton.setEnabled((false));
                generateNewKidsButton.setEnabled((false));
                generateNewMazeButton.setEnabled(false);
                if (solver == null){
                    solver = setSolver();
                }
                runThread = new Thread(() -> {
                    try {
                        while (!solver.isSolved() && solver.nextStep(0)){
                            mazePanel.repaint();
                            mazePanel.getMaze().setSolution(solver.getSolution());
                            solutionLabel.setText("Cells visited for solution: "+
                                    mazePanel.getMaze().getSolution().size());
                            totalCellsLabel.setText("Total Cells: " + mazePanel.TotalCells());
                            countBlackenLabel.setText("Cell Walls: " + maze.countBlacken());
                            float total= mazePanel.TotalCells();
                            float blacken = maze.countBlacken();
                            float percentagetotal = blacken / total;
                            float traversed = mazePanel.getMaze().getSolution().size();
                            float percentageoptimal = traversed / total;
                            float deadends = (total - blacken) - traversed;
                            float percentagedead = deadends / total;
                            percentageTaversable.setText("Maze traversable:  " + Math.round((percentagetotal*100)) +"%");
                            percentageTraversedOptimal.setText("Maze traversable in solution: " + Math.round((percentageoptimal*100)) +"%");
                            percentageDeadEnds.setText("Ending in dead ends: " + Math.round((percentagedead*100)) + "%");
                        }
                    } catch (InterruptedException p) {
                        System.out.println("Interrupted!");
                    }
                    mazePanel.getMaze().setSolution(solver.getSolution());
                    mazePanel.repaint();
                    if (mazePanel.getMaze().getSolution() != null &&
                            mazePanel.getMaze().getSolution().get(mazePanel.getMaze().getSolution().size() - 1)
                                    .y == mazePanel.getMaze().getGoal().x && mazePanel.getMaze().getSolution()
                            .get(mazePanel.getMaze().getSolution().size() - 1).x == mazePanel.getMaze().getGoal().y) {
                        statusLabel.setText("Status: Solved!");
                    }
                    else{
                        statusLabel.setText("Status: No solution found");
                    }
                    solutionLabel.setText("Cells visited for solution: "+
                            mazePanel.getMaze().getSolution().size());
                    solver = null;
                });
                runThread.start();
            }
        });

        resetButton.addActionListener(e -> {
            mazePanel.setEditable(true);

            if (runThread != null){
                runThread.interrupt();
            }
            clearButton.setEnabled(true);
            generateNewAdultButton.setEnabled((true));
            generateNewKidsButton.setEnabled((true));
            generateNewMazeButton.setEnabled(true);
            solveButton.setEnabled(true);
            resetButton.setEnabled(false);
            clearMaze();
            statusLabel.setText("Status: Not Started");

        });

        clearButton.addActionListener(e -> {
            mazePanel.setEditable(true);
            generateNewKidsButton.setEnabled(true);
            generateNewAdultButton.setEnabled(true);
            if (runThread != null){
                runThread.interrupt();
            }
            clearButton.setEnabled(true);

            generateNewMazeButton.setEnabled(true);
            solveButton.setEnabled(true);
            resetButton.setEnabled(false);
            clearMaze();
            statusLabel.setText("Status: Not Started");
            mazePanel.getMaze().whitenThisMaze();
            mazePanel.repaint();
        });

        SaveToDatabaseButton.addActionListener(e -> {
            maze = new Maze(mazePanel.getMaze().getRows(),
                    mazePanel.getMaze().getColumns(), AuthorField.getText(), MazeName.getText(), LocalDate.now());
            System.out.println(maze.ToString());
        });
        // END


        // Start, Goal, Logo ACTION LISTENERS

        startLabel.setToolTipText("Start");
        startLabel.setText("S");
        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                startLabel.setTransferHandler(new TransferHandler("text"));
                goalLabel.setTransferHandler(new TransferHandler(null));
                logoLabel.setTransferHandler(new TransferHandler(null));
                JComponent c = (JComponent)e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.setDragImage(startIcon.getImage());
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
            @Override
            public void mouseEntered(MouseEvent e){
            }
        });

        goalLabel.setToolTipText("Goal");
        goalLabel.setText("G");
        mazePanel.setText("G");
        goalLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                goalLabel.setTransferHandler(new TransferHandler("text"));
                startLabel.setTransferHandler(new TransferHandler(null));
                logoLabel.setTransferHandler(new TransferHandler(null));
                JComponent c = (JComponent)e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.setDragImage(goalIcon.getImage());
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
        });

        logoLabel.setToolTipText("Logo");
        logoLabel.setText("L");
        mazePanel.setText("L");
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                logoLabel.setTransferHandler(new TransferHandler("text"));
                startLabel.setTransferHandler(new TransferHandler(null));
                goalLabel.setTransferHandler(new TransferHandler(null));
                JComponent c = (JComponent)e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.setDragImage(logoIcon.getImage());
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
            @Override
            public void mouseEntered(MouseEvent e){
            }
        });

        //END


        //Drop down menu ACTION LISTENER
        fileMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        newMaze.addActionListener(e -> {
            resetButton.doClick();
            newMaze();
        });

        openMaze.addActionListener(e -> {
            resetButton.doClick();
            openMaze();
        });

        mazePanel.setBorder(new EmptyBorder(10, 10, 10, 20));
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(commandPanel, BorderLayout.EAST);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                savePrompt(mainFrame);
                if (runThread != null){
                    runThread.interrupt();
                }
                mainFrame.dispose();
            }
        });
        mainFrame.setMinimumSize(new Dimension(1000, 1000));
        mainFrame.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.pack();
        mainFrame.setLocation(screenSize.width/2 - (mainFrame.getWidth())/2,
                screenSize.height/2 - (mainFrame.getHeight()/2));
        mainFrame.setVisible(true);
    }


    /**
     * Saves maze to a specific location
     */
    private void saveAs () {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save As");
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (.txt)", "txt"));
            int selection = chooser.showSaveDialog(mainFrame);
            if (selection == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getPath();
                if (chooser.getSelectedFile().exists()) {
                    String[] options = {"Yes", "No"};
                    int n = JOptionPane.showOptionDialog(chooser, "This file already"
                                    + "exists. Overwrite?",
                            "Confirm Overwrite", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                    if (n == 0) {
                        mazePanel.getMaze().saveMaze(filename);
                        directory = filename;
                        saved = true;
                    }
                } else {
                    mazePanel.getMaze().saveMaze(filename + ".txt");
                    directory = filename + ".txt";
                    saved = true;
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("Maze was not saved to specific directory");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves maze to save directory
     */
    private void save (){
        if (directory != null){
            File mazeFile = new File(directory);
            if (mazeFile.exists()){
                mazePanel.getMaze().saveMaze(directory);
                saved = true;
            }
            else{
                saveAs();
            }

        }
        else{
            saveAs();
        }
    }

    /**
     * Prompts the user to save if the maze has been modified
     * @param parent parent component
     */
    private void savePrompt (JFrame parent){
        if (!saved){
            int n = JOptionPane.showConfirmDialog(parent, "This maze has"
                            + " been modified. Save changes?", "Select an option",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0){
                save();
            }
        }
        saved = true;
    }

    /**
     * Creates GUI to build a new maze
     */
    private void newMaze() {
        try {


            if (runThread != null) {
                runThread.interrupt();
            }
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int maxSize;
            if (screenSize.height > screenSize.width) {
                maxSize = screenSize.height / 2;
            } else {
                maxSize = screenSize.width / 2;
            }
            JDialog newDialog = new JDialog(mainFrame, "Build New Maze", true);
            newDialog.setResizable(false);
            JPanel newPanel = new JPanel();
            newPanel.setMaximumSize(new Dimension(50, 50));
            newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
            newPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            GridLayout gridLayout = new GridLayout(1, 2, 5, 5);

            JSpinner rowSpinner = new JSpinner(new SpinnerNumberModel(mazePanel.getMaze().getRows(),
                    2, maxSize, 1));
            JSpinner columnSpinner = new JSpinner(new SpinnerNumberModel(mazePanel.getMaze().getColumns(),
                    2, maxSize, 1));

            JCheckBox keepOld = new JCheckBox("Keep previous maze");
            keepOld.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel rowPanel = new JPanel(gridLayout);
            rowPanel.add(new JLabel("Rows: "));
            rowPanel.add(rowSpinner);

            JPanel columnPanel = new JPanel(gridLayout);
            columnPanel.add(new JLabel("Columns: "));
            columnPanel.add(columnSpinner);


            JButton buildButton = new JButton("Build");

            JButton cancelButton = new JButton("Cancel");

            cancelButton.addActionListener(e -> newDialog.dispose());

            JPanel buttonPanel = new JPanel(gridLayout);
            buttonPanel.add(buildButton);
            buttonPanel.add(cancelButton);

            MazePanel newMazePanel = new MazePanel(new Maze(mazePanel.getMaze().getRows(),
                    mazePanel.getMaze().getColumns(), "Steve Smith", "Maze Name", LocalDate.now()));
            System.out.println(newMazePanel);
            newMazePanel.setMaximumSize(new Dimension(500, 500));
            newMazePanel.setEditable(false);
            newMazePanel.setMoveable(true);
            newMazePanel.setDrawGrid(mazePanel.getDrawGrid());
            newMazePanel.setOriginalMaze(mazePanel.getMaze());
            newMazePanel.getMaze().copyMazeObstacles(mazePanel.getMaze(), 0, 0);
            JPanel borderPanel = new JPanel(new GridLayout(0, 1));
            borderPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
            borderPanel.add(newMazePanel);

            JCheckBox gridBox = new JCheckBox("Draw grid");
            gridBox.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            gridBox.setSelected(mazePanel.getDrawGrid());
            gridBox.setVisible(false);

            gridBox.addActionListener(e -> newMazePanel.setDrawGrid(gridBox.isSelected()));

            keepOld.addActionListener(e -> {
                if (keepOld.isSelected()) {
                    borderPanel.setVisible(true);
                    gridBox.setVisible(true);
                    newMazePanel.invalidate();
                    newMazePanel.repaint();
                    newDialog.setSize(new Dimension(500, 400));
                    newDialog.repaint();
                } else {
                    borderPanel.setVisible(false);
                    gridBox.setVisible(false);
                    newDialog.pack();
                }
            });

            rowSpinner.addChangeListener(e -> {
                int spinnerValue = (Integer) rowSpinner.getValue();
                int currentRows = newMazePanel.getMaze().getRows();
                if (spinnerValue > currentRows) {

                    for (int j = currentRows; j < spinnerValue; j++) {
                        newMazePanel.getMaze().addRow(newMazePanel.getOriginalMaze());
                    }


                    newMazePanel.invalidate();
                    newMazePanel.repaint();
                } else if (spinnerValue < currentRows) {
                    for (int j = spinnerValue; j < currentRows; j++) {
                        newMazePanel.getMaze().removeRow();
                    }
                    newMazePanel.invalidate();
                    newMazePanel.repaint();
                }
            });

            columnSpinner.addChangeListener(e -> {
                int spinnerValue = (Integer) columnSpinner.getValue();
                int currentColumns = newMazePanel.getMaze().getColumns();
                if (spinnerValue > currentColumns) {
                    for (int j = currentColumns; j < spinnerValue; j++) {
                        newMazePanel.getMaze().addColumn(newMazePanel.getOriginalMaze());
                    }
                    newMazePanel.invalidate();
                    newMazePanel.repaint();
                } else if (spinnerValue < currentColumns) {
                    for (int j = spinnerValue; j < currentColumns; j++) {
                        newMazePanel.getMaze().removeColumn();
                    }
                    newMazePanel.invalidate();
                    newMazePanel.repaint();
                }
            });

            buildButton.addActionListener(e -> {
                if (keepOld.isSelected()) {
                    maze = newMazePanel.getMaze();
                } else {
                    maze = new Maze((Integer) rowSpinner.getValue(), (Integer) columnSpinner.getValue(), "Steve Smith", "Maze Name", LocalDate.now());
                    System.out.println(maze.ToString());
                }
                mazePanel.setMaze(maze);
                if (maze.getStart() != null && (maze.getStart().x < 0 || maze.getStart().y < 0 ||
                        maze.getStart().x >= maze.getRows() || maze.getStart().y >= maze.getColumns())) {
                    maze.setStart(null);
                }
                if (maze.getGoal() != null && (maze.getGoal().x < 0 || maze.getGoal().y < 0 ||
                        maze.getGoal().x >= maze.getRows() || maze.getGoal().y >= maze.getColumns())) {
                    maze.setGoal(null);
                }
                solver = null;
                directory = null;
                saved = true;
                mainFrame.setPreferredSize(mazePanel.getPreferredSize());

                if (!(mainFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH)) {
                    mainFrame.pack();
                    mainFrame.setLocation(screenSize.width / 2 - (mainFrame.getWidth()) / 2,
                            screenSize.height / 2 - (mainFrame.getHeight() / 2));
                    if ((Integer) rowSpinner.getValue() > 64 || (Integer) columnSpinner.getValue() > 64) {
                        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
                mainFrame.repaint();
                newDialog.dispose();
            });
            borderPanel.setVisible(false);
            newPanel.add(Box.createVerticalGlue());
            newPanel.add(rowPanel);
            newPanel.add(columnPanel);
            newPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            newPanel.add(keepOld);
            newPanel.add(gridBox);
            newPanel.add(borderPanel);
            newPanel.add(buttonPanel);
            newPanel.add(Box.createVerticalGlue());
            newDialog.add(newPanel);
            newDialog.setMaximumSize(new Dimension(500, 500));
            newDialog.setLocationRelativeTo(mainFrame);
            newDialog.pack();
            newDialog.setVisible(true);
        }
        catch (Exception e)
        {
            System.out.println("Issues occurred when creating maze");

            System.out.println(e.getMessage());
        }
    }



    /**
     * Opens a maze from a text file
     */
    public void ImageImporter() {

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                try {
                    JPanel panel = new JPanel();
                    panel.setBounds(0, 0, 1000, 1400);
                    BufferedImage img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                    mazePanel.setTempTimage(img);
                    mazePanel.setImgPath(selectedFile.getAbsolutePath());
                    mazePanel.repaint();
                } catch (IOException ignored) {
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("Issues occured when importing an image");

            System.out.println(e.getMessage());


        }
    }


    private boolean openMaze(){
        boolean flag = false;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (.txt)",
                "txt"));
        int selection = chooser.showOpenDialog(mainFrame);
        if (selection == JFileChooser.APPROVE_OPTION){
            String filename = chooser.getSelectedFile().getPath();
            savePrompt(mainFrame);
            maze = new Maze(filename);
            mazePanel.setMaze(maze);
            solver = null;
            mainFrame.setPreferredSize(mazePanel.getPreferredSize());
            if (!(mainFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH))
            {
                mainFrame.pack();
                mainFrame.setLocation(screenSize.width/2 - (mainFrame.getWidth())/2,
                        screenSize.height/2 - (mainFrame.getHeight()/2));
            }
            mainFrame.repaint();
            directory = filename;
            saved = true;
            flag = true;
        }
        return flag;
    }


    /**
     * Sets up a solver for this maze
     * @return  solver for this maze
     */
    private MazeSolver setSolver()
    {
        solver = new MazeSolver(mazePanel.getMazeData(), mazePanel.getMaze());
        solver.MazeSolverBFSInit();
        return solver;
    }

    /**
     * Checks if this maze is solvable
     * @return true if this maze can be solved, false otherwise
     */
    private boolean canSolve(){
        if (mazePanel.getMaze().getStart() == null){
            JOptionPane.showMessageDialog(mainFrame, "Set up a starting point to"
                    + " solve this maze!");
            return false;
        }
        if (mazePanel.getMaze().getGoal() == null){
            JOptionPane.showMessageDialog(mainFrame, "Set up a goal to"
                    + " solve this maze!");
            return false;
        }
        return true;
    }

    /**
     * Clears this maze,
     */
    private void clearMaze(){
        for (int i = 0; i < mazePanel.getMaze().getRows(); i++)
        {
            for (int j = 0; j < mazePanel.getMaze().getColumns(); j++)
            {
                mazePanel.getMaze().getCellArray()[i][j].isVisited = false;
                mazePanel.getMaze().setSolution(new ArrayList<>());
                mazePanel.getMaze().setCurrent(null);
                mainFrame.repaint();
                solver = null;
            }
        }
    }
}
