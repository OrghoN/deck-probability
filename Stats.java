/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.ArrayList;

/**
 *
 * @author itworkstudy
 */
public class Stats {

    private static final double SIMULATIONS = 100000000;
    private static double perfectGame = 0;
    private static double perfectHand = 0;
    private static final ArrayList<String> INOPENING = new ArrayList<>();
    private static final ArrayList<String> FIRSTDRAW = new ArrayList<>();
    private static final int[] MULLS = new int[6];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        INOPENING.add("Steppe Lynx");
        FIRSTDRAW.add("Atarka's Command");

        for (int i = 0; i < SIMULATIONS; i++) {
            //System.out.println("\n\nNew Game");
            Deck.initGame();
            Deck.shuffle();
            Deck.draw(7);

            Deck.resolveMulligans();

            MULLS[Deck.scry()]++;
            if (Deck.handCheck(INOPENING) && Deck.hContainsLand(2, 6) && Deck.hContainsFetch(1,6)) {
                perfectHand++;
//                System.out.println("Perfect Hand");
                Deck.play();
                Deck.draw(1);

                if (Deck.handCheck(FIRSTDRAW) && Deck.hContainsFetch(2, 6)) {
                    //System.out.println("Perfect Game");
                    perfectGame++;
                }
            }

        }//for
        int total = 0;
//        for (int i = 0; i <mulls.length; i++){
//            total+=mulls[1];
//        }
        System.out.println(perfectHand + " perfect hands");
        System.out.println(perfectGame + " perfect games");
//        System.out.println("total number of arrays stuff: " + total); 
        System.out.printf("%.3f percent of hands mulled to 6\t",((MULLS[1] - MULLS[2]) / SIMULATIONS * 100) );
        System.out.printf("%.3f percent of hands mulled to 5\t",((MULLS[2] - MULLS[3]) / SIMULATIONS * 100) );
        System.out.printf("%.3f percent of hands mulled to 4\n",((MULLS[3] - MULLS[4]) / SIMULATIONS * 100) );
        System.out.printf("%.3f percent of hands mulled to 3\t",((MULLS[4] - MULLS[5]) / SIMULATIONS * 100) );
        System.out.printf("%.3f percent of hands mulled to 2 or less\n",(MULLS[5] / SIMULATIONS * 100) );
        System.out.printf("%.3f percent of hands were perfect\n", (perfectHand / SIMULATIONS * 100));
        System.out.printf("%.3f percent of games were perfect\n", (perfectGame/SIMULATIONS*100));
    }

}
