package org.qrmaster.ui.workspace;


import org.qrmaster.ui.sync.WorkSyncer;

import javax.swing.*;

public class QRMasterWebPool extends JScrollPane {
    QRMasterWebView webView;
    public QRMasterWebPool(QRMasterWebView webView){
        super(webView);
        this.webView = webView;
    }
    public  WorkSpace getWorkSpace(){
        return WorkSyncer.workSpace;
    }
    public QRMasterWebView getWebView() {
        return webView;
    }
    /**
     * 源:JFrame的Size实际会小一些,如果子组件和其使用一个宽高,
     * 会导致部分元素被遮盖,目前为debug阶段,主要为动态设计,但又直接赋值
     * **/
    public void initWebPool(){
        WorkSpace workspace = getWorkSpace();
        int width = workspace.getWidth();
        int height = workspace.getHeight();
        this.setLocation(0,0);
//        this.setSize(width,height);
        this.setSize(1200,800);
    }

}
