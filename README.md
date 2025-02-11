# Group Members
1. Ryan Ho
2. Scarlett Ho
3. Nicholas Chau
4. Karthik Raveeswaran
   
# How the concept of "Levels" was used
* The concept of levels was used from the main character's progression, defeating each enemy to reach his goal.
* Enemies also get progressively harder, and each enemy has a unique trait.
* The game has an indefinite ending. 

# Data structures used and their purpose 

[HashMap]

  1. The `HashMap` data structure was used to store and track the highest level cleared by the player, this code can be found in `HighScoreManager`, and it builds up every time a player clears a level, even re-runs.

[Queues]

  2. Used in  `InstructionScreen`, to switch between screens when the player hits next or back, enabling seamless navigation through multiple instruction pages or sections. This allows the player to view different sets of instructions.

[Arrays and ArrayLists]
  
  3. Arrays store the animation frames for different player states (idle, running, jumping, attacking). These frames are loaded into GreenfootImage[] arrays and accessed by the `animate()` method to display the correct animation based on the player's current state.

# Additional information (features)
Ending screen
   
   1. Click the view ending button if the bosses are too hard to beat.

Gameplay
   
   2. A wide variety of music and sound effects.
   3.  2nd phase for the final enemy for a fun extra NEW challenge


UI (User Interface)
   
   4. Health bars are included
   5. Level indicator
   6. Checkpoints for a more convenient experience

Music Manager

   7. The MusicManager  is a separate class that manages all in-game music.
   8. It uses methods like .playLoop or .setVolume

Buttons
   
   9. This object is used mostly before and after the game's content (before the game starts or when the game is complete).
   10. The button plays a sound effect when clicked.
   11. The button increases in size when hovered over.
   12. public Button (Runnable action, String imageName, String hoverImgName)

Scrolling background
   
   13. For the background image of ScreenSelect, a scrolling background is used.

