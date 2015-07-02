package kr.co.chunjae.entities.group;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kr.co.chunjae.entities.PageEntity;

import org.apache.commons.lang.StringUtils;

public class GroupEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = 3849420258513015472L;

	public GroupEntity() {
		super(1L);
	}

	private long groupKey;						// 그룹 고유키
	private long userKey;						// 사용자 고유키
	private String groupName;					// 그룹명
	private String groupColor;					// 그룹컬러
	private String groupCoverLine;				// 그룹 한줄소개
	private String groupCoverImageName;			// 그룹 커버 파일명
	private String groupCoverCameraImageName;	// 그룹 프로필 이미지 (base64 이미지 데이타)
	private String groupTypeCode;				// 그룹상태코드(01:공개, 02:비공개)
	private String groupTypeCodeName;			// 그룹상태코드명
	private String leaderName;					// 리더명
	private String memberStateCode;				// 그룹 멤버 상태(01:가입, 02:가입대기, 03:탈퇴)
	private int groupMemberCount;				// 멤버수
	private int questionCount;					// 문제수
	private int answerCount;					// 답글수
	private int activityScore;					// 활동점수
	private Date insertGroupDate;				// 등록일
	private Date updateGroupDate;				// 수정일
	private Date deleteGroupDate;				// 삭제일
	private String groupRating;					// 그룹 레벨
	private String groupRatingClassName;		// 그룹 레벨 클래스명
	private int memberStayCount;				// 그룹 대기인원 수
	private int isScrap;						// 문제 스크랩 여부 확인(그룹에 스크랩 되었는지)
	private int isScrapByUserKey;				// 문제 스크랩 여부 확인(내가 스크랩 했는지)

	private List<GroupEntity> groupList;

	public void setPageParams() {
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(groupTypeCode))    paramsMap.put("groupTypeCode", groupTypeCode);
		if (StringUtils.isNotEmpty(getStartDate()))   paramsMap.put("startDate", getStartDate());
		if (StringUtils.isNotEmpty(getEndDate()))     paramsMap.put("endDate", getEndDate());
		if (StringUtils.isNotEmpty(getSearchKey()))   paramsMap.put("searchKey", getSearchKey());
		if (StringUtils.isNotEmpty(getSearchValue())) paramsMap.put("searchValue", getSearchValue());
		if (StringUtils.isNotEmpty(getSortBy()))      paramsMap.put("sortBy", getSortBy());
		if (StringUtils.isNotEmpty(getSortOrder()))   paramsMap.put("sortOrder", getSortOrder());
		super.setPageParams(paramsMap);
	}

	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getGroupColor() {
		return groupColor;
	}
	public void setGroupColor(String groupColor) {
		this.groupColor = groupColor;
	}
	public String getGroupCoverLine() {
		return groupCoverLine;
	}
	public void setGroupCoverLine(String groupCoverLine) {
		this.groupCoverLine = groupCoverLine;
	}
	public String getGroupCoverImageName() {
		return groupCoverImageName;
	}
	public void setGroupCoverImageName(String groupCoverImageName) {
		this.groupCoverImageName = groupCoverImageName;
	}
	public String getGroupTypeCode() {
		return groupTypeCode;
	}
	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}
	public String getGroupTypeCodeName() {
		return groupTypeCodeName;
	}
	public void setGroupTypeCodeName(String groupTypeCodeName) {
		this.groupTypeCodeName = groupTypeCodeName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getMemberStateCode() {
		return memberStateCode;
	}
	public void setMemberStateCode(String memberStateCode) {
		this.memberStateCode = memberStateCode;
	}
	public int getGroupMemberCount() {
		return groupMemberCount;
	}
	public void setGroupMemberCount(int groupMemberCount) {
		this.groupMemberCount = groupMemberCount;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public Date getInsertGroupDate() {
		return insertGroupDate == null ? null : new Date(insertGroupDate.getTime());
	}
	public void setInsertGroupDate(Date insertGroupDate) {
		this.insertGroupDate = insertGroupDate == null ? null : new Date(insertGroupDate.getTime());
	}
	public Date getUpdateGroupDate() {
		return updateGroupDate == null ? null : new Date(updateGroupDate.getTime());
	}
	public void setUpdateGroupDate(Date updateGroupDate) {
		this.updateGroupDate = updateGroupDate == null ? null : new Date(updateGroupDate.getTime());
	}
	public Date getDeleteGroupDate() {
		return deleteGroupDate == null ? null : new Date(deleteGroupDate.getTime());
	}
	public void setDeleteGroupDate(Date deleteGroupDate) {
		this.deleteGroupDate = deleteGroupDate == null ? null : new Date(deleteGroupDate.getTime());
	}
	public List<GroupEntity> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GroupEntity> groupList) {
		this.groupList = groupList;
	}
	public String getGroupRating() {
		return groupRating;
	}
	public void setGroupRating(String groupRating) {
		this.groupRating = groupRating;
	}
	public String getGroupRatingClassName() {
		return groupRatingClassName;
	}
	public void setGroupRatingClassName(String groupRatingClassName) {
		this.groupRatingClassName = groupRatingClassName;
	}
	public int getMemberStayCount() {
		return memberStayCount;
	}
	public void setMemberStayCount(int memberStayCount) {
		this.memberStayCount = memberStayCount;
	}
	public String getGroupCoverCameraImageName() {
		return groupCoverCameraImageName;
	}
	public void setGroupCoverCameraImageName(String groupCoverCameraImageName) {
		this.groupCoverCameraImageName = groupCoverCameraImageName;
	}
	public int getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(int isScrap) {
		this.isScrap = isScrap;
	}
	public int getIsScrapByUserKey() {
		return isScrapByUserKey;
	}
	public void setIsScrapByUserKey(int isScrapByUserKey) {
		this.isScrapByUserKey = isScrapByUserKey;
	}
}