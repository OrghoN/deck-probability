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

