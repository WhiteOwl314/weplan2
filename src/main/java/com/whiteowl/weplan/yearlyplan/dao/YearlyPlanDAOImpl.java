package com.whiteowl.weplan.yearlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

@Repository("yearlyPlanDAO")
public class YearlyPlanDAOImpl implements YearlyPlanDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List yearlyPlanList(
			Map<String, Object> map
	) throws DataAccessException {

		return sqlSession.selectList(
				"mapper.yearlyPlan.yearlyPlanList",
				map
		);
	}

	@Override
	public void addYearlyPlan(
			YearlyPlanVO yearlyPlanVO
	) throws DataAccessException {
		int yearlyPlan_NO = selectNewYearlyPlan_NO();
		yearlyPlanVO.setId(yearlyPlan_NO);
		
		sqlSession.insert(
				"mapper.yearlyPlan.addYearlyPlan",
				yearlyPlanVO
		);
	}

	private int selectNewYearlyPlan_NO() 
	throws DataAccessException{
		
		return sqlSession.selectOne(
					"mapper.yearlyPlan.selectNewYearlyPlan_NO"
				);
	}
	

}
