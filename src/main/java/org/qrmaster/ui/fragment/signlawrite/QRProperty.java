package org.qrmaster.ui.fragment.signlawrite;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class QRProperty {
    public QRContentType qrContentType;
    public String barcodeSize;
    public ErrorCorrectionLevel errorCorrectionLevel;
    public String characterEncoding;

    private SpecialType specialType;
    private Color color;
    private ArrayList<Color> colors;
    private BufferedImage bufferedImage;
    private BufferedImage[] bufferedImages;

    public QRProperty(){
        qrContentType = QRContentType.Text;
        barcodeSize = "Small";
        errorCorrectionLevel = ErrorCorrectionLevel.L;
        characterEncoding = "Utf-8";
    }
    public QRProperty(QRContentType qrContentType,String barcodeSize,ErrorCorrectionLevel errorCorrectionLevel,String characterEncoding){
        this.qrContentType = qrContentType;
        this.barcodeSize = barcodeSize;
        this.errorCorrectionLevel = errorCorrectionLevel;
        this.characterEncoding = characterEncoding;
    }

    public SpecialType getSpecialType() {
        return specialType;
    }

    public void setSpecialType(SpecialType specialType) {
        this.specialType = specialType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage[] getBufferedImages() {
        return bufferedImages;
    }

    public void setBufferedImages(BufferedImage[] bufferedImages) {
        this.bufferedImages = bufferedImages;
    }

    @Override
    public String toString() {
        return "QRProperty{" +
                "qrContentType=" + qrContentType +
                ", barcodeSize='" + barcodeSize + '\'' +
                ", errorCorrectionLevel=" + errorCorrectionLevel +
                ", characterEncoding='" + characterEncoding + '\'' +
                ", specialType=" + specialType +
                ", color=" + color +
                ", colors=" + colors +
                ", bufferedImage=" + bufferedImage +
                ", bufferedImages=" + Arrays.toString(bufferedImages) +
                '}';
    }
}
