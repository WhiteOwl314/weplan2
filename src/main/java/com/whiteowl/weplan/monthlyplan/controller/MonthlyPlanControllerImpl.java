package com.whiteowl.weplan.monthlyplan.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			value="/monthlyplan/addMonthlyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity addMonthlyPlan(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String month = request.getParameter("month");
		String member_id = request.getParameter("member_id");
		int yearlyPlan_id = Integer.parseInt(request.getParameter("yearlyPlan_id"));
		
		
		monthlyPlanVO.setTitle(title);
		monthlyPlanVO.setContent(content);
		monthlyPlanVO.setImportance(importance);
		monthlyPlanVO.setMonth(month);
		monthlyPlanVO.setYearlyPlan_id(yearlyPlan_id);
		monthlyPlanVO.setMember_id(member_id);

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			monthlyPlanService.addMonthlyPlan(monthlyPlanVO);

			message = "<script>";
			message += " alert('추가되었습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt ;
	}

}
