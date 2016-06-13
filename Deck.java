/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author itworkstudy
 */
public class Deck {

    private static final Random RNG = new Random();
    private static final List<String> deck = new ArrayList<String>();
    private static final List<String> hand = new ArrayList<String>();

    static void initGame() {
        hand.clear();
        deck.clear();
        deck.add("Arid Mesa");
        deck.add("Arid Mesa");
        deck.add("Bloodstained Mire");
        deck.add("Bloodstained Mire");
        deck.add("Bloodstained Mire");
        deck.add("Bloodstained Mire");
        deck.add("Forest");
        deck.add("Plains");
        deck.add("Sacred Foundry");
        deck.add("Sacred Foundry");
        deck.add("Stomping Grounds");
        deck.add("Stomping Grounds");
        deck.add("Stomping Grounds");
        deck.add("Temple Garden");
        deck.add("Windswept Heath");
        deck.add("Windswept Heath");
        deck.add("Wooded Foothills");
        deck.add("Wooded Foothills");
        deck.add("Wooded Foothills");
        deck.add("Wooded Foothills");
        deck.add("Atarka's Command");
        deck.add("Atarka's Command");
        deck.add("Atarka's Command");
        deck.add("Atarka's Command");
        deck.add("Lightning Bolt");
        deck.add("Lightning Bolt");
        deck.add("Lightning Bolt");
        deck.add("Lightning Bolt");
        deck.add("Path to Exile");
        deck.add("Path to Exile");
        deck.add("Burning-Tree Emissary");
        deck.add("Burning-Tree Emissary");
        deck.add("Burning-Tree Emissary");
        deck.add("Burning-Tree Emissary");
        deck.add("Experiment One");
        deck.add("Experiment One");
        deck.add("Experiment One");
        deck.add("Experiment One");
        deck.add("Ghor-Clan Rampager");
        deck.add("Ghor-Clan Rampager");
        deck.add("Goblin Guide");
        deck.add("Goblin Guide");
        deck.add("Goblin Guide");
        deck.add("Goblin Guide");
        deck.add("Kird Ape");
        deck.add("Kird Ape");
        deck.add("Kird Ape");
        deck.add("Kird Ape");
        deck.add("Reckless Bushwhacker");
        deck.add("Reckless Bushwhacker");
        deck.add("Reckless Bushwhacker");
        deck.add("Reckless Bushwhacker");
        deck.add("Steppe Lynx");
        deck.add("Steppe Lynx");
        deck.add("Steppe Lynx");
        deck.add("Steppe Lynx");
        deck.add("Wild Nacatl");
        deck.add("Wild Nacatl");
        deck.add("Wild Nacatl");
        deck.add("Wild Nacatl");

        //System.out.println("Deck size: " + deck.size() + "\nHand size: " + hand.size());
    }

    static void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));

    }

    static void draw(int cards) {
        for (int i = 0; i < cards; i++) {
            hand.add(deck.get(i));
        }
        for (int j = 0; j < cards; j++) {
            deck.remove(0);
        }
        

    }

    static void resolveMulligans() {
//        System.out.println("Resolve Mulligans");
        switch (hand.size()) {
            case 7: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(2, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(6);
                    resolveMulligans();
                    break;
                }
            }
            case 6: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(5);
                    resolveMulligans();
                    break;
                }
            }
            case 5: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(4);
                    resolveMulligans();
                    break;
                }
            }
            case 4: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(3);
                    resolveMulligans();
                    break;
                }
            }
            case 3: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 3)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(2);
                    resolveMulligans();
                    break;
                }
            }
            default: {
//                System.out.println("Defaulted");
                break;
            }

        }

    }

    

    static int scry() {
        if (hand.size() < 7) {
            //System.out.println("Scry");
            if (hContainsLand(1, 1)) {
                if (!isLand(deck,0)){
//                    System.out.println("Before Scry: " + deck);
                    scryBottom();
//                    System.out.println("After Scry: " + deck);
                }
            }
            return 7-hand.size();
        } else {
            return 0;
        }

    }
    
    static void scryBottom(){
        deck.add(deck.get(0));
        deck.remove(0);
    }

    static boolean hContainsLand(int lower, int upper) {
        int landCount = 0;

        for (int i = 0; i < hand.size(); i++) {
            if (isLand(hand, i)) {
                landCount++;
            }
        }
//        System.out.println("Hand contains: " + hand + "\nLand Count: " + landCount);
        return (landCount >= lower && landCount <= upper);
    }
    
    static boolean hContainsFetch(int lower, int upper) {
        int fetchCount = 0;

        for (int i = 0; i < hand.size(); i++) {
            if (isFetch(hand, i)) {
                fetchCount++;
            }
        }
//        System.out.println("Fetch Count: " + fetchCount + "\n");
        return (fetchCount >= lower && fetchCount <= upper);
    }

    static boolean isLand(List<String> list, int i) {
        return ("Forest".equals(list.get(i))) || (isFetch(list,i))
                || ("Plains".equals(list.get(i))) || ("Sacred Foundry".equals(list.get(i)))
                || ("Stomping Grounds".equals(list.get(i))) || ("Temple Garden".equals(list.get(i)));
    }
    static boolean isFetch(List<String> list, int i) {
        return ("Arid Mesa".equals(list.get(i)))
                || ("Bloodstained Mire".equals(list.get(i))) 
                || ("Wooded Foothills".equals(list.get(i))) 
                || ("Windswept Heath".equals(list.get(i)));
    }

    static int numCards(int swtch) {
        if (swtch == 0) {
            return deck.size();
        } else {
            return hand.size();
        }
    }

    static boolean handCheck(Collection<?> c) {
        return hand.containsAll(c);

    }

    static void play() {

    }

}
