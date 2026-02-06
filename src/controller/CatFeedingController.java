/*
 * This file contains the CatFeedingController class.
 */

package controller;

import java.util.ArrayList;
import model.Cat;
import model.FoodCard;
import model.Hand;

/**
 * Oversees the process of a player feeding their cats.
 * 
 * @author Serena Davis
 * @version 4-3-2025
 */
public class CatFeedingController {
    /**
     * The index of "treat" in the array of food names.
     */
    private static final int TREAT_INDEX = 4;
    /**
     * The index of "wild" in the array of food names.
     */
    private static final int WILD_INDEX = 3;
    /**
     * An array of the names of all types of food.
     */
    private String[] foodNames;

    /**
     * Class constructor instantiating the list of food names.
     */
    public CatFeedingController(){
        foodNames = new String[]{"chicken","tuna","milk","wild","treat"};
    }

    /**
     * Feeds the cats in a player's hand using user input to specifiy which cats to feed.
     * 
     * @param hand The hand containing the cat cards to be fed.
     * @param inputController The InputController.
     * @param displayController The DisplayController.
     * @return void
     */
    public void feedCats(Hand hand, InputController inputController, DisplayController displayController){
        if(hand.getType().equals("cat player")){
            feedCatPlayerCats(hand);
        }
        else{
            //get the cats and food
            ArrayList<Cat> cats = hand.getCatCards();
            ArrayList<FoodCard> food = hand.getFoodCards();
            ArrayList<String> catNames = new ArrayList<>();
            for(Cat cat: cats){
                catNames.add(cat.getName().toLowerCase());
            }
            catNames.add("done");
            int[] foodAmounts = {0,0,0,0,0};
            for(FoodCard foodItem: food){
                for(int i = 0; i < foodNames.length; i++){
                    if(foodItem.getFoodType().equals(foodNames[i])){
                        foodAmounts[i]++;
                    }
                }
            }

            //display the cats and food for the user
            displayController.displayCatFeeding(cats,foodNames,foodAmounts);
            //get the next cat to feed
            String nextCatName = inputController.getInput(catNames,"Which cat would you like to feed/unfeed next? \nIf you are finished feeding your cats, please type 'done'.");
            while(!nextCatName.equals("done")){
                //get the cat
                Cat nextCat = null;
                for(Cat cat : cats){
                    if(cat.getName().toLowerCase().equals(nextCatName)){
                        nextCat = cat;
                    }
                }
                
                //feed the cat
                feedNextCat(nextCat,foodAmounts,inputController,displayController);

                //display the cats and food for the user
                displayController.displayCatFeeding(cats,foodNames,foodAmounts);
                //get the next cat to feed
                nextCatName = inputController.getInput(catNames,"Which cat would you like to feed/unfeed next? \nIf you are finished feeding your cats, please type 'done'.");
            }

            //set the hand's lists of cats and food to the updated versions
            food = new ArrayList<>();
            for(int i = 0; i < foodNames.length; i++){
                for(int j = 0; j < foodAmounts[i]; j++){
                    food.add(new FoodCard("Food card",foodNames[i]));
                }
            }
            hand.setCatList(cats);
            hand.setFoodList(food);
        }
    }

    /**
     * Feeds (or unfeeds) the given cat with food from the list of food, for use in the feedCats() method.
     * 
     * @param nextCat The cat to feed.
     * @param foodAmounts The food available.
     * @param inputController The InputController for getting user input.
     * @param displayController The DisplayController to display information to the user.
     * @return void
     */
    private void feedNextCat(Cat nextCat, int[] foodAmounts, InputController inputController, DisplayController displayController){
        //unfeed a cat
        if(nextCat.isFed()){
            unfeedCat(nextCat,foodAmounts);
            displayController.displayMessage(nextCat.getName()+" has been unfed!");
        }

        //feed a cat
        else {
            boolean feedWithTreat = false;
            if(foodAmounts[TREAT_INDEX] > 0){
                feedWithTreat = inputController.getInput(new String[]{"yes","no"},"Would you like to feed this cat a treat? Please enter 'yes' or 'no'.").equals("yes");
            }
            if(feedWithTreat){
                nextCat.feedWithTreat();
                foodAmounts[TREAT_INDEX]--;
                displayController.displayMessage("\n"+nextCat.getName()+" has been fed!");
            }
            //feed the cat normally
            else {
                int[] requiredFood = {0,0,0,0,0};
                for(String foodItem: nextCat.getFood()){
                    for(int i = 0; i < foodNames.length; i++){
                        if(foodItem.equals(foodNames[i])){
                            requiredFood[i]++;
                        }
                    }
                }
                int amountMissing = 0;
                for(int i = 0; i < foodNames.length; i++){
                    if(requiredFood[i] > foodAmounts[i]) amountMissing += (requiredFood[i] - foodAmounts[i]);
                }
                boolean canFeed = amountMissing == 0;
                if(!canFeed){
                    canFeed = feedWithWild(foodAmounts,requiredFood,amountMissing,inputController);
                }
                if(canFeed){
                    for(int i = 0; i < foodAmounts.length; i++){
                        foodAmounts[i] -= requiredFood[i];
                    }
                    nextCat.changeFedStatus();
                    displayController.displayMessage("\n"+nextCat.getName()+" has been fed!");
                }
                else{
                    displayController.displayMessage("You do not have enough food to feed "+nextCat.getName());
                }
            }
        }
    }

