package org.qrmaster.operation.datatransfer.read.text_picker;

/**
 *合并字符串中连续出现的空格
 * */
public class SpaceFilter {
    /**
     * 单循环状态机,合并字符串中出现的连续空格.
     * 设计时没有考虑其他字符的状况，这是因为相比空格来说，其他字符都不应被如此过滤。
     */
    public static String getFilterText(String text){
        boolean status = false;//代表前面一个字符是空格吗?
        StringBuilder builder = new StringBuilder();
        for(char c : text.toCharArray()){
            if(c==' '){
                if(!status){
                    builder.append(" ");
                }
                status = true;
            }else{
                builder.append(c);
                status = false;
            }
        }
        return builder.toString();
    }
    public static String getFilterText(StringBuilder text){
        return getFilterText(text.toString());
    }
}
