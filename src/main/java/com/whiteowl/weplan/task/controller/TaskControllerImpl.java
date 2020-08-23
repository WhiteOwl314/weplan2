package com.whiteowl.weplan.task.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.member.service.MemberService;
import com.whiteowl.weplan.member.vo.MemberVO;
import com.whiteowl.weplan.task.service.TaskService;
import com.whiteowl.weplan.task.vo.TaskVO;


@Controller("taskController")
public class TaskControllerImpl implements TaskController{
	
	@Autowired
	private TaskService taskService;
	@Autowired
	TaskVO taskVO;
	
	@Override
	@RequestMapping(value="/task/listInboxTasks.do",
						method = RequestMethod.GET)
	public ModelAndView listInboxTasks(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		try {
			String viewName = (String)request.getAttribute("viewName");
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();

			List inboxTasksList = taskService.listInboxTasks(member_id);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("inboxTasksList", inboxTasksList);
			return mav;
		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
	}
	
	@Override
	@RequestMapping(value="/task/addInboxTask.do",
						method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addInboxTask(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String startDate = request.getParameter("startDate"); 
		String startTime = request.getParameter("startTime");
		String limitDate = request.getParameter("limitDate");
		String limitTime = request.getParameter("limitTime");
		String member_id = request.getParameter("member_id");
		int yearlyPlan_id = Integer.parseInt(request.getParameter("yearlyPlan_id"));
		
		if(startDate.equals("")) {
			taskVO.setStartDate("");
		} else if (startTime.equals("")) {
			taskVO.setStartDate(startDate + " " + "00:00");
		} else {
			String fullStartDate = request.getParameter("startDate") + " " + request.getParameter("startTime") ;
			taskVO.setStartDate(fullStartDate);
		}
		
		if(limitDate.equals("")) {
			taskVO.setLimitDate("");
		} else if (limitTime.equals("")) {
			taskVO.setLimitDate(limitDate + " " + "00:00");
		} else {
			String fullLimitDate = request.getParameter("limitDate") + " " + request.getParameter("limitTime") ;
			taskVO.setLimitDate(fullLimitDate);
		}

		
		taskVO.setTitle(title);
		taskVO.setContent(content);
		taskVO.setImportance(importance);
		taskVO.setMember_id(member_id);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			if(yearlyPlan_id == 0) {
				taskService.addInboxTask(taskVO);
			} else {
				taskVO.setYearly_plan_id(yearlyPlan_id);
				taskService.addTaskWithYearlyPlanId(taskVO);
			}
			
			message = "<script>";
			message += " alert('할일이 추가되었습니다.');";
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
	@RequestMapping(value="/task/viewTask.do", method = RequestMethod.GET)
	public ModelAndView viewTask(
			@RequestParam("id") int taskNO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		taskVO = taskService.viewTask(taskNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("task", taskVO);
		return mav;
	}
	
	@RequestMapping(value="/task/updateTask.do"
			,method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateTask(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			taskVO.setMember_id(member_id);
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
		
		
		int importance = Integer.parseInt(request.getParameter("importance"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String limitDate = request.getParameter("limitDate");
		String limitTime = request.getParameter("limitTime");
		int id = Integer.parseInt(
				request.getParameter("id")
		);
		int yearlyPlan_id = Integer.parseInt(request.getParameter("yearlyPlan_id"));
		
		taskVO.setId(id);
		taskVO.setTitle(title);
		taskVO.setContent(content);
		taskVO.setImportance(importance);

		if(startDate.equals("")) {
			taskVO.setStartDate("");
		} else if (startTime.equals("")) {
			taskVO.setStartDate(startDate + " " + "00:00");
		} else {
			String fullStartDate = request.getParameter("startDate") + " " + request.getParameter("startTime") ;
			taskVO.setStartDate(fullStartDate);
		}
		
		if(limitDate.equals("")) {
			taskVO.setLimitDate("");
		} else if (limitTime.equals("")) {
			taskVO.setLimitDate(limitDate + " " + "00:00");
		} else {
			String fullLimitDate = request.getParameter("limitDate") + " " + request.getParameter("limitTime") ;
			taskVO.setLimitDate(fullLimitDate);
		}

		try {
			
			if(yearlyPlan_id == 0) {
				taskService.updateTask(taskVO);
			} else {
				taskVO.setYearly_plan_id(yearlyPlan_id);
				taskService.updateTaskWithYearlyPlanId(taskVO);
			}

			message = "<script>";
			message += " alert('할일이 수정되었습니다.');";
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
		
		return resEnt;
	}
	
	@RequestMapping(value="/task/removeTask.do", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity removeTask(
			@RequestParam("id") int taskNO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");	
		
		try {
			taskService.removeTask(taskNO);
			
			message = "<script>";
			message += " alert('할일이 삭제되었습니다.');";
			message += " location.href='"+request.getContextPath()+"/task/listInboxTasks.do';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+request.getContextPath()+"/task/listInboxTasks.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return resEnt;
	}
	
	@RequestMapping(value="/task/completeTask.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity completeTask(
			@RequestParam("id") int taskNO,
			HttpServletRequest request,
			HttpServletResponse response
	)throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");	
		
		String referer = request.getHeader("Referer");
		
		try {
			
			taskService.completeTask(taskNO);

			message = "<script>";
			message += " alert('할일을 완료했습니다.');";
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
	
	
	@RequestMapping(value="/task/notCompleteTask.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity notCompleteTask(
			@RequestParam("id") int taskNO,
			HttpServletRequest request,
			HttpServletResponse response
	)throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");	
		
		String referer = request.getHeader("Referer");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			map.put("member_id", member_id);
			map.put("id", taskNO);
		} catch (NullPointerException e) {
			String oldUrl = request.getRequestURL().toString();
			String[] oldUrlArray = oldUrl.split("weplan");
			String url = oldUrlArray[0] + "weplan/main";
			message = "<script>";
			message = " location.href='" + url + "'; ";
			message = " <script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEnt;
		}
		
		try {
			
			taskService.notCompleteTask(map);

			message = "<script>";
			message += " alert('완료했습니다.');";
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

//	weekly 검색
	@RequestMapping(value="/task/weeklyTaskList.do",
						method = RequestMethod.GET)
	public ModelAndView weeklyTaskList(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		try {
			String date = "";
			
			if(request.getParameter("date") == null) {
				Date today = new Date();
				SimpleDateFormat Simpleformat = new SimpleDateFormat("yyyy-MM-dd");
				date = Simpleformat.format(today);
			} else {
				date = request.getParameter("date");
			}
			
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			List weeklyTaskList = taskService.weeklyTaskList(member_id, date);
			ModelAndView mav = new ModelAndView("/task/weeklyTaskList");
			mav.addObject("weeklyTaskList", weeklyTaskList);
			
	//		월요일 가져오기
			Map dayList = taskService.returnMondaySunday(date);
			mav.addObject("dayList", dayList);
			return mav;
		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
	}
	
	@RequestMapping(
			value="/task/moveDate.do",
			method = RequestMethod.GET	
	)
	@ResponseBody
	public ResponseEntity moveDate(
			@RequestParam("id") int task_id,
			@RequestParam("date") String date,
			HttpServletRequest request,
			HttpServletResponse response
	)throws Exception {
		request.setCharacterEncoding("utf-8");
		taskVO = taskService.viewTask(task_id);
		String oldLimitDate = taskVO.getLimitDate();
		String oldTime = ""; 
		String newLimitDate = "";
		String defualtTime = "21:00";
		
		if(oldLimitDate.equals("0000-00-00 00:00") || oldLimitDate.equals(" ")) {
			
			newLimitDate = date + " " + defualtTime;
			
			taskService.moveDate(task_id, newLimitDate);
		} else {
			String[] dateArray = oldLimitDate.split(" ");
			oldTime = dateArray[1];
			newLimitDate = date + " " + oldTime;
			taskService.moveDate(task_id, newLimitDate);
		}
		
		
		String referer = request.getHeader("Referer");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		message = "<script>";
		message += " location.href='"+ referer +"'; ";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
	    
	    return resEnt;
	}
	
	@RequestMapping(
			value="/task/popUpTaskView.do",
			method = RequestMethod.POST
	)
	public void popUpTaskView(
			@RequestParam("id") String task_id,
			HttpServletResponse response
	) throws Exception {
		taskService.popUpTaskView(task_id, response);
	}
	
	@Override
	@RequestMapping(
			value="/task/weeklyView.do",
			method = RequestMethod.GET
	)
	public ModelAndView weeklyView(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			
			String fullMonth = request.getParameter("month");
			
			String year = fullMonth.split("-")[0];
			String month = fullMonth.split("-")[1];
			
			
			
			String week = request.getParameter("week");

			ModelAndView mav = new ModelAndView();
			mav.setViewName("/task/weeklyView");
			
			

			mav.addObject("member_id", member_id);
			mav.addObject("year", year);
			mav.addObject("month", month);
			mav.addObject("fullMonth", fullMonth);
			mav.addObject("week", week);
			
			return mav;
		} catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
	}

	@Override
	@RequestMapping(
			value="/task/getTaskListByMonthAndWeek.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getTaskListByMonthAndWeek(
			HttpServletRequest request
	) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		String firstDay = request.getParameter("firstDay");
		String lastDay = request.getParameter("lastDay");

		map.put("member_id", member_id);
		map.put("firstDay", firstDay);
		map.put("lastDay", lastDay);

		
		JSONArray jsonObj = taskService.getTaskListByMonthAndWeek(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/task/popUpGetTask.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String popUpGetTask(
			@RequestParam("id") int taskId,
			HttpServletRequest request
	) throws Exception {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("id", taskId);
		
		JSONObject jsonObj = taskService.popUpGetTask(
				map
		);
		
		return jsonObj.toString();
	}

	@Override
	@RequestMapping(
			value="/task/moveTaskAjax.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public String moveTaskAjax(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String member_id = request.getParameter("member_id");
		String day = request.getParameter("day");

		
		taskVO.setId(id);
		taskVO.setMember_id(member_id);
		taskVO.setLimitDate(day+" 00:00");
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		taskService.moveTaskAjax(taskVO);

		return "success";
	}

}
