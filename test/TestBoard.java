/*
 * This is part of the test suite.
 */

import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
/**
 * This is a test class for model.Board.
 * 
 * @author Asher Lawrence
 * @author Emil Paul
 * @version 17/3/2025
 */
public class TestBoard {
    Board gameBoard;
    DrawCardsAction drawCardsAction;
    Hand hand;

    /**
     * Creates a custom board to use for tests, as well as a DrawCardsAction and Hand.
     */
    @BeforeEach
    void setup(){
        gameBoard = new Board(2);
        // Fill the game board with cards from the deck
        gameBoard.getBoard()[0][0]= new CatCard("Pablo Picatso", "B", new String[]{"tuna","tuna","tuna"}, 6);
        gameBoard.getBoard()[0][1]= new CatCard("Sox", "W", new String[]{"tuna","tuna","milk"}, 6);
        gameBoard.getBoard()[0][2]= new CatCard("Blackberry", "B", new String[]{"chicken","chicken"}, 3);
        gameBoard.getBoard()[1][0]= new Card("Costume card");
        gameBoard.getBoard()[1][1]= new FoodCard("Food card", "wild");
        gameBoard.getBoard()[1][2]= new FoodCard("Food card", "2x tuna"); 
        gameBoard.getBoard()[2][0]= new ToyCard("Toy card", "mouse toy");
        gameBoard.getBoard()[2][1]= new Card("Spray bottle card");
        gameBoard.getBoard()[2][2]= new Card("Lost cat card");
        drawCardsAction = new DrawCardsAction();
        hand = new Hand("player");
    }

    /**
     * Function to check whether an array is full, to be used in tests.
     * 
     * @param array The array to check.
     * @return True if there is a card in every position, false otherwise.
     */
    public boolean isArrayFull(Card[][] array) {
        if (array == null) {
            return false;
        }
        
        for (int i=0; i<3; i++) {
            for(int j=0; j<3; j++){
            if (array[i][j] == null) {
                return false;
            }
        }
        }
        return true;
    }
 
    /**
     * These are tests for Board.takeLine() for taking columns.
     */
    @Test
    public void testTakeLine_Column() {
        // Test taking a column, e.g., column "b"
        List<Card> takenCards = gameBoard.takeLine("b");

        // Validate the cards taken from column "b"
        assertEquals(3, takenCards.size());
        assertEquals("Cat", takenCards.get(0).getType());  // First row, column "b"
        assertEquals("Food card", takenCards.get(1).getType());  // Second row, column "b"
        assertEquals("Spray bottle card", takenCards.get(2).getType());  // Third row, column "b"

        // Validate the board was updated correctly
        assertTrue(isArrayFull(gameBoard.getBoard()));
    }

    /**
     * These are tests for Board.takeLine() for taking rows.
     */
    @Test
    public void testTakeLine_Row() {
        // Test taking a row, e.g., row "f"
        List<Card> takenCards = gameBoard.takeLine("f");

        // Validate the cards taken from row "f"
        assertEquals(3, takenCards.size());
        assertEquals("Toy card", takenCards.get(0).getType());  // Row "f"
        assertEquals("Spray bottle card", takenCards.get(1).getType());
        assertEquals("Lost cat card", takenCards.get(2).getType());

        // Validate the board was updated correctly
        assertTrue(isArrayFull(gameBoard.getBoard()));
    }

}