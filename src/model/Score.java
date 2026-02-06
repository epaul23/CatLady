/*
 * This file contains the Score class. 
 */

package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class calculates the score.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 4-1-2025
 */
public class Score {
    /**
     * Method to calculate the scores of each hand.
     * 
     * @param hands An array of the players' hands.
     * @return An array containing the scores.
     */
    public int[] calculateScore(Hand[] hands){
        int[] scores = new int[hands.length];


        for (int i=0; i<hands.length;i++){
            int score =0;
            if (hands[i].getType().equals("cat player")){
                score += hands[i].getNumberOf("Spray bottle card");
                score += hands[i].getNumberOf("Laser pointer card");
            }
            score += calculateCatScore(hands[i]);
            score += calculateCatnipPoints(hands[i]);
            score += calculateToyScore(hands[i]);
            score += hands[i].getTokenCount()*2;
            scores[i] = score;
        }

        int[] costumePoints = calculateCostumes(hands);
        for (int i=0; i<costumePoints.length;i++){
            scores[i] += costumePoints[i];
        }

        int[] foodPoints = calculateFoodScore(hands);
        for(int i=0; i<foodPoints.length;i++){
            scores[i] += foodPoints[i];
        }

        return scores;
    }


    /**
     * Method to calculate the points to add to the hand based on cat cards.
     * 
     * @param hand The player's hand.
     * @return The number of points to add.
     */
    private int calculateCatScore(Hand hand){
         // Get the cat cards for each hand.
         int catScore = 0;
         List<Cat> cats = hand.getCatCards();
 
         // Add points for each fed cat in the hand.
         for(Cat cat : cats){
             if(cat.isFed()){
                 if (cat.isFedWithTreat()){
                     catScore += 1;
                 }
                 else if (cat.getType().equals("Stray cat")){
                     catScore += calculateStrayCatPoints(cat.getName(), hand);
                 } else{
                     catScore += ((CatCard) cat).getPoints();
                 }
             } else if (!cat.isFed()){
                 catScore -=2;
             }
         }
         return catScore;
    }

    /**
     * Method to calculate the points that need to be added based on catnip cards.
     * 
     * @param hand The player's hand.
     * @return The number of points to be added.
     */
    private int calculateCatnipPoints(Hand hand){
        int catnipScore = 0;
        if(hand.getNumberOf("Catnip card") == 1){
            catnipScore -= 2;
        }
        if(hand.getNumberOf("Catnip card") == 2 || hand.getNumberOf("Catnip card") == 3){
            for(Cat cat : hand.getCatCards()){
                if(cat.isFed()){
                    catnipScore += 1;
                }
            }
        }
        if(hand.getNumberOf("Catnip card") >= 4){
            for(Cat cat : hand.getCatCards()){
                if(cat.isFed()){
                    catnipScore += 2;
                }
            }
        }
        return catnipScore;
    }

    /**
     * Method that returns a list of costume scors corresponding to the list of hands.
     * 
     * @param hands An array of the players' hands.
     * @return An array that gives the number of points to add/subtract to each hand.
     */
    private int[] calculateCostumes(Hand[] hands){
        // Get number of costumes
        int maxCostumes = 0;
        int maxCount =0;
        int[] positions = new int[hands.length];
        int[] costumeScores = new int[hands.length];
        // Count the costumes in the hands.
        for (int i=0;i<hands.length;i++){
            costumeScores[i] = hands[i].getNumberOf("Costume card");
        }
        for(int i=0;i<costumeScores.length;i++){
            // If the hand has no costume cards, subtract 2
            if(costumeScores[i]==0){
                positions[i] = -2;
            }
            //if the costume count is greater than the max, make a new max
            else if(maxCostumes < costumeScores[i]){
                maxCostumes = costumeScores[i];
                maxCount = 1;
            } else if(maxCostumes == costumeScores[i]){
                // Max count increases if there are multiple hands with the same costume count
                // Keeps track of whether two hands are tied or not
                maxCount++;
            }
        }
        for(int i=0;i<costumeScores.length;i++){
            // if there is one hand with the most, that hand gets 6 points
            if(maxCostumes == costumeScores[i] && maxCount == 1){
                positions[i] = 6;
            } else if (maxCostumes == costumeScores[i]&& maxCount>1){
                // if there are multiple hands that are tied, they each get three
                positions[i] = 3;
            }
        }
        return positions;
    }

    /**
     * Method that returns the position in the array of hands to subtract VP from for having
     * the most food.
     * 
     * @param hands An array of the players' hands.
     * @return The position of the hand to subtract VP from.
     */
    private int[] calculateFoodScore(Hand[] hands){
        int maxFood = 0;
        int[] position = new int[hands.length];
        int[] foodScores = new int[hands.length];
        // Count the food in the hands.
        for (int i=0;i<hands.length;i++){
            foodScores[i] = hands[i].getFoodCards().size();
        }
        for(int i=1;i<foodScores.length;i++){
            if (foodScores[i-1]<foodScores[i]){
                maxFood = foodScores[i];
            }
        }
        for(int i=0;i<foodScores.length;i++){
            if(maxFood == foodScores[i]){
                position[i] = -2;
            }
            else position[i] = 0;
        }
        return position;
    }


