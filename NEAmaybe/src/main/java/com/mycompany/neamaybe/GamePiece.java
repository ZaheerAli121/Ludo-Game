package com.mycompany.neamaybe;

// Class to represent a game piece in a board game
public class GamePiece {
    
    // Instance variables to store piece properties
    public String colour;   // Color of the game piece
    public int xpos;        // X-coordinate position on the game board
    public int ypos;        // Y-coordinate position on the game board
    public boolean active;  // Flag indicating whether the piece is active or not
    public int order;       // Order or index of the piece
    
    // Constructor to initialize the game piece with given properties
    public GamePiece(int xpos, int ypos, boolean active, String colour, int order) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.active = active;
        this.colour = colour;
        this.order = order;
    } 
    
    // Method to update the coordinates of the game piece
    public void updateCoordinates(int newX, int newY) {
        this.xpos = newX;
        this.ypos = newY;   
    }
   
    // Getter method for the color of the game piece
    public String getColour() {
        return colour;
    }

    // Getter method for the X-coordinate position
    public int getXpos() {
        return xpos;
    }

    // Getter method for the Y-coordinate position
    public int getYpos() {
        return ypos;
    }

    // Getter method for the active status of the game piece
    public boolean isActive() {
        return active;
    }

    // Setter method to update the active status of the game piece
    public void setActive(boolean active) {
        this.active = active;
    }
    
    // Getter method for the order or index of the game piece
    public int getOrder() {
        return order;
    }
    
}



/*
package com.mycompany.neamaybe;

public class GamePiece {
    public String colour;
    public int xpos;
    public int ypos;
    public boolean active;
    public int order;
    public boolean won;
    public GamePiece(int xpos, int ypos, boolean active, String colour, int order, boolean won){
        this.xpos = xpos;
        this.ypos = ypos;
        this.active = active;
        this.colour = colour;
        this.order=order;
        this.won=won;
    } 
    
   public void updateCoordinates(int newX,int newY){
       this.xpos = newX;
       this.ypos = newY;   
   }
   
    public String getColour() {
        return colour;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getOrder(){
        return order;
    }
    
    public boolean isWon(){
        return won;
    }
    
   
    
    
}
*/