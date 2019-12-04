package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import PSY.SQL;
import YJ.Protocol;

public class LoginServer{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
	        ServerSocket sSocket = new ServerSocket(3000);
	        System.out.println("클라이언트 접속 대기중...");
	        Socket socket = sSocket.accept();
	        System.out.println("클라이언트 접속");
	
	        // 바이트 배열로 전송할 것이므로 필터 스트림 없이 Input/OutputStream만 사용해도 됨
	        OutputStream os = socket.getOutputStream();
	        InputStream is = socket.getInputStream();
	
	        // 로그인 정보 요청용 프로토콜 객체 생성 및 전송
	        Protocol protocol = new Protocol(Protocol.PT_REQ_LOGIN);
	        os.write(protocol.getPacket());
	
	        boolean program_stop = false;
	        int chance = 5;
	
	        while(true){
	            protocol = new Protocol();			// 새 Protocol 객체 생성 (기본 생성자)
	            byte[] buf = protocol.getPacket();	// 기본 생성자로 생성할 때에는 바이트 배열의 길이가 1000바이트로 지정됨
	            is.read(buf);						// 클라이언트로부터 로그인정보 (ID와 PWD) 수신
	            int packetType = buf[0];			// 수신 데이터에서 패킷 타입 얻음
	            protocol.setPacket(packetType,buf);	// 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사
	
	            switch(packetType){
	                case Protocol.PT_EXIT:			// 프로그램 종료 수신
	                    protocol = new Protocol(Protocol.PT_EXIT);
	                    os.write(protocol.getPacket());
	                    program_stop = true;
	                    System.out.println("서버종료");
	                    break;
	
	                case Protocol.PT_RES_LOGIN:		// 로그인 정보 수신
	                    System.out.println("클라이언트가 " + "로그인 정보를 보냈습니다");
	                    String id = protocol.getId();
	                    String password = protocol.getPassword();
	                    System.out.println(id+" "+password);
	
	                    SQL sql = new SQL();
	                    sql.setInfo(id.toString().trim(), password.toString().trim());
	                    if(id.charAt(0) == 'a'){
	                        sql.doLogin(1); // 얘는 관리자고 (관리자 id는 admin1, admin2 이런 식으로 되어있다.)
	                    }else{
	                        sql.doLogin(2); // 얘는 학생이다.
	                    }
	
	                    if(sql.getInfo().getName() == null) { //
	                        System.out.println("암호 틀림");
	                        chance = chance - 1;
	                        if (chance > 0) {
	                            protocol = new Protocol(Protocol.PT_REQ_LOGIN);
	                        } else {
	                            protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
	                            protocol.setLoginResult("2");
	                        }
	                    }else{
	                        protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
	                        String queryResult;
	                        if (sql.getInfo().getId().toString().charAt(0) == 'a') {
	                            queryResult = sql.getInfo().getId() + "," + sql.getInfo().getName() + "," + sql.getInfo().getPhoneNumber();
	                        }else{
	                            queryResult = sql.getInfo().getId() + "," + sql.getInfo().getName() + "," + sql.getInfo().getStatus() + "," + sql.getInfo().getSex() + "," + sql.getInfo().getPhoneNumber();
	                        }
	                        //로그인한 사람의 정보를 가져오는 방식이다.
	                        protocol.setQueryResult(queryResult);
	                        System.out.println(queryResult);
	                        protocol.setLoginResult("1");
	                        System.out.println("로그인 성공");
	                    }
	
	                    System.out.println("로그인 처리 결과 전송\n");
	                    os.write(protocol.getPacket());
	                    break;
	            }//end switch
	            if(program_stop) break;
	
	        }//end while
	        

	        is.close();
	        os.close();
	        socket.close();
    }
}
