package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.supermode.multiColor.AddList;

public class MultiColorRegionFragment  extends Fragment implements SpecialRegion{
    private AddList addList;
    public MultiColorRegionFragment(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(40,220,220,200);
        addList = new AddList();
        add(addList);
    }

    public AddList getAddList() {
        return addList;
    }
}
