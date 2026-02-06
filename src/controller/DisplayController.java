/*
 * This file contains the DisplayController class.
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Card;
import model.Cat;
import model.Hand;
import view.DisplayView;

/**
 * This class controls how information about the game is displayed to the user.
 * 
 * @author Flora Campbell
 * @author Emil Paul
 * @author Serena Davis
 * @version 3-30-25
 */
public class DisplayController {
    /**
     * The DisplayView for use in the class.
     */
    private DisplayView displayView;

    /**
     * Constructor instantiating the DisplayView
     */
    public DisplayController(){
        displayView = new DisplayView();
    }

    /**
     * Displays a given set of cards.
     * 
     * @param cards The cards to display.
     * @return void
     */
    public void displayCards(ArrayList<? extends Card> cards){
        StringBuilder sb = new StringBuilder();
        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n\n");
        }
        displayView.display(sb.toString());
    }

    /**
     * Displays the board and a player's hand during their turn.
     * 
     * @param hand The hand to display.
     * @param board The game board.
     * @return void
     */
    public void displayTurn(Hand hand, Board board){
        displayView.display(board.toString());
        displayView.display(hand.toString());
    }

    /**
     * Displays the final scores.
     * 
     * @param score An array of the players' final scores.
     * @param hands The player's hands.
     * @return void
     */
    public void displayScore(int[] score, Hand[] hands){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < score.length; i++) {
            sb.append("\nThe score for "+hands[i].getType()+ ": "+score[i]);
        }
        displayView.display(sb.toString());
    }

    /**
     * Displays a given message.
     * 
     * @param message The message to be displayed.
     * @return void
     */
    public void displayMessage(String message){
        displayView.display(message);
    }

    /**
     * Displays information about cat feeding.
     * 
     * @param cats The player's cats.
     * @param foodNames The names of the foods.
     * @param foodAmounts The amount of each food.
     * @return void
     */
    public void displayCatFeeding(List<Cat> cats, String[] foodNames, int[] foodAmounts){
        StringBuilder sb = new StringBuilder();
        sb.append("\nThese are your cat cards:\n\n");
        for(Cat cat: cats){
            sb.append(cat.toString()).append("\n\n");
        }
        sb.append("These are your food cards:\n");
        for(int i = 0; i < foodNames.length; i++){
            sb.append(foodNames[i]).append(": ").append(foodAmounts[i]).append("\n");
        }
        displayView.display(sb.toString());
    }
}
