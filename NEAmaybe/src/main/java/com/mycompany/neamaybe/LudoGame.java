
// Import necessary java classes
package com.mycompany.neamaybe;
import static com.mycompany.neamaybe.MathQuestion.generateRandomMathQuestion;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Random;

// LudoGame class that starts the LudoGame
public class LudoGame extends JFrame implements ActionListener{
    
   int countblue = 0; // Variable that controls position of the 1st blue piece by pointing to a position in the bluex[] and bluey[] array
   int countblue2 = 0; // Variable that controls position of the 2nd blue piece by pointing to a position in the bluex and bluey[] array
   int countblue3 = 0; // Variable that controls position of the 3rd blue piece by pointing to a position in the bluex and bluey[] array
   int countblue4 = 0; // Variable that controls position of the 4th blue piece by pointing to a position in the bluex and bluey[] array
   int countred = 0; // Variable that controls position of the 1st red piece by pointing to a position in the redx and redy[] array
   int countred2 = 0; // Variable that controls position of the 2nd red piece by pointing to a position in the redx and redy[] array
   int countred3 = 0; // Variable that controls position of the 3rd red piece by pointing to a position in the redx and redy[] array
   int countred4 = 0; // Variable that controls position of the 4th red piece by pointing to a position in the redx and redy[] array
   int countyellow = 0; // Variable that controls position of the 1st yellow piece by pointing to a position in the yellowx[] and yellowy[] array
   int countyellow2 = 0; // Variable that controls position of the 2nd yellow piece by pointing to a position in the yellowx[] and yellowy[] array
   int countyellow3 = 0; // Variable that controls position of the 3rd yellow piece by pointing to a position in the yellowx[] and yellowy[] array
   int countyellow4 = 0; // Variable that controls position of the 4th yellow piece by pointing to a position in the yellowx[] and yellowy[] array
   int countgreen = 0; // Variable that controls position of the 1st green piece by pointing to a position in the greenx[] and greeny[] array
   int countgreen2 = 0; // Variable that controls position of the 2nd green piece by pointing to a position in the greenx[] and greeny[] array
   int countgreen3 = 0; // Variable that controls position of the 3rd green piece by pointing to a position in the greenx[] and greeny[] array
   int countgreen4 = 0; // Variable that controls position of the 4th green piece by pointing to a position in the greenx[] and greeny[] array
   int chosenorder; // Variable that controls which piece number is chosen for the user
   
   // Boolean flags that control if red,yellow or green pieces are on the field
   boolean isR1OnField = true;
   boolean isR2OnField = true;
   boolean isR3OnField = true;
   boolean isR4OnField = true;
   boolean isY1OnField = true;
   boolean isY2OnField = true;
   boolean isY3OnField = true;
   boolean isY4OnField = true;
   boolean isG1OnField = true;
   boolean isG2OnField = true;
   boolean isG3OnField = true;
   boolean isG4OnField = true;
   
   // Check if someone has won
   boolean anyoneWin = false;
   
   // Integer variables that keep track of how many pieces have been liberated
   int bluewin;
   int greenwin;
   int yellowwin;
   int redwin;
    
   // Boolean flags that are used to keep on generating orders until one that is on the field is count
   boolean redOrderChecker = false;
   boolean greenOrderChecker = false;
   boolean yellowOrderChecker = false;
   
   // String values that contain HEX code for the specific colours
   String yellowcode = "#FBE106";
   String greencode = "#006400";
   String bluecode = "#8080FF";
   String redcode = "#FF8164";
   
   // Creates arraylist that contains instances of the GamePiece objects
   ArrayList<GamePiece> pieces = new ArrayList<>();
   
   // Variables used for GUI components
   JButton startGameButton;
   JLabel questionBoxLabel;
   JButton exitGame = new JButton("Exit Game");
   JTextField answerBox;
   GameBoard board = new GameBoard(pieces,"");
   JButton userTurnButton;
   JButton submitAnswer;
   JLabel userTurnLabel = new JLabel();
   
   // Buttons for the user to select the piece they want to move
   JButton submit1 = new JButton();
   JButton submit2 = new JButton();
   JButton submit3 = new JButton();
   JButton submit4 = new JButton();
   
   // Creates instance of timer in order to time user when answering question
   Timer timer = new Timer();
   
   // Variables used to get values from previous menu
   String username;
   String skin;
   
   int questionAnswer; // Variable that stores the real answer to the question
   int rollBasedOnTime; // Variable that stores the users roll
   int timerCounter = 0; // Variable that keeps track of the timer counter
   int intAnswer; // Variable that stores the users answer as an integer from the JTextField
   
