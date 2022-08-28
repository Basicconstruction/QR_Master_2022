package org.qrmaster.ui.fragment.signlawrite.supermode.signalColor;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class RgbColor extends Fragment {
    JLabel look;
    JLabel colorLook;
    JLabel selectColor;
    JTextField colorPicker;
    public RgbColor() {
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(30,50,160,90);
        setBorder(new LineBorder(Color.BLACK));
        selectColor = new JLabel("颜色",JLabel.CENTER);
        add(selectColor);
        selectColor.setBounds(20,10,60,30);
        colorPicker = new JTextField();

        look = new JLabel("预览",JLabel.CENTER);
        add(look);
        look.setBounds(20,40,60,40);
        colorLook = new JLabel();
        colorLook.setOpaque(true);
        add(colorLook);
        colorLook.setBackground(Color.BLACK);
        colorLook.setBounds(80,40,60,40);

        UiInitializer.qrProperty.setColor(Color.BLACK);
        colorPicker.setBounds(80,10,60,30);
        add(colorPicker);
        colorPicker.addCaretListener(e -> {
            if(!Objects.equals(colorPicker.getText(), "")){
                int x = Integer.parseInt(colorPicker.getText(),16);
                Color color;
                try{
                     color = new Color(x/256/256,x/256%256,x%256);
                }catch (IllegalArgumentException ill){
                    color = Color.BLACK;
                }
                colorLook.setBackground(color);
                UiInitializer.qrProperty.setColor(color);
            }
        });
    }
//    public String getColorContent(){
//        return colorPicker.getText();
//    }
}
