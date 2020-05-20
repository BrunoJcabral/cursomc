package com.bjcabral.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class UrlUtil {

	public static String decodeParam(String nome) {
		try {
			return URLDecoder.decode(nome,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	public static List<Integer> decodeIntList(String s){
		String [] vet = s.split(",");
		List<Integer> integer = new ArrayList<>();;
		
		for (int i = 0; i < vet.length; i++) {
			integer.add(Integer.parseInt(vet[i]));
		}
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		return integer;
	}
	
}
