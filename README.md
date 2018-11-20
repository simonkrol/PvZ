# PvZ - SYSC3110 Plants vs Zombies Software Development Project - [Github](https://github.com/simonkrol/PvZ)

### Authors:
- [Simon Krol](https://github.com/simonkrol)
- [Boyan Siromahov](https://github.com/BoyanSiromahov)
- [Shaun Gordon](https://github.com/swim224)
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


### Known Issues:
- If a group of zombies kills a plant, sometimes the group will split up. (Not necessarily an issue)
- No delay exists between being allowed to place plants
- **Lacking error checking on user input(Fixed)**
- Plants are created regardless of whether they are successfully able to be placed on the board
- Zombies always attack the front plant, regardless of whether that plant is in front of them.
- Plants always attack the front zombie, regardless of whether that zombie is in front of them.
- **The method to place plants can be confusing(Fixed)**
- Plants don't have their costs printed on screen
- When the size of the gui is changed, the component's sizes don't change and can overlap in strange ways
- No menu exists to give instructions, only a popup at game start
- Print statements still exist throughout the project and should be removed


### How to play:
1. Launch the main class, this should bring up the gui
2. Within each turn, you have the options of placing plants, ending your turn or quitting.
3. When placing a plant, you must click on the "add plant" button, click on the spot you'd like to place your plant, then click on the plant you'd like to place.
4. Ending your turn will cause the board to update with any potential new zombies, as well as cause all Entities currently on the board to update (Peashooters will shoot, Sunflowers generate sun, Zombies either walk forward or attack a plant)
5. If zombies reach the end of the lane, a 1-use per lane per level lawnmower will be used to mow over every zombie in that lane, the second time a zombie reaches the end of a lane, the player loses.
6. If all waves of zombies are killed by the player, the player wins.

### Roadmap Ahead
- Implement continuous integration on github
- Include additional plants and zombies
- Add more levels
- Add lawnmower image
- Improve robustness of code
- Allow for unlimited undo/redo

Readme Author: Simon Krol

JavaDoc Documentation Author: Simon Krol

UML Diagram and Sequence Diagram Author: Shaun Gordon and Simon Krol