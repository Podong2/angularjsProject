package kr.co.chunjae.entities;



public class FranchiseEntity{

	private String HakbunID;			// 프랜차이즈 아이디
	private String Name;				// 프랜차이즈 이름
	private String franchiseType; 	// 프랜차이즈 인증 타입
	private int resultCount;

	public String getHakbunID() {
		return HakbunID;
	}
	public void setHakbunID(String hakbunID) {
		HakbunID = hakbunID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getFranchiseType() {
		return franchiseType;
	}
	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}


}