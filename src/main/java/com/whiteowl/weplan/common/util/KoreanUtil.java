package com.whiteowl.weplan.common.util;

public class KoreanUtil {
	
	public static final String getComleteWordByJongsung(
			String name, String firstValue, String secondValue
	) {
		//마지막 글자 가져오기
		char lastName = name.charAt(name.length() -1);
		
		//한글의 제일 처음과 끝의 범위밖일 경우는 오류
		if (lastName < 0xAC00 || lastName > 0xD7A3) {
			return name;
		}
		
		String seletedValue = (lastName - 0xAC00) % 28 > 0 ? firstValue : secondValue; 
		return name+seletedValue;

	}

}
