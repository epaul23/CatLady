/**
 * This file contains the ManagerController class
 */

package controller;

import java.util.Scanner;
import model.Board;
import model.Hand;
import model.HandFactory;

/**
 * This class manages the game controllers.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 4-1-25
 */
public class ManagerController {
    /**
     * The controller that starts the game.
     */
    private GameStartController gameStartController;
    /**
     * The controller that ends the game.
     */
    private GameEndController gameEndController;
    /**
     * The controller that checks user input.
     */
    private InputController inputController;
    /**
     * The controller that runs turns.
     */
    private TurnController turnController;
    /**
     * The controller that coordinates displaying information.
     */
    private DisplayController displayController;
    /**
     * The game board.
     */
    private Board board;
    /**
     * The hands of all players.
     */
    private Hand[] hands;
    /**
     * A Scanner for use in InputView.
     */
    private Scanner scanner;

    /**
     * Constructor instantiating the controllers and scanner.
     */
    public ManagerController(){
        gameStartController = new GameStartController();
        gameEndController = new GameEndController();
        scanner = new Scanner(System.in);
        inputController = new InputController(scanner);
        turnController = new TurnController();
        displayController = new DisplayController();
    }

    /**
     * This method oversees the whole game.
     * 
     * @return void
     */
    public void startGame(){
        //set up the game
        int numberOfPlayers = Integer.valueOf(inputController.getInput(new String[]{"1","2","3","4"},"How many players are there? (The maximum number is 4)"));
        board = new Board(numberOfPlayers);
        hands = HandFactory.createHands(numberOfPlayers,board.getStrayCatDeck());
        
        //start the game
        gameStartController.startGame(board, inputController, displayController, numberOfPlayers);

        //run turns
        int player = 0;
        while (!board.getStandardDeck().isEmpty()){
            displayController.displayMessage("\n\nIt is now "+hands[player].getType()+"'s turn.\n");
            turnController.startTurn(board, hands[player], displayController, inputController);
            player = (player + 1) % hands.length;
        }

        //end the game
        if (board.getStandardDeck().isEmpty()){
            gameEndController.endGame(hands, inputController, displayController);
        }
        scanner.close();
    }
}
