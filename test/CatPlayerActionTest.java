import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the CatPlayerAction class
 * Includes tests for performAction and deciding lines baseed on turncards
 * 
 * @author Emil Paul
 * @version 30/3/2025
 * 
 */
class CatPlayerActionTest {
    private CatPlayerAction catPlayerAction;
    private Board board;
    private Hand hand;

    /**
     * Sets up the test environment before each test case.
     * Initializes the CatPlayerAction, Board, and Hand instances,
     * and populates the board with predefined cards.
     */

    @BeforeEach
    void setUp() {
        catPlayerAction = new CatPlayerAction();
        board = new Board(2);
        hand = new Hand("cat player");

        // board setup
        board.getBoard()[0][0] = new ToyCard("Toy card", "mouse toy");  
        board.getBoard()[1][0] = new ToyCard("Toy card", "cat tower");  
        board.getBoard()[2][0] = new ToyCard("Toy card", "ball");      

        board.getBoard()[0][1] = new ToyCard("Toy card", "yarn ball");  
        board.getBoard()[1][1] = new FoodCard("Food card", "wild");     
        board.getBoard()[2][1] = new FoodCard("Food card", "tuna");     

        board.getBoard()[0][2] = new FoodCard("Food card", "wild");     
        board.getBoard()[1][2] = new FoodCard("Food card", "2x tuna");  
        board.getBoard()[2][2] = new Card("Lost cat card");           
    }

    /**
     * Tests the performAction method of CatPlayerAction
     * Verifies that the correct number of Toy cards are added to the hand
     * when selecting a specific line (column "a").
     */
    @Test
    void testPerformAction() {
        // Selecting column "a" [0,0], [1,0], [2,0] == 3 Toy Cards
        String selectedLine = "a";
        catPlayerAction.performAction(board, hand, selectedLine);

        assertEquals(3, hand.getToyCards().size()); // Expecting 3 toy cards
        assertEquals(0, hand.getFoodCards().size());
        assertEquals(0, hand.getCatCards().size());
    }

    /**
     * Tests the decideLine method of CatPlayerAction.
     * Verifies that the correct line options are returned based on the turn card
     * when there are matching Food cards in the specified column.
     */
    @Test
    void testDecideLine() {
        // Set up a custom board, and gave Food Card to column d, and passed TurnCard with column b
        board.getBoard()[0][0] = new FoodCard("Food card", "tuna"); 
        board.getBoard()[0][1] = new FoodCard("Food card", "tuna"); 
        board.getBoard()[0][2] = new FoodCard("Food card", "tuna");       
        board.getBoard()[1][0] = new Card("Some other card");      
        board.getBoard()[1][1] = new ToyCard("Food card", "tuna");
        board.getBoard()[1][2] = new Card("Some other card");   
        board.getBoard()[2][0] = new Card("Some other card");
        board.getBoard()[2][1] = new Card("Some other card");
        board.getBoard()[2][2] = new Card("Some other card");

        List<TurnCard> customCards = new ArrayList<>();
        customCards.add(new TurnCard("Food card", "column", "b"));  
        TurnCardDeck customDeck = new TurnCardDeck(customCards);

        // Set the custom deck in the CatPlayerAction
        catPlayerAction.setTurnCardDeck(customDeck);

        // Call decideLine() and check results
        List<String> lineOptions = catPlayerAction.decideLine(board);

        // Assert that the expected line ("d") is in the options
        assertTrue(lineOptions.contains("d"));
    }

