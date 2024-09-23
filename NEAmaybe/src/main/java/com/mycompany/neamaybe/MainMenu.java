// Import necessary packages
package com.mycompany.neamaybe;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

// MainMenu class to represent the main menu in the game
public class MainMenu extends JFrame implements ActionListener{
    
    // Declare instance variables for buttons, labels, and user data
    String username;
    JButton gameButton;
    JButton classroommenu;
    JButton sendToItemShop;
    String pic = "C:/Users/Zaheer/Documents/Screenshot 2023-11-26 182230.png";
    JLabel im = loadimage(pic);
    JLabel topText = new JLabel("Main Menu");
    JLabel totalAnswered = new JLabel();
    JLabel totalqLabel = new JLabel("Total questions answered");
    JLabel percentageLabel = new JLabel("Total % correct");
    JLabel totalPercentage = new JLabel();
    
    // Constructor for the MainMenu class
    MainMenu(String username){
        
        // Initialize instance variables
        this.username = username;
        
        // Create a border for labels
        Border border = BorderFactory.createLineBorder(Color.green,3);
        
        // Set properties for the topText label
        topText.setBounds(420,40,300,30);
        topText.setFont(new Font("MV Boli", Font.PLAIN,30));
        
        // Set properties for the totalAnswered label
        totalAnswered.setBorder(border);
        totalAnswered.setBounds(90,490,100,100);
        totalAnswered.setText("             " + questionsTotal(username));
        
        // Set properties for the totalPercentage label
        totalPercentage.setBorder(border);
        totalPercentage.setBounds(290,490,100,100);
        totalPercentage.setText("           " + questionP(username) +"%");
        
        // Set properties for labels
        percentageLabel.setBounds(300,580,200,40);
        totalqLabel.setBounds(70,580,200,40);
        
        // Set layout for the JFrame
        this.setLayout(null);
        
        // Create and set properties for the background label
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0,0,1000000000,100000);
        
        // Set properties for the image label
        im.setBounds(500,100,400,500);
        
        // Set properties for the sendToItemShop button
        sendToItemShop = new JButton("Send to shop");
        sendToItemShop.addActionListener(this);
        sendToItemShop.setFont(new Font("MV Boli", Font.PLAIN,15));
        sendToItemShop.setFocusable(false);
        sendToItemShop.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        sendToItemShop.setBounds(100,370,300,50);
        
        // Set properties for the gameButton
        gameButton = new JButton();
        gameButton.setBounds(100,170,300,50);
        gameButton.addActionListener(this);
        gameButton.setText("Send to game");
        gameButton.setFont(new Font("MV Boli", Font.PLAIN,15));
        gameButton.setFocusable(false);
        gameButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        // Set properties for the classroommenu button
        classroommenu = new JButton("Send to classroom menu");
        classroommenu.addActionListener(this);
        classroommenu.setFont(new Font("MV Boli", Font.PLAIN,15));
        classroommenu.setFocusable(false);
        classroommenu.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        classroommenu.setBounds(100,270,300,50); 
        
