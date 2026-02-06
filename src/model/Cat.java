/*
 * This file contains the Cat interface. 
 */

package model;

/**
 * An abstract class that is extended by all cat cards.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-26-2025
 */
public abstract class Cat extends Card {
    /**
     * The cat's name.
     */
    private String name;
    /**
     * The cat's colour.
     */
    private String colour;
    /**
     * The cat's food requirements.
     */
    private String[] food;
    /**
     * True if the cat is fed, false otherwise.
     */
    private boolean isFed;
    /**
     * True if the cat was fed with a treat, false otherwise.
     */
    private boolean fedWithTreat;

    /**
     * Class constructor specifying card type, name, colour, and food requirements. 
     * 
     * @param type A string representing the type of card.
     * @param name A string representing the cat's name.
     * @param colour A string representing the cat's colour.
     * @param food An array of strings representing the cat's required food.
     */
    public Cat(String type, String name, String colour, String[] food){
        super(type);
        this.name = name;
        this.colour = colour;
        this.food = food;
        isFed = false;
        fedWithTreat = false;
    }

    /**
      * Returns the name of the cat.
      * 
      * @return A string representing the cat's name.
      */
    public String getName(){
        return name;
    }

    /**
      * Returns the colour of the cat.
      * 
      * @return A string representing the cat's colour. 
      */
    public String getColour(){
        return colour;
    }

    /**
      * Returns the food the cat requires.
      * 
      * @return An array of strings representing the cat's required food.
      */
    public String[] getFood(){
        return food;
    }

    /**
      * Returns the fed status of the cat
      * 
      * @return True if the cat is fed, false if not.
      */
    public boolean isFed(){
        return isFed;
    }

    /**
      * Changes the fed status of the cat. 
      *
      * @return void
      */
    public void changeFedStatus(){
        if (isFed == false){
          isFed = true;
        } 
        else {
          isFed = false;
          fedWithTreat = false;
        }
    }

    /**
     * Returns whether the cat was fed with a treat.
     * 
     * @return True if the cat was fed with a treat, false otherwise.
     */
    public boolean isFedWithTreat(){
        return fedWithTreat;
    }

    /**
     * Changes the fed status of the cat after being fed a treat.
     * 
     * @return void
     */
    public void feedWithTreat(){
        isFed = true;
        fedWithTreat = true;
    }

    /**
     * Returns a string to display the card.
     * 
     * @return A string of the card information.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(": ");
        sb.append("  Name: ");
        sb.append(name);
        sb.append(",  Colour: ");
        sb.append(colour);
        sb.append(",  Food: ");
        for(int i = 0; i < food.length; i++){
          sb.append(food[i]);
          if(i != food.length-1){
            sb.append(", ");
          }
        }
        sb.append(",  Is fed: ");
        sb.append(isFed);

        return sb.toString();
    }
}
