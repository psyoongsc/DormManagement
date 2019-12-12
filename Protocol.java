package DormitoryProgram;

import java.io.*;
import java.sql.ResultSet;

public class Protocol implements Serializable {
   // 프로토콜 타입에 관한 변수
   public static final int PT_UNDEFINED = -1; // 프로토콜이 지정되어 있지 않은 경우
   public static final int PT_EXIT = 0; // 프로그램 종료
   public static final int PT_REQ_LOGIN = 1; // 로그인 요청
   public static final int PT_RES_LOGIN = 2; // 로그인 응답
   public static final int PT_LOGIN_RESULT = 3; // 인증 결과
   /////////////////////// 로그인 TYPE /////////////////////////////////
   public static final int PT_REQ_APPLICATION = 4; // 신청 데이터 전송
   public static final int PT_RES_APPLICATION = 5; // 응답
   ////////////////////// 신청 TYPE //////////////////////////////
   public static final int SEND_DATA = 6; //등록데이터 전송
   public static final int RES_DATA = 7; //데이터 수신 결과
   ////////////////////// 등록 TYPE //////////////////////////////
   public static final int PT_REQ_DOWNLOAD = 8; // 개별 파일 다운로드 요청
   public static final int PT_REQ_ALLDOWNLOAD = 11; // 일괄 파일 다운로드 요청
   public static final int PT_RES_DOWNLOAD = 9; // 파일 다운로드 요청에 대한 응답 (승인 / 거절)
   public static final int PT_RES_ALLDOWNLOAD = 10;
   ////////////////////// 다운로드 TYPE ////////////////////////////
   public static final int PT_REQ_UPLOAD = 12; // 파일 업로드 요청
   public static final int PT_REQ_UPLOAD_MANAGER = 14; // ★나중에 고침..
   public static final int PT_RES_UPLOAD = 13; // 업로드 요청에 대한 응답
   ////////////////////// 학생 UPLOAD, 매니저 UPLOAD TYPE/////////////////
   public static final int PT_REQ_INQUIRE_ALL = 15; // 전체 데이터 조회 요청
	public static final int PT_REQ_INQUIRE_ONE = 16; // 한사람에 대한 데이터 조회 요청
	public static final int PT_RES_INQUIRE = 17; // 응답 및 데이터 전송
	///////////////////// 조회 TYPE //////////////////////////////
	public static final int PT_REQ_OPEN = 18; // 한사람에 대한 데이터 조회 요청
	public static final int PT_RES_OPEN = 19; 
	////////////////////////////////////////////////////////////
	public static final int PT_REQ_CAN = 20; // 한사람에 대한 데이터 조회 요청
	public static final int PT_RES_CAN = 21; 
	///////////////////////////////////////////////////////////
	
	
   public static final int LEN_STUDENT_ID = 8; // 학생id
   public static final int LEN_APPLICATION_PERIOD = 15; // 기숙구분 길이
   public static final int LEN_APPLICATION_DORM = 40; // 기숙사명 길이
   public static final int LEN_APPLICATION_DESIRE = 15; // 지망번호
   public static final int LEN_APPLICATION_MEAL = 15; // 식사명 길이
  // public static final int LEN_QUERY_RESULT = 300; // 쿼리문 결과 길이
   public static final int LEN_RES_APPLICATION = 2; // 요청 인증 값 길이

   public static final int LEN_LOGIN_ID = 20; // ID 길이
   public static final int LEN_LOGIN_PASSWORD = 20; // PWD 길이
   public static final int LEN_LOGIN_RESULT = 2; // 로그인 인증 값 길이
   public static final int LEN_PROTOCOL_TYPE = 1; // 프로토콜 타입 길이
   public static final int LEN_KINDOFOPEN = 3;
   public static final int LEN_STUDENT_NUMBER = 17;
   public static final int LEN_FILE_NAME = 80; // 파일명 길이
   public static final int LEN_FILE_SIZE = 10; // 파일 크기
   public static final int LEN_RES_DOWNLOAD = 2; // 다운로드 요청 응답길이
   public static final int LEN_RES_RECEIVE = 2; // 수신 결과, 초기값 1
   public static final int LEN_UPLOAD_RESULT = 2; // 업로드 요청 응답길이
   public static final int LEN_FILE = 2001;

