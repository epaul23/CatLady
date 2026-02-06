/*
 * This is part of the test suite.
 */

import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
/**
 * This is a test class for model.Action.
 * This includes tests for DrawCardsAction, SprayBottleAction and LostCatAction.
 * @author Emil Paul
 * @author Asher Lawrence
 * @version 17/3/2025
 */
public class TestActions {
    Hand hand;
    Hand hand2;
    Board board;
    DrawCardsAction drawCardsAction;
    SprayBottleAction sprayBottleAction;
    LostCatAction lostCatAction;

    /**
     * Creates a custom board to use for tests, as well as Hands and Actions.
     */
    @BeforeEach
    void setup(){
        hand= new Hand("player");
        hand2= new Hand("player");
        board = new Board(2);
        drawCardsAction = new DrawCardsAction();
        sprayBottleAction = new SprayBottleAction();
        lostCatAction = new LostCatAction();
        board.getBoard()[0][0]= new CatCard("Pablo Picatso", "B", new String[]{"tuna","tuna","tuna"}, 6);
        board.getBoard()[0][1]= new CatCard("Sox", "W", new String[]{"tuna","tuna","milk"}, 6);
        board.getBoard()[0][2]= new CatCard("Blackberry", "B", new String[]{"chicken","chicken"}, 3);
        board.getBoard()[1][0]= new Card("Costume card");
        board.getBoard()[1][1]= new FoodCard("Food card", "wild");
        board.getBoard()[1][2]= new FoodCard("Food card", "2x tuna"); 
        board.getBoard()[2][0]= new ToyCard("Toy card", "mouse toy");
        board.getBoard()[2][1]= new Card("Spray bottle card");
        board.getBoard()[2][2]= new Card("Lost cat card");
    }

    /**
     * This is a test for DrawCardsAction.performAction().
     */
    @Test
    void testDrawCardsActionPerformAction(){
        drawCardsAction.performAction(hand, board,"a");
        assertEquals(1, hand.getCatCards().size());
        assertEquals(1, hand.getToyCards().size());
        assertEquals(1, hand.getNumberOf("Costume card"));
    }

    /**
     * This is a test for DrawCardsAction.isValidAction().
     */
    @Test
    void testDrawCardsActionIsValidAction(){
        drawCardsAction.newTurnNotify(); // Notify that a new turn has started
        assertTrue(drawCardsAction.isValidAction(hand));
        
        drawCardsAction.performAction(hand, board, "a");
        assertFalse(drawCardsAction.isValidAction(hand)); 
    }
    
    /**
     * This is a test for SprayBottleAction.performAction().
     */
    @Test
    void testSprayBottleActionPerformAction() {
        hand.addCard(new Card("Spray bottle card"));
        assertEquals(1, hand.getNumberOf("Spray bottle card"));
        
        sprayBottleAction.newTurnNotify(); // Notify that a new turn has started
        assertTrue(sprayBottleAction.isValidAction(hand));

        sprayBottleAction.performAction(hand, board, "a");
        assertEquals(0, hand.getNumberOf("Spray bottle card")); // Should be removed after action
    }
    
    /**
     * Tests performAction when no spray bottle card is present.
     */
    @Test
    void testPerformActionWithoutSprayBottle() {
        assertFalse(sprayBottleAction.isValidAction(hand));
        
        sprayBottleAction.performAction(hand, board, "b");
        assertEquals(0, hand.getNumberOf("Spray bottle card"));
    }
    
    /**
     * Tests isValidAction with and without a spray bottle card.
     */
    @Test
    void testIsValidAction() {
        assertFalse(sprayBottleAction.isValidAction(hand)); // Should be invalid without spray bottle
        
        hand.addCard(new Card("Spray bottle card"));
        sprayBottleAction.newTurnNotify(); // Notify that a new turn has started
        assertTrue(sprayBottleAction.isValidAction(hand)); // Should be valid now
    }
    
 }

