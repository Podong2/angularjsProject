package kr.co.chunjae.entities;



public class SmsEntity{

	private int CId;					//75 : 다즐 회원가입인증, 76 : 다즐 이벤트, 77 : 다즐 친구초대
	private String DestAddr;			//수신전화번호
	private String CallBack;			//발신전화번호
	private String MsgSubject;		//장문전송 : 메시지타이틀 (길이 120자, 특수문자 사용시 전송실패 가능성 있음)
	private String Msg;				//이벤트전송:문자내용, 장문:문자내용 (길이 4000자)
	private String MsgType;			//이벤트,장문전송 :(1:즉시 전송, 2:예약전송), 리얼타임 발송: 모두 즉시전송(1: 즉시 전송)
	private String ReservedTime;	//예약전송일 경우 예약시간(2008-01-24 10:00:00 형식)
	private int MsgID;				//그룹전송인 경우 마스터 아이디
	private String DestName;			//수신자 이름
	private String Etc1;				//기타1
	private String Etc2;				//기타2
	private String Etc3;				//기타3
	private String[] DestAddrArray; //다중수신전화번호

	public int getCId() {
		return CId;
	}
	public void setCId(int cId) {
		CId = cId;
	}
	public String getCallBack() {
		return CallBack;
	}
	public void setCallBack(String callBack) {
		CallBack = callBack;
	}
	public String getDestAddr() {
		return DestAddr;
	}
	public void setDestAddr(String destAddr) {
		DestAddr = destAddr;
	}
	public String getMsgSubject() {
		return MsgSubject;
	}
	public void setMsgSubject(String msgSubject) {
		MsgSubject = msgSubject;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getReservedTime() {
		return ReservedTime;
	}
	public void setReservedTime(String reservedTime) {
		ReservedTime = reservedTime;
	}
	public int getMsgID() {
		return MsgID;
	}
	public void setMsgID(int msgID) {
		MsgID = msgID;
	}
	public String getDestName() {
		return DestName;
	}
	public void setDestName(String destName) {
		DestName = destName;
	}
	public String getEtc1() {
		return Etc1;
	}
	public void setEtc1(String etc1) {
		Etc1 = etc1;
	}
	public String getEtc2() {
		return Etc2;
	}
	public void setEtc2(String etc2) {
		Etc2 = etc2;
	}
	public String getEtc3() {
		return Etc3;
	}
	public void setEtc3(String etc3) {
		Etc3 = etc3;
	}
	public String[] getDestAddrArray() {
		return DestAddrArray;
	}
	public void setDestAddrArray(String[] destAddrArray) {
		DestAddrArray = destAddrArray;
	}

}