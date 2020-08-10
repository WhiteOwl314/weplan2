package com.whiteowl.weplan.weeklyplan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whiteowl.weplan.member.vo.MemberVO;
import com.whiteowl.weplan.weeklyplan.service.WeeklyPlanService;
import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

@Controller("weeklyPlanController")
public class WeeklyPlanControllerImpl implements WeeklyPlanController{
	
	@Autowired
	private WeeklyPlanService weeklyPlanService;
	@Autowired
	WeeklyPlanVO weeklyPlanVO;
	
	@Override
	@RequestMapping(
			value="/weeklyplan/getweeklyPlanListByMonth.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getweeklyPlanListByMonth(
			@RequestParam("id") int yearlyPlanId,
			@RequestParam("month") String month,
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("yearlyPlanId", yearlyPlanId);
		map.put("month", month);
		
		JSONArray jsonObj = weeklyPlanService.getweeklyPlanListByMonth(
				map
		);
		
		return jsonObj.toString();
	}
}
