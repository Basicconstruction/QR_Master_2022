package org.qrmaster.ui.activity;

import org.qrmaster.ui.fragment.share.BackButton;
import org.qrmaster.ui.fragment.signalread.SignalReadFragment;

public class SignalReadActivity extends Activity{
    SignalReadFragment fragment;
    BackButton backButton = new BackButton();

    public SignalReadActivity() throws NoSuchFieldException, IllegalAccessException {
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
    }

    @Override
    public void loadActivity() throws NoSuchFieldException, IllegalAccessException {
        super.loadActivity();
        fragment = new SignalReadFragment();
        add(fragment);
    }

    @Override
    public void exitActivity() {
        super.exitActivity();
    }

    @Override
    public void destroyActivity() {
        super.destroyActivity();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
