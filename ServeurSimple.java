import java.net.*;
import java.util.Scanner;
import java.io.*;
public class ServeurSimple
{
	private ServerSocket ss ;
	public ServeurSimple()
	{
		try
		{
			ss	= new ServerSocket(6000);
			Thread tAccep = new Thread (new ThreadAcceptClient());
			tAccep.start();
		} 
		catch (Exception e){e.printStackTrace();}		
	}
	class ThreadAcceptClient implements Runnable
	{
		public void run ()
		{
			try
			{
				while (true) // on boucle
				{
					Socket s = ss.accept();
					System.out.println("Un client connecté");

					// créer un GerantDeClient pour traiter ce nouveau client
					GerantDeClient gdc = new GerantDeClient(s);
					// mettre ce gérant de client dans une Thread
					Thread tgdc = new Thread(gdc);
					// lancer la thread qui gérera ce client
					tgdc.start();
					// ... et on recommence...
	
					PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
					BufferedReader in = new BufferedReader( new InputStreamReader(s.getInputStream()));
					Thread threadChat = new Thread(new ThreadChat(out, in));
					threadChat.start();
				}
			}
			catch(ConnectException e){e.printStackTrace();}
			catch(IOException e){e.printStackTrace();}
		}
	}
	class ThreadChat implements Runnable
	{
		PrintWriter out;
		BufferedReader in;
		public ThreadChat (PrintWriter out, BufferedReader in)
		{
			this.out = out;
			this.in  = in;
		}
		public void run ()
		{

			Scanner scCli = new Scanner(System.in);
			String mess = scCli.nextLine();

			while(!mess.equals("quit"))
			{
				out.println(mess);
				mess = scCli.nextLine();
			}
			scCli.close();
		}
	}
	public static void main(String[] args) {
		new ServeurSimple();
	}
}