    /**
     * Unfeeds the given cat and adds the food back to the list, for use in the feedCats() method.
     * 
     * @param nextCat The cat to unfeed.
     * @param foodAmounts The amount of food available.
     * @return void
     */
    private void unfeedCat(Cat nextCat, int[] foodAmounts){
        if(nextCat.isFedWithTreat()){
            foodAmounts[TREAT_INDEX]++;
        }
        else{
            for(String foodName : nextCat.getFood()){
                for(int i = 0; i < foodNames.length; i++){
                    if(foodNames[i].equals(foodName)){
                        foodAmounts[i]++;
                    }
                }
            }
        }
        nextCat.changeFedStatus();
    }

    /**
     * Returns whether or not the cat is able to be fed using wild food, for use in the
     * feedCats() method.
     * 
     * @param foodAmounts The amount of food available.
     * @param requiredFood The amount of food required.
     * @param amountMissing The amount of food missing.
     * @param inputController The input controller for getting user input.
     * @return True if the cat can be fed with wild food, false otherwise.
     */
    private boolean feedWithWild(int[] foodAmounts, int[] requiredFood, int amountMissing, InputController inputController){
        boolean canFeed = false;
        if(foodAmounts[WILD_INDEX] >= amountMissing){
            canFeed = inputController.getInput(new String[]{"yes","no"},"You do not have enough food to feed that cat. Would you like to use wild food instead?").equals("yes");
        }
        if(canFeed){
            requiredFood[WILD_INDEX] = amountMissing;
            for(int i = 0; i < foodAmounts.length; i++){
                if(requiredFood[i] > foodAmounts[i]){
                    requiredFood[i] = foodAmounts[i];
                }
            }
        }
        return canFeed;
    }

    /**
     * Feeds all the cats in a cat player's hand: all the stray cats first, then the rest in ascending order
     * of required food. 
     * 
     * @param hand The cat player's hand.
     * @return void
     */
    private void feedCatPlayerCats(Hand hand){
        ArrayList<Cat> cats = hand.getCatCards();
        ArrayList<FoodCard> foodList = hand.getFoodCards();

        //separate normal food and treats
        int food = foodList.size();
        int treats = 0;
        for(FoodCard foodItem: foodList){
            if(foodItem.getFoodType().equals("treat")){
                treats++;
            }
        }
        food -= treats;

        //sort cats in order of increasing food requirements
        for(int i = 1; i < cats.size(); i++){
            while(cats.get(i).getFood().length < cats.get(i-1).getFood().length){
                Cat temp = cats.get(i-1);
                cats.set(i-1,cats.get(i));
                cats.set(i,temp);
            }
        }

        //feed stray cats first
        for(Cat cat: cats){
            if(cat.getType().equals("Stray cat")){
                if(food >= cat.getFood().length){
                    cat.changeFedStatus();
                }
                else if(treats != 0){
                    cat.feedWithTreat();
                }
            }
        }

        //feed normal cats
        for(Cat cat: cats){
            if(!cat.isFed()){
                if(food >= cat.getFood().length){
                    cat.changeFedStatus();
                }
                else if(treats != 0){
                    cat.feedWithTreat();
                }
            }
        }

        //put food and cats back in hand
        foodList = new ArrayList<>();
        for(int i = 0; i < (food+treats); i++){
            foodList.add(new FoodCard("Food card","wild"));
        }
        hand.setFoodList(foodList);
        hand.setCatList(cats);
    }

}
