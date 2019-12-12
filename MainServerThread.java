package DormitoryProgram;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class MainServerThread extends Thread {
	
   private MainServer server = null;
   private Socket socket = null;
   private int ID = -1;
   private InputStream is = null;
   private OutputStream os = null;
   private FileOutputStream fs = null;
   private FileInputStream fis = null;
   private LoginInfo studentInfo = null;
   private int tempsize = 0;
   private int cnt = 0;
   private int a_cnt = 0;
   private int b_cnt = 0;
   private Protocol protocol = null;
   private String updownStudentNum = null;
   
   private File f = null;
   
   public void setB_Cnt(int cnt) { b_cnt = cnt; } 
   
   public String getFilenameD() {
      return filenameD;
   }

   public void setFilenameD(String filenameD) {
      this.filenameD = filenameD;
   }

   public String getFilesizeD() {
      return filesizeD;
   }

   public void setFilesizeD(String filesizeD) {
      this.filesizeD = filesizeD;
   }

   public String getStudentNumD() {
      return studentNumD;
   }

   public void setStudentNumD(String studentNumD) {
      this.studentNumD = studentNumD;
   }

   public int getCntD() {
      return cntD;
   }

   public void setCntD(int cntD) {
      this.cntD = cntD;
   }

   public ArrayList<byte[]> getDataD() {
      return dataD;
   }

   public void setDataD(ArrayList<byte[]> dataD) {
      this.dataD = dataD;
   }

   //개별 다운로드 전용
   String filenameD = null;
    String filesizeD = null;
    String studentNumD = null;
    int cntD = 0; // 몇 번째 분할
    
    ArrayList<byte[]> dataD = new ArrayList<>();

   public MainServerThread(MainServer _server, Socket _socket) {
      super();
      server = _server;
      socket = _socket;
      ID = socket.getPort();
      studentInfo = new LoginInfo();
   }
   
   public void DNULL() {
      filenameD = null;
       filesizeD = null;
       studentNumD = null;
       cntD = 0; // 몇 번째 분할
       
       dataD = new ArrayList<>();
   }
   
   public String getUpdownStudentNum() {
      return updownStudentNum;
   }

   public int getTempSize() {
      return tempsize;
   }

   public void setTempSize(int tempsize) {
      this.tempsize = tempsize;
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
      byte[] buf = new byte[Protocol.LEN_DATA_MAX];
      try {
         while (true) {
            is.read(buf);
            protocol = new Protocol();
            protocol.setPacket(buf[0], buf);
            if (studentInfo.getId() != null) { // main server thread에서 첫 시작이라는거 어떻게 알지?
               if (buf[0] == 12 && cnt == 0) { // 학생 업로드 요청
                  System.out.println("파일 스트림 오픈");
                  fs = new FileOutputStream("C:\\tuber\\" + studentInfo.getId() + ".jpg");
                  cnt++;
               }

               if (buf[0] == 12 && protocol.isLast().equals("1")) {
                  cnt = 0;
               }
            }
            
            if (buf[0] == 14) { 
               if (a_cnt == 0) { // 관리자 업로드요청
                  fs = new FileOutputStream("C:\\tuber\\" + protocol.getStudentNum() + ".jpg");
                  updownStudentNum = protocol.getStudentNum();
                  a_cnt++;
               }
               if (buf[0] == 14 && protocol.isLastUM().equals("1")) {
                  a_cnt = 0;
               }
            }
            
            if (buf[0] == 8) { 
               if(b_cnt == 0) { // 첫 번 째로 다운로드 해줄 때
                  fis = new FileInputStream("C:\\tuber\\" + protocol.getStudentNum() + ".jpg");
                  studentNumD = protocol.getStudentNum();
                  setFileInfo(studentNumD);
                  fileSplit(fis);
                  b_cnt++;
               }
            }

            server.handle(os, fs, ID, buf);

         } // end while

      } catch (IOException ioe) {
         System.out.println(ID + " ERROR reading: " + ioe.getMessage());
         server.remove(ID);
         stop();
      }
   }
   
   private void setFileInfo(String studentNum){
        long fsize = 0;
        // filename은 DB table에 접근해서 얻어낸다.
        // filename = Select 이미지경로 from 결핵진단서 where 학번 = studentNum;
        filenameD = "packman.png";

        f = new File("C:\\tuber\\"+studentNum+".jpg"); // 파일 위치
        if(f.exists()){
            fsize = f.length();
        }
        filesizeD = Long.toString(fsize);
    }
   
   private void fileSplit(FileInputStream file) throws FileNotFoundException, IOException{
        int fsize = Integer.parseInt(filesizeD);
        
        dataD = new ArrayList();
        for(int i =0 ; i <= fsize / 2000; i++){ // 103358 byte -> 104번 0~ 103
            dataD.add(new byte[2000]);
            file.read(dataD.get(i), 0, 2000);
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