package com.whiteowl.weplan.absolutevalue.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.absolutevalue.service.AbsoluteValueService;
import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;
import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.member.vo.MemberVO;

@Controller("absoluteValueController")
public class AbsoluteValueControllerImpl implements AbsoluteValueController{
	
	@Autowired
	private AbsoluteValueService absoluteValueService;
	@Autowired
	AbsoluteValueVO absoluteValueVO;
	
	@Override
	@RequestMapping(
			value="/absoluteValue/absoluteValueList.do",
			method = RequestMethod.GET
	)
	public ModelAndView absoluteValueList(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		try {
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			List absoluteValueList = absoluteValueService
					.absoluteValueList(member_id);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/absoluteValue/absoluteValueList");
			mav.addObject("absoluteValueList", absoluteValueList);
			return mav;
		}catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}

	}
	
	@Override
	@RequestMapping(
			value="/absoluteValue/addAbsoluteValue.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity addAbsoluteValue(
			HttpServletRequest request,
			HttpServletResponse response
	)throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String member_id = request.getParameter("member_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		
		absoluteValueVO.setMember_id(member_id);
		absoluteValueVO.setTitle(title);
		absoluteValueVO.setContent(content);
		absoluteValueVO.setImportance(importance);
		
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");	
		
		String referer = request.getHeader("Referer");
		
		try {
			
			absoluteValueService.addAbsoluteValue(absoluteValueVO);

			message = "<script>";
			message += " alert('항목을 추가했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
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
			value="/absoluteValue/absoluteValueView.do",
			method = RequestMethod.GET
	)
	public ModelAndView absoluteValueView(
			@RequestParam("id") int absoluteValueID,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception {
		
		try {
			
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = (String)memberVO.getId();
			
			absoluteValueVO = 
					absoluteValueService.absoluteValueView(
							member_id,
							absoluteValueID
					);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("member_id", member_id);
			map.put("absoluteValue_id", absoluteValueID);
			
			List<GoalVO> goalList = 
					absoluteValueService.linkingGoalList(
							map
					);
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/absoluteValue/absoluteValueView");
			mav.addObject("absoluteValue", absoluteValueVO);
			mav.addObject("goalList", goalList);
			return mav;

		}catch (NullPointerException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
		

		
	}
	
	@Override
	@RequestMapping(
			value="/absoluteValue/popUpAbsoluteValueView.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"

	)
	@ResponseBody
	public String popUpAbsoluteValueView(
			@RequestParam("id") int absoluteValueID
	) throws Exception {
		
		JSONObject jsonObj = absoluteValueService.popUpAbsoluteValueView(
				absoluteValueID
		);
		
		return jsonObj.toString();
	}
	
	@Override
	@RequestMapping(
			value="/absoluteValue/updateAbsoluteValue.do",
			method = RequestMethod.POST
	)
	@ResponseBody
	public ResponseEntity updateAbsoluteValue(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		int importance = Integer.parseInt(request.getParameter("importance"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String member_id = request.getParameter("member_id");
		int id = Integer.parseInt(
				request.getParameter("id")
		);
		
		absoluteValueVO.setImportance(importance);
		absoluteValueVO.setTitle(title);
		absoluteValueVO.setContent(content);
		absoluteValueVO.setId(id);
		absoluteValueVO.setMember_id(member_id);
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		

		try {
			
			absoluteValueService.updateAbsoluteValue(absoluteValueVO);
			
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
			value="/absoluteValue/deleteAbsoluteValue.do",
			method = RequestMethod.GET
	)
	@ResponseBody
	public ResponseEntity deleteAbsoluteValue(
			@RequestParam("id") int absoluteValue_id,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		String referer = request.getHeader("Referer");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		

		HttpSession session;
		MemberVO memberVO;
		String member_id;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			session = request.getSession();
			memberVO = (MemberVO)session.getAttribute("member");
			member_id = (String)memberVO.getId();
			map.put("member_id", member_id);
			map.put("absoluteValue_id", absoluteValue_id);
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
			
			absoluteValueService.deleteAbsoluteValue(map);
			
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
			value="/absoluteValue/getAbsoluteValueList.do",
			method = RequestMethod.POST,
			produces = "application/json; charset=utf8"
	)
	@ResponseBody
	public String getAbsoluteValueList(
			HttpServletRequest request
	) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		
		JSONArray jsonObj = absoluteValueService.getAbsoluteValueList(
				map
		);
		
		return jsonObj.toString();
	}
	

}
