package org.qrmaster.ui.fragment.batchwrite;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EachLineProfile extends Fragment {
    JTextPane jTextPane = new JTextPane();
    public EachLineProfile(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBorder(new LineBorder(Color.BLACK));
        setBounds(30,200,250,250);
        jTextPane.setBounds(10,1,230,240);
        add(jTextPane);
    }
    public JTextPane getTextPane(){
        return jTextPane;
    }
}
