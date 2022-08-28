package org.qrmaster.ui.fragment.signlawrite.supermode.signalColor;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public class PreSetColor extends Fragment {
    JLabel look;
    JLabel colorLook;
    JLabel selectColor;
    JComboBox<String> colorPicker;
    public PreSetColor(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(30,50,160,90);
        setBorder(new LineBorder(Color.BLACK));
        selectColor = new JLabel("选择颜色",JLabel.CENTER);
        add(selectColor);
        selectColor.setBounds(20,10,60,30);
        Vector<String> preColor = new Vector<>();
        preColor.add("BLACK");
        preColor.add("BLUE");
        preColor.add("PURPLE");
        preColor.add("RED");
        colorPicker = new JComboBox<>(preColor);

        look = new JLabel("预览",JLabel.CENTER);
        add(look);
        look.setBounds(20,40,60,40);
        colorLook = new JLabel();
        colorLook.setOpaque(true);
        add(colorLook);
        colorLook.setBackground(Color.BLACK);
        colorLook.setBounds(80,40,60,40);

        UiInitializer.qrProperty.setColor(Color.BLACK);
        colorPicker.addItemListener(e -> {
            String selectColor = (String) e.getItem();
            if(e.getStateChange() == ItemEvent.SELECTED){
                switch (selectColor){
                    case "BLACK":
                        colorLook.setBackground(Color.BLACK);
                        UiInitializer.qrProperty.setColor(Color.BLACK);
                        break;
                    case "BLUE":
                        colorLook.setBackground(Color.BLUE);
                        UiInitializer.qrProperty.setColor(Color.BLUE);
                        break;
                    case "PURPLE":
                        colorLook.setBackground(Color.MAGENTA);
                        UiInitializer.qrProperty.setColor(Color.MAGENTA);
                        break;
                    case "RED":
                        colorLook.setBackground(Color.RED);
                        UiInitializer.qrProperty.setColor(Color.RED);
                        break;
                    default:


                }
            }
        });
        colorPicker.setBounds(80,10,60,30);
        add(colorPicker);
    }
}
