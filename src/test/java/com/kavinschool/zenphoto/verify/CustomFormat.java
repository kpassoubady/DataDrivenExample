/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import com.kavinschool.zenphoto.utils.StringUtils;


/**
 * The Class CustomFormat.
 */
public class CustomFormat {

	public static void main(String[] args) {
		System.out.println(StringUtils.customFormat("###,###.###", 123456.789));
		System.out.println(StringUtils.customFormat("###.##", 123456.789));
		System.out.println(StringUtils.customFormat("000000.000", 123.78));
		System.out.println(StringUtils.customFormat("$###,###.###", 12345.67));
		System.out.println(StringUtils.customFormat("###.00", 123456.7));
		System.out.println(StringUtils.customFormat("$###,###.00##", 234567.89676776));

		System.out.println("=".repeat(20));
		Double tempVal =1234.12345678;
		System.out.println(StringUtils.customFormat("#,###.##",tempVal));
		System.out.println(StringUtils.customFormat("$#,###.##",tempVal));
		System.out.println(StringUtils.customFormat("#,###.####",tempVal));
		System.out.println("=".repeat(20));
	}

}
