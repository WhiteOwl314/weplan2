package com.whiteowl.weplan.absolutevalue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.absolutevalue.service.AbsoluteValueService;
import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;
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
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = (String)memberVO.getId();
		List absoluteValueList = absoluteValueService
				.absoluteValueList(member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/absoluteValue/absoluteValueList");
		mav.addObject("absoluteValueList", absoluteValueList);
		return mav;
		
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
	
	

}
