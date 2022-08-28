package org.qrmaster.operation.datatransfer.read.text_parser;


import org.qrmaster.operation.datatransfer.read.text_picker.EasySplit;
import org.qrmaster.operation.datatransfer.read.text_picker.TextIterator;

public class CSVLikeParser {
    double[][] res;
    /**
     * StringBuilder as input data,
     * splits as 分隔符
     * part as 指定的分割部分
     * */
    public CSVLikeParser(StringBuilder sb, String splits, int part){
        TextIterator ti = new TextIterator(sb);
        res = new double[ti.getAsArrayList().size()][part];
        for(int i = 0; i < res.length;i++){
            String text = ti.getLine();
            EasySplit es = new EasySplit(splits);
            String[] got = es.getSplits(text);
            for(int j = 0;j < part;j++){
                res[i][j] = j >= got.length? 0.0:Double.parseDouble(got[j]);
//                res[i][j] = Double.parseDouble(got[j]);
            }
        }
    }
    /**
     * 返回分割后的二维数组
     * */
    public double[][] getData(){
        return this.res;
    }
}
