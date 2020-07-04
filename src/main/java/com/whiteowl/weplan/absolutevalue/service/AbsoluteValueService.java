package com.whiteowl.weplan.absolutevalue.service;

import java.util.List;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

public interface AbsoluteValueService {

	public List absoluteValueList(String member_id) throws Exception;

	public void addAbsoluteValue(AbsoluteValueVO absoluteValueVO) throws Exception;

}
