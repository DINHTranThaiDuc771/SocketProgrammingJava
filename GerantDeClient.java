import java.net.*;
import java.util.Scanner;
import java.io.*;
public class GerantDeClient implements Runnable
{
	private Socket socketContacClient; // socket Serveur side that bond with port and ip of client
	private PrintWriter send;
	private BufferedReader receive;
	public GerantDeClient(Socket socketContacClient)
	{
		this.socketContacClient = socketContacClient;
	}
	public void run()
	{
		try
		{

			send    = new PrintWriter   (socketContacClient.getOutputStream(), true);
			receive = new BufferedReader( new InputStreamReader(socketContacClient.getInputStream() ) );
			
			send.println("Bienvenue sur le serveur de DUC");

			while (send!=null && receive!=null)
			{
				System.out.println(receive.readLine());
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}	

}
