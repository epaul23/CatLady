/*
 * This file contains TurnController class
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import model.Action;
import model.Board;
import model.CatPlayerAction;
import model.ContinuedTurnObserver;
import model.DrawCardsAction;
import model.Hand;
import model.LaserPointerAction;
import model.LaserPointerObserver;
import model.LostCatAction;
import model.SprayBottleAction;

/**
 * This class controls a player's turn. 
 * 
 * @author Emil Paul
 * @author Serena Davis
 * @version 3-31-2025
 */
public class TurnController {
    /**
     * A list of actions available to normal players in the game.
     */
    private ArrayList<Action> actions;
    /**
     * Observers to nofity when a turn continues past one action.
     */
    private ArrayList<ContinuedTurnObserver> continuedTurnObservers;
    /**
     * The action to perform during a cat player's turn.
     */
    private CatPlayerAction catPlayerAction;


    /**
     * Class constructor instantiating the lists of observers. 
     */
    public TurnController(){
        catPlayerAction = new CatPlayerAction();
        actions = new ArrayList<>();
        continuedTurnObservers = new ArrayList<>();
        actions.add(new DrawCardsAction());
        actions.add(new SprayBottleAction());
        actions.add(new LostCatAction());
        LaserPointerAction laser = new LaserPointerAction();
        for(Action action: actions){
            laser.addObserver((LaserPointerObserver) action);
        }
        actions.add(laser);
        continuedTurnObservers.add(laser);
    }

    /**
     * This method starts the turn, and performs actions for the current player until
     * they are finished with their turn.
     * 
     * @param currentHand The current player's hand.
     * @param board The game board.
     * @param display The controller responsible for displaying game content.
     * @param input The controller responsible for handling user input.
     * @return void
     */
    public void startTurn(Board board,Hand currentHand,DisplayController display,InputController input){
        //cat player's turn
        if(currentHand.getType().equals("cat player")){
            catPlayerTurn(board,currentHand,display,input);
        }
        //normal player's turn
        else {

            for(Action observer: actions){
                observer.newTurnNotify();
            }

            //flag to track if current player's turn is still active
            boolean activeTurn=true;

            while(activeTurn){
                //Display current game state
                display.displayTurn(currentHand,board);

                //Ask player for action
                ArrayList<String> validActions = new ArrayList<>();
                for(Action action: actions){
                    if(action.isValidAction(currentHand)) validActions.add(action.getActionName());
                }
                validActions.add("finish");
                String actionName = input.getInput(validActions,"What action would you like to do? Please enter one of the following options: "+validActions.toString());
                Action action = null;
                for(Action actionItem: actions){
                    if(actionItem.getActionName().equals(actionName)){
                        action = actionItem;
                    }
                }

                try {
                    if(action.isValidAction(currentHand)){
                        String actionInput=input.getActionInput(action, board);
                        action.performAction(currentHand, board, actionInput);
                        for(ContinuedTurnObserver observer: continuedTurnObservers){
                            observer.continuedTurnNotify();
                        }
                    }else{
                        display.displayMessage("Invalid action, try something else!");
                    }
                } catch (NullPointerException e) {
                    activeTurn=false;
                    display.displayMessage("Turn Ended");
                }
            }
        }
    }

    /**
     * Runs a cat player's turn by performing the cat player action.
     * 
     * @param board The game board.
     * @param hand The cat player's hand.
     * @param display The controller responsible for displaying game content.
     * @param input The controller responsible for handling user input.
     * @return void
     */
    private void catPlayerTurn(Board board, Hand hand, DisplayController display, InputController input){
        List<String> lineOptions = catPlayerAction.decideLine(board);
        String line;
        if(lineOptions.size() != 1){
            line = input.getInput(lineOptions,"There is a tie for which line the cat player should take. Which line would you like them to take?\nThese are your options: "+lineOptions.toString());
        }
        else{
            line = lineOptions.get(0);
        }
        catPlayerAction.performAction(board,hand,line);
        display.displayMessage("The cat player took line "+line.toUpperCase()+" as indicated by a turn card.");
        display.displayMessage("The cat player's turn is now over.");
    }
    
}
