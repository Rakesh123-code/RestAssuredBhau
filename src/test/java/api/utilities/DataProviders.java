package api.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
    
    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";
        XLUtility xl = new XLUtility(path);
        
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);
        
        List<String[]> validData = new ArrayList<>();
        
        for (int i = 1; i <= rownum; i++) {
            if (xl.getCellData("Sheet1", i, 0).trim().isEmpty()) { // Skip empty rows
                continue;
            }
            String[] rowData = new String[colcount];
            for (int j = 0; j < colcount; j++) {
                rowData[j] = xl.getCellData("Sheet1", i, j);
            }
            validData.add(rowData);
        }

        return validData.toArray(new String[0][0]);
    }
    

    @DataProvider(name="UserNames")
    public String[] getUserName() throws IOException {
    	String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");

        List<String> validUsernames = new ArrayList<>();

        for (int i = 1; i <= rownum; i++) {
            String username = xl.getCellData("Sheet1", i, 1).trim();
            if (!username.isEmpty()) {
                validUsernames.add(username);
            }
        }

        return validUsernames.toArray(new String[0]);
    }   
}
