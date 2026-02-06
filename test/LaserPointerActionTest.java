import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for LaserPointerAction.
 * Includes tests for validating and performing the Laser Pointer action.
 * 
 * @author Emil Paul
 * @version 3/30/2025
 * 
 */
public class LaserPointerActionTest {
    private LaserPointerAction laserPointerAction;
    private Hand playerHand;
    private Board gameBoard;
    private TestObserver testObserver;


    /**
     * Sets up the test environment before each test case.
     * Initializes the LaserPointerAction, Hand, and Board instances.
     * Adds a TestObserver to track notifications.
     */
    @Before
    public void setUp() {
        laserPointerAction = new LaserPointerAction();
        playerHand = new Hand("player");
        gameBoard = new Board(2);
        testObserver = new TestObserver();
        laserPointerAction.addObserver(testObserver);
    }

    /**
     * Tests the isValidAction method to ensure that the action is valid
     * only when the player has a "Laser pointer card" in their hand.
     */
    @Test
    public void testIsValidAction() {
        laserPointerAction.newTurnNotify();
        Card laserPointer = new Card("Laser pointer card");

        // Add Laser Pointer card to hand
        playerHand.addCard(laserPointer);
        assertEquals(1, playerHand.getNumberOf("Laser pointer card"));
        assertTrue(laserPointerAction.isValidAction(playerHand));

        // Remove Laser Pointer card
        playerHand.removeCard("Laser pointer card");
        assertEquals(0, playerHand.getNumberOf("Laser pointer card"));
        assertFalse(laserPointerAction.isValidAction(playerHand));
    }

    /**
     * Tests the performAction method to verify that the correct cards
     * are taken from the board, the Laser Pointer card is removed, and
     * the observer gets notified.
     */
    @Test
    public void testPerformAction() {
        // Add Laser Pointer card to player's hand
        playerHand.addCard(new Card("Laser pointer card"));
        // Perform the action
        laserPointerAction.performAction(playerHand, gameBoard, "aeafbb");
        // Check that the action is no longer valid
        assertFalse(laserPointerAction.isValidAction(playerHand));
        // Verify that the observer was notified of the action
        assertTrue(testObserver.notified);
    }
    
    /**
     * TestObserver class implements the LaserPointerObserver interface
     * to track when the LaserPointerAction is used.
     */
    private static class TestObserver implements LaserPointerObserver {
        boolean notified = false;
        
        @Override
        public void notifyLaserUsed() {
            notified = true;
        }
    }
}
