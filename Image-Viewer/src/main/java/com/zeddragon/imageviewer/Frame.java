package com.zeddragon.imageviewer;

import com.sun.tools.javac.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Frame extends JFrame implements ActionListener{
    //GLOBALS
    private boolean imageFileSelectedFlag = false;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private JButton nextButton;
    private JButton previousButton;
    private JButton zoomButton;
    private JFileChooser fileChooser; 
    private ImageIcon image;
    private ImageIcon zoomIcon;
    private JMenuBar topMenuBar;
    private JMenu file;
    private JMenu options;
    private JMenu help;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem info;
    private JMenuItem loadDirectory;
    private JMenuItem loadIndividualImageFile;
    private File chosenFile = null;
    private FileFilter imageFileExtensionFilter;
    private String fileName = new File("assets/icon.png").getName();
    private int currentFileIndex;
    private int numberOfFiles;
    private File imageFilesInDirectory[];
    private final ImageIcon bruhChoiceIcon = new ImageIcon("assets/stoopidChoice.jpg");
    
    public Frame(){
        setTitle("Image Viewer Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/icon.png").getImage());
        setResizable(false);        
        initComponents();
    }

    private void initComponents() {
        
        topMenuBar = new JMenuBar();
        file = new JMenu("File");
        options = new JMenu("Options");
        help = new JMenu("Help");
        
        save = new JMenuItem("Save", new ImageIcon(new ImageIcon("assets/saveFileIcon.png").getImage().getScaledInstance(20, 20, Image.SCALE_FAST)));
        save.addActionListener(this);
        exit = new JMenuItem("Exit", new ImageIcon(new ImageIcon("assets/exitApplicationIcon.png").getImage().getScaledInstance(20, 20, Image.SCALE_FAST)));
        exit.addActionListener(this);
        loadDirectory = new JMenuItem("Load image Directory");
        loadDirectory.addActionListener(this);
        loadIndividualImageFile = new JMenuItem("Load Image");
        loadIndividualImageFile.addActionListener(this);
        info = new JMenuItem("Info", new ImageIcon(new ImageIcon("assets/infoIcon.png").getImage().getScaledInstance(20, 20, Image.SCALE_FAST)));
        info.addActionListener(this);
        
        file.add(save);
        file.add(exit);
        options.add(loadDirectory);
        options.add(loadIndividualImageFile);
        help.add(info);
        topMenuBar.add(file);
        topMenuBar.add(options);
        topMenuBar.add(help);
        
        zoomIcon = new ImageIcon(new ImageIcon("assets/zoomicon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        image = new ImageIcon("assets/icon.png");
        
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(500, 500));
        
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        imageLabel.setIcon(image);
        imageLabel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black, 2),
                                              fileName));
        
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 150));
        buttonPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 3),
                                                 BorderFactory.createEmptyBorder(25, 100, 25, 100)));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        nextButton = new JButton(">");
        nextButton.setPreferredSize(new Dimension(100, 100));
//        nextButton.setFont(new Font("Consolas", 20, Font.PLAIN));
        nextButton.setFocusable(false);
        nextButton.setBackground(Color.RED);
        nextButton.setFont(new Font("Consolas", Font.PLAIN, 40));
        nextButton.setForeground(new Color(120,190,33));
        nextButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        nextButton.addActionListener(this);
        
        zoomButton = new JButton();
        zoomButton.setPreferredSize(new Dimension(90, 100));
        zoomButton.setIcon(zoomIcon);
        zoomButton.setFocusable(false);
        zoomButton.setBackground(new Color(120,190,33));
        zoomButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        zoomButton.addActionListener(this);
        
        previousButton = new JButton("<");
        previousButton.setPreferredSize(new Dimension(100, 100));
