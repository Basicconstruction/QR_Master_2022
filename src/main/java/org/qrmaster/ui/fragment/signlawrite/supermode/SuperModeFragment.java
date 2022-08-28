package org.qrmaster.ui.fragment.signlawrite.supermode;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.SpecialType;
import org.qrmaster.ui.fragment.signlawrite.share.Generator;
import org.qrmaster.ui.fragment.signlawrite.share.Mode;
import org.qrmaster.ui.fragment.signlawrite.share.ShareHints;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SuperModeFragment extends Fragment implements Mode {
    private final Generator generator = new Generator();
    private final ShareHints shareHints = new ShareHints();
    private SpecialRegion specialRegion;
    public SuperModeFragment(){
        super();
        setBounds(360,140,290,470);
        setBorder(new LineBorder(Color.BLACK));
        initComponents();
    }
    @Override
    public void initComponents(){
        add(generator);
        add(shareHints);
        JLabel type = new JLabel("type",JLabel.CENTER);
//        type.setOpaque(true);
//        type.setBackground(Color.PINK);
        type.setBounds(30,180,110,30);
//        type.setBorder(new LineBorder(Color.BLACK));
        add(type);
        SpecialType[] specialTypes = new SpecialType[4];
        specialTypes[0] = SpecialType.SignalColor;
        specialTypes[1] = SpecialType.MultiColor;
        specialTypes[2] = SpecialType.RandomColor;
        specialTypes[3] = SpecialType.Image;
//        specialTypes[4] = SpecialType.MultiImage;
        JComboBox<SpecialType> specialTypeSelector = new JComboBox<>(specialTypes);
        specialTypeSelector.setBounds(150,180,110,30);
        add(specialTypeSelector);

        specialRegion = new SignalColorRegionFragment();
        add((Fragment) specialRegion);
        UiInitializer.qrProperty.setSpecialType(SpecialType.SignalColor);

        specialTypeSelector.addItemListener(e -> {
            if(e.getStateChange()==ItemEvent.SELECTED){
                SpecialType specialType = (SpecialType) e.getItem();
                UiInitializer.qrProperty.setSpecialType(specialType);
                System.out.println("select: "+specialType);
                System.out.println("switch to: "+specialType+" Fragment...");
                switch(specialType){
                    case SignalColor:
                        remove((Fragment) specialRegion);
                        specialRegion = new SignalColorRegionFragment();
                        add((Fragment)specialRegion);
                        break;
                    case MultiColor:
                        remove((Fragment) specialRegion);
                        specialRegion = new MultiColorRegionFragment();
                        add((Fragment)specialRegion);
                        break;
                    case RandomColor:
                        remove((Fragment) specialRegion);
                        specialRegion = new RandomColorRegionFragment();
                        add((Fragment)specialRegion);
                        break;
                    case Image:
                        remove((Fragment) specialRegion);
                        specialRegion = new SignalImageRegionFragment();
                        add((Fragment)specialRegion);
                        break;
                    case MultiImage:
                        remove((Fragment) specialRegion);
                        specialRegion = new MultiImageRegionFragment();
                        add((Fragment)specialRegion);
                        break;
                    default:
                        break;
                }
                ((Fragment)specialRegion).repaint();
            }
        });

    }
    @Override
    public Generator getGenerator() {
        return generator;
    }

    @Override
    public ShareHints getShareHints() {
        return shareHints;
    }
    public Fragment getFragment(){
        return (Fragment) specialRegion;
    }
    public MultiColorRegionFragment getMultiColorRegionFragment(){
        if(specialRegion instanceof MultiColorRegionFragment){
            return (MultiColorRegionFragment) specialRegion;
        }else{
            return null;
        }
    }
}