   public static final int LEN_FROMWHERE = 3; //어느 창에서 쓰여야하는지
   public static final int LEN_REGISTRESULT = 2; //로그인 결과
  // public static final int LEN_DATA = 5000; //데이터 크기
  // public static final int LEN_REGMAX = 10000; //최대 크기
   
   public static final int LEN_QUERY_RESULT = 5000;
   public static final int LEN_RES_INQUIRE = 2; // 조회 요청 응답 길이
   public static final int LEN_DATA_MAX = 10010; // 최대 데이터 길이
   
   public static final int LEN_LAST = 2;
   public static final int LEN_FRAG = 2;
   public static final int LEN_SEQNUM = 2;
   
   public static final int LEN_OX = 2;
   // public static final int LEN_MAX = 2114;
   
   public static final String EXTRAPOINT = "1"; //거리가산점
   public static final String SCHEDULE = "2"; //일정
   public static final String CAFETERIA = "3"; //급식실
   public static final String DORMITORY = "4"; //생활관
   public static final String DORM_FOR_APPLICATION = "5"; //생활관_신청
   public static final String DORM_ROOMNUMBER = "6"; //생활관_호실
   public static final String ROOMNUMBER_DETAILS = "7"; //호실상세
   public static final String SELECTION = "8"; //신청 -> 선발
   public static final String SELECTION_TO_DEPOSIT = "9"; //선발 -> 입금
   public static final String TEMP_PAYMENT = "10"; //이건 저희 영역이 아니므로 임시로 넣었습니다!
   public static final String UPDATE_PAYMENT_STATUS = "11"; //미납 -> 납부(입금)
   public static final String DEPOSIT_TO_TUBER_CERTIFICATE = "12"; //입금 -> 결핵진단서
   public static final String CERTIFICATE_TO_ASSIGN = "13"; // 결핵진단서 -> 배정
   public static final String OATH = "15"; //입사서약서
   public static final String DIAGNOSIS_DATE = "16"; 
   public static final String UPDATE_ROOMNUMBER = "17"; // 방 번호 배정해줌
   public static final String UPDATE_ACCOUTNUM = "18";
   
   protected int protocolType;
   private byte[] packet; // 프로토콜과 데이터의 저장공간이 되는 바이트 배열

   public Protocol() { // 생성자
      this(PT_UNDEFINED);
   }

   public Protocol(int protocolType) { // 생성자
      this.protocolType = protocolType;
      getPacket(protocolType);
   }