    /**
     * Method to calculate the points to add to a hand based on their toy cards.
     * 
     * @param hand The player's hand.
     * @return The points to add to the hand.
     */
    private int calculateToyScore(Hand hand){
        int toyScore = 0;
        Set<ToyCard> toycards1 = new HashSet<>();
        Set<ToyCard> toycardsExtra1 = new HashSet<>();
        Set<ToyCard> toycardsExtraExtra1 = new HashSet<>();
        // Get the number of unique toy cards in each hand
        for (ToyCard card : hand.getToyCards()){
            if (!toycards1.contains(card)){
                toycards1.add(card);
            } else if (!toycardsExtra1.contains(card)){
                toycardsExtra1.add(card);
            } else{
                toycardsExtraExtra1.add(card);
            }
        }
        // Get the number of unique toy cards
        int toycardCount1 = toycards1.size();
        int toycardCountExtra1 = toycardsExtra1.size();
        int toycardCountExtraExtra1 = toycardsExtraExtra1.size();
        // Check how much VP to add to each score
        if(toycardCount1 == 1 || toycardCountExtra1 == 1 || toycardCountExtraExtra1 == 1){
            toyScore += 1;
        }
        if(toycardCount1 == 2 || toycardCountExtra1 == 2 || toycardCountExtraExtra1 == 2){
            toyScore += 3;
        }
        if(toycardCount1 == 3 || toycardCountExtra1 == 3 || toycardCountExtraExtra1 == 3){
            toyScore += 5;
        }
        if(toycardCount1 == 4 || toycardCountExtra1 == 4 || toycardCountExtraExtra1 == 4){
            toyScore += 8;
        }
        if(toycardCount1 == 5 || toycardCountExtra1 == 5 || toycardCountExtraExtra1 == 5){
            toyScore += 12;
        }
        return toyScore;
    }

    /**
     * Method to calculate the points from the stray cat cards.
     * 
     * @param name The name of the stray cat.
     * @param hand The hand containing the stray cat card.
     * @return The stray cat points.
     */
    public int calculateStrayCatPoints(String name, Hand hand){
        int strayScore = 0;
        // loop through check if type is stray cat.
        // check if the name of the cat card is the one we are looking for
        for (Cat cat : hand.getCatCards()){
            if(cat.getType().equals("Stray cat")){
                if(cat.getName().equals("Penny")){
                    // Penny is worth one point for each toy you have
                    strayScore += hand.getToyCards().size();
                }
                if (cat.getName().equals("Florence")) {
                    // Florence is worth one point for each orange cat you feed
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.getColour().contains("O") && catCard.isFed()){
                            strayScore++;
                        } 
                        }
                }
                if (cat.getName().equals("Hemingway")) {
                    // Hemingway is worth 7 points if you feed 4 or more cats and 3 otherwise
                    int numCatsFed = 0;
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.isFed()){
                            numCatsFed++;
                        }
                        
                    }
                    if(numCatsFed >= 4){
                        strayScore += 7;
                    } else{
                        strayScore += 3;
                    }
                }
                if (cat.getName().equals("Waffle")) {
                    // Waffle is worth 3 points if fed
                    if (cat.isFed()){
                        strayScore += 3;
                    }
                } 
                if (cat.getName().equals("Eliot")) {
                    // Eliot is worth 2 points for each black cat you feed
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.getColour().contains("B") && catCard.isFed()){
                            strayScore+=2;
                        }
                        
                    }
                } 
                if (cat.getName().equals("Cow")) {
                    // Cow is worth 2 points for being fed
                    if (cat.isFed()){
                        strayScore += 2;
                    }
                }
                if (cat.getName().equals("Zoroaster")) {
                    // Zoroaster is worth 2 points for each costume a hand has.
                    strayScore += hand.getNumberOf("Costume card");
                } 
                if (cat.getName().equals("Sweetheart")&& cat.isFed()) {
                    // Sweetheart is worth 5 points.
                    strayScore += 5;
                } 
                if (cat.getName().equals("LeVar Purrton")) {
                    //LeVar is worth 4 points for cat you feed.
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.isFed()){
                        strayScore+=4;
                        }
                        
                    }
                }
                if (cat.getName().equals("Truffle")) {
                    // Truffle is worth 2 points for each cat you feed.
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.isFed()){
                            strayScore+=2;
                        }
                    }
                }
                if (cat.getName().equals("Antoinette")) {
                    //Antoinette is worth 2 points for each white cat you feed.
                    for (Cat aCat : hand.getCatCards()){
                        Cat catCard = (Cat) aCat;
                        if(catCard.getColour().contains("W") && catCard.isFed()){
                            strayScore+=2;
                        }
                    }
                }
                
                if (cat.getName().equals("Moonbeam")) {
                    // Moonbeam is worth 3 points
                    strayScore += 3;
                }
                if (cat.getName().equals("Macak")) {
                    // Macak is worth 2 points
                    strayScore+=2;
                }

            }
        }
        return strayScore;
    }
} 


