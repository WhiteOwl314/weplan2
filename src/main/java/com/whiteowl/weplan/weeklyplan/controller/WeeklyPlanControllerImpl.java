package com.whiteowl.weplan.weeklyplan.controller;

import java.util.HashMap;
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
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		map.put("member_id", member_id);
		map.put("yearlyPlanId", yearlyPlanId);
		map.put("month", month);

		
		JSONArray jsonObj = weeklyPlanService.getweeklyPlanListByMonth(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/weeklyplan/addWeeklyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity addWeeklyPlan(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String month = request.getParameter("month");
		String member_id = request.getParameter("member_id");
		int week = Integer.parseInt(request.getParameter("week"));
		
		
		
		weeklyPlanVO.setTitle(title);
		weeklyPlanVO.setContent(content);
		weeklyPlanVO.setImportance(importance);
		weeklyPlanVO.setMonth(month);
		weeklyPlanVO.setMember_id(member_id);
		weeklyPlanVO.setWeek(week);

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {

			if(request.getParameter("yearlyPlan_id") == "") {
				weeklyPlanService.addWeeklyPlanNullYearlyPlanId(weeklyPlanVO);
			} else {
				int yearly_plan_id = Integer.parseInt(request.getParameter("yearlyPlan_id"));
				weeklyPlanVO.setYearly_plan_id(yearly_plan_id);
				weeklyPlanService.addWeeklyPlan(weeklyPlanVO);
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
			value="/weeklyPlan/popUpWeeklyPlanView.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String popUpWeeklyPlanView(
			@RequestParam("id") int weekly_plan_id,
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("weekly_plan_id", weekly_plan_id);

		JSONObject jsonObj = weeklyPlanService.popUpWeeklyPlanView(
				map
		);
		
		return jsonObj.toString();
	}
	@Override
	@RequestMapping(
			value="/weeklyPlan/updateWeeklyPlan.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity updateWeeklyPlan(
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
		int id = Integer.parseInt(
				request.getParameter("id")
		);
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			weeklyPlanVO.setMember_id(member_id);
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

		
		weeklyPlanVO.setImportance(importance);
		weeklyPlanVO.setTitle(title);
		weeklyPlanVO.setContent(content);
		weeklyPlanVO.setId(id);
		

		try {
			weeklyPlanService.updateWeeklyPlan(weeklyPlanVO);
			
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
			value="/weeklyPlan/completeWeeklyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity completeWeeklyPlan(
			@RequestParam("id") int weeklyPlanId,
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
			map.put("weeklyPlanId", weeklyPlanId);
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
			
			weeklyPlanService.completeWeeklyPlan(map);
			
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
			value="/weeklyPlan/notCompleteWeeklyPlan.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity notCompleteWeeklyPlan(
			@RequestParam("id") int weeklyPlanId,
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
			map.put("weeklyPlanId", weeklyPlanId);
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
			
			weeklyPlanService.notCompleteWeeklyPlan(map);
			
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
			value="/weeklyPlan/monthlyView.do",
			method = RequestMethod.GET
	)
	public ModelAndView monthlyView(
			@RequestParam("month") String month,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			String fullMonth = month;
			
			String year = fullMonth.split("-")[0];
			String month2 = fullMonth.split("-")[1];

			ModelAndView mav = new ModelAndView();
			mav.setViewName("/weeklyPlan/monthlyView");

			mav.addObject("member_id", member_id);
			mav.addObject("month", month2);
			mav.addObject("year", year);
			mav.addObject("fullMonth",fullMonth);
			
			return mav;
		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}

	}
	@Override
	@RequestMapping(
			value="/weeklyplan/getWeeklyPlanListByOnlyMonth.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getWeeklyPlanListByOnlyMonth(
			HttpServletRequest request
	) throws Exception {
		
		String month = request.getParameter("month");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("month", month);
		
		JSONArray jsonObj = weeklyPlanService.getWeeklyPlanListByOnlyMonth(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/weeklyPlan/moveWeek.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public String moveWeek(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		int week = Integer.parseInt(request.getParameter("week"));
		String member_id = request.getParameter("member_id");

		
		weeklyPlanVO.setId(id);
		weeklyPlanVO.setMember_id(member_id);
		weeklyPlanVO.setWeek(week);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		weeklyPlanService.moveWeek(weeklyPlanVO);

		return "success";
	}
	
	
	@Override
	@RequestMapping(
			value="/weeklyplan/getWeeklyPlanListbyMonthAndWeek.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getWeeklyPlanListbyMonthAndWeek(
			HttpServletRequest request
	) throws Exception {
		
		String month = request.getParameter("month");
		int week = Integer.parseInt(request.getParameter("week"));
		
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		map.put("member_id", member_id);
		map.put("week", week);
		map.put("month", month);
		
		JSONArray jsonObj = weeklyPlanService.getWeeklyPlanListbyMonthAndWeek(
				map
		);
		
		return jsonObj.toString();
	}
	
	
}
