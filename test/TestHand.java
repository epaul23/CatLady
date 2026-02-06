/*
 * This is part of the test Hand
 */

import model.*;
 
import static org.junit.Assert.assertEquals;
 
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
/**
 * This is a test class for model.Hand.
 * 
 * @author Asher Lawrence
 * @author Emil Paul
 * @version 17/3/2025
 */
public class TestHand {
    Hand handOne;
    Hand handTwo;
    CatCard pabloCard;
    CatCard soxCard;
    CatCard blackberryCard;
    Card costumeCard;
    FoodCard wilFoodCard;
    FoodCard  doubleTuna;
    ToyCard mouseToyCard;
    Card sprayCard;
    Card lostCatCard;
    StrayCatCard waffleCard;

    /**
     * Create Hands and Cards to be used in tests.
     */
    @BeforeEach
    void setup(){
    handOne = new Hand("player");
    handTwo = new Hand("cat player");
    pabloCard= new CatCard("Pablo Picatso", "B", new String[]{"tuna","tuna","tuna"}, 6);
    soxCard= new CatCard("Sox", "W", new String[]{"tuna","tuna","milk"}, 6);
    blackberryCard= new CatCard("Blackberry", "B", new String[]{"chicken","chicken"}, 3);
    costumeCard= new Card("Costume card");
    wilFoodCard= new FoodCard("Food card", "wild");
    doubleTuna= new FoodCard("Food card", "2x tuna"); 
    mouseToyCard= new ToyCard("Toy card", "mouse toy");
    sprayCard= new Card("Spray bottle card");
    lostCatCard= new Card("Lost cat card");
    waffleCard= new StrayCatCard("Waffle", "orange", new String[]{"chicken","fish"}, "3 points");
   }

  /**
   * This is a test for Hand.addCard().
   */
  @Test
  void testAddCard(){
      //Add a cat card
      handOne.addCard(pabloCard);
      handOne.addCard(waffleCard);
      assertEquals(2, handOne.getCatCards().size());
      //Add a toy card
      handOne.addCard(mouseToyCard);
      assertEquals(1, handOne.getToyCards().size());
      //Add a food card
      handOne.addCard(doubleTuna);
      handOne.addCard(wilFoodCard);
      assertEquals(3, handOne.getFoodCards().size());//Check if food cards were added successfully
      handOne.addCard(costumeCard);
      assertEquals(1, handOne.getNumberOf("Costume card"));// Check if costume card was added successfully
      handOne.addCard(sprayCard);
      assertEquals(1, handOne.getNumberOf("Spray bottle card"));// Check if spray bottle card was added successfully
  }

  /**
  * This is a test for Hand.removeCard().
  * Game only ever needs to remove spray bottle, laser pointer and lost cat cards.
  */
  @Test
  void testRemove() {
      handTwo.addCard(sprayCard);
      handTwo.addCard(new Card("Laser pointer card"));
      assertEquals(1, handTwo.getNumberOf("Spray bottle card"));
      assertEquals(1, handTwo.getNumberOf("Laser pointer card"));
      handTwo.removeCard("Spray bottle card");
      assertEquals(0, handTwo.getNumberOf("Spray bottle card"));
      handTwo.removeCard("Laser pointer card");
      assertEquals(0, handTwo.getNumberOf("Laser pointer card"));
  }

  /**
   * This is a test for Hand.getNumberOf().
   */
  @Test
  void testGetNumberOf() {
      handOne.addCard(pabloCard);
      handOne.addCard(soxCard);
      handOne.addCard(costumeCard);
      
      assertEquals(1, handOne.getNumberOf("Costume card")); // Check if costume card count is correct
      assertEquals(0, handOne.getNumberOf("Lost cat card")); // Check for a card not in hand
  }
 
}
 
