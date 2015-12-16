package com.Nepian.Test;

public class PlayGround {

	public static void main(String[] args) {
		Double ran = Math.random();
		System.out.println(ran);
		String s = ran.toString();
		System.out.println(s);
		float f = Float.valueOf(s);
		System.out.println(f);
	}
}
