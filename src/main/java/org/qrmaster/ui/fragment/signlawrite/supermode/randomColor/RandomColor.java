package org.qrmaster.ui.fragment.signlawrite.supermode.randomColor;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RandomColor extends Fragment {
    JButton random = new JButton("random");
    JLabel color1 = new JLabel();
    JLabel color2 = new JLabel();
    JLabel color3 = new JLabel();
    JLabel color4 = new JLabel();
    JLabel color5 = new JLabel();
    JLabel color6 = new JLabel();
    JLabel color7 = new JLabel();
    JLabel color8 = new JLabel();
    private ArrayList<Color> colors = new ArrayList<>();
    Random rand = new Random();
    public RandomColor() {
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(0,0,220,200);
        color1.setOpaque(true); color2.setOpaque(true);
        color3.setOpaque(true); color4.setOpaque(true);
        color5.setOpaque(true); color6.setOpaque(true);
        color7.setOpaque(true); color8.setOpaque(true);
        color1.setBackground(Color.BLACK); color2.setBackground(Color.BLACK);
        color3.setBackground(Color.BLACK); color4.setBackground(Color.BLACK);
        color5.setBackground(Color.BLACK); color6.setBackground(Color.BLACK);
        color7.setBackground(Color.BLACK); color8.setBackground(Color.BLACK);
        color1.setBounds(15,90,40,40);
        color2.setBounds(65,90,40,40);
        color3.setBounds(115,90,40,40);
        color4.setBounds(165,90,40,40);
        color5.setBounds(15,140,40,40);
        color6.setBounds(65,140,40,40);
        color7.setBounds(115,140,40,40);
        color8.setBounds(165,140,40,40);
        add(color1); add(color2);
        add(color3); add(color4);
        add(color5); add(color6);
        add(color7); add(color8);
        add(random);
        random.setBounds(100,15,90,35);
        for(int i =0;i<8;i++){
            colors.add(Color.BLACK);
        }
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colors.clear();
                for(int i = 0;i<8;i++){
                    colors.add(getRandomColor(rand.nextBoolean()|rand.nextBoolean()|rand.nextBoolean(), rand.nextBoolean()|rand.nextBoolean()|rand.nextBoolean() ,rand.nextBoolean()|rand.nextBoolean()|rand.nextBoolean()));
                }
                UiInitializer.qrProperty.setColors(colors);
                color1.setBackground(colors.get(0)); color2.setBackground(colors.get(1));
                color3.setBackground(colors.get(2)); color4.setBackground(colors.get(3));
                color5.setBackground(colors.get(4)); color6.setBackground(colors.get(5));
                color7.setBackground(colors.get(6)); color8.setBackground(colors.get(7));
            }
        });
    }
    public Color getRandomColor(boolean i,boolean j,boolean k){
        int x = 1,y = 1,z = 1;
        if(i){
            x = rand.nextInt(255);
        }
        if(j){
            y = rand.nextInt(255);
        }
        if(j){
            z = rand.nextInt(255);;
        }
        if(i&&j&&k){
            x = rand.nextInt(150);
        }
        return new Color(x,y,z);
    }
}
