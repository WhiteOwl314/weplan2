package com.whiteowl.weplan.absolutevalue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.absolutevalue.service.AbsoluteValueService;
import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;
import com.whiteowl.weplan.member.vo.MemberVO;

@Controller("absoluteValueController")
public class AbsoluteValueControllerImpl implements AbsoluteValueController{
	
	@Autowired
	private AbsoluteValueService absoluteValueService;
	@Autowired
	AbsoluteValueVO absoluteValueVO;
	
	@Override
	@RequestMapping(value="/absoluteValue/absoluteValueList.do",
		method = RequestMethod.GET
	)
	public ModelAndView absoluteValueList(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		List absoluteValueList = absoluteValueService
				.absoluteValueList(member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/absoluteValue/absoluteValueList");
		mav.addObject("absoluteValueList", absoluteValueList);
		return mav;
		
	}
	
	

}
