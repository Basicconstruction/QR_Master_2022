package org.qrmaster.ui.activity;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.QRContentType;
import org.qrmaster.ui.fragment.signlawrite.QRMaker;
import org.qrmaster.ui.fragment.signlawrite.QRProperty;
import org.qrmaster.ui.fragment.signlawrite.basicmode.BasicModeFragment;
import org.qrmaster.ui.fragment.signlawrite.contentregion.ContentRegionFragment;
import org.qrmaster.ui.fragment.signlawrite.share.Mode;
import org.qrmaster.ui.fragment.signlawrite.supermode.SuperModeFragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignalWriteActivity extends Activity{
    QRProperty qrProperty;
    Fragment mode,basicModeFragment,superModeFragment;
    ContentRegionFragment content;
    QRMaker qrMaker;
    public SignalWriteActivity() throws NoSuchFieldException, IllegalAccessException {
        super();
        initialize();
        loadActivity();
        repaint();
    }

    @Override
    public void initialize() {
        super.initialize();
        setSize(1200,800);
//        setBackground(Color.BLACK);
    }

    @Override
    public void loadActivity() throws NoSuchFieldException, IllegalAccessException {
        super.loadActivity();
        content =new ContentRegionFragment();
        qrMaker = new QRMaker();
        add(content);
        add(qrMaker);
        loadModeComponents();
    }

    public void addModeListener(){
        ((Mode)mode).getGenerator().addActionListener(e -> {
            qrProperty = new QRProperty();
            switch (((Mode) mode).getShareHints().content.getSelectedIndex()){
                case 0:
                    qrProperty.qrContentType = QRContentType.CalendarEvent;
                    break;
                case 1:
                    qrProperty.qrContentType = QRContentType.ContactInformation;
                    break;
                case 2:
                    qrProperty.qrContentType = QRContentType.EmailAddress;
                    break;
                case 3:
                    qrProperty.qrContentType = QRContentType.GeoLocation;
                    break;
                case 4:
                    qrProperty.qrContentType = QRContentType.PhoneNumber;
                    break;
                case 5:
                    qrProperty.qrContentType = QRContentType.SMS;
                    break;
                case 6:
                    qrProperty.qrContentType = QRContentType.Text;
                    break;
                case 7:
                    qrProperty.qrContentType = QRContentType.URL;
                    break;
                case 8:
                    qrProperty.qrContentType = QRContentType.WifiNetwork;
                    break;
            }
            switch (((Mode) mode).getShareHints().barcodeSizeSelector.getSelectedIndex()){
                case 0:
                    qrProperty.barcodeSize = "Small";
                    break;
                case 1:
                    qrProperty.barcodeSize = "Medium";
                    break;
                case 2:
                    qrProperty.barcodeSize = "Large";
                    break;

            }
            switch(((Mode) mode).getShareHints().errorCorrectionSelector.getSelectedIndex()){
                case 0:
                    qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.L;
                    break;
                case 1:
                    qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.M;
                    break;
                case 2:
                    qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.Q;
                    break;
                case 3:
                    qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.H;
                    break;

            }
            switch (((Mode) mode).getShareHints().characterEncodingSelector.getSelectedIndex()){
                case 0:
                    qrProperty.characterEncoding = "Utf-8";
                    break;
                case 1:
                    qrProperty.characterEncoding = "ISO-8859-1";
                    break;
                case 2:
                    qrProperty.characterEncoding = "Shift_JIS";
                    break;
            }
            System.out.println(qrProperty);
            if(mode instanceof BasicModeFragment){
                System.out.println("BasicModeFragment");
                try {
                    qrMaker.makeQRCode(qrProperty,content.getContentPane().getContent());
                } catch (WriterException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                System.out.println("SuperModeFragment");
                try {
                    qrMaker.makeQRCode(qrProperty,content.getContentPane().getContent());
                } catch (WriterException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
    }
    public void loadModeComponents() throws NoSuchFieldException, IllegalAccessException {
        basicModeFragment = new BasicModeFragment();
        superModeFragment = new SuperModeFragment();
        mode = basicModeFragment;
        add(mode);
        addModeListener();

//        remove(mode);
//        mode = superModeFragment;
//        add(mode);
        JLabel basicMode = new JLabel("基本模式",JLabel.CENTER);
        JLabel superMode = new JLabel("高级模式",JLabel.CENTER);
        add(basicMode);
        add(superMode);
        basicMode.setOpaque(true);
        superMode.setOpaque(true);
        basicMode.setBounds(50,50,300,40);
        superMode.setBounds(350,50,300,40);
        basicMode.setBorder(new LineBorder(Color.BLACK));
        superMode.setBorder(new LineBorder(Color.BLACK));
        basicMode.setBackground(Color.gray);
        basicMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!(mode instanceof  BasicModeFragment)){
                    basicMode.setBackground(Color.gray);
                    superMode.setBackground(Color.WHITE);
                    remove(mode);
                    mode = basicModeFragment;
                    add(mode);
                    addModeListener();
                    System.out.println("switch to normal mode...");
                }
            }
        });
        superMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!(mode instanceof SuperModeFragment)){
                    superMode.setBackground(Color.GRAY);
                    basicMode.setBackground(Color.white);
                    remove(mode);
                    mode = superModeFragment;
                    add(mode);
                    addModeListener();
                    System.out.println("switch to super mode...");
                }
            }
        });
//        ((Mode)mode).getShareHints().barcodeSizeSelector.grabFocus();
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
