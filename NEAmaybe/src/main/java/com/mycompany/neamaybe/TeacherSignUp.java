// Import statements for necessary Java classes and dependencies
package com.mycompany.neamaybe;
import static com.mycompany.neamaybe.StudentSignUp.containsCapitalLetter;
import static com.mycompany.neamaybe.StudentSignUp.containsSpecialCharacter;
import static com.mycompany.neamaybe.StudentSignUp.isValidPassword;
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
import java.util.Random;
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

// The main class representing the TeacherSignUp window, extending JFrame and implementing ActionListener
public class TeacherSignUp extends JFrame implements ActionListener {

    // Function to generate a random number within a given range
    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        int range = upperBound - lowerBound + 1;
        return lowerBound + random.nextInt(range);
    }

    // Variables declaration
    int passwordChecker = 0; // Flag to check if password validation has already occurred
    int lowerBound = 100000; // Lower bound for generating random numbers
    int upperBound = 1000000; // Upper bound for generating random numbers
    int low = 1; // Lower bound for generating unique teacher IDs
    int up = 99999; // Upper bound for generating unique teacher IDs
    boolean checker = false; // Flag to check if a unique class code has been generated
    boolean teachidchecker = false; // Flag to check if a unique teacher ID has been generated
    int rando; // Variable to store a random number
    JButton submit; // Button to submit the teacher's information
    JButton teacherLogin; // Button to go back to the initial page
    JTextField userName; // Text field for entering the username
    JToggleButton visibilityToggle; // Toggle button to show/hide the password
    JTextField firstName; // Text field for entering the first name
    JTextField lastName; // Text field for entering the last name
    JPasswordField passWord; // Password field for entering the password

    // Constructor
    TeacherSignUp() {

        // GUI components initialization
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(675, 250, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN,8));
        visibilityToggle.setBackground(red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        this.add(visibilityToggle);
        
        userName = new JTextField();
        userName.setFont(new Font("MV Boli", Font.PLAIN,15));
        userName.setBounds(150,200,500,30);
        this.add(userName);
        
        passWord = new JPasswordField();
        passWord.setFont(new Font("MV Boli", Font.PLAIN,15));
        passWord.setBounds(150,250,500,30);
        this.add(passWord);
        
        firstName = new JTextField();
        firstName.setFont(new Font("MV Boli", Font.PLAIN,15));
        firstName.setBounds(150,300,500,30);
        this.add(firstName);
        
        lastName = new JTextField();
        lastName.setFont(new Font("MV Boli", Font.PLAIN,15));
        lastName.setBounds(150,350,500,30);
        this.add(lastName);
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setText("Welcome to the maths ludo revision game");
        backgroundLabel.setVerticalAlignment(JLabel.TOP);
        backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        
        JLabel topText = new JLabel();
        topText.setText("Teacher Sign Up Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN,50));
        topText.setBounds(240,-25,550,300);
        
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        usernameLabel.setBounds(50,163,100,100);
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        passwordLabel.setBounds(50,213,100,100);
        
        JLabel firstName = new JLabel();
        firstName.setText("First Name");
        firstName.setFont(new Font("MV Boli", Font.PLAIN,20));
        firstName.setBounds(25,263,1000,100);
        
        JLabel lastName = new JLabel();
        lastName.setText("Last Name");
        lastName.setFont(new Font("MV Boli", Font.PLAIN,20));
        lastName.setBounds(25,313,1000,100);
        
        submit = new JButton();
        submit.setBounds(350,400,300,150);
        submit.addActionListener(this);
        submit.setText("Submit");
        submit.setFont(new Font("MV Boli", Font.PLAIN,15));
        submit.setFocusable(false);
        submit.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        this.add(submit);
        
        teacherLogin = new JButton();
        teacherLogin.setBounds(25,600,100,50);
        teacherLogin.addActionListener(this);
        teacherLogin.setText("Back to inital page");
        teacherLogin.setFont(new Font("MV Boli", Font.PLAIN,10));
        teacherLogin.setFocusable(false);
        teacherLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        this.add(teacherLogin);
        
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setResizable(false);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(firstName);
        this.add(lastName);
        this.add(backgroundLabel);
        this.setVisible(true);

    }

    // Action event handling
    @Override
    public void actionPerformed(ActionEvent e) {

        // Toggle password visibility
        if (visibilityToggle.isSelected()) {
            passWord.setEchoChar((char) 0);
        } else {
            passWord.setEchoChar('*');
        }

        // Action when Back button is clicked
        if (e.getSource() == teacherLogin) {
            this.dispose();
            TeacherLogin login = new TeacherLogin(); // Redirect to the TeacherLogin window
        }

        // Getting input values
        String userna = userName.getText();
        String passwo = passWord.getText();
        String first = firstName.getText();
        String last = lastName.getText();

        // Action when Submit button is clicked
        if (e.getSource() == submit) {
            // Password validation
            if (isValidPassword(passwo) && passwordChecker == 1) {
                // Additional actions if needed for a valid password
            } else if (isValidPassword(passwo)) {
                // Display a message for a valid password
                JOptionPane.showMessageDialog(null, "Password is valid."); 
                passwordChecker = 1;
            } else {
                // Display detailed password validation error message
                String message = "Password is not valid. It should have:\n";
                if (passwo.length() < 6) {
                    message += "- At least 6 characters\n";
                }
                if (!containsCapitalLetter(passwo)) {
                    message += "- At least one capital letter\n";
                }
                if (!containsSpecialCharacter(passwo)) {
                    message += "- At least one special character\n";
                }
                JOptionPane.showMessageDialog(null, message);
            }

            // If password is valid, proceed with database operations
            if (passwordChecker == 1) {
                try {
                    // Establish a connection to the MySQL database
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                            "root", "Poopface891.");

                    // Check if the entered username already exists in the database
                    PreparedStatement sr = (PreparedStatement) connection
                            .prepareStatement("SELECT * FROM teacher WHERE username ='" + userna + "'");
                    ResultSet rs = sr.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username already exists, please enter another "
                                + "one");
                    } else {
                        // Generate a unique teacher ID
                        while (teachidchecker == false) {
                            rando = generateRandomNumber(low, up);
                            PreparedStatement rind = (PreparedStatement) connection
                                    .prepareStatement("SELECT * FROM teacher WHERE teacherid ='" + rando + "'");
                            ResultSet m = rind.executeQuery();
                            if (m.next()) {
                                teachidchecker = false;
                            } else {
                                // Hash the password and insert teacher details into the database
                                HashAlg alg = new HashAlg();
                                passwo = alg.hash(passwo);
                                teachidchecker = true;
                                PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                                        "INSERT INTO teacher(teacherid,username,passw,firstname," + "lastname) VALUES('"
                                                + rando + "','" + userna + "','" + passwo + "','" + first + "','"
                                                + last + "')");
                                st.executeUpdate();
                            }
                        }
                        // Generate a unique class code
                        while (checker == false) {
                            int randomNumber = generateRandomNumber(lowerBound, upperBound);
                            PreparedStatement r = (PreparedStatement) connection
                                    .prepareStatement("SELECT * FROM class WHERE classcode ='" + randomNumber + "'");
                            ResultSet s = r.executeQuery();
                            if (s.next()) {
                                checker = false;
                            } else {
                                // Insert class details into the database
                                PreparedStatement insertion = (PreparedStatement) connection
                                        .prepareStatement("INSERT INTO class(classcode,teacherid) "
                                                + "VALUES('" + randomNumber + "' , '" + rando + "')");
                                insertion.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Your classcode is: " + randomNumber);
                                checker = true;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "You have signed up, please log in from the menu");
                        this.dispose();
                        TeacherLogin login = new TeacherLogin(); // Redirect to the TeacherLogin window
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherSignUp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TeacherSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Nothing happens
            }
        }
    }
}






