package org.qrmaster.boot;

import org.qrmaster.ui.fragment.signalread.QRReadProperty;
import org.qrmaster.ui.fragment.signlawrite.QRProperty;
import org.qrmaster.ui.sync.WorkSyncer;
import org.qrmaster.ui.workspace.WorkSpace;

import javax.swing.*;

public class UiInitializer {
    public static WorkSyncer syncer;
    public static QRProperty qrProperty = new QRProperty();
    public static QRReadProperty qrReadProperty = new QRReadProperty();
    static {
        try {
            syncer = new WorkSyncer();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
               UnsupportedLookAndFeelException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public UiInitializer(){

    }
    public void run(){
        WorkSpace ws = WorkSyncer.workSpace;
        SwingUtilities.invokeLater(()->{
            ws.setVisible(true);
        });
    }
}
