package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.signalImage.SignalImage;

public class SignalImageRegionFragment  extends Fragment implements SpecialRegion{
    SignalImage signalImage = new SignalImage();
    public SignalImageRegionFragment(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(40,220,220,200);
        add(signalImage);
    }
}
