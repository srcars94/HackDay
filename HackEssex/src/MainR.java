/**
 * Created by Matthew on 03/12/2016.
 */
public class MainR {
    public static void main(String[] args) {
        try
        {
            ClientInterface ci = new ClientInterface();
            Thread ciThread = new Thread(ci);
            ciThread.start();
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
