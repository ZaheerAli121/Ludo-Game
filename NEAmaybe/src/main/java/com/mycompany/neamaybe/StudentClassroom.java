
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class StudentClassroom extends JFrame implements ActionListener {

    // Variables to store the username, leaderboard panel, class goal, and class code
    private String usernam; // User's username
    private JPanel leaderboardPanel; // Panel for displaying the leaderboard
    int classgoalScore; // Total score needed to achieve the class goal
    int classScoreGoal; // The set class goal
    int l = 1; // Counter variable for leaderboard ranking
    JLabel studentScoreLabel; // Label for displaying student scores
    JLabel studentUsernameLabel; // Label for displaying student usernames
    JLabel classgoalLabel; // Label for displaying the class goal
    JProgressBar progressBar; // Progress bar for displaying the current progress toward the class goal
    JButton backtomenu = new JButton("Main Menu"); // Button to go back to the main menu
    int classcodeNum; // The class code associated with the user

    // Constructor for the StudentClassroom class
    public StudentClassroom(String username) {

        // Set up the "Main Menu" button
        backtomenu.setBounds(780, 800, 200, 60);
        backtomenu.addActionListener(this);

        // Initialize instance variables
        this.usernam = username; // Initialize the username
        classgoalLabel = new JLabel(); // Initialize the label for displaying the class goal
        progressBar = new JProgressBar(); // Initialize the progress bar

        // Labels for displaying information
        JLabel studentLeaderboardLabel = new JLabel("Student Leaderboard:"); // Label for indicating the leaderboard section
        studentLeaderboardLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
        studentLeaderboardLabel.setBounds(335, 30, 300, 20);

        JLabel topText = new JLabel("Student class menu"); // Label for indicating the menu section
        topText.setFont(new Font("Mv Boli", Font.BOLD, 25));
        topText.setBounds(400, 10, 300, 20);

        // Labels for displaying student scores and usernames
        studentScoreLabel = new JLabel(); // Label for displaying student scores
        studentScoreLabel.setBounds(485, 50, 150, 900);
        studentScoreLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
        studentScoreLabel.setBackground(Color.RED);
        studentScoreLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        studentScoreLabel.setLayout(new BoxLayout(studentScoreLabel, BoxLayout.Y_AXIS));
        studentScoreLabel.setOpaque(true);

        studentUsernameLabel = new JLabel(); // Label for displaying student usernames
        studentUsernameLabel.setBounds(335, 50, 150, 900);
        studentUsernameLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
        studentUsernameLabel.setBackground(Color.RED);
        studentUsernameLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        studentUsernameLabel.setLayout(new BoxLayout(studentUsernameLabel, BoxLayout.Y_AXIS));
        studentUsernameLabel.setOpaque(true);

        // Panel for displaying leaderboard
        leaderboardPanel = new JPanel() {
            BufferedImage backgroundImage;

            {
                try {
                    // Set background image
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

        // Add components to the frame
        this.add(studentUsernameLabel);
        this.add(studentScoreLabel);
        this.add(progressBar);
        this.add(topText);
        this.add(studentLeaderboardLabel);
        this.add(classgoalLabel);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 900);
        this.add(backtomenu);
        this.setResizable(false);
        setLayout(new BorderLayout());
        this.add(leaderboardPanel, BorderLayout.CENTER);
        this.setVisible(true);

        try {
            // Database connection to retrieve class goal
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase", "root",
                    "Poopface891.");
            String q = "SELECT class.classgoal from class,student where student.username=? and student.classcode=class.classcode";
            PreparedStatement preparedStatemen = connection.prepareStatement(q);
            preparedStatemen.setString(1, usernam);
            ResultSet resultSe = preparedStatemen.executeQuery();

            if (resultSe.next()) {
                // Set class goal and update progress bar if goal exists
                classScoreGoal = resultSe.getInt("classgoal");
                
                if (classScoreGoal != 0) {
                    // If goal exists, display goal and current score on the progressbar
                    String stat = "select classcode from student where username =?";
                    PreparedStatement prepared = connection.prepareStatement(stat);
                    prepared.setString(1, usernam);
                    ResultSet set = prepared.executeQuery();

                    if (set.next()) {
                        classcodeNum = set.getInt("classcode");
                    }

                    classgoalScore = 0;
                    String m = "select score from student,gamedata where student.classcode = ? and student.studentid = gamedata.studentid ";
                    PreparedStatement preparedStatement = connection.prepareStatement(m);
                    preparedStatement.setInt(1, classcodeNum);
                    ResultSet rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        // Accumulate scores for the progress bar
                        classgoalScore = classgoalScore + rs.getInt("score");
                    }

                    // Set up progress bar
                    progressBar.setMaximum(classScoreGoal);
                    progressBar.setValue(classgoalScore);
                    progressBar.setStringPainted(true);
                    progressBar.setString(classgoalScore + "/" + classScoreGoal);
                    progressBar.setBounds(0, 330, 300, 30);
                    progressBar.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    progressBar.setBackground(Color.RED);
                    progressBar.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                    progressBar.setOpaque(false);

                    // Set up class goal label
                    classgoalLabel.setText("Reach score of " + classScoreGoal);
                    classgoalLabel.setBounds(0, 300, 300, 30);
                    classgoalLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    classgoalLabel.setBackground(Color.RED);
                    classgoalLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                    classgoalLabel.setOpaque(true);
                } else {
                    // If no goal exists
                    classgoalLabel.setText("No class goal");
                    classgoalLabel.setBounds(0, 300, 300, 30);
                    classgoalLabel.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    classgoalLabel.setBackground(Color.RED);
                    classgoalLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                    classgoalLabel.setOpaque(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Fetch and display the leaderboard
        fetchAndDisplayLeaderboard(classcodeNum);
    }

    // Fetch and display the leaderboard for a given class code
    private void fetchAndDisplayLeaderboard(int classcoddle) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connections = DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase", "root",
                    "Poopface891.");
            String query = "SELECT student.username, gamedata.score FROM student,gamedata WHERE student.classcode = ? and student.studentid = gamedata.studentid";

            // Prepared statement for fetching username and score
            PreparedStatement preparedStatement = connections.prepareStatement(query);
            preparedStatement.setInt(1, classcoddle);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there is matching data
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No matching data found.");
            } else {
                // Populate the students list with data from the database
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    int score = resultSet.getInt("score");
                    students.add(new Student(username, score));
                }

                // Sort students based on score in descending order
                Collections.sort(students, Comparator.comparingInt(Student::getScore).reversed());

                // Display student information in the leaderboard by iterating through students ArrayList
                for (Student student : students) {
                    JLabel label = new JLabel(l + "......." + student.getUsername() + "..............");
                    JLabel lab = new JLabel("..........." + student.getScore() + ".................");
                    lab.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    lab.setBackground(Color.RED);
                    lab.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                    label.setFont(new Font("Mv Boli", Font.BOLD, 15));
                    label.setBackground(Color.RED);
                    label.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                    label.setOpaque(true);
                    lab.setOpaque(true);
                    studentScoreLabel.add(lab);
                    studentUsernameLabel.add(label);
                    l = l + 1;
                }
                // Add leadboardPanel to frame
                add(leaderboardPanel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Action performed when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backtomenu) {
            this.dispose();
            MainMenu menu = new MainMenu(usernam);
        }
    }
}

// Class to represent a student with a username and score
class Student {
    private String username;
    private int score;

    public Student(String username, int score) {
        this.username = username;
        this.score = score;
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return username;
    }

    // Getter method for retrieving the score
    public int getScore() {
        return score;
    }
}





/*
public class StudentClassroom extends JFrame implements ActionListener {
    
    private String usernam;
    private JPanel leaderboardPanel;
    int classgoalScore;
    int classScoreGoal;
    int l = 1;
    JLabel studentScoreLabel;
    JLabel studentUsernameLabel;
    JLabel classgoalLabel;
    JProgressBar progressBar;
    JButton backtomenu = new JButton("Main Menu");
    int classcodeNum;
    
   public StudentClassroom(String username){
       
        backtomenu.setBounds(780,800,200,60);
        backtomenu.addActionListener(this);
       
        this.usernam=username;
       
        classgoalLabel = new JLabel();
        
        progressBar = new JProgressBar();
        
        JLabel studentLeaderboardLabel = new JLabel("Student Leaderboard:");
        studentLeaderboardLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
        studentLeaderboardLabel.setBounds(335,30,300,20);
        
        JLabel topText = new JLabel("Student class menu");
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
        this.add(studentScoreLabel);
        this.add(progressBar);
        this.add(topText);
        this.add(studentLeaderboardLabel);
        this.add(classgoalLabel);
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,900);
        this.add(backtomenu);
        this.setResizable(false);setLayout(new BorderLayout());
        this.add(leaderboardPanel, BorderLayout.CENTER);
        this.setVisible(true);
        
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea", "root", "Poopface891.");
        String q = "SELECT class.classgoal from class,student where student.username=? and student.classcode=class.classcode";
            PreparedStatement preparedStatemen = connection.prepareStatement(q);
            preparedStatemen.setString(1, usernam);
            ResultSet resultSe = preparedStatemen.executeQuery();
            if(resultSe.next()){
                classScoreGoal = resultSe.getInt("classgoal");
                if(classScoreGoal!=0){
                    String stat = "select classcode from student where username =?";
                    PreparedStatement prepared = connection.prepareStatement(stat);
                    prepared.setString(1, usernam);
                    ResultSet set = prepared.executeQuery();
                    if(set.next()){
                       classcodeNum = set.getInt("classcode");
                    }
                    classgoalScore = 0;
                    String  m ="select score from student,gamedata where student.classcode = ? and student.studentid = gamedata.studentid ";
                    PreparedStatement preparedStatement = connection.prepareStatement(m);
                    preparedStatement.setInt(1, classcodeNum);
                    ResultSet rs = preparedStatement.executeQuery();
                    while(rs.next()){
                        classgoalScore = classgoalScore + rs.getInt("score");
                    }
                    
                progressBar.setMaximum(classScoreGoal);
                progressBar.setValue(classgoalScore);
                progressBar.setStringPainted(true);
                progressBar.setString(classgoalScore +"/"+classScoreGoal);
                progressBar.setBounds(0,330,300,30);
                progressBar.setFont(new Font("Mv Boli", Font.BOLD,15));
                progressBar.setBackground(Color.RED);
                progressBar.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                progressBar.setOpaque(false);
                
                classgoalLabel.setText("Reach score of "+ classScoreGoal);
                classgoalLabel.setBounds(0,300,300,30);
                classgoalLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
                classgoalLabel.setBackground(Color.RED);
                classgoalLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                classgoalLabel.setOpaque(true);
                
                }
                else{
                    
                classgoalLabel.setText("No classgoal");
                classgoalLabel.setBounds(0,300,300,30);
                classgoalLabel.setFont(new Font("Mv Boli", Font.BOLD,15));
                classgoalLabel.setBackground(Color.RED);
                classgoalLabel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                classgoalLabel.setOpaque(true);
                
                }
            }
            
        }
        catch(SQLException e){
            
        }
        if(classgoalScore >= classScoreGoal){
                JOptionPane.showMessageDialog(null, "Classgoal has been reached.");
            }
       fetchAndDisplayLeaderboard(classcodeNum);
   }
   
private void fetchAndDisplayLeaderboard(int classcoddle) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connections = DriverManager.getConnection("jdbc:mysql://localhost:3306/pleasenea", "root", "Poopface891.");
            String query = "SELECT student.username, gamedata.score FROM student,gamedata WHERE student.classcode = ? and student.studentid = gamedata.studentid";
            PreparedStatement preparedStatement = connections.prepareStatement(query);
            preparedStatement.setInt(1, classcoddle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
              System.out.println("No matching data found.");
                 } 
           
            else{  
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int score = resultSet.getInt("score");
                students.add(new Student(username, score));
            }
            
            Collections.sort(students, Comparator.comparingInt(Student::getScore).reversed());

            for (Student student : students) {
                JLabel label = new JLabel(l +"......." +student.getUsername()+"..............");
                JLabel lab = new JLabel("..........."+student.getScore()+ ".................");
                lab.setFont(new Font("Mv Boli", Font.BOLD,15));
                lab.setBackground(Color.RED);
                lab.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                label.setFont(new Font("Mv Boli", Font.BOLD,15));
                label.setBackground(Color.RED);
                label.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
                label.setOpaque(true);
                lab.setOpaque(true);
                studentScoreLabel.add(lab);
                studentUsernameLabel.add(label);
                l = l+1;
            }
           
            add(leaderboardPanel);
            }
            
                    } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backtomenu){
            this.dispose();
            MainMenu menu = new MainMenu(usernam);
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