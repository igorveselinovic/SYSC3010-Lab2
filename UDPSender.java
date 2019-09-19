import java.net.*;
import java.util.Scanner;

public class UDPSender {

	private static final int PACKETSIZE = 100;

	public static void main(String[] args)
	{
		// Check the arguments
		if( args.length != 3 )
		{
			System.out.println( "usage: java UDPSender host port numberOfMessages" ) ;
			return ;
		}
		DatagramSocket socket = null ;
		try
		{
			// Convert the arguments first, to ensure that they are valid
			InetAddress host = InetAddress.getByName( args[0] ) ;
			int port         = Integer.parseInt( args[1] ) ;
			int n            = Integer.parseInt( args[2] ) ;
			socket = new DatagramSocket() ;

			for (int i = 0; i < n; i++)
			{
				byte [] data = ("message" + i).getBytes() ;
				DatagramPacket sendPacket = new DatagramPacket( data, data.length, host, port ) ;
				System.out.println("Sending " + new String(data)) ;
				socket.send( sendPacket ) ;

				DatagramPacket receivePacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE) ;
				socket.receive( receivePacket ) ;
				System.out.println( receivePacket.getAddress() + " " + receivePacket.getPort() + ": " + new String(receivePacket.getData()).trim() ) ;
			}
			System.out.println ("Closing down");
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
		finally
		{
			if( socket != null )
			socket.close() ;
  		}
	}
}

