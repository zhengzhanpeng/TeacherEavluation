package com.hdxy.test;

import com.hdxy.util.EncryptionUtil;


public class TestEncryption {
	public static void main(String[] args) {
		String random = EncryptionUtil.getRandom();
		System.out.println(random);
	}
}
