package org.qrmaster.ui.fragment.signlawrite.supermode.multiColor;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class RgbAddListFragment extends Fragment implements ColorBehavior {
    private Color color = Color.WHITE;
    JTextField rgb;
    JLabel rgbShow;
    public RgbAddListFragment(){
        super();
        initComponents();
        repaint();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setSize(136,50);
        rgb = new JTextField();
        rgbShow = new JLabel();
        rgbShow.setOpaque(true);
        rgbShow.setBorder(new LineBorder(Color.BLACK));
        add(rgb);
        add(rgbShow);
        rgb.setBounds(10,5,76,40);
        rgbShow.setBounds(96,5,40,40);
        rgb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!Objects.equals(rgb.getText(), "")&&rgb.getText().length()<=6){
                    int x = Integer.parseInt(rgb.getText(),16);
                    try{
                        color = new Color(x/256/256,x/256%256,x%256);
                    }catch (IllegalArgumentException ill){
                        System.out.println("color error");
                        color = Color.BLACK;
                    }
                    rgbShow.setBackground(color);
//                    UiInitializer.qrProperty.setColor(color);
                }else{
                    System.out.println("超出颜色范围");
                    color = Color.BLACK;
                    rgbShow.setBackground(color);
                }
                RgbAddListFragment.this.repaint();

            }
        });

    }

    @Override
    public Color getColor() {
        return color;
    }
}
