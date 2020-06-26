package com.whiteowl.weplan.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.member.vo.MemberVO;

public interface MemberService {

	public List listMembers() throws DataAccessException;

	public int addMember(MemberVO memberVO) throws DataAccessException;

	public int removeMember(String id) throws DataAccessException;

	public MemberVO login(MemberVO member) throws Exception;
	
	public void check_id(String id, HttpServletResponse response) throws Exception;

	public void check_email(String email, HttpServletResponse response) throws Exception;

	public int join_member(MemberVO member, HttpServletResponse response) throws Exception;

	public String create_key() throws Exception;

	public void approval_member(MemberVO member, HttpServletResponse response) throws Exception;

	public MemberVO login(MemberVO member, HttpServletResponse response) throws Exception;

	public String find_id(HttpServletResponse response, String email) throws Exception;

	public void send_mail(MemberVO member, String div) throws Exception;

	public void find_pw(HttpServletResponse response, MemberVO member) throws Exception;

	public MemberVO update_mypage(MemberVO member) throws Exception;

	public MemberVO update_pw(MemberVO member, String old_pw, HttpServletResponse response) throws Exception;

}
