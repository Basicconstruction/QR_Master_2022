package org.qrmaster.operation.datatransfer.read.text_picker;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TextCollector {
    /**
     * 序列指针
     * */
    private int it = 0;
    /**
     * 指针是否还能够向后一位指向。
     * */
    public boolean hasNext = true;
    /**
     * 对象解析的StringBuilder对象
     * */
    private final StringBuilder sb = new StringBuilder();
    /**
     * 对象解析的ArrayList<String>对象
     * */
    private ArrayList<String> data = new ArrayList<>();
    public TextCollector(File file) {
        try{
            BufferedReader r = new BufferedReader(new FileReader(file));
            String s = r.readLine();
            while(s!=null&&!Objects.equals(s,"")){
                if(!s.equals("\r")){
                    sb.append(s.replace("\r","")).append("\n");
                }
                s = r.readLine();
            }
            data = new TextIterator(sb).getAsArrayList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try{
//            Scanner sc = new Scanner(file);
//            sc.useDelimiter("\n");
//            while(sc.hasNext()){
//                String s = sc.next();
//                if((!s.equals("\r")&&!s.equals(""))){
//                    sb.append(s.replace("\r","")).append("\n");
//                }
//            }
//            data = new TextIterator(sb).getAsArrayList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public TextCollector(String path){
        try{
            BufferedReader r = new BufferedReader(new FileReader(path));
            String s = r.readLine();
            while(s!=null&&!Objects.equals(s,"")){
                if(!s.equals("\r")){
                    sb.append(s.replace("\r","")).append("\n");
                }
                s = r.readLine();
            }
            data = new TextIterator(sb).getAsArrayList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try{
//            Scanner sc = new Scanner(new File(path));
//            sc.useDelimiter("\n");
//            while(sc.hasNext()){
//                sb.append(sc.next()).append("\n");
//            }
//            data = new TextIterator(sb).getAsArrayList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static String getAsText(File file){
        String text = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            text = new String(fileInputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    public TextCollector(FileReader fr){
        try{
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while((s=br.readLine())!=null){
                sb.append(s).append("\n");
            }
            br.close();
            data = new TextIterator(sb).getAsArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public TextCollector(BufferedReader br){
        try{
            String s = null;
            while((s=br.readLine())!=null){
                sb.append(s).append("\n");
            }
            br.close();
            data = new TextIterator(sb).getAsArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 返回对象解析的ArrayList<String>对象
     * */
    public ArrayList<String> getAsArrayList(){
        return this.data;
    }
    /**
     * 返回该对象解析获得的整体StringBuilder对象.
     * */
    public StringBuilder getAsStringBuilder(){
        return this.sb;
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
        if(it< data.size()-1){
            s =  data.get(it);
            hasNext = true;
            it ++;
        }else if(it== data.size()-1){
            s =  data.get(it);
            hasNext = false;
            it ++;
        }else{
            s = null;
        }

        return s;
    }
    public TextIterator getAsTextIterator(){
        return new TextIterator(this.sb);
    }
}
