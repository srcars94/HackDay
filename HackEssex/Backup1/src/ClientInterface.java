import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Matthew on 03/12/2016.
 */
public class ClientInterface implements Runnable{
    JTextArea txt;
    Webcam wc;

    public ClientInterface(){
        wc = Webcam.getDefault();
        wc.open();
        JPanel panel = new JPanel();
        txt = new JTextArea();
        txt.setColumns(64);
        txt.setRows(64);
        txt.setFont(new Font("Courier New", Font.PLAIN, 10));
        panel.add(txt);
        JFrame window = new JFrame("Testing Frame");
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setSize(window.getMaximumSize());
        window.setVisible(true);
    }

    public void run(){
        while(true) {
            try {
                BufferedImage image = wc.getImage();
//            String preceding = System.getProperty("user.dir");
//            String imageLocation = "/images/cat.png";
//            File imageToLoad = new File(preceding + imageLocation);
//            BufferedImage image = ImageIO.read(imageToLoad);
                //
                //Attempt at scaling image
                int width = 64;
                int height = 64;
                int imageType = image.getType() == 0 ? 5 : image.getType();
                BufferedImage temp = new BufferedImage(width, height, imageType);
                Graphics2D g2 = temp.createGraphics();
                g2.drawImage(image, 0, 0, width, height, null);
                g2.dispose();
                image = temp;
                //
//            System.out.println(imageToLoad.toString());
                ImageProcessor ip = new ImageProcessor(image);
                ip.outputTo = txt;
                ip.outputFile = false;
                Thread ipThread = new Thread(ip);
                ipThread.start();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}
