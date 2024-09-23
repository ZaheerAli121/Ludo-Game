// Import statements for necessary Java classes and dependencies
package com.mycompany.neamaybe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

// The main class representing the TeacherClassroom window, extending JFrame and implementing ActionListener
public class TeacherClassroom extends JFrame implements ActionListener {

    // Member variables
    private String usernam; // Username of the teacher
    private JPanel leaderboardPanel; // Panel to display the student leaderboard
    int l = 1; // Counter for student rankings
    int classgoalScore; // Goal score for the class
    int currentClassgoalScore; // Current total score of the class
    JLabel studentScoreLabel; // Label to display student scores
    JButton confirmButton; // Button to confirm class goal update
    JLabel studentUsernameLabel; // Label to display student usernames
    JLabel classgoal; // Label to display the class goal
    JTextField goal; // Text field for entering/updating the class goal
    JButton change = new JButton("Update Classgoal"); // Button to initiate class goal update
    JProgressBar progress; // Progress bar displaying class score progress
    JLabel classgoalLabel;
    int classGoalInt; // Integer representation of the class goal
    int classcodeV;
    // Constructor
    public TeacherClassroom(String username) {
        
        this.usernam=username;
        // GUI components initialization
        confirmButton = new JButton("Confirm");
        confirmButton.setVisible(false);
        confirmButton.addActionListener(this);
        
        classgoalLabel = new JLabel();
        classgoalLabel.setBounds(780, 800, 200, 60);
        classgoalLabel.setForeground(Color.WHITE);
        try{
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root", "Poopface891.");

            // SQL query to retrieve the class goal for the teacher
            String q = "SELECT class.classcode from class, teacher where teacher.username = ? and teacher.teacherid"
                    + " = class.teacherid";
            PreparedStatement preparedStatemen = connection.prepareStatement(q);
            preparedStatemen.setString(1, usernam);
            ResultSet resultSe = preparedStatemen.executeQuery();
            if(resultSe.next()){
                classcodeV = resultSe.getInt("classcode");
                
            }
            classgoalLabel.setText("Your classcode is " + classcodeV);
            
        }
        catch(Exception j){
            
        }
        classgoal = new JLabel();
        
        goal = new JTextField();
        goal.setVisible(false);
        this.add(goal);
        
        progress = new JProgressBar();
        
        change.setVisible(false);
        
        JLabel studentLeaderboardHeading = new JLabel("Student Leaderboard:");
        studentLeaderboardHeading.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentLeaderboardHeading.setBounds(335,30,300,20);
        
        JLabel topText = new JLabel("Teacher class menu");
        topText.setFont(new Font("Mv Boli", Font.BOLD,25));
        topText.setBounds(400,10,300,20);
        
        studentScoreLabel = new JLabel();
        studentScoreLabel.setBounds(485,50,150,900);
        studentScoreLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentScoreLabel.setBackground(Color.RED);
        studentScoreLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        studentScoreLabel.setLayout(new BoxLayout(studentScoreLabel, BoxLayout.Y_AXIS));
        studentScoreLabel.setOpaque(true);
        
        studentUsernameLabel = new JLabel();
        studentUsernameLabel.setBounds(335,50,150,900);
        studentUsernameLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentUsernameLabel.setBackground(Color.RED);
        studentUsernameLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.green));
        studentUsernameLabel.setLayout(new BoxLayout(studentUsernameLabel, BoxLayout.Y_AXIS));
        studentUsernameLabel.setOpaque(true);
        
        leaderboardPanel = new JPanel(){
                BufferedImage backgroundImage;
                {
                    try {
                        backgroundImage = ImageIO.read(new File("C:\\Users\\Zaheer\\Desktop\\wrGnD8.jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            };
        
        leaderboardPanel.setBackground(Color.WHITE);
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        
        this.add(studentUsernameLabel);
        this.add(change);
        this.add(studentScoreLabel);
        this.add(progress);
        this.add(topText);
        this.add(studentLeaderboardHeading);
        this.add(classgoal);
        this.add(confirmButton);
        this.add(classgoalLabel);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,900);
        this.setResizable(false);
        setLayout(new BorderLayout());                                         
        this.add(leaderboardPanel, BorderLayout.CENTER);               
        this.setVisible(true);
        
        // Calls method to display leaderboard
        fetchAndDisplayLeaderboard();
        
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root", "Poopface891.");

            // SQL query to retrieve the class goal for the teacher
            String q = "SELECT class.classgoal from class, teacher where teacher.username = ? and teacher.teacherid"
                    + " = class.teacherid";
            PreparedStatement preparedStatemen = connection.prepareStatement(q);
            preparedStatemen.setString(1, usernam);
            ResultSet resultSe = preparedStatemen.executeQuery();

            // Check if the class goal exists for the teacher
            if (resultSe.next()) {
                classgoalScore = resultSe.getInt("classgoal");

            // Check if the class goal is not zero
            if (classgoalScore != 0) {
                currentClassgoalScore = 0;

                // SQL query to calculate the current total score of the class
                String m = "select score from class, teacher, student, gamedata where teacher.username = ? and "
                    + "teacher.teacherid=class.teacherid and class.classcode = student.classcode and"
                        + " student.studentid = gamedata.studentid";
                PreparedStatement preparedStatement = connection.prepareStatement(m);
                preparedStatement.setString(1, usernam);
                ResultSet rs = preparedStatement.executeQuery();

                // Calculate the current total score of the class
                while (rs.next()) {
                    currentClassgoalScore = currentClassgoalScore + rs.getInt("score");
                }

                // Update the progress bar based on the current class score
                progress.setMaximum(classgoalScore);
                progress.setValue(currentClassgoalScore);
                progress.setStringPainted(true);
                progress.setString(currentClassgoalScore + "/" + classgoalScore);
                progress.setBounds(0, 330, 300, 30);
                progress.setFont(new Font("Mv Boli", Font.BOLD, 15));
                progress.setBackground(Color.RED);
                progress.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                progress.setOpaque(false);

                // Display the class goal information
                classgoal.setText("Reach score of " + classgoalScore);
                classgoal.setBounds(0, 300, 300, 30);
                classgoal.setFont(new Font("Mv Boli", Font.BOLD, 15));
                classgoal.setBackground(Color.RED);
                classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                classgoal.setOpaque(true);

                // Configure the "Update Classgoal" button
                change.setFont((new Font("Mv Boli", Font.BOLD, 15)));
                change.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                change.setBounds(0, 360, 300, 30);
                change.setVisible(true);
                change.addActionListener(this);
            } 
            else {
                // Display components when there is no class goal
                classgoal.setText("No classgoal. Please enter a score");
                classgoal.setBounds(0, 300, 300, 30);
                classgoal.setFont(new Font("Mv Boli", Font.BOLD, 15));
                classgoal.setBackground(Color.RED);
                classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                classgoal.setOpaque(true);

                goal.setBounds(0, 330, 300, 30);
                goal.setVisible(true);
                goal.setFont(new Font("Mv Boli", Font.BOLD, 15));
                goal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));

                confirmButton.setVisible(true);
                confirmButton.setBounds(0, 360, 100, 30);
                confirmButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
        }
    }

        
        } 
    catch (SQLException e) {
    } 
  }

    // Fetches and displays the student leaderboard
    private void fetchAndDisplayLeaderboard() {
        List<Student> students = new ArrayList<>();
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root", "Poopface891.");
            
            // SQL query to retrieve student usernames and scores
            String query = "SELECT student.username, gamedata.score FROM student,gamedata,teacher,class WHERE"
                    + " teacher.username = ? "
                    + "and teacher.teacherid=class.teacherid and class.classcode = student.classcode and"
                    + " student.studentid=gamedata.studentid";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernam);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Process the query results
            if(resultSet.next()) {
                String usern = resultSet.getString("username");
                int scorec = resultSet.getInt("score");
                students.add(new Student(usern,scorec));
                System.out.println("hi");
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    int score = resultSet.getInt("score");
                    students.add(new Student(username, score));
                    System.out.println("hiya");
                }

                // Sort students based on their scores in descending order
                Collections.sort(students, Comparator.comparingInt(Student::getScore).reversed());

                // Display student information on the GUI
                for (Student student : students) {
                    JLabel tempUsernameLabel = new JLabel(l + "......." + student.getUsername() + "..............");
                    JLabel tempScoreLabel = new JLabel("..........." + student.getScore() + ".................");

                    tempScoreLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    tempScoreLabel.setBackground(Color.RED);
                    tempScoreLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                            Color.green));
                    tempScoreLabel.setOpaque(true);

                    tempUsernameLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    tempUsernameLabel.setBackground(Color.RED);
                    tempUsernameLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                            Color.green));
                    tempUsernameLabel.setOpaque(true);

                    studentScoreLabel.add(tempScoreLabel);
                    studentUsernameLabel.add(tempUsernameLabel);
                    l = l + 1;
                }
                add(leaderboardPanel);
            } 
            else {
                // Display a message label when there are no students
                JLabel lab = new JLabel(" No students ");
                lab.setFont(new Font("Mv Boli", Font.BOLD, 20));
                lab.setBackground(Color.RED);
                lab.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                JLabel none = new JLabel(" No students ");
                none.setFont(new Font("Mv Boli", Font.BOLD, 20));
                none.setBackground(Color.RED);
                none.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                        Color.green));
                none.setOpaque(true);
                studentScoreLabel.add(none);
                studentUsernameLabel.add(lab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Action event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String classgoalstring = goal.getText();
        try{
        classGoalInt = Integer.parseInt(classgoalstring);
       }
       catch(NumberFormatException r){
       }
        
        // Action when the "Update Classgoal" button is clicked
        if (e.getSource() == change) {
            change.setVisible(false);

            // Display class goal update components
            classgoal.setText("No classgoal. Please enter a score goal");
            classgoal.setBounds(0, 300, 300, 30);
            classgoal.setFont(new Font("Mv Boli", Font.BOLD, 15));
            classgoal.setBackground(Color.RED);
            classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                    Color.green));
            classgoal.setOpaque(true);

            goal.setBounds(0, 330, 300, 30);
            goal.setVisible(true);
            goal.setFont(new Font("Mv Boli", Font.BOLD, 15));
            goal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                    Color.green));

            confirmButton.setVisible(true);
            confirmButton.setBounds(0, 360, 100, 30);
            confirmButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                    Color.green));
        }

        // Action when the "Confirm" button is clicked
        if (e.getSource() == confirmButton) {
            // Attempt to update the class goal in the database
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root", "Poopface891.");
                
                // SQL query to update the class goal in the database
                String query = "UPDATE class SET classgoal = ? WHERE teacherid = ? ";
                String q = "select teacherid from teacher where username = ?";
                PreparedStatement preparedStateme = connection.prepareStatement(q);
                preparedStateme.setString(1, usernam);
                int teacherId = -1;
                var resultSet = preparedStateme.executeQuery();
                if (resultSet.next()) {
                    teacherId = resultSet.getInt("teacherid");
                    System.out.println(teacherId);
                }
                
                // Set parameters and execute the update query
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, classGoalInt);
                preparedStatement.setInt(2, teacherId);
                preparedStatement.executeUpdate();
                
                // Reset student scores to zero for the new class goal
                PreparedStatement pin = connection.prepareStatement("update gamedata set score = 0"
                        + " where studentid in(select studentid from student where classcode in"
                        + "(select classcode from class,teacher where teacher.username = ? and"
                        + " teacher.teacherid = class.teacherid))");
                pin.setString(1, usernam);
                pin.executeUpdate();
                
                // Display success message and refresh the page
                JOptionPane.showMessageDialog(null, "Successfully updated classgoal"
                        + " now refreshing page");
                this.dispose();
                TeacherClassroom classroom = new TeacherClassroom(usernam);
            } catch (SQLException ex) {
                Logger.getLogger(TeacherClassroom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

// Class to represent a student with a username and a score
class Student {
    private String username;
    private int score;

    // Constructor
    public Student(String username, int score) {
        this.username = username;
        this.score = score;
    }

    // Getter method for the username
    public String getUsername() {
        return username;
    }

    // Getter method for the score
    public int getScore() {
        return score;
    }
}


/*
package com.mycompany.neamaybe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class TeacherClassroom extends JFrame implements ActionListener{
    
    private String usernam;
    private JPanel leaderboardPanel;
    int l = 1; 
    int classgoalScore;
    int currentClassgoalScore;
    JLabel studentScoreLabel;
    JButton confirmButton;
    JLabel studentUsernameLabel;
    JLabel classgoal;
    JTextField goal;
    JButton change = new JButton("Update Classgoal");
    JProgressBar progress;
    int classGoalInt;
    
    public TeacherClassroom(String username){
        
        confirmButton = new JButton("Confirm");
        confirmButton.setVisible(false);
        confirmButton.addActionListener(this);
        
        classgoal = new JLabel();
        
        goal = new JTextField();
        goal.setVisible(false);
        this.add(goal);
        
        progress = new JProgressBar();
        
        change.setVisible(false);
        
        JLabel studentLeaderboardHeading = new JLabel("Student Leaderboard:");
        studentLeaderboardHeading.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentLeaderboardHeading.setBounds(335,30,300,20);
        
        JLabel topText = new JLabel("Teacher class menu");
        topText.setFont(new Font("Mv Boli", Font.BOLD,25));
        topText.setBounds(400,10,300,20);
        
        studentScoreLabel = new JLabel();
        studentScoreLabel.setBounds(485,50,150,900);
        studentScoreLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentScoreLabel.setBackground(Color.RED);
        studentScoreLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        studentScoreLabel.setLayout(new BoxLayout(studentScoreLabel, BoxLayout.Y_AXIS));
        studentScoreLabel.setOpaque(true);
        
        studentUsernameLabel = new JLabel();
        studentUsernameLabel.setBounds(335,50,150,900);
        studentUsernameLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentUsernameLabel.setBackground(Color.RED);
        studentUsernameLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        studentUsernameLabel.setLayout(new BoxLayout(studentUsernameLabel, BoxLayout.Y_AXIS));
        studentUsernameLabel.setOpaque(true);
        
        leaderboardPanel = new JPanel(){
                BufferedImage backgroundImage;
                {
                    try {
                        backgroundImage = ImageIO.read(new File("C:\\Users\\Zaheer\\Desktop\\wrGnD8.jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            };
        
        leaderboardPanel.setBackground(Color.WHITE);
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        
        this.add(studentUsernameLabel);
        this.add(change);
        this.add(studentScoreLabel);
        this.add(progress);
        this.add(topText);
        this.add(studentLeaderboardHeading);
        this.add(classgoal);
        this.add(confirmButton);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,900);
        this.setResizable(false);
        this.usernam=username;
        setLayout(new BorderLayout());                                         
        this.add(leaderboardPanel, BorderLayout.CENTER);               
        this.setVisible(true);
        fetchAndDisplayLeaderboard();
        
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea", "root", ".");
        String q = "SELECT class.classgoal from class, teacher where teacher.username = ? and teacher.teacherid = class.teacherid";
            PreparedStatement preparedStatemen = connection.prepareStatement(q);
            preparedStatemen.setString(1, usernam);
            ResultSet resultSe = preparedStatemen.executeQuery();
            if(resultSe.next()){
                classgoalScore = resultSe.getInt("classgoal");
                if(classgoalScore!=0){
                    currentClassgoalScore = 0;
                    String  m ="select score from class,teacher,student,gamedata where teacher.username = ? and "
                            + "teacher.teacherid=class.teacherid and class.classcode = student.classcode and student.studentid = gamedata.studentid";
                    PreparedStatement preparedStatement = connection.prepareStatement(m);
                    preparedStatement.setString(1, usernam);
                    ResultSet rs = preparedStatement.executeQuery();
                    while(rs.next()){
                        currentClassgoalScore = currentClassgoalScore + rs.getInt("score");
                    }
                    
                progress.setMaximum(classgoalScore);
                progress.setValue(currentClassgoalScore);
                progress.setStringPainted(true);
                progress.setString(currentClassgoalScore +"/"+classgoalScore);
                progress.setBounds(0,330,300,30);
                progress.setFont(new Font("Mv Boli", Font.BOLD,15));
                progress.setBackground(Color.RED);
                progress.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                progress.setOpaque(false);
                
                classgoal.setText("Reach score of "+ classgoalScore);
                classgoal.setBounds(0,300,300,30);
                classgoal.setFont(new Font("Mv Boli", Font.BOLD,15));
                classgoal.setBackground(Color.RED);
                classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                classgoal.setOpaque(true);
                
                change.setFont((new Font("Mv Boli", Font.BOLD,15)));
                change.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                change.setBounds(0,360,300,30);
                change.setVisible(true);
                change.addActionListener(this);
                
                }
                
                else{
                    
                classgoal.setText("No classgoal. Please enter a score goal to reach");
                classgoal.setBounds(0,300,300,30);
                classgoal.setFont(new Font("Mv Boli", Font.BOLD,15));
                classgoal.setBackground(Color.RED);
                classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                classgoal.setOpaque(true);
                
                goal.setBounds(0,330, 300, 30);
                goal.setVisible(true);
                goal.setFont(new Font("Mv Boli", Font.BOLD,15));
                goal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                
                confirmButton.setVisible(true);
                confirmButton.setBounds(0,360,100,30);
                confirmButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                
                }
                
            }
            
            if(currentClassgoalScore >= classgoalScore){
                JOptionPane.showMessageDialog(null, "Well Done you have reached the goal. Please update the classgoal");
            }
        }
        catch(SQLException e){
        } 
    }

    
     private void fetchAndDisplayLeaderboard() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea", "root", ".");
            String query = "SELECT student.username, gamedata.score FROM student,gamedata,teacher,class WHERE teacher.username = ? "
                    + "and teacher.teacherid=class.teacherid and class.classcode = student.classcode and student.studentid=gamedata.studentid";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernam);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int score = resultSet.getInt("score");
                students.add(new Student(username, score));
            }

            Collections.sort(students, Comparator.comparingInt(Student::getScore).reversed());

            for (Student student : students) {
                JLabel tempUsernameLabel = new JLabel(l +"......." +student.getUsername()+"..............");
                JLabel tempScoreLabel = new JLabel("..........."+student.getScore()+ ".................");
                
                tempScoreLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
                tempScoreLabel.setBackground(Color.RED);
                tempScoreLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                tempScoreLabel.setOpaque(true);
                
                tempUsernameLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
                tempUsernameLabel.setBackground(Color.RED);
                tempUsernameLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                tempUsernameLabel.setOpaque(true);
                
                studentScoreLabel.add(tempScoreLabel);
                studentUsernameLabel.add(tempUsernameLabel);
                l = l+1; 
            }
            add(leaderboardPanel);
            }
            else{
                JLabel lab = new JLabel(" No students ");
                lab.setFont(new Font("Mv Boli", Font.BOLD,20));
                lab.setBackground(Color.RED);
                lab.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                JLabel none = new JLabel(" No students ");
                none.setFont(new Font("Mv Boli", Font.BOLD,20));
                none.setBackground(Color.RED);
                none.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                none.setOpaque(true);
                studentScoreLabel.add(none);
                studentUsernameLabel.add(lab);
                }
                    }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
     String classGoalString = goal.getText();
     try{
        classGoalInt = Integer.parseInt(classGoalString);
       }
       catch(NumberFormatException r){
       }
     
     if(e.getSource() == change){
                change.setVisible(false);
                
                classgoal.setText("No classgoal. Please enter a score goal to reach");
                classgoal.setBounds(0,300,300,30);
                classgoal.setFont(new Font("Mv Boli", Font.BOLD,15));
                classgoal.setBackground(Color.RED);
                classgoal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                classgoal.setOpaque(true);
                
                goal.setBounds(0,330, 300, 30);
                goal.setVisible(true);
                goal.setFont(new Font("Mv Boli", Font.BOLD,15));
                goal.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                
                confirmButton.setVisible(true);
                confirmButton.setBounds(0,360,100,30);
                confirmButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
     }
     
     if(e.getSource()==confirmButton){
         
         try {
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea", "root", ".");
             String query = "UPDATE class SET classgoal = ? WHERE teacherid = ? ";
             String q = "select teacherid from teacher where username = ?";
             PreparedStatement preparedStateme = connection.prepareStatement(q);
             preparedStateme.setString(1, usernam);
             int teacherId = -1;
             var resultSet = preparedStateme.executeQuery();
             if(resultSet.next()){
             teacherId = resultSet.getInt("teacherid");
             }
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setInt(1, classGoalInt);
              preparedStatement.setInt(2,teacherId);
              preparedStatement.executeUpdate();
              PreparedStatement pin = connection.prepareStatement("update gamedata set score = 0"
                      + " where studentid in(select studentid from student where classcode in"
                      + "(select classcode from class,teacher where teacher.username = ? and teacher.teacherid = class.teacherid))");
              pin.setString(1,usernam);
              pin.executeUpdate();
              JOptionPane.showMessageDialog(null, "Successfully updated classgoal now refreshing page");
              this.dispose();
              TeacherClassroom classroom = new TeacherClassroom(usernam);
         }
         catch (SQLException ex) {
             Logger.getLogger(TeacherClassroom.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
    }
}

class Student {
    private String username;
    private int score;

    public Student(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
*/