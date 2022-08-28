package org.qrmaster.ui.fragment.share;

import org.qrmaster.ui.activity.HomeActivity;
import org.qrmaster.ui.fragment.Fragment;
import org.qrmaster.ui.sync.WorkSyncer;

import javax.swing.*;

public class BackButton extends Fragment {
    JButton back = new JButton("BACK");
    public BackButton(){
        super();
        initComponents();
        setBounds(0,0,90,40);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        back.addActionListener(e -> {
            try {
                WorkSyncer.webView.setActivity(new HomeActivity());
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        back.setBounds(0,0,90,40);
        add(back);
    }
}
