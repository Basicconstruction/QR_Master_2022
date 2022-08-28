package org.qrmaster.ui.activity;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.qrmaster.datatackle.bufferedImage.zoom.BufferedImageZoom;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.share.BackButton;
import org.qrmaster.ui.fragment.signalread.QRReader;
import org.qrmaster.ui.fragment.signlawrite.QRContentType;
import org.qrmaster.ui.fragment.signlawrite.QRMaker;
import org.qrmaster.ui.fragment.signlawrite.SpecialType;
import org.qrmaster.ui.fragment.signlawrite.basicmode.BasicModeFragment;
import org.qrmaster.ui.fragment.signlawrite.contentregion.ContentRegionFragment;
import org.qrmaster.ui.fragment.signlawrite.share.Mode;
import org.qrmaster.ui.fragment.signlawrite.supermode.MultiColorRegionFragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.SuperModeFragment;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static org.qrmaster.boot.UiInitializer.qrProperty;

public class SignalWriteActivity extends Activity{

    Fragment mode;
    ContentRegionFragment content;
    QRMaker qrMaker;
    BackButton backButton = new BackButton();
    JButton save = new JButton("save");
    JButton validate = new JButton("validate");
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
        add(backButton);
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
        save.setBounds(670,680,90,40);
        validate.setBounds(770,680,100,40);
        add(save);
        add(validate);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setTitle("保存QR code");
                dialog.setSize(500+16,310+39);
                dialog.setLayout(null);
                Dimension screenSize = getToolkit().getScreenSize();
                dialog.setLocation((int) (screenSize.getWidth()/2-dialog.getWidth()/2), (int) (screenSize.getHeight()/2-dialog.getHeight()/2));
                JLabel size = new JLabel("保存大小",JLabel.CENTER);
                JLabel format = new JLabel("保存格式",JLabel.CENTER);
                JLabel path = new JLabel(("保存路径"),JLabel.CENTER);
                JButton ok = new JButton("确认");
                JTextField width,height,fileName;
                JButton pathField;
                width = new JTextField();
                height = new JTextField();
                pathField = new JButton("选取");
                fileName = new JTextField();
                width.setText("800");
                height.setText("800");
                fileName.setText("qr"+new Random().nextInt(1000)+".png");
                String[] qrType = new String[3];;
                qrType[0] = "png";
                qrType[1] = "jpg";
                qrType[2] = "jpeg";
                JComboBox<String> formatField = new JComboBox<>(qrType);
                size.setBounds(90,40,110,50);
                width.setBounds(220,40,80,50);
                height.setBounds(330,40,80,50);
                format.setBounds(90,110,110,50);
                formatField.setBounds(220,110,80,50);
                path.setBounds(90,180,110,50);
                pathField.setBounds(220,180,80,50);
                fileName.setBounds(90,240,320,40);
                ok.setBounds(420,240,60,40);
                dialog.add(size); dialog.add(width); dialog.add(height);
                dialog.add(format); dialog.add(formatField); dialog.add(path);
                dialog.add(pathField); dialog.add(fileName); dialog.add(ok);
                formatField.addItemListener(e1 -> {
                    if (e1.getStateChange()==ItemEvent.SELECTED){
                        String imgType = (String) e1.getItem();
                        String s = fileName.getText();
                        s = s.substring(0,s.lastIndexOf("."))+"."+imgType;
                        fileName.setText(s);
                    }
                });
                pathField.addActionListener(e12 -> {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = chooser.showOpenDialog(null);
                    if(result==JFileChooser.APPROVE_OPTION){
                        pathField.setText(chooser.getSelectedFile().getAbsolutePath());
                    }
                });
                ok.addActionListener(e13 -> {
                    BufferedImage bi = qrMaker.getBufferedImage();
                    int saveWidth = Integer.parseInt(width.getText());
                    int saveHeight = Integer.parseInt(height.getText());
                    int qrSize = Math.min(saveWidth,saveHeight)/11*10;
                    int left = (saveWidth-qrSize)/2;
                    int right = saveWidth - qrSize-left;
                    int top = (saveHeight-qrSize)/2;
                    int bottom = saveHeight - qrSize - top;
                    BufferedImage good = new BufferedImage(saveWidth,saveHeight,BufferedImage.TYPE_INT_RGB);
                    QRMaker.paintRect(0,0,saveWidth,top,Color.WHITE.getRGB(),good);
                    QRMaker.paintRect(0,0,left,saveHeight,Color.WHITE.getRGB(),good);
                    QRMaker.paintRect(0,saveHeight-bottom,saveWidth,bottom,Color.WHITE.getRGB(),good);
                    QRMaker.paintRect(saveWidth-right,0,right,saveHeight,Color.WHITE.getRGB(),good);
                    try {
                        QRMaker.paintRect(left,top,qrSize,qrSize, BufferedImageZoom.getBufferedImageZoom(bi,qrSize,qrSize),good);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try{
                        ImageIO.write(good,((String) Objects.requireNonNull(formatField.getSelectedItem())),new File(pathField.getText()+"\\\\"+fileName.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dialog.dispose();
                });
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        validate.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setSize(500+16,310+39);
            dialog.setLayout(null);
            Dimension screenSize = getToolkit().getScreenSize();
            dialog.setLocation((int) (screenSize.getWidth()/2-dialog.getWidth()/2), (int) (screenSize.getHeight()/2-dialog.getHeight()/2));
            JLabel tip = new JLabel("扫描内容是",JLabel.CENTER);
            tip.setBounds(130,35,240,50);
            dialog.add(tip);
            JTextPane textPane = new JTextPane();
            textPane.setBounds(20,95,460,210);
            textPane.setBorder(new LineBorder(Color.black));
            dialog.add(textPane);
            textPane.setText(QRReader.getContent(qrMaker.getBufferedImage()));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }

    public void addModeListener(){
        ((Mode)mode).getGenerator().addActionListener(e -> {
            switch (((Mode) mode).getShareHints().content.getSelectedIndex()) {
                case 0 -> qrProperty.qrContentType = QRContentType.CalendarEvent;
                case 1 -> qrProperty.qrContentType = QRContentType.ContactInformation;
                case 2 -> qrProperty.qrContentType = QRContentType.EmailAddress;
                case 3 -> qrProperty.qrContentType = QRContentType.GeoLocation;
                case 4 -> qrProperty.qrContentType = QRContentType.PhoneNumber;
                case 5 -> qrProperty.qrContentType = QRContentType.SMS;
                case 6 -> qrProperty.qrContentType = QRContentType.Text;
                case 7 -> qrProperty.qrContentType = QRContentType.URL;
                case 8 -> qrProperty.qrContentType = QRContentType.WifiNetwork;
            }
            switch (((Mode) mode).getShareHints().barcodeSizeSelector.getSelectedIndex()) {
                case 0 -> qrProperty.barcodeSize = "Small";
                case 1 -> qrProperty.barcodeSize = "Medium";
                case 2 -> qrProperty.barcodeSize = "Large";
            }
            switch (((Mode) mode).getShareHints().errorCorrectionSelector.getSelectedIndex()) {
                case 0 -> qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.L;
                case 1 -> qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.M;
                case 2 -> qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.Q;
                case 3 -> qrProperty.errorCorrectionLevel = ErrorCorrectionLevel.H;
            }
            switch (((Mode) mode).getShareHints().characterEncodingSelector.getSelectedIndex()) {
                case 0 -> qrProperty.characterEncoding = "Utf-8";
                case 1 -> qrProperty.characterEncoding = "ISO-8859-1";
                case 2 -> qrProperty.characterEncoding = "Shift_JIS";
            }
            System.out.println(qrProperty);
            if(mode instanceof BasicModeFragment){
                System.out.println("BasicModeFragment");
                try {
                    qrProperty.setSpecialType(SpecialType.Normal);
                    qrMaker.makeQRCode(qrProperty,content.getContentPane().getContent());
                } catch (WriterException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                System.out.println("SuperModeFragment");
                if(((SuperModeFragment)mode).getFragment() instanceof MultiColorRegionFragment){
                    ((SuperModeFragment)mode).getMultiColorRegionFragment().getAddList().SyncColors();
                }
                try {
                    qrMaker.makeQRCode(qrProperty,content.getContentPane().getContent());
                } catch (WriterException ex) {
                    throw new RuntimeException(ex);
                }
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
