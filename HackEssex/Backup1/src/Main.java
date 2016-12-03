//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.List;
//import javafx.application.Application;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import javafx.stage.FileChooser;
//import javafx.stage.FileChooser.ExtensionFilter;
//import javafx.stage.Stage;
//import javax.imageio.ImageIO;
//
//public class Main
//        extends Application
//{
//    private FileChooser fileChooser = new FileChooser();
//    private Button btnImage = new Button();
//    private Button btnVid = new Button();
//    private Pane root = new Pane();
//    private Label myLabelImage = new Label();
//    private Label myLabelVid = new Label();
//    private Label listImageOutput = new Label();
//    private Label imageOutput = new Label();
//    private static int x = 1200;
//    private static int prefWidthBtn = 150;
//    private static int prefWifthHt = 10;
//    private static int width;
//    private static int height;
//
//    public static void main(String[] args)
//    {
//        try
//        {
//            ClientInterface ci = new ClientInterface();
//            Thread ciThread = new Thread(ci);
//            ciThread.start();
//        }
//        catch (Exception localException) {}
//    }
//
//    private static void analyseImage(String preceding, String imageLocation)
//    {
//        try
//        {
//            File imageToLoad = new File(preceding + imageLocation);
//            BufferedImage image = ImageIO.read(imageToLoad);
//
//            width = 64;
//            height = 64;
//            BufferedImage temp = new BufferedImage(width, height, image.getType());
//            Graphics2D g2 = temp.createGraphics();
//            g2.drawImage(image, 0, 0, width, height, null);
//            g2.dispose();
//            image = temp;
//
//            System.out.println(imageToLoad.toString());
//            ImageProcessor ip = new ImageProcessor(image);
//            Thread ipThread = new Thread(ip);
//            ipThread.start();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    private void guiOutput()
//    {
//        try
//        {
//            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/output2.txt");
//            BufferedReader textReader = new BufferedReader(fileReader);
//            System.out.println("1");
//            List<String> textList = new ArrayList();
//            for (y = 0; y < height; y++) {
//                textList.add(textReader.readLine());
//            }
//            for (String s : textList) {
//                this.imageOutput.setText(this.imageOutput.getText() + s + "\n");
//            }
//        }
//        catch (Exception e)
//        {
//            int y;
//            e.printStackTrace();
//        }
//    }
//
//    public void start(final Stage primaryStage)
//    {
//        primaryStage.getIcons().add(new Image("catTest1.gif"));
//
//        Image image = new Image("catTest.jpg");
//
//        ImageView iv1 = new ImageView();
//        iv1.setImage(image);
//
//        primaryStage.setTitle("Cat image to ASCII values converter");
//        this.btnImage.setText("Select image");
//        this.btnVid.setText("Select video input");
//
//        this.fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter(".jpg", new String[] { "*.jpg" }), new FileChooser.ExtensionFilter(".png", new String[] { "*.png" }), new FileChooser.ExtensionFilter("Other", new String[] { "*.*" }) });
//
//        this.btnImage.setOnAction(new EventHandler()
//        {
//            public void handle(ActionEvent event)
//            {
//                Main.this.fileChooser.setTitle("Open File");
//
//                Main.analyseImage("", Main.this.fileChooser.showOpenDialog(primaryStage).toString());
//                Main.this.guiOutput();
//            }
//        });
//        this.myLabelImage.setText("Select the\nimage you \nwant to convert \nto ASCII values");
//        this.myLabelVid.setText("Select the\n video input \nyou wish to \nconvert into ASCII values");
//
//        this.btnImage.setLayoutX(x);
//        this.btnImage.setPrefSize(prefWidthBtn, prefWifthHt);
//        this.btnImage.setLayoutY(20.0D);
//
//        this.btnVid.setLayoutX(x);
//        this.btnVid.setPrefSize(prefWidthBtn, prefWifthHt);
//        this.btnVid.setLayoutY(200.0D);
//
//        this.myLabelImage.setLayoutX(x);
//        this.myLabelImage.setLayoutY(50.0D);
//
//        this.myLabelVid.setLayoutX(x);
//        this.myLabelVid.setLayoutY(230.0D);
//
//        this.listImageOutput.setLayoutX(10.0D);
//        this.listImageOutput.setLayoutY(100.0D);
//
//        this.root.getChildren().add(this.btnImage);
//        this.root.getChildren().add(this.btnVid);
//        this.root.getChildren().add(this.myLabelImage);
//        this.root.getChildren().add(this.myLabelVid);
//        this.root.getChildren().add(this.imageOutput);
//
//        primaryStage.setScene(new Scene(this.root, 500.0D, 500.0D));
//        primaryStage.show();
//    }
//}
