// Import necessary packages
package com.mycompany.neamaybe;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// SelectSkin class that extends JFrame and implements ActionListener
public class SelectSkin extends JFrame implements ActionListener {
    
    // Variables for the username, skin buttons, skin images, and other components
    String username;
    JButton skin1, skin2, skin3, skin4;
    JLabel einstein = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 194045.png");
    JLabel detective = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200351.png");
    JLabel king = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200611.png");
    JLabel blue = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-25 223513.png");
    JLabel topText = new JLabel("Please select your skin");
    JButton mainMenu = new JButton("Main Menu");
    int stuId;

    // Constructor for the SelectSkin class
    SelectSkin(String username) {

        // Set bounds for skin images
        einstein.setBounds(60, 125, 60, 60);
        detective.setBounds(160, 125, 60, 60);
        king.setBounds(260, 125, 60, 60);
        blue.setBounds(360, 125, 60, 60);

        // Set bounds for the main menu button and top text label
        mainMenu.setBounds(325, 310, 150, 40);
        mainMenu.addActionListener(this);
        topText.setBounds(150, 50, 1000, 40);
        topText.setFont(new Font("MV Boli", Font.PLAIN, 20));

        // Set layout to null
        this.setLayout(null);

        // Initialize skin buttons and set their bounds
        skin1 = new JButton("Einstein");
        skin1.setBounds(50, 200, 90, 30);
        skin1.addActionListener(this);

        skin2 = new JButton("Detective");
        skin2.setBounds(150, 200, 90, 30);
        skin2.addActionListener(this);

        skin3 = new JButton("King");
        skin3.setBounds(250, 200, 90, 30);
        skin3.addActionListener(this);

        skin4 = new JButton("Default");
        skin4.setBounds(350, 200, 90, 30);
        skin4.addActionListener(this);

        // Set the username and configure the JFrame
        this.username = username;
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.add(topText);
        this.add(skin1);
        this.add(skin2);
        this.add(skin3);
        this.add(skin4);
        this.add(einstein);
        this.add(detective);
        this.add(king);
        this.add(blue);
        this.add(mainMenu);
        this.setVisible(true);
    }

