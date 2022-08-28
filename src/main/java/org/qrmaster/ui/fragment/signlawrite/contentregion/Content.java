package org.qrmaster.ui.fragment.signlawrite.contentregion;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Content extends Fragment implements ContentBehavior{
    JEditorPane area = new JEditorPane();
    public Content(){
        super();
        setBorder(new LineBorder(Color.BLACK));
        initComponents();
    }
    @Override
    public void initComponents(){
        area.setBounds(20,20,260,430);
        add(area);
        area.setFont(new Font("微软雅黑",Font.PLAIN,18));
        this.setVisible(true);
    }
    public String getContent(){
        return area.getText();
    }
}
