package org.qrmaster.ui.fragment.batchwrite;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;

public class CSVProfile extends Fragment {
    JLabel title = new JLabel("条目头",JLabel.CENTER);
    JLabel column = new JLabel("条目",JLabel.CENTER);
    JLabel split = new JLabel("分割符号",JLabel.CENTER);
    JComboBox<Boolean> titlePicker;
    JTextField columnPicker;
    JTextField splitPicker;
    JButton look = new JButton("预览");
    public CSVProfile(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(30,200,250,250);
        Boolean[] titleSelects = new Boolean[2];
        titleSelects[0] = true;
        titleSelects[1] = false;
        titlePicker = new JComboBox<>(titleSelects);
        columnPicker = new JTextField();
        splitPicker = new JTextField();
        title.setBounds(50,10,80,40);
        column.setBounds(50,60,80,40);
        split.setBounds(50,110,80,40);
        titlePicker.setBounds(135,10,90,40);
        columnPicker.setBounds(135,60,90,40);
        splitPicker.setBounds(135,110,90,40);
        add(title);
        add(column);
        add(split);
        add(titlePicker);
        add(columnPicker);
        add(splitPicker);
        look.setBounds(135,180,80,40);

    }
    public boolean withTitle(){
        return titlePicker.getSelectedIndex()==0;
    }
    public String getSplit(){
        return splitPicker.getText();
    }
    public int getColumn(){
        return Integer.parseInt(columnPicker.getText());
    }
}
