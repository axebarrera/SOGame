# StageFightController Methods

## Overview
The `StageFightController` class handles the game mechanics, UI interactions, and updates for the stage fight in the game.

## Methods

### 1. setApplication(HelloApplication application)
- Sets the application instance for the controller.

### 2. initialize()
- Initializes the main UI and the attack UI.

### 3. mainUIInit()
- Initializes the main UI elements, such as setting up mouse click and hover events for characters.

### 4. attackUIInit()
- Initializes the attack UI elements, such as setting up mouse click events for attack options.

### 5. startGame()
- Starts the game by setting up the turn order, fighters, models, UI, and updating the stats.

### 6. namePlateAppearHandler(Pane p) and namePlateRemoveHandler(Pane p)
- Handles the appearance and disappearance of the nameplate for characters.

### 7. UI Transition Methods
- **enterAttackMenu()**, **exitAttackMenu()**, **enterBasicMenu()**, **exitGeneralMenu()**, **enterAbilitiesMenu()**, and **enterUltimatesMenu()**
    - These methods handle the UI transitions between different menus.

### 8. chooseMove(int id)
- Chooses a move based on its ID.

### 9. cancelMove()
- Cancels the currently selected move.

### 10. setDescriptionText(String content)
- Sets the description text for a move.

### 11. executeTurn(Fighter target)
- Executes a turn for a fighter against a target.

### 12. calculateDamageOfMove(Fighter target, Fighter caster, Moves move)
- Calculates the damage dealt by a move.

### 13. addTargetsToList(Fighter target,Fighter caster,Moves move)
- Adds targets to a list based on the move's targeting type.

### 14. generateListOfMoves(VBox parent,ArrayList<Moves> moveList,int start,int end)
- Generates a list of moves for the UI.

### 15. modifyGeneralUIImage(ImageView iv)
- Modifies the appearance of an image in the UI.

### 16. formatTextForMoveMenu(String content)
- Formats the text for the move menu.

### 17. setModelsAndUI()
- Sets the models and UI elements for the characters.

### 18. updateUIStats()
- Updates the UI stats for characters.

### 19. nextTurn()
- Transitions to the next turn.

### 20. setTeammates()
- Sets the teammates for each fighter.

### 21. clearMouseEvents()
- Clears mouse events to prevent memory leaks.

### 22. getPercentageResult(int chanceOfHit)
- Determines if an action succeeds based on a percentage chance.

### 23. setTurnName()
- Sets the name of the fighter whose turn it is.

## Conclusion
These methods collectively manage the game's stage fight mechanics, ensuring smooth gameplay and user interactions.
