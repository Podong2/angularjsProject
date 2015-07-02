package kr.co.chunjae.service;

import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.user.ConfirmPhoneEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.entities.user.UserLoginEntity;

public interface UserService {
	/**
	 * 로그인
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	UserLoginEntity getDoLogin(UserEntity userInfo) throws Exception;

	/**
	 * 비밀번호 확인
	 * @param password
	 * @param userKey
	 * @param oldPassword
	 * @return
	 * @throws Exception
	 */
	ResponseEntity getDoPassword(long userKey, String password, String oldPassword) throws Exception;

	/**
	 * 유저 목록
	 * @param
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	UserEntity selectUserList(UserEntity userList) throws Exception;

	/**
	 * 유저 상세정보
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
    UserEntity selectUser(long userKey) throws Exception;

    /**
     * 한줄토크 전송
     * @param linetalkentity
     * @return
     * @throws Exception
     */
    void insertTalk(LineTalkEntity linetalkentity) throws Exception;

	/**
	 * 한줄토크 삭제
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
    LineTalkEntity deleteLineTalk(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 회원정보 수정
     * @param userInfo
     * @return
     * @throws Exception
     */
    void updateUser(UserEntity userInfo) throws Exception;

	/**
	 * 사용자 아이디 찾기
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	UserEntity userIdFind(UserEntity userInfo)throws Exception;

	/**
	 * 사용자 비밀번호 찾기 임시비밀번호 SMS 발송
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ResponseEntity sendSmsUserPassword(UserEntity userInfo) throws Exception;

	/**
	 * 사용자 비밀번호 찾기
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	UserEntity userPasswordFind(UserEntity userInfo) throws Exception;

	/**
	 * 사용자 휴대폰인증 SMS 발송
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity userPhoneConfirm(UserEntity userInfo)throws Exception;

	/**
	 *  모바일 사용자 휴대폰번호 중복확인
	 *
	 * @param userInfo, result
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity userPhoneDuplication(UserEntity userInfo)throws Exception;

	/**
	 * 회원가입 아이디 중복체크
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ResponseEntity selectIdCheck(UserEntity userInfo)throws Exception;

	/**
	 * 회원가입
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity insertUser(UserEntity userInfo)throws Exception;

	/**
	 * 회원 탈퇴
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ResponseEntity updateUserWithdraw(UserEntity userInfo)throws Exception;

	/**
	 * 유저 상세정보
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity selectUserProfile(UserEntity userInfo) throws Exception;

	/**
	 * 유저 상세정보 (한줄토크)
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity selectUserProfileLineTalk(UserEntity userInfo) throws Exception;

	/**
	 * 유저 상세정보 (활동로그)
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity selectUserProfileActivityLog(UserEntity userInfo) throws Exception;

	/**
	 * 유저 프로필 편집
	 *
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ConfirmPhoneEntity updateProfile(UserEntity userInfo)throws Exception;
}