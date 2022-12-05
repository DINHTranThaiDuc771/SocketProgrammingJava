import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
public class Client {
	public static void main (String[] a)
	{
		try
		{
			//Connect to serveur
            Socket toServer  = new Socket ("127.0.0.1", 6000);
            PrintWriter    send    = new PrintWriter   (toServer.getOutputStream(), true);
			BufferedReader receive = new BufferedReader( new InputStreamReader(toServer.getInputStream() ) );
			Thread threadChat = new Thread(new ThreadChat(receive));
			threadChat.start();
			System.out.println (receive.readLine() );// affiche a l'eran message du serveur

            Scanner scCli = new Scanner(System.in);
            String mess = scCli.nextLine();

            while(!mess.equals("quit"))
            {
                send.println(mess);
                mess = scCli.nextLine();
            }
			send.println("Un client déconnecté");
			send.close();
			receive.close();
			toServer.close();
            scCli.close();
		}
		catch (IOException e) {e.printStackTrace();}

	}

}