        // Add components to the JFrame
        this.add(gameButton);
        this.add(classroommenu);
        this.add(sendToItemShop);
        this.add(topText);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.add(im);
        this.add(totalAnswered);
        this.add(totalPercentage);
        this.add(percentageLabel);
        this.add(totalqLabel);
        this.add(backgroundLabel);
        this.setVisible(true);
    }
    
    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle gameButton click
        if(e.getSource()==gameButton){
            this.dispose();
            SelectSkin skin = new SelectSkin(username);
        }
        
        // Handle classroommenu button click
        if(e.getSource() == classroommenu){
            this.dispose();
            StudentClassroom classroom = new StudentClassroom(username); 
        }
        
        // Handle sendToItemShop button click
        if(e.getSource() == sendToItemShop){
            this.dispose();
            ItemShop shop = new ItemShop(username);
        }
    }
    
    // Method to retrieve total questions answered
    public static int questionsTotal(String username){
        int total;
        try{
            // Establish a database connection
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase","root","Poopface891.");
            
            // Retrieve the total questions answered from the database
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select totalqans from gamedata,student where student.username=? "
                    + "and student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                total = rs.getInt("totalqans");
                return total;
            }
            else{
                return 9; // Default value if data is not found
            }
        }
        catch(Exception e){
            System.out.println(e);
            return 100; // Default value for error case
        }
    }
    
    // Method to retrieve the total percentage correct
    public static int questionP(String username){
        int total;
        try{
            // Establish a database connection
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase","root","Poopface891.");
            
            // Retrieve the total percentage correct from the database
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select totalpercentage from gamedata,student where student.username=? "
                    + "and student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                total = rs.getInt("totalpercentage");
                return total;
            }
            else{
                return 99; // Default value if data is not found
            }
        }
        catch(Exception e){
            System.out.println(e);
            return 94; // Default value for error case
        }
    }
    
    // Method to load an image from a file
    public static JLabel loadimage(String filename){
        BufferedImage image;
        JLabel imagecon;
        try{
            // Read the image file
            image = ImageIO.read(new File(filename));
            Image scaledImage = image.getScaledInstance(400, 500, Image.SCALE_SMOOTH);
            imagecon = new JLabel(new ImageIcon(scaledImage));
            return imagecon;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
}


/*
package com.mycompany.neamaybe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MainMenu extends JFrame implements ActionListener{
    
    String username;
    JButton gameButton;
    JButton classroommenu;
    JButton sendToItemShop;
    String pic = "C:/Users/Zaheer/Documents/Screenshot 2023-11-26 182230.png";
    JLabel im = loadimage(pic);
    JLabel topText = new JLabel("Main Menu");
    JLabel totalAnswered = new JLabel();
    JLabel totalqLabel = new JLabel("Total questions answered");
    JLabel percentageLabel = new JLabel("Total % correct");
    JLabel totalPercentage = new JLabel();
    
    MainMenu(String username){
        
        this.username = username;
        
        Border border = BorderFactory.createLineBorder(Color.green,3);
        
        topText.setBounds(420,40,300,30);
        topText.setFont(new Font("MV Boli", Font.PLAIN,30));
        
        totalAnswered.setBorder(border);
        totalAnswered.setBounds(90,490,100,100);
        totalAnswered.setText("             " + questionsTotal(username));
        
        totalPercentage.setBorder(border);
        totalPercentage.setBounds(290,490,100,100);
        totalPercentage.setText("           " + questionP(username) +"%");
        
        percentageLabel.setBounds(300,580,200,40);
        
        totalqLabel.setBounds(70,580,200,40);
        
        this.setLayout(null);
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0,0,1000000000,100000);
        
        im.setBounds(500,100,400,500);
        
        sendToItemShop = new JButton("Send to shop");
        sendToItemShop.addActionListener(this);
        sendToItemShop.setFont(new Font("MV Boli", Font.PLAIN,15));
        sendToItemShop.setFocusable(false);
        sendToItemShop.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        sendToItemShop.setBounds(100,370,300,50);
        
        gameButton = new JButton();
        gameButton.setBounds(100,170,300,50);
        gameButton.addActionListener(this);
        gameButton.setText("Send to game");
        gameButton.setFont(new Font("MV Boli", Font.PLAIN,15));
        gameButton.setFocusable(false);
        gameButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        classroommenu = new JButton("Send to classroom menu");
        classroommenu.addActionListener(this);
        classroommenu.setFont(new Font("MV Boli", Font.PLAIN,15));
        classroommenu.setFocusable(false);
        classroommenu.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        classroommenu.setBounds(100,270,300,50); 
        
        this.add(gameButton);
        this.add(classroommenu);
        this.add(sendToItemShop);
        this.add(topText);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.add(im);
        this.add(totalAnswered);
        this.add(totalPercentage);
        this.add(percentageLabel);
        this.add(totalqLabel);
        this.add(backgroundLabel);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==gameButton){
            this.dispose();
            SelectSkin skin = new SelectSkin(username);
        }
        
        if(e.getSource() == classroommenu){
            this.dispose();
            StudentClassroom classroom = new StudentClassroom(username); 
        }
        
        if(e.getSource() == sendToItemShop){
            this.dispose();
            ItemShop shop = new ItemShop(username);
        }
        
    }
    
    
    public static int questionsTotal(String username){
        int total;
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select totalqans from gamedata,student where student.username=? "
                    + "and student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                total = rs.getInt("totalqans");
                return total;
            }
            else{
                return 9;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return 100;
        }
    }
    
    public static int questionP(String username){
        int total;
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select totalpercentage from gamedata,student where student.username=? "
                    + "and student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                total = rs.getInt("totalpercentage");
                return total;
            }
            else{
                return 99;
            }
            
        }
        catch(Exception e){
            System.out.println(e);
            return 94;
        }
    }
    
    public static JLabel loadimage(String filename){
        BufferedImage image;
        JLabel imagecon;
        try{
        image = ImageIO.read(new File(filename));
        Image scaledImage = image.getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        imagecon = new JLabel(new ImageIcon(scaledImage));
        return imagecon;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
}
*/