package org.qrmaster.operation.datatransfer.read;

import org.qrmaster.operation.datatransfer.read.text_picker.TextCollector;

import java.io.File;
import java.util.ArrayList;

public class MultiWriteCSVData {
    public static ArrayList<String> getContents(File file,String split,boolean withTitle,int columns){
        ArrayList<String> lines = new TextCollector(file).getAsArrayList();
        ArrayList<String> collect = new ArrayList<>();
        String firstLine = lines.get(0);
        String[] parts = firstLine.split(split,columns);
        for(int i = 1;i<lines.size();i++){
            String line = lines.get(i);
            String[] row = line.split(split,columns);
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j< parts.length;j++){
                sb.append(parts[j]).append(": ").append(j>=row.length?"":row[j]).append("\n");
            }
            collect.add(sb.toString());
        }
        return collect;
    }
    public static void main(String[] args){
        for(String s:MultiWriteCSVData.getContents(new File("C:\\Users\\betha\\Documents\\tt.csv"), ",",true,3)){
            System.out.println(s);
        }
    }
}
