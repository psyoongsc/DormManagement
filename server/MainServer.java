package server;

import java.net.*;

import java.io.*;

import PSY.LoginInfo;
import PSY.SQL;
import YJ.Protocol;

public class MainServer implements Runnable {
	private MainServerThread clients[] = new MainServerThread[50]; // index 같게 처리
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;

	private OutputStream os = null;

	private Protocol protocol = null;
	private SQL sql = null;

	public MainServer(int port) {
		try {
			protocol = new Protocol();
			sql = new SQL("127.0.0.1", "root", "fss7182963");

			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
		}
	}

	public void run() {
		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
				stop();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private int findClient(int ID) {
		for (int i = 0; i < clientCount; i++)
			if (clients[i].getID() == ID)
				return i;
		return -1;
	}

	public synchronized void remove(int ID) {
		int pos = findClient(ID);
		if (pos >= 0) {
			MainServerThread toTerminate = clients[pos];
			System.out.println("Removing client thread " + ID + " at " + pos);
			if (pos < clientCount - 1)
				for (int i = pos + 1; i < clientCount; i++)
					clients[i - 1] = clients[i];
			clientCount--;
			try {
				toTerminate.close();
			} catch (IOException ioe) {
				System.out.println("Error closing thread: " + ioe);
			}
			toTerminate.stop();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	private void addThread(Socket socket) {
		if (clientCount < clients.length) {
			System.out.println("Client accepted: " + socket);
			clients[clientCount] = new MainServerThread(this, socket);
			try {
				clients[clientCount].open(); // Stream open
				clients[clientCount].start();
				clientCount++;
			} catch (IOException ioe) {
				System.out.println("Error opening thread: " + ioe);
			}
		} else
			System.out.println("Client refused: maximum " + clients.length + " reached.");
	}

	public synchronized void handle(InputStream is, OutputStream os, int ID, byte[] buf) throws IOException{

		boolean login_stop = false;
		boolean login_success = false;
		int chance = 5;

		while (true) {
			is.read(buf);
			int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음
			protocol.setPacket(packetType, buf); // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사

			switch (packetType) {
			case Protocol.PT_EXIT: // 프로그램 종료 수신
				protocol = new Protocol(Protocol.PT_EXIT);
				os.write(protocol.getPacket());
				login_stop = true;
				System.out.println(ID + " : 로그인 종료");
				break;

			case Protocol.PT_RES_LOGIN: // 로그인 정보 수신
				System.out.println(ID + " : 클라이언트가 " + "로그인 정보를 보냈습니다");
				String id = protocol.getId();
				String password = protocol.getPassword();

				sql.setInfo(id.toString().trim(), password.toString().trim());
				if (id.charAt(0) == 'a') {
					sql.doLogin(1); // 얘는 관리자고 (관리자 id는 admin1, admin2 이런 식으로 되어있다.)
				} else {
					sql.doLogin(2); // 얘는 학생이다.
				}

				if (sql.getInfo().getName() == null) { //
					System.out.println(ID + " : 암호 틀림");
					chance = chance - 1;
					if (chance > 0) {
						protocol = new Protocol(Protocol.PT_REQ_LOGIN);
					} else {
						protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
						protocol.setLoginResult("2");
					}
				} else {
					protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
					String queryResult;
					if (sql.getInfo().getId().toString().charAt(0) == 'a') {
						queryResult = sql.getInfo().getId() + "," + sql.getInfo().getName() + ","
								+ sql.getInfo().getPhoneNumber();
					} else {
						queryResult = sql.getInfo().getId() + "," + sql.getInfo().getName() + ","
								+ sql.getInfo().getStatus() + "," + sql.getInfo().getSex() + ","
								+ sql.getInfo().getPhoneNumber();
					}
					// 로그인한 사람의 정보를 가져오는 방식이다.
					protocol.setQueryResult(queryResult);
					System.out.println(ID + " : " + queryResult);
					protocol.setLoginResult("1");
					System.out.println(ID + " : 로그인 성공");
					login_success = true;
				}

				System.out.println(ID + " : 로그인 처리 결과 전송\n");
				os.write(protocol.getPacket());
				break;
			}// end switch
			if (login_stop)
				break;

		} // end while
		
		//다른 기능들 대기
		if(login_success) {
			// 다른 기능 여기 넣어
		} else {
			remove(ID);
			return;
		}
	}

	public static void main(String[] args) {
		MainServer server = null;
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		else
			server = new MainServer(Integer.parseInt(args[0]));
	}
}
