package cxy.demo.common.parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ParseExcel
 * @Description 解析Excel文件公共类
 * @auther chaoxingyu
 * @Date 2018/9/7 22:40
 * @Version 1.0
 */
public class ParseExcel {

    /**
     * POI解析Excel文件原理：
     * 1.每一个Excel文件（Excel工作簿）都将被解析成一个WorkBook对象；
     * 2.Excel的每一页（Excel工作表）都将被解析成一个Sheet对象；
     * 3.Excel中的每一行都是一个Row对象；
     * 4.每一个单元格都是一个Cell对象。
     * 注意：POI解析Excel与Excel文件耦合度很高
     */

    /**
     * Excel的两种文件类型
     */
    private static final String EXCEL_XLS = ".xls";

    private static final String EXCEL_XLSX = ".xlsx";


    /**
     * Test
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String date = fmt.format(new Date());
        // String filePath = "C:/Users/YNET/Documents/WeChat Files/wxid_8fr08zt4jqp322/Files/接口文档/00010000397304_查询对私客户信息_provider_body(1).xls";
        // String filePath = "C:/Users/YNET/Documents/WeChat Files/wxid_8fr08zt4jqp322/Files/接口文档/00010000324001_贷款授权通知_Provide_body.xls";
        String filePath = "C:/Users/YNET/Documents/WeChat Files/wxid_8fr08zt4jqp322/Files/接口文档/00010000300102_贷款放款_Provider_body.xls";

        String writePath = "C:\\Users\\YNET\\Desktop\\";
        try {
            // 工作簿对象
            Workbook workbook = initWorkBook(filePath);
            String fileName = "";
            String strPath = "/";
            String strPath2 = "\\";
            if (filePath.contains(strPath)) {
                fileName = filePath.substring(filePath.lastIndexOf(strPath) + 1);
            } else if (filePath.contains(strPath2)) {
                fileName = filePath.substring(filePath.lastIndexOf(strPath2) + 1);
            }
            // Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetCount; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                parseSccbaEsbSheet(date, fileName, sheet, writePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件对象
     *
     * @param filepath 文件路径
     * @return File
     */
    private static File checkFileIsExcel(String filepath) throws Exception {
        if (null == filepath || "".equals(filepath)) {
            throw new Exception("文件路径错误！");
        }
        File file = new File(filepath);
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!file.isFile()) {
            throw new Exception("文件不存在");
        }
        String fileName = file.getName().toLowerCase();
        if (!(fileName.endsWith(EXCEL_XLS) || fileName.endsWith(EXCEL_XLSX))) {
            throw new Exception("文件不是Excel");
        }
        return file;
    }

    /**
     * 判断Excel的类型, 获取Workbook
     *
     * @param filepath 文件路径
     * @return Workbook
     * @throws Exception
     */
    public static Workbook initWorkBook(String filepath) throws Exception {
        File file = checkFileIsExcel(filepath);
        String fileName = file.getName().toLowerCase();
        FileInputStream in = new FileInputStream(file);
        Workbook wb = null;
        // 这种方式 Excel2003/2007/2010都是可以处理的
        // Workbook workbook = WorkbookFactory.create(is);
        if (fileName.endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
        } else if (fileName.endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 解析山盟ESB表格并写文件
     *
     * @param date      日期
     * @param fileName  文件名称
     * @param sheet     待解析表格
     * @param writePath 文件写入路径
     */
    public static void parseSccbaEsbSheet(String date, String fileName, Sheet sheet, String writePath) {
        // 获取工作表名称
        String sheetName = sheet.getSheetName();
        // 一般第一行为标题行，但解析时其下标为0
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        System.err.println("文件名称：" + fileName + "工作表名称：" + sheetName + "，起始结束行数：" + firstRowNum + "，结束行数：" + lastRowNum);
        String lines = System.getProperty("line.separator");
        StringBuilder sbf = new StringBuilder();
        sbf.append("package sccba.esb.common.form;");
        sbf.append(lines);
        sbf.append(lines);
        sbf.append("import javax.xml.bind.annotation.XmlAccessType; ");
        sbf.append(lines);
        sbf.append("import javax.xml.bind.annotation.XmlAccessorType; ");
        sbf.append(lines);
        sbf.append("import javax.xml.bind.annotation.XmlElement; ");
        sbf.append(lines);
        sbf.append("import java.io.Serializable; ");
        sbf.append(lines);
        sbf.append(lines);
        sbf.append("/**").append(lines);
        sbf.append(" * ").append(lines);
        String api = "";
        String apiDesc = "";
        String downLine = "_";
        if (fileName.contains(downLine)) {
            String[] nameStr = fileName.split(downLine);
            api = nameStr[0];
            apiDesc = nameStr[1];
        }
        sbf.append(" * 联盟ESB接口编号[" + api + "], 接口描述[" + apiDesc + "]").append(lines);
        sbf.append(" * ").append(lines);
        sbf.append(" * @author 800P_chaoxy01").append(lines);
        sbf.append(" * @date " + date).append(lines);
        sbf.append(" * 参考资料：" + fileName).append(lines);
        sbf.append(" * ESB联系人：王新义；核心联系人：李帅 ").append(lines);
        sbf.append(" * ").append(lines);
        sbf.append(" */ ");
        sbf.append(lines);
        sbf.append("@XmlAccessorType(XmlAccessType.FIELD) ");
        sbf.append(lines);
        String className = sheetName.toUpperCase() + api;
        String extendName = "Request";
        if (sheetName.toUpperCase().equals(extendName.toUpperCase())) {
            extendName = "EsbBaseRequestBean";
        } else {
            extendName = "EsbBaseResponseBean";
        }
        sbf.append("public class " + className + " extends " + extendName + " implements Serializable { ");
        sbf.append(lines);
        sbf.append(lines);
        sbf.append("private static final long serialVersionUID =  1L; ");
        sbf.append(lines);
        sbf.append(lines);
        // 表格第四行开始
        int beginRowNum = 3;
        for (int i = firstRowNum + beginRowNum; i < lastRowNum + 1; i++) {
            Row dataRow = sheet.getRow(i);
            if (null == dataRow) {
                continue;
            }
            String xmlDesc = getCellValue(dataRow, 2);
            String eng = getCellValue(dataRow, 4);
            eng = convertCamelbakNameing(eng);
            String desc = getCellValue(dataRow, 5);
            String length = getCellValue(dataRow, 6);
            if ("".equals(xmlDesc) && "".equals(eng) && "".equals(desc) && "".equals(length)) {
                continue;
            }
            String isRequired = getCellValue(dataRow, 9);
            if ("2".equals(isRequired)) {
                isRequired = "  必输  ";
            } else {
                isRequired = "  非必输  ";
            }
            String note = getCellValue(dataRow, 16);

            if (desc.equals(note)) {
                note = "";
            }
            sbf.append("/**").append(lines);
            sbf.append(" * " + desc + "  长度：" + length + isRequired);
            if (!"".equals(note)) {
                sbf.append("备注：" + note);
            }
            sbf.append(lines);
            sbf.append(" */ ").append(lines);
            sbf.append("@XmlElement(name = \"" + xmlDesc + "\"" + ") " + lines);
            sbf.append(" private String " + eng + "; ").append(lines);
            sbf.append(lines);
            sbf.append(lines);
        }
        sbf.append(lines);
        sbf.append("}");
        sbf.append(lines);
        // 写文件
        // System.err.println(sbf.toString());
        String filePath = writePath + className + ".Java";
        writeFile(sbf.toString(), filePath);
    }

    /**
     * 写文件
     *
     * @param str      文件内容
     * @param filePath 写入的文件路径
     */
    private static void writeFile(String str, String filePath) {
        System.err.println("文件写入：" + filePath);
        Path path = Paths.get(filePath);
        try {
            Files.write(path, str.getBytes(Charset.defaultCharset()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("写入文件结束");
    }

    /**
     * 获取单元值
     *
     * @param dataRow 行对象
     * @param index   单元格索引
     * @return String
     */
    private static String getCellValue(Row dataRow, int index) {
        Cell xmlCell = dataRow.getCell(index);
        if (null == xmlCell) {
            System.err.println("行数[" + dataRow.getRowNum() + "]的第[" + index + "]单元格为null");
            return "";
        }
        xmlCell.setCellType(CellType.STRING);
        return xmlCell.getStringCellValue();
    }


    /**
     * 根据单元格类型获取对应的单元格的值
     *
     * @param cell 单元格
     * @return Object
     */
    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * 字符串转换为驼峰式
     *
     * @param str 被转换字符串
     * @return String
     */
    private static String convertCamelbakNameing(String str) {
        if (null == str || str.isEmpty()) {
            return "";
        }
        String middle = "-";
        String downLine = "_";
        if (str.contains(middle)) {
            str = str.replaceAll(middle, downLine);
        }
        if (!str.contains(downLine)) {
            return str.toLowerCase();
        }
        StringBuilder result = new StringBuilder();
        // 用下划线将原始字符串分割
        String[] strs = str.split(downLine);
        for (String camel : strs) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

}
