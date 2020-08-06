package com.whiteowl.weplan.monthlyplan.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	

}
