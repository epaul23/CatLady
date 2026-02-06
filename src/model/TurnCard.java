/*
 * This file contains the TurnCard class. 
 */

package model;

/**
 * This class represents a Turn Card.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-29-2025
 */
public class TurnCard extends Card {
    /**
     * Contains the description or title of the card
     */
    private String description;

    /**
     * Contains the direction of the arrow on the card: "row" or "column"
     */
    private String direction;

    /**
     * The line title, ex. A, E.
     */
    private String line;

    /**
     * Constructor instantiating the description, direction, and line.
     * 
     * @param description The card description.
     * @param direction The card direction.
     * @param line The line indicated on the card.
     */
    public TurnCard(String description, String direction, String line){
        super("Turn card");
        this.description = description;
        this.direction = direction;
        this.line = line;
    }

    /**
     * Method to get the description of the card.
     * 
     * @return The card's description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method to get the direction of the card.
     * 
     * @return The direction of the card.
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Method to get the line title of the card.
     * 
     * @return The line indicated on the card.
     */
    public String getLine(){
        return line;
    }
}
