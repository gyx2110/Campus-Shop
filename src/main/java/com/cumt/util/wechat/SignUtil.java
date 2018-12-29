package com.cumt.util.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/***
 * 微信请求校验工具类
 * 
 * @author draymonder
 *
 */
public class SignUtil {
	private static String token = "myo2o";

	/***
	 * 验证签名
	 * 
	 * @param signature
	 *            微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、 nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// 摘要算法
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 进行SHA-1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/***
	 * 将字节数组转换为16进制字符串
	 * 
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		String str = "";
		for (int i = 0; i < digest.length; i++) {
			str += byteToHexStr(digest[i]);
		}
		return str;
	}

	/***
	 * 将字节转换为十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0x0f];
		tempArr[1] = Digit[b & 0x0f];
		String s = new String(tempArr);
		return s;
	}
}
