package com.whiteowl.weplan.absolutevalue.dao;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

public interface AbsoluteValueDAO {

	public List absoluteValueList(String member_id) throws DataAccessException;

	public void addAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws DataAccessException;

	public AbsoluteValueVO absoluteValueView(Map<String, Object> map) throws DataAccessException;

	public JSONObject popUpAbsoluteValueView(int absoluteValueID) throws DataAccessException;

	public void updateAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws DataAccessException;
}