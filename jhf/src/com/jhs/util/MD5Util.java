package com.jhs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @author liuyang
 */
public class MD5Util {

	/**
	 * @param content
	 * @return
	 */
	public static String toMd5(String content) {
		try {

			byte[] buffer = content.getBytes("Unicode");

			return toMd5(buffer);
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * @param bytes
	 * @return
	 */
	public static String toMd5(byte[] bytes) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(bytes);
			return toHexString(algorithm.digest(), "");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * public static void main(String[] args) { Md52 m=new Md52();
	 * System.out.println(m.toMd5String("123456")); }
	 */
	/**
	 * @param bytes
	 * @param separator
	 * @return
	 */
	private static String toHexString(byte[] bytes, String separator) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(Integer.toHexString(0xFF & b)).append(separator);
		}
		return hexString.toString();
	}

	public static String toMd5String(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plainText;
	}
}
