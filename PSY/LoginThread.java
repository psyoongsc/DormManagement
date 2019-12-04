package PSY;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import KMS.Login;

public class LoginThread extends Thread {
	private Socket socket = null;
	private Login client = null;

	public LoginThread(Login _client, Socket _socket) {
		client = _client;
		socket = _socket;
		this.start();
	}

	public void run() {
		while (true) {
			try {
				client.handle();
			} catch (IOException ioe) {
				System.out.println("Listening error: " + ioe.getMessage());
				client.stop();
			}
		}
	}
}
