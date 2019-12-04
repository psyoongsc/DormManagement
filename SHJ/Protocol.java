package SHJ;

import java.io.File;
import java.io.StringBufferInputStream;

public class Protocol {
    public static final int PT_UNDEFINED = -1;		// 프로토콜이 지정되어 있지 않은 경우
    public static final int PT_EXIT = 0;			// 프로그램 종료
    public static final int PT_REQ_DOWNLOAD = 8;	// 파일 다운로드 요청
    public static final int PT_RES_DOWNLOAD= 9;		// 파일 다운로드 요청에 대한 응답 (승인 / 거절)
    public static final int PT_REQ_UPLOAD = 12;		// 파일 업로드 요청
    public static final int PT_RES_UPLOAD = 13;		// 업로드 요청에 대한 응답
   
    public static final int LEN_PROTOCOL_TYPE = 1;	// 프로토콜 타입 길이
    public static final int LEN_STUDENT_NUMBER = 8;
    public static final int LEN_FILE_NAME = 80;		// 파일명 길이
    public static final int LEN_FILE_SIZE = 10;		// 파일 크기
    public static final int LEN_RES_DOWNLOAD = 2; 	// 다운로드 요청 응답길이 // 필요 X?
    public static final int LEN_LAST = 2;
    public static final int LEN_FRAG = 2;
    public static final int LEN_SEQNUM = 2;
    public static final int LEN_MAX = 2001;			//최대 데이터 길이 // ????
    public static final int LEN_UPLOAD_RESULT = 2;	// 업로드 요청 응답길이

    protected int protocolType;
    private byte[] packet;

    public Protocol(){
        this(PT_UNDEFINED);
    }

    public Protocol(int protocolType){
        this.protocolType = protocolType;
        getPacket(protocolType);
    }
    public byte[] getPacket(int protocoltype){
        if (packet == null) {
        switch(protocoltype) {
            case PT_REQ_UPLOAD: // 1
                packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + LEN_MAX]; // 58
                break;
            case PT_RES_UPLOAD: // 3
                packet = new byte[LEN_PROTOCOL_TYPE + LEN_UPLOAD_RESULT]; // 3
                break;
            case PT_REQ_DOWNLOAD: // 1
                packet = new byte[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + LEN_FILE_NAME ]; // 학번, 파일명 요청 ex) 20180650\\결핵진단서.jpg
                break;
            case PT_RES_DOWNLOAD: // 2
                packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + LEN_MAX]; // 파일명은 그대로 저장하기위해, 사이즈는 받는 양 알기위해 + 파일
                break;
            case PT_UNDEFINED: // -1
                packet = new byte[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + LEN_MAX]; // 58
                break;
            case PT_EXIT : // 0
                packet = new byte[LEN_PROTOCOL_TYPE]; // 1
                break;
        }
        }
          packet[0] = (byte)protocoltype;
        return packet;
    }
    public int getProtocolType()
    {
        return protocolType;
    }

    public void setProtocolType(int protocolType)
    {
        this.protocolType = protocolType;
    }

    public byte[] getPacket(){
        return packet;
    }

    public void setPacket(int protocolType, byte[] buf){
        packet = null;
        packet = getPacket(protocolType); // packet 조절
        this.protocolType = protocolType; // 타입 설정
        System.arraycopy(buf, 0, packet, 0, packet.length); // packet에다 packet.length만큼 buf의 내용을 복사.
    }

    public String getFilenameU(){
        return new String(packet, LEN_PROTOCOL_TYPE, LEN_FILE_NAME).trim();
    }

    public void setFilenameU(String filename){
        System.arraycopy(filename.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, filename.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + filename.trim().getBytes().length] = '\0';
    }

    public int getFileSizeU(){
        return Integer.parseInt(new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME, LEN_FILE_SIZE).trim());
    }

    public void setFileSizeU(String size){
        System.arraycopy(size.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME, size.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + size.trim().getBytes().length] = '\0';
    } //

    public String getUploadResultU(){
        return new String(packet, LEN_PROTOCOL_TYPE, LEN_UPLOAD_RESULT).trim();
    }

    public void setUploadResultU(String ok){
        System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
    }
    public String getFragU(){
        return new String(packet, LEN_PROTOCOL_TYPE, LEN_FRAG).trim();
    }

    public void setFragU(String frag){
        System.arraycopy(frag.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE, frag.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + frag.trim().getBytes().length] = '\0';
    }

    public String isLastU(){
        return new String(packet, LEN_PROTOCOL_TYPE + + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG, LEN_LAST).trim();
    }

    public void setLastU(String last){
        System.arraycopy(last.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG, last.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + last.trim().getBytes().length] = '\0';
    }

    public String getSeqnumU(){
        return new String(packet, LEN_PROTOCOL_TYPE  + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST , LEN_SEQNUM).trim();
    }

    public void setSeqnumU(String seq){
        System.arraycopy(seq.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST, seq.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + seq.trim().getBytes().length] = '\0';
    }

    public void setFileU(byte[] filebuf){
        System.arraycopy(filebuf, 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM, filebuf.length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_NAME + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + filebuf.length] = '\0';
    }
    public String geStudentNumD(){
        return new String(packet, LEN_PROTOCOL_TYPE, LEN_STUDENT_NUMBER).trim();
    }

    public void setStudentNumD(String Studentnum){
        System.arraycopy(Studentnum.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, Studentnum.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + Studentnum.trim().getBytes().length] = '\0';
    }

    public String getFilenameD(){
        return new String(packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER, LEN_FILE_NAME).trim();
    }

    public void setFilenameD(String filename){
        System.arraycopy(filename.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER, filename.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_STUDENT_NUMBER + filename.trim().getBytes().length] = '\0';
    }

    public int getFileSizeD(){
        return Integer.parseInt(new String(packet, LEN_PROTOCOL_TYPE, LEN_FILE_SIZE).trim());
    }

    public void setFileSizeD(String size){
        System.arraycopy(size.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE , size.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + size.trim().getBytes().length] = '\0';
    }

    public String getDownloadResultD(){
        return new String(packet, LEN_PROTOCOL_TYPE, LEN_RES_DOWNLOAD).trim();
    }

    public void setDownloadResultD(String ok){
        System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
    } // '승인'과 함께 파일 전송

    public String getFragD(){
        return new String(packet, LEN_PROTOCOL_TYPE  + LEN_FILE_SIZE , LEN_FRAG).trim();
    }

    public void setFragD(String frag){
        System.arraycopy(frag.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_SIZE, frag.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + frag.trim().getBytes().length] = '\0';
    }

    public String isLastD(){
        return new String(packet, LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG, LEN_LAST).trim();
    }

    public void setLastD(String last){
        System.arraycopy(last.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG, last.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG + last.trim().getBytes().length] = '\0';
    }

    public String getSeqnumD(){
        return new String(packet, LEN_PROTOCOL_TYPE  + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST, LEN_SEQNUM).trim();
    }

    public void setSeqnumD(String seq){
        System.arraycopy(seq.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE  + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST, seq.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + seq.trim().getBytes().length] = '\0';
    }

    public void setFileD(byte[] filebuf){
        System.arraycopy(filebuf, 0, packet, LEN_PROTOCOL_TYPE  + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM, filebuf.length);
        packet[LEN_PROTOCOL_TYPE + LEN_FILE_SIZE + LEN_FRAG + LEN_LAST + LEN_SEQNUM + filebuf.length] = '\0';
    }
}
