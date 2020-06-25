package com.whiteowl.weplan.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.member.vo.MemberVO;

public interface MemberDAO {

	public List selectAllMemberList() throws DataAccessException;

	public int insertMember(MemberVO memberVO) throws DataAccessException;

	public int deleteMember(String id) throws DataAccessException;

	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;

	public int check_id(String id) throws Exception;

	public int check_email(String email) throws Exception;

	public int join_member(MemberVO member) throws Exception;

	public int approval_member(MemberVO member) throws Exception;

	public MemberVO login(String id) throws Exception;

	public int update_log(String id) throws Exception;

	public String find_id(String email) throws Exception;

	public int update_pw(MemberVO member) throws Exception;

}
