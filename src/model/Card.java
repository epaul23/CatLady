/*
 * This file contains the Card class.
 */

package model;

/**
 * A class representing a card in the game.
 * 
 * @author Serena Davis
 * @version 3-14-2025
 */
public class Card {
    /**
     * The type of card.
     */
    private String type;

    /**
     * Class constructor specifying type of card.
     * 
     * @param type The type of card.
     */
    public Card(String type){
        this.type = type;
    }

    /**
     * Returns the card type. 
     * 
     * @return A String representing the card type.
     */
    public String getType(){
        return type;
    }

    /**
     * Returns a string to display the card.
     * 
     * @return A string of the card information.
     */
    @Override
    public String toString(){
        return type;
    }
}
