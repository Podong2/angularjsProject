package kr.co.chunjae.entities.question;

import java.io.Serializable;

public class ClassCodeEntity implements Serializable {
	
	private static final long serialVersionUID = 8118762910943961759L;
	
	private String classCode;		// class6+class7+class8+class9+class10
	private String class1;			// A9 - 09개정 교육과정(차후 교육과정 개정 예정이므로 해당 필드 유지)
	private String class6;			// 학년/학기
	private String class7;			// 대단원
	private String class8;			// 중단원
	private String class9;			// 소단원
	private String class10;			// 유형
	private String name1;			// 이름
	private String status;			// Classcode 상태(01-사용, 99-사용안함, 앱/관리자페이지에서 status-01인 값만 노출되도록함)
	private String type;			// 구분(large:대단원, medium:중단원, small:소단원, category:유형)
	private String gradeCode;		// 학년 구분 코드
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public String getClass6() {
		return class6;
	}
	public void setClass6(String class6) {
		this.class6 = class6;
	}
	public String getClass7() {
		return class7;
	}
	public void setClass7(String class7) {
		this.class7 = class7;
	}
	public String getClass8() {
		return class8;
	}
	public void setClass8(String class8) {
		this.class8 = class8;
	}
	public String getClass9() {
		return class9;
	}
	public void setClass9(String class9) {
		this.class9 = class9;
	}
	public String getClass10() {
		return class10;
	}
	public void setClass10(String class10) {
		this.class10 = class10;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
}