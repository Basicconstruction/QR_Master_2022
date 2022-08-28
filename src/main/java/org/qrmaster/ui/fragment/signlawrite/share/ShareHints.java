package org.qrmaster.ui.fragment.signlawrite.share;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;

public class ShareHints extends Fragment {
    public JComboBox<String> content;
    public JComboBox<String> barcodeSizeSelector;
    public JComboBox<String> errorCorrectionSelector;
    public JComboBox<String> characterEncodingSelector;
    public ShareHints(){
        super();
        setBounds(40,20,220,150);
        initComponents();
        setVisible(true);
    }

    @Override
    public void initComponents() {
        JLabel contentType = new JLabel("content",JLabel.CENTER);
        JLabel barcodeSize = new JLabel("barcode size",JLabel.CENTER);
        JLabel errorCorrection = new JLabel("error correction",JLabel.CENTER);
        JLabel characterEncoding = new JLabel("character encoding",JLabel.CENTER);
        contentType.setBounds(0,0,110,30);
        barcodeSize.setBounds(0,40,110,30);
        errorCorrection.setBounds(0,80,110,30);
        characterEncoding.setBounds(0,120,110,30);
        add(contentType);
        add(barcodeSize);
        add(errorCorrection);
        add(characterEncoding);

        String[] contents = new String[9];
        contents[0] = "Text"; contents[1] = "Contact information";
        contents[2] = "Email address";  contents[3] = "Geo location";
        contents[4] = "Phone number";   contents[5] = "SMS";
        contents[6] = "Calendar event";           contents[7] = "URL";
        contents[8] = "Wifi network";
        content = new JComboBox<>(contents);
        add(content);
        content.setBounds(110,0,110,30);

        String[] barcodeSizes = new String[3];
        barcodeSizes[0] = "Small"; barcodeSizes[1] = "Medium";
        barcodeSizes[2] = "Large";
        barcodeSizeSelector = new JComboBox<>(barcodeSizes);
        add(barcodeSizeSelector);
        barcodeSizeSelector.setBounds(110,40,110,30);

        String[] errorCorrections = new String[4];
        errorCorrections[0] = "L"; errorCorrections[1] = "M";
        errorCorrections[2] = "Q"; errorCorrections[3] = "H";
        errorCorrectionSelector = new JComboBox<>(errorCorrections);
        add(errorCorrectionSelector);
        errorCorrectionSelector.setBounds(110,80,110,30);

        String[] characterEncodings = new String[3];
        characterEncodings[0] = "Utf-8"; characterEncodings[1] = "ISO-8859-1";
        characterEncodings[2] = "Shift_JIS";
        characterEncodingSelector = new JComboBox<>(characterEncodings);
        add(characterEncodingSelector);
        characterEncodingSelector.setBounds(110,120,110,30);

//        contentType.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println(content.getSelectedIndex());
//            }
//        });
    }
}
