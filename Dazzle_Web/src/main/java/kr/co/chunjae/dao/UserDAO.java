package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.user.UserEntity;

public interface UserDAO {
	/**
	 * 로그인
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	UserEntity getDoLogin(UserEntity userInfo) throws Exception;

	/**
	 * 페스워드 확인
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	int selectPassword(UserEntity userInfo) throws Exception;

	/**
	 * 페스워드 수정
	 * @param userInfo
	 * @throws Exception
	 */
	void updateAdminPassword(UserEntity userInfo) throws Exception;

	/**
	 * 사용자 목록
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	List<UserEntity> selectUserList(UserEntity userList) throws Exception;

	/**
	 * 사용자 목록 개수
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	int selectUserListCount(UserEntity userList) throws Exception;

	/**
	 * 사용자 상세정보
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
    UserEntity selectUser(long userKey)throws Exception;

    /**
     * 한줄토크 전송
     * @param lineTalkEntity
     * @throws Exception
     */
    void inserTalk(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 사용자 상세 정보 변경
     * @param userInfo
     * @throws Exception
     */
    void updateUser(UserEntity userInfo)throws Exception;

     /**
      * 사용자 아이디찾기 SMS 발송
      * @param userInfo
      * @throws Exception
      */
     UserEntity userIdFind(UserEntity userInfo)throws Exception;

     /**
      * 사용자 비밀번호 찾기 임시비밀번호 SMS발송
      * @param userInfo
      * @throws Exception
      */
     UserEntity userPasswordFind(UserEntity userInfo)throws Exception;


     /**
      * 임시비밀번호 저장
      * @param userInfo
      * @return
      * @throws Exception
      */
     int updateTempPassword(UserEntity userInfo)throws Exception;

     /**
      * 휴대폰 중복확인
      * @param userInfo
      * @return
      * @throws Exception
      */
     int phoneDuplication(UserEntity userInfo)throws Exception;

     /**
      * 아이디 중복체크
      * @param userInfo
      * @return
      */
	int selectIdCheck(UserEntity userInfo)throws Exception;

	/**
	 * 회원가입
	 * @param userInfo
	 * @throws Exception
	 */
	void insertUser(UserEntity userInfo)throws Exception;

	/**
	 * 회원 탈퇴
	 * @param userInfo
	 * @throws Exception
	 */
	void updateUserWithdraw(UserEntity userInfo)throws Exception;

	/**
	 * 회원 프로필 편집
	 * @param userInfo
	 * @throws Exception
	 */
	void updateProfile(UserEntity userInfo)throws Exception;

	/**
	 * 내정보 한줄토크 목록
	 * @param lineTalkList
	 * @return
	 * @throws Exception
	 */
	List<LineTalkEntity> selectLineTalk(UserEntity userInfo)throws Exception;
}