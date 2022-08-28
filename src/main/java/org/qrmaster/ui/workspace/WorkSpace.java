package org.qrmaster.ui.workspace;

import javax.swing.*;
import java.awt.*;

public class WorkSpace extends JFrame {
    QRMasterWebPool webPool;
    public WorkSpace(QRMasterWebPool webPool){
        super();
        this.webPool = webPool;
        setTitle("QR Master_window_v1.05");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(new Point(300,100));
        setSize(1216,839);
        this.add(webPool);
    }

    public QRMasterWebPool getWebPool() {
        return webPool;
    }
}
