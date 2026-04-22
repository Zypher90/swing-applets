/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stopwatch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

/**
 *
 * @author quicksilver69
 */
public class Frame extends JFrame{
    
    //GLOBAL VARIABLES
    private JPanel top;
    private JPanel bottom;
    private JLabel seconds;
    private JLabel minutes;
    private JLabel millisecs;
    private JLabel hours;
    private JButton start;
    private JButton pause;
    private JButton reset;
    private ImageIcon icon;
    private Timer timer;
    int sec = 0, min = 0, hr = 0, millisec = 0;
    boolean resetFlag = false;
    
    //PUBLIC CONSTRUCTOR
    public Frame(){
        initComponents();
        
    }

    //METHOD INITIALISES COMPONENTS
    private void initComponents() {
        
        //FRAME OPERATIONS
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        //SETTING ICON TO JFRAME
        icon = new ImageIcon("assets\\icon.png");
        setIconImage(icon.getImage());
        
        //INITIALISES TOP PANEL
        top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.setBackground(Color.BLACK);
        top.add(Box.createRigidArea(new Dimension(10,0)));
        top.setPreferredSize(new Dimension(400,150));
        top.setBorder(new EmptyBorder(new Insets(44,70,30,40))); //adds padding area in format (top,left,bottom,right)
        
        //INITIALISES BOTTOM PANEL
        bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.setBackground(Color.black);
        bottom.setPreferredSize(new Dimension(400, 100));
        bottom.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green, 3), 
                         new EmptyBorder(0,30,0,0))); //Adds padding of 30 pixels to left od BottomPanel
        
        //INITIALISES THE LABEL CONTAINING MILLISECOND COUNT
        millisecs = new JLabel("0" + millisec + "");
        millisecs.setForeground(Color.GREEN);
        millisecs.setOpaque(false);
        millisecs.setFont(new Font("Consolas", Font.PLAIN, 30));
        
        //INITIALISES LABEL CONTAINING SECOND COUNT
        seconds = new JLabel("0" + sec + ":");
        seconds.setForeground(Color.GREEN);
        seconds.setOpaque(false);
        seconds.setFont(new Font("Consolas", Font.PLAIN, 40));
        
        //INITIALISES LABEL CONTAINING MINUTE COUNT
        minutes = new JLabel("0" + min + ":");
        minutes.setForeground(Color.GREEN);
        minutes.setOpaque(false);
        minutes.setFont(new Font("Consolas", Font.PLAIN, 40));
        
        //INITIALISES LABEL CONTAINING HOUR COUNT
        hours = new JLabel("0" + hr + ":");
        hours.setForeground(Color.GREEN);
        hours.setOpaque(false);
        hours.setFont(new Font("Consolas", Font.PLAIN, 40));
        
        //ADDS THE LABELS TO TOP PANEL
        top.add(hours);
        top.add(minutes);
        top.add(seconds);       
        top.add(millisecs);
        
        //INITIALISES THE SWING TIMER THAT UPDATES THE STOPWATCH COUNT
        timer = new Timer(10, new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                millisec++;
                if(millisec == 100){
                    sec++;
                    millisec = 0;
                }
                if(sec == 60){
                    sec = 0;
                    min++;
                }
                if(min == 60){
                    hr++;
                    min = 0;
                }
                
                if(millisec < 10) {millisecs.setText("0"+millisec+"");} else {millisecs.setText(millisec+"");}
                if(sec < 10) {seconds.setText("0"+sec+":");} else {seconds.setText(sec + ":");}
                if(min < 10) {minutes.setText("0"+min+":");} else {minutes.setText(min + ":");}          
                if(hr < 10) {hours.setText("0"+hr+":");} else {hours.setText(hr + ":");}
            }
        });
        timer.setRepeats(true);
        
        //INITIALISES THE BUTTON THAT FLARES THE START COMMAND FOR STOPWATCH
        start = new JButton("Start");
        start.setMinimumSize(new Dimension(100, 100));
        start.setPreferredSize(new Dimension(110, 100));
        start.setBackground(Color.BLACK);
        start.setForeground(Color.green);
        start.setFont(new Font("Tahoma", Font.BOLD, 15));
        //start.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        start.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green, 2),
                                           new EmptyBorder(5,0,5,0))); //adds padding area in format (top,left,bottom,right)
        start.setFocusable(false);
        //ADDING ACTIONLISTENER TO BUTTON
        start.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(resetFlag == true){
                    timer.restart();
                    resetFlag = false;
                }
                else{
                    timer.start();
                }
            }
        });
        
        //INITIALISES THE BUTTON THAT FLARES THE PAUSE COMMAND FOR STOPWATCH
        pause = new JButton("Pause");
        pause.setMinimumSize(new Dimension(100, 100));
        pause.setPreferredSize(new Dimension(110, 100));
        pause.setBackground(Color.BLACK);
        pause.setForeground(Color.green);
        pause.setFont(new Font("Tahoma", Font.BOLD, 15));
        //pause.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        pause.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green, 2),
                                           new EmptyBorder(5,0,5,0))); //adds padding area in format (top,left,bottom,right)
        pause.setFocusable(false);
        //ADDING ACTIONLISTENER TO BUTTON
        pause.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        
        //INITIALISES THE BUTTON THAT FLARES THE RESET COMMAND FOR STOPWATCH
        reset = new JButton("Reset");
        reset.setMinimumSize(new Dimension(100, 100));
        reset.setPreferredSize(new Dimension(110, 100));
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.green);
        reset.setFont(new Font("Tahoma", Font.BOLD, 15));
        //reset.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        reset.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green, 2),
                                           new EmptyBorder(5,0,5,0))); //adds padding area in format (top,left,bottom,right)
        reset.setFocusable(false);
        //ADDING ACTIONLISTENER TO BUTTON
        reset.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                resetFlag = true;
                millisec = 0;
                sec = 0;
                min = 0;
                hr = 0;
                millisecs.setText("0"+millisec+"");
                seconds.setText("0"+sec+":");
                minutes.setText("0"+min+":");
                hours.setText("0"+hr+":");
            }
        });
        
        //ADDING ALL THESE BUTTONS TO BOTTOM PANEL WHILE ADDING RIGID AREAS TO FORMAT COMPONENTS
        bottom.add(start);
        bottom.add(Box.createRigidArea(new Dimension(20, 0)));
        bottom.add(pause);
        bottom.add(Box.createRigidArea(new Dimension(20, 0)));
        bottom.add(reset);
        bottom.add(Box.createRigidArea(new Dimension(20, 0)));
        
        //ADDS THE PANEL TO THE FRAME AND DOES OTHER OPERATIONS
        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setVisible(true);
    }
    
}
