package com.whiteowl.weplan.yearlyplan.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

	@Override
	@RequestMapping(
			value="/yearlyPlan/addYearlyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity addYearlyPlan(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String startDate = request.getParameter("startDate");
		String limitDate = request.getParameter("limitDate");
		String member_id = request.getParameter("member_id");
		int goal_id = Integer.parseInt(request.getParameter("goal_id"));
		
		
		yearlyPlanVO.setTitle(title);
		yearlyPlanVO.setContent(content);
		yearlyPlanVO.setImportance(importance);
		yearlyPlanVO.setStartDate(startDate);
		yearlyPlanVO.setLimitDate(limitDate);
		yearlyPlanVO.setMember_id(member_id);
		yearlyPlanVO.setGoal_id(goal_id);
		
		
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			yearlyPlanService.addYearlyPlan(yearlyPlanVO);

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
	
	@Override
	@RequestMapping(
			value="/yearlyPlan/popUpYearlyPlanView.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String popUpYearlyPlanView(
			@RequestParam("id") int yearlyPlan_id
	) throws Exception {
		
		JSONObject jsonObj = yearlyPlanService.popUpYearlyPlanView(
				yearlyPlan_id
		);
		
		return jsonObj.toString();
	}
	
	@Override
	@RequestMapping(
			value="/yearlyPlan/updateYearlyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity updateYearlyPlan(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		int importance = Integer.parseInt(request.getParameter("importance"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String startDate = request.getParameter("startDate");
		String limitDate = request.getParameter("limitDate");
		String member_id = request.getParameter("member_id");
		int id = Integer.parseInt(
				request.getParameter("id")
		);
		
		yearlyPlanVO.setImportance(importance);
		yearlyPlanVO.setTitle(title);
		yearlyPlanVO.setContent(content);
		yearlyPlanVO.setId(id);
		yearlyPlanVO.setMember_id(member_id);
		yearlyPlanVO.setStartDate(startDate);
		yearlyPlanVO.setLimitDate(limitDate);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		try {
			yearlyPlanService.updateYearlyPlan(yearlyPlanVO);
			
			message = "<script>";
			message += " alert('수정되었습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@Override
	@RequestMapping(
			value="/yearlyPlan/deleteYearlyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity deleteYearlyPlan(
			@RequestParam("id") int yearlyPlan_id,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
		map.put("yearlyPlan_id", yearlyPlan_id);
		
		try {
			
			yearlyPlanService.deleteYearlyPlan(map);
			
			message = "<script>";
			message += " alert('삭제되었습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resEnt;

	}


	@Override
	@RequestMapping(
			value="/yearlyPlan/completeYearlyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity completeYearlyPlan(
			@RequestParam("id") int yearlyPlanId,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
		map.put("yearlyPlanId", yearlyPlanId);
		
		try {
			
			yearlyPlanService.completeYearlyPlan(map);
			
			message = "<script>";
			message += " alert('완료되었습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resEnt;

	}
	@Override
	@RequestMapping(
			value="/yearlyPlan/monthlyPlanList.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String monthlyPlanList(
			@RequestParam("id") int yearlyPlan_id,
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("yearlyPlan_id", yearlyPlan_id);
		
		JSONArray jsonObj = yearlyPlanService.monthlyPlanList(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/yearlyPlan/getMonthList.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getMonthList(
			@RequestParam("id") int yearlyPlan_id,
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("yearlyPlan_id", yearlyPlan_id);
		
		JSONArray jsonObj = yearlyPlanService.getMonthList(
				map
		);
		
		return jsonObj.toString();
	}

	

}
