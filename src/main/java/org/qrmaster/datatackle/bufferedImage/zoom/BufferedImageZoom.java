package org.qrmaster.datatackle.bufferedImage.zoom;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageZoom {
    public static BufferedImage getBufferedImageZoom(BufferedImage bufferedImage,int newWidth,int newHeight) throws IOException {;
        Image img = bufferedImage.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH);
        BufferedImage image = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.drawImage(img,0,0,null);
        g2.dispose();
        return image;
    }
    public static BufferedImage getBufferedImageZoom(BufferedImage bufferedImage,double scale) throws IOException {
        int newWidth = (int) (bufferedImage.getWidth()*scale);
        int newHeight = (int) (bufferedImage.getWidth()*scale);
        return getBufferedImageZoom(bufferedImage,newWidth,newHeight);
    }
    public static BufferedImage getBufferedImageZoom(BufferedImage bufferedImage,double xscale,double yscale) throws IOException {
        int newWidth = (int) (bufferedImage.getWidth()*xscale);
        int newHeight = (int) (bufferedImage.getWidth()*yscale);
        return getBufferedImageZoom(bufferedImage,newWidth,newHeight);
    }
}
