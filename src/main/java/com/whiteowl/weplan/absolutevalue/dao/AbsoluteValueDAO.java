package com.whiteowl.weplan.absolutevalue.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AbsoluteValueDAO {

	public List absoluteValueList(String member_id) throws DataAccessException;

}
