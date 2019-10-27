/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class ReadProperty.
 */
public class ReadProperty {

	/** The str password. */
	private static String strUserID, strPassword;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException  {
		//Instantiate Properties
		Properties props = new Properties();
		//Load Property File using FileInputStream
		props.load(new FileInputStream(Constants.SERV_PROP_FILE));

		
		String seleniumServerHost = props.getProperty("host");
		int seleniumServerPort = Integer.parseInt(props.getProperty("port"));
		String testerURL = props.getProperty("url_ks");
		String browserString = props.getProperty("mode");
		strUserID = props.getProperty("userid");
		strPassword = props.getProperty("password");
		String sonName = props.getProperty("son_name");
		
		System.out.println("seleniumServerHost==>" + seleniumServerHost);
		System.out.println("seleniumServerPort==>" + seleniumServerPort);
		System.out.println("testerURL==>" + testerURL);
		System.out.println("browserString==>" + browserString);
		System.out.println("strUserID==>" + strUserID);
		System.out.println("strPassword==>" + strPassword);
		System.out.println("Son Name****>" + sonName);

		//StringUtils.usdToDouble(props.getProperty("salary"));
		String salaryCurrency = props.getProperty("salary");
		Double salary = StringUtils.usdToDouble(salaryCurrency);

		String bonusCurrency = props.getProperty("bonus");
		Double bonus = StringUtils.usdToDouble(bonusCurrency);

		String spendingCurrency = props.getProperty("spending");
		Double spending = StringUtils.usdToDouble(spendingCurrency);

		Double remaining = salary + bonus - spending;
		if ( remaining > 0) {
			System.out.println("Spent wisely");
		} else {
			System.out.println("Spent foolishly");
		}
	}
}
