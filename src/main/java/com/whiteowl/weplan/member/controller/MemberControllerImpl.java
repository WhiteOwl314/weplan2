package com.whiteowl.weplan.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteowl.weplan.member.service.MemberService;
import com.whiteowl.weplan.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;
	
	@RequestMapping(
			value= {"/", "/main.do"},
			method= RequestMethod.GET
	)
	private ModelAndView main(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		String viewName = (String)request
				.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/listMembers.do", 
				method=RequestMethod.GET)
	public ModelAndView listMembers(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList",membersList);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(
			@ModelAttribute("member")MemberVO member, 
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception {
		request.setCharacterEncoding("utf-8");
		int result= 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(
			@RequestParam("id")String id, 
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(
			@ModelAttribute("member")MemberVO member, 
			RedirectAttributes rAttr, 
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			String action = (String)session.getAttribute("action");
			session.removeAttribute("action");
			if(action != null) {
				mav.setViewName("redirect:"+action);
			} else {
				mav.setViewName("redirect:/task/listInboxTasks.do");
			}
			
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
	@RequestMapping(value="/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(
			@RequestParam(value="result", required = false) String result,
			@RequestParam(value="action", required = false) String action,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 아이디 중복 검사(AJAX)
	@RequestMapping(
			value = "/member/check_id.do", 
			method = RequestMethod.POST)
	public void check_id(
			@RequestParam("id") String id, 
			HttpServletResponse response
	) throws Exception{
		memberService.check_id(id, response);
	}
	
	// 이메일 중복 검사(AJAX)
	@RequestMapping(
			value = "/member/check_email.do", 
			method = RequestMethod.POST)
	public void check_email(
			@RequestParam("email") String email, 
			HttpServletResponse response
	) throws Exception{
		memberService.check_email(email, response);
	}
	
	// 회원 가입
	@RequestMapping(
			value = "/member/join_member.do", 
			method = RequestMethod.POST
	)
	public String join_member(
			@ModelAttribute MemberVO member, 
			RedirectAttributes rttr, 
			HttpServletResponse response
	) throws Exception{
		rttr.addFlashAttribute("result", memberService.join_member(member, response));
		return "redircet:./memberJoinForm.do";
		//TODO 주소 수정
	}

}
