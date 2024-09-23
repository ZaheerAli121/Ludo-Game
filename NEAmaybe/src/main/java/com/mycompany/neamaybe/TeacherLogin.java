
package com.mycompany.neamaybe;

//Import necessary java classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

// TeacherLogin class inherits from JFrame and implements ActionListener
public class TeacherLogin extends JFrame implements ActionListener {

    // Component initialization
    JToggleButton visibilityToggle;
    JButton signUp;
    JButton studentLogin;
    JTextField username;
    JButton confirm;
    JPasswordField passw;

    // Constructor for the class
    TeacherLogin() {

        // Create a green border object to be added to JLabels
        Border border = BorderFactory.createLineBorder(Color.green, 5);

        // Set frame properties
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);

        // Create visibilityToggle button and configure properties
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(150, 290, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN, 8));
        visibilityToggle.setBackground(Color.red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        this.add(visibilityToggle);

        // Create username JTextField and configure properties
        username = new JTextField();
        username.setFont(new Font("MV Boli", Font.PLAIN, 15));
        username.setBounds(150, 200, 500, 30);
        this.add(username);

        // Create passw JPasswordField and configure properties
        passw = new JPasswordField();
        passw.setFont(new Font("MV Boli", Font.PLAIN, 15));
        passw.setBounds(150, 250, 500, 30);
        this.add(passw);

        // Create JLabel backGround and configure properties
        JLabel backGround = new JLabel();
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setBackground(new Color(0x00FFFF));
        backGround.setBorder(border);
        backGround.setOpaque(true);
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setFont(new Font("MV Boli", Font.PLAIN, 20));

        // Create JLabel topText and configure properties
        JLabel topText = new JLabel();
        topText.setText("Teacher Login Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN, 50));
        topText.setBounds(250, -25, 500, 300);

        // Create JLabel usernameLabel and configure properties
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernameLabel.setBounds(50, 163, 100, 100);

        // Create JLabel passwordLabel and configure properties
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 213, 100, 100);

        // Create signUp JButton and configure properties
        signUp = new JButton();
        signUp.setBounds(445, 500, 100, 50);
        signUp.addActionListener(this);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("MV Boli", Font.PLAIN, 15));
        signUp.setFocusable(false);
        signUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));

        // Create studentLogin button and configure properties
        studentLogin = new JButton();
        studentLogin.setBounds(330, 330, 330, 75);
        studentLogin.setText("Student Login Page");
        studentLogin.setFocusable(false);
        studentLogin.setFont(new Font("MV Boli", Font.PLAIN, 15));
        studentLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        studentLogin.addActionListener(this);

        // Create confirm button and configure properties
        confirm = new JButton();
        confirm.setBounds(700, 220, 100, 50);
        confirm.addActionListener(this);
        confirm.setText("Confirm");
        confirm.setFont(new Font("MV Boli", Font.PLAIN, 15));
        confirm.setFocusable(false);
        confirm.setBackground(Color.red);

        // Add components to the frame in specifc order
        this.add(signUp);
        this.add(studentLogin);
        this.add(confirm);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(backGround);
        this.setResizable(false);
        this.setVisible(true);
    }

    // Overriding the actionPerformed method from ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        // Toggle password visibility
        if (visibilityToggle.isSelected()) {
            passw.setEchoChar((char) 0);
        } else {
            passw.setEchoChar('*');
        }

        // SignUp button action
        if (e.getSource() == signUp) {
            //Closes frame and opens SignUpChoice frame
            this.dispose();
            SignUpChoice window = new SignUpChoice();
        }

        // StudentLogin button action
        if (e.getSource() == studentLogin) {
            //Closes frame and opens StudentLogin frame
            this.dispose();
            StudentLogin hi = new StudentLogin();
        }

        // Get username and password from JTextFields
        String usern = username.getText();
        String password = passw.getText();

        // Create an instance of the HashAlg class
        HashAlg alg = new HashAlg();

        // Try-catch block to handle hashing exceptions
        try {
            // Hash the password using the hash method
            password = alg.hash(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Confirm button action
        if (e.getSource() == confirm) {

            // Try-catch block to handle SQL exceptions
            try {
                // Establish a connection to the database
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root", "Poopface891.");

                // Prepare a statement for selecting username and password from the teacher table
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select username,"
                        + " passw from teacher where"
                        + " username=? and passw=?");
                st.setString(1, usern);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();

                // Check if the login is successful
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Successfully logged in!");
                    TeacherClassroom classroom = new TeacherClassroom(usern);
                    dispose();
                } else {
                    //If unsucessful, displays message
                    JOptionPane.showMessageDialog(null, "Incorrect username/password please retry");
                }

            } catch (SQLException ex) {
                Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


/*
//creates TeacherLogin class that inherits JFrame and ActionListener attributes
public class TeacherLogin extends JFrame implements ActionListener{
    
    //initializes components that are later to be added to the frame
    JToggleButton visibilityToggle;
    JButton signUp;
    JButton studentLogin;
    JTextField username;
    JButton confirm;
    JPasswordField passw;
    
    //creates constructor for the class to be called
    TeacherLogin(){
        
        //creates border object with the colour green which is added onto JLabels
        Border border = BorderFactory.createLineBorder(Color.green,5);
        
        //sets the title to Maths Ludo Game, ensures frame is closed when X is clicked and sets size of frame
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        
        //creates visibilityToggle button and configures its properties.
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(150, 290, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN,8));
        visibilityToggle.setBackground(red);
        //adding an actionlistener ensures when clicked, java will be able to listen to the click
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        //adds to the frame
        this.add(visibilityToggle);
        
        //creates username JTextField and configures properties
        username = new JTextField();
        username.setFont(new Font("MV Boli", Font.PLAIN,15));
        username.setBounds(150,200,500,30);
        //adds to frame
        this.add(username); 
        
        //creates passw JPasswordField and configures properties
        passw = new JPasswordField();
        passw.setFont(new Font("MV Boli", Font.PLAIN,15));
        passw.setBounds(150,250,500,30);
        //adds to frame
        this.add(passw);
        
        //creates JLabel backGround and configures properties
        JLabel backGround = new JLabel();
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setBackground(new Color(0x00FFFF));
        //uses border object to add border
        backGround.setBorder(border);
        backGround.setOpaque(true);
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setFont(new Font("MV Boli", Font.PLAIN,20));
        
        //creates JLabel topText and configures properties
        JLabel topText = new JLabel();
        topText.setText("Teacher Login Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN,50));
        topText.setBounds(250,-25,500,300);
        
        //creates JLabel usernameLabel and configures properties
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        usernameLabel.setBounds(50,163,100,100);
        
        //creates JLabel passwordLabel and configures properties
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        passwordLabel.setBounds(50,213,100,100);
        
        //creates signUp JButton and configures properties
        signUp = new JButton();
        signUp.setBounds(445,500,100,50);
        signUp.addActionListener(this);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("MV Boli", Font.PLAIN,15));
        signUp.setFocusable(false);
        signUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        //creates studentLogin button and configures properties
        studentLogin = new JButton();
        studentLogin.setBounds(330,330,330,75);
        studentLogin.setText("Student Login Page");
        studentLogin.setFocusable(false);
        studentLogin.setFont(new Font("MV Boli", Font.PLAIN,15));
        studentLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        studentLogin.addActionListener(this);
        
        //creates confirm button and configures properties
        confirm = new JButton();
        confirm.setBounds(700,220,100,50);
        confirm.addActionListener(this);
        confirm.setText("Confirm");
        confirm.setFont(new Font("MV Boli", Font.PLAIN,15));
        confirm.setFocusable(false);
        confirm.setBackground(red);
        
        //adds all components to the frame in a specific order to ensure it appears correct on the screen
        this.add(signUp);
        this.add(studentLogin);
        this.add(confirm);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(backGround);
        this.setResizable(false);
        //sets the frame to visible
        this.setVisible(true);
        
        }    
    
    //overriding the actionPerformed method defined in the ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //when visibilityToggle is clicked
        if (visibilityToggle.isSelected()) {
                    //sets the passw JTextField text to the actual string password
                    passw.setEchoChar((char) 0);
                } else {
                    //sets the passwJTextField text to asterixes to hide the password. This is the deafault
                    passw.setEchoChar('*'); 
                }
        
        //when signUp is clicked
         if(e.getSource()==signUp){
            //closes this frame and opens SignUpChoice frame in SignUpChoice class
            this.dispose();
            SignUpChoice window = new SignUpChoice(); 
            }
         
       //when studentLogin is clicked 
       if(e.getSource()==studentLogin){
            //closes frame and opens StudentLogin frame in StudentLogin class
            this.dispose();
            StudentLogin hi = new StudentLogin();
            
        }
        
        //creates string variables usern and password to store username and password from the JTextFields
        String usern = username.getText();
        String password = passw.getText();
        //creates instance of the HashAlg class
        HashAlg alg = new HashAlg();
        
        //try catch block to ensure program doesnt crash when hashing
        try {
            //hashes password using hash method
            password = alg.hash(password);
        } 
        //catches exception
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //if confirm button is clicked
        if(e.getSource()==confirm){
        
        //try catch block to catch exceptions    
        try{
            //connection to database is made using connection object
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root","Poop.");
            //preparedstatment is made 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select username, passw from teacher where username=? and passw=?");
            st.setString(1, usern);
            st.setString(2,password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Successfully logged in!");
                TeacherClassroom classroom = new TeacherClassroom(usern);
                dispose();
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect username/password please retry");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
*/