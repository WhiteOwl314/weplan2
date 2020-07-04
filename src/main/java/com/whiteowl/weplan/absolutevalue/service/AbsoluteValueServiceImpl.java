package com.whiteowl.weplan.absolutevalue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.absolutevalue.dao.AbsoluteValueDAO;

@Service("absoluteValueService")
@Transactional(propagation = Propagation.REQUIRED)
public class AbsoluteValueServiceImpl 
implements AbsoluteValueService{
	@Autowired
	private AbsoluteValueDAO absoluteValueDAO;
	
	@Override
	public List absoluteValueList(
			String member_id
	) throws Exception {
		List absoluteValueList = absoluteValueDAO.absoluteValueList(member_id);
		return absoluteValueList;
	}

}
