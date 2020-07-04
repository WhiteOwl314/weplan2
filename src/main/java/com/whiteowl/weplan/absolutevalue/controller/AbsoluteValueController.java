package com.whiteowl.weplan.absolutevalue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface AbsoluteValueController {

	public ModelAndView absoluteValueList(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
