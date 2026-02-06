/*
 * This file contains the LaserPointerAction class.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that carries out the action of using a laser pointer card to take cards
 * from the board.
 * 
 * @author Serena Davis
 * @version 3-28-2025
 */
public class LaserPointerAction implements Action, ContinuedTurnObserver {
    /**
     * The action's name.
     */
    private static final String NAME = "laser pointer";
    /**
     * The action's validiy based on the progress of the turn.
     */
    private boolean isValid;
    /**
     * A list of observers to be notified when the action is used.
     */
    private ArrayList<LaserPointerObserver> observers;

    /**
     * Class constructor instantiating the list of observers.
     */
    public LaserPointerAction(){
        observers = new ArrayList<>();
    }

    /**
     * Checks whether the action is currently valid or not.
     * 
     * @param hand The current player's hand.
     * @return True if the action is valid, false otherwise.
     */
    @Override
    public boolean isValidAction(Hand hand){
        if(isValid){
            return hand.getNumberOf("Laser pointer card") > 0;
        }
        return false;
    }

    /**
     * Carries out the action of using a laser pointer card.
     * 
     * @param hand The current player's hand.
     * @param board The game board.
     * @param input A String representing the 3 cards to take from the board.
     * @return void
     */
    @Override
    public void performAction(Hand hand, Board board, String input){
        hand.addCard(board.takeCard(input.substring(0,2)));
        hand.addCard(board.takeCard(input.substring(2,4)));
        board.takeCard(input.substring(4));
        hand.removeCard("Laser pointer card");
        isValid = false;
        for(LaserPointerObserver observer: observers){
            observer.notifyLaserUsed();
        }
    }

    /**
     * Returns a list of valid input for the action.
     * 
     * @param board The game board.
     * @return A list of valid input options.
     */
    @Override
    public List<String> getValidInputs(Board board){
        List<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");
        lines.add("c");
        lines.add("d");
        lines.add("e");
        lines.add("f");
        return lines;
    }

    /**
     * Returns a description of the expected input for the action.
     * 
     * @return The String description.
     */
    @Override
    public String getInputMessage(){
        return "Choose 3 cards. The first two you choose you will get to keep, and the third will be discarded.";
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
     * Changes the action's validity to false when a turn continues past one action.
     * 
     * @return void
     */
    @Override
    public void continuedTurnNotify(){
        isValid = false;
    }

    /**
     * Adds a new observer to the action's list of observers.
     * 
     * @param observer The observer to be added.
     * @return void
     */
    public void addObserver(LaserPointerObserver observer){
        observers.add(observer);
    }
}
