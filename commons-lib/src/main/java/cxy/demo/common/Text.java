package cxy.demo.common;

import cxy.demo.common.utils.Date4OldUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Text
 * @Description TODO
 * @auther chaoxingyu
 * @Date 2018/10/23 10:22
 * @Version 1.0
 */
public class Text {


    private static void dealOpen() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        String idno = "511527198001010011";
        // 18 身份证号
        sb.append(idno);
        // 2 证件类型 01-身份证18位
        sb.append("01");
        String name = "王零零";
        String names = changeCharset(name, "GBK");
        // 60 中文姓名
        sb.append(names);
        for (int i = names.length(); i < 60; i++) {
            sb.append(" ");
        }
        // 1 性别 1-男,2-女
        sb.append("1");
        String phone = "13011111111";
        // 12 手机号码
        sb.append(phone);
        for (int i = phone.length(); i < 12; i++) {
            sb.append(" ");
        }
        // 1 账户类型 0：个人账户；1：企业账户；
        sb.append("0");
        // 40 E-mail地址
        for (int i = 0; i < 40; i++) {
            sb.append(" ");
        }
        String appId = "000001969";
        // 60 请求方用户ID
        sb.append(appId);
        for (int i = appId.length(); i < 60; i++) {
            sb.append(" ");
        }
        // 9 组织机构代码 30 税务登记号 20 渠道推荐码
        for (int i = 0; i < 59; i++) {
            sb.append(" ");
        }
        // 1 账户类型 2：活期账户
        sb.append("2");
        // 2 基金公司代码 100 请求方保留信息 32 对公账户号/绑定卡号 18 营业执照编号1 1 身份角色 26 保留域
        for (int i = 0; i < 179; i++) {
            sb.append(" ");
        }
        // 以字符串结束符’\0’结尾；
        // sb.append("\\0");
        // 每条记录以回车换行 \r 回车 \n 换行
        sb.append("\r\n");
        System.err.println(sb.toString().length());
        String bank = "3015";
        String dateStr = Date4OldUtil.getNowStrDate("yyyyMMdd");
        String batchNo = "100001";
        // $$$$-APPZX????-XXXXXX-YYYYMMDD ($$$$为银行编号, XXXXXX为批次号，????为产品号， YYYYMMDD为8位日期)
        String fileName = bank + "-APPZX0012" + "-" + batchNo + "-" + dateStr;
        String path = "/Users/chaoxingyu/Downloads/";
        writeFile(sb.toString(), fileName, path);
    }

    private static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    private static void writeFile(String str, String fileName, String path) {
        /*
        Path paths = Paths.get(path + fileName);
        List<String> lines = new ArrayList<>();
        lines.add(str);

            Files.write(paths, lines, cs);
        */
        try {
            //FileWriter fileWriter = new FileWriter(path + fileName);
            //System.err.println(fileWriter.getEncoding());
            //Charset cs = Charset.forName("US-ASCII");
            //fileWriter.write(str);

            File file = new File(path + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            osw.write(str);
            osw.flush();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }


    public static void main(String[] args) throws Exception {
        /**
         System.err.println(System.getProperty("file.encoding"));
         System.err.println(System.getProperty("sun.jnu.encoding"));


         StringBuilder sb = new StringBuilder();
         sb.append("文件");
         String name = "转化为";
         for (int i = name.length(); i < 10; i++) {
         System.out.println(i);
         sb.append(" ");
         }
         sb.append("需");
         System.out.println(sb.toString());
         System.out.println(sb.toString().length());
         // String  str = "0123456789";
         // System.out.println(str.substring(6));
         */
        /** 文件由ANSI转化为UTF-8
         * 需要用到流InputStreamReader和OutputStreamWriter
         * 这两个流有charset功能
         */
        readFiles();
        // String line = System.getProperty("line.separator");
        /**
         //dealOpen();
        String str = "This is a 中文的 String!";
        System.out.println("str: " + str+" ,length: "+str.length());
        String gbk = changeCharset(str,"GBK");
        System.out.println("转换成GBK码: " + gbk+" ,length: "+gbk.length());
        String idno = "511527198001010011";
        System.err.println(idno.substring(15,16));
        System.err.println(idno.substring(16,17));
        System.err.println(idno.substring(17,18));
        System.err.println(idno.substring(18));
        //String str = "$$$$-APPZX0012RES-XXXXXX-YYYYMMDD";
        */
        //System.err.println(str.substring(str.indexOf("$$$$-APPZX0012RES-")));
        /*
        String tmt = "110.01";
        if(tmt.contains(".")){
            tmt = tmt.replace(".","");
        }
        String app = "";
        for (int i = tmt.length(); i <12 ; i++) {
            app = app+"0";
        }
        System.err.println(app);
        System.err.println(tmt);

        String str = app+tmt;
        System.err.println(str);
        System.err.println(str.length());
        */
        /*
        File srcFile = new File("/Users/chaoxingyu/Downloads/3015-APPZX0012-100001-20181109.txt");
        File destFile = new File("/Users/chaoxingyu/Downloads/3015-APPZX0012-100001-20181109");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(srcFile), "GBK");
        //ANSI编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile), "US-ASCII");
        //存为UTF-8

        int len = isr.read();
        while (-1 != len) {
            osw.write(len);
            len = isr.read();
        }
        //刷新缓冲区的数据，强制写入目标文件
        osw.flush();
        osw.close();
        isr.close();
        */

         /*
         asciiToString();// ASCII转换为字符串

         stringToAscii();// 字符串转换为ASCII码
         */
    }



    private static void readFiles(){
        // String originalFilename = "3015-APPZX0012RES-100001-20171224";
        String originalFilename = "3015-TRXRESPN-300001-997774-20171225";
        File destFile = new File("/Users/chaoxingyu/Downloads/data/"+originalFilename);
        FileInputStream fs = null;
        InputStreamReader read = null;
        BufferedReader br = null;
        List<String> list = new ArrayList<>();
        try {
            fs =new FileInputStream(destFile);
            read = new InputStreamReader(fs , "GBK");
            br = new BufferedReader(read);
            String lineTXT = null;
            int line = 1;
            while ((lineTXT = br.readLine()) != null) {
                // lineTXT = br.readLine();
                list.add(lineTXT);
                // System.err.println("第" + line + "行文件内容: " + lineTXT);
                line++;
            }
            System.err.println("总行数："+line);
            br.close();
            read.close();
            fs.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != read) {
                    read.close();
                }
                if (null != fs) {
                    fs.close();
                }
            } catch (IOException e) {

            }
        }
        //dealOpen(originalFilename,list);
        dealTrans(originalFilename,list);

    }
    private static void dealTrans(String fileName, List<String> list) {
        if (null == list || list.isEmpty()) {
            System.err.println("文件[" + fileName + "]读取失败，无内容！");
            return;
        }
        for(String str :list){
            System.err.println(str);
            System.err.println(str.length());
            String batch = str.substring(20, 26);
            System.err.println("batch(20, 26)--"+batch);
            String newAccount = str.substring(89, 108);
            System.err.println("newAccount(89, 108)--"+newAccount);
            String flag = str.substring(108, 110);
            System.err.println("flag(108, 110)--"+flag);
            //String errorCode = str.substring(40, 43);
            //System.err.println("errorCode(40, 43)--"+errorCode);
        }
    }

    private static void dealOpen(String fileName, List<String> list) {
        if (null == list || list.isEmpty()) {
            System.err.println("文件[" + fileName + "]读取失败，无内容！");
            return;
        }
        for(String str :list){
            System.err.println(str);
            System.err.println(str.length());
            String idNo = str.substring(19, 37);
            System.err.println("idNo(19, 37)--"+idNo);
            String newAccount = str.substring(0, 19);
            System.err.println("newAccount(0, 19)--"+newAccount);
            String flag = str.substring(39, 40);
            System.err.println("flag(39, 40)--"+flag);
            String errorCode = str.substring(40, 43);
            System.err.println("errorCode(40, 43)--"+errorCode);
        }
    }






    public static void asciiToString() {// ASCII转换为字符串

        String s = "22307 35806 24555 20048";// ASCII码

        String[] chars = s.split(" ");
        System.out.println("ASCII 汉字 \n----------------------");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " "
                    + (char) Integer.parseInt(chars[i]));
        }
    }

    public static void stringToAscii() {// 字符串转换为ASCII码

        String s = "你好中国！";// 字符串

        char[] chars = s.toCharArray(); // 把字符中转换为字符数组

        System.out.println("\n\n汉字 ASCII\n----------------------");
        for (int i = 0; i < chars.length; i++) {// 输出结果

            System.out.println(" " + chars[i] + " " + (int) chars[i]);
        }
    }
}
