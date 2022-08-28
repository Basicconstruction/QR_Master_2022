package org.qrmaster.ui.fragment.signlawrite.supermode.signalImage;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.datatackle.bufferedImage.zoom.BufferedImageZoom;
import org.qrmaster.ui.fragment.Fragment;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignalImage extends Fragment {
    private JButton imagePicker = new JButton("Iamge Picker");
    private JTextField path = new JTextField();
    private Region region = new Region(Color.WHITE);
    FileNameExtensionFilter filter;
    JFileChooser chooser;
    public SignalImage(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(0,0,220,200);
        imagePicker.setBounds(110,15,90,35);
        path.setBounds(20,60,130,30);
        region.setBounds(30,100,80,80);
        add(imagePicker);
        add(path);
        add(region);
        filter = new FileNameExtensionFilter("*.img","jpg","png","jpeg");
        chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        imagePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = chooser.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION){
                    String pave = chooser.getSelectedFile().getAbsolutePath();
                    path.setText(pave);
                    BufferedImage bi = null;
                    try {
                        bi = ImageIO.read(new File(pave));
                    } catch (IOException ex) {
                        path.setText("选择的并非图片");
                        throw new RuntimeException(ex);
                    }
                    try {
                        bi = BufferedImageZoom.getBufferedImageZoom(bi,80,80);
                        region.setBufferedImage(bi);
                        System.out.println("bi: "+bi);
                        UiInitializer.qrProperty.setBufferedImage(bi);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
