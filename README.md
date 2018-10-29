# PvZ - SYSC3110 Plants vs Zombies Software Development Project

### Authors:
- Simon Krol -simonkrol
- Boyan Siromahov - BoyanSiromahov
- Shaun Gordon - swim224
- Gordon MacDonald - Gordon-MacDonald

### Changes made in Deliverable 1:
- Added base model(Plants, Zombies, Level Grid)
- Created text based User Interface that plays level
- Added basic level modeling (currently a .txt file)
- Added lawnmower functionality


### Known Issues:
- If a group of zombies kills a plant, sometimes the group will split up. (Not necessarily an issue)
- No delay exists between being allowed to place plants
- Lacking error checking on user input

###How to play:
1. After launching our Main class, create a new game by inputting an n. In Deliverable 1, this will launch the level.
2. Within each turn, you have the options of placing a plant, ending your turn or quitting.
3. When placing a plant, you will be prompted for the lane number (1-6), the spot number (1-6) and the plant type, where p is a Peashooter(The main attacking plant) and s is Sunflower(The income generation plant)
4. Ending your turn will cause the board to update with any potential new zombies, as well as cause all Entities currently on the board to update (Peashooters will shoot, Sunflowers generate sun, Zombies either walk forward or attack a plant)
5. If zombies reach the end of the lane, a 1-use per lane per level lawnmower will be used to mow over every zombie in that lane, the second time a zombie reaches the end of a lane, the player loses.
6. If all waves of zombies are killed by the player, the player wins.