//        previousButton.setFont(new Font("Fira Code", 20, Font.PLAIN));
        previousButton.setBackground(Color.RED);
        previousButton.setForeground(new Color(120,190,33));
        previousButton.setFont(new Font("Consolas", Font.PLAIN, 40));
        previousButton.setFocusable(false);
        previousButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        previousButton.addActionListener(this);
        
        fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        imageFileExtensionFilter = new FileFilter() {
              public boolean accept(File file) {
                if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
                  return true;
                }
                return false;
            }
        };
        
        addComponents();
    }
    
    private void addComponents(){
        add(topMenuBar, BorderLayout.NORTH);
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.CENTER);
        buttonPanel.add(previousButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPanel.add(zoomButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton){
            if(imageFileSelectedFlag == true){
                JOptionPane.showMessageDialog(null, 
                        "There are no previous images", 
                        "No previous images", 
                        JOptionPane.OK_OPTION, 
                        bruhChoiceIcon);
            }
            else{
                if(currentFileIndex < (numberOfFiles - 1)){
                    image = new ImageIcon(imageFilesInDirectory[++currentFileIndex].getAbsolutePath());
                    imageLabel.setIcon(image);
                    imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
                }
                else{
                    JOptionPane.showMessageDialog(null, 
                            "This is the last image in directory", 
                            "No previous images", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } 
        if(e.getSource() == previousButton){
            if(imageFileSelectedFlag == true){
                JOptionPane.showMessageDialog(null, 
                        "There are no previous images", 
                        "No previous images", 
                        JOptionPane.OK_OPTION, 
                        bruhChoiceIcon);
            }
            else{
                if(currentFileIndex > 0){
                    image = new ImageIcon(imageFilesInDirectory[--currentFileIndex].getAbsolutePath());
                    imageLabel.setIcon(image);
                    imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
                }
                else{
                    JOptionPane.showMessageDialog(null, 
                            "This is the first image in directory", 
                            "No previous images", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if(e.getSource() == save){
            int res = fileChooser.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "Image Has Been Saved!", "Image Saved", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(res == JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Image not saved");
            }
        }
        if(e.getSource() == exit){
            setVisible(false);
            dispose();
        }
        if(e.getSource() == loadDirectory){
            numberOfFiles = 0;
            currentFileIndex = 0;
            int res = fileChooser.showOpenDialog(null);
            if(res == JFileChooser.APPROVE_OPTION){
                chosenFile = fileChooser.getSelectedFile();
                if(chosenFile.isDirectory()){
                    imageFilesInDirectory = chosenFile.listFiles(imageFileExtensionFilter);
                    for(File counterFile : imageFilesInDirectory){
                        numberOfFiles++;
                    }
                    if(numberOfFiles != 0){
                        imageFileSelectedFlag = false;
                        image = new ImageIcon(imageFilesInDirectory[currentFileIndex].getAbsolutePath());
                        image.setDescription(imageFilesInDirectory[currentFileIndex].getName());
                        imageLabel.setIcon(image); 
                        imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
                    }
                    else if(numberOfFiles == 0){
                        JOptionPane.showMessageDialog(null, 
                                "No image files in chosen directory", 
                                "No images found!", 
                                JOptionPane.OK_OPTION, 
                                bruhChoiceIcon);
                    }
                }
                else if(chosenFile.isFile()){
                    JOptionPane.showMessageDialog(null, 
                            "Error", 
                            "The folder you have chosen is a file!", 
                            JOptionPane.ERROR_MESSAGE, 
                            bruhChoiceIcon);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Bruh...");
                }
            }
            else if(res == JFileChooser.CANCEL_OPTION){
                //do nothing
            }
        }
        if(e.getSource() == loadIndividualImageFile){
            int res = fileChooser.showOpenDialog(null);
            if(res == JFileChooser.APPROVE_OPTION){
                chosenFile = fileChooser.getSelectedFile();
                System.out.println(chosenFile.getAbsolutePath());
                if(chosenFile.isFile()){
                    image = new ImageIcon(chosenFile.getAbsolutePath());
                    image.setDescription(chosenFile.getName());
                    imageLabel.setIcon(image); 
                    imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
                    imageFileSelectedFlag = true;
                }
                else if(chosenFile.isDirectory()){
                    JOptionPane.showMessageDialog(null, 
                            "Error", 
                            "The file you have chosen is a directory!", 
                            JOptionPane.ERROR_MESSAGE, 
                            bruhChoiceIcon);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Bruh...");
                }
            }
            else if(res == JFileChooser.CANCEL_OPTION){
                //do nothing
            }
        }
        if(e.getSource() == info){
            JOptionPane.showMessageDialog(null, "This is a image viewing program made in Swing\nIn this program, you can choose a image directory or standalone image file to load it into the application\nUse the >, < and zoom button to navigate through images or zoom into images at your will");
        }

        if(e.getSource() == zoomButton){
            new ZoomWindow(image);
        }
    }
}