   // 프로토콜 타입에 따라 바이트 배열 packet의 length가 다름
   public byte[] getPacket(int protocolType) {
      if (packet == null) {
         switch (protocolType) {
         case PT_REQ_LOGIN:
            packet = new byte[LEN_PROTOCOL_TYPE];
            break;
         case PT_RES_LOGIN:
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_LOGIN_ID + LEN_LOGIN_PASSWORD];
            break;
         case PT_UNDEFINED:
            packet = new byte[LEN_DATA_MAX];
            break;
         case PT_LOGIN_RESULT:
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_LOGIN_RESULT + LEN_QUERY_RESULT];
            break;
         case PT_EXIT:
            packet = new byte[LEN_PROTOCOL_TYPE];
            break;
         case PT_REQ_UPLOAD: // 1
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM
                  + LEN_FILE]; // 58
            break;
         case PT_REQ_UPLOAD_MANAGER: // 관리자가 업로드 할 때 서버에게 학번정보 보냄
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG
                  + LEN_LAST + LEN_SEQNUM + LEN_FILE]; // 58
            break;
         case PT_RES_UPLOAD: // 3
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_UPLOAD_RESULT]; // 3
            break;
         //////////////////////////////////////////////////////// 업로드//////////////////////////////////////////////////
         case PT_REQ_DOWNLOAD: // 개별 다운
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER]; // 학번 전송 ex) 20180650\\결핵진단서.jpg
            break;
         case PT_REQ_ALLDOWNLOAD: // 일괄 다운
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_RES_RECEIVE];
            break;
         case PT_RES_DOWNLOAD: // 2
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + LEN_FILE]; // 파일명은
            break;
         case PT_RES_ALLDOWNLOAD: // 2
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM
                  + LEN_FILE]; // 사이즈는 받는 양 알기위해
            break;
         ///////////////////////////////////////// 신청//////////////////////////////////////////
         case PT_REQ_APPLICATION:
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL];
            break;
         case PT_RES_APPLICATION:
            packet = new byte[LEN_PROTOCOL_TYPE + LEN_RES_APPLICATION];
            break;
         case PT_REQ_INQUIRE_ALL:
				packet = new byte[LEN_PROTOCOL_TYPE + LEN_FROMWHERE];
				break;
		case PT_REQ_INQUIRE_ONE:
				packet = new byte[LEN_PROTOCOL_TYPE + LEN_FROMWHERE + LEN_STUDENT_NUMBER];
				break;
		case PT_RES_INQUIRE:
				packet = new byte[LEN_PROTOCOL_TYPE + LEN_RES_INQUIRE + LEN_QUERY_RESULT];
				break;
		 case SEND_DATA :
             packet = new byte[LEN_PROTOCOL_TYPE + LEN_FROMWHERE + LEN_QUERY_RESULT];
             break;
         case RES_DATA:
             packet = new byte[LEN_PROTOCOL_TYPE+LEN_REGISTRESULT];
             break;
         case PT_REQ_OPEN:
        	 packet = new byte[LEN_PROTOCOL_TYPE+ LEN_STUDENT_NUMBER + LEN_KINDOFOPEN];
        	 break;
         case PT_RES_OPEN:
        	 packet = new byte[LEN_PROTOCOL_TYPE+LEN_QUERY_RESULT];
        	 break;
         case PT_REQ_CAN:
        	 packet = new byte[LEN_PROTOCOL_TYPE+ LEN_STUDENT_NUMBER + LEN_KINDOFOPEN];
        	 break;
         case PT_RES_CAN:
        	 packet = new byte[LEN_PROTOCOL_TYPE+LEN_OX];
        	 break;
        	 
        	 
         } // end switch
      } // end if
      packet[0] = (byte) protocolType; // packet 바이트 배열의 첫 번째 바이트에 프로토콜 타입 설정
      return packet;
   }

   // 로그인후 성공/실패의 결과 값을 프로토콜로부터 추출하여 문자열로 리턴
   public String getLoginResult() {
      // String의 다음 생성자를 사용 : String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_LOGIN_RESULT).trim();
   }

   // String ok를 byte[]로 만들어서 packet의 프로토콜 타입 바로 뒤에 추가
   public void setLoginResult(String ok) {
      // arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

   public void setProtocolType(int protocolType) {
      this.protocolType = protocolType;
   }

   public int getProtocolType() {
      return protocolType;
   }

   public byte[] getPacket() {
      return packet;
   }

   // Default 생성자로 생성한 후 Protocol 클래스의 packet 데이터를 바꾸기 위한 메서드
   public void setPacket(int pt, byte[] buf) {
      packet = null;
      packet = getPacket(pt);
      protocolType = pt;
      System.arraycopy(buf, 0, packet, 0, packet.length);
   }

   public String getId() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_LOGIN_ID).trim();
   }

   // byte[] packet에 String ID를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setId(String id) {
      System.arraycopy(id.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, id.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + id.trim().getBytes().length] = '\0';
   }
   
   public String getAId() {
	      // String(byte[] bytes, int offset, int length)
	      return new String(packet, LEN_PROTOCOL_TYPE, LEN_STUDENT_ID).trim();
	   }

	   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
	   public void setAId(String id) {
	      System.arraycopy(id.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, id.trim().getBytes().length);
	      packet[LEN_PROTOCOL_TYPE + id.trim().getBytes().length] = '\0';
	   }

   // 패스워드는 byte[]에서 로그인 아이디 바로 뒤에 있음
   public String getPassword() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_LOGIN_ID, LEN_LOGIN_PASSWORD).trim();
   }

   public void setPassword(String password) {
      System.arraycopy(password.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_LOGIN_ID,
            password.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_LOGIN_ID + password.trim().getBytes().length] = '\0';
   }

   public String getQueryResult() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_LOGIN_RESULT, LEN_QUERY_RESULT).trim();
   }

   public void setQueryResult(String queryResult) {
      System.arraycopy(queryResult.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_LOGIN_RESULT,
            queryResult.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_LOGIN_RESULT + queryResult.trim().getBytes().length] = '\0';
   }

