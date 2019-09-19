
import java.net.*;

public class UDPReceiver {

	private final static int PACKETSIZE = 100 ;
	private final static String PREFIX = "ACK: " ;

	public static void main( String args[] )
	{
		// Check the arguments
		if( args.length != 1 )
		{
			System.out.println( "usage: UDPReceiver port" ) ;
			return ;
		}
		try
		{
			// Convert the argument to ensure that is it valid
			int port = Integer.parseInt( args[0] ) ;

			// Construct the socket
			DatagramSocket socket = new DatagramSocket( port ) ;

			for( ;; )
			{
				System.out.println( "Receiving on port " + port ) ;
				DatagramPacket receivePacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
				socket.receive( receivePacket ) ;
				String message = new String(receivePacket.getData()).trim() ;
				System.out.println( receivePacket.getAddress() + " " + receivePacket.getPort() + ": " + message ) ;

				byte[] sendData = (PREFIX + message).getBytes() ;
				DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort() ) ;
				System.out.println("Sending " + new String(sendData) + "\n") ;
				socket.send( sendPacket ) ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
    }
}

