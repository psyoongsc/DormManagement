package SHJ;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DownloadClient {

    static String filename = null;
    static String studentNum = null;
    static int filesize = 0;
    static int tempsize = 0;

    static FileOutputStream fout = null;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);

        Socket socket = new Socket("localhost", 8080);

        System.out.println("서버와 연결되었습니다. ");
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        boolean program_stop = false;
        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();

        System.out.println("학번과 파일명을 입력");
        studentNum = s.next(); //4byte
        filename = s.next(); // 한글 2byte 영어 1byte

        protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD);
        protocol.setStudentNumD(studentNum);
        protocol.setFilenameD(filename); // filename이 null일 수 있음

        os.write(protocol.getPacket());

        while (true) {

            is.read(buf); // 타입만 읽고 버퍼에 넣음.
            int packetType = buf[0];
            protocol.setPacket(packetType, buf);

            switch (packetType) {

                case Protocol.PT_EXIT:
                    System.out.println("클라이언트 종료");
                    protocol = new Protocol(Protocol.PT_EXIT);
                    os.write(protocol.getPacket());

                    program_stop = true;
                    break;

                case Protocol.PT_RES_DOWNLOAD: //LEN_PROTOCOL_TYPE + LEN_FRAG + LEN_LAST + LEN_FILE_SIZE + LEN_MAX

                    if(protocol.getSeqnumD().equals("0")){
                        tempsize = filesize = protocol.getFileSizeD();
                        fout = new FileOutputStream("C:\\Users\\sec\\Desktop\\결핵진단서.jpg");
                    }
                    //System.out.println(filename + ", " + filesize / 1024 + "KB, " + filesize + "Byte 파일을 받았습니다. ");
                    //System.out.println("수신 중");

                        if(protocol.isLastD().equals("1")) {
                            fout.write(buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FRAG + Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM, tempsize); // 파일에다 씀.

                            protocol = new Protocol(Protocol.PT_EXIT); // 다받았음 .
                            os.write(protocol.getPacket());
                            System.out.println("정상적으로 다운 받았습니다 !"); // UI에서 띄워줄거

                            fout.close();
                        }
                        else {
                            fout.write(buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FRAG + Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM,2000); // 버퍼에 쓰인 파일데이터를 write

                            protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD); // 다음 분할 데이터를 전달해주세요
                            protocol.setStudentNumD(studentNum);
                            protocol.setFilenameD(filename);

                            os.write(protocol.getPacket());
                         //   System.out.println("파일 수신중");
                        }
                        if(tempsize >= 2000){
                            tempsize -= 2000;}
                        else tempsize =0 ;

                      //  System.out.println(tempsize+"byte 남음");
                    break;
            }
            if (program_stop) break;
        }
        os.close();
        is.close();
        socket.close();

    }

}
