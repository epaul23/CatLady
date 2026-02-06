/*
 * This file contains Action interface
 */

 package model;

import java.util.List;

/**
  * Represents actions that can be performed in the game
  * 
  * @author Emil Paul
  * @author Serena Davis
  * @version 3-27-2025
  */
 public interface Action {
     
    /**
     * Performs an action using the provided Hand, board and input
     * 
     * @param hand The player's hand.
     * @param board The game board.
     * @param input The input required for the action.
     * @return void
     */
    void performAction(Hand hand,Board board,String input);

    /**
     * Checks if action is valid based on the current state of the hand.
     * 
     * @param hand The player's hand.
     * @return True if action is valid, false otherwise.
     */
    boolean isValidAction(Hand hand);

    /**
     * Returns a list of the valid inputs that the action can use.
     * 
     * @param board The game board.
     * @return A list of valid input options.
     */
    List<String> getValidInputs(Board board);

    /**
     * Returns a description of what input the class requires.
     * 
     * @return A String description.
     */
    String getInputMessage();

    /**
     * Returns the name of the action.
     * 
     * @return The name of the action.
     */
    String getActionName();

    /**
     * Recieve a notification when a new turn begins.
     * 
     * @return void
     */
    void newTurnNotify();
 }