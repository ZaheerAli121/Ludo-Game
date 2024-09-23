
// Import necessary java classes
package com.mycompany.neamaybe;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


// Class representing GameBoard in the maths ludo game
public class GameBoard extends JPanel {
    private ArrayList<GamePiece> pieces; // List to store gamepieces
    private String skin; // String to store selected skin
    
    // Constructor to initialize gameboard with pieces and skin
    public GameBoard(ArrayList<GamePiece> pieces,String skin) {
        this.pieces = pieces;
        this.skin = skin;
    }
    
    // Override method to paint the game board and pieces
    @Override
    protected void paintComponent(Graphics g) {
                         
                        // Painting components to the screen
                        super.paintComponent(g);
                        g.setColor(Color.red);
			g.fillRect(0, 40, 240, 240);
			g.setColor(Color.white);
			g.fillRect(40,80, 160, 160);
			
			g.setColor(Color.green);
		        g.fillRect(0+360, 40, 240, 240);
			g.setColor(Color.white);
			g.fillRect(40+360,80, 160, 160);
			
			g.setColor(Color.yellow);
			g.fillRect(0+360, 40+360, 240, 240);
			g.setColor(Color.white);
			g.fillRect(40+360,80+360, 160, 160);
			
			g.setColor(Color.blue);
			g.fillRect(0, 40+360, 240, 240);
			g.setColor(Color.white);
			g.fillRect(40,80+360, 160, 160);
		
			
			g.setColor(Color.black);
			g.drawRect(0, 40,600,600);
			g.drawLine(240, 80, 360, 80);
			g.drawLine(240, 120, 360, 120);
			g.drawLine(240, 160, 360, 160);
			g.drawLine(240, 200, 360, 200);
			g.drawLine(240, 240, 360, 240);
			g.drawLine(240, 280, 360, 280);
			
			g.drawLine(240, 400, 360, 400);
			g.drawLine(240, 440, 360, 440);
			g.drawLine(240, 480, 360, 480);
			g.drawLine(240, 520, 360, 520);
			g.drawLine(240, 560, 360, 560);
			g.drawLine(240, 600, 360, 600);
			
			g.drawLine(0, 280, 0, 400);
			g.drawLine(40, 280, 40, 400);
			g.drawLine(80, 280, 80, 400);
			g.drawLine(120, 280, 120, 400);
			g.drawLine(160, 280, 160, 400);
			g.drawLine(200, 280, 200, 400);
			g.drawLine(240, 280, 240, 400);
			
			g.drawLine(360, 280, 360, 400);
			g.drawLine(400, 280, 400, 400);
			g.drawLine(440, 280, 440, 400);
			g.drawLine(480, 280, 480, 400);
			g.drawLine(520, 280, 520, 400);
			g.drawLine(560, 280, 560, 400);
			

			g.drawLine(240,40,240,280);
			g.drawLine(280,40,280,280);
			g.drawLine(320,40,320,280);
			g.drawLine(360,40,360,280);
			
			g.drawLine(240,400,240,640);
			g.drawLine(280,400,280,640);
			g.drawLine(320,400,320,640);
			g.drawLine(360,400,360,640);
			
			g.drawLine(0,280,240,280);
			g.drawLine(0,320,240,320);
			g.drawLine(0,360,240,360);
			g.drawLine(0,400,240,400);
			
			g.drawLine(360,280,600,280);
			g.drawLine(360,320,600,320);
			g.drawLine(360,360,600,360);
			g.drawLine(360,400,600,400);
			
			
			g.setColor(Color.green);
			g.fillRect(321,81,39,39);
			g.fillRect(281,81,39,39);
			g.fillRect(281,81+40,39,39);
			g.fillRect(281,81+80,39,39);
			g.fillRect(281,81+120,39,39);
			g.fillRect(281,81+160,39,39);
			
			g.setColor(Color.blue);
			g.fillRect(241,561,39,39);
			g.fillRect(281,561,39,39);
			g.fillRect(281,561-40,39,39);
			g.fillRect(281,561-80,39,39);
			g.fillRect(281,561-120,39,39);
			g.fillRect(281,561-160,39,39);
			
			g.setColor(Color.red);
			g.fillRect(41,281,39,39);
			g.fillRect(41,321,39,39);
			g.fillRect(41+40,321,39,39);
			g.fillRect(41+80,321,39,39);
			g.fillRect(41+120,321,39,39);
			g.fillRect(41+160,321,39,39);
			
			g.setColor(Color.yellow);
			g.fillRect(521,361,39,39);
			g.fillRect(521,321,39,39);
			g.fillRect(521-40,321,39,39);
			g.fillRect(521-80,321,39,39);
			g.fillRect(521-120,321,39,39);
			g.fillRect(521-160,321,39,39);
			

			Polygon red = new Polygon();
			red.addPoint(240+1,280);
			red.addPoint(300, 340);
			red.addPoint(240+1, 280+120);
			g.setColor(Color.red);
			g.fillPolygon(red);
		        g.fillOval(55, 95,60, 60);
			g.fillOval(130,95,60,60);
			g.fillOval(55, 165, 60, 60);
			g.fillOval(130, 165, 60, 60);

			Polygon green = new Polygon();
			green.addPoint(241,281);
			green.addPoint(300, 340);
			green.addPoint(359, 281);
			g.setColor(Color.green);
			g.fillPolygon(green);
			g.fillOval(55+360, 95,60, 60);
			g.fillOval(130+360,95,60,60);
			g.fillOval(55+360, 165, 60, 60);
			g.fillOval(130+360, 165, 60, 60);
			

			Polygon yellow = new Polygon();
			yellow.addPoint(240+120,280);
			yellow.addPoint(300, 340);
			yellow.addPoint(360, 280+120);
			g.setColor(Color.yellow);
			g.fillPolygon(yellow);
			g.fillOval(55+360, 95+360,60, 60);
			g.fillOval(130+360,95+360,60,60);
			g.fillOval(55+360, 165+360, 60, 60);
			g.fillOval(130+360, 165+360, 60, 60);
			
			
			Polygon blue = new Polygon();
			blue.addPoint(240,280+120);
			blue.addPoint(300, 340);
			blue.addPoint(360, 280+120);
			g.setColor(Color.blue);
			g.fillPolygon(blue);
			g.fillOval(55, 95+360,60, 60);
			g.fillOval(130,95+360,60,60);
			g.fillOval(55, 165+360, 60, 60);
			g.fillOval(130, 165+360, 60, 60);
			g.setColor(Color.black);
			g.drawLine(240, 280, 240+120,280+120);
			g.drawLine(360, 280, 240, 400);
        
                        
        // Draw pieces onto the gameboard by iterating through pieces array
        
        for (GamePiece piece : pieces) {
            
            // Sets size of piece
            int pieceSize = 30;
            
                // If the piece is blue and the skin passed is einstein, paints the einstein skin to the screen
                if(piece.colour.equals("#8080FF") && skin == "einstein"){
                    try {
                        Image image = ImageIO.read(new File("C:/Users/Zaheer/Documents/Screenshot"
                                + " 2023-11-23 194045.png"));
                        g.drawImage(image, piece.getXpos(), piece.getYpos(), pieceSize,
                                pieceSize, null);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE,
                                null, ex);
                    }
                }
                
                // If the piece is blue and the skin passed is detective, paints the detective
                //skin to the screen
                else if(piece.colour.equals("#8080FF") && skin == "detective"){
                    try {
                        Image image = ImageIO.read(new File("C:/Users/Zaheer/Documents/Screenshot"
                                + " 2023-11-23 200351.png"));
                        g.drawImage(image, piece.getXpos(), piece.getYpos(), pieceSize,
                                pieceSize, null);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null,
                                ex);
                    }
                    
                    
                }
                