    /**
     * Tests the decideLine method when there are no matching cards, follows direction in turn card instead
     * Verifies that the correct line options are returned based on the turn card
     * when the board contains only non-matching cards.
     */
    @Test
    void testDecideNoMatchLine() {
        // Set up a custom board with no matching Food cards
        board.getBoard()[0][0] = new Card("Some other card");
        board.getBoard()[0][1] = new Card("Some other card");
        board.getBoard()[0][2] = new Card("Some other card");     
        board.getBoard()[1][0] = new Card("Some other card");      
        board.getBoard()[1][1] = new Card("Some other card");
        board.getBoard()[1][2] = new Card("Some other card");   
        board.getBoard()[2][0] = new Card("Some other card");
        board.getBoard()[2][1] = new Card("Some other card");
        board.getBoard()[2][2] = new Card("Some other card");

        List<TurnCard> customCards = new ArrayList<>();
        customCards.add(new TurnCard("Food card", "column", "b"));  
        TurnCardDeck customDeck = new TurnCardDeck(customCards);

        // Set the custom deck in the CatPlayerAction
        catPlayerAction.setTurnCardDeck(customDeck);

        // Call decideLine() and check results
        List<String> lineOptions = catPlayerAction.decideLine(board);

        // Assert that the expected line ("b") is in the options
        assertTrue(lineOptions.contains("b"));
    }

    /**
     * Tests the decideLine method in a tie-breaking scenario.
     * Verifies that the correct line option is chosen based on the turn card
     * when there is a tie in the number of matching Toy cards (Multiple row/columns tie)
     */
    @Test
    void testTieBreakerMulipleDecideLine() {
        // Set up a custom board, and gave 2 Toy Cards to column a and column b
        board.getBoard()[0][0] = new ToyCard("Toy card", "yarn ball"); 
        board.getBoard()[0][1] = new ToyCard("Toy card", "yarn ball"); 
        board.getBoard()[0][2] = new Card("Some other card");     
        board.getBoard()[1][0] = new ToyCard("Toy card", "yarn ball");      
        board.getBoard()[1][1] = new ToyCard("Toy card", "yarn ball"); 
        board.getBoard()[1][2] = new Card("Some other card");   
        board.getBoard()[2][0] = new Card("Some other card");
        board.getBoard()[2][1] = new Card("Some other card");
        board.getBoard()[2][2] = new Card("Some other card");

        List<TurnCard> customCards = new ArrayList<>();
        customCards.add(new TurnCard("Toy card", "column", "c"));  
        TurnCardDeck customDeck = new TurnCardDeck(customCards);

        // Set the custom deck in the CatPlayerAction
        catPlayerAction.setTurnCardDeck(customDeck);

        // Call decideLine() and check results
        List<String> lineOptions = catPlayerAction.decideLine(board);

        // Assert that the expected line ("a"and "b") is in the options
        //If there's a tie, the actual player is supposed to decide which line it takes 
        //- that's why it returns both options
        assertTrue(lineOptions.contains("a"));
        assertTrue(lineOptions.contains("b"));
    }
    
    /**
     * Tests tie-breaking when a row and a column both have the most of the required card type.
     * The arrow on the turn card should determine whether to take from the row or column.
     */
    @Test
    void testTieBreakerWithArrowDirection() {
        // Scenario where a row"e" and a column"a" tie in card count, should pick line based on arrow in turncard

        //Column a
        board.getBoard()[0][0] = new FoodCard("Food card", "wild"); 
        board.getBoard()[1][0] = new FoodCard("Food card", "wild"); 
        board.getBoard()[2][0] = new FoodCard("Food card", "wild"); 

        //ROw e
        board.getBoard()[1][0] = new FoodCard("Food card", "wild"); 
        board.getBoard()[1][1] = new FoodCard("Food card", "wild"); 
        board.getBoard()[1][2] = new FoodCard("Food card", "wild"); 

        List<TurnCard> customCards = new ArrayList<>();
        customCards.add(new TurnCard("Food card", "row", "f")); // Arrow points to row "e"
        TurnCardDeck customDeck = new TurnCardDeck(customCards);
        
        catPlayerAction.setTurnCardDeck(customDeck);
        List<String> lineOptions = catPlayerAction.decideLine(board);

        System.out.println("Line options: " + lineOptions);
        assertEquals(1, lineOptions.size());
        assertTrue(lineOptions.contains("e"));
        assertFalse(lineOptions.contains("a"));
        
    }
}




