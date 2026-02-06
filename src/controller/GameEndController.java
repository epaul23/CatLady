/*
 * This file contains GameEndController class
 */

package controller;

/**
 * This class oversees the end of the game by directing other classes to perform
 * various responsibilities.
 * 
 * @author Emil Paul
 * @author Serena Davis
 * @version 3-16-2025
 */
import model.Hand;
import model.Score;

public class GameEndController {
    /**
     * The controller that oversees the feeding of players' cats.
     */
    private CatFeedingController catFeedingController;
    /**
     * The class that calculates the scores of the players.
     */
    private Score score;

    /**
     * Constructor instantiating the CatFeedingController and score 
     * 
     */
    public GameEndController(){
        this.catFeedingController= new CatFeedingController();
        this.score= new Score();
    }
    
    /**
     * This method starts when gameplay is done. It directs other classes to feed the cats in 
     * the players' hands, and calculate and display the scores.
     * 
     * @param hands An array containing each player's hand.
     * @param inputController The InputController responsible for handling inputs.
     * @param displayController The DisplayController responsible for displaying final game results.
     * @return void
     */        
    public void endGame(Hand[] hands,InputController inputController,DisplayController displayController){
        
        displayController.displayMessage("The game has ended! Players will now feed their cats.");

        //feed the cats based on each hand in Hand[] array
        for(int i = 0; i < hands.length; i++){
            displayController.displayMessage(hands[i].getType()+", please feed your cats.");
            catFeedingController.feedCats(hands[i], inputController, displayController);
        }
    
        //calculate final scores in both hands and store in array
        int[] finalScores=score.calculateScore(hands);

        //displays final scores
        displayController.displayScore(finalScores,hands);
        
    }
}
