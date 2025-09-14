package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    String path = System.getProperty("user.dir") + "/testData/random_user_data.xlsx";

    // Returns all student data (full row)
    @DataProvider(name = "AllStudentData")
    public String[][] getAllStudentData() throws IOException {
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        String studentData[][] = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) { // skip header row
            for (int j = 0; j < totalCols; j++) {
                studentData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return studentData;
    }

    // Returns only Student Names
    @DataProvider(name = "onlyStudentName")
    public String[] getStudentNames() throws IOException {
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Sheet1");
        // Name column index = 1 (0 = StudentID, 1 = Name, 2 = Location, etc.)
        String studentNames[] = new String[totalRows];

        for (int i = 1; i <= totalRows; i++) { // skip header
            studentNames[i - 1] = xlutil.getCellData("Sheet1", i, 0);
        }
        return studentNames;
    }
}
