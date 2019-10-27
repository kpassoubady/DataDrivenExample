/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import com.kavinschool.zenphoto.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class ReadText.
 */
public class ReadText {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {

		// Here BufferedReader is added for fast reading.
		try (BufferedReader br = new BufferedReader(new FileReader(Constants.TAGS_FILE))) {
			String strLine; // Holds the current line
			strLine = br.readLine(); // Skips the header row
			// Read from the file until end
			int i = 1;
			while ((strLine = br.readLine()) != null) {
				System.out.println("Line " + i++ + " : " + strLine);
			}
		}
	}

}
