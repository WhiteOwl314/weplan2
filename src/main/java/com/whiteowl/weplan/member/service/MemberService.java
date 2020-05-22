package com.whiteowl.weplan.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.member.vo.MemberVO;

public interface MemberService {

	public List listMembers() throws DataAccessException;

	public int addMember(MemberVO memberVO) throws DataAccessException;

	public int removeMember(String id) throws DataAccessException;

	public MemberVO login(MemberVO member) throws Exception;

}
