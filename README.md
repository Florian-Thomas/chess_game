# Chess game with Monte-Carlo Tree Search Algorithm and Minimax

This chess game can be played against an Artificial Intelligence algorithm based on Monte-Carlo Tree Search and Minimax algorithms.
It is also possible to play against another human player or to watch 2 AIs play against each other.
The maximum allowed time for the AI turn needs to be specified at the beginning of the game.
## Run

To launch the game, run:

```
cd src
javac game/Launcher.java
java game.Launcher
```

## Play

You can then choose the game mode between Player vs Player, Player vs AI and AI vs AI. If an AI is used, the time limit given to the AI must be specified in seconds.

You will also be asked if you want to play first or not. The white player always plays first in chess.

To move a piece, select it with your left click. You will see the different possible moves with red dots and can then left click on the chosen one. If you have selected a piece and changed your mind, you can unselect it using the right click.

