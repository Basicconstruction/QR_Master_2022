package org.qrmaster.ui.fragment;

import javax.swing.*;
import java.awt.*;

public class Fragment extends JPanel {
    public int serializeId;
    public String describe;
    public Fragment(){
        super();
        serializeId = 10000;
        setLayout(null);
        setBackground(Color.white);
    }
    public int getSerializeId(){
        return serializeId;
    }
    public void initComponents(){

    }

}
