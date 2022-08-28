package org.qrmaster.ui.sync;


import org.qrmaster.ui.workspace.QRMasterWebPool;
import org.qrmaster.ui.workspace.QRMasterWebView;
import org.qrmaster.ui.workspace.WorkSpace;

public class WorkSyncer {
    public static WorkSpace workSpace;
    public static QRMasterWebPool webPool;
    public static QRMasterWebView webView;



    public WorkSyncer() throws NoSuchFieldException, IllegalAccessException {
        initialize();
    }
    public void initialize() throws NoSuchFieldException, IllegalAccessException {
        webView = new QRMasterWebView();
        webPool = new QRMasterWebPool(webView);
        workSpace = new WorkSpace(webPool);
        webPool.initWebPool();
        webView.initWebView();
        webView.syncActivitySize();

    }
}
