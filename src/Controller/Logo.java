package Controller;

import java.time.LocalDate;

/**
 *
 * The elements of the Logo will be managed by this class and will include functions for positioning, exporting, removing and adding the logo
 */
public class Logo {
    private int logoSizeX;
    private int logoSizeY;
    private String filename;

    Logo(String filename, int logoSizeX, int logoSizeY)
    {
        this.filename = filename;
        this.logoSizeY = logoSizeY;
        this.logoSizeX = logoSizeY;
    }

    /**
     * Used for debugging to print to console
     * @return string file name, logo
     */
    public String toString(){
        return "File Name: " + filename + " LogoX: " + logoSizeX + " LogoY: " + logoSizeY;
    }


    /**
     * Used to export the logo
     */
    public void exportLogo()
    {

    }

    /**
     * Used to add logo to a maze
     */
    public void addLogo()
    {

    }

    /**
     * used to removed a logo from a maze
     */
    public void removeLogo()
    {

    }


    /**
     * used to set the position of a logo on a maze
     */
    public void positionLogo()
    {

    }
}
