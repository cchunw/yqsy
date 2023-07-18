package com.youceedu.tools.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * @program: yqsy
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-04-30 22:06
 * @Version 1.0
 */
public class ExcelUtil {
    /*
     * 定义私有属性
     */
    private String fileName;

    public ExcelUtil(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @Title: getWb
     * @Description: 得到XSSFWorkbook对象
     * @param:
     * @return: * @return: org.apache.poi.xssf.usermodel.XSSFWorkbook
     * @author chen chunwei
     * @date 2023/4/18 13:12
     */
    public XSSFWorkbook getWb() {
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * @Title: getSheet
     * @Description: 得到XSSFSheet的对象
     * @param: * @param sheetIndex:
     * @return: * @return: org.apache.poi.xssf.usermodel.XSSFSheet
     * @author chen chunwei
     * @date 2023/4/17 21:11
     */
    public XSSFSheet getSheet(int sheetIndex) {
        XSSFSheet sheet = null;
        try {
            XSSFWorkbook wb = getWb();
            sheet = wb.getSheetAt(sheetIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }

    /**
     * @param rowIndex:
     * @param cellIndex:
     * @Title: getCellValue
     * @Description: 据sheetIndex、rowIndex、cellIndex得到Cell，并根据Cell类型得到对应的值
     * @param: * @param sheetIndex:
     * @return: * @return: java.lang.Object
     * @author chen chunwei
     * @date 2023/4/18 13:03
     */
    public Object getCellValue(int sheetIndex, int rowIndex, int cellIndex) {
        Object value = null;
        try {
            XSSFRow xssfRow = getSheet(sheetIndex).getRow(rowIndex);
            XSSFCell getXssfCell = xssfRow.getCell(cellIndex);
            value = fromCellTypeGetCellValue(getXssfCell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * @Title: fromCellTypeGetCellValue
     * @Description: 据Cell类型得到对应的value
     * @param: * @param cell:
     * @return: * @return: java.lang.Object
     * @author chen chunwei
     * @date 2023/4/18 13:03
     */
    public Object fromCellTypeGetCellValue(Cell cell) {
        Object value = null;
        try {
            CellType cellTypeEnum = cell.getCellType();
            switch (cellTypeEnum) {
                case _NONE:
                    value = "";
                    break;
                case NUMERIC:
                    //value = cell.getNumericCellValue();
                    DataFormatter dataFormatter = new DataFormatter();
                    dataFormatter.addFormat("###########", null);
                    value = dataFormatter.formatCellValue(cell);
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case BLANK:
                    value = "";
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    value = cell.getErrorCellValue();
                    break;
                default:
                    value = "";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * @Title: getArrayCellValue
     * @Description: excel内的数据存入二维数组
     * @param: * @param sheetIndex:
     * @return: * @return: java.lang.Object[][]
     * @author chen chunwei
     * @date 2023/4/18 13:04
     */
    public Object[][] getArrayCellValue(int sheetIndex) {
        //定义一个二维数组
        Object[][] testCaseData = null;
        XSSFSheet sheet = getSheet(sheetIndex);
        int lastRowNum = sheet.getLastRowNum();
        testCaseData = new Object[lastRowNum][10];

        //遍历行
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            //判断行是否为空，为空跳过该行，继续下一个循环
            if (row == null) {
                continue;
            }

            //遍历行上的每一单元格
            for (short cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                XSSFCell cell = row.getCell(cellIndex);
                testCaseData[rowIndex - 1][cellIndex] = fromCellTypeGetCellValue(cell);
            }
        }
        return testCaseData;

    }

    public static void main(String[] args) throws IOException {

        //测试1
        ExcelUtil excelUtil = new ExcelUtil("D:\\app_testcase.xlsx");
        Object cellValue = excelUtil.getCellValue(0, 0, 0);
        System.out.println(cellValue.toString());

        /*
        //测试2
        ExcelUtil excelUtil = new ExcelUtil("D:\\app_testcase.xlsx");
        Object[][] objects = excelUtil.getArrayCellValue(3);
        System.out.println(objects[0][0].toString());

         */
    /*    for (int i = 0; i < 2; i++) {
            System.out.println("{");
            for (int j = 0; j <10; j++) {
                System.out.println(objects[i][j]+" ");
            }
            System.out.println("}");
            System.out.println();
        }*/
    }
}
