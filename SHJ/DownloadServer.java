package SHJ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DownloadServer {

    static String filename = null;
    static String filesize = null;
    static String studentNum = null;
    static int cnt = 0; // 몇 번째 분할

    static ArrayList<byte[]> data = new ArrayList<>();

    public  static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{

        ServerSocket ssocket = new ServerSocket(8080);
        System.out.println("클라이언트 접속 대기 중 …");

        Socket socket = ssocket.accept();
        System.out.println("클라이언트 접속");

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol;

        boolean program_stop = false;

        while(true){

            protocol = new Protocol();
            byte[] buf = protocol.getPacket();
            is.read(buf); //
            int packetType = buf[0];
            protocol.setPacket(packetType, buf);

            if(packetType == Protocol.PT_EXIT) {
                protocol = new Protocol(Protocol.PT_EXIT);
                os.write(protocol.getPacket());
                System.out.println("클라이언트 종료");
                break;
            }

            switch(packetType){
                case Protocol.PT_REQ_DOWNLOAD:
                    if(cnt == 0){
                        studentNum = protocol.geStudentNumD();
                        filename = protocol.getFilenameD();

                        if(filename == null){
                            System.out.println("파일 존재 하지않음");
                            protocol = new Protocol(Protocol.PT_EXIT);
                            os.write(protocol.getPacket());

                            break;
                        }
                        else
                        fileSplit(studentNum, filename);
                    }
                    // 파일크기설정, 분할

                    protocol = new Protocol(protocol.PT_RES_DOWNLOAD); // LEN_FRAG + LEN_LAST + LEN_FILE_SIZE + LEN_MAX
                    protocol.setFileSizeD(filesize);
                    protocol.setFragD("1"); // 분할여부

                    if (data.size() - 1 == cnt) { // cnt == 103이면,,,
                        System.out.println("모든 분할 파일 전송 완료 !");
                        protocol.setLastD("1"); // 마지막
                        protocol.setSeqnumD(Integer.toString(cnt));
                        protocol.setFileD(data.get(cnt++));

                        os.write(protocol.getPacket());
                    }
                    else {
                        protocol.setLastD("0");
                        protocol.setSeqnumD(Integer.toString(cnt));
                        protocol.setFileD(data.get(cnt++));

                        os.write(protocol.getPacket());
                    }
                    break;
            }
            if(program_stop) break;
        }

        is.close();
        os.close();
        socket.close();
    }

    public static void setFileInfo(String studentNum, String filename){
        long fsize = 0;
        File img = new File("C:\\Users\\sec\\IdeaProjects\\"+studentNum+"\\"+filename); // 파일 위치
        if(img.exists()){
            fsize = img.length();
        }
        filesize = Long.toString(fsize);
    }

    static void fileSplit(String studentNum, String filename) throws FileNotFoundException, IOException{

        setFileInfo(studentNum, filename); // 파일크기 설정
        int fsize = Integer.parseInt(filesize);
        FileInputStream file = new FileInputStream("C:\\Users\\sec\\IdeaProjects\\"+studentNum+"\\"+filename);

        data = new ArrayList();
        for(int i =0 ; i <= fsize / 2000; i++){ // 103358 byte -> 104번 0~ 103
            data.add(new byte[2000]);
            file.read(data.get(i), 0, 2000);
        }

    }
}


