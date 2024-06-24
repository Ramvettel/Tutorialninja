package com.tutorialninja.qa.utilities;

public class Sample {
	
	public void reverseString() {
		
		String s = "sumathy";
		String s2 = "";

		for (int i = s.length() - 1; i >= 0; i--) {
			s2 = s2 + s.charAt(i);
		}
		System.out.println(s2);
		
	}

	public static void main(String[] args) {
		
		Sample se = new Sample();
		se.reverseString();
	}
}
