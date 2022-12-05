import java.io.BufferedReader;
import java.io.IOException;

public class ThreadChat implements Runnable
{
    BufferedReader receive;
    public ThreadChat (BufferedReader receive)
    {
        this.receive = receive;
    }
    public void run ()
    {
        try
        {
            while (true)
            {
                System.out.println(receive.readLine());
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
}