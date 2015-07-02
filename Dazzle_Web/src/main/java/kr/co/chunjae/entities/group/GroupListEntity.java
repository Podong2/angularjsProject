package kr.co.chunjae.entities.group;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.QuestionEntity;

public class GroupListEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 4183247453480750062L;

	private List<GroupEntity> groupList;					// 그룹 목록
	private GroupEntity groupEntity;						// 그룹 정보
	private List<QuestionEntity> questionEntity;			// 그룹 문제 목록
	private List<GroupMemberEntity> groupMemberEntity;	// 그룹 멤버 목록
	private GroupMemberEntity groupMemberCheckEntity;	// 그룹 멤버 정보
	private List<AttachEntity> templateList;				// 그룹만들기 - 그룹커버 템플릿 이미지 목록

	public List<GroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupEntity> groupList) {
		this.groupList = groupList;
	}

	public GroupEntity getGroupEntity() {
		return groupEntity;
	}

	public void setGroupEntity(GroupEntity groupEntity) {
		this.groupEntity = groupEntity;
	}

	public List<QuestionEntity> getQuestionEntity() {
		return questionEntity;
	}

	public void setQuestionEntity(List<QuestionEntity> questionEntity) {
		this.questionEntity = questionEntity;
	}

	public List<GroupMemberEntity> getGroupMemberEntity() {
		return groupMemberEntity;
	}

	public void setGroupMemberEntity(List<GroupMemberEntity> groupMemberEntity) {
		this.groupMemberEntity = groupMemberEntity;
	}

	public GroupMemberEntity getGroupMemberCheckEntity() {
		return groupMemberCheckEntity;
	}

	public void setGroupMemberCheckEntity(GroupMemberEntity groupMemberCheckEntity) {
		this.groupMemberCheckEntity = groupMemberCheckEntity;
	}

	public List<AttachEntity> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<AttachEntity> templateList) {
		this.templateList = templateList;
	}

}