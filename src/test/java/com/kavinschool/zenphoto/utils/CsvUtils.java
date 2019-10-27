package com.kavinschool.zenphoto.utils;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvUtils {

	public static String[][] get2dCsvData() throws IOException {
		List<String[]> allData;
		// Read all the data from CSV file
		FileReader fileReader = new FileReader(Constants.USER_CSV_FILE);
		try (CSVReader reader = new CSVReader(fileReader,',','\"',1)) {
			allData = reader.readAll();
		}

		// Convert the list into 2D Object
		String[][] dataInfo = new String[allData.size()][];
		int i = 0;
		for (String[] curData : allData) {
			String userId = curData[0];
			String password = curData[1];
			dataInfo[i++] = new String[] { userId, password };
		}
		return dataInfo;
	}

	public Iterator<Object[]> getIteratorCsvTestCases() throws IOException {
		String[] curLine;
		final List<Object[]> dataToBeReturned = new ArrayList<>();
		FileReader fileReader = new FileReader(Constants.USER_CSV_FILE);
		try (CSVReader csvReader= new CSVReader(fileReader,',','\"',1)) {
			while ((curLine = csvReader.readNext()) != null) {
				dataToBeReturned.add(new Object[] { curLine });
			}
		}
		return dataToBeReturned.iterator();
	}
}
