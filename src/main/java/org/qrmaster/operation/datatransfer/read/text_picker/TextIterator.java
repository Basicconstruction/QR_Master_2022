package org.qrmaster.operation.datatransfer.read.text_picker;

import java.util.ArrayList;
import java.util.Arrays;

public class TextIterator {
    /**
     * 序列指针
     * */
    private int it = 0;
    /**
     * 指针是否还能够向后一位指向。
     * */
    public boolean hasNext = true;
    /**
     * 对象解析的ArrayList<String>对象
     * */
    private final ArrayList<String> data = new ArrayList<>();
    public TextIterator(String text){
        data.addAll(Arrays.asList(text.split("\\n")));
    }
    public TextIterator(StringBuilder text){
        data.addAll(Arrays.asList((text.toString()).split("\\n")));
    }
    /**
     * 返回对象解析的ArrayList<String>对象
     * */
    public ArrayList<String> getAsArrayList(){
        return this.data;
    }
    /**
     * 初始化指针
     * */
    public void setIterator(){
        it = 0;
        if(data.size()>1){
            hasNext = true;
        }
    }
    /**
     * 如果本对象的 ArrayList<String>指针没有指向序列的最后一个元素，则返回指向的元素，
     * 通常可使用 if(obj.hasNext){ },然后再调用getLine()函数.直接调用而不经过判断，则
     * 会在元素均返回后返回null.
     *
     * */
    public String getLine(){
        String s;
        if(it<data.size()-1){
            s =  data.get(it);
            hasNext = true;
            it ++;
        }else if(it==data.size()-1){
            s =  data.get(it);
            hasNext = false;
            it ++;
        }else{
            s = null;
        }

        return s;
    }

}