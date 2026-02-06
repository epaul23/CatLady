/*
 * This file contains the StrayCatCard class. 
 */

package model;

/**
 * A class representing a stray cat card.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-16-2025
 */
public class StrayCatCard extends Cat {
    /**
     * A description of the cat's unique points method.
     */
    private String pointsDescription;

    /**
     * Class constructor specifying the cat's name, colour, food requirements and points description.
     * 
     * @param name The cat's name.
     * @param colour The cat's colour.
     * @param food The cat's food.
     * @param pointsDescription The cat's points method.
     */
    public StrayCatCard(String name,String colour,String[] food, String pointsDescription){
        super("Stray cat",name,colour,food);
        this.pointsDescription = pointsDescription;
    }

    /**
     * Returns a string to display the card information.
     * 
     * @return A String of the card information.
     */
    @Override
    public String toString(){
        return super.toString() + ",  Points: " + pointsDescription;
    }

}
