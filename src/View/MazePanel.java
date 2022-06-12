package View;

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

import Controller.IMaze;
import Controller.KidsMaze;
import Controller.Maze;
import Controller.Cell;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;


/**
 * JPanel for the Maze to display on the main GUI frame
 */
public class MazePanel extends JPanel{
    /**
     * mouse selection
     */
    private Point selection;
    /**
     * redraw flag
     */
    private boolean needsRedraw;
    /**
     * linked maze
     */
    private IMaze aMaze;

    /**
     * paints maze cells
     */
    private MouseAdapter mousePainter;
    /**
     * selects maze cells
     */
    private MouseAdapter mouseSelector;

    /**
     * arrows on solution
     */
    private final boolean drawArrows;
    /**
     * can be edited
     */
    private boolean editable;
    /**
     * draw maze grid
     */
    private boolean drawGrid;
    /**
     * used in drag n drop operations
     */
    private String text;
    /**
     * previews drop point of goal (DnD operations)
     */
    private boolean previewGoal;
    /**
     * previews drop point of start (DnD operations)
     */
    private boolean previewStart;
    /**
     * preview drop point of logo
     */
    private boolean previewLogo;
    /**
     * can be moved around with cursor
     */
    private boolean IsMoveable;
    /**
     * from which point movement occurred
     */
    private Point movementStartingPoint;
    /**
     * old linked maze
     */
    private IMaze originalMaze;
    /**
     * where the old maze is placed in relation to the current one
     */
    private final Point originalMazeStart;
    /**
     * path of temp logo icon
     */
    public String imagePath = "/Icons/logo.png";
    /**
     * getter for logo icon default path
     */
    private final ImageIcon logoIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            imagePath)));
    /**
     * this graphics
     */
    Graphics2D g2D;
    /**
     * logo image
     */
    BufferedImage thisLogoImage;
    /**
     * kids goal image path
     */
    String kidsGoalIconPath = "/Icons/KidsGoal.png";
    /**
     * kids start image path
     */
    String kidsStartIconPath = "/Icons/KidsStart.png";
    /**
     * getter for kids goal icon default path
     */
    private final ImageIcon KidsGoalIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            kidsGoalIconPath)));
    /**
     * getter for kids start logo icon default path
     */
    private final ImageIcon KidsStartIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
            kidsStartIconPath)));
    /**
     * Default constructor
     * @param aMaze maze linked to the panel
     */
    public MazePanel (IMaze aMaze){
        super();
        setTransferHandler(new TransferHandler("text"));
        selection = null;
        mousePainter = null;
        needsRedraw = true;
        drawArrows = false;
        editable = true;
        drawGrid = true;
        IsMoveable = false;
        this.aMaze = aMaze;
        this.originalMaze = aMaze;
        this.originalMazeStart = new Point(0, 0);
        setMouseSelector();
        setMousePainter();
        addMouseMotionListener(mouseSelector);
        addMouseListener(mousePainter);
        addMouseMotionListener(mousePainter);


    }

    /**
     * @param a the logo icon to be set
     *          sets the logo icon over the temp logo icon
     */
    public void setTempTimage( BufferedImage a)
    {
        thisLogoImage = a;
    }


    /**
     * @param path set the image path
    */
    public void setImgPath(String path)
    {
        imagePath = path;
    }
    /**
     * Needs repaint
     */
    @Override
    public void invalidate(){
        selection = null;
        needsRedraw = true;
        super.invalidate();
    }


    /**
     * Preffered size
     * @return preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(15*aMaze.getColumns(), 15*aMaze.getRows());
    }

    /**
     * Paint all the maze cells accordingly to linked maze data
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);//initialize

        g2D = (Graphics2D)g.create();

        int width, height;//calculate width and height along with offsets
        width = getWidth();
        height = getHeight();

        int cellWidth, cellHeight;
        cellWidth = width/aMaze.getColumns();
        cellHeight = height/aMaze.getRows();
        int xOff, yOff;

        if (cellWidth< cellHeight){
            cellHeight = cellWidth;
        }
        else{
            cellWidth = cellHeight;
        }

        xOff = (width - aMaze.getColumns()*cellWidth)/2;
        yOff = (height - aMaze.getRows()*cellHeight)/2;

        if (xOff == 0){
            width -=1;
            cellWidth = width/aMaze.getColumns();
        }
        if (yOff == 0){
            height-=1;
            cellHeight = height/aMaze.getRows();
        }



        if (needsRedraw){//replace cells if needed
            for (int i = 0;i< aMaze.getRows();i++){
                for (int j = 0;j< aMaze.getColumns();j++){
                    Rectangle cell;
                    cell = new Rectangle(xOff + j*cellWidth,
                            yOff + i*cellHeight, cellWidth, cellHeight);
                    aMaze.getCellArray()[i][j].placeCell(cell);
                }
            }
        }
        needsRedraw = false;


        for (int i = 0;i< aMaze.getRows();i++){//draw
            for (int j = 0;j< aMaze.getColumns();j++){
                if (drawGrid){//draw grid
                    g2D.setColor(Color.BLACK);
                }
                else{
                    g2D.setColor(getBackground());
                }
                g2D.draw(aMaze.getCellArray()[i][j].getCell());
                if (aMaze.getCellArray()[i][j].isObstacle()){
                    g2D.setColor(Color.BLACK);//draw obstacle
                    g2D.fill(aMaze.getCellArray()[i][j].getCell());
                }

                if (aMaze.getCellArray()[i][j].isVisited()){ //REMOVE THIS TO REMOVE THE TILES TRAVERSED
                    g2D.setColor(Color.pink);//draw visited
                    g2D.fill(aMaze.getCellArray()[i][j].getCell());
                }



            }

        }



        g2D.setColor(Color.red);//draw solution so far
        Cell previous;
        Cell aSolutionBox = null;
        if (aMaze.getSolution() != null){
            int currentSize = aMaze.getSolution().size();
            for (int i = 0;i< currentSize;i++){
                try {//catch concurrent modifications
                    previous = aSolutionBox;
                    aSolutionBox = aMaze.getSolution().get(i);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                if (aSolutionBox != null){
                    g2D.fill(aMaze.getCellArray()[aSolutionBox.y]
                            [aSolutionBox.x].getCell());
                }
                if (previous != null && aSolutionBox != null && drawArrows){
                    g2D.setColor(Color.BLACK);//draw arrors
                    Rectangle cell = aMaze.getCellArray()[previous.y][previous.x].getCell();
                    if (previous.x > aSolutionBox.x){
                        g2D.fillPolygon(new int[]{cell.x,
                                cell.x - cell.width/4, cell.x}, new int[]{
                                cell.y + cell.height/4, cell.y + cell.height/2,
                                cell.y + (3*cell.height)/4}, 3
                        );
                        g2D.drawPolyline(new int[]{cell.x + cell.width/2,
                                cell.x - cell.width/2}, new int[]{cell.y + cell.height/2,
                                cell.y + cell.height/2}, 2);
                    }
                    else if (previous.x< aSolutionBox.x){
                        g2D.fillPolygon(new int[]{cell.x + cell.width,
                                cell.x + cell.width + cell.width/4, cell.x + cell.width}, new int[]{
                                cell.y + cell.height/4, cell.y + cell.height/2,
                                cell.y + (3*cell.height)/4}, 3
                        );
                        g2D.drawPolyline(new int[]{cell.x + 3*cell.width/2,
                                cell.x + cell.width/2}, new int[]{cell.y + cell.height/2,
                                cell.y + cell.height/2}, 2);
                    }
                    else if (previous.y > aSolutionBox.y){
                        g2D.fillPolygon(new int[]{cell.x + cell.width/4,
                                cell.x + cell.width/2, cell.x + (3*cell.height)/4}, new int[]{
                                cell.y, cell.y - cell.height/4,
                                cell.y}, 3
                        );
                        g2D.drawPolyline(new int[]{cell.x + cell.width/2,
                                cell.x + cell.width/2}, new int[]{cell.y + cell.height/2,
                                cell.y - cell.height/2}, 2);
                    }
                    else{
                        g2D.fillPolygon(new int[]{cell.x + cell.width/4,
                                cell.x + cell.width/2, cell.x + (3*cell.height)/4}, new int[]{
                                cell.y + cell.height, cell.y + cell.height + cell.height/4,
                                cell.y + cell.height}, 3
                        );
                        g2D.drawPolyline(new int[]{cell.x + cell.width/2,
                                cell.x + cell.width/2}, new int[]{cell.y + 3*cell.height/2,
                                cell.y + cell.height/2}, 2);
                    }
                    g2D.setColor(Color.GREEN);
                }
            }
        }

        if (aMaze.getCurrent() != null){//draw current cell
            g2D.setColor(Color.MAGENTA);
            g2D.fill(aMaze.getCellArray()[aMaze.getCurrent().x]
                    [aMaze.getCurrent().y].getCell());
        }


        if (aMaze.getStart() != null && aMaze.getStart().x>= 0 &&
                aMaze.getStart().y>= 0 && aMaze.getStart().x< aMaze.getRows() &&
                aMaze.getStart().y< aMaze.getColumns()){
            g2D.setColor(Color.YELLOW);//draw start
            if (!aMaze.getIsKidsMaze()) {
                g2D.fill(aMaze.getCellArray()[aMaze.getStart().x]
                        [aMaze.getStart().y].getCell());
            }
            else
            {
                Rectangle a = aMaze.getCellArray()[aMaze.getStart().x][aMaze.getStart().y].getCell();
                g2D.drawImage(KidsStartIcon.getImage(), (int) a.getX(), (int) a.getY()
                        , cellWidth, cellHeight, Color.white, this);
            }
        }
        if (aMaze.getGoal() != null && aMaze.getGoal().x>= 0 &&
                aMaze.getGoal().y>=0 && aMaze.getGoal().x<aMaze.getRows() &&
                aMaze.getGoal().y< aMaze.getColumns()){
            g2D.setColor(Color.BLUE);//draw goal
            System.out.println((aMaze.getIsKidsMaze()));
            if(!aMaze.getIsKidsMaze()) {
                g2D.fill(aMaze.getCellArray()[aMaze.getGoal().x]
                        [aMaze.getGoal().y].getCell());
            }else
            {
                Rectangle a = aMaze.getCellArray()[aMaze.getGoal().x][aMaze.getGoal().y].getCell();
                g2D.drawImage(KidsGoalIcon.getImage(), (int) a.getX(), (int) a.getY()
                        , cellWidth, cellHeight, Color.white, this);
            }
        }
        if (aMaze.getLogo() != null && aMaze.getLogo().x>= 0  &&
                aMaze.getLogo().y>=0 && aMaze.getLogo().x<aMaze.getRows() &&
                aMaze.getLogo().y< aMaze.getColumns()){
            g2D.setColor(Color.magenta);//draw logo


            Rectangle a = aMaze.getCellArray()[aMaze.getLogo().x][aMaze.getLogo().y].getCell();

            aMaze.blackenThisCell(aMaze.getLogo().x, aMaze.getLogo().y);
            aMaze.getCellArray()[aMaze.getLogo().x][aMaze.getLogo().y].getCell();
            if(thisLogoImage == null) {
                g2D.drawImage(logoIcon.getImage(), (int) a.getX(), (int) a.getY()
                        , cellWidth, cellHeight, Color.white, this);
            }
            else {
                g2D.drawImage(thisLogoImage, (int) a.getX(), (int) a.getY()
                        , cellWidth, cellHeight, Color.white, this);
            }



            //g2D.fill(aMaze.getCellArray()[aMaze.getLogo().x][aMaze.getLogo().y].getCell());

        }


        if (previewStart && selection != null && !selection.equals(aMaze.getGoal())){
            Color previewYellow = new Color(Color.YELLOW.getRed(),
                    Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), 100);
            g2D.setColor(previewYellow);//draw preview of DnD (start)
            g2D.fill(aMaze.getCellArray()[selection.x][selection.y].getCell());
        }
        if (previewLogo && selection != null && !selection.equals(aMaze.getLogo())){
            Color previewPink = new Color(Color.PINK.getRed(),
                    Color.PINK.getGreen(), Color.PINK.getBlue(), 100);
            g2D.setColor(previewPink);//draw preview of DnD (logo)
            g2D.fill(aMaze.getCellArray()[selection.x][selection.y].getCell());
        }

        if (previewGoal && selection != null && !selection.equals(aMaze.getStart())){
            Color previewBlue = new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(),
                    Color.BLUE.getBlue(), 100);
            g2D.setColor(previewBlue);//draw preview of DnD (goal)
            g2D.fill(aMaze.getCellArray()[selection.x][selection.y].getCell());
        }

        g2D.setColor(Color.BLACK);
        if (drawGrid){//draw grid
            for (int i = 0;i< aMaze.getRows();i++){
                for (int j = 0;j< aMaze.getColumns();j++){
                    g2D.draw(aMaze.getCellArray()[i][j].getCell());
                }
            }
        }
        else{//draw outline
            Rectangle upperLeftCell = aMaze.getCellArray()[0][0].getCell();
            Rectangle upperRightCell = aMaze.getCellArray()[0][aMaze.getColumns() - 1].getCell();
            Rectangle bottomLeftCell = aMaze.getCellArray()[aMaze.getRows() - 1][0].getCell();
            Rectangle bottomRightCell = aMaze.getCellArray()[aMaze.getRows() - 1][aMaze.getColumns() - 1].getCell();
            g2D.drawPolyline(new int[]{upperLeftCell.x, upperRightCell.x + cellWidth,
                    bottomRightCell.x + cellWidth, bottomLeftCell.x, upperLeftCell.x}, new int[]{upperLeftCell.y,
                    upperRightCell.y, bottomRightCell.y + cellHeight, bottomLeftCell.y + cellWidth, upperLeftCell.y}, 5);
        }
        g2D.dispose();
    }


    /**
     * @return total number of cells present in a maze
     */
    public int TotalCells(){
        return aMaze.getColumns() * aMaze.getRows();
    }
    /**
     * draw logo to screen when the user presses the generte new maze button
     */
    public void drawLogo()
    {

        int width, height;//calculate width and height along with offsets
        width = getWidth();
        height = getHeight();
        int cellWidth, cellHeight;
        cellWidth = width/aMaze.getColumns();
        cellHeight = height/aMaze.getRows();
        int x = aMaze.getRows();
        int y = aMaze.getColumns();

        Random rand = new Random();
        int upperbound = 0;
        if(x > y)
        {
            upperbound = y;
        }
        else if(y > x)
        {
            upperbound = x;
        }
        else
        {
            upperbound = x;
        }

        int int_random = rand.nextInt(2, upperbound-2);
        int int_random2 = rand.nextInt(2, upperbound-2);

        Point newPoint = new Point(int_random,int_random2);
        aMaze.setLogo(newPoint);

        g2D.drawImage(logoIcon.getImage(), int_random, int_random2
                ,cellWidth, cellHeight, Color.BLUE, this);
    }

    /**
     * Link this panel with a new maze
     * @param aMaze maze to link
     */
    public void setMaze(IMaze aMaze){
        removeMouseMotionListener(mouseSelector);
        removeMouseMotionListener(mousePainter);
        removeMouseListener(mousePainter);
        this.aMaze = aMaze;
        setMouseSelector();
        setMousePainter();
        addMouseMotionListener(mouseSelector);
        addMouseListener(mousePainter);
        addMouseMotionListener(mousePainter);
        invalidate();
    }

    /**
     * Returns linked maze's data as a 2d array
     * @return  2d array
     */
    public int[][] getMazeData(){
        int[][] mazeData = new int[aMaze.getRows()][aMaze.getColumns()];
        for (int i = 0;i< aMaze.getRows();i++){
            for (int j = 0;j< aMaze.getColumns();j++){
                mazeData[i][j] = 0;
                if (aMaze.getStart().x == i && aMaze.getStart().y == j){
                    mazeData[i][j] = 1;
                }
                if (aMaze.getGoal().x == i && aMaze.getGoal().y == j){
                    mazeData[i][j] = 2;
                }
                if (aMaze.getCellArray()[i][j].isObstacle()){
                    mazeData[i][j] = 3;
                }
            }
        }
        return mazeData;
    }

    /**
     * Returns the maze currently linked to this panel
     * @return maze object
     */
    public IMaze getMaze(){
        return aMaze;
    }

    /**
     * Transforms all cells to obstacles
     */

    public void blacken(){
        if(!aMaze.getIsKidsMaze()) {
            if (aMaze.getRows() % 2 == 0 && aMaze.getColumns() % 2 == 0) {
                aMaze = new Maze(aMaze.getRows() + 1, aMaze.getColumns() + 1);
            }
            if (aMaze.getRows() % 2 == 0) {
                aMaze = new Maze(aMaze.getRows() + 1, aMaze.getColumns());
            }
            if (aMaze.getColumns() % 2 == 0) {
                aMaze = new Maze(aMaze.getRows(), aMaze.getColumns() + 1);
            }
            this.invalidate();
            aMaze.blackenThisMaze();
        }
        else if (aMaze.getIsKidsMaze())
        {
            if (aMaze.getRows() % 2 == 0 && aMaze.getColumns() % 2 == 0) {
                aMaze = new KidsMaze(aMaze.getRows() + 1, aMaze.getColumns() + 1);
            }
            if (aMaze.getRows() % 2 == 0) {
                aMaze = new KidsMaze(aMaze.getRows() + 1, aMaze.getColumns());
            }
            if (aMaze.getColumns() % 2 == 0) {
                aMaze = new KidsMaze(aMaze.getRows(), aMaze.getColumns() + 1);
            }
            this.invalidate();
            aMaze.blackenThisMaze();
        }
    }


    /**
     * Calculates mouse selection as maze cell
     * @param e mouse movement
     */
    private void calculatePosition(MouseEvent e){
        int width, height;
        width = getWidth();
        height = getHeight();


        int cellWidth, cellHeight;
        cellWidth = width/aMaze.getColumns();
        cellHeight = height/aMaze.getRows();
        int xOff, yOff;

        if (cellWidth< cellHeight){
            cellHeight = cellWidth;
        }
        else{
            cellWidth = cellHeight;
        }

        xOff = (width - aMaze.getColumns()*cellWidth)/2;
        yOff = (height - aMaze.getRows()*cellHeight)/2;

        if (e.getX()>= xOff && e.getX()<= width - xOff &&
                e.getY()>= yOff && e.getY()<= height - yOff){
            selection = new Point();
            selection.x = (e.getY() - yOff)/cellHeight;
            selection.y = (e.getX() - xOff)/cellWidth;
            if (selection.x>= aMaze.getRows()){
                selection.x = aMaze.getRows() - 1;
            }
            if (selection.y>= aMaze.getColumns()){
                selection.y = aMaze.getColumns() - 1;
            }
        }
        else{
            selection = null;
        }
    }


    /**
     * Set to true for variable is editable else false
     * @param editable new value
     */
    public void setEditable(boolean editable){
        this.editable = editable;
    }

    /**
     * Calculates on which maze cell the pointer hovers on
     * @return point indicating maze cell
     */
    private Point calculatePointerSelection(){
        Point pointer = this.getMousePosition();
        Point pointerSelection = new Point();
        int width, height;
        width = getWidth();
        height = getHeight();

        int cellWidth, cellHeight;
        cellWidth = width/aMaze.getColumns();
        cellHeight = height/aMaze.getRows();
        int xOff, yOff;

        if (cellWidth< cellHeight){
            cellHeight = cellWidth;
        }
        else{
            cellWidth = cellHeight;
        }

        xOff = (width - aMaze.getColumns()*cellWidth)/2;
        yOff = (height - aMaze.getRows()*cellHeight)/2;

        if (pointer != null && pointer.x>= xOff && pointer.x<= width - xOff &&
                pointer.y>= yOff && pointer.y<= height - yOff){
            pointerSelection.x = (pointer.y - yOff)/cellHeight;
            pointerSelection.y = (pointer.x - xOff)/cellWidth;
            if (pointerSelection.x>= aMaze.getRows()){
                pointerSelection.x = aMaze.getRows() - 1;
            }
            if (pointerSelection.y>= aMaze.getColumns()){
                pointerSelection.y = aMaze.getColumns() - 1;
            }
        }
        else{
            pointerSelection = null;
        }
        return pointerSelection;
    }

    /**
     * Enables preview of DnD operations according to data transefered
     * @param selection start or goal
     */
    public void setDnDPreview(String selection){
        if (editable){
            this.selection = calculatePointerSelection();
            if (selection.equals("S")){
                this.previewGoal = false;
                this.previewStart = true;
                this.previewLogo = false;
            }
            else if (selection.equals("G")){
                this.previewGoal = true;
                this.previewStart = false;
                this.previewLogo = false;
            }
            else if (selection.equals("L")){
                this.previewLogo = true;
                this.previewGoal = false;
                this.previewStart = false;
            }
            repaint();
        }
    }



    /**
     * Sets start and goal with drag n drop
     *
     * @param selection string reperesenting user selection (start or goal)
     */

    public void setText(String selection){
        Point pointerSelection = calculatePointerSelection();

        if (pointerSelection != null && editable){
            if (selection.equals("S") && (aMaze.getGoal() == null ||
                    !pointerSelection.equals(aMaze.getGoal()))){
                aMaze.setStart(pointerSelection);
                aMaze.getCellArray()[pointerSelection.x][pointerSelection.y].
                        SetIsObstacle(false);
            }

            if (selection.equals("L") && (aMaze.getLogo() == null ||
                    !pointerSelection.equals(aMaze.getLogo()))){
                aMaze.setLogo(pointerSelection);
                aMaze.getCellArray()[pointerSelection.x][pointerSelection.y].
                        SetIsObstacle(true);
            }


            else if (aMaze.getStart() == null || !pointerSelection.equals(aMaze.getStart())){
                aMaze.setGoal(pointerSelection);
                aMaze.getCellArray()[pointerSelection.x][pointerSelection.y].
                        SetIsObstacle(false);
            }
        }

        else if (editable){
            if (selection.equals("S")){
                aMaze.setStart(null);
            }
            else if (selection.equals("L")){
                aMaze.setGoal(null);
            }
            else if (selection.equals("L")){
                aMaze.setLogo(null);
            }
        }

        repaint();


    }


    /**
     *
     * @return  dummy, required for drag n drop operations
     */
    public String getText(){
        return this.text;
    }

    /**
     * Setter of drawGrid variable
     * @param drawGrid new value
     */
    public void setDrawGrid(boolean drawGrid){
        this.drawGrid = drawGrid;
        repaint();
    }

    /**
     * Sets up the mouse selector that selects individual maze cells
     */
    private void setMouseSelector(){
        this.mouseSelector = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                calculatePosition(e);

            }

            @Override
            public void mouseDragged(MouseEvent e){
                calculatePosition(e);
            }

        };
    }

    /**
     * Sets up the mousePainter that modifies individual maze cells
     */
    private void setMousePainter(){
        mousePainter = new MouseInputAdapter() {


            @Override
            public void mousePressed(MouseEvent e){
                movementStartingPoint = selection;
            }

            @Override
            public void mouseClicked(MouseEvent e){
                if (selection != null && editable){
                    if (SwingUtilities.isLeftMouseButton(e)){
                        if (!selection.equals(aMaze.getStart()) && !selection.equals(aMaze.getGoal())
                                && !aMaze.getCellArray()[selection.x][selection.y].
                                isObstacle()){
                            aMaze.getCellArray()[selection.x][selection.y].
                                    SetIsObstacle(true);
                        }
                        else if (aMaze.getCellArray()[selection.x][selection.y].
                                isObstacle()){
                            aMaze.getCellArray()[selection.x][selection.y].
                                    SetIsObstacle(false);
                        }
                        else if (aMaze.getStart() != null && aMaze.getStart().
                                equals(selection)){
                            aMaze.setStart(null);
                        }
                        else if (aMaze.getGoal() != null && aMaze.getGoal().
                                equals(selection)){
                            aMaze.setGoal(null);
                        }
                    }
                    else if (SwingUtilities.isRightMouseButton(e)){
                        aMaze.getCellArray()[selection.x][selection.y].
                                SetIsObstacle(false);
                    }
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e){
                if (selection!= null && editable){
                    if (SwingUtilities.isLeftMouseButton(e)){
                        if (!selection.equals(aMaze.getStart()) && !selection.equals(aMaze.getGoal()) && !selection.equals(aMaze.getLogo())){
                            aMaze.getCellArray()[selection.x][selection.y].
                                    SetIsObstacle(true);
                        }
                        else if (selection.equals(aMaze.getStart())){
                            text = "S";
                            JComponent c = (JComponent)e.getSource();
                            TransferHandler handler = c.getTransferHandler();
                            handler.setDragImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                                    "/Icons/start.png")));
                            handler.exportAsDrag(c, e, TransferHandler.COPY);
                        }
                        else if (selection.equals(aMaze.getGoal())){
                            text = "G";
                            JComponent c = (JComponent)e.getSource();
                            TransferHandler handler = c.getTransferHandler();
                            handler.setDragImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                                    "/Icons/goal.png")));
                            handler.exportAsDrag(c, e, TransferHandler.COPY);
                        }
                        else if (selection.equals(aMaze.getLogo())){
                            text = "L";
                            JComponent c = (JComponent)e.getSource();
                            TransferHandler handler = c.getTransferHandler();
                            handler.setDragImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                                    "/Icons/logo.png")));
                            handler.exportAsDrag(c, e, TransferHandler.COPY);
                        }
                    }
                    else if (SwingUtilities.isRightMouseButton(e)){
                        aMaze.getCellArray()[selection.x][selection.y].
                                SetIsObstacle(false);
                    }
                }
                if (IsMoveable && movementStartingPoint != null &&
                        selection != null &&
                        !movementStartingPoint.equals(selection)){
                    int i = movementStartingPoint.x - selection.x;
                    int j = movementStartingPoint.y - selection.y;
                    originalMazeStart.x += i;
                    originalMazeStart.y += j;
                    if (aMaze.getStart() != null){
                        aMaze.getStart().x -= i;
                        aMaze.getStart().y -= j;
                    }
                    if (aMaze.getGoal() != null){
                        aMaze.getGoal().x -= i;
                        aMaze.getGoal().y -= j;
                    }
                    if (aMaze.getLogo() != null){
                        aMaze.getLogo().x -= i;
                        aMaze.getLogo().y -= j;
                    }
                    aMaze.copyMazeObstacles(originalMaze, originalMazeStart.x,
                            originalMazeStart.y);
                    movementStartingPoint = selection;
                }
                invalidate();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e){
                if (IsMoveable && selection != null){
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
                }
                else if (IsMoveable){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }



        };
    }



    /**
     * Setter of moveable variable
     * @param moveable new value
     */
    public void setMoveable(boolean moveable){
        this.IsMoveable = moveable;
    }

    /**
     * Getter of originalMaze variable
     * @return originalMaze variable
     */
    public IMaze getOriginalMaze() {
        return originalMaze;
    }

    /**
     * Setter of originalMaze variable
     * @param newOriginalMaze new value
     */
    public void setOriginalMaze(IMaze newOriginalMaze){
        this.originalMaze = newOriginalMaze;
    }

    /**
     * Getter of drawGrid variable
     * @return drawGrid variable
     */
    public boolean getDrawGrid(){
        return this.drawGrid;
    }


}
