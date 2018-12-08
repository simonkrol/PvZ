# PvZ - SYSC3110 Plants vs Zombies Software Development Project - [Github](https://github.com/simonkrol/PvZ)

### Authors:
- [Simon Krol](https://github.com/simonkrol)
- [Boyan Siromahov](https://github.com/BoyanSiromahov)
- [Shaun Gordon](https://github.com/sGordon224)
- [Gordon MacDonald](https://github.com/Gordon-MacDonald)

### Changes made in Deliverable 1:
- Added base model(Plants, Zombies, Level Grid)
- Created text based User Interface that plays level
- Added basic level modeling (currently a .txt file)
- Added lawnmower functionality

### Changes made in Deliverable 2:
- Created Graphic User Interface to play level
- Reconfigured certain aspects of model to improve configurability or reduce "smell"
- Java classes were refactored to be in organized locations
- JUnit testing was implemented across the project's model

### Changes made in Deliverable 3:
- Drastically Improved the Graphic User Interface
- Implemented JSON to store level data
- JUnit testing was implemented across the project's view and controller
- 3 Additional plants and 2 additional zombies were added to the game
- The ability to have more than 1 level was added
- The view was improved to form to different screen sizes and resolutions
- Entity movement was changed to be based on # spots rather than # pixels

### Changes made in Deliverable 4:
- Added save/load functionality using GSON (loaded via Maven)
- Added game level builder
- Added Main Menu
- JUnit testing for complete model, view, and controller
- Cleaned up messy code



### Known Issues:
- If a group of zombies kills a plant, sometimes the group will split up. (Not necessarily an issue)
- No delay exists between being allowed to place plants
- **(Fixed)Lacking error checking on user input**
- Plants are created regardless of whether they are successfully able to be placed on the board
- **(Fixed)Zombies always attack the front plant, regardless of whether that plant is in front of them**
- **(Fixed)Plants always attack the front zombie, regardless of whether that zombie is in front of them**
- **(Fixed)The method to place plants can be confusing**
- **(Fixed)Plants don't have their costs printed on screen**
- **(Fixed)When the size of the gui is changed, the component's sizes don't change and can overlap in strange ways**
- No menu exists to give instructions, only a popup at game start
- **(Fixed)Print statements still exist throughout the project and should be removed**
- Win/Lose Dialog Box Doesn't end game 


### How to play:
1. Launch the main class, this should bring up the gui
2. Within each turn, you have the options of placing plants, ending your turn or quitting.
3. When placing a plant, click on the spot you'd like to place your plant, then click on the plant you'd like to place.
4. Ending your turn will cause the board to update with any potential new zombies, as well as cause all Entities currently on the board to update (Peashooters will shoot, Sunflowers generate sun, Zombies either walk forward or attack a plant)
5. If zombies reach the end of the lane, a 1-use per lane per level lawnmower will be used to mow over every zombie in that lane, the second time a zombie reaches the end of a lane, the player loses.
6. If all waves of zombies are killed by the player, the player wins.
7. You can undo and redo plant placements within a turn using ctrl-z and ctrl-y or by clicking the respective buttons in the menu.

### Roadmap Ahead
- Implement continuous integration on github
- Fix the slight smell in Lane.java

Readme Author: Simon Krol and Shaun Gordon

JavaDoc Documentation Author: Simon Krol and Shaun Gordon

UML Diagram and Sequence Diagram Author: Shaun Gordon and Simon Krol