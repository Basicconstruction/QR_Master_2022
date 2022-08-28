package org.qrmaster.ui.fragment.signlawrite.supermode.multiColor;

import org.qrmaster.boot.UiInitializer;
import org.qrmaster.ui.fragment.Fragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddList extends Fragment {
    private int elementSize = 0;
    private JScrollPane jScrollPane;
    private Fragment inner;
    private JLabel addButton = new JLabel("+", JLabel.CENTER);
    private ArrayList<Color> collectType = new ArrayList<>();
    private ArrayList<AddListItem> addListItems = new ArrayList<>();
    public AddList(){
        super();
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBounds(0,0,220,200);
        addButton.setFont(new Font("微软雅黑",Font.PLAIN,30));
        addButton.setBounds(160,16,40,40);
        add(addButton);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("尝试添加AddListItem 此前已有 "+addListItems.size());
                addElement();
            }
        });
        inner = new Fragment();
        jScrollPane = new JScrollPane(inner);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(24,60,200,140);
        add(jScrollPane);
    }
    public void delete(AddListItem addListItem){
        addListItems.remove(addListItem);
        elementSize = addListItems.size();
        innerRepaint();
        jScrollPane.repaint();
        SyncColors();
    }
    /**
     * 更精确的时候是在generate时更新颜色
     * **/
    public void SyncColors(){
        UiInitializer.qrProperty.setColors(getAllColor());
    }
    public ArrayList<Color> getAllColor(){
        collectType.clear();
        for (AddListItem addListItem : addListItems) {
            collectType.add(addListItem.getColor());
        }
        System.out.println("Items Size: "+addListItems.size());
        System.out.println("Color size: "+collectType.size());
        return collectType;
    }
    public void addElement(){
        AddListItem newAddListItem = new AddListItem(this,176,50,new RgbAddListFragment());
        newAddListItem.setLocation(0,50*elementSize);
        addListItems.add(newAddListItem);
        innerRepaint();
        jScrollPane.repaint();
        SyncColors();
    }
    public void innerRepaint(){
        inner.removeAll();
        elementSize = addListItems.size();
        inner.setSize(new Dimension(200, Math.max(50 * elementSize, 140)));
        inner.setPreferredSize(new Dimension(200, Math.max(50 * elementSize, 140)));
        for(int i = 0;i<addListItems.size();i++){
            AddListItem addListItem = addListItems.get(i);
            addListItem.setLocation(0,50*i);
            inner.add(addListItem);
        }
        inner.repaint();
        syncFlowSpeed();
    }
    public void syncFlowSpeed(){
        jScrollPane.getVerticalScrollBar().setUnitIncrement((int) (inner.getHeight()*0.1));
    }
}