////////////학번 getter, setter
   public String getStudentNum() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_STUDENT_NUMBER).trim();
   }

   public void setStudentNum(String Studentnum) {
      System.arraycopy(Studentnum.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE,
            Studentnum.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + Studentnum.trim().getBytes().length] = '\0';
   }
/////////////////파일명 getter, setter

   public String getFilename() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_FILE_NAME).trim();
   }

   public void setFilename(String filename) {
      System.arraycopy(filename.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, filename.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + filename.trim().getBytes().length] = '\0';
   }

   public String getFilenameUM() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER, LEN_FILE_NAME).trim();
   }

   public void setFilenameUM(String filename) {
      System.arraycopy(filename.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER,
            filename.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + filename.trim().getBytes().length] = '\0';
   }

   public String getFilenameD() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_FILE_NAME).trim();
   }

   public void setFilenameD(String filename) {
      System.arraycopy(filename.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE,
            filename.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + filename.trim().getBytes().length] = '\0';
   }

/////////////// 파일크기 getter, setter	
   public int getFileSize() {
      return Integer.parseInt(new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME, LEN_FILE_SIZE).trim());
   }

   public void setFileSize(String size) {
      System.arraycopy(size.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME,
            size.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + size.trim().getBytes().length] = '\0';
   }

   public int getFileSizeUM() {
      return Integer.parseInt(
            new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + LEN_FILE_NAME, LEN_FILE_SIZE).trim());
   }

   public void setFileSizeUM(String size) {
      System.arraycopy(size.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER,
            size.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + size.trim().getBytes().length] = '\0';
   }

   public int getFileSizeD() {
      return Integer.parseInt(new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME, LEN_FILE_SIZE).trim());
   }

   public void setFileSizeD(String size) {
      System.arraycopy(size.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME, size.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME+ size.trim().getBytes().length] = '\0';
   }

//////////////// 업로드 응답 getter, setter
   public String getUploadResult() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_UPLOAD_RESULT).trim();
   }

   public void setUploadResult(String ok) {
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

   public String getUploadResultUM() {

      return new String(packet, LEN_PROTOCOL_TYPE, LEN_UPLOAD_RESULT).trim();
   }

   public void setUploadResultUM(String ok) {
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

///////////////////// Frag getter, setter
   public String getFragU() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE, LEN_FRAG).trim();
   }

   public void setFrag(String frag) {
      System.arraycopy(frag.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE,
            frag.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + frag.trim().getBytes().length] = '\0';
   }

   public String getFragUM() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE, LEN_FRAG)
            .trim();
   }

   public void setFragUM(String frag) {
      System.arraycopy(frag.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE, frag.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE
            + frag.trim().getBytes().length] = '\0';
   }

   public String getFragD() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE , LEN_FRAG).trim();
   }

   public void setFragD(String frag) {
      System.arraycopy(frag.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE,
            frag.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + + LEN_FILE_NAME + LEN_FILE_SIZE + frag.trim().getBytes().length] = '\0';
   }

