package com.zeddragon.imageviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ZoomWindow extends JFrame implements ChangeListener{
    //GLOBALS
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JSlider zoomSlider;
    private JScrollPane mainPane;
    private JLabel imageLabel;
    private int xsize_Panel = 400, ysize_Panel = 400;
    private ImageIcon image;
    public ZoomWindow(ImageIcon image) {
        setTitle(image.getDescription());
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.image = image;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setIconImage(new ImageIcon("assets/icon.png").getImage());
        
        zoomSlider = new JSlider(JSlider.HORIZONTAL, 0, 75, 0);
        zoomSlider.setPaintLabels(false);
        zoomSlider.setPaintTicks(false);
        zoomSlider.setPaintTrack(true);
        zoomSlider.setPreferredSize(new Dimension(150, 25));
        zoomSlider.addChangeListener(this);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3),
                                                 BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        bottomPanel.setPreferredSize(new Dimension(250, 45));
                
        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400, 400));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(imageLabel);
        //topPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green, 3),
        //                                        BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
        mainPane =  new JScrollPane(topPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        addComponents();
    }
     private void addComponents() {
        bottomPanel.add(zoomSlider);
        add(mainPane, BorderLayout.PAGE_START);
        add(bottomPanel, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setVisible(true);   
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == zoomSlider){
            int nh = ((4 * image.getIconHeight() * (zoomSlider.getValue() + 25)) / 100);
            int nw = ((4 * image.getIconWidth() * (zoomSlider.getValue() + 25)) / 100);
            topPanel.setPreferredSize(new Dimension(nw + 400, nh + 400));
            imageLabel.setPreferredSize(new Dimension(nw, nh));
            ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(nw ,nh ,Image.SCALE_SMOOTH));
            imageLabel.setIcon(img);            
            topPanel.add(imageLabel);
            mainPane.revalidate();
//            mainPane.setViewportView(topPanel);
//            add(mainPane, BorderLayout.PAGE_START);
//            pack();
        }
    }   
    
}
