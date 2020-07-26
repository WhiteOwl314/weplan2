package com.whiteowl.weplan.monthlyplan.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.member.vo.MemberVO;
import com.whiteowl.weplan.monthlyplan.service.MonthlyPlanService;
import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

@Controller("monthlyPlanController")
public class MonthlyPlanControllerImpl implements MonthlyPlanController{
	
	@Autowired
	private MonthlyPlanService monthlyPlanService;
	@Autowired
	MonthlyPlanVO monthlyPlanVO;
	
	@Override
	@RequestMapping(
			value="/monthlyPlan/monthlyPlanList.do",
			method = RequestMethod.GET	
	)
	public ModelAndView monthlyPlanList(
			@RequestParam("month") int month,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		int request_month = 0;
		
		if(month == 0) {
			Calendar cal = Calendar.getInstance();
			request_month = cal.get(cal.MONTH);
		} else {
			request_month = month;
		}
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
		map.put("request_month", request_month);
		
		List monthlyPlanList = monthlyPlanService.monthlyPlanList(map);
		ModelAndView mav = new ModelAndView("/monthlyPlan/monthlyPlanList");
		mav.addObject("monthlyPlanList", monthlyPlanList);
		return mav;
	}
	


}
