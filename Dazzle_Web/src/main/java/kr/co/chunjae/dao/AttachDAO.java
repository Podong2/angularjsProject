package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.AttachEntity;


public interface AttachDAO {
	/**
	 * 첨부파일 등록
	 * @param attachEntity
	 * @throws Exception
	 */
	void insertAttach(AttachEntity attachEntity) throws Exception;

	/**
	 * 기존 파일 삭제
	 * @param attachKey
	 * @throws Exception
	 */
	void deleteFile(long attachKey) throws Exception;

	/**
	 * 첨부파일 정보 가져오기
	 * @param attachEntity
	 * @return
	 * @throws Exception
	 */
	AttachEntity selectAttachInfo(AttachEntity attachEntity)throws Exception;

	/**
	 * 그룹 커버이미지
	 * @return
	 * @throws Exception
	 */
	List<AttachEntity> selectTemplateImage()throws Exception;

	/**
	 * 그룹 커버이미지 저장
	 * @param attachEntity
	 * @throws Exception
	 */
	void insertGroupAttach(AttachEntity attachEntity)throws Exception;
}