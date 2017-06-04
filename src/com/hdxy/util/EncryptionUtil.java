package com.hdxy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 用于加密明文
 *
 */
public class EncryptionUtil {
	
	/*获取加密后的密码，hashType为选择的加密类型*/
	public static String getPassword(String source, String random, String hashType) {
		StringBuilder sb = new StringBuilder(source).append(random);
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance(hashType);
			md5.update(sb.toString().getBytes());
			sb = new StringBuilder();
			for ( byte b : md5.digest()) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/* 获取随机生成的32位字符串，此为加密时用到的盐*/
	public static String getRandom() {
		StringBuilder sb = new StringBuilder();
		byte[] bs = SecureRandom.getSeed(16);
		for (byte b : bs) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
