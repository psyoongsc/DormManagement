package DormitoryProgram;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer implements Runnable {
   private MainServerThread clients[] = new MainServerThread[50]; // index 같게 처리
   private ServerSocket server = null;
   private Thread thread = null;
   private int clientCount = 0;

   private OutputStream os = null;
   private int cnt = 0;
   private Protocol protocol = null;
   private SQL sql = null;

   private int size = 0;

   public MainServer(int port) {
      try {
         protocol = new Protocol();
         sql = new SQL("127.0.0.1", "root", "Dtd0613~~");
         // sql = new SQL("192.168.220.14");

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

   public synchronized void handle(OutputStream os, FileOutputStream fs, int ID, byte[] buf) throws IOException {

      int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음
      protocol.setPacket(packetType, buf); // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사

      System.out.println("타입"+ buf[0]);
      switch (packetType) {

      case Protocol.PT_EXIT: // 프로그램 종료 수신
         protocol = new Protocol(Protocol.PT_EXIT);
         os.write(protocol.getPacket());
         System.out.println(protocol.getPacket()[0]);

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
            protocol = new Protocol(Protocol.PT_REQ_LOGIN);

         } else {
            protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
            String queryResult;
            if (sql.getInfo().getId().toString().charAt(0) == 'a') {
               queryResult = sql.getInfo().getId() + "#" + sql.getInfo().getName() + "#"
                     + sql.getInfo().getPhoneNumber();
            } else {
               queryResult = sql.getInfo().getId() + "#" + sql.getInfo().getName() + "#"
                     + sql.getInfo().getStatus() + "#" + sql.getInfo().getSex() + "#"
                     + sql.getInfo().getPhoneNumber();
            }
            // 로그인한 사람의 정보를 가져오는 방식이다.
            protocol.setQueryResult(queryResult);
            System.out.println(ID + " : " + queryResult);
            protocol.setLoginResult("1");
            System.out.println(ID + " : 로그인 성공");
            clients[findClient(ID)].getStudentInfo().setId(id);
         }

         System.out.println(ID + " : 로그인 처리 결과 전송\n");
         os.write(protocol.getPacket());
         System.out.println(protocol.getPacket()[0]);

         break;

      case Protocol.PT_REQ_UPLOAD: // 파일 전송 요청 // 파일이름 , 사이즈, 분할여부,

         String filename = protocol.getFilename(); // null 일 수도
         int filesize = protocol.getFileSize();

         if (protocol.getSeqnum().equals("0")) {
            // 서버측에서 저장할 폴더 : 바탕화면 - 학생의 학번 폴더
            System.out.println(filename + ", " + filesize / 1024 + "KB, " + filesize + "Byte 파일을 받았습니다. ");
            clients[findClient(ID)].setTempSize(filesize);
         }

         if (protocol.isLast().equals("1")) { // 마지막 분할
            fs.write(
                  buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_SIZE + Protocol.LEN_FILE_NAME
                        + Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM,
                  clients[findClient(ID)].getTempSize()); // 파일에다 씀.

            protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 다받았음.
            protocol.setUploadResult("3");
            os.write(protocol.getPacket());
            System.out.println("모든 분할 데이터 수신 완료 !");
            sql.submitTuberCertificate(filename, clients[findClient(ID)].getStudentInfo().getId());
            fs.close();

         } else { // 마지막 분할 X
            fs.write(buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_SIZE + Protocol.LEN_FILE_NAME
                  + Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM, 2000); // 버퍼에 쓰인 파일데이터를 write

            protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 클라이언트에게 다음 분할 데이터를 전달 요청.
            protocol.setUploadResult("2");

            os.write(protocol.getPacket());
         }

         if (clients[findClient(ID)].getTempSize() >= 2000) { // 남은 데이터 양
            clients[findClient(ID)].setTempSize(clients[findClient(ID)].getTempSize() - 2000);
         } else
            clients[findClient(ID)].setTempSize(0);

         break;

      case Protocol.PT_REQ_UPLOAD_MANAGER: // 업로드
         filename = protocol.getFilenameUM();
         filesize = protocol.getFileSizeUM();

         if (protocol.getSeqnumUM().equals("0")) {
            // 서버측에서 저장할 폴더 : 바탕화면 - 학생의 학번 폴더
            clients[findClient(ID)].setTempSize(filesize);
         }

         if (filename == null) {
            protocol = new Protocol(Protocol.PT_RES_UPLOAD);
            protocol.setUploadResultUM("1");
            os.write(protocol.getPacket());

            break;
         }

         if (protocol.isLastUM().equals("1")) { // 마지막 분할
            fs.write(buf,
                  Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_STUDENT_NUMBER + Protocol.LEN_FILE_SIZE
                        + Protocol.LEN_FILE_NAME + Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM,
                  clients[findClient(ID)].getTempSize()); // 파일에다 씀.
            protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 다받았음.
            protocol.setUploadResultUM("3");

            os.write(protocol.getPacket());
            System.out.println("모든 분할 데이터 수신 완료 !");
            sql.submitTuberCertificate(filename, clients[findClient(ID)].getUpdownStudentNum());
            fs.close();
         } else {
            fs.write(buf,
                  Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_STUDENT_NUMBER + Protocol.LEN_FILE_SIZE
                        + Protocol.LEN_FILE_NAME + Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM,
                  2000); // 버퍼에 쓰인 파일데이터를 write

            protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 클라이언트에게 다음 분할 데이터를 전달 요청.
            protocol.setUploadResultUM("2");

            os.write(protocol.getPacket());
         }

         if (clients[findClient(ID)].getTempSize() >= 2000) { // 남은 데이터 양
            clients[findClient(ID)].setTempSize(clients[findClient(ID)].getTempSize() - 2000);
         } else
            clients[findClient(ID)].setTempSize(0);

         break;
         
      case Protocol.PT_REQ_DOWNLOAD:
    	  
    	  	if(clients[findClient(ID)].getCntD() == 0) {
          	System.out.println("학번" + protocol.getStudentNum()); // 받은 학번
          	
              clients[findClient(ID)].setFilenameD(sql.getFileName(protocol.getStudentNum()));
           }
            // 파일크기설정, 분할
    	  	
            protocol = new Protocol(protocol.PT_RES_DOWNLOAD); // LEN_FRAG + LEN_LAST + LEN_FILE_SIZE + LEN_MAX
            
            protocol.setFileSizeD(clients[findClient(ID)].getFilesizeD());
            protocol.setFilenameD(clients[findClient(ID)].getFilenameD());
            //protocol.setFragD("1"); // 분할여부

            // 한 파일 전송 끝날 때
            if (clients[findClient(ID)].getDataD().size() - 1 == clients[findClient(ID)].getCntD()) { // cnt == 103이면,,,
                System.out.println("모든 분할 파일 전송 완료 !");
                protocol.setLastD("1"); // 마지막
                protocol.setSeqnumD(Integer.toString(clients[findClient(ID)].getCntD()));
                protocol.setFileD(clients[findClient(ID)].getDataD().get(clients[findClient(ID)].getCntD()));
                
                clients[findClient(ID)].setCntD(0);
                os.write(protocol.getPacket());
                
                clients[findClient(ID)].setB_Cnt(0);
                clients[findClient(ID)].DNULL();
            }
            else {
                protocol.setLastD("0");
                protocol.setSeqnumD(Integer.toString(clients[findClient(ID)].getCntD()));
                protocol.setFileD(clients[findClient(ID)].getDataD().get(clients[findClient(ID)].getCntD()));
                clients[findClient(ID)].setCntD(clients[findClient(ID)].getCntD() + 1);

                os.write(protocol.getPacket());
            }
            break;

      case Protocol.PT_REQ_APPLICATION: // 생활관신청 정보 수신
          System.out.println("클라이언트가 " + "생활관 신청 정보를 보냈습니다");

          System.out.println(protocol.getId());
          // 클라이언트가 보낸 정보를 sql클래스 안의 setInfo()로 보냄.
          sql.setAinfo(protocol.getAId(), protocol.getPeriod(), protocol.getDorm(), protocol.getDesire(),
                protocol.getMeal(), protocol.getPeriod1(), protocol.getDorm1(), protocol.getDesire1(),
                protocol.getMeal1(), protocol.getPeriod2(), protocol.getDorm2(), protocol.getDesire2(),
                protocol.getMeal2(), protocol.getPeriod3(), protocol.getDorm3(), protocol.getDesire3(),
                protocol.getMeal3());

          // 1년 신청
          String result = sql.getAinfo().getId() + sql.getAinfo().getPeriod() + sql.getAinfo().getDorm()
                + sql.getAinfo().getDesire() + sql.getAinfo().getMeal();
          // 1학기 신청
          String result1 = sql.getAinfo().getId() + sql.getAinfo().getPeriod1() + sql.getAinfo().getDorm1()
                + sql.getAinfo().getDesire1() + sql.getAinfo().getMeal1();
          String result2 = sql.getAinfo().getId() + sql.getAinfo().getPeriod2() + sql.getAinfo().getDorm2()
                + sql.getAinfo().getDesire2() + sql.getAinfo().getMeal2();
          String result3 = sql.getAinfo().getId() + sql.getAinfo().getPeriod3() + sql.getAinfo().getDorm3()
                + sql.getAinfo().getDesire3() + sql.getAinfo().getMeal3();

          System.out.println(sql.getAinfo().getDorm());
          System.out.println(sql.getAinfo().getDorm1());
          System.out.println(sql.getAinfo().getDorm2());
          System.out.println(sql.getAinfo().getDorm3());
          System.out.println("정보입력");
          sql.insertStudent(result);
          sql.insertStudent1(result1);
          sql.insertStudent2(result2);
          sql.insertStudent3(result3);

          // 신청 성공
          protocol = new Protocol(Protocol.PT_RES_APPLICATION);
          protocol.setResApplication("1");
          System.out.println("신청완료");
          System.out.println("신청 결과 전송\n");
          os.write(protocol.getPacket());
          break;
         
      case Protocol.PT_REQ_INQUIRE_ALL : // 모든조회 요청 수신 및 응답
          if(protocol.getWhere().equals("1")) {
             System.out.println("클라이언트가 " + "일정조회 요청을 보냈습니다.");
             List<Calender> list = sql.c_selectAll();
             String resultQuery = "";

             //관리자_일정조회
             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getDayname() + "#" + list.get(i).getFirstday() + "#"
                            + list.get(i).getLastday() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          //관리자_금액조회
          if(protocol.getWhere().equals("2")) {
             System.out.println("클라이언트가 " + "금액조회 요청을 보냈습니다.");
             List<Money> list = sql.m_selectAll();
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getDorm() + "#" + list.get(i).getPeriod() + "#"
                            + list.get(i).getFirstday() + "#" + list.get(i).getLastday() + "#" +list.get(i).getSdaymeal() + "#" +list.get(i).getFdaymeal() + "#" + list.get(i).getNomeal() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          //관리자_입사자 목록 조회 요청(합격자)
          if(protocol.getWhere().equals("3")) {
             System.out.println("클라이언트가 " + "입사자 목록 조회 요청을 보냈습니다.");
             List<Applicant> list = sql.a_selectAll();
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getStudentid() + "#" + list.get(i).getStudentname() + "#"
                            + list.get(i).getDorm() + "#" + list.get(i).getPeriod() + "#"
                            + list.get(i).getRoomtype() + "#" +list.get(i).getRrn() + "#" + list.get(i).getGrade() + "#"
                            + list.get(i).getDistanceaddpoint() + "#" + list.get(i).getScoresum() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          //관리자_입사선발자 목록 조회(신청자)
          if(protocol.getWhere().equals("4")) {
             System.out.println("클라이언트가 " + "입사선발자 목록 조회 요청을 보냈습니다.");
             List<Applicant> list = sql.a_selectAll();
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getStudentid() + "#" + list.get(i).getStudentname() + "#"
                            +list.get(i).getDorm() + "#" + list.get(i).getDesire() + "#" +list.get(i).getPeriod() + "#"
                            + list.get(i).getRrn() + "#" +list.get(i).getSex() + "#" + list.get(i).getGrade() + "#"
                            + list.get(i).getDistanceaddpoint() + "#" + list.get(i).getScoresum() + "#";
                      resultQuery += tmp;
                   }
                   System.out.println(resultQuery);
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          //관리자_결핵진단서 제출자 목록조회
          if(protocol.getWhere().equals("5")) {
             System.out.println("클라이언트가 " + "결핵진단서 목록 조회 요청을 보냈습니다.");
             List<Tuber> list = sql.t_selectAll();
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getStudentid() + "#" + list.get(i).getFilename() + "#"
                            +list.get(i).getSubmitdate() + "#" + list.get(i).getDiagnosisdate() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
       case Protocol.PT_REQ_INQUIRE_ONE :
          //학생_생활관신청확인요청
          if(protocol.getWhere().equals("6")) {
             System.out.println("클라이언트가 " + "생활관 신청확인 조회 요청을 보냈습니다.");
             String stunum = protocol.getStudentNumINQ();
             List<ApplicationConfirm> list = sql.ac_selectONE(stunum);
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getDorm() + "#" + list.get(i).getPeriod() + "#"
                            +list.get(i).getMeal() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          //학생_호실확인조회(호실배정결과)
          else if(protocol.getWhere().equals("7")) {
             System.out.println("클라이언트가 " + "호실확인 조회 요청을 보냈습니다.");
             
             String studentid = protocol.getStudentNumINQ();
             System.out.println(studentid);
             List<CheckRoom> list = sql.cr_selectONE(studentid);
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getMeal() + "#" + list.get(i).getDorm() + "#" + list.get(i).getRoomtype() + "#"
                            + list.get(i).getRoomnum() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }

          //신청창 들어갈때 학생id로 성별 구분해서 생활관 뜰수있도록 하기위함.
          else if(protocol.getWhere().equals("8")) {
             System.out.println("클라이언트가 " + "생활관 조회 요청을 보냈습니다.");
             String stunum = null;
             stunum = protocol.getStudentNumINQ();
             List<Dorminquire> list = sql.d_selectONE(stunum);
             String resultQuery = "";

             if (list.isEmpty()) {
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                System.out.println("조회할 데이터가 없음");
                protocol.setResult("4");
                System.out.println("조회 결과 전송\n");
                os.write(protocol.getPacket());
                break;
             } else {
                try {
                   for (int i = 0; i < list.size(); i++) {
                      String tmp = list.get(i).getDorm() + "#" + list.get(i).getNomealprovided() + "#" + list.get(i).getSmeal() + "#"
                            + list.get(i).getFmeal() + "#" + list.get(i).getDormfee() + "#" + list.get(i).getOthercharge() + "#";
                      resultQuery += tmp;
                   }
                } catch (IndexOutOfBoundsException e) {
                   System.out.println(e);
                }
                protocol = new Protocol(Protocol.PT_RES_INQUIRE);
                protocol.setResult("3");
                System.out.println("조회완료");
                System.out.println("조회 결과 전송\n");
                protocol.setQueryResultINQ(resultQuery);
                os.write(protocol.getPacket());
                break;
             }
          }
          
          else if(protocol.getWhere().equals("9")) { 
        	  
				System.out.println("클라이언트가 " + "출력 요청을 보냈습니다.");
				String stunum = null;
				stunum = protocol.getStudentNumINQ();
				System.out.println("출력요청학번" +stunum);
				
				List<Print> list = sql.p_selectONE(stunum);
				String resultQuery = "";

				if (list.isEmpty()) {
					protocol = new Protocol(Protocol.PT_RES_INQUIRE);
					System.out.println("조회할 데이터가 없음");
					protocol.setResult("4");
					System.out.println("조회 결과 전송\n");
					os.write(protocol.getPacket());
					
					break;
				} else {
					try {
						String tmp = "";
						for (int i = 0; i < list.size(); i++) { // 
							tmp = list.get(i).getStudentid() + "#" + list.get(i).getStudentname() + "#" + list.get(i).getDorm() + "#"
		                              + list.get(i).getPeriod() + "#" + list.get(i).getMeal() + "#" +list.get(i).getDormfee() + "#" + 
									list.get(i).getMealfee() + "#" + list.get(i).getOthercharge() + "#" + list.get(i).getTotalfee() + 
									"#" + list.get(i).getAccountnum() + "#";
		                        resultQuery += tmp;
						}
						System.out.println(tmp);
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e);
					}
					protocol = new Protocol(Protocol.PT_RES_INQUIRE);
					protocol.setResult("3");
					System.out.println("조회완료");
					System.out.println("조회 결과 전송\n");
					protocol.setQueryResult(resultQuery);
					os.write(protocol.getPacket());
					break;
				}
			}
          
       case Protocol.SEND_DATA:        // ID 정보 수신
             System.out.println("클라이언트가 " + "데이터를 보냈습니다"); // data는 학번
             
             String data = protocol.getData();
             String type = protocol.getWhere();
             
             System.out.println(type); 
             System.out.println(data);
             System.out.println(sql.RegisterDormitoryInfo(type, data)); //이거는 튜플 몇 개 등록되나 확인 차원에서...!
             
             protocol = new Protocol(Protocol.RES_DATA); // 일치하는 아이디가 존재한다면 비밀번호를 요청한다.
             os.write(protocol.getPacket());
             break;
             
       case Protocol.PT_REQ_OPEN: // 20
    	   
    	   	String studentNum = protocol.getStudentNum();
    	   	String kindofOpen = protocol.getKindofOpen();
    	   	String str = "";
    	   	
    	   	switch(kindofOpen) {
    	   	
    	   	case "1":
    	   		str = sql.showUpdateSchedule();
    	   		break;
    	   	case "2":
    	   		str = sql.showUpdateDorForApplication();
    	   		break;
    	   	case "3":
    	   		str = sql.showAdminDorApplication();
    	   		break;
    	   	case "4":
    	   		//str = sql.showUpdateDormitory(); // sql문 전달 할 필요 X
    	   		break;
    	   	case "5":
                str = sql.AdminDeposit();
                 break;
            case "6":
                str = sql.AdminTuberCertificate();
                 break;
            case "7":
                str = sql.AdminFinalSelected();
                 break;
            case "8":
                str = sql.StudentDorInfo();
                 break;
                 
            case "9":
                List<Dorminquire> doList = new ArrayList<Dorminquire>();
                System.out.println( " : " + protocol.getStudentNumOpen());
                doList = sql.d_selectAll(protocol.getStudentNumOpen());
                Dorminquire dorminquire;
                
                for(int i=0; i<doList.size(); i++) {
                   dorminquire = doList.get(i);
                   str += dorminquire.getDorm() + "#" + dorminquire.getNomealprovided() + "#" + dorminquire.getDormfee() + "#" + dorminquire.getSmeal() + "#" + dorminquire.getFmeal() + "#" + dorminquire.getOthercharge();
                   if(i != doList.size() - 1)
                      str += "#";
                }
                
                System.out.println(str);
                break;
             
            case "10":
                List<ApplicationConfirm> acList = new ArrayList<ApplicationConfirm>();
                acList = sql.ac_selectONE(protocol.getStudentNumOpen());
                ApplicationConfirm ac;

                for (int i = 0; i < 4; i++) {
                   ac = acList.get(i);
                   str += ac.getDorm() + "#" + ac.getMeal() + "#";
                }

                List<ApplicationConfirm1> ac1List = new ArrayList<ApplicationConfirm1>();
                ac1List = sql.ac_selectONE1(protocol.getStudentNumOpen());
                ApplicationConfirm1 ac1 = ac1List.get(0);
                str += ac1.getDorm() + "#" + ac1.getPeriod() + "#" + ac1.getMeal() + "#" + ac1.getPayment() + "#"
                      + ac1.getPassORnot();
                break;
    	   		
    	   	case "11": // 결핵진단서 제출
    	   		str = sql.showStudentTuberCertificate(studentNum);
    	   		break;
    	   		
    	    case "12":
                List<CheckRoom> list = new ArrayList<CheckRoom>();
                list = sql.cr_selectONE(protocol.getStudentNumOpen());
                CheckRoom cr = list.get(0);

                str += cr.getDorm() + "#" + cr.getRoomtype() + "#" + cr.getMeal() + "#" + cr.getRoomnum();
                break;
    	   	}
    	   	
    	    protocol = new Protocol(Protocol.PT_RES_OPEN);
    	    protocol.setOpenQueryResult(str);
    	    os.write(protocol.getPacket());
    	    
    	    break;
    	    
       case Protocol.PT_REQ_CAN:
    	   
    	   String stunum = protocol.getStudentNum(); // 20180650
    	   String kOpen = protocol.getKindofOpen(); // 2
    	   System.out.println(stunum + "," + kOpen);
    	   
    	   String rs = sql.CAN_OPEN(kOpen, stunum);
    	   
    	   protocol = new Protocol(Protocol.PT_RES_CAN);
    	   protocol.setcanOpen(rs);
    	   os.write(protocol.getPacket());
    	   
      }// end switch
      
   }

   public static void main(String[] args) {
      MainServer server = new MainServer(3000);
   }
} //////////// MAIN SERVER