   // Arrays that store the X and Y coordiantes of specifc board locations
   int redx[] = {40,80,120,160,200,240,240,240,240,240,240,280,320,320,320,320,320,320,360,400,440,480,520,560,560,560,520,480,
                 440,400,360,320,320,320,320,320,320,280,240,240,240,240,240,240,200,160,120,80,40,0,0,40,80,120,160,200,20};
   int redy[] = {280,280,280,280,280,240,200,160,120,80,40,40,40,80,120,160,200,240,280,280,280,280,280,280,320,360,360,360,360,360,360,
                 400,440,480,520,560,600,600,600,560,520,480,440,400,360,360,360,360,360,360,320,320,320,320,320,320,30};
   int greenx[] = {320,320,320,320,320,360,400,440,480,520,560,560,560,520,480,440,400,360,320,320,320,320,320,320,280,240,240,240,240,240,240,
                   200,160,120,80,40,0,0,0,40,80,120,160,200,240,240,240,240,240,240,280,280,280,280,280,280,40};
   int greeny[] = {80,120,160,200,240,280,280,280,280,280,280,320,360,360,360,360,360,360,400,440,480,520,560,600,600,600,560,520,480,440,400,360,
                   360,360,360,360,360,320,280,280,280,280,280,280,240,200,160,120,80,40,40,80,120,160,200,240,50};
   int yellowx[] = {520,480,440,400,360,320,320,320,320,320,320,280,240,240,240,240,240,240,200,160,120,80,40,0,0,0,40,80,120,160,200,240,240,240,
                    240,240,240,280,320,320,320,320,320,320,360,400,440,480,520,560,560,520,480,440,400,360,60};
   int yellowy[] = {360,360,360,360,360,400,440,480,520,560,600,600,600,560,520,480,440,400,360,360,360,360,360,360,320,280,280,280,280,280,280,
                    240,200,160,120,80,40,40,40,80,120,160,200,240,280,280,280,280,280,280,320,320,320,320,320,320,70};
   int bluex[] = {240,240,240,240,240,200,160,120,80,40,0,0,0,40,80,120,160,200,240,240,240,240,240,240,280,320,320,320,320,320,320,360,400,440,480,
                    520,560,560,560,520,480,440,400,360,320,320,320,320,320,320,280,280,280,280,280,280,80};
   int bluey[] = {560,520,480,440,400,360,360,360,360,360,360,320,280,280,280,280,280,280,240,200,160,120,80,40,40,40,80,120,160,200,240,280,280,
                  280,280,280,280,320,360,360,360,360,360,360,400,440,480,520,560,600,600,560,520,480,440,400,90};
   
   
   // Constructor for the LudoGame class with parameters, username and skin
   LudoGame(String username, String skin){
       
       // Sets passed username and skin to variables, skin and username
       this.skin = skin;
       this.username = username;
       
       // Creating and configuring the startGameButton
       startGameButton = new JButton();
       startGameButton.setBounds(750,150,100,100);
       startGameButton.addActionListener(this);
       startGameButton.setText("Start Game");
       startGameButton.setFont(new Font("MV Boli", Font.PLAIN,15));
       startGameButton.setFocusable(false);
       startGameButton.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
       
       // Creating and configuring exitGame
       exitGame.setBounds(5,830,100,100);
       exitGame.addActionListener(this);
       exitGame.setFont(new Font("MV Boli", Font.PLAIN,15));
       exitGame.setFocusable(false);
       exitGame.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.green));
       
       // Initialise GUI components 
       submitAnswer = new JButton();
       answerBox = new JTextField();
       questionBoxLabel = new JLabel();
       userTurnButton = new JButton();
       
       
       
            // Add each piece with specific coordinates to pieces array
            // Configiure colour and postitons to each corner of the screen
            pieces.add(new GamePiece(505,183,false,greencode,4));
            pieces.add(new GamePiece(505,113,false,greencode,2));
            pieces.add(new GamePiece(427,183,false,greencode,3));
            pieces.add(new GamePiece(427,113,false,greencode,1));
            pieces.add(new GamePiece(505,473,false,yellowcode,2));
            pieces.add(new GamePiece(505,543,false,yellowcode,4));
            pieces.add(new GamePiece(427,473,false,yellowcode,1));
            pieces.add(new GamePiece(427,543,false,yellowcode,3));
            pieces.add(new GamePiece(70,473,false,bluecode,1));
            pieces.add(new GamePiece(143,473,false,bluecode,2));
            pieces.add(new GamePiece(70,543,false,bluecode,3));
            pieces.add(new GamePiece(143,543,false,bluecode,4));
            pieces.add(new GamePiece(70,113,false,redcode,1));
            pieces.add(new GamePiece(143,113,false,redcode,2));
            pieces.add(new GamePiece(70,183,false,redcode,3));
            pieces.add(new GamePiece(143,183,false,redcode,4));
            
            // Create new instance of the board using pieces arrayliist and passed skin
            board = new GameBoard(pieces,skin);
            
            // Create submit buttons and dont set theirn visibility
            submit1.setBounds(750,310,70,25);
            submit1.setText("1");
            submit1.setFont(new Font("MV Boli",Font.PLAIN,15));
            
            submit2.setBounds(750,340,70,25);
            submit2.setText("2");
            submit2.setFont(new Font("MV Boli",Font.PLAIN,15));
            
            submit3.setBounds(750,370,70,25);
            submit3.setText("3");
            submit3.setFont(new Font("MV Boli",Font.PLAIN,15));
            
            submit4.setBounds(750,400,70,25);
            submit4.setText("4");
            submit4.setFont(new Font("MV Boli",Font.PLAIN,15));
            
            // Add and configire frame
            this.setTitle("Maths Ludo Game");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1000,1400);
            this.setResizable(false);
            this.setLayout(new BorderLayout());
            this.add(userTurnLabel);
            this.add(startGameButton);
            this.add(userTurnButton);
            this.add(questionBoxLabel);
            this.add(answerBox);
            this.add(exitGame);
            this.add(submitAnswer);
            this.add(submit1);
            this.add(submit2);
            this.add(submit3);
            this.add(submit4);
            
            // Set GUI components to false that need to be configured later 
            userTurnLabel.setVisible(false);
            submitAnswer.setVisible(false);
            submit1.setVisible(false);
            submit2.setVisible(false);
            submit3.setVisible(false);
            submit4.setVisible(false);
            exitGame.setVisible(true);
            submitAnswer.addActionListener(this);
            submit1.addActionListener(this);
            submit2.addActionListener(this);
            submit3.addActionListener(this);
            submit4.addActionListener(this);
            userTurnButton.addActionListener(this);
            questionBoxLabel.setVisible(false);
            answerBox.setVisible(false);
            userTurnButton.setVisible(false);
            
            // Add board to frame and set frame visibility to true
            this.add(board, BorderLayout.CENTER);
            this.setVisible(true);
            
            // Displays rules to user
            new RulesFrame();
            
    }
    
    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Convert answerBox text to the variable answerString
        String answerString = answerBox.getText();
        
        // Try-Catch to catch numberFormatException
        try {
            // Convert answerString to an integer
            intAnswer = Integer.parseInt(answerString);
        } 
        catch (NumberFormatException l) {
            System.out.println(l);
        }
        
        // If exitGame button is clicked, close frame and open MainMenu frame
        if(e.getSource() == exitGame){
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }
        
        // If startGameButton is clicked, start game loop
        if(e.getSource() == startGameButton){
            
            // Replace and set GUI components
            startGameButton.setVisible(false);
            
            userTurnLabel.setBounds(750,150,1000,100);
            userTurnLabel.setText("Its your turn");
            userTurnLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
            userTurnLabel.setVisible(true);
            
            userTurnButton.addActionListener(this);
            userTurnButton.setText("Start your turn");
            userTurnButton.setFont(new Font("MV Boli",Font.PLAIN,15));
            userTurnButton.setFocusable(true);
            userTurnButton.setBounds(700,300,200,200);
            userTurnButton.setVisible(true);
            
        }
       
        // If userTurnButton is clicked start timer
        if(e.getSource() == userTurnButton){
            
            // Deafault value for chosenorder
            chosenorder = 10000;
            
            // Start new timer task to continuously add 1 to timerCounter per second
            TimerTask task = new TimerTask() {
            @Override
            public void run() {
                
                // If timerCounter less than the limit 30 accumulate timer counts
                if(timerCounter < 30) {
                    timerCounter++;
                    System.out.println("counter " + timerCounter);
                }
                else{
                    // Cancel timer after 30 seconds
                    if (timer != null) {
                        timer.cancel();
                        timer.purge();
                        timer = new Timer();
                    }
                }
            }
        };
            // Schedule timer to run every second
            timer.scheduleAtFixedRate(task, 0, 2000);
            
            // Sets buttons and labels visibility to false as userturn is over
            userTurnButton.setVisible(false);
            userTurnLabel.setVisible(false);
            
            // Create new instance of mathquestion and obtain a random math question and store in string question
            MathQuestions mathquestion = generateRandomMathQuestion();
            String question = mathquestion.getQuestion();
            
            // Get answer for the same question
            questionAnswer = mathquestion.getAnswer();
            System.out.println(questionAnswer);
            
            // Configure label to contain the question
            questionBoxLabel.setText("The question is: " + question + " enter answer below");
            questionBoxLabel.setBounds(630,150,1000,120);
            questionBoxLabel.setFont(new Font("MV Boli",Font.PLAIN,15));
            questionBoxLabel.setVisible(true);
            
            // Configure answerBox JTextField
            answerBox.setBounds(630,250,1000,70);
            answerBox.setVisible(true);
            
            // Configure submitAnswer button
            submitAnswer.setBounds(630,350,200,50);
            submitAnswer.setText("Submit answer");
            submitAnswer.setFont(new Font("MV Boli",Font.PLAIN,15));
            submitAnswer.setVisible(true); 
            
        }
        
        // If submitAnswer is clicked end timer
        if(e.getSource() == submitAnswer){
            
            if (timer != null) {
               timer.cancel();
               timer.purge();
               // Create new instance of timer to ensure no errors or overlap
               timer = new Timer();
             }
            
            // If entered answer is the same as the actual answer enter statement
            if(intAnswer == questionAnswer){
                
                // Update gamedata table using passed username and number 1 to indicate that the question was answered correctly
                GameLogic.gameDataUpdate(username, 1);
                
                // Set GUI components visibility off as user has answered the question
                questionBoxLabel.setVisible(false);
                submitAnswer.setVisible(false);
                answerBox.setVisible(false);
                
                // Calulate roll based on time it took for user to answer the question
                rollBasedOnTime = GameLogic.updateRollBasedOnCounter(timerCounter, rollBasedOnTime);
                
                // Update score in database based on user roll by calling updateScore method
                GameLogic.updateScore(rollBasedOnTime,username);
                
                // Set timer counter back to zero to ensure roll is new everytime
                timerCounter = 0;
                
                // Wait 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LudoGame.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                // Change text of label and configiure
             questionBoxLabel.setVisible(true);
             questionBoxLabel.setBounds(650,150,1000,70);
             questionBoxLabel.setFont(new Font("MV Boli",Font.PLAIN,15));
             questionBoxLabel.setText("Which piece would you like to move");
             
             // Iterate through pieces arraylist and see which pieces are on the board
             // If the specific order is on field, only then displat the respective button
             for(GamePiece piece: pieces){
                 if(piece.order == 1 && piece.colour.equals(bluecode) && piece.active == false){
                     submit1.setVisible(true);
                 }
                 else if(piece.order == 2 && piece.colour.equals(bluecode) && piece.active == false){
                     submit2.setVisible(true);
                 }
                 else if(piece.order == 3 && piece.colour.equals(bluecode) && piece.active == false){
                     submit3.setVisible(true);
                 }
                 else if(piece.order == 4 && piece.colour.equals(bluecode) && piece.active == false){
                     submit4.setVisible(true);
                 }
             }
                
         }
            
            else{
                
                // If answer is wrong update gamadata table respectivly by calling gameDataUpdate method
                GameLogic.gameDataUpdate(username, 0);
                // Output message
                JOptionPane.showMessageDialog(null, "Answer incorrect, you have rolled a 0");
                
                // Set GUI components visibility to false 
                questionBoxLabel.setVisible(false);
                submitAnswer.setVisible(false);
                answerBox.setVisible(false);
                
                // Call aiplay method to make AI play
                aiplay();
                // Call removefromboard method to remove pieces from board that are neccesary
                removefromboard();
                // Checkwin method to check which pieces have won
                checkwin();
                
            } 
           
        }
        
        // When specific piece to be moved is chosen
        if (e.getSource() == submit1 || e.getSource() == submit2 || e.getSource() == submit3 || e.getSource() == submit4) {
               
               // Set deafault value
               chosenorder = 0;
               
               // When submit 1,2,3 or 4 is clicked, moves respective piece
               if (e.getSource() == submit1) {
                 
                 // Set chosenorder to 1 so that specific piece in array can be located 
                 chosenorder = 1;
                 questionBoxLabel.setVisible(false);
                 
                 
                 for (GamePiece piece : pieces) {
                     
                     // If piece is in specific position for example 51, and if the roll is 6 or if position is 52 and roll is 6 or 5 ect , 
                     // They are prompted to choose another piece
                     // As adding 6 to 51 goes above the coordinate limit in the array
                      if (piece.colour.equals(bluecode) && piece.order == chosenorder && piece.active == false) {
                          if( (countblue == 51 && rollBasedOnTime == 6) || (countblue == 52 && (rollBasedOnTime == 6 || rollBasedOnTime == 5)) || (countblue == 53 && 
                                (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4)) || (countblue == 54 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3)) 
                                    || (countblue == 55 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3 || rollBasedOnTime == 2))){
                                     if(bluewin == 3){
                                         piece.updateCoordinates(bluex[56],bluey[56]);
                                     }
                                     else{
                                       JOptionPane.showMessageDialog(null, "Unable to move piece");  
                                     }
                                    }
                          else{
                              countblue += rollBasedOnTime;
                              piece.updateCoordinates(bluex[countblue], bluey[countblue]);
                              piece.updateCoordinates(bluex[56], bluey[56]);
                          }
            
                      }
                      
                   }
                  }
               
               // Same method for 2nd piece
               else if (e.getSource() == submit2) {
                  chosenorder = 2;
                  questionBoxLabel.setVisible(false);
                  for (GamePiece piece : pieces) {
                  if (piece.colour.equals(bluecode) && piece.order == chosenorder && piece.active == false) {
                      if( (countblue2 == 51 && rollBasedOnTime == 6) || (countblue2 == 52 && (rollBasedOnTime == 6 || rollBasedOnTime == 5)) || (countblue2 == 53 && 
                          (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4)) || (countblue2 == 54 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3)) 
                              || (countblue2 == 55 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3 || rollBasedOnTime == 2))){
                          if(bluewin == 3){
                                         piece.updateCoordinates(bluex[56],bluey[56]);
                                     }
                                     else{
                                       JOptionPane.showMessageDialog(null, "Unable to move piece");  
                                     }
                                 System.out.println("NOPE");
                      }
                      else{
                          countblue2 += rollBasedOnTime;
                          piece.updateCoordinates(bluex[countblue2], bluey[countblue2]);
                          piece.updateCoordinates(bluex[56], bluey[56]);
                          }
                      }
                 }
                 
               }
               
               // Same method for 3rd piece
               else if (e.getSource() == submit3) {
                      chosenorder = 3;
                      questionBoxLabel.setVisible(false);
                      for (GamePiece piece : pieces) {
                     if (piece.colour.equals(bluecode) && piece.order == chosenorder && piece.active == false) {
                         if( (countblue3 == 51 && rollBasedOnTime == 6) || (countblue3 == 52 && (rollBasedOnTime == 6 || rollBasedOnTime == 5)) || (countblue3 == 53 && 
                                 (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4)) || (countblue3 == 54 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3)) 
                                    || (countblue3 == 55 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3 || rollBasedOnTime == 2))){
                             if(bluewin == 3){
                                         piece.updateCoordinates(bluex[56],bluey[56]);
                                     }
                                     else{
                                       JOptionPane.showMessageDialog(null, "Unable to move piece");  
                                     }
                                    System.out.println("NOPE");
                                }
                         else{
                             countblue3 += rollBasedOnTime;
                  piece.updateCoordinates(bluex[countblue3], bluey[countblue3]);
                  piece.updateCoordinates(bluex[56], bluey[56]);
                         }
            
                   }
                  }
               }
               
               // Same method for 4th piece
               else if (e.getSource() == submit4) {
                 chosenorder = 4;
                 questionBoxLabel.setVisible(false);
                 for (GamePiece piece : pieces) {
                 if (piece.colour.equals(bluecode) && piece.order == chosenorder && piece.active == false) {
                     if( (countblue4 == 51 && rollBasedOnTime == 6) || (countblue4 == 52 && (rollBasedOnTime == 6 || rollBasedOnTime == 5)) || (countblue4 == 53 && 
                             (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4)) || (countblue4 == 54 && ( rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3)) 
                             || (countblue4 == 55 && (rollBasedOnTime == 6 || rollBasedOnTime == 5 || rollBasedOnTime == 4 || rollBasedOnTime == 3 || rollBasedOnTime == 2))){
                         if(bluewin == 3){
                                         piece.updateCoordinates(bluex[56],bluey[56]);
                                     }
                                     else{
                                       JOptionPane.showMessageDialog(null, "Unable to move piece");  
                                     }
                         System.out.println("NOPE");
                     
                     }
                     else{
                         countblue4 += rollBasedOnTime;
                        piece.updateCoordinates(bluex[countblue4], bluey[countblue4]);
                        piece.updateCoordinates(bluex[56], bluey[56]);
                     }
               }
              }
            }
    
    // Repaint board to display movements
    board.repaint(); 
    
    // Set buttons visibility to false
    submit1.setVisible(false);
    submit2.setVisible(false);
    submit3.setVisible(false);
    submit4.setVisible(false);
    
    // Do methods
    removefromboard();
    checkwin();
    
    aiplay(); 
    removefromboard();
    checkwin();
    
    
    
   }
 }
    
   
   // Method to remove pieces from the gameboard
   public void removefromboard(){
       
       // Iterate through pieces
for (GamePiece piece : pieces) {
    // Get x and y coordinates for the piece
    int x = piece.xpos;
    int y = piece.ypos;
    System.out.println(" xcod = " + x + "ycod " + y);
    // Check if the piece is at its final position in the array, signaling it has reached the end of the board and it's actually on the board
    if (x == redx[56] && y == redy[56] && piece.colour.equals(redcode) && !piece.isActive()) {
        // Update the piece's coordinates to display it away from the board
        piece.updateCoordinates(100000, 100000);
        System.out.println("hi");
        // Display message
        JOptionPane.showMessageDialog(null, "red has liberated a piece!");
        // Set active to true to indicate the piece has been removed
        piece.setActive(true);
        // Add one to the respective colours "win" variable
        redwin++;
    } else if (x == bluex[56] && y == bluey[56] && piece.colour.equals(bluecode) && !piece.isActive()) {
        piece.updateCoordinates(100000, 100000);
        JOptionPane.showMessageDialog(null, "You have liberated a piece!");
        System.out.println("hello");
        piece.setActive(true);
        bluewin++;
    } else if (x == yellowx[56] && y == yellowy[56] && piece.colour.equals(yellowcode) && !piece.isActive()) {
        piece.updateCoordinates(100000, 100000);
        JOptionPane.showMessageDialog(null, "yellow has liberated a piece!");
        piece.setActive(true);
        yellowwin++;
    } else if (x == greenx[56] && y == greeny[56] && piece.colour.equals(greencode) && !piece.isActive()) {
        piece.updateCoordinates(100000, 100000);
        JOptionPane.showMessageDialog(null, "green has liberated a piece!");
        piece.setActive(true);
        greenwin++;
    }
}

// Repaint the board to display if a piece has been removed
board.repaint();
       
   }
   
   
    
    public void aiplay(){
        
        //Generates random red order for AI to move from 1-4
        Random r = new Random();
        int redorder = r.nextInt(4)+1;
        
        // Iterates through each gamepiece in the pieces array
        for(GamePiece piece : pieces){
            
            //If the red piece has been liberated and the number is 1 or 2 or 3 or 4
            if(piece.colour.equals(redcode) && piece.active == true && piece.order == 1){
                
                // Sets the flag to false
                isR1OnField = false;
            }
            
            else if(piece.colour.equals(redcode) && piece.active == true && piece.order == 2){
                isR2OnField = false;
            }
            else if(piece.colour.equals(redcode) && piece.active == true && piece.order == 3){
                isR3OnField = false;
            }
            else if(piece.colour.equals(redcode) && piece.active == true && piece.order == 4){
                isR4OnField = false;
            }
        }
        
        // While this flag is false
        while(redOrderChecker == false){
            
            // If the specific order is on the field, sets the flag to true and exit while loop
            if(redorder == 1 && isR1OnField == true){
                redOrderChecker = true;
            }
            else if(redorder == 2 && isR2OnField == true){
                redOrderChecker = true;
            }
            else if(redorder == 3 && isR3OnField == true){
                redOrderChecker = true;
            }
            else if(redorder == 4 && isR4OnField == true){
                redOrderChecker = true;
            }
            // Else continue to generate random order until an order thats on the field is chosen
            else{
                redorder = r.nextInt(4)+1;
            }
            
        }
        
        // Sets boolean back to false to access loop again
        redOrderChecker = false;
        
        // Generates random roll for the ai red player
        int redroll = r.nextInt(6)+1;
        
        // Displays the ai's roll
        JOptionPane.showMessageDialog(null, "red has rolled a " + redroll);
        
        // Pause for one second
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            
        }
        
        // Switch case statement to peform piece update depending on order
        switch (redorder) {
    case 1:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(redcode) && piece.active == false && piece.order == redorder){
                if( (countred == 51 && redroll == 6) || (countred == 52 && (redroll == 6 || redroll == 5)) || (countred == 53 && 
     (redroll == 6 || redroll == 5 || redroll == 4)) || (countred == 54 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3)) 
     || (countred == 55 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3 || redroll == 2))){
                    if(redwin == 3){
                        piece.updateCoordinates(redx[56],redy[56]);
                      }       
                    
}
                else{
                
                // Add roll onto position of countred to keep track of piece in array
                countred += redroll;
                // Update co-ordinates
                piece.updateCoordinates(redx[countred], redy[countred]);
                }
                
                
            }
           board.repaint();
            
        }
        break;
    case 2:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(redcode) && piece.active == false && piece.order == redorder){
                if( (countred2 == 51 && redroll == 6) || (countred2 == 52 && (redroll == 6 || redroll == 5)) || (countred2 == 53 && 
     (redroll == 6 || redroll == 5 || redroll == 4)) || (countred2 == 54 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3)) 
     || (countred2 == 55 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3 || redroll == 2))){
                    if(redwin == 3){
                        piece.updateCoordinates(redx[56],redy[56]);
                      }
}
                else{
                    countred2 += redroll;
                piece.updateCoordinates(redx[countred2], redy[countred2]);
                }
                
                
            }
           board.repaint();
            
        }
        break;
    case 3:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(redcode) && piece.active == false && piece.order == redorder){
                if( (countred3 == 51 && redroll == 6) || (countred3 == 52 && (redroll == 6 || redroll == 5)) || (countred3 == 53 && 
     (redroll == 6 || redroll == 5 || redroll == 4)) || (countred3 == 54 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3)) 
     || (countred3 == 55 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3 || redroll == 2))){
                    if(redwin == 3){
                        piece.updateCoordinates(redx[56],redy[56]);
                      }
}
                else{
                    countred3 += redroll;
                piece.updateCoordinates(redx[countred3], redy[countred3]);
                }
                
                
            }
           board.repaint();
            
        }
        break;
    case 4:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(redcode) && piece.active == false && piece.order == redorder){
                if( (countred4 == 51 && redroll == 6) || (countred4 == 52 && (redroll == 6 || redroll == 5)) || (countred4 == 53 && 
     (redroll == 6 || redroll == 5 || redroll == 4)) || (countred4 == 54 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3)) 
     || (countred4 == 55 && (redroll == 6 || redroll == 5 || redroll == 4 || redroll == 3 || redroll == 2))){
                    if(redwin == 3){
                        piece.updateCoordinates(redx[56],redy[56]);
                      }
                    else{
                         JOptionPane.showMessageDialog(null, "Unable to move piece");  
                        } 
}
                else{
                    countred4 += redroll;
                piece.updateCoordinates(redx[countred4], redy[countred4]);
                }
                
            }
           board.repaint();
            
        }
        break;
    default:
        break;
}
        
        // Same for green
        int greenorder = r.nextInt(4)+1;
        for (GamePiece piece : pieces) {
            if (piece.colour.equals(greencode) && piece.active && piece.order == 1) {
                 isG1OnField = false;
    }       else if (piece.colour.equals(greencode) && piece.active && piece.order == 2) {
                 isG2OnField = false;
    }       else if (piece.colour.equals(greencode) && piece.active && piece.order == 3) {
                    isG3OnField = false;
    }       else if (piece.colour.equals(greencode) && piece.active && piece.order == 4) {
                    isG4OnField = false;
    }
}

        while (greenOrderChecker == false) {
            if (greenorder == 1 && isG1OnField == true) {
                 greenOrderChecker = true;
    }       else if (greenorder == 2 && isG2OnField == true) {
                 greenOrderChecker = true;
    }       else if (greenorder == 3 && isG3OnField == true) {
                 greenOrderChecker = true;
    }       else if (greenorder == 4 && isG4OnField == true) {
                    greenOrderChecker = true;
    }       else {
                 greenorder = r.nextInt(4) + 1;
    }
}
        int greenroll = r.nextInt(6)+1;
        
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            
        }
        JOptionPane.showMessageDialog(null, "green has rolled a " + greenroll);
        switch (greenorder) {
    case 1:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(greencode) && piece.active == false && piece.order == greenorder){
                if( (countgreen == 51 && greenroll == 6) || (countgreen == 52 && (greenroll == 6 || greenroll == 5)) || (countgreen == 53 && 
                 ( greenroll == 6 || greenroll == 5 || greenroll == 4)) || (countgreen == 54 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || 
                        greenroll == 3)) || (countgreen == 55 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || greenroll == 3 ||
                        greenroll == 2))){
                    if(greenwin == 3){
                        piece.updateCoordinates(greenx[56],greeny[56]);
                      }
}
                else{
                    countgreen += greenroll;
                piece.updateCoordinates(greenx[countgreen], greeny[countgreen]);
                }
                
                
            }
           board.repaint();
            
        }
        break;
    case 2:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(greencode) && piece.active == false && piece.order == greenorder){
                if( (countgreen2 == 51 && greenroll == 6) || (countgreen2 == 52 && (greenroll == 6 || greenroll == 5)) || (countgreen2 == 53 && 
     (greenroll == 6 || greenroll == 5 || greenroll == 4)) || (countgreen2 == 54 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || 
                        greenroll == 3))  || (countgreen2 == 55 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || greenroll == 3 || 
                        greenroll == 2))){
                    if(greenwin == 3){
                        piece.updateCoordinates(greenx[56],greeny[56]);
                      }
                 }
                else{
                    countgreen2 += greenroll;
                piece.updateCoordinates(greenx[countgreen2], greeny[countgreen2]);
                }
                
                
                
            }
           board.repaint();
            
        }
        break;
    case 3:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(greencode) && piece.active == false && piece.order == greenorder){
                if( (countgreen3 == 51 && greenroll == 6) || (countgreen3 == 52 && (greenroll == 6 || greenroll == 5)) || (countgreen3 == 53 && 
     (greenroll == 6 || greenroll == 5 || greenroll == 4)) || (countgreen3 == 54 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || 
                        greenroll == 3)) || (countgreen3 == 55 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || greenroll == 3 || 
                        greenroll == 2))){
                    if(greenwin == 3){
                        piece.updateCoordinates(greenx[56],greeny[56]);
                      }
}
                else{
                    countgreen3 += greenroll;
                piece.updateCoordinates(greenx[countgreen3], greeny[countgreen3]);
                }
                
                
            }
           board.repaint();
            
        }
        break;
    case 4:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(greencode) && piece.active == false && piece.order == greenorder){
                if( (countgreen4 == 51 && greenroll == 6) || (countgreen4 == 52 && (greenroll == 6 || greenroll == 5)) || (countgreen4 == 53 && 
     (greenroll == 6 || greenroll == 5 || greenroll == 4)) || (countgreen4 == 54 && (greenroll == 6 || greenroll == 5 || greenroll == 4 ||
                        greenroll == 3)) || (countgreen4 == 55 && (greenroll == 6 || greenroll == 5 || greenroll == 4 || greenroll == 3 ||
                        greenroll == 2))){
                    if(greenwin == 3){
                        piece.updateCoordinates(greenx[56],greeny[56]);
                      }
}
                else{
                countgreen4 += greenroll;
                piece.updateCoordinates(greenx[countgreen4], greeny[countgreen4]);
                }
               }
           board.repaint();
            
           }
        break;
    default:
        break;
    }
        
        
      // Same for yellow
      int yelloworder = r.nextInt(4)+1;
      for (GamePiece piece : pieces) {
         if (piece.colour.equals(yellowcode) && piece.active && piece.order == 1) {
                 isY1OnField = false;
    }    else if (piece.colour.equals(yellowcode) && piece.active && piece.order == 2) {
                 isY2OnField = false;
    }   else if (piece.colour.equals(yellowcode) && piece.active && piece.order == 3) {
                 isY3OnField = false;
    }   else if (piece.colour.equals(yellowcode) && piece.active && piece.order == 4) {
                 isY4OnField = false;
    }
}

