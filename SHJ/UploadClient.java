package SHJ;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class UploadClient { // 학번은
    static String filename = null;
    static String filesize = null;
    static int cnt = 0;
    // 최대사이즈만큼의 바이트배열리스트를 만들고, 크기만큼 생성한다.
    static ArrayList<byte[]> data = new ArrayList<>();

    public  static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{

        Socket socket = new Socket("localhost", 8080);
        System.out.println("서버와 연결되었습니다. "); // 연결과 로그인을 통해 학생의 학번이 서버가 알고있게 함.

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol = new Protocol(Protocol.PT_REQ_UPLOAD); // 업로드 요청

        filename = "porky.png"; // 제출할 파일명, UI에서 파일을 입력하게 함.
        setFileInfo(filename);

        protocol.setFilenameU(filename);
        protocol.setFileSizeU(filesize); // 파일명과 파일크기를 전송

        if(filename != null){
            fileSplit(filename, filesize);
        }// 파일분할.

        protocol.setFragU("1"); // 분할여부
        protocol.setLastU("0"); // 1이 마지막
        protocol.setSeqnumU(Integer.toString(cnt));
        protocol.setFileU(data.get(cnt++)); // packet에다 file을 넣음

        os.write(protocol.getPacket());

        boolean program_stop = false;

        while(true){

            protocol = new Protocol();
            byte[] buf = protocol.getPacket();
            is.read(buf);
            int packetType = buf[0];
            protocol.setPacket(packetType, buf);

            if(packetType == Protocol.PT_EXIT) {
                System.out.println("클라이언트 종료");
                break;
            }

            switch(packetType) {
                case Protocol.PT_RES_UPLOAD: // 파일 정보를 주고 가능한지 요청

                    if (protocol.getUploadResultU().equals("1")) {
                        System.out.println("제출할 파일이 선택되지 않았습니다. ");
                        protocol = new Protocol(Protocol.PT_EXIT);
                        os.write(protocol.getPacket());
                    }
                    else if (protocol.getUploadResultU().equals("2")) {

                        protocol = new Protocol(protocol.PT_REQ_UPLOAD); // 수신바람

                        protocol.setFilenameU(filename);
                        protocol.setFileSizeU(filesize);
                        protocol.setFragU("1"); // 분할여부

                        if (data.size() - 1 == cnt) { // cnt == 103이면,,,
                            protocol.setLastU("1"); // 마지막
                            protocol.setSeqnumU(Integer.toString(cnt));
                            protocol.setFileU(data.get(cnt++));

                            os.write(protocol.getPacket());
                        } else {

                            protocol.setLastU("0");
                            protocol.setSeqnumU(Integer.toString(cnt));
                            protocol.setFileU(data.get(cnt++));
                            os.write(protocol.getPacket());
                        }
                    }
                    else{
                        System.out.println("종료 패킷 보냄");
                        protocol = new Protocol(protocol.PT_EXIT);
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

    public static void setFileInfo(String filename){
        long fsize = 0;
        File img = new File(filename); // 파일 정보
        if(img.exists()){
            fsize = img.length();
        }
        filesize = Long.toString(fsize);
    }

    static void fileSplit(String filename, String filesize) throws FileNotFoundException, IOException{

        int fsize = Integer.parseInt(filesize);
        FileInputStream file = new FileInputStream(filename);
        data = new ArrayList();
        for(int i =0 ; i <= fsize / 2000; i++){ // 103358 byte -> 104번 0~ 103
            data.add(new byte[2000]);
            file.read(data.get(i), 0, 2000);
        }
    }
}


