package org.qrmaster.ui.fragment.signlawrite.contentregion;

import org.qrmaster.ui.fragment.Fragment;

import javax.swing.border.LineBorder;
import java.awt.*;

public class ContentRegionFragment extends Fragment {
    Fragment regionFragment;
    public ContentRegionFragment(){
        super();
        setBounds(50,140,300,470);
        this.setBorder(new LineBorder(Color.BLACK,1));
        initComponents();
    }
    @Override
    public void initComponents(){
        regionFragment = new Content();
        regionFragment.setBounds(0,0,300,470);
        add(regionFragment);
    }
    public void setRegionFragment(Fragment regionFragment){
        remove(this.regionFragment);
        this.regionFragment = regionFragment;
        regionFragment.setBounds(0,0,300,470);
        add(regionFragment);
    }
    public ContentBehavior getContentPane(){
        return (ContentBehavior) regionFragment;
    }
}
