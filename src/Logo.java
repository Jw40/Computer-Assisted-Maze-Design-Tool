import java.time.LocalDate;

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

    public String toString(){
        return "File Name: " + filename + " LogoX: " + logoSizeX + " LogoY: " + logoSizeY;
    }
}
