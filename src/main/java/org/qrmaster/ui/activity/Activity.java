package org.qrmaster.ui.activity;


import javax.swing.*;
import java.awt.*;

public class Activity extends JPanel {
    private Status status = Status.preInit;
    public Activity() throws NoSuchFieldException, IllegalAccessException {
        super();
        setLayout(null);
        setBackground(Color.white);
    }
    public void initialize(){

    }
    public void loadActivity() throws NoSuchFieldException, IllegalAccessException {
        if(status!=Status.preInit){
            return;
        };
    }
    public void exitActivity(){

    }
    public void destroyActivity(){

    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

    }

    @Override
    public String toString() {
        return "Activity{" +
                "status=" + status +
                '}';
    }


}
