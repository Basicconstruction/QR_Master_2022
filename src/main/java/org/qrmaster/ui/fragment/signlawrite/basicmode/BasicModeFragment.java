package org.qrmaster.ui.fragment.signlawrite.basicmode;

import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.fragment.signlawrite.share.Generator;
import org.qrmaster.ui.fragment.signlawrite.share.Mode;
import org.qrmaster.ui.fragment.signlawrite.share.ShareHints;

import javax.swing.border.LineBorder;
import java.awt.*;

public class BasicModeFragment extends Fragment implements Mode {
    private final ShareHints shareHints = new ShareHints();
    private final Generator generator = new Generator();
    public BasicModeFragment(){
        super();
        setBounds(360,140,290,470);
        setBorder(new LineBorder(Color.BLACK,1));
        initComponents();
    }

    @Override
    public void initComponents() {
        add(shareHints);
        add(generator);

    }
    @Override
    public Generator getGenerator() {
        return generator;
    }

    @Override
    public ShareHints getShareHints() {
        return shareHints;
    }
}