/////////////////// Last getter, setter
   public String isLast() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG, LEN_LAST).trim();
   }

   public void setLast(String last) {
      System.arraycopy(last.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG, last.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + last.trim().getBytes().length] = '\0';
   }

   public String isLastUM() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG,
            LEN_LAST).trim();
   }

   public void setLastUM(String last) {
      System.arraycopy(last.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG,
            last.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG
            + last.trim().getBytes().length] = '\0';
   }

   public String isLastD() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_SIZE  + LEN_FILE_NAME + LEN_FRAG, LEN_LAST).trim();
   }

   public void setLastD(String last) {
      System.arraycopy(last.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG,
            last.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FILE_NAME +  LEN_FRAG + last.trim().getBytes().length] = '\0';
   }

//////////////// seq number getter, setter
   public String getSeqnum() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST, LEN_SEQNUM)
            .trim();
   }

   public void setSeqnum(String seq) {
      System.arraycopy(seq.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST, seq.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST
            + seq.trim().getBytes().length] = '\0';
   }

   public String getSeqnumUM() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST,
            LEN_SEQNUM).trim();
   }

   public void setSeqnumUM(String seq) {
      System.arraycopy(seq.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST,
            seq.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST
            + seq.trim().getBytes().length] = '\0';
   }

   public String getSeqnumD() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FILE_NAME+ LEN_FRAG + LEN_LAST, LEN_SEQNUM).trim();
   }

   public void setSeqnumD(String seq) {
      System.arraycopy(seq.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME+ LEN_FILE_SIZE + LEN_FRAG + LEN_LAST,
            seq.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME+ LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + seq.trim().getBytes().length] = '\0';
   }

// file setter, getter는 main에
   public void setFile(byte[] filebuf) {
      System.arraycopy(filebuf, 0, packet,
            LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM, filebuf.length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM
            + filebuf.length] = '\0';
   }

   public void setFileUM(byte[] filebuf) {
      System.arraycopy(filebuf, 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE
            + LEN_FRAG + LEN_LAST + LEN_SEQNUM, filebuf.length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_STUDENT_NUMBER + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM
            + filebuf.length] = '\0';
   }

   public void setFileD(byte[] filebuf) {
      System.arraycopy(filebuf, 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME+ LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM,
            filebuf.length);
      packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME+ LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + filebuf.length] = '\0';
   }

