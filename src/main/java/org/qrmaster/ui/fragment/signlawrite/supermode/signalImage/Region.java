package org.qrmaster.ui.fragment.signlawrite.supermode.signalImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Region extends JComponent implements MouseListener {
    Color color;
    BufferedImage bufferedImage;
    String tes;
    public Region(Color color){
        super();
        setOpaque(true);
        setBackground(color);
        this.color = color;
//        this.addMouseListener(this);
        setBorder(new LineBorder(Color.BLACK));
    }
    public Region(BufferedImage bi){
        super();
        bufferedImage = bi;
//        this.addMouseListener(this);
        setBorder(new LineBorder(Color.BLACK));
    }
    public void setBufferedImage(BufferedImage bi){
        this.bufferedImage = bi;
        repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(bufferedImage!=null){
            g2d.drawImage(bufferedImage,0,0,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("bounds: "+getBounds());
        System.out.println(tes);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
