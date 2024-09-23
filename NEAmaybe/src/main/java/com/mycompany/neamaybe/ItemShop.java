// Import necessary packages
package com.mycompany.neamaybe;
import java.awt.Color;
import static java.awt.Color.red;
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
import javax.swing.JOptionPane;
import javax.swing.border.Border;

// ItemShop class to represent the item shop in the game
public class ItemShop extends JFrame implements ActionListener{
    
    // Declare instance variables for buttons, labels, and user data
    JButton einstein, detective, king;
    String username;
    int stuId;
    JLabel einsteinskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 194045.png");
    JLabel detectiveskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200351.png");
    JLabel kingskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200611.png");
    JLabel einsteinPurchase = new JLabel("Einstein skin has been purchased");
    JLabel detectivePurchase = new JLabel("Detective skin has been purchased");
    JLabel kingPurchase = new JLabel("King skin has been purchased");
    JButton mainMenu = new JButton("Main Menu");
    JLabel topText = new JLabel("Welcome to the item shop");
    JLabel detectivePrice = new JLabel("3000 to purchase");
    JLabel einsteinPrice = new JLabel("6000 to purchase");
    JLabel kingPrice = new JLabel("9000 to purchase");
    JLabel background = new JLabel();
    JButton checkScore = new JButton("Check score");
    // Constructor for the ItemShop class
    ItemShop(String username){
        
        // Initialize instance variables
        this.username = username;
        
        // Create a border for the topText label
        Border border = BorderFactory.createLineBorder(Color.green,5);
        
        // Set properties for the topText label
        topText.setFont(new Font("MV Boli", Font.PLAIN,20));
        topText.setBounds(350,50,270,60);
        topText.setBorder(border);
        topText.setBackground(new Color(0x00FFFF));
        topText.setOpaque(true);
        
        // Set properties for the background label
        background.setBackground(new Color(0x00FFFF));
        background.setOpaque(true);
        background.setBounds(0,0,1000000000,100000);
        
        // Set properties for the mainMenu button
        mainMenu.setBounds(780,600,200,60);
        mainMenu.addActionListener(this);
        
        // Set properties for the checkScore button
        checkScore.setBounds(780,100,200,60);
        checkScore.addActionListener(this);
        
        
        // Initialize buttons and set their properties
        einstein = new JButton("Purchase Einstein");
        detective = new JButton("Purchase Detective");
        king = new JButton("Purchase King");
        
        // Set properties for the JFrame
        this.setTitle("Maths Ludo Game");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        
        // Add components to the JFrame
        this.add(einsteinskin);
        this.add(detectiveskin);
        this.add(kingskin);
        this.add(einstein);
        this.add(detective);
        this.add(king);
        this.add(einsteinPurchase);
        this.add(mainMenu);
        this.add(checkScore);
        this.add(topText);
        this.add(kingPurchase);
        this.add(detectivePurchase);
        this.add(detectivePrice);
        this.add(kingPrice);
        this.add(einsteinPrice);
        this.add(background);
        this.setVisible(true);
        
        // Hide initially unnecessary components
        einsteinskin.setVisible(false);
        kingskin.setVisible(false);
        detectiveskin.setVisible(false);
        einstein.setVisible(false);
        einsteinPurchase.setVisible(false);
        einsteinPrice.setVisible(false);
        detectivePrice.setVisible(false);
        kingPrice.setVisible(false);
        
        // Display images and user purchases
        displayImages();
    }

    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Handle mainMenu button click
        if(e.getSource()==mainMenu){
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }
        
