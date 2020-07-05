package com.whiteowl.weplan.absolutevalue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;

public interface AbsoluteValueController {

	public ModelAndView absoluteValueList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity addAbsoluteValue(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public ModelAndView absoluteValueView(int absoluteValueID, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public void popUpAbsoluteValueView(int absoluteValueID, HttpServletResponse response) throws Exception;

}
