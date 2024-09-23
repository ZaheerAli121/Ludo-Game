// Import necessary packages
package com.mycompany.neamaybe;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// SignUpChoice class that extends JFrame and implements ActionListener
public class SignUpChoice extends JFrame implements ActionListener {
    
    // Buttons for student and teacher sign-up
    JButton studentSignUp;
    JButton teacherSignUp;

    // Constructor for SignUpChoice class
    SignUpChoice() {
        
        // Label for the background
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setOpaque(true);
        
        // Label for displaying the top text
        JLabel topText = new JLabel();
        topText.setText("Which occupation would you like to sign up as");
        topText.setFont(new Font("MV Boli", Font.PLAIN, 40));
        topText.setBounds(50, -25, 900, 300);
        
        // Button for student sign-up
        studentSignUp = new JButton();
        studentSignUp.setBounds(550, 200, 330, 200);
        studentSignUp.addActionListener(this);
        studentSignUp.setText("Student Sign Up");
        studentSignUp.setFont(new Font("MV Boli", Font.PLAIN, 15));
        studentSignUp.setFocusable(false);
        studentSignUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        // Button for teacher sign-up
        teacherSignUp = new JButton();
        teacherSignUp.setBounds(100, 200, 330, 200);
        teacherSignUp.setText("Teacher Sign up");
        teacherSignUp.setFocusable(false);
        teacherSignUp.setFont(new Font("MV Boli", Font.PLAIN, 15));
        teacherSignUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        teacherSignUp.addActionListener(this);
        
        // Set up the JFrame
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);
        this.add(studentSignUp);
        this.add(teacherSignUp);
        this.add(topText);
        this.add(backgroundLabel);
        this.setVisible(true);
    }

    // Action performed when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the student sign-up button is clicked, open the StudentSignUp window
        if (e.getSource() == studentSignUp) {
            this.dispose();
            StudentSignUp hi = new StudentSignUp();
        }
        // If the teacher sign-up button is clicked, open the TeacherSignUp window
        if (e.getSource() == teacherSignUp) {
            this.dispose();
            TeacherSignUp sign = new TeacherSignUp();
        }
    }
}




/*
package com.mycompany.neamaybe;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SignUpChoice extends JFrame implements ActionListener{
    JButton studentSignUp;
    JButton teacherSignUp;
    SignUpChoice(){
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBackground(new Color(0x00FFFF));
        backgroundLabel.setOpaque(true);
        
        JLabel topText = new JLabel();
        topText.setText("Which occupation would you like to sign up as");
        topText.setFont(new Font("MV Boli", Font.PLAIN,40));
        topText.setBounds(50,-25,900,300);
        
        studentSignUp = new JButton();
        studentSignUp.setBounds(550,200,330,200);
        studentSignUp.addActionListener(this);
        studentSignUp.setText("Student Sign Up");
        studentSignUp.setFont(new Font("MV Boli", Font.PLAIN,15));
        studentSignUp.setFocusable(false);
        studentSignUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        
        teacherSignUp = new JButton();
        teacherSignUp.setBounds(100,200,330,200);
        teacherSignUp.setText("Teacher Sign up");
        teacherSignUp.setFocusable(false);
        teacherSignUp.setFont(new Font("MV Boli", Font.PLAIN,15));
        teacherSignUp.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
        teacherSignUp.addActionListener(this);
        
        this.setTitle("Maths Ludo Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setResizable(false);
        this.add(studentSignUp);
        this.add(teacherSignUp);
        this.add(topText);
        this.add(backgroundLabel);
        this.setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == studentSignUp){
            this.dispose();
            StudentSignUp hi = new StudentSignUp();
        }
        if(e.getSource() == teacherSignUp){
            this.dispose();
            TeacherSignUp sign = new TeacherSignUp();
        }
    }
    
}
*/