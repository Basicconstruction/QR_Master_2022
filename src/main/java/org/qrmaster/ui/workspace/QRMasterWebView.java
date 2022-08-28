package org.qrmaster.ui.workspace;

import org.qrmaster.ui.activity.Activity;
import org.qrmaster.ui.activity.ActivityStack;
import org.qrmaster.ui.activity.HomeActivity;
import org.qrmaster.ui.sync.WorkSyncer;

import javax.swing.*;
import java.awt.*;

public class QRMasterWebView extends JPanel {
    private ActivityStack activityStack;
    private Activity activity;
    private String path;
    public QRMasterWebView() throws NoSuchFieldException, IllegalAccessException {
        super();
        setLayout(null);
//        setBackground(Color.WHITE);被activity覆盖,背景无用
        activityStack = new ActivityStack();
        setActivity(new HomeActivity());
    }
    public void setActivity(Activity newActivity){
        if(activity == null){
            activity = newActivity;
            // 变更activity
            this.add(activity);
        }else{
            this.remove(activity);
            // 变更activity
            activity = newActivity;
            this.add(activity);
        }
        activityStack.clearActivityStack();
        activityStack.pushActivity(activity);
        syncActivitySize();
    }
    public Activity pushActivity(Activity activity){
        activityStack.pushActivity(activity);
        this.remove(this.activity);
        this.activity = activity;
        this.add(this.activity);
        syncActivitySize();
        return this.activity;
    }
    public Activity peekActivity(){
        return activityStack.peekActivity();
    }
    public Activity getCurrentActivity(){
        return activityStack.peekActivity();
    }
    public Activity popActivity(){
//        printAllActivity();
        Activity activity = activityStack.popActivity();
        this.remove(this.activity);
        this.activity = activityStack.peekActivity();
        this.add(this.activity);
        syncActivitySize();
        this.repaint();
        return activity;
    }
    public void printAllActivity(){
        System.out.println(activityStack);
    }
    public void syncActivitySize(){
        int width = this.getWidth();
        int height = this.getHeight();
        this.activity.setSize(width, height);
//        System.out.println(width+" "+height);
    }
    public void updatePreferredSize(int width, int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setSize(width,height);
        syncActivitySize();
    }
    public WorkSpace getWorkSpace(){
        return WorkSyncer.workSpace;
    }
    /**
     * 源:JFrame的Size实际会小一些,如果子组件和其使用一个宽高,
     * 会导致部分元素被遮盖,目前为debug阶段,主要为动态设计,但又直接赋值
     * **/
    public void initWebView() {
        WorkSpace workspace = getWorkSpace();
        int width = workspace.getWidth();
        int height = workspace.getHeight();
        this.setLocation(0,0);
//        this.setSize(width,height);
        this.setSize(1200,800);
        System.out.println(width+" "+height);
    }

    public String getPath() {
        return path;
    }
}
