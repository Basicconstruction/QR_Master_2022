package org.qrmaster.ui.fragment.signalread;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SignalReadFragment extends Fragment {
    SignalReadRegion qrPikcer;
    JTextArea textArea;
    public SignalReadFragment() {
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(0,50,1200,750);
        qrPikcer = new SignalReadRegion(this);
        textArea = new JTextArea();
        textArea.setBounds(870,40,290,650);
        textArea.setBorder(new LineBorder(Color.BLACK));
        textArea.setFont(new Font("微软雅黑",Font.PLAIN,18));
        add(qrPikcer);
        add(textArea);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
