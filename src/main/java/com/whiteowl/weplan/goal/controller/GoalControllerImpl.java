package com.whiteowl.weplan.goal.controller;

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

import com.whiteowl.weplan.goal.dao.GoalDAO;
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
		try {
			
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();

			List goalList = goalService.goalList(member_id);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/goal/goalList");
			mav.addObject("goalList", goalList);
			
			return mav;

		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
	}
	
	@Override
	@RequestMapping(
			value="/goal/addGoal.do",
			method = RequestMethod.POST	
	)
	@ResponseBody
	public ResponseEntity addGoal(
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
		int absolute_value_id = Integer.parseInt(request.getParameter("absolute_value_id"));
		
		
		goalVO.setTitle(title);
		goalVO.setContent(content);
		goalVO.setImportance(importance);
		goalVO.setLimitDate(limitDate);
		goalVO.setStartDate(startDate);
		goalVO.setMember_id(member_id);
		
		
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			if(absolute_value_id != 0) {
				goalVO.setAbsolute_value_id(absolute_value_id);
				goalService.addGoal(goalVO);
			} else {
				goalService.addGoalNullAbsoluteValue(goalVO);
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
			value="/goal/popUpGoalView.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String popUpGoalView(
			@RequestParam("id") int goal_id,
			HttpServletRequest request
	) throws Exception {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
		map.put("goal_id", goal_id);

		JSONObject jsonObj = goalService.popUpGoalView(
				map
		);
		
		return jsonObj.toString();
	}
	
	@Override
	@RequestMapping(
			value="/goal/updateGoal.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity updateGoal(
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
		int absolute_value_id = Integer.parseInt(request.getParameter("absolute_value_id"));
		
		goalVO.setImportance(importance);
		goalVO.setTitle(title);
		goalVO.setContent(content);
		goalVO.setId(id);
		goalVO.setMember_id(member_id);
		goalVO.setLimitDate(limitDate);
		goalVO.setStartDate(startDate);

		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		try {
			if(absolute_value_id == 0) {
				goalService.updateGoal(goalVO);
			} else {
				
				goalVO.setAbsolute_value_id(absolute_value_id);
				goalService.updateGoalWithAbsoluteValue(goalVO);
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
	
	@Override
	@RequestMapping(
			value="/goal/deleteGoal.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity deleteGoal(
			@RequestParam("id") int goal_id,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		
		HttpSession session ;
		MemberVO memberVO ;
		String member_id ;
		Map<String, Object> map = new HashMap<String, Object>();

		
		try {
			
			session = request.getSession();
			memberVO = (MemberVO)session.getAttribute("member");
			member_id = (String)memberVO.getId();
			map.put("member_id", member_id);
			map.put("goal_id", goal_id);
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
			
			goalService.deleteGoal(map);
			
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
			value="/goal/yearlyPlanList.do",
			method = RequestMethod.GET,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String yearlyPlanList(
			@RequestParam("id") int goal_id,
			HttpServletRequest request
	) throws Exception {
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");

		Map<String, Object> map = new HashMap<String, Object>();
		
			
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		map.put("member_id", member_id);
		map.put("goal_id", goal_id);

		JSONArray jsonObj = goalService.yearlyPlanList(
				map
		);
		
		return jsonObj.toString();
	}

	
	@Override
	@RequestMapping(
			value="/goal/completeGoal.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity completeGoal(
			@RequestParam("id") int goal_id,
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
			map.put("goal_id", goal_id);
		} catch (Exception e) {
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
			
			goalService.completeGoal(map);
			
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
			value="/goal/getGoalAllList.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getGoalAllList(
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		
		JSONArray jsonObj = goalService.getGoalAllList(
				map
		);
		
		return jsonObj.toString();
	}

}
