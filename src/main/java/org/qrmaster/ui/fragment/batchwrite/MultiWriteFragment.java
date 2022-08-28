package org.qrmaster.ui.fragment.batchwrite;

import org.qrmaster.operation.datatransfer.read.MultiWriteCSVData;
import org.qrmaster.operation.datatransfer.read.text_picker.TextCollector;
import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class MultiWriteFragment extends Fragment {
    JLabel chooseFile = new JLabel("选择文件",JLabel.CENTER);
    JButton chooser = new JButton("选取");
    JLabel path = new JLabel("",JLabel.CENTER);
    JLabel makeFormat = new JLabel("生成模式",JLabel.CENTER);
    JComboBox<String> format;
    Fragment formatArea;
    FileNameExtensionFilter filter;
    JFileChooser fileChooser;
    File choosedFile;
    public MultiWriteFragment(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(50,140,300,470);
        filter = new FileNameExtensionFilter(".所有文件","txt","csv","*");
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(filter);
        String[] formats = new String[2];
        formats[0] = "EACH LINE";
        formats[1] = "CSV";
        format = new JComboBox<>(formats);
        formatArea = new EachLineProfile();
        add(formatArea);


        chooseFile.setBounds(30,30,110,40);
        chooser.setBounds(170,30,100,40);
        path.setBounds(30,85,250,35);
        path.setBorder(new LineBorder(Color.BLACK));
        makeFormat.setBounds(30,140,120,40);
        format.setBounds(170,140,90,40);
        add(chooseFile); add(chooser); add(path); add(makeFormat); add(format);
        chooser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int result = fileChooser.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION){
                    path.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    choosedFile = fileChooser.getSelectedFile();
                    if(formatArea instanceof EachLineProfile){
                        ((EachLineProfile)formatArea).getTextPane().setText(new TextCollector(choosedFile).getAsStringBuilder().toString());
                    }
                }
            }
        });
        format.addItemListener(e -> {
            if(e.getStateChange()==ItemEvent.SELECTED){
                if(e.getItem().equals("CSV")){
                    remove(formatArea);
                    formatArea = new CSVProfile();
                    add(formatArea);
                }else{
                    remove(formatArea);
                    formatArea = new EachLineProfile();
                    add(formatArea);
                }
            }
            formatArea.repaint();
            MultiWriteFragment.this.repaint();
        });


    }
    public ArrayList<String> getListText(){
        if(formatArea instanceof EachLineProfile){
            return new TextCollector(choosedFile).getAsArrayList();
        }else{
            return MultiWriteCSVData.getContents(choosedFile,((CSVProfile)formatArea).getSplit(),((CSVProfile)formatArea).withTitle(),((CSVProfile)formatArea).getColumn());
        }
    }
}
