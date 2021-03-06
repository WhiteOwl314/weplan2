package com.whiteowl.weplan.member.controller;

import java.lang.reflect.Method;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView join_member(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setEmail(email);
		memberVO.setName(name);
		
		memberService.join_member(memberVO, response);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/login_email");
		return mav;
	}
	
	// 회원 인증
	@RequestMapping(
			value = "/member/approval_member.do", 
			method = RequestMethod.POST)
	public void approval_member(
			@ModelAttribute MemberVO member, 
			HttpServletResponse response
	) throws Exception{
		memberService.approval_member(member, response);
	}
	
	// 로그인 폼 이동
	@RequestMapping(
			value="/member/login_form.do",
			method = RequestMethod.GET
	) public ModelAndView login_form() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	//로그인
	@RequestMapping(
			value = "/member/login2.do",
			method = RequestMethod.POST
	) public ModelAndView login(
			@ModelAttribute MemberVO member,
			HttpSession session,
			HttpServletRequest request,
			RedirectAttributes rAttr, 
			HttpServletResponse response
	) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		memberVO = memberService.login(member, response);
		
		if(memberVO != null) {
			HttpSession session1 = request.getSession();
			session1.setAttribute("member", memberVO);
			session1.setAttribute("isLogOn", true);
			String action = (String)session1.getAttribute("action");
			session1.removeAttribute("action");
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
	
	// 아이디 찾기 폼
	@RequestMapping(value="/member/find_id_form.do")
	public ModelAndView find_id_form() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/find_id_form");
		return mav;
	}
	
	// 아이디 찾기
	@RequestMapping(
			value = "/member/find_id.do", 
			method = RequestMethod.POST
	)
	public ModelAndView find_id(
			HttpServletResponse response, 
			@RequestParam("email") String email 
	) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/find_id");
		mav.addObject("id", memberService.find_id(response, email));
		return mav;
	}
	
	// 비밀번호 찾기 폼
	@RequestMapping(value = "/member/find_pw_form.do")
	public ModelAndView find_pw_form() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/find_pw_form");
		return mav;
	}
	
	// 비밀번호 찾기
	@RequestMapping(
			value = "/member/find_pw.do",
			method = RequestMethod.POST
	)
	public void find_pw(
			@ModelAttribute MemberVO member, 
			HttpServletResponse response
	) throws Exception{
		memberService.find_pw(response, member);
	}
	
	// 마이페이지 이동
	@RequestMapping(
			value="/member/mypage.do",
			method = RequestMethod.GET
	)
	public ModelAndView mypage() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/mypage");
		return mav;
	}
	
	// mypage 수정
	@RequestMapping(
			value = "/member/update_mypage.do", 
			method = RequestMethod.POST
	)
	public String update_mypage(
			@ModelAttribute MemberVO member, 
			HttpSession session, 
			RedirectAttributes rttr
	) throws Exception{
		session.setAttribute("member", memberService.update_mypage(member));
		rttr.addFlashAttribute("msg", "회원정보 수정 완료");
		return "redirect:/member/mypage.do";
	}
	
	// 비밀번호 변경
	@RequestMapping(
			value = "/member/update_pw.do",
			method = RequestMethod.POST
	)
	public String update_pw(
			@ModelAttribute MemberVO member, 
			@RequestParam("old_pw") String old_pw, 
			HttpSession session, 
			HttpServletResponse response, 
			RedirectAttributes rttr
	) throws Exception{
		session.setAttribute("member", memberService.update_pw(member, old_pw, response));
		rttr.addFlashAttribute("msg", "비밀번호 수정 완료");
		return "redirect:/member/mypage.do";
	}
}
