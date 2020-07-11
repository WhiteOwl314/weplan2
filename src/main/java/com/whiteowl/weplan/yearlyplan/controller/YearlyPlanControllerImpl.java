package com.whiteowl.weplan.yearlyplan.controller;

import java.util.Calendar;
import java.util.Date;
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
import com.whiteowl.weplan.yearlyplan.dao.YearlyPlanDAO;
import com.whiteowl.weplan.yearlyplan.service.YearlyPlanService;
import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

@Controller("yearlyPlanController")
public class YearlyPlanControllerImpl implements YearlyPlanController{
	
	@Autowired
	private YearlyPlanService yearlyPlanService;
	@Autowired
	YearlyPlanVO yearlyPlanVO;
	
	@Override
	@RequestMapping(
			value="/yearlyPlan/yearlyPlanList.do",
			method = RequestMethod.GET	
	) 
	public ModelAndView yearlyPlanList(
			@RequestParam("year") int year,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		int request_year = 0;
		
		if(year == 0000) {
			Calendar cal = Calendar.getInstance();
			request_year = cal.get(cal.YEAR);
		} else {
			request_year = year;
		}
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
		map.put("request_year", request_year);
		
		List yearlyPlanList = yearlyPlanService.yearlyPlanList(map);
		ModelAndView mav = new ModelAndView("/yearlyPlan/yearlyPlanList");
		mav.addObject("yearlyPlanList", yearlyPlanList);
		return mav;
	}

}
