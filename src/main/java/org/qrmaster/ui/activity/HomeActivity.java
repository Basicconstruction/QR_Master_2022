package org.qrmaster.ui.activity;

import org.qrmaster.ui.sync.WorkSyncer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeActivity extends Activity{
    public HomeActivity() throws NoSuchFieldException, IllegalAccessException {
        super();
        initialize();
        loadActivity();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.setSize(1200,800);
        JLabel logo = new JLabel("QR Master");
        this.add(logo);
        logo.setBounds(0,0,170,50);
        logo.setFont(new Font("微软雅黑",Font.PLAIN,18));
        logo.setForeground(Color.CYAN);
        JLabel signalRead,signalWrite,multiRead,multiWrite;
        signalRead = new JLabel("读取二维码",JLabel.CENTER);
        signalWrite = new JLabel("生成二维码",JLabel.CENTER);
        multiRead = new JLabel("批量读取二维码",JLabel.CENTER);
        multiWrite = new JLabel("批量生成二维码",JLabel.CENTER);
        signalRead.setBorder(new LineBorder(Color.BLACK,1));
        signalWrite.setBorder(new LineBorder(Color.BLACK,1));
        multiRead.setBorder(new LineBorder(Color.BLACK,1));
        multiWrite.setBorder(new LineBorder(Color.BLACK,1));
        signalWrite.setBounds(200,120,400,280);
        signalRead.setBounds(600,120,400,280);
        multiWrite.setBounds(200,400,400,280);
        multiRead.setBounds(600,400,400,280);
        add(signalWrite);
        add(signalRead);
        add(multiWrite);
        add(multiRead);
        JLabel analysis = new JLabel("分析模式",JLabel.CENTER);
        analysis.setBorder(new LineBorder(Color.BLACK,1));
        analysis.setBounds(1040,40,120,50);
        add(analysis);
        signalWrite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    WorkSyncer.webView.pushActivity(new SignalWriteActivity());
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        signalRead.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    WorkSyncer.webView.pushActivity(new SignalReadActivity());
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        multiWrite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    WorkSyncer.webView.pushActivity(new MultiWriteActivity());
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        multiRead.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    WorkSyncer.webView.pushActivity(new MultiReadActivity());
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void loadActivity() throws NoSuchFieldException, IllegalAccessException {
        super.loadActivity();
    }

    @Override
    public void exitActivity() {
        super.exitActivity();
    }

    @Override
    public void destroyActivity() {
        super.destroyActivity();
    }
}