    // Action performed when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        // Retrieve the student ID from the database based on the username
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root", "Poopface891.");
            PreparedStatement sn = (PreparedStatement) connection.prepareStatement("select studentid"
                    + " from student where username = ?");
            sn.setString(1, username);
            ResultSet rs = sn.executeQuery();
            if (rs.next()) {
                stuId = rs.getInt("studentid");
            }
        } catch (Exception n) {
        }

        // If the main menu button is clicked, return to the main menu
        if (e.getSource() == mainMenu) {
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }

        // If skin1 is pressed, load LudoGame with einstein skin
        if (e.getSource() == skin1) {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root", "Poopface891.");
                PreparedStatement s = (PreparedStatement) connection
                        .prepareStatement("select skin1 from userskins where studentid = ?");
                s.setInt(1, stuId);
                ResultSet r = s.executeQuery();
                if (r.next()) {
                    if (r.getBoolean("skin1") == true) {
                        this.dispose();
                        LudoGame ludo = new LudoGame(username, "einstein");
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Skin hasn't been purchased,"
                                + " unable to use");
                    }
                }
            } catch (Exception n) {
            }

        }
        
        // If skin2 is pressed, load LudoGame with detective skin
        if(e.getSource() == skin2){
                        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase"
                    ,"root","Poopface891.");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select skin2 from userskins where studentid = ?");
            s.setInt(1,stuId);
            ResultSet r = s.executeQuery();
            if(r.next()){
                if(r.getBoolean("skin2")==true){
                    this.dispose();
                    LudoGame ludo = new LudoGame(username,"detective");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Skin hasnt been purchased, unable to use");
                }
            }
            }
            catch(Exception n){
            }
            
        }
        
        // If skin3 is pressed, load LudoGame with king skin
        if(e.getSource()== skin3){
                        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase"
                    ,"root","Poopface891.");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select skin3 from userskins"
                    + " where studentid = ?");
            s.setInt(1,stuId);
            ResultSet r = s.executeQuery();
            if(r.next()){
                if(r.getBoolean("skin3") == true){
                    this.dispose();
                    LudoGame ludo = new LudoGame(username,"king");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Skin hasnt been purchased,"
                            + " unable to use");
                }
            }
            }
            catch(Exception n){
            }
            
        }
        
        // If skin4 is pressed, load LudoGame with blue skin
        if(e.getSource() == skin4){
            this.dispose();
            LudoGame ludo = new LudoGame(username,"blue");
            
        }
        
    }

    // Method to load and return an image as a JLabel
    public static JLabel loadimage(String filename) {
        BufferedImage image;
        JLabel imagecon;
        try {
            image = ImageIO.read(new File(filename));
            Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            imagecon = new JLabel(new ImageIcon(scaledImage));
            return imagecon;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}



/*
package com.mycompany.neamaybe;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SelectSkin extends JFrame implements ActionListener {
    String username;
    JButton skin1,skin2,skin3,skin4;
    JLabel einstein = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 194045.png");
    JLabel detective = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200351.png");
    JLabel king = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200611.png");
    JLabel blue = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-25 223513.png");
    JLabel topText = new JLabel("Please select your skin");
    JButton mainMenu = new JButton("Main Menu");
    int stuId;
    
    SelectSkin(String username){
        
        einstein.setBounds(60,125,60,60);
        detective.setBounds(160,125,60,60);
        king.setBounds(260,125,60,60);
        blue.setBounds(360,125,60,60);
        
        mainMenu.setBounds(325,310,150,40);
        mainMenu.addActionListener(this);
        
        topText.setBounds(150,50,1000,40);
        topText.setFont(new Font("MV Boli", Font.PLAIN,20));
        
        this.setLayout(null);
        
        skin1 = new JButton("Einstein");
        skin1.setBounds(50,200,90,30);
        skin1.addActionListener(this);
        
        skin2 = new JButton("Detective");
        skin2.setBounds(150,200,90,30);
        skin2.addActionListener(this);
        
        skin3 = new JButton("King");
        skin3.setBounds(250,200,90,30);
        skin3.addActionListener(this);
        
        skin4 = new JButton("Default");
        skin4.setBounds(350,200,90,30);
        skin4.addActionListener(this);
        
        this.username = username;
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,400);
        this.add(topText);
        this.add(skin1);
        this.add(skin2);
        this.add(skin3);
        this.add(skin4);
        this.add(einstein);
        this.add(detective);
        this.add(king);
        this.add(blue);
        this.add(mainMenu);
        this.setVisible(true);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try{
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
                PreparedStatement sn = (PreparedStatement) connection.prepareStatement("select studentid from student where username = ?");
                sn.setString(1,username);
                ResultSet rs = sn.executeQuery();
                if(rs.next()){
                    stuId = rs.getInt("studentid");
                }
            }
            catch(Exception n){ 
            }
        
        if(e.getSource() == mainMenu){
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }
        
        if(e.getSource() == skin1){
            try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select skin1 from userskins where studentid = ?");
            s.setInt(1,stuId);
            ResultSet r = s.executeQuery();
            if(r.next()){
                if(r.getBoolean("skin1")==true){
                    this.dispose();
                    LudoGame ludo = new LudoGame(username,"einstein");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Skin hasnt been purchased, unable to use");
                }
            }
            }
            catch(Exception n){
            }
            
        }
        
        if(e.getSource() == skin2){
                        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select skin2 from userskins where studentid = ?");
            s.setInt(1,stuId);
            ResultSet r = s.executeQuery();
            if(r.next()){
                if(r.getBoolean("skin2")==true){
                    this.dispose();
                    LudoGame ludo = new LudoGame(username,"detective");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Skin hasnt been purchased, unable to use");
                }
            }
            }
            catch(Exception n){
            }
            
        }
        
        if(e.getSource()== skin3){
                        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select skin3 from userskins where studentid = ?");
            s.setInt(1,stuId);
            ResultSet r = s.executeQuery();
            if(r.next()){
                if(r.getBoolean("skin3") == true){
                    this.dispose();
                    LudoGame ludo = new LudoGame(username,"king");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Skin hasnt been purchased, unable to use");
                }
            }
            }
            catch(Exception n){
            }
            
        }
        
        if(e.getSource() == skin4){
            this.dispose();
            LudoGame ludo = new LudoGame(username,"blue");
            
        }
    }
    
    public static JLabel loadimage(String filename){
        BufferedImage image;
        JLabel imagecon;
        try{
        image = ImageIO.read(new File(filename));
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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