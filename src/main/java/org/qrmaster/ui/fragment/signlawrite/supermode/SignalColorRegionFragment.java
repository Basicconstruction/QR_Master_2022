package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.signalColor.PreSetColor;
import org.qrmaster.ui.fragment.signlawrite.supermode.signalColor.RgbColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignalColorRegionFragment extends Fragment implements SpecialRegion {
    Fragment colorArea;
    JLabel preSet;
    JLabel rgb;
    public SignalColorRegionFragment(){
        super();
        initComponents();
//        setBackground(Color.BLACK);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(40,220,220,200);
        preSet = new JLabel("预设",JLabel.CENTER);
        rgb = new JLabel("rgb",JLabel.CENTER);
        preSet.setBounds(30,18,80,31);
        rgb.setBounds(110,18,80,31);
        add(preSet);
        add(rgb);
        preSet.setOpaque(true);
        rgb.setOpaque(true);
        preSet.setBackground(Color.GRAY);
        rgb.setBackground(Color.WHITE);

        colorArea = new PreSetColor();
        add(colorArea);

        preSet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(colorArea instanceof RgbColor){
                    remove(colorArea);
                    colorArea = new PreSetColor();
                    add(colorArea);
                    preSet.setBackground(Color.GRAY);
                    rgb.setBackground(Color.WHITE);
                }
                colorArea.repaint();
            }
        });
        rgb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(colorArea instanceof PreSetColor){
                    remove(colorArea);
                    colorArea = new RgbColor();
                    add(colorArea);
                    preSet.setBackground(Color.WHITE);
                    rgb.setBackground(Color.GRAY);
                }
                colorArea.repaint();
            }
        });
    }
}
