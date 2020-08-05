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
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		List inboxTasksList = taskService.listInboxTasks(member_id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("inboxTasksList", inboxTasksList);
		return mav;
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
		String startDate = request.getParameter("startDate") + " " + request.getParameter("startTime") ;
		String limitDate = request.getParameter("limitDate") + " " + request.getParameter("limitTime") ;
		String member_id = request.getParameter("member_id");
		
		
		taskVO.setTitle(title);
		taskVO.setContent(content);
		taskVO.setImportance(importance);
		taskVO.setLimitDate(limitDate);
		taskVO.setStartDate(startDate);
		taskVO.setMember_id(member_id);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			taskService.addInboxTask(taskVO);
			
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
		Map<String, Object> taskMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		
		String limitDate = request.getParameter("date") + " " + request.getParameter("time") ;
		taskMap.put("limitDate", limitDate);
		
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			taskMap.put(name, value);
		}
		
		String referer = request.getHeader("Referer");
		
		String taskNO = (String)taskMap.get("taskNO");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		try {
			
			if(limitDate.equals("0000-00-00 00:00") || limitDate.equals(" ")) {
				taskService.updateTaskNullDate(taskMap);
			} else {
				taskService.updateTask(taskMap);
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

//	weekly 검색
	@RequestMapping(value="/task/weeklyTaskList.do",
						method = RequestMethod.GET)
	public ModelAndView weeklyTaskList(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
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
	


}
