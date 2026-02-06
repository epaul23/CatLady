/*
 * This file contains the FoodCard class.
 */

package model;

/**
 * A class representing a food card in the game.
 * 
 * @author Serena Davis
 * @version 3-14-2025
 */
public class FoodCard extends Card {
    /**
     * The type of food.
     */
    private String foodType;

    /**
     * Class constructor specifying type of card and type of food.
     * 
     * @param cardType The type of card.
     * @param foodType The type of food.
     */
    public FoodCard(String cardType, String foodType){
        super(cardType);
        this.foodType = foodType;
    }

    /**
     * Returns the food type.
     * 
     * @return A string representing the food type.
     */
    public String getFoodType(){
        return foodType;
    }

    /**
     * Returns a string to display the card.
     * 
     * @return A string of the card information.
     */
    @Override
    public String toString(){
        return super.toString() + "\n" + foodType;
    }
}