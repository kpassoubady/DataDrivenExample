package com.kavinschool.zenphoto.data;

import com.kavinschool.zenphoto.utils.Constants;
import com.kavinschool.zenphoto.utils.CsvUtils;
import com.kavinschool.zenphoto.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ZenPhotoDataProvider {

    @DataProvider(name = "userInfoFromCsv")
    public Object[][] userInfoFromCsv() throws IOException {
        return CsvUtils.get2dCsvData();
    }

    @DataProvider(name = "userInfoFromExcel")
    public static Object[][] userInfoFromExcel() throws Exception {
        return ExcelUtils.get2dExcelData(Constants.USER_XLS_FILE);
    }

}
