package com.whiteowl.weplan.absolutevalue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;
import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.task.vo.TaskVO;

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

	@Override
	public void addAbsoluteValue(
			AbsoluteValueVO absoluteValueVO
	) throws DataAccessException {
		int absoluteValue_NO = selectNewAbsoluteValue_NO();
		absoluteValueVO.setId(absoluteValue_NO);
		sqlSession.insert(
				"mapper.absoluteValue.addAbsoluteValue",
				absoluteValueVO
		);
	}

	private int selectNewAbsoluteValue_NO() {
		return sqlSession.selectOne(
				"mapper.absoluteValue.selectNewAbsoluteValueListNO"
				);
	}

	@Override
	public AbsoluteValueVO absoluteValueView(
			Map<String, Object> map
	) throws DataAccessException {
		return sqlSession.selectOne(
				"mapper.absoluteValue.absoluteValueView",
				map
				);
	}

	@Override
	public JSONObject popUpAbsoluteValueView(
			int absoluteValueID
	) throws DataAccessException {
		JSONObject data = new JSONObject();
		
		AbsoluteValueVO absoluteValueVO = sqlSession.selectOne(
				"mapper.absoluteValue.popUpAbsoluteValueView", 
				absoluteValueID
		);
		int id = absoluteValueVO.getId();
		String title = absoluteValueVO.getTitle();
		String content = absoluteValueVO.getContent();
		int importance = absoluteValueVO.getImportance();

		data.put("id", id);
		data.put("title", title);
		data.put("content", content);
		data.put("importance", importance);
		
		return data;
	}

	@Override
	public void updateAbsoluteValue(
			AbsoluteValueVO absoluteValueVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.absoluteValue.updateAbsoluteValue",
				absoluteValueVO
		);
		
	}

	@Override
	public void deleteAbsoluteValue(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.absoluteValue.deleteAbsoluteValue",
				map
		);
	}

	@Override
	public List<GoalVO> linkingGoalList(
			Map<String, Object> map
	) throws DataAccessException {
		return sqlSession.selectList(
				"mapper.absoluteValue.linkingGoalList",
				map
				);
	}

	@Override
	public JSONArray getAbsoluteValueList(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		List<AbsoluteValueVO> absoluteValueList = sqlSession.selectList(
				"mapper.absoluteValue.getAbsoluteValueList",
				map
		);
		
		for (AbsoluteValueVO absoluteValueVO : absoluteValueList) {
			JSONObject data = new JSONObject();
			
			int id = absoluteValueVO.getId();
			String title = absoluteValueVO.getTitle();
			String content = absoluteValueVO.getContent();
			int importance = absoluteValueVO.getImportance();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("importance", importance);
			
			ja.add(data);
		}
		return ja;
	}
	

}
