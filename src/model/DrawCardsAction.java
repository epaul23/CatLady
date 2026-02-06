/*
 * This file contains the DrawCardsAction class.
 */

package model;

import java.util.List;

/**
 * A class that carries out the action of drawing cards from the board.
 * 
 * @author Serena Davis
 * @version 3-28-2025
 */
public class DrawCardsAction implements Action, LaserPointerObserver {
    /**
     * The action's validity based on the progress of the turn.
     */
    private boolean isValid;
    /**
     * The action's name.
     */
    private static final String NAME = "draw cards";

    /**
     * Checks whether the action is currently valid or not.
     * 
     * @param hand The current player's hand.
     * @return True if the action is valid, false if not.
     */
    @Override
    public boolean isValidAction(Hand hand){
        return isValid;
    }

    /**
     * Carries out the action of drawing cards from the board.
     * 
     * @param hand The current player's hand.
     * @param board The game board.
     * @param input A String representing the line of cards to take.
     * @return void
     */
    @Override
    public void performAction(Hand hand, Board board, String input){
        List<Card> cards = board.takeLine(input);
        for(Card card: cards){
            hand.addCard(card);
        }
        isValid = false;
    }

    /**
     * Returns the list of valid line choices.
     * 
     * @param board The game board.
     * @return A list of valid line choices. 
     */
    @Override
    public List<String> getValidInputs(Board board){
        return board.getLineList();
    }

    /**
     * Returns a description of the input the action expects.
     * 
     * @return The String description. 
     */
    @Override
    public String getInputMessage(){
        return "Which row or column would you like to choose? Please enter a letter from A-F. You cannot choose the line the cat figure is on.";
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
     * Changes the action's validity to true when a new turn begins.
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
