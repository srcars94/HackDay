import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class ImageProcessor
        implements Runnable
{
    private BufferedImage image = null;
    private List<RGB[]> imageContents = new ArrayList();
    public boolean outputFile = true;
    public JTextArea outputTo;

    public ImageProcessor(BufferedImage image)
    {
        this.image = image;
    }

    public void run()
    {
//        System.out.println("ANALYSING IMAGE");
        RGB[] line = new RGB[this.image.getHeight()];
        for (int y = 0; y < this.image.getHeight(); y++)
        {
            for (int x = 0; x < this.image.getWidth(); x++)
            {
                int pixel = this.image.getRGB(x, y);
                int red = pixel >> 16 & 0xFF;
                int green = pixel >> 8 & 0xFF;
                int blue = pixel & 0xFF;
                int avg = (red + green + blue) / 3;

                line[x] = new RGB(avg, avg, avg);
            }
            this.imageContents.add(line);
            line = new RGB[this.image.getWidth()];
        }
        if (this.outputFile) {
            saveToFile();
        }
        if (this.outputTo != null) {
            saveToJText(this.outputTo);
        }
    }

    public void outputGrayscale()
    {
        try
        {
            File f = new File(System.getProperty("user.dir") + "/1.png");
            ImageIO.write(this.image, "png", f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void saveToJText(JTextArea txt)
    {
        String theLine = "";
        String theWholeThing = "";
        for (RGB[] line : this.imageContents)
        {
            for (RGB s : line) {
                theLine = theLine + getAscii(s.getR()) + "" + getAscii(s.getR());
            }
            theWholeThing = theWholeThing + theLine + "\n";
            theLine = "";
        }
        txt.setText(theWholeThing);
    }

    public void saveToFile()
    {
        try
        {
            PrintWriter out = new PrintWriter("output.txt");
            for (RGB[] line : this.imageContents)
            {
                for (RGB s : line) {
                    out.print(s);
                }
                out.println();
            }
            out.close();

            out = new PrintWriter("output2.txt");
            for (RGB[] line : this.imageContents)
            {
                for (RGB s : line) {
                    out.print(getAscii(s.getR()) + "" + getAscii(s.getR()));
                }
                out.println();
            }
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("There was a problem outputting the results");
            e.printStackTrace();
        }
    }

    public int getRandomInt(int min, int max)
    {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }

    public char getAscii(int value)
    {
        if (isBetween(0, 35, value)) {
            return '#';
        }
        if (isBetween(36, 72, value)) {
            return '@';
        }
        if (isBetween(72, 108, value)) {
            return '&';
        }
        if (isBetween(108, 143, value)) {
            return ';';
        }
        if (isBetween(144, 179, value)) {
            return '"';
        }
        if (isBetween(180, 215, value)) {
            return '*';
        }
        if (isBetween(216, 255, value)) {
            return '\'';
        }
        return ' ';
    }

    public boolean isBetween(int min, int max, int value)
    {
        return (min <= value) && (value <= max);
    }
}
