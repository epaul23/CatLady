/*
 * This file contains the ToyCard class.
 */

 package model;

 /**
  * A class representing a toy card in the game.
  * 
  * @author Serena Davis
  * @version 3-14-2025
  */
 public class ToyCard extends Card {
    /**
     * The type of toy.
     */
    private String toyType;

    /**
     * Class constructor specifying type of card and type of toy.
     * 
     * @param cardType The type of card.
     * @param toyType The type of toy.
     */
    public ToyCard(String cardType, String toyType){
        super(cardType);
        this.toyType = toyType;
    }
    
    /**
     * Returns the toy type.
     * 
     * @return A string representing the toy type.
     */
    public String getToyType(){
        return toyType;
    }

    /**
     * Returns a string to display the card.
     * 
     * @return A string of the card information.
     */
    @Override
    public String toString(){
        return super.toString() + "\n" + toyType;
    }
 }