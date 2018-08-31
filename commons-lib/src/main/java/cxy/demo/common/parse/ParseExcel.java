package cxy.demo.common.parse;

public class ParseExcel {




    public static void main(String[] args) {
        String lines = System.getProperty("line.separator");
        StringBuffer sbf = new StringBuffer();
        sbf.append("public class Aa { " + lines);
        sbf.append("11111"+lines);
        sbf.append("22222+ +\""+lines);
        sbf.append("}");
        System.err.println(sbf.toString());
        String filepath = "Files\\接口文档\\00010000397304_查询对私客户信息_provider_body(1).xls";
        String fileName = "";
        if(filepath.contains("/")){
            fileName =  filepath.substring(filepath.lastIndexOf("/") + 1);
        }else if(filepath.contains("\\")){
            fileName =  filepath.substring(filepath.lastIndexOf("\\") + 1);
        }
        System.err.println(fileName);
    }



}
