package org.qrmaster.ui.fragment.signlawrite;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import org.qrmaster.datatackle.bufferedImage.zoom.BufferedImageZoom;
import org.qrmaster.ui.fragment.Fragment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QRMaker extends Fragment {
    BufferedImage bufferedImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
    public QRMaker(){
        super();
//        setBorder(new LineBorder(Color.BLACK));
        setBounds(670,110,500,500);
//        setOpaque(true);
//        setBackground(Color.BLACK);
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        for(int i = 1;i<499;i++){
            for(int j = 1;j<499;j++){
                bufferedImage.setRGB(i,j,0xffffff);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bufferedImage,0,0,null);
//        System.out.println(bufferedImage);
    }
    public ArrayList<BufferedImage> makeQRCode(QRProperty qrProperty, ArrayList<String> contents) throws WriterException{
        ArrayList<BufferedImage> bis = new ArrayList<>();
        int paintWidth = 120,paintHeight = 120;
        for(String str:contents){
            BufferedImage bi = new BufferedImage(paintWidth,paintHeight,BufferedImage.TYPE_INT_RGB);
            paintQRCodeInBufferedImage(qrProperty,bi,str);
            bis.add(bi);
        }
        return bis;
    }

    private void paintQRCodeInBufferedImage(QRProperty qrProperty,BufferedImage bi,String str) {
        try{
            makeQRCode(qrProperty,str,bi);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<BufferedImage> generateMultiPreview(QRProperty qrProperty,ArrayList<String> contents,int start){
        ArrayList<BufferedImage> bis = null;
        try{
            bis = makeQRCode(qrProperty,contents);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        int len = bis.size();
        //len%4==0?len/4*120:len/4*120+120
        BufferedImage big = new BufferedImage(480,480,BufferedImage.TYPE_INT_RGB);
        for(int i = 0;i<Math.min(bis.size(),16);i++){
            paintRect(i%4*120,i/4*120,120,120,bis.get(i),big);
        }
        paintRect(start,start,480,480,big,bufferedImage);
        System.out.println("test: color changed to white x,y");
        for(int i = start;i<490;i++){
            for(int j =start;j<490;j++){
                if(((bufferedImage.getRGB(i,j)>>16)&0xff)==0 &&
                        ((bufferedImage.getRGB(i,j)>>8)&0xff)==0 &&
                        ((bufferedImage.getRGB(i,j))&0xff)==0){
                    bufferedImage.setRGB(i,j,0xffffff);
//                    System.out.println(i+" "+j);
                }
            }
        }
        repaint();
        return bis;
    }
    public void makeQRCode(QRProperty qrProperty,String content) throws WriterException{
        makeQRCode(qrProperty,content,bufferedImage);
    }
    public void makeQRCode(QRProperty qrProperty,String content,BufferedImage bi) throws WriterException {
        Map<EncodeHintType,Object> hint = new HashMap<>();
        hint.put(EncodeHintType.CHARACTER_SET,qrProperty.characterEncoding);
        QRCode code = Encoder.encode(content,qrProperty.errorCorrectionLevel,hint);
        ByteMatrix bm = code.getMatrix();
        int xRegion = bi.getWidth()/bm.getHeight();
        int gapAll = bi.getWidth()-xRegion*bm.getWidth();
        int gap = (gapAll)/2;
//        System.out.println("GapAll: "+gapAll+" gap: "+gap);
        if(gapAll%2==0){
            paintRect(0,0,bi.getWidth(),gap,0xffffff,bi);
            paintRect(0,0,gap,bi.getWidth(),0xffffff,bi);
            paintRect(bi.getWidth()-gap,0,gap,bi.getWidth(),0xffffff,bi);
            paintRect(0,bi.getWidth()-gap,bi.getWidth(),gap,0xffffff,bi);
        }else{
            paintRect(0,0,bi.getWidth(),gap,0xffffff,bi);
            paintRect(0,0,gap,bi.getWidth(),0xffffff,bi);
            paintRect(bi.getWidth()-gap-1,0,gap+1,bi.getWidth(),0xffffff,bi);
            paintRect(0,bi.getWidth()-gap-1,bi.getWidth(),gap+1,0xffffff,bi);
        }

        if(qrProperty.getSpecialType()==SpecialType.SignalColor){
            for(int y = 0;y<bm.getHeight();y++){
                for(int x = 0;x<bm.getWidth();x++){
                    if(bm.get(x,y)==1){
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,qrProperty.getColor().getRGB(),bi);
                    }else{
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,0xffffff,bi);
                    }
                }
            }
        }else if(qrProperty.getSpecialType()==SpecialType.MultiColor||qrProperty.getSpecialType()==SpecialType.RandomColor){
            int colorRand = 0;
            int size = qrProperty.getColors().size();
            System.out.println(size);
            for(int y = 0;y<bm.getHeight();y++){
                for(int x = 0;x<bm.getWidth();x++){
                    colorRand++;
                    if(bm.get(x,y)==1){
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,qrProperty.getColors().get(colorRand%size).getRGB(),bi);
                    }else{
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,0xffffff,bi);
                    }
                }
            }
        }else if(qrProperty.getSpecialType()==SpecialType.Image){
            for(int y = 0;y<bm.getHeight();y++){
                for(int x = 0;x<bm.getWidth();x++){
                    if(bm.get(x,y)==1){
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,qrProperty.getBufferedImage(),bi);
                    }else{
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,0xffffff,bi);
                    }
                }
            }
        } else{
            for(int y = 0;y<bm.getHeight();y++){
                for(int x = 0;x<bm.getWidth();x++){
                    if(bm.get(x,y)==1){
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,0x234567,bi);
                    }else{
                        paintRect(x*xRegion+gap,y*xRegion+gap,xRegion,xRegion,0xffffff,bi);
                    }
                }
            }
        }
        repaint();
    }
    public void paintRect(int x, int y, int width,int height, int color){
        paintRect(x,y,width,height,color,bufferedImage);
    }
    public static void paintRect(int x, int y, int width,int height, int color,BufferedImage bi){
//        System.out.println(x+" "+y+" "+lineSize+" "+color);
        for(int j = y;j<y+height;j++){
            for(int i = x;i<x+width;i++){
//                System.out.println(i+" "+j);
                bi.setRGB(i,j,color);
            }
        }
//        System.out.println(x);
    }
    public void paintRect(int x, int y, int width,int height, BufferedImage bi){
        paintRect(x,y,width,height,bi,bufferedImage);
    }
    public static void paintRect(int x, int y, int width,int height, BufferedImage obj,BufferedImage paintArea){
        try{
            paintArea.getGraphics().drawImage(BufferedImageZoom.getBufferedImageZoom(obj,width,height),x,y,null);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
}
