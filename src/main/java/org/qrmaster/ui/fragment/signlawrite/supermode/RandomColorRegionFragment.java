package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.randomColor.RandomColor;

public class RandomColorRegionFragment  extends Fragment implements SpecialRegion{
    RandomColor randomColor = new RandomColor();
    public RandomColorRegionFragment(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(40,220,220,200);
        add(randomColor);

    }
}
