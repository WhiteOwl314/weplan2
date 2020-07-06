package com.whiteowl.weplan.goal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.goal.service.GoalService;
import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.member.vo.MemberVO;

@Controller("goalController")
public class GoalControllerImpl implements GoalController{
	@Autowired
	private GoalService goalService;
	@Autowired
	GoalVO goalVO;
	
	@Override
	@RequestMapping(
			value="/goal/goalList.do",
			method = RequestMethod.GET
	)
	public ModelAndView goalList(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		List goalList = goalService.goalList(member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/goal/goalList");
		mav.addObject("goalList", goalList);
		return mav;
	}

}
