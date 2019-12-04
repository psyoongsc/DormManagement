package SHJ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {

    static String filename = null;
    static int filesize = 0;
    static int tempsize = 0;
    static String studentNum = "20180650"; //학생의 학번은 TCP연결 후 id정보를 통해 알아내자.
    static FileOutputStream fout = null;

    // code 추가하기

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket sSocket = new ServerSocket(8080);
        System.out.println("클라이언트 접속 대기 중 …");
        Socket socket = sSocket.accept();
        System.out.println("클라이언트 접속");

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        boolean program_stop = false;

        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();

        while (true) {

            is.read(buf); // 타입만 읽고 버퍼에 넣음.
            int packetType = buf[0];
            protocol.setPacket(packetType, buf);

            switch (packetType) {

                case Protocol.PT_EXIT:
                    System.out.println("서버 종료");
                    protocol = new Protocol(Protocol.PT_EXIT);
                    os.write(protocol.getPacket());

                    program_stop = true;
                    break;

                case Protocol.PT_REQ_UPLOAD: // 파일 전송 요청 // 파일이름 , 사이즈, 분할여부,

                    filename = protocol.getFilenameU(); // null 일 수도
                    filesize = protocol.getFileSizeU();

                    if(protocol.getSeqnumU().equals("0")) {
                        fout = new FileOutputStream("C:\\Users\\sec\\Desktop\\" + studentNum + filename);
                        // 서버측에서 저장할 폴더 : 바탕화면 - 학생의 학번 폴더
                        System.out.println(filename + ", " + filesize / 1024 + "KB, " + filesize + "Byte 파일을 받았습니다. ");
                        tempsize = filesize;
                    }

                    if(filename == null){
                        protocol = new Protocol(Protocol.PT_RES_UPLOAD);
                        protocol.setUploadResultU("1");
                        os.write(protocol.getPacket());

                        break;
                    }

                    if(protocol.isLastU().equals("1")) { // 마지막 분할
                    fout.write(buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_SIZE + Protocol.LEN_FILE_NAME +Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM, tempsize); // 파일에다 씀.

                    protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 다받았음.
                    protocol.setUploadResultU("3");

                    os.write(protocol.getPacket());
                    System.out.println("모든 분할 데이터 수신 완료 !");
                    fout.close();
                }
                else {
                    fout.write(buf, Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_SIZE + Protocol.LEN_FILE_NAME + Protocol.LEN_LAST + Protocol.LEN_FRAG + Protocol.LEN_SEQNUM,2000); // 버퍼에 쓰인 파일데이터를 write

                    protocol = new Protocol(Protocol.PT_RES_UPLOAD); // 클라이언트에게 다음 분할 데이터를 전달 요청.
                    protocol.setUploadResultU("2");
                    os.write(protocol.getPacket());
                }

                if(tempsize >= 2000){ // 남은 데이터 양
                    tempsize -= 2000;
                }
                else tempsize =0 ;

              break;
            }
        if (program_stop) break;
    }
        os.close();
        is.close();
        socket.close();
    }

}
