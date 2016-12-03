import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Matthew on 03/12/2016.
 */
public class ClientInterface implements Runnable{
    JTextArea txt;

    JTextField txtRefreshRate;
    JLabel lblRefreshRate;
    JButton btnAsciiPicture = new JButton("Ascii a picture");

    Webcam wc;
    ImageProcessor ip;

    public ClientInterface(){

        wc = Webcam.getDefault();
        wc.open();
        JPanel panel = new JPanel();
        txt = new JTextArea();
        txt.setColumns(64);
        txt.setRows(64);
        txt.setFont(new Font("Courier New", Font.PLAIN, 10));
        JPanel refreshSettings = new JPanel(new BorderLayout());
        lblRefreshRate = new JLabel("Refresh Rate");
        txtRefreshRate = new JTextField("0");
        refreshSettings.add(lblRefreshRate, BorderLayout.NORTH);
        refreshSettings.add(txtRefreshRate, BorderLayout.SOUTH);
        panel.add(refreshSettings);
        panel.add(txt);

        JPanel loadPicture = new JPanel(new GridLayout(1, 2));
        loadPicture.add(btnAsciiPicture);
        btnAsciiPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                try{
                    int returnVal = jfc.showOpenDialog(btnAsciiPicture);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        BufferedImage out = ImageIO.read(jfc.getSelectedFile());
                        int width = 64;
                        int height = 64;
                        int imageType = out.getType() == 0 ? 5 : out.getType();
                        BufferedImage temp = new BufferedImage(width, height, imageType);
                        Graphics2D g2 = temp.createGraphics();
                        g2.drawImage(out, 0, 0, width, height, null);
                        g2.dispose();
                        out = temp;
                        ImageProcessor customPic = new ImageProcessor(out);
                        new Thread(customPic).start();
                    }
                }catch (Exception ec){
                    ec.printStackTrace();
                }
            }
        });
        panel.add(loadPicture);
        JFrame window = new JFrame("Testing Frame");


        panel.setBackground(new Color(0, 0, 150));
        loadPicture.setBackground(new Color(0, 0, 150));
        window.setBackground(new Color(0,0,150));
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
                ip = new ImageProcessor(image);
                ip.setCI(this);
                ip.outputTo = txt;
                ip.outputFile = false;
                Thread ipThread = new Thread(ip);
                ipThread.start();
                Thread.sleep(getRefreshRate());
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
    synchronized public int getRefreshRate() {
        try {
            return Integer.parseInt(txtRefreshRate.getText());
        } catch (Exception e) {
            return 0;
        }
    }

}

