package org.qrmaster.ui.fragment.signalread;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class QRReadProperty {
    private BufferedImage bufferedImage = null;
    private ArrayList<BufferedImage> bufferedImages = null;
    private QRReadType qrReadType = null;

    public QRReadProperty() {

    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public ArrayList<BufferedImage> getBufferedImages() {
        return bufferedImages;
    }

    public void setBufferedImages(ArrayList<BufferedImage> bufferedImages) {
        this.bufferedImages = bufferedImages;
    }

    public QRReadType getQrReadType() {
        return qrReadType;
    }

    public void setQrReadType(QRReadType qrReadType) {
        this.qrReadType = qrReadType;
    }
}
