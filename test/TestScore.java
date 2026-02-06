import model.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
   * This is a test class for model.Score.
   * 
   * @author Asher Lawrence
   * @author Emil Paul
   * @version 2/4/2025
*/
public class TestScore {
    private Hand player1;
    private Hand player2;
    private Hand catPlayerHand;
    private Score score;
    
    // Regular cat cards
    private CatCard sox;
    private CatCard blackberry;
    private CatCard pablo;
    
    // Stray cat cards
    private StrayCatCard penny;
    private StrayCatCard levar;
    private StrayCatCard eliot;

    /**
     * Set up Hands, Score, and Cats to be used in tests.
     */
    @BeforeEach
    void setup() {
        player1 = new Hand("player");
        player2 = new Hand("player");
        catPlayerHand = new Hand("cat player");
        score = new Score();
    
        sox = new CatCard("Sox", "W", new String[]{"tuna","tuna","milk"}, 6);
        blackberry = new CatCard("Blackberry", "B", new String[]{"chicken","chicken"}, 3);
        pablo = new CatCard("Pablo Picatso", "B", new String[]{"tuna","tuna","tuna"}, 6);
        
        levar = new StrayCatCard("LeVar Purrton", "W", new String[]{"tuna","chicken"}, "4 VP per fed cat.");
        penny = new StrayCatCard("Penny", "O", new String[]{"milk"}, "1 VP per toy.");
        eliot = new StrayCatCard("Eliot", "B", new String[]{"tuna","chicken"},"2 VP per black cat.");
    }

    /**
     * Test that regular cats are scored correctly.
     */
    @Test
    void testRegularCatScoring() {
        // Player 1 - Fed Blackberry and Sox
        player1.addCard(blackberry);//3vp not fed
        player1.addCard(sox);//6vp
        sox.changeFedStatus();//fed

        Hand[] hands = {player1, player2};
        int[] scores = score.calculateScore(hands);
        
        assertEquals(0, scores[0]); //6 for fed cat, -2 for unfed cat, -2 for no costumes, -2 for most food
        assertEquals(-4, scores[1]);//-2 for no costumes, -2 for most food
    }

    /**
     * Test that 2 points are removed for unfed cats.
     */
    @Test
    void testUnfedCatPenalty() {
        player1 = new Hand("player");
        player2 = new Hand("player");

        player1.addCard(pablo);
        
        int[] scores = score.calculateScore(new Hand[]{player1, player2});
        assertEquals(-6, scores[0]); // -2 for unfed cat, -2 for no costumes, -2 for most food
    }

    /**
     * Test that stray cat points are calculated correctly.
     */
    @Test
    void testCalculateStrayCatPoints() {
        player2.addCard(penny); //1vp per toy
        player2.addCard(new ToyCard("Toy card", "mouse toy"));
        penny.changeFedStatus();

        assertEquals(1, score.calculateStrayCatPoints("Penny", player2));

        player1.addCard(levar); //4vp per fed cat
        player1.addCard(sox); //6vp
        sox.changeFedStatus();
        levar.changeFedStatus();
        
        assertEquals(8, score.calculateStrayCatPoints("LeVar Purrton", player1)); 
        
        player2.addCard(eliot); //2vp per black cat
        player2.addCard(blackberry); //3vp
        blackberry.changeFedStatus();
        
        assertEquals(3, score.calculateStrayCatPoints("Eliot", player2)); 
    }

    /**
     * Test that the cat player recieves points for spray bottle cards and laser pointer
     * cards and normal players do not.
     */
    @Test
    void testCatPlayerBonuses() {
        catPlayerHand.addCard(new Card("Spray bottle card")); // +1
        catPlayerHand.addCard(new Card("Laser pointer card")); // +1
        int[] scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(-2, scores[1]); //-2 for no costumes, -2 for most food

        player1.addCard(new Card("Spray bottle card")); // +0 (regular player)
        player1.addCard(new Card("Laser pointer card")); // +0
        scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(-4, scores[0]); //-2 for no costumes, -2 for most food

    }

    /**
     * Test that points are removed if there are no costumes in the hand.
     */
    @Test
    void testNoCostumePenalty() {
        player1.addCard(blackberry);
        blackberry.changeFedStatus();//3 fed
        int[] scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(-1, scores[0]); // 3 for cat, -2 for no costumes, -2 for most food
    }

    /**
     * Test that catnip cards are scored correctly.
     */
    @Test
    void testCatnipScoring() {
        player1.addCard(pablo); // +6
        pablo.changeFedStatus();
        // 1 catnip penalty
        player1.addCard(new Card("Catnip card"));
        
        int[] scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(0, scores[0]); // 6 for cat, - 2 for catnip, -2 for no costumes, -2 for most food
        
        // 2 catnip bonus
        player1.addCard(new Card("Catnip card"));
        scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(3, scores[0]); // 6 + 1 (1 per fed cat), -2 for no costumes, -2 for most food
    
    }


    /**
     * Test that toys are scored correctly.
     */
    @Test
    void testToyScoring() {
        player1.addCard(new ToyCard("Toy card", "mouse toy")); // +1
        player1.addCard(new ToyCard("Toy card", "cat tower")); // +3 (set of 2)
        player1.addCard(new ToyCard("Toy card", "feather wand")); // +5 (set of 3)
        
        int[] scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(1, scores[0]); //3 toys = 5 VP and -2 for no costume cards, -2 for most food
    }

    /**
     * Test that tokens are scored correctly.
     */
    @Test
    void testTokenScoring() {
        player1.addCard(pablo);
        pablo.changeFedStatus();//6 fed
        player1.addToken(); // +2
        player1.addToken(); // +2
        catPlayerHand.addCard(sox);
        sox.changeFedStatus();//6 fed
        catPlayerHand.addToken(); // +2
        catPlayerHand.addToken();//+2

        int[] scores = score.calculateScore(new Hand[]{player1, catPlayerHand});
        assertEquals(6, scores[0]);//6+2+2 and -2 for no costumes, -2 for most food
        assertEquals(6, scores[1]);//6+2+2 and -2 for no costumes, -2 for most food

    }
}