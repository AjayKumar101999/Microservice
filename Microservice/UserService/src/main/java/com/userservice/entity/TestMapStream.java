package com.userservice.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestMapStream {

	public static void main(String[] args) {


		List<String> ajayList=Arrays.asList("Ajay","Kumar","Maurya");
		List<String> ajayList2=ajayList.stream().map(t->t.substring(0,3)).collect(Collectors.toList());
		System.out.println(ajayList2);
	}

}
