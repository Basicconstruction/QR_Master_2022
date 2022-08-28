package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.ui.fragment.Fragment;

public class MultiImageRegionFragment  extends Fragment implements SpecialRegion{
    public MultiImageRegionFragment(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(40,220,220,200);
    }
}
