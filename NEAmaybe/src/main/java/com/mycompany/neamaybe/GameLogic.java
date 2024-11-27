package com.mycompany.neamaybe;

// Class containing game logic for the Maths Ludo game

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class GameLogic {
    
    // Static variable to store the student ID
    static int id;
    
    // Method to update the roll based on the counter value
    public static int updateRollBasedOnCounter(int counter, int roll) {
        if (counter <= 5) {
            roll = 6;
            JOptionPane.showMessageDialog(null, "You rolled a 6 based on your time");
        } else if (counter >= 6 && counter <= 10) {
            roll = 5;
            JOptionPane.showMessageDialog(null, "You rolled a 5 based on your time");
        } else if (counter >= 11 && counter <= 15) {
            roll = 4;
            JOptionPane.showMessageDialog(null, "You rolled a 4 based on your time");
        } else if (counter >= 16 && counter <= 20) {
            roll = 3;
            JOptionPane.showMessageDialog(null, "You rolled a 3 based on your time");
        } else if (counter >= 21 && counter <= 25) {
            roll = 2;
            JOptionPane.showMessageDialog(null, "You rolled a 2 based on your time");
        } else if (counter >= 26 && counter <= 30) {
            roll = 1;
            JOptionPane.showMessageDialog(null, "You rolled a 1 based on your time");
        } else {
            roll = 0;
            JOptionPane.showMessageDialog(null, "You rolled a 0 based on your time");
        }
        return roll;
    }
    
    // Method to update the score based on the roll value
    public static void updateScore(int roll, String username) {
        int score = 0;
        if (roll == 1) {
            score = 10; 
        } else if (roll == 2) {
            score = 20;
        } else if (roll == 3) {
            score = 30;
        } else if (roll == 4) {
            score = 40;
        } else if (roll == 5) {
            score = 50;
        } else if (roll == 6) {
            score = 60;
        }
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root","...");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select score"
                    + " from student, gamedata where student.username = ? and "
                    + "student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int scoreadd = rs.getInt("score");
                score += scoreadd;
            }
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid"
                    + " from student where username = ?");
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            if (r.next()) {
                id = r.getInt("studentid");
            }
            PreparedStatement stat = (PreparedStatement) connection.prepareStatement("update gamedata"
                    + " set score = ? where studentid = ?");
            stat.setInt(1, score);
            stat.setInt(2, id);
            stat.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        } 
    }
    
    // Method to update the game data based on if student got question right or wrong
    public static void gameDataUpdate(String username, int questionWrongOrRight) {
        int wrongadd = 0;
        int rightadd = 0;
        int totaladd = 0;
        
        // Initially adds 1 to the students total questions answered
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root","...");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select"
                    + " totalqans from gamedata,student where student.username=? "
                    + "and student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                totaladd = rs.getInt("totalqans");
                totaladd = totaladd + 1;
            }
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid"
                    + " from student where username = ?");
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            if (r.next()) {
                id = r.getInt("studentid");
            }
            PreparedStatement stat = (PreparedStatement) connection.prepareStatement("update gamedata set"
                    + " totalqans = ? where studentid = ?");
            stat.setInt(1, totaladd);
            stat.setInt(2, id);
            stat.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        // If they got the question wrong, adds one to totalqwrong field in gamedata table
        if (questionWrongOrRight == 0) {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root","...");
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select "
                        + "totalqwrong from gamedata,student where student.username=? "
                        + "and student.studentid = gamedata.studentid");
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    wrongadd = rs.getInt("totalqwrong");
                    wrongadd = 1 + wrongadd;
                }
                PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid"
                        + " from student where username = ?");
                s.setString(1, username);
                ResultSet r = s.executeQuery();
                if (r.next()) {
                    id = r.getInt("studentid");
                }
                PreparedStatement stat = (PreparedStatement) connection.prepareStatement("update "
                        + "gamedata set totalqwrong = ? where studentid = ?");
                stat.setInt(1, wrongadd);
                stat.setInt(2, id);
                stat.executeUpdate();
            } catch(Exception e) {
                System.out.println(e);
            }
        } 
        // Otherwise add one to totalqright in gamedata table
        else {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                        "root","...");
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select totalqright from"
                        + " gamedata,student where student.username=? "
                        + "and student.studentid = gamedata.studentid");
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    rightadd = rs.getInt("totalqright");
                    rightadd = rightadd + 1;
                }
                PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid"
                        + " from student where username = ?");
                s.setString(1, username);
                ResultSet r = s.executeQuery();
                if (r.next()) {
                    id = r.getInt("studentid");
                }
                PreparedStatement stat = (PreparedStatement) connection.prepareStatement("update gamedata"
                        + " set totalqright = ? where studentid = ?");
                stat.setInt(1, rightadd);
                stat.setInt(2, id);
                stat.executeUpdate();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void updateWinScore(String username){
        int score = 500;
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neadatabase",
                    "root","...");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select score"
                    + " from student, gamedata where student.username = ? and "
                    + "student.studentid = gamedata.studentid");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int scoreadd = rs.getInt("score");
                score += scoreadd;
            }
            PreparedStatement s = (PreparedStatement) connection.prepareStatement("select studentid"
                    + " from student where username = ?");
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            if (r.next()) {
                id = r.getInt("studentid");
            }
            PreparedStatement stat = (PreparedStatement) connection.prepareStatement("update gamedata"
                    + " set score = ? where studentid = ?");
            stat.setInt(1, score);
            stat.setInt(2, id);
            stat.executeUpdate();
        } 
        catch(Exception e) {
            System.out.println(e);  
    }
   }
}


