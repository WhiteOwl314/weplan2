package com.whiteowl.weplan.absolutevalue.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

public interface AbsoluteValueDAO {

	public List absoluteValueList(String member_id) throws DataAccessException;

	public void addAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws DataAccessException;

	public AbsoluteValueVO absoluteValueView(Map<String, Object> map) throws DataAccessException;
}