        // Handle checkScore button click
        if(e.getSource() == checkScore){
            try{
                int score = 0;
            // Establish a database connection
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root",
                        "Poopface891.");
                
                // Check if the user has enough score to purchase the skin
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from gamedata,student"
                        + " where student.username = ? "
                        + "and student.studentid=gamedata.studentid ");
                st.setString(1,username);
                ResultSet r = st.executeQuery();
                if(r.next()){
                    score = r.getInt("score");
                }
                JOptionPane.showMessageDialog(null, "Your current score is " + score);
                
        }
            catch(Exception s){
                System.out.println(s);
            }
            
        }
            
        
        // Handle einstein button click
        if(e.getSource() == einstein){
            try{
                // Establish a database connection
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root",
                        "Poopface891.");
                
                // Check if the user has enough score to purchase the skin
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from gamedata,student"
                        + " where student.username = ? "
                        + "and student.studentid=gamedata.studentid ");
                st.setString(1,username);
                ResultSet r = st.executeQuery();
                if(r.next()){
                    if(r.getInt("score") >= 6000){
                         // Update the database to mark the skin as purchased
                         PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE userskins SET skin1"
                                 + " = true WHERE studentid = ?");
                         stn.setInt(1, stuId);
                         stn.executeUpdate();
                         JOptionPane.showMessageDialog(null, "Successfully bought Einstein skin!");
                         this.dispose();
                         ItemShop item = new ItemShop(username);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not enough score to purchase Einstein skin.");
                    }

                }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
        }
        
        // Handle detective button click
        if(e.getSource() == detective){
            try{
                // Establish a database connection
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root",
                        "Poopface891.");
                
                // Check if the user has enough score to purchase the skin
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from"
                        + " gamedata,student"
                        + " where student.username = ? "
                        + "and student.studentid=gamedata.studentid ");
                st.setString(1,username);
                ResultSet r = st.executeQuery();
                if(r.next()){
                    if(r.getInt("score") >= 3000){
                        // Update the database to mark the skin as purchased
                        PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE userskins"
                                + " SET"
                                + " skin2 = true WHERE studentid = ?");
                         stn.setInt(1, stuId);
                         stn.executeUpdate();
                          JOptionPane.showMessageDialog(null, "Successfully bought Detective"
                                  + " skin!");
                          this.dispose();
                          ItemShop item = new ItemShop(username);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not enough score to purchase"
                                + " Detective skin.");
                    }

                }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
            
        }
        
        // Handle king button click
        if(e.getSource() == king){
            try{
                // Establish a database connection
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase","root",
                        "Poopface891.");
                
                // Check if the user has enough score to purchase the skin
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from"
                        + " gamedata,student where student.username = ? "
                        + "and student.studentid=gamedata.studentid ");
                st.setString(1,username);
                ResultSet r = st.executeQuery();
                if(r.next()){
                    if(r.getInt("score") >= 9000){
                        // Update the database to mark the skin as purchased
                        PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE"
                                + " userskins SET skin3 = true WHERE studentid = ?");
                         stn.setInt(1, stuId);
                         stn.executeUpdate();
                          JOptionPane.showMessageDialog(null, "Successfully bought King"
                                  + " skin!");
                          this.dispose();
                          ItemShop item = new ItemShop(username);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not enough score to purchase"
                                + " King skin.");
                    }

                }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
            
        }
        
    }
        
    // Method to load an image from a file
    public static JLabel loadimage(String filename){
        
        BufferedImage image;
        JLabel imagecon;
        
        try{
            
        // Read the image file
        image = ImageIO.read(new File(filename));
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagecon = new JLabel(new ImageIcon(scaledImage));
        return imagecon;
        
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    // Method to display images and user purchases
    public void displayImages(){
        try{
            // Establish a database connection
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root","Poopface891.");
            
            // Retrieve the user's student ID from the database
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid from student"
                    + " where username = ?");
            s.setString(1,username);
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                stuId = rs.getInt("studentid");
            }
            
            // Check if the user has purchased the Einstein skin
            PreparedStatement skinone = (PreparedStatement) connection.prepareStatement("SELECT skin1 FROM "
                    + "userskins WHERE studentid = ?");
            skinone.setInt(1,stuId);
            ResultSet skin1 = skinone.executeQuery();
            if(skin1.next()){
                if(skin1.getBoolean("skin1") == true){
                    
                    // Display the purchase message for Einstein skin
                    einsteinPurchase.setBounds(100,175,200,200);
                    einsteinPurchase.setVisible(true);
                    
                }
                else{
                    
                    // Display the price for Einstein skin
                    einsteinPrice.setBounds(135,440,200,50);
                    einsteinPrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    einsteinPrice.setVisible(true);
                    
                    // Display the image for Einstein skin
                    einsteinskin.setBounds(100, 175, 200, 200);
                    einsteinskin.setVisible(true);
                    
                    // Display the purchase button for Einstein skin
                    einstein.setBounds(100,400,200,50);
                    einstein.addActionListener(this);
                    einstein.setFont(new Font("MV Boli", Font.PLAIN,15));
                    einstein.setFocusable(false);
                    einstein.setBackground(red);
                    einstein.setVisible(true);
                    
                }
                
            }
            
            // Check if the user has purchased the Detective skin
            PreparedStatement skintwo = (PreparedStatement) connection.prepareStatement("SELECT skin2 FROM"
                    + " userskins WHERE studentid = ?");
            skintwo.setInt(1,stuId);
            ResultSet skin2 = skintwo.executeQuery();
            if(skin2.next()){
                if(skin2.getBoolean("skin2")== true){
                    
                    // Display the purchase message for Detective skin
                    detectivePurchase.setBounds(350,175,200,200);
                    detectivePurchase.setVisible(true);
                    
                }
                else{
                    
                    // Display the price for Detective skin
                    detectivePrice.setBounds(385,440,200,50);
                    detectivePrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    detectivePrice.setVisible(true);
                    
                    // Display the image for Detective skin
                    detectiveskin.setBounds(350, 175, 200, 200);
                    detectiveskin.setVisible(true);
                    
                    // Display the purchase button for Detective skin
                    detective.setBounds(350,400,200,50);
                    detective.addActionListener(this);
                    detective.setFont(new Font("MV Boli", Font.PLAIN,15));
                    detective.setFocusable(false);
                    detective.setBackground(red);
                    detective.setVisible(true);    
                    
                }
                
            }
            
            // Check if the user has purchased the King skin
            PreparedStatement skinthree = (PreparedStatement) connection.prepareStatement("SELECT skin3 FROM"
                    + " userskins WHERE studentid = ?");
            skinthree.setInt(1,stuId);
            ResultSet skin3 = skinthree.executeQuery();
            if(skin3.next()){
                if(skin3.getBoolean("skin3")== true){
                    
                    // Display the purchase message for King skin
                    kingPurchase.setBounds(600,175,200,200);
                    kingPurchase.setVisible(true);
                    
                }
                else{
                    
                    // Display the price for King skin
                    kingPrice.setBounds(635,440,200,50);
                    kingPrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    kingPrice.setVisible(true);
                    
                    // Display the image for King skin
                    kingskin.setBounds(600, 175, 200, 200);
                    kingskin.setVisible(true);
                    
                    // Display the purchase button for King skin
                    king.setBounds(600,400,200,50);
                    king.addActionListener(this);
                    king.setFont(new Font("MV Boli", Font.PLAIN,15));
                    king.setFocusable(false);
                    king.setBackground(red);
                    king.setVisible(true);
                    
                }
                
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}


/*
package com.mycompany.neamaybe;
import java.awt.Color;
import static java.awt.Color.red;
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
import javax.swing.JOptionPane;
import javax.swing.border.Border;




public class ItemShop extends JFrame implements ActionListener{
    
    JButton einstein,detective,king;
    String username;
    int stuId;
    JLabel einsteinskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 194045.png");
    JLabel detectiveskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200351.png");
    JLabel kingskin = loadimage("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200611.png");
    JLabel einsteinPurchase = new JLabel("Einstein skin has been purchased");
    JLabel detectivePurchase = new JLabel("Detective skin has been purchased");
    JLabel kingPurchase = new JLabel("King skin has been purchased");
    JButton mainMenu = new JButton("Main Menu");
    JLabel topText = new JLabel("Welcome to the item shop");
    JLabel detectivePrice = new JLabel("3000 to purchase");
    JLabel einsteinPrice = new JLabel("6000 to purchase");
    JLabel kingPrice = new JLabel("9000 to purchase");
    JLabel background = new JLabel();
    
    ItemShop(String username){
        
        this.username = username;
        
        Border border = BorderFactory.createLineBorder(Color.green,5);
        
        topText.setFont(new Font("MV Boli", Font.PLAIN,20));
        topText.setBounds(350,50,270,60);
        topText.setBorder(border);
        topText.setBackground(new Color(0x00FFFF));
        topText.setOpaque(true);
        
        
        background.setBackground(new Color(0x00FFFF));
        background.setOpaque(true);
        background.setBounds(0,0,1000000000,100000);
        
        mainMenu.setBounds(780,600,200,60);
        mainMenu.addActionListener(this);
        
        einstein = new JButton("Purchase Einstein");
        detective = new JButton("Purchase Detective");
        king = new JButton("Purchase King");
        
        this.setTitle("Maths Ludo Game");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.add(einsteinskin);
        this.add(detectiveskin);
        this.add(kingskin);
        this.add(einstein);
        this.add(detective);
        this.add(king);
        this.add(einsteinPurchase);
        this.add(mainMenu);
        this.add(topText);
        this.add(kingPurchase);
        this.add(detectivePurchase);
        this.add(detectivePrice);
        this.add(kingPrice);
        this.add(einsteinPrice);
        this.add(background);
        this.setVisible(true);
        
        einsteinskin.setVisible(false);
        kingskin.setVisible(false);
        detectiveskin.setVisible(false);
        einstein.setVisible(false);
        einsteinPurchase.setVisible(false);
        einsteinPrice.setVisible(false);
        detectivePrice.setVisible(false);
        kingPrice.setVisible(false);
        displayImages();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==mainMenu){
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }
        
        if(e.getSource() == einstein){
            try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from gamedata,student where student.username = ? "
                    + "and student.studentid=gamedata.studentid ");
            st.setString(1,username);
            ResultSet r = st.executeQuery();
            if(r.next()){
                if(r.getInt("score") >= 6000){
                     PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE userskins SET skin1 = true WHERE studentid = ?");
                     stn.setInt(1, stuId);
                     stn.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Successfully bought skin!");
                     this.dispose();
                     ItemShop item = new ItemShop(username);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not enough score buddy");
                }
                
            }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
    }
    
        if(e.getSource() == detective){
            try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from gamedata,student where student.username = ? "
                    + "and student.studentid=gamedata.studentid ");
            st.setString(1,username);
            ResultSet r = st.executeQuery();
            if(r.next()){
                if(r.getInt("score") >= 3000){
                    PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE userskins SET skin2 = true WHERE studentid = ?");
                     stn.setInt(1, stuId);
                     stn.executeUpdate();
                      JOptionPane.showMessageDialog(null, "Successfully bought skin!");
                      this.dispose();
                      ItemShop item = new ItemShop(username);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not enough score buddy");
                }
                
            }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
            
        }
        
        if(e.getSource() == king){
            try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select score from gamedata,student where student.username = ? "
                    + "and student.studentid=gamedata.studentid ");
            st.setString(1,username);
            ResultSet r = st.executeQuery();
            if(r.next()){
                if(r.getInt("score") >= 9000){
                    PreparedStatement stn = (PreparedStatement) connection.prepareStatement("UPDATE userskins SET skin2 = true WHERE studentid = ?");
                     stn.setInt(1, stuId);
                     stn.executeUpdate();
                      JOptionPane.showMessageDialog(null, "Successfully bought skin!");
                      this.dispose();
                      ItemShop item = new ItemShop(username);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not enough score buddy");
                }
                
            }
            }
            catch(Exception ev){
                System.out.println(ev);
            }
            
        }
        
    }
        
    public static JLabel loadimage(String filename){
        
        BufferedImage image;
        JLabel imagecon;
        
        try{
            
        image = ImageIO.read(new File(filename));
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagecon = new JLabel(new ImageIcon(scaledImage));
        return imagecon;
        
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public void displayImages(){
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid from student where username = ?");
            s.setString(1,username);
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                stuId = rs.getInt("studentid");
            }
            PreparedStatement skinone = (PreparedStatement) connection.prepareStatement("SELECT skin1 FROM userskins WHERE studentid = ?");
            skinone.setInt(1,stuId);
            ResultSet skin1 = skinone.executeQuery();
            if(skin1.next()){
                if(skin1.getBoolean("skin1") == true){
                    
                    einsteinPurchase.setBounds(100,175,200,200);
                    einsteinPurchase.setVisible(true);
                    
                }
                else{
                    
                    einsteinPrice.setBounds(135,440,200,50);
                    einsteinPrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    einsteinPrice.setVisible(true);
                    
                    einsteinskin.setBounds(100, 175, 200, 200);
                    einsteinskin.setVisible(true);
                    
                    einstein.setBounds(100,400,200,50);
                    einstein.addActionListener(this);
                    einstein.setFont(new Font("MV Boli", Font.PLAIN,15));
                    einstein.setFocusable(false);
                    einstein.setBackground(red);
                    einstein.setVisible(true);
                    
                }
                
            }
            
            PreparedStatement skintwo = (PreparedStatement) connection.prepareStatement("SELECT skin2 FROM userskins WHERE studentid = ?");
            skintwo.setInt(1,stuId);
            ResultSet skin2 = skintwo.executeQuery();
            if(skin2.next()){
                if(skin2.getBoolean("skin2")== true){
                    
                    detectivePurchase.setBounds(350,175,200,200);
                    detectivePurchase.setVisible(true);
                    
                }
                else{
                    
                    detectivePrice.setBounds(385,440,200,50);
                    detectivePrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    detectivePrice.setVisible(true);
                    
                    detectiveskin.setBounds(350, 175, 200, 200);
                    detectiveskin.setVisible(true);
                    
                    detective.setBounds(350,400,200,50);
                    detective.addActionListener(this);
                    detective.setFont(new Font("MV Boli", Font.PLAIN,15));
                    detective.setFocusable(false);
                    detective.setBackground(red);
                    detective.setVisible(true);    
                    
                }
                
            }
            
            PreparedStatement skinthree = (PreparedStatement) connection.prepareStatement("SELECT skin3 FROM userskins WHERE studentid = ?");
            skinthree.setInt(1,stuId);
            ResultSet skin3 = skinthree.executeQuery();
            if(skin3.next()){
                if(skin3.getBoolean("skin3")== true){
                    
                    kingPurchase.setBounds(600,175,200,200);
                    kingPurchase.setVisible(true);
                    
                }
                else{
                    
                    kingPrice.setBounds(635,440,200,50);
                    kingPrice.setFont(new Font("MV Boli", Font.PLAIN,15));
                    kingPrice.setVisible(true);
                    
                    kingskin.setBounds(600, 175, 200, 200);
                    kingskin.setVisible(true);
                    
                    king.setBounds(600,400,200,50);
                    king.addActionListener(this);
                    king.setFont(new Font("MV Boli", Font.PLAIN,15));
                    king.setFocusable(false);
                    king.setBackground(red);
                    king.setVisible(true);
                    
                }
                
            }
            }
        catch(Exception e){
            System.out.println(e);
        }
    }
}





    /*
    String username;
    JButton backToMainMenu, button1, button2, button3;
    JLabel imageLabel1, imageLabel2, imageLabel3;
    JLabel label1, label2, label3;
    itemShop(String username) {
        JPanel mainPanel = new JPanel(new BorderLayout());
label1 = new JLabel("Label 1");
label2 = new JLabel("Label 2");
label3 = new JLabel("Label 3");
        JPanel imagePanel = new JPanel();
        backToMainMenu = new JButton("Main Menu");
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");
        this.username = username;

        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);

        // Add backToMainMenu button to the content pane (Bottom Left)
        mainPanel.add(backToMainMenu, BorderLayout.SOUTH);
        backToMainMenu.addActionListener(this);
        backToMainMenu.setFont(new Font("MV Boli", Font.PLAIN, 15));
        backToMainMenu.setFocusable(false);
        backToMainMenu.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));

        // Create imageLabel1 and add it to the imagePanel
        imageLabel1 = createImageLabel("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 194045.png", "Image 1");
        imagePanel.add(imageLabel1);
        imagePanel.add(label1);
        imagePanel.add(button1);

        // Create imageLabel2 and add it to the imagePanel
        imageLabel2 = createImageLabel("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200351.png", "Image 2");
        imagePanel.add(imageLabel2);
        imagePanel.add(label2);
        imagePanel.add(button2);

        // Create imageLabel3 and add it to the imagePanel
        imageLabel3 = createImageLabel("C:/Users/Zaheer/Documents/Screenshot 2023-11-23 200611.png", "Image 3");
        imagePanel.add(imageLabel3);
        imagePanel.add(label3);
        imagePanel.add(button3);

        // Add imagePanel to the mainPanel (Center)
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Add mainPanel to the content pane
        this.add(mainPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    private JLabel createImageLabel(String imagePath, String labelText) {
        JLabel imageLabel = new JLabel();
        imageLabel.setBackground(new Color(0x00FFFF));
        ImageIcon imageIcon = resizeImage(imagePath, 100, 100);
        imageLabel.setIcon(imageIcon);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
JLabel label = new JLabel(labelText);
    label.setVerticalAlignment(JLabel.TOP);
    label.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.add(label, BorderLayout.SOUTH);
        // Create a button beneath the image
        JButton button = new JButton(labelText);
        imageLabel.add(button, BorderLayout.SOUTH);
        button.addActionListener(this);
        button.setFocusable(false);

        return imageLabel;
    }
    
    private ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/