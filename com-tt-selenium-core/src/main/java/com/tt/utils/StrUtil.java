package com.tt.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class StrUtil {
	public static String getNumberFromString(String msg,int occ) {
		String output = msg;
		int currI = 0;
		String[] words = msg.split(" ");
		for(int i=0;i<words.length;i++) {
			
			if(NumberUtils.isNumber(words[i])) {
				currI++;
				if(currI == occ) {
					output = words[i];
				    break;
				}
			}
		}
		
		return output;
	}
	
	public static String getStringBetween(String msg,String start,String end) {
		String output = "";
		int startIndex = msg.indexOf(start);
		int endIndex = msg.indexOf(end);
		
		if((startIndex ==-1||endIndex==-1))
		{
			output = "No match found";
			return output;
		}
		
        System.out.println("Before Index:" +startIndex);
        System.out.println("After Index:" +endIndex);
		int temp = 0;
		if(startIndex>endIndex)
		{
			endIndex = endIndex + end.length();
			temp = startIndex;
			startIndex = endIndex;
			endIndex = temp;
		}	
		else
		{
			startIndex = startIndex + start.length();
		}
		output = msg.substring(startIndex,endIndex).trim();
		return output;
	}
	
	public static String getRandomString(int size) {
		String output = "";
		String randomOf = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int len = randomOf.length();
		for(int i=0;i<size;i++) {
			int index = (int) (Math.random() * len);
			output = output + randomOf.charAt(index);
		}
		
		return output;
	}
	
	public static void main(String args[]) {
		System.out.println("Number Extracted is: "+StrUtil.getNumberFromString("your order 1234 created successfully,"
				+ "and has 10 items in total",1));
		
		System.out.println("String Between one and Three is :"+StrUtil.getStringBetween("my name is souvik kayal", "souvik", "name"));
		
		System.out.println("Random generated String:"+StrUtil.getRandomString(5));
	}


}
