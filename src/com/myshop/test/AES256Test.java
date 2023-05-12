package com.myshop.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.crypto.util.AES256;

public class AES256Test {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
		String plainText = "1111";
		String key = "%03x";
		
		System.out.println("MD5 : "+ plainText + ", 암호화 : "+AES256.md5(plainText));
		System.out.println("sha256 : "+plainText+", 암호화 : "+AES256.sha256(plainText));
		
		String encText = AES256.encryptAES256(plainText, key);
		System.out.println("AES256 : "+plainText+", 암호화 : "+encText);
		String desText = AES256.decryptAES256(encText, key);
		System.out.println("AES256 : "+encText+", 복호화 : "+desText);
		
		String[] strArr = {"1111","2222","3333","4444","5555","1234"};
		
		for(String str : strArr){
			String eText = AES256.encryptAES256(str, key);
			System.out.println("AES256 : "+str+", 암호화 : "+eText);
		}
	}
}