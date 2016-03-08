# deck-probability
This repository is to store a project for calculating and simulating card probabilities for the popular card game Magic the Gathering

Initial concept comes from a deck in the Modern format of competitive magic and trying to optimize the probability of drawing at least 3 <a href="mtgsalvation.gamepedia.com/Land"> lands </a> by turn 2 while accounting for specific creatures and other cards in timely manner. Full list of parameters to follow. Hopefully, when completed, this project can be used to optimize the probability of any number of cards in any 60 card format.

Desired outcome:

1) At least one <a href="http://gatherer.wizards.com/Pages/Card/Discussion.aspx?multiverseid=171012">Steppe Lynx</a> on turn one. This must be in initial hand if user is on the play. If the user is on the draw, it can be in the initial hand or on top of the deck

2) At least one <a href="http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=394502">Attarka's Command</a> by turn two. 

3) At least one source of <a href="mtgsalvation.gamepedia.com/White">white mana</a> on turn one.

4) At least two <a href="mtgsalvation.gamepedia.com/Fetch_lands">fetch lands</a> by turn two that do NOT include the white source on turn one.

Constraints: 

1) No more than 4 <a href="http://gatherer.wizards.com/Pages/Card/Discussion.aspx?multiverseid=171012">Steppe Lynx</a>, 4 <a href="http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=394502">Attarka's Command</a>s, or 4 of any individual <a href="mtgsalvation.gamepedia.com/Fetch_lands">fetch land</a> exist within the deck.

2) The deck consists of at least 60 cards (intuitively, exactly 60 will return the highest probability)

3) The cards are in a random order at the outset

Game play:

1) If the player wins the die roll, they choose whether to play first or draw a card on their first turn. It is assumed and well established that playing first gives a larger strategic advantage than drawing.

2) User/player starts by drawing 7 cards off the top. 

3) The player decides whether or not to keep the hand he has drawn or take a <a href="http://mtgsalvation.gamepedia.com/Mulligan#Vancouver_mulligan">mulligan</a>

4a) If the player plays first, they play immediately with the card in their hand. 

4b) If the player draws, they draw a card before playing.

5) Both players then draw a card at the beginning of each of their respective turns and when a card tells them to draw a card. 

6) The game ends when one person's life total drops to zero, or they attempt to draw a card from their deck and can not. 


NOTE for this, we will be using proability calculations for finding multiple permutations, resources for the math behind this can be found in <a href="https://www.khanacademy.org/math/probability/probability-and-combinatorics-topic/probability_combinatorics/e/probability_with_perm_comb">multiple</a> <a href="http://study.com/academy/lesson/how-to-calculate-the-probability-of-permuations.html">different</a> <a href="https://www.khanacademy.org/math/probability/probability-and-combinatorics-topic/permutations/v/permutation-formula">places</a>

