package org.qrmaster.ui.fragment.signalread;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class QRReader {
    public static String getContent(BufferedImage bufferedImage){
        MultiFormatReader reader = new MultiFormatReader();
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET,"UTF-8");
        Result result = null;
        try{
            result = reader.decode(binaryBitmap,hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result!=null?result.getText():"读取出现了错误";
    }
}
