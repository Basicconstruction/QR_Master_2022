package org.qrmaster.ui.fragment.signlawrite.supermode.multiColor;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddListItem extends Fragment {
    private AddList parent;
    int x_width;
    int y_height;
    JLabel deleteTable;
    Fragment fragment;
    public AddListItem(AddList parent,int width,int height,Fragment fragment){
        super();
        setSize(width,height);
        this.parent = parent;
        x_width = width;
        y_height = height;
        this.fragment = fragment;
        fragment.setLocation(0,0);
        add(fragment);
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        deleteTable = new JLabel("-",JLabel.CENTER);
        deleteTable.setOpaque(true);
        deleteTable.setBorder(new LineBorder(Color.BLACK));
        deleteTable.setFont(new Font("微软也黑",Font.PLAIN,30));
//        deleteTable.setBackground(Color.PINK);
//        deleteTable.setBounds(x_width-deleteSquare,(y_height-deleteSquare)/2,deleteSquare,deleteSquare);
        deleteTable.setSize(40,40);
        deleteTable.setLocation(136,5);
        deleteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getParent().delete(AddListItem.this);
            }
        });
        add(deleteTable);
        repaint();
    }

    @Override
    public AddList getParent() {
        return parent;
    }
    public Color getColor(){
        return ((ColorBehavior)fragment).getColor();
    }
}
