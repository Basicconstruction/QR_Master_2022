package org.qrmaster.ui.fragment.signalread;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.datatackle.bufferedImage.zoom.BufferedImageZoom;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.signalImage.Region;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignalReadRegion extends Fragment {
    BufferedImage bi = null;
    JLabel loadFromPath = new JLabel("从路径选取二维码",JLabel.CENTER);
    JLabel loadFromSystem = new JLabel("从文件系统中选择二维码",JLabel.CENTER);
    JTextField path = new JTextField();
    JButton pathGet = new JButton("读取");
    JButton sysGet = new JButton("读取");
    Region region = new Region(Color.WHITE);
    FileNameExtensionFilter filter;
    JFileChooser chooser;
    JLabel scan = new JLabel("扫描",JLabel.CENTER);
    Fragment parentFragment;
    public SignalReadRegion(Fragment parent) {
        super();
        this.parentFragment = parent;
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(60,40,780,690);
        loadFromPath.setBounds(70,15,120,40);
        loadFromSystem.setBounds(70,100,120,40);
        path.setBounds(195,10,370,50);
        pathGet.setBounds(600,10,93,50);
        sysGet.setBounds(600,90,93,50);
        region.setBounds(154,180,500,500);
        add(loadFromPath);
        add(loadFromSystem);
        add(path);
        add(pathGet);
        add(sysGet);
        add(region);
        pathGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bi = ImageIO.read(new File(path.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    region.setBufferedImage(BufferedImageZoom.getBufferedImageZoom(bi,500,500));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                UiInitializer.qrReadProperty.setQrReadType(QRReadType.signalRead);
                UiInitializer.qrReadProperty.setBufferedImage(bi);
            }
        });
        filter = new FileNameExtensionFilter(".img","png","jpg","jpeg");
        chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        sysGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = chooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    try {
                        bi = ImageIO.read(chooser.getSelectedFile());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        region.setBufferedImage(BufferedImageZoom.getBufferedImageZoom(bi,500,500));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    UiInitializer.qrReadProperty.setQrReadType(QRReadType.signalRead);
                    UiInitializer.qrReadProperty.setBufferedImage(bi);
                }
            }
        });
        scan.setBorder(new LineBorder(Color.BLACK));
        scan.setBounds(660,305,120,60);
        scan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((SignalReadFragment)parentFragment).getTextArea().setText(QRReader.getContent(bi));
            }
        });
        add(scan);

    }
}
