/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import com.kavinschool.zenphoto.utils.Constants;



/**
 * The Class ReadCSV.
 */
public class ReadCsv {

	/** The str password. */
	private static String strUserID, strPassword;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Reading the user id, password from a CSV file");
		try (CSVReader reader = new CSVReader(new FileReader(Constants.USER_CSV_FILE))) {
			String[] nextLine;
			nextLine = reader.readNext(); // Skips the 1st row
			while ((nextLine = reader.readNext()) != null) {
				System.out.println(nextLine[0] + "," + nextLine[1] + "," + nextLine[2]);
				strUserID = nextLine[0];
				strPassword = nextLine[1];
				String strAge = nextLine[2];
				int intAge = Integer.parseInt(strAge);
				System.out.println("strUserID: " + strUserID + " strPassword: " + strPassword + " intAge: " + intAge);
			}
		}
	}

}
