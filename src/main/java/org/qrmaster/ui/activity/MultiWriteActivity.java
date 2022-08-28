package org.qrmaster.ui.activity;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.batchwrite.MultiWriteFragment;
import org.qrmaster.ui.fragment.share.BackButton;
import org.qrmaster.ui.fragment.signlawrite.QRContentType;
import org.qrmaster.ui.fragment.signlawrite.QRMaker;
import org.qrmaster.ui.fragment.signlawrite.SpecialType;
import org.qrmaster.ui.fragment.signlawrite.basicmode.BasicModeFragment;
import org.qrmaster.ui.fragment.signlawrite.share.Mode;
import org.qrmaster.ui.fragment.signlawrite.supermode.MultiColorRegionFragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.SuperModeFragment;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static org.qrmaster.boot.UiInitializer.qrProperty;

public class MultiWriteActivity extends Activity{
    Fragment mode;
    QRMaker qrMaker;
    MultiWriteFragment multiWriteFragment;
    BackButton backButton = new BackButton();
    public MultiWriteActivity() throws NoSuchFieldException, IllegalAccessException {
        super();
        initialize();
        loadActivity();
        repaint();
    }

    @Override
    public void initialize() {
        super.initialize();
        setSize(1200,800);
        add(backButton);
    }

    @Override
    public void loadActivity() throws NoSuchFieldException, IllegalAccessException {
        super.loadActivity();
        multiWriteFragment = new MultiWriteFragment();
        qrMaker = new QRMaker();
        add(multiWriteFragment);
        add(qrMaker);
        loadModeComponents();
    }
    public void addModeListener(){
        ((Mode)mode).getGenerator().addActionListener(e -> {
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
                qrProperty.setSpecialType(SpecialType.Normal);
                qrMaker.generateMultiPreview(qrProperty,multiWriteFragment.getListText(),10);
            }else{
                System.out.println("SuperModeFragment");
                if(((SuperModeFragment)mode).getFragment() instanceof MultiColorRegionFragment){
                    ((SuperModeFragment)mode).getMultiColorRegionFragment().getAddList().SyncColors();
                }
                qrMaker.generateMultiPreview(qrProperty,multiWriteFragment.getListText(),10);
            }
            mode.repaint();

        });
    }
    public void loadModeComponents() throws NoSuchFieldException, IllegalAccessException {
        mode = new BasicModeFragment();
        add(mode);
        addModeListener();

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
                    mode = new BasicModeFragment();
                    add(mode);
                    addModeListener();
                    System.out.println("switch to normal mode...");
                    mode.repaint();
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
                    mode = new SuperModeFragment();
                    add(mode);
                    addModeListener();
                    System.out.println("switch to super mode...");
                    mode.repaint();
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