while (yellowOrderChecker == false) {
        if (yelloworder == 1 && isY1OnField == true) {
            yellowOrderChecker = true;
    }    else if (yelloworder == 2 && isY2OnField == true) {
             yellowOrderChecker = true;
    }   else if (yelloworder == 3 && isY3OnField == true) {
            yellowOrderChecker = true;
    }   else if (yelloworder == 4 && isY4OnField == true) {
            yellowOrderChecker = true;
    } else {
        yelloworder = r.nextInt(4) + 1;
    }
}
      int yellowroll = r.nextInt(6)+1;
      try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            
        }
      JOptionPane.showMessageDialog(null, "yellow has rolled a " + yellowroll);
    switch (yelloworder) {
    case 1:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(yellowcode) && piece.active == false && piece.order == yelloworder){
                if( (countyellow == 51 && yellowroll == 6) || (countyellow == 52 && (yellowroll == 6 || yellowroll == 5)) || (countyellow == 53 && 
     (yellowroll == 6 || yellowroll == 5 || yellowroll == 4)) || (countyellow == 54 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || 
                        yellowroll == 3))  || (countyellow == 55 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || yellowroll == 3 ||
                        yellowroll == 2))){
                    if(yellowwin == 3){
                        piece.updateCoordinates(yellowx[56],yellowy[56]);
                      }
}
                else{
                    countyellow += yellowroll;
                piece.updateCoordinates(yellowx[countyellow], yellowy[countyellow]);
                }
            }
            board.repaint();
        }
        break;
    case 2:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(yellowcode) && piece.active == false && piece.order == yelloworder){
                if( (countyellow2 == 51 && yellowroll == 6) || (countyellow2 == 52 && (yellowroll == 6 || yellowroll == 5)) || (countyellow2 == 53 && 
     (yellowroll == 6 || yellowroll == 5 || yellowroll == 4)) || (countyellow2 == 54 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || 
                        yellowroll == 3)) || (countyellow2 == 55 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || yellowroll == 3 || 
                        yellowroll == 2))){
                    if(yellowwin == 3){
                        piece.updateCoordinates(yellowx[56],yellowy[56]);
                      }
}
                else{
                    countyellow2 += yellowroll;
                piece.updateCoordinates(yellowx[countyellow2], yellowy[countyellow2]);
                }
            }
            board.repaint();
        }
        break;
    case 3:
        
        for(GamePiece piece : pieces){
            if(piece.colour.equals(yellowcode) && piece.active == false && piece.order == yelloworder){
                if( (countyellow3 == 51 && yellowroll == 6) || (countyellow3 == 52 && (yellowroll == 6 || yellowroll == 5)) || (countyellow3 == 53 && 
     (yellowroll == 6 || yellowroll == 5 || yellowroll == 4)) || (countyellow3 == 54 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || 
                        yellowroll == 3))|| (countyellow3 == 55 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || yellowroll == 3 || 
                        yellowroll == 2))){
                    if(yellowwin == 3){
                        piece.updateCoordinates(yellowx[56],yellowy[56]);
                      }
}
                else{
                    countyellow3 += yellowroll;
                piece.updateCoordinates(yellowx[countyellow3], yellowy[countyellow3]);
                }
            }
            board.repaint();
        }
        break;
    case 4:
       
        for(GamePiece piece : pieces){
            if(piece.colour.equals(yellowcode) && piece.active == false && piece.order == yelloworder){
                if( (countyellow4 == 51 && yellowroll == 6) || (countyellow4 == 52 && (yellowroll == 6 || yellowroll == 5)) || (countyellow4 == 53 && 
     (yellowroll == 6 || yellowroll == 5 || yellowroll == 4)) || (countyellow4 == 54 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 ||
                        yellowroll == 3)) || (countyellow4 == 55 && (yellowroll == 6 || yellowroll == 5 || yellowroll == 4 || yellowroll == 3 || 
                        yellowroll == 2))){
                    if(yellowwin == 3){
                        piece.updateCoordinates(yellowx[56],yellowy[56]);
                      }
}
                else{
                     countyellow4 += yellowroll;
                piece.updateCoordinates(yellowx[countyellow4], yellowy[countyellow4]);
                }
            }
            board.repaint();
        }
        break;
    default:
        break;       
}   
    // Set userTurnButton to true so student can play again
    userTurnButton.setVisible(true);
    
    }

    // Method to check if the colour has been won
    public void checkwin(){
       
       // If the specific colours checker is 4, meaning 4 pieces has been liberated
       if (bluewin == 4) {
            // Update score for user if they win, using updateWinScore method
            GameLogic.updateWinScore(username);
            // Display winning message
            JOptionPane.showMessageDialog(null, "Blue has won!");
            anyoneWin = true;
            // Close frame and open MainMenu frame
            this.dispose();
            MainMenu menu = new MainMenu(username);
        } else if (greenwin == 4) {
            JOptionPane.showMessageDialog(null, "Green has won!");
            anyoneWin = true;
            this.dispose();
            MainMenu menu = new MainMenu(username);
        } else if (redwin == 4) {
            JOptionPane.showMessageDialog(null, "Red has won!");
            anyoneWin = true;
            this.dispose();
            MainMenu menu = new MainMenu(username);
        } else if (yellowwin == 4) {
            JOptionPane.showMessageDialog(null, "Yellow has won!");
            anyoneWin = true;
            this.dispose();
            MainMenu menu = new MainMenu(username);
        }  
      
       
       
       
    }
}

            /*
            BufferedImage backgroundImage = ImageIO.read(new File("C:\\Users\\Zaheer\\Documents\\photos\\ludo.jpg"));
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            backgroundPanel.setPreferredSize(new Dimension(1000, 1400));
            */