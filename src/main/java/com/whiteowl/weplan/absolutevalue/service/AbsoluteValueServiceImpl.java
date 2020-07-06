package com.whiteowl.weplan.absolutevalue.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.absolutevalue.dao.AbsoluteValueDAO;
import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

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

	@Override
	public void addAbsoluteValue(
			AbsoluteValueVO absoluteValueVO
	) throws Exception {
		absoluteValueDAO.addAbsoluteValue(absoluteValueVO);
	}

	@Override
	public AbsoluteValueVO absoluteValueView(
			String member_id, 
			int absoluteValueID
	) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("absoluteValueID", absoluteValueID);
		
		return absoluteValueDAO.absoluteValueView(
				map
		);
	}

	@Override
	public JSONObject popUpAbsoluteValueView(
			int absoluteValueID
	) throws Exception {
		return absoluteValueDAO.popUpAbsoluteValueView(
				absoluteValueID
				);
	}

	@Override
	public void updateAbsoluteValue(
			AbsoluteValueVO absoluteValueVO
	) throws Exception {
		absoluteValueDAO.updateAbsoluteValue(absoluteValueVO);
	}

	@Override
	public void deleteAbsoluteValue(
			Map<String, Object> map
	) throws Exception {
		absoluteValueDAO.deleteAbsoluteValue(map);
		
	}

}
