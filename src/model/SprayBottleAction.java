/**
 * This file contains the SprayBottleAction class.
 */

package model;

import java.util.List;

/**
 * This class represents the action of using a spray bottle card to move the cat figure.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-28-25
 */
public class SprayBottleAction implements Action, LaserPointerObserver {
    /**
     * The action's name.
     */
    private static final String NAME = "spray bottle";
    /**
     * The action's validity based on the progress of the turn.
     */
    private boolean isValid;

    /**
     * Performs an action using the provided hand, board and input.
     * 
     * @param hand The player's hand.
     * @param board The game board.
     * @param input The input required for the action.
     * @return void
     */
    @Override
    public void performAction(Hand hand,Board board,String input){
      if(isValidAction(hand)){
          hand.removeCard("Spray bottle card");
          board.moveCat(input);
      }
    }

    /**
    * Checks if action is valid based on the player's hand.
    * 
    * @param hand The player's hand.
    * @return True if the hand contains at least one spray bottle card and is valid at the
    * current point in the turn, false otherwise.
    */
    @Override
    public boolean isValidAction(Hand hand){
      if(isValid){
        return hand.getNumberOf("Spray bottle card") >= 1;
      }
      return false;
    }

    /**
     * Returns a list of the valid line choices for the action.
     * 
     * @param board The game board.
     * @return A list of valid input options.
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
