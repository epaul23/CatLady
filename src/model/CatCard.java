/*
 * This file contains the CatCard class. 
 */

package model;

/**
 * A class representing a cat card.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-14-2025
 */
public class CatCard extends Cat {
    /**
     * The cat's points.
     */
    private int points;

    /**
     * Class constructor specifying type of card, name, colour, required food, and points.
     * 
     * @param name The cat's name.
     * @param colour The cat's colour.
     * @param food The cat's required food.
     * @param points The cat's points.
     */
    public CatCard(String name, String colour, String[] food, int points){
        super("Cat",name,colour,food);
        this.points = points;
    }

    /**
    * Returns the cat's points.
    * 
    * @return The number of points.
    */
    public int getPoints(){
        return points;
    }

    /**
     * Returns a string to display the card.
     * 
     * @return A string of the card information.
     */
    @Override
    public String toString(){
        return super.toString() + ",  Points: " + points;
    }
}