/*
package com.mycompany.neamaybe;
import static com.mycompany.neamaybe.StudentSignUp.containsCapitalLetter;
import static com.mycompany.neamaybe.StudentSignUp.containsSpecialCharacter;
import static com.mycompany.neamaybe.StudentSignUp.isValidPassword;
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
import java.util.Random;
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


public class TeacherSignUp extends JFrame implements ActionListener {
    
    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        int range = upperBound - lowerBound + 1;
        return lowerBound + random.nextInt(range);
    }
    
    
    int passwordChecker = 0;
    int lowerBound = 100000;
    int upperBound = 1000000;
    int low = 1;
    int up = 99999;
    boolean checker = false;
    boolean teachidchecker = false;
    int rando;
    JButton submit;
    JButton teacherLogin;
    JTextField userName;
    JToggleButton visibilityToggle;
    JTextField firstName;
    JTextField lastName;
    JPasswordField passWord;
    
     TeacherSignUp(){
         
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(675, 250, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN,8));
        visibilityToggle.setBackground(red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        this.add(visibilityToggle);
        
        userName = new JTextField();
        userName.setFont(new Font("MV Boli", Font.PLAIN,15));
        userName.setBounds(150,200,500,30);
        this.add(userName);
        
        passWord = new JPasswordField();
        passWord.setFont(new Font("MV Boli", Font.PLAIN,15));
        passWord.setBounds(150,250,500,30);
        this.add(passWord);
        
        firstName = new JTextField();
        firstName.setFont(new Font("MV Boli", Font.PLAIN,15));
        firstName.setBounds(150,300,500,30);
        this.add(firstName);
        
        lastName = new JTextField();
        lastName.setFont(new Font("MV Boli", Font.PLAIN,15));
        lastName.setBounds(150,350,500,30);
        this.add(lastName);
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setText("Welcome to the maths ludo revision game");
        backgroundLabel.setVerticalAlignment(JLabel.TOP);
        backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        
        JLabel topText = new JLabel();
        topText.setText("Teacher Sign Up Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN,50));
        topText.setBounds(240,-25,550,300);
        
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        usernameLabel.setBounds(50,163,100,100);
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        passwordLabel.setBounds(50,213,100,100);
        
        JLabel firstName = new JLabel();
        firstName.setText("First Name");
        firstName.setFont(new Font("MV Boli", Font.PLAIN,20));
        firstName.setBounds(25,263,1000,100);
        
        JLabel lastName = new JLabel();
        lastName.setText("Last Name");
        lastName.setFont(new Font("MV Boli", Font.PLAIN,20));
        lastName.setBounds(25,313,1000,100);
        
        submit = new JButton();
        submit.setBounds(350,400,300,150);
        submit.addActionListener(this);
        submit.setText("Submit");
        submit.setFont(new Font("MV Boli", Font.PLAIN,15));
        submit.setFocusable(false);
        submit.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        this.add(submit);
        
        teacherLogin = new JButton();
        teacherLogin.setBounds(25,600,100,50);
        teacherLogin.addActionListener(this);
        teacherLogin.setText("Back to inital page");
        teacherLogin.setFont(new Font("MV Boli", Font.PLAIN,10));
        teacherLogin.setFocusable(false);
        teacherLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        this.add(teacherLogin);
        
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setResizable(false);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(firstName);
        this.add(lastName);
        this.add(backgroundLabel);
        this.setVisible(true);
        
        }
     
     
     
    @Override
    public void actionPerformed(ActionEvent e) {
        
         if (visibilityToggle.isSelected()) {
                    passWord.setEchoChar((char) 0);
                } else {
                    passWord.setEchoChar('*'); 
                }
        if(e.getSource() == teacherLogin){
            this.dispose();
            TeacherLogin login = new TeacherLogin();
        }
        
        String userna = userName.getText();
        String passwo = passWord.getText();
        String first = firstName.getText();
        String last = lastName.getText();
        
        if(e.getSource()==submit){
            if (isValidPassword(passwo)&& passwordChecker==1) {
                
            }
            else if(isValidPassword(passwo)){
            JOptionPane.showMessageDialog(null, "Password is valid.");
            passwordChecker = 1;
            }
         else {
            String message = "Password is not valid. It should have:\n";
            if (passwo.length() < 6) {
                message += "- At least 6 characters\n";
            }
            if (!containsCapitalLetter(passwo)) {
                message += "- At least one capital letter\n";
            }
            if (!containsSpecialCharacter(passwo)) {
                message += "- At least one special character\n";
            }
            JOptionPane.showMessageDialog(null, message);
           
        }
            
        if(passwordChecker ==1){
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root",".");
            PreparedStatement sr = (PreparedStatement) connection.prepareStatement("SELECT * FROM teacher WHERE username ='" +userna+"'");
            ResultSet rs = sr.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Username already exists, please enter another one");
                }
            else{
                while(teachidchecker == false){
                    rando = generateRandomNumber(low,up);
                    PreparedStatement rind = (PreparedStatement) connection.prepareStatement("SELECT * FROM teacher WHERE teacherid ='" +rando+"'");
                    ResultSet m = rind.executeQuery();
                    if(m.next()){
                        teachidchecker = false;
                    }
                    else{
                        HashAlg alg = new HashAlg();
                        passwo = alg.hash(passwo);
                        teachidchecker = true;
                        PreparedStatement st = (PreparedStatement) connection.prepareStatement("INSERT INTO teacher(teacherid,username,passw,firstname,"
                                + "lastname) VALUES('"+rando+"','"+userna+"','"+passwo+"','"+first+"','"+last+"')");
                        st.executeUpdate();
                    }
                }
                while(checker == false){
                int randomNumber = generateRandomNumber(lowerBound, upperBound);
                PreparedStatement r = (PreparedStatement) connection.prepareStatement("SELECT * FROM class WHERE classcode ='" +randomNumber+"'");
                ResultSet s = r.executeQuery();
                if(s.next()){
                    checker = false;
                }
                else{
                    PreparedStatement insertion = (PreparedStatement) connection.prepareStatement("INSERT INTO class(classcode,teacherid) "
                            + "VALUES('"+randomNumber+"' , '"+rando+"')");
                    insertion.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Your classcode is: " + randomNumber);
                     checker = true;
                     }
                }
                JOptionPane.showMessageDialog(null, "You have signed up, please log in from menu");
                this.dispose();
                TeacherLogin login = new TeacherLogin();
            }
            } catch (SQLException ex) {
            Logger.getLogger(TeacherSignUp.class.getName()).log(Level.SEVERE, null, ex);
        }       catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TeacherSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else{
            
        }
        }
}
}
    
*/