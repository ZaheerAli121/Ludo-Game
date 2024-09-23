
package com.mycompany.neamaybe;

//Import necessary java classes
import java.awt.Color;
import static java.awt.Color.red;
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

/**
 * StudentLogin class represents the user interface for student login.
 * It inherits from JFrame and implements ActionListener for button events.
 */
public class StudentLogin extends JFrame implements ActionListener {

    // UI components declaration
    JButton signUp;
    JButton teacherLogin;
    JButton confirm;
    JTextField usern;
    JPasswordField passw;
    JTextField classcode;
    int realcode;
    JToggleButton visibilityToggle;

    //Constructor for initializing the StudentLogin user interface.
    StudentLogin() {

        // Create and configure the visibility toggle button
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(675, 250, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN, 8));
        visibilityToggle.setBackground(red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        this.add(visibilityToggle);

        // Create and configure the username JTextField
        usern = new JTextField();
        usern.setFont(new Font("MV Boli", Font.PLAIN, 15));
        usern.setBounds(150, 200, 500, 30);
        this.add(usern);

        // Create and configure the password JPasswordField
        passw = new JPasswordField();
        passw.setFont(new Font("MV Boli", Font.PLAIN, 15));
        passw.setBounds(150, 250, 500, 30);
        this.add(passw);

        // Create and configure the class code JTextField
        classcode = new JTextField();
        classcode.setFont((new Font("MV Boli", Font.PLAIN, 15)));
        classcode.setBounds(150, 300, 500, 30);
        this.add(classcode);

        // Create and configure the background JLabel
        JLabel backGround = new JLabel();
        backGround.setBackground(new Color(0x00FFFF));
        backGround.setFont(new Font("MV Boli", Font.PLAIN, 20));
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setOpaque(true);

        // Create and configure the top text JLabel
        JLabel topText = new JLabel();
        topText.setText("Student Login Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN, 50));
        topText.setBounds(270, -25, 500, 300);

        // Create and configure JLabels for username, password, and class code
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernameLabel.setBounds(50, 163, 100, 100);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 213, 100, 100);

        JLabel classcodeLabel = new JLabel();
        classcodeLabel.setText("Classcode");
        classcodeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        classcodeLabel.setBounds(50, 263, 1000, 100);

        // Create and configure buttons for Sign Up, Teacher Login, and Confirm
        signUp = new JButton();
        signUp.setBounds(445, 500, 100, 50);
        signUp.addActionListener(this);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("MV Boli", Font.PLAIN, 15));
        signUp.setFocusable(false);
        signUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));

        teacherLogin = new JButton();
        teacherLogin.setBounds(330, 400, 330, 75);
        teacherLogin.setText("Back To Teacher Login Page");
        teacherLogin.setFocusable(false);
        teacherLogin.setFont(new Font("MV Boli", Font.PLAIN, 15));
        teacherLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        teacherLogin.addActionListener(this);

        confirm = new JButton();
        confirm.setBounds(150, 350, 100, 50);
        confirm.addActionListener(this);
        confirm.setText("Confirm");
        confirm.setFont(new Font("MV Boli", Font.PLAIN, 15));
        confirm.setFocusable(false);
        confirm.setBackground(red);

        // Set JFrame properties
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);

        // Add components to the frame
        this.add(signUp);
        this.add(teacherLogin);
        this.add(confirm);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(classcodeLabel);
        this.add(backGround);
        this.setVisible(true);
    }

    
    //Override actionPerformed method to handle button events.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Toggle password visibility
        if (visibilityToggle.isSelected()) {
            passw.setEchoChar((char) 0);
        } else {
            passw.setEchoChar('*');
        }

        // Handle Sign Up button click
        if (e.getSource() == signUp) {
            //Closes frame and opens SignUpChoice frame from SignUpChoice class
            this.dispose();
            SignUpChoice signup = new SignUpChoice();
        }

        // Handle Teacher Login button click
        if (e.getSource() == teacherLogin) {
            //Closes frame and opens TeacherLogin frame from TeacherLogin class
            this.dispose();
            TeacherLogin login = new TeacherLogin();
        }

        // Get input values from JTextFields
        String userna = usern.getText();
        String password = passw.getText();
        String classcu = classcode.getText();
        HashAlg alg = new HashAlg();

        // Hash the password
        try {
            password = alg.hash(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StudentLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Parse class code to integer
        try {
            realcode = Integer.parseInt(classcu);
        } catch (NumberFormatException r) {
        }

        // Handle Confirm button click
        if (e.getSource() == confirm) {
            try {
                // Establish a database connection
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root", "Poopface891.");

                // Prepare and execute a SQL query
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select username,"
                        + " passw,classcode from student"
                        + " where username=? and passw=? "
                        + "and classcode=?");
                st.setString(1, userna);
                st.setString(2, password);
                st.setInt(3, realcode);
                ResultSet rs = st.executeQuery();

                // Check if the login is successful
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Successfully logged in!");
                    //Close frame and open MainMenu frame from MainMenu class
                    this.dispose();
                    MainMenu menu = new MainMenu(userna);
                } else {
                    //Display message if username/password is incorrect
                    JOptionPane.showMessageDialog(null, "Incorrect username/password please retry");
                }

            } catch (SQLException ex) {
                Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*
public class StudentLogin extends JFrame implements ActionListener{
    
    JButton signUp;
    JButton teacherLogin;
    JButton confirm;
    JTextField usern;
    JPasswordField passw;
    JTextField classcode;
    int realcode;
    JToggleButton visibilityToggle;
    
    StudentLogin(){
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(675, 250, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN,8));
        visibilityToggle.setBackground(red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false); 
        this.add(visibilityToggle);
        
        usern = new JTextField();
        usern.setFont(new Font("MV Boli", Font.PLAIN,15));
        usern.setBounds(150,200,500,30);
        this.add(usern);
        
        passw = new JPasswordField();
        passw.setFont(new Font("MV Boli", Font.PLAIN,15));
        passw.setBounds(150,250,500,30);
        this.add(passw);
        
        classcode = new JTextField();
        classcode.setFont((new Font("MV Boli", Font.PLAIN,15)));
        classcode.setBounds(150,300,500,30);
        this.add(classcode);
        
        JLabel backGround = new JLabel();
        backGround.setBackground(new Color(0x00FFFF));
        backGround.setFont(new Font("MV Boli", Font.PLAIN,20));
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setOpaque(true);
        
        JLabel topText = new JLabel();
        topText.setText("Student Login Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN,50));
        topText.setBounds(270,-25,500,300);
        
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        usernameLabel.setBounds(50,163,100,100);
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        passwordLabel.setBounds(50,213,100,100);
        
        JLabel classcodeLabel = new JLabel();
        classcodeLabel.setText("Classcode");
        classcodeLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        classcodeLabel.setBounds(50,263,1000,100);
        
        signUp = new JButton();
        signUp.setBounds(445,500,100,50);
        signUp.addActionListener(this);
        signUp.setText("Sign Up");
        signUp.setFont(new Font("MV Boli", Font.PLAIN,15));
        signUp.setFocusable(false);
        signUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        teacherLogin = new JButton();
        teacherLogin.setBounds(330,400,330,75);
        teacherLogin.setText("Back To Teacher Login Page");
        teacherLogin.setFocusable(false);
        teacherLogin.setFont(new Font("MV Boli", Font.PLAIN,15));
        teacherLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        teacherLogin.addActionListener(this);
        
        confirm = new JButton();
        confirm.setBounds(150,350,100,50);
        confirm.addActionListener(this);
        confirm.setText("Confirm");
        confirm.setFont(new Font("MV Boli", Font.PLAIN,15));
        confirm.setFocusable(false);
        confirm.setBackground(red);
        
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setResizable(false);
        this.add(signUp);
        this.add(teacherLogin);
        this.add(confirm);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(classcodeLabel);
        this.add(backGround);
        this.setVisible(true);
           
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if (visibilityToggle.isSelected()) {
                    passw.setEchoChar((char) 0);
                } else {
                    passw.setEchoChar('*'); 
                }
         
         
         if(e.getSource()==signUp){
            this.dispose();
            SignUpChoice signup = new SignUpChoice();  
            }
         
         if(e.getSource()==teacherLogin){
            this.dispose();
            TeacherLogin login = new TeacherLogin();
            }
         
         
        String userna = usern.getText();
        String password = passw.getText();
        String classcu = classcode.getText();
        HashAlg alg = new HashAlg();
        
        try {
            password = alg.hash(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StudentLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
        realcode = Integer.parseInt(classcu);
        }
        catch(NumberFormatException r){
        }
        
        if(e.getSource()==confirm){
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select username, passw,classcode from student where username=? and passw=? "
                    + "and classcode=?");
            st.setString(1, userna);
            st.setString(2,password);
            st.setInt(3, realcode);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Successfully logged in!");
                this.dispose();
                MainMenu menu = new MainMenu(userna);
                
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