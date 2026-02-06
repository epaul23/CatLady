/**
 * This file contains the StartingCard class.
 */

package model;

/**
 * This class represents a Starting Card.
 * 
 * @author Flora Campbell
 * @version 3-28-2025
 */
public class StartingCard extends Card {
    /**
     * Contains the title of the card. eg. "Stray Cat" or "Toy"
     */
    private String title;

    /**
     * Constructor instantiating the title and card type.
     * 
     * @param cardType
     * @param title
     */
    public StartingCard(String cardType, String title){
        super(cardType);
        this.title = title;
    }

    /**
     * Method to get the title of the card
     * 
     * @return The title of the card.
     */
    public String getTitle(){
        return title;
    }
}
