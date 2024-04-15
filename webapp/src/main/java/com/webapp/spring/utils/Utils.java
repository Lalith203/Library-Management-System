package com.webapp.spring.utils;

public class Utils {

	//Getting the acronym
	public static String getAcronym(String title){
		String[] words = title.split(" ");
		StringBuilder acronym = new StringBuilder();
		for(String word: words){
			acronym.append(word.charAt(0));
		}
		return acronym.toString().toUpperCase();

	}
	
	

}
