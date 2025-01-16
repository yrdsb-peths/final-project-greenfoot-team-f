# Group Members
1. Ryan Ho
2. Scarlett Ho
3. Nicholas Chau
4. Karthik Raveeswaran

# Game Overview
Our game is an action-inspired platformer where the player navigates levels, interacts with the environment, defeats enemies, and progresses through challenges. It includes various game mechanics like jumping, attacking, health management, and high score tracking.

# How the concept of "Levels" was used
* The concept of levels was used from the main character's progression, defeating each enemy to reach his goal.
* Enemies also get progressively harder, and each enemy has a unique trait.
* The game has an indefinite ending. 

# Data structures used and their purpose 

[HashMap]
Used in HighScoreManager to store and manage the highest score the player has achieved. 

[Queues]
Used in InstructionScreen, to switch between screens when the player hits next or back. 

[Arrays and ArrayLists]
Arrays are used to store the animation frames for different player states (idle, running, jumping, attacking). These frames are loaded into GreenfootImage[] arrays and accessed by the animate() method to display the correct animation based on the player's current state.

# Additional information (features)

Ending screen
  1. Click the `view ending` button if the bosses are too hard to beat.

Gameplay
  1. A wide variety of music and sound effects
   

UI (User Interface)
  1. Health bars are included
  2. 

2. Music Manager
     - The MusicMManager is a separate class that manages all in-game music.
     - It uses methods like .playLoop or .setVolume
3. Buttons
     - This object is used mostly before and after the game's content (before the game        starts or when the game is complete).
     - Button plays a sound effect when clicked.
     - Button increases in size when hovered over
     - public Button (Runnable action, String imageName, String hoverImgName)
4. Scrolling background
     - For the background image of ScreenSelect, a scrolling background is used.











# Set up Greenfoot for VSCode
[Mac]
1. Open `Finder` and navigate to the Applications folder.
2. Right-click on the Greenfoot app and select `Show Package Contents`.
3. In the opened folder, go to `Contents -> Resources -> Java` and find the `greenfoot.jar` file.
4. Copy this `greenfoot.jar` to the `/lib` Folder in this project.

[Windows]
1. Open `File Explorer` and navigate to `C:\Program Files\Greenfoot` and locate the `greenfoot.jar` file.
2. Copy this `greenfoot.jar` to the `\lib` Folder in this project.

<br>
<br>

# project.greenfoot
Copy the `project.greenfoot.md` file and rename it to `project.greenfoot`

# Learn Markdown Basics
[Markdown basics](https://www.markdownguide.org/getting-started/)

Remember to push origin!!!!