// 다운로드 요청 코드 getter, setter
   public String getDownloadResult() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_RES_DOWNLOAD).trim();
   }

   public void setDownloadResult(String ok) {
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

// 수신 결과 getter, setter 초기값 1
   public String getReceiveResult() {
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_RES_RECEIVE).trim();
   }

   public void setReceiveResult(String ok) {
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

   // 신청 결과 getter, setter
   public String getResApplication() {
      // String의 다음 생성자를 사용 : String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE, LEN_RES_APPLICATION).trim();
   }

   // String ok를 byte[]로 만들어서 packet의 프로토콜 타입 바로 뒤에 추가
   public void setResApplication(String ok) {
      // arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
      System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
   }

   public String getPeriod() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID, LEN_APPLICATION_PERIOD).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setPeriod(String period) {
      System.arraycopy(period.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID,
            period.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + period.trim().getBytes().length] = '\0';
   }

   public String getDorm() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD,
            LEN_APPLICATION_DORM).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setDorm(String dorm) {
      System.arraycopy(dorm.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD,
            dorm.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + dorm.trim().getBytes().length] = '\0';
   }

   // 지망번호는 byte[]에서 생활관명 바로 뒤에 있음
   public String getDesire() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            LEN_APPLICATION_DESIRE).trim();
   }

   public void setDesire(String desire) {
      System.arraycopy(desire.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            desire.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + desire.trim().getBytes().length] = '\0';
   }

   // 식사는 byte[]에서 지망번호 바로 뒤에 있음
   public String getMeal() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE, LEN_APPLICATION_MEAL).trim();
   }

   public void setMeal(String meal) {
      System.arraycopy(meal.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE, meal.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + meal.trim().getBytes().length] = '\0';
   }

   public String getPeriod1() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL, LEN_APPLICATION_PERIOD)
                  .trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setPeriod1(String period1) {
      System.arraycopy(
            period1.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL,
            period1.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + period1.trim().getBytes().length] = '\0';
   }

   public String getDorm1() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            LEN_APPLICATION_DORM).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setDorm1(String dorm1) {
      System.arraycopy(dorm1.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            dorm1.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + dorm1.trim().getBytes().length] = '\0';
   }

   // 지망번호는 byte[]에서 생활관명 바로 뒤에 있음
   public String getDesire1() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM,
            LEN_APPLICATION_DESIRE).trim();
   }

   public void setDesire1(String desire1) {
      System.arraycopy(desire1.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM,
            desire1.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + desire1.trim().getBytes().length] = '\0';
   }

   // 식사는 byte[]에서 지망번호 바로 뒤에 있음
   public String getMeal1() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE,
            LEN_APPLICATION_MEAL).trim();
   }

   public void setMeal1(String meal1) {
      System.arraycopy(meal1.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE,
            meal1.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + meal1.trim().getBytes().length] = '\0';
   }

   public String getPeriod2() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL,
            LEN_APPLICATION_PERIOD).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setPeriod2(String period2) {
      System.arraycopy(period2.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL,
            period2.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + period2.trim().getBytes().length] = '\0';
   }

   public String getDorm2() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            LEN_APPLICATION_DORM).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setDorm2(String dorm2) {
      System.arraycopy(dorm2.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            dorm2.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + dorm2.trim().getBytes().length] = '\0';
   }

   // 지망번호는 byte[]에서 생활관명 바로 뒤에 있음
   public String getDesire2() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            LEN_APPLICATION_DESIRE).trim();
   }

   public void setDesire2(String desire2) {
      System.arraycopy(desire2.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            desire2.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + desire2.trim().getBytes().length] = '\0';
   }

   // 식사는 byte[]에서 지망번호 바로 뒤에 있음
   public String getMeal2() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE,
            LEN_APPLICATION_MEAL).trim();
   }

   public void setMeal2(String meal2) {
      System.arraycopy(meal2.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE,
            meal2.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + meal2.trim().getBytes().length] = '\0';
   }

   public String getPeriod3() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL, LEN_APPLICATION_PERIOD)
                  .trim();
   }

   // byte[] packet에 String Period를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setPeriod3(String period3) {
      System.arraycopy(period3.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID
            + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
            + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
            + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL,
            period3.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + period3.trim().getBytes().length] = '\0';
   }

   public String getDorm3() {
      // String(byte[] bytes, int offset, int length)
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            LEN_APPLICATION_DORM).trim();
   }

   // byte[] packet에 String DORM를 byte[]로 만들어 프로토콜 타입 바로 뒤에 추가
   public void setDorm3(String dorm3) {
      System.arraycopy(dorm3.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD,
            dorm3.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + dorm3.trim().getBytes().length] = '\0';
   }

   // 지망번호는 byte[]에서 생활관명 바로 뒤에 있음
   public String getDesire3() {
      return new String(packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE
                  + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            LEN_APPLICATION_DESIRE).trim();
   }

   public void setDesire3(String desire3) {
      System.arraycopy(desire3.trim().getBytes(), 0, packet,
            LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
                  + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
                  + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL
                  + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE
                  + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM,
            desire3.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + desire3.trim().getBytes().length] = '\0';
   }

   // 식사는 byte[]에서 지망번호 바로 뒤에 있음
   public String getMeal3() {
      return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE, LEN_APPLICATION_MEAL).trim();
   }

   public void setMeal3(String meal3) {
      System.arraycopy(meal3.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD
            + LEN_APPLICATION_DORM + LEN_APPLICATION_DESIRE, meal3.trim().getBytes().length);
      packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_ID + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + LEN_APPLICATION_MEAL + LEN_APPLICATION_PERIOD + LEN_APPLICATION_DORM
            + LEN_APPLICATION_DESIRE + meal3.trim().getBytes().length] = '\0';
   }
   
   public String getData(){ // 
       return new String(packet, LEN_PROTOCOL_TYPE + LEN_FROMWHERE, 2000).trim();
   }

   public void setData(String data){
       System.arraycopy(data.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FROMWHERE, data.trim().getBytes().length);
       packet[LEN_PROTOCOL_TYPE + LEN_FROMWHERE + data.trim().getBytes().length] = '\0';
   }

   public String getWhere(){
       return new String(packet, LEN_PROTOCOL_TYPE, LEN_FROMWHERE).trim();
   }

   public void setWhere(String where){
       System.arraycopy(where.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, where.trim().getBytes().length);
       packet[LEN_PROTOCOL_TYPE  + where.trim().getBytes().length] = '\0';
   }
   
   public String getResult() {
		// String의 다음 생성자를 사용 : String(byte[] bytes, int offset, int length)
		return new String(packet, LEN_PROTOCOL_TYPE, LEN_RES_INQUIRE).trim();
	}

	// String ok를 byte[]로 만들어서 packet의 프로토콜 타입 바로 뒤에 추가
	public void setResult(String ok) {
		// arraycopy(Object src, int srcPos, Object dest, int destPos, int length) ->
		// src의 srcPos번 값을 dest의 destPos번째로 length(길이)만큼 복사
		System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
	}
	
	// 하나의 자료만 조회할 때
	public String getStudentNumINQ() {
			return new String(packet, LEN_PROTOCOL_TYPE + LEN_FROMWHERE, LEN_STUDENT_NUMBER).trim();
		}

	public void setStudentNumINQ(String stunum) {
			System.arraycopy(stunum.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FROMWHERE,
					stunum.trim().getBytes().length);
			packet[LEN_PROTOCOL_TYPE + LEN_FROMWHERE + stunum.trim().getBytes().length] = '\0';
		}

	public String getQueryResultINQ() {
			return new String(packet, LEN_PROTOCOL_TYPE + LEN_RES_INQUIRE, LEN_QUERY_RESULT).trim();
		}

	public void setQueryResultINQ(String queryResult) {
			System.arraycopy(queryResult.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_RES_INQUIRE,
					queryResult.trim().getBytes().length);
			packet[LEN_PROTOCOL_TYPE + LEN_RES_INQUIRE + queryResult.trim().getBytes().length] = '\0';
		}
	
	public String getOpenQueryResult() {
		return new String(packet, LEN_PROTOCOL_TYPE, LEN_QUERY_RESULT).trim();
	}
	
	public void setOpenQueryResult(String qr) {
		System.arraycopy(qr.getBytes(), 0, packet, LEN_PROTOCOL_TYPE ,
				qr.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE + qr.trim().getBytes().length] = '\0';
	}
	
	public String getKindofOpen() {
		return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER, LEN_KINDOFOPEN).trim();
	}
	
	public void setKindofOpen(String num) {
		System.arraycopy(num.getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER,
				num.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + num.trim().getBytes().length] = '\0';
	} // 1 + 17 + 3 ,, 21 글 자 쓰였데
	
	
	public String getcanOpen() {
		return new String(packet, LEN_PROTOCOL_TYPE , LEN_OX).trim();
	}
	
	public void setcanOpen(String ox) {
		System.arraycopy(ox.getBytes(), 0, packet, LEN_PROTOCOL_TYPE,
				ox.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE+ ox.trim().getBytes().length] = '\0';
	} // 1 + 17 + 3 ,, 21 글 자 쓰였데
	
	public void setStudentNumOpen(String stunum) {
	      System.arraycopy(stunum.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, stunum.trim().getBytes().length);
	      
	      packet[LEN_PROTOCOL_TYPE + stunum.trim().getBytes().length] = '\0';
	   }
	   
	   public String getStudentNumOpen() {
	      return new String(packet, LEN_PROTOCOL_TYPE, LEN_STUDENT_NUMBER).trim();
	   }
}