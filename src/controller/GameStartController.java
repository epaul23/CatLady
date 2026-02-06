/*
 *This file contains the GameStartController class. 
 */

package controller;

import model.Board;

/**
 * Sets up the game for playing by setting the initial position of the cat figure.
 * 
 * @author Serena Davis
 * @author 3-30-2025
 */
public class GameStartController {

    /**
     * Displays the board for the user, then places the cat figure on the row/column of their choice,
     * or places the cat figure on the leftmost column if it is a one player game.
     * 
     * @param board The game board.
     * @param inputController The InputController to get user input.
     * @param displayController The DisplayController to display information to the user.
     * @param numberOfPlayers The number of players in the game.
     * @return void
     */
    public void startGame(Board board, InputController inputController, DisplayController displayController, int numberOfPlayers){
        if(numberOfPlayers == 1){
            board.moveCat("a");
        }
        else {
            displayController.displayMessage(board.toString());
            displayController.displayMessage("Player "+numberOfPlayers+", please place the cat figure.");
            String input = inputController.getInput(board.getLineList(),"Which row/column would you like to place the cat figure on? Please enter a letter A-F.");
            board.moveCat(input);
        }
    }
}
