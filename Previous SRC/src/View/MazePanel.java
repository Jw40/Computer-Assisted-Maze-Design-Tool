package View;

import Controller.IMaze;


import javax.swing.*;
import java.awt.*;


/**
 * Currently used to hold a maze object and display it in the gui window (not production ready)
 */
    class MazePanel extends JPanel
    {
        private IMaze maze; // the maze object

        public MazePanel(IMaze theMaze)
        {
            maze = theMaze;
        }

        /**
         * paintComponent used every time this panel needs refrehing or a maze needs to be drawn on the GUI
         * @param thisPage
         *
         */
        // The paintComponent method is called every time
        // that the panel needs to be displayed or refreshed.
        // Anything you want drawn on the panel should be drawn
        // in this method.
        public void paintComponent(Graphics thisPage)
        {
            super.paintComponent(thisPage);
            setBackground(Color.white);
            this.setPreferredSize(maze.windowSize());
            maze.draw(thisPage);
        }
    }

