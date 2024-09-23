package com.mycompany.neamaybe;

// Import necessary libraries and classes
import static com.mycompany.neamaybe.TeacherSignUp.generateRandomNumber;
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

// Class definition for StudentSignUp, which extends JFrame and implements ActionListener
public class StudentSignUp extends JFrame implements ActionListener {

    // Method to check if a password is valid
    public static boolean isValidPassword(String password) {
        return password.length() >= 6 && containsCapitalLetter(password) && 
                containsSpecialCharacter(password);
    }

    // Method to check if a password contains at least one capital letter
    public static boolean containsCapitalLetter(String passwon) {
        for (char ch : passwon.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a password contains at least one special character
    public static boolean containsSpecialCharacter(String passwod) {
        String specialCharacters = "!@#$%^&*()-_+=<>?/{}[]|.";
        for (char ch : passwod.toCharArray()) {
            if (specialCharacters.indexOf(ch) != -1) {
                return true;
            }
        }
        return false;
    }

    // Declaration of class-level variables
    int realClassCodeEntered;
    int passwordChecker = 0;
    JButton submit;
    JButton teacherLogin;
    JTextField usern;
    JPasswordField passw;
    JTextField classc;
    int randomNumber;
    boolean stuidchecker = false;
    int low = 1;
    int up = 99999;
    boolean classchecker = false;
    JToggleButton visibilityToggle;

    // Constructor for the StudentSignUp class
    StudentSignUp() {

        // Initialization of GUI components
        visibilityToggle = new JToggleButton("Show password");
        visibilityToggle.setBounds(675, 250, 90, 30);
        visibilityToggle.setFont(new Font("MV Boli", Font.PLAIN, 8));
        visibilityToggle.setBackground(red);
        visibilityToggle.addActionListener(this);
        visibilityToggle.setFocusable(false);
        this.add(visibilityToggle);

        usern = new JTextField();
        usern.setFont(new Font("MV Boli", Font.PLAIN, 15));
        usern.setBounds(150, 200, 500, 30);
        this.add(usern);

        passw = new JPasswordField();
        passw.setFont(new Font("MV Boli", Font.PLAIN, 15));
        passw.setBounds(150, 250, 500, 30);
        this.add(passw);

        classc = new JTextField();
        classc.setFont(new Font("MV Boli", Font.PLAIN, 15));
        classc.setBounds(150, 300, 500, 30);
        this.add(classc);

        // Declaration and initialization of JLabel for background
        JLabel backGround = new JLabel();
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setOpaque(true);
        backGround.setBackground(new Color(0x00FFFF));
        backGround.setFont(new Font("MV Boli", Font.PLAIN, 20));

        // Declaration and initialization of JLabel for top text
        JLabel topText = new JLabel();
        topText.setText("Student Sign Up Page");
        topText.setFont(new Font("MV Boli", Font.PLAIN, 50));
        topText.setBounds(240, -25, 550, 300);

        // Declaration and initialization of JLabels for username, password, and class code
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

        // Declaration and initialization of Submit button
        submit = new JButton();
        submit.setBounds(350, 400, 300, 150);
        submit.addActionListener(this);
        submit.setText("Submit");
        submit.setFont(new Font("MV Boli", Font.PLAIN, 15));
        submit.setFocusable(false);
        submit.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        this.add(submit);

        // Declaration and initialization of Teacher Login button
        teacherLogin = new JButton();
        teacherLogin.setBounds(25, 600, 100, 50);
        teacherLogin.addActionListener(this);
        teacherLogin.setText("Back to initial page");
        teacherLogin.setFont(new Font("MV Boli", Font.PLAIN, 10));
        teacherLogin.setFocusable(false);
        teacherLogin.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        this.add(teacherLogin);

        // JFrame settings
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);
        this.add(topText);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(classcodeLabel);
        this.add(backGround);
        this.setVisible(true);

    }

    // ActionListener implementation for handling button clicks and actions
    @Override
    public void actionPerformed(ActionEvent e) {

        // Toggle password visibility
        if (visibilityToggle.isSelected()) {
            passw.setEchoChar((char) 0);
        } else {
            passw.setEchoChar('*');
        }

        // Retrieve user input values
        String userna = usern.getText();
        String passwo = passw.getText();
        String classcode = classc.getText();

        // Convert class code to integer (if valid)
        try {
            realClassCodeEntered = Integer.parseInt(classcode);
        } catch (NumberFormatException r) {
        }

        // Handle Teacher Login button click
        if (e.getSource() == teacherLogin) {
            // Close current window and open Teacher Login window
            this.dispose();
            TeacherLogin login = new TeacherLogin();
        }

        // Handle Submit button click
        if (e.getSource() == submit) {
            // Check password validity
            if (isValidPassword(passwo) && passwordChecker == 1) {

            } else if (isValidPassword(passwo)) {
                // Display message if password is valid
                JOptionPane.showMessageDialog(null, "Password is valid.");
                passwordChecker = 1;
            } else {
                // Display message if password is not valid
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

            // Proceed with sign-up if password is valid based on variable passwordChecker
            if (passwordChecker == 1) {
                try {
                    // Establish database connection
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                            "root", "Poopface891.");

                    // Check if username already exists
                    PreparedStatement sr = (PreparedStatement) connection
                            .prepareStatement("SELECT * FROM student WHERE username ='" + userna + "'");
                    ResultSet rs = sr.executeQuery();

                    // Display message if username already exists
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username already exists,"
                                + " please enter another one");
                    } else {
                        // Generate a random student ID and stay in loop until ID is unique
                        while (stuidchecker == false) {
                            //Generate using predefined variables, low and up
                            randomNumber = generateRandomNumber(low, up);
                            //Create executable sql query
                            PreparedStatement rind = (PreparedStatement) connection
                                    .prepareStatement("SELECT * FROM student WHERE studentid ='" + randomNumber + "'");
                            ResultSet m = rind.executeQuery();

                            // Check if the generated student ID already exists in database
                            if (m.next()) {
                                stuidchecker = false;
                            } else {
                                // Check if the entered class code exists
                                while (classchecker == false) {
                                    //Create executable sql query
                                    PreparedStatement select = (PreparedStatement) connection
                                            .prepareStatement("Select classcode from class where classcode=?");
                                    select.setInt(1, realClassCodeEntered);
                                    ResultSet fin = select.executeQuery();

                                    // If class code exists, retrieve teacher information
                                    if (fin.next()) {
                                        String query = "SELECT firstname,lastname FROM teacher,class WHERE"
                                                + " class.classcode=?"
                                                + " and class.teacherid=teacher.teacherid";
                                        PreparedStatement statement = connection.prepareStatement(query);
                                        statement.setInt(1, realClassCodeEntered);
                                        ResultSet resultSet = statement.executeQuery();

                                        // Display teacher's first name and last name
                                        if (resultSet.next()) {
                                            String firstName = resultSet.getString("firstname");
                                            String lastName = resultSet.getString("lastname");
                                            JOptionPane.showMessageDialog(null,
                                                    "Your Teachers First Name is: " + firstName
                                                            + " And Their Lastname is:" + " " + lastName);
                                        }
                                        // Class code exists and exit while loop and set variable classchecker to true
                                        classchecker = true;
                                    } else {
                                        // Class code doesn't exist and break out of while loop
                                        JOptionPane.showMessageDialog(null, "Class code doesn't exist,"
                                                + " try again");
                                        break;
                                    }
                                }

                                // If class code is valid, proceed with sign-up
                                if (classchecker == true) {
                                    // Hash the password
                                    HashAlg alg = new HashAlg();
                                    passwo = alg.hash(passwo);

                                    // Insert student information into the database
                                    PreparedStatement st = (PreparedStatement) connection
                                            .prepareStatement("INSERT INTO student(studentid,username,passw,classcode) "
                                                    + "VALUES('" + randomNumber + "','" + userna + "','" + passwo
                                                    + "','" + realClassCodeEntered + "')");
                                    st.executeUpdate();

                                    // Insert student ID into gamedata table and userskins table
                                    PreparedStatement stu = (PreparedStatement) connection
                                            .prepareStatement("INSERT INTO gamedata(studentid) VALUES('" + randomNumber
                                                    + "')");
                                    stu.executeUpdate();
                                    PreparedStatement stn = (PreparedStatement) connection.prepareStatement(
                                            "INSERT INTO userskins(studentid) VALUES('" + randomNumber + "')");
                                    stn.executeUpdate();

                                    // Display success message
                                    JOptionPane.showMessageDialog(null,
                                            "Sign Up successful! Please log in from the menu");

                                    // Close current window and open Student Login window
                                    this.dispose();
                                    StudentLogin log = new StudentLogin();
                                    stuidchecker = true;
                                } else {
                                    // Break out of the loop if class code is not valid
                                    break;
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(StudentSignUp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(StudentSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}


/*
package com.mycompany.neamaybe;
import static com.mycompany.neamaybe.TeacherSignUp.generateRandomNumber;
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


public class StudentSignUp extends JFrame implements ActionListener{
    
    public static boolean isValidPassword(String password) {
        return password.length() >= 6 && containsCapitalLetter(password) && containsSpecialCharacter(password);
    }
    
    public static boolean containsCapitalLetter(String passwon) {
        for (char ch : passwon.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsSpecialCharacter(String passwod) {
        String specialCharacters = "!@#$%^&*()-_+=<>?/{}[]|.";
        for (char ch : passwod.toCharArray()) {
            if (specialCharacters.indexOf(ch) != -1) {
                return true;
            }
        }
        return false;
    }
    
        int realClassCodeEntered;
        int passwordChecker = 0;
        JButton submit;
        JButton teacherLogin;
        JTextField usern;
        JPasswordField passw;
        JTextField classc;
        int randomNumber;
        boolean stuidchecker = false;
        int low = 1;
        int up = 99999;
        boolean classchecker = false;
        JToggleButton visibilityToggle;
        
        
        StudentSignUp(){
            
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
        
        classc = new JTextField();
        classc.setFont(new Font("MV Boli", Font.PLAIN,15));
        classc.setBounds(150,300,500,30);
        this.add(classc);
        
        JLabel backGround = new JLabel();
        backGround.setText("Welcome to the maths ludo revision game");
        backGround.setVerticalAlignment(JLabel.TOP);
        backGround.setHorizontalAlignment(JLabel.CENTER);
        backGround.setOpaque(true);
        backGround.setBackground(new Color(0x00FFFF));
        backGround.setFont(new Font("MV Boli", Font.PLAIN,20));
        
        JLabel topText = new JLabel();
        topText.setText("Student Sign Up Page");
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
        
        JLabel classcodeLabel = new JLabel();
        classcodeLabel.setText("Classcode");
        classcodeLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        classcodeLabel.setBounds(50,263,1000,100);
        
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
         
        String userna = usern.getText();
        String passwo = passw.getText();
        String classcode = classc.getText();
        
       try{
        realClassCodeEntered = Integer.parseInt(classcode);
       }
       catch(NumberFormatException r){
       }
        
        if(e.getSource() == teacherLogin){
            this.dispose();
            TeacherLogin login = new TeacherLogin();
            }
        
        if(e.getSource() == submit){
            
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
            
            if(passwordChecker == 1){
                
            try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea","root","Poopface891.");
            PreparedStatement sr = (PreparedStatement) connection.prepareStatement("SELECT * FROM student WHERE username ='" +userna+"'");
            ResultSet rs = sr.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Username already exists, please enter another one");
                }
            
            else{
                while(stuidchecker == false){
                    randomNumber = generateRandomNumber(low,up);
                    PreparedStatement rind = (PreparedStatement) connection.prepareStatement("SELECT * FROM student WHERE studentid ='" +randomNumber+"'");
                    ResultSet m = rind.executeQuery();
                    if(m.next()){
                        stuidchecker = false;
                    }
                    else{
                        while(classchecker == false){
                            PreparedStatement select = (PreparedStatement) connection.prepareStatement("Select classcode from class where classcode=?");
                            select.setInt(1, realClassCodeEntered);
                            ResultSet fin = select.executeQuery();
                            if(fin.next()){
                                String query = "SELECT firstname,lastname FROM teacher,class WHERE class.classcode=? and class.teacherid=teacher.teacherid";
                                PreparedStatement statement = connection.prepareStatement(query);
                                statement.setInt(1, realClassCodeEntered);
                                ResultSet resultSet = statement.executeQuery();

                                if (resultSet.next()) {
                                 String firstName = resultSet.getString("firstname");
                                 String lastName = resultSet.getString("lastname");
                                   JOptionPane.showMessageDialog(null, "Your Teachers First Name is: " + firstName + " And Their Lastname is:"
                                           + " " + lastName);
                                   }
                                //its there
                                classchecker = true;
                                }
                            else{
                                //not there
                                JOptionPane.showMessageDialog(null,"Classcode doesnt exist, try again");
                                break;
                               }
                           }
                        if(classchecker == true){
                        HashAlg alg = new HashAlg();
                        passwo = alg.hash(passwo);
                        PreparedStatement st = (PreparedStatement) connection.prepareStatement("INSERT INTO student(studentid,username,passw,classcode) "
                                + "VALUES('"+randomNumber+"','"+userna+"','"+passwo+"','"+realClassCodeEntered+"')");
                        st.executeUpdate();
                        PreparedStatement stu = (PreparedStatement) connection.prepareStatement("INSERT INTO gamedata(studentid) VALUES('"+randomNumber+"')");
                        stu.executeUpdate();
                        PreparedStatement stn = (PreparedStatement) connection.prepareStatement("INSERT INTO userskins(studentid) VALUES('"+randomNumber+"')");
                        stn.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Sign Up successful! Please log in from menu");
                        this.dispose();
                        StudentLogin log = new StudentLogin();
                        stuidchecker = true;
                        }
                        else{
                            break;
                        }
                     }
                 }
                
                
                }
      }   catch (SQLException ex) {
                Logger.getLogger(StudentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(StudentSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
    }
}

    
}
 */       
        
        