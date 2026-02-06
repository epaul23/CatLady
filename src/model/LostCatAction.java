/**
 * This file contains the LostCatAction class.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs the action of using 2 lost cat cards in exchange for a stray cat card or token.
 *
 * @author Emil Paul
 * @author Serena Davis
 * @version 3-28-2025
 */
public class LostCatAction implements Action, LaserPointerObserver {
    /**
     * The action's name.
     */
    private static final String NAME = "lost cat";
    /**
     * The action's validity based on the progress of the turn.
     */
    private boolean isValid;

    /**
     * Checks if action is valid.
     * 
     * @param hand The player's hand.
     * @return True if the action if the hand contains at least 2 lost cat cards and is valid at
     * the current point in the turn, false otherwise.
     */
    @Override
    public boolean isValidAction(Hand hand) {
        if(isValid){
            return hand.getNumberOf("Lost cat card") >= 2;
        }
        return false;
    }

    /**
     * Performs the action of trading in 2 lost cat cards for a stray cat card or token.
     * 
     * @param hand The player's hand.
     * @param board The game board.
     * @param input The input required for the action. 
     * @return void
     */
    @Override
    public void performAction(Hand hand, Board board, String input) {
        //remove 2 lost cat cards
        hand.removeCard("Lost cat card");
        hand.removeCard("Lost cat card");

        //input is to recieve token
        if(input.equalsIgnoreCase("token")){
            hand.addToken();
            return;
        }

        //input is to recieve a Stray cat
        Card strayCat=board.getStrayCatCard(input);
        if(strayCat!=null){
            hand.addCard(strayCat);

        //Player recieves a token if no stray cats left    
        }else{
            hand.addToken();
        }
    }

    /**
     * Returns a list of the valid card choices (or token).
     * 
     * @param board The game board.
     * @return A list of valid input options.
     */
    @Override
    public List<String> getValidInputs(Board board){
        ArrayList<String> cats = new ArrayList<>();
        for(Card cat: board.getStrayCatDeck().getCards()){
            cats.add(((Cat) cat).getName().toLowerCase());
        }
        cats.add("token");
        return cats;
    }

    /**
     * Returns a description of the expected input for the action.
     * 
     * @return The String description.
     */
    @Override
    public String getInputMessage(){
        return "Which stray cat card do you want to take? Please enter the cat's name or 'token' if you want a token instead.";
    }
             
    /**
     * Returns the name of the action.
     * 
     * @return The name of the action.
     */
    @Override
    public String getActionName(){
        return NAME;
    }

    /**
     * Changes the validity of the action to true when a new turn begins.
     * 
     * @return void
     */
    @Override
    public void newTurnNotify(){
        isValid = true;
    }

    /**
     * Changes the action's validity to false when the laser pointer action is used.
     * 
     * @return void
     */
    @Override
    public void notifyLaserUsed(){
        isValid = false;
    }
}
        
  
