package server;

import java.net.*;
import java.io.*;

import PSY.LoginInfo;

public class MainServerThread extends Thread {
	private MainServer server = null;
	private Socket socket = null;
	private int ID = -1;
	private InputStream	is = null;
	private OutputStream os = null;
	private LoginInfo studentInfo = null;

	public MainServerThread(MainServer _server, Socket _socket) {
		super();
		server = _server;
		socket = _socket;
		ID = socket.getPort();
	}

	public void send(byte[] packet) {
		try {
			os.write(packet); // packet 보내는걸로 바꿔야됨
			os.flush();
		} catch (IOException ioe) {
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			server.remove(ID);
			stop();
		}
	}

	public int getID() {
		return ID;
	}
	public LoginInfo getStudentInfo() {
		return studentInfo;
	}

	public void run() {
		System.out.println("Server Thread " + ID + " running.");
		byte[] buf = new byte[1000];
		try {
			is.read(buf);
			server.handle(os, ID, buf);
		} catch (IOException ioe) {
			System.out.println(ID + " ERROR reading: " + ioe.getMessage());
			server.remove(ID);
			stop();
		}
	}

	public void open() throws IOException {
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}

	public void close() throws IOException {
		if (socket != null)
			socket.close();
		if (is != null)
			is.close();
		if (os != null)
			os.close();
	}
}