                // If the piece is blue and the skin passed is king, paints the king skin to the screen
                else if(piece.colour.equals("#8080FF") && skin == "king"){
                    try {
                        Image image = ImageIO.read(new File("C:/Users/Zaheer/Documents/Screenshot"
                                + " 2023-11-23 200611.png"));
                        g.drawImage(image, piece.getXpos(), piece.getYpos(), pieceSize,
                                pieceSize, null);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null,
                                ex);
                    }
                    
                }
                
                // If the piece is blue and no skin is passed, paints regular blue piece
                else{
                g.setColor(Color.decode(piece.getColour()));
                g.fillOval(piece.getXpos(), piece.getYpos(), pieceSize, pieceSize);
                }
        }
        
        // Draws numbers 1,2,3,4 onto each gamepiece
        for(GamePiece piece:pieces){
            
            // If its the first piece paint a one
            if(piece.order == 1){
            int x = piece.getXpos(); 
                int y = piece.getYpos(); 
                int size = 30;     
                Color color = Color.decode(piece.getColour());
                Graphics2D g2d = (Graphics2D) g;
                Stroke originalStroke = g2d.getStroke();
                BasicStroke thickerStroke = new BasicStroke(3.0f); 
                g2d.setStroke(thickerStroke);
                g2d.setColor(color);
                
                // If theres a skin, do not fillOval as it overrides skin
                if(piece.colour.equals("#8080FF") && (skin == "einstein" || skin == "detective"
                        || skin == "king")){
                }
                else{
                    g2d.fillOval(x, y, size, size);
                    
                }
                
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.setColor(Color.BLACK);
                String number = "1";
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (size - fm.stringWidth(number)) / 2;
                int textY = y + size / 2 + fm.getAscent() / 2;
                g2d.drawString(number, textX, textY);
                g2d.setStroke(originalStroke);    
        }
            // If its the second piece paint a 2
            else if(piece.order == 2){
                int x = piece.getXpos(); 
                int y = piece.getYpos(); 
                int size = 30;     
                Color color = Color.decode(piece.getColour());
                Graphics2D g2d = (Graphics2D) g;
                Stroke originalStroke = g2d.getStroke();
                BasicStroke thickerStroke = new BasicStroke(3.0f); 
                g2d.setStroke(thickerStroke);
                g2d.setColor(color);
                
                // If theres a skin, do not fillOval as it overrides skin
                if(piece.colour.equals("#8080FF") && (skin == "einstein" || skin == "detective" ||
                        skin == "king")){
                }
                else{
                    g2d.fillOval(x, y, size, size);
                    
                }
                
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.setColor(Color.BLACK);
                String number = "2";
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (size - fm.stringWidth(number)) / 2;
                int textY = y + size / 2 + fm.getAscent() / 2;
                g2d.drawString(number, textX, textY);
                g2d.setStroke(originalStroke);   
            }
            
            // If its the third piece paint a 3
            else if(piece.order == 3){
                int x = piece.getXpos(); 
                int y = piece.getYpos(); 
                int size = 30;     
                Color color = Color.decode(piece.getColour());
                Graphics2D g2d = (Graphics2D) g;
                Stroke originalStroke = g2d.getStroke();
                BasicStroke thickerStroke = new BasicStroke(3.0f); 
                g2d.setStroke(thickerStroke);
                g2d.setColor(color);
                
                // If theres a skin, do not fillOval as it overrides skin
                if(piece.colour.equals("#8080FF") && (skin == "einstein" || skin == "detective"
                        || skin == "king")){
                }
                else{
                    g2d.fillOval(x, y, size, size);
                    
                }
                
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.setColor(Color.BLACK);
                String number = "3";
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (size - fm.stringWidth(number)) / 2;
                int textY = y + size / 2 + fm.getAscent() / 2;
                g2d.drawString(number, textX, textY);
                g2d.setStroke(originalStroke);    
            }
            
            // If its fourth piece paint a 4
            else{
                int x = piece.getXpos(); 
                int y = piece.getYpos(); 
                int size = 30;     
                Color color = Color.decode(piece.getColour());
                Graphics2D g2d = (Graphics2D) g;
                Stroke originalStroke = g2d.getStroke();
                BasicStroke thickerStroke = new BasicStroke(3.0f); 
                g2d.setStroke(thickerStroke);
                g2d.setColor(color);
                
                // If theres a skin, do not fillOval as it overrides skin
                if(piece.colour.equals("#8080FF") && (skin == "einstein" || skin == "detective"
                        || skin == "king")){
                }
                else{
                    g2d.fillOval(x, y, size, size);
                    
                }
                
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.setColor(Color.BLACK);
                String number = "4";
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (size - fm.stringWidth(number)) / 2;
                int textY = y + size / 2 + fm.getAscent() / 2;
                g2d.drawString(number, textX, textY);
                g2d.setStroke(originalStroke);   
            }
            
        
        }
    }
}


/*
public class GameBoard extends JPanel {
    private ArrayList<gamepiece> pieces;
    private BackgroundPanel backgroundPanel; // Declare BackgroundPanel

    public GameBoard(ArrayList<gamepiece> pieces, BackgroundPanel backgroundPanel) {
        this.pieces = pieces;
        this.backgroundPanel = backgroundPanel; // Initialize with the provided BackgroundPanel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the game board background here if needed.
        // Use the provided BackgroundPanel for the background image
        if (backgroundPanel != null) {
            backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
            backgroundPanel.paintComponent(g);
        }

        // Iterate through the game pieces and draw them
        for (gamepiece piece : pieces) {
            // Assuming pieceSize is the size of each game piece
            int pieceSize = 30;
            g.setColor(Color.decode(piece.getColour()));
            g.fillOval(piece.getXpos(), piece.getYpos(), pieceSize, pieceSize);
        }
    }
}
class BackgroundPanel extends JPanel {
    private BufferedImage background;

    public BackgroundPanel(BufferedImage background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
*/
 