package com.whiteowl.weplan.absolutevalue.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

@Repository("absoluteValueDAO")
public class AbsoluteValueDAOImpl implements AbsoluteValueDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List absoluteValueList(
			String member_id
	) throws DataAccessException {
		List<AbsoluteValueVO> absoluteValueList = 
				sqlSession.selectList(
						"mapper.absoluteValue.absoluteValueList",
						member_id
				);
		return absoluteValueList;
	}

}
