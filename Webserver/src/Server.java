import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void run() throws IOException {
		int port = 8010; // client listen
		ServerSocket socket = new ServerSocket(port);// Server open on this port
		// what is my ip address
		socket.setSoTimeout(10000);// socket wait 10sec then close automatically
		// this will free our port

		while (true) {
			try {
				System.out.println("Server is listening on port" + port);
				Socket acceptedConnection = socket.accept();
				System.out.println("Connection accepted from client"+acceptedConnection.getRemoteSocketAddress());
				PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
				BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
				toClient.println("Hello from the server");
				toClient.flush();
				
				fromClient.close();
				toClient.close();
				acceptedConnection.close();
				
				
			} catch (IOException e) {

				e.printStackTrace();
			} // this will work for 10 sec if no req will come

		}

	}

	public static void main(String[] args) {
        Server server=new Server();
        try {
			server.run();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
