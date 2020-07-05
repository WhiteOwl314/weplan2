package com.whiteowl.weplan.absolutevalue.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

public interface AbsoluteValueService {

	public List absoluteValueList(String member_id) throws Exception;

	public void addAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws Exception;

	public AbsoluteValueVO absoluteValueView(String member_id, int absoluteValueID) throws Exception;

	public JSONObject popUpAbsoluteValueView(int absoluteValueID) throws Exception;

	public void updateAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws Exception;

}
