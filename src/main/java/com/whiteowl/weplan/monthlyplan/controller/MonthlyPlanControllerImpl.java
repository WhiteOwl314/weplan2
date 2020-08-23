package com.whiteowl.weplan.monthlyplan.controller;

import java.util.Calendar;
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
		monthlyPlanVO.setMember_id(member_id);

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {

			if(yearlyPlan_id != 0) {
				monthlyPlanVO.setYearlyPlan_id(yearlyPlan_id);
				monthlyPlanService.addMonthlyPlan(monthlyPlanVO);
			} else {
				monthlyPlanService.addMonthlyPlanNullYearlyId(monthlyPlanVO);
			}

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
			value="/monthlyPlan/completeMonthlyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity completeMonthlyPlan(
			@RequestParam("id") int monthlyPlanId,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			map.put("member_id", member_id);
			map.put("monthlyPlanId", monthlyPlanId);
		} catch (NullPointerException e) {
			String oldUrl = request.getRequestURL().toString();
			String[] oldUrlArray = oldUrl.split("weplan");
			String url = oldUrlArray[0] + "weplan/main";
			message = "<script>";
			message += " location.href='"+ url +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEnt;
		}

		
		try {
			
			monthlyPlanService.completeMonthlyPlan(map);
			
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
			value="/monthlyPlan/notCompleteMonthlyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity notCompleteMonthlyPlan(
			@RequestParam("id") int monthlyPlanId,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			map.put("member_id", member_id);
			map.put("monthlyPlanId", monthlyPlanId);
		} catch (NullPointerException e) {
			String oldUrl = request.getRequestURL().toString();
			String[] oldUrlArray = oldUrl.split("weplan");
			String url = oldUrlArray[0] + "weplan/main";
			message = "<script>";
			message += " location.href='"+ url +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEnt;
		}
		
		
		try {
			
			monthlyPlanService.notCompleteMonthlyPlan(map);
			
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
			value="/monthlyPlan/getMonthlyPlan.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getMonthlyPlan(
			@RequestParam("id") int monthlyPlanId,
			HttpServletRequest request
	) throws Exception {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("monthlyPlanId", monthlyPlanId);
		
		JSONObject jsonObj = monthlyPlanService.getMonthlyPlan(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/monthlyPlan/moveMonth.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public String moveMonth(
			@RequestParam("id") int id,
			@RequestParam("month") String month,
			@RequestParam("member_id") String member_id,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		monthlyPlanVO.setId(id);
		monthlyPlanVO.setMember_id(member_id);
		monthlyPlanVO.setMonth(month);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		monthlyPlanService.moveMonth(monthlyPlanVO);

		return "success";
	}
	
	
	@Override
	@RequestMapping(
			value="/monthlyPlan/yearlyView.do",
			method = RequestMethod.GET
	)
	public ModelAndView yearlyView(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		String year = request.getParameter("year");

		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/monthlyPlan/yearlyView");

			mav.addObject("year", year);
			mav.addObject("member_id", member_id);
		return mav;
		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
		

	}
	
	@Override
	@RequestMapping(
			value="/monthlyPlan/getMonthlyPlanListByYear.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getMonthlyPlanListByYear(
			@RequestParam("year") String year,
			HttpServletRequest request
	) throws Exception {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		String startDate = year + "-01-01";
		String limitDate = year + "-12-31";
		map.put("startDate", startDate);
		map.put("limitDate", limitDate);
		
		JSONArray jsonObj = monthlyPlanService.getMonthlyPlanListByYear(
				map
		);
		
		return jsonObj.toString();
	}
	
	@Override
	@RequestMapping(
			value="/monthlyplan/updateMonthlyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity updateMonthlyPlan(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		int importance = Integer.parseInt(request.getParameter("importance"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String month = request.getParameter("month");
		int id = Integer.parseInt(
				request.getParameter("id")
		);
		int yearlyPlan_id = Integer.parseInt(request.getParameter("yearlyPlan_id"));

		try {
			
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			monthlyPlanVO.setMember_id(member_id);
		} catch (NullPointerException e) {
			String oldUrl = request.getRequestURL().toString();
			String[] oldUrlArray = oldUrl.split("weplan");
			String url = oldUrlArray[0] + "weplan/main";
			message = "<script>";
			message += " location.href='"+ url +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEnt;
		}

		
		monthlyPlanVO.setImportance(importance);
		monthlyPlanVO.setTitle(title);
		monthlyPlanVO.setContent(content);
		monthlyPlanVO.setId(id);
		monthlyPlanVO.setMonth(month);
		

		try {
			if(yearlyPlan_id == 0) {
				monthlyPlanService.updateMonthlyPlan(monthlyPlanVO);
			} else {
				monthlyPlanVO.setYearlyPlan_id(yearlyPlan_id);
				monthlyPlanService.updateMonthlyPlanWithYearlyPlanId(monthlyPlanVO);
			}
			
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

	@RequestMapping(
			value="/monthlyPlan/getMonthlyPlanListByMonth.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getMonthlyPlanListByMonth(
			HttpServletRequest request
	) throws Exception {
		
		String month = request.getParameter("month");

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("month", month);
		
		JSONArray jsonObj = monthlyPlanService.getMonthlyPlanListByMonth(
				map
		);
		
		return jsonObj.toString();
	}

}
