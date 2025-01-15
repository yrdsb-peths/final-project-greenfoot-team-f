import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;

public class FightWorldThree extends World
{
    private GreenfootSound backgroundMusic;
    private int countdownFrame = 0;
    private boolean animationFinished = false;

    private GreenfootSound countdownSfx;

    private int frameDelay = 3; // Adjust to control the speed of animation
    private int delayCounter = 0; // Counter to implement the delay 

    private boolean objectsSpawned = false;

    private HealthBar playerHealthBar;
    private HealthBar enemyHealthBar;

    private MainPlayer mainPlayer;
    private Kenny kennyEnemy;

    private FadeOverlay fadeOverlay;
    private boolean isFading = false;

    public FightWorldThree()
    {    
        super(600, 400, 1);

        GreenfootImage background = new GreenfootImage("stage3.png"); // Background for stage 3
        
        GreenfootImage levelNum = new GreenfootImage("levelThree.png");
        background.drawImage(levelNum, (getWidth() - levelNum.getWidth()) / 2, (getHeight() - levelNum.getHeight()) / 2 - 149);
        
        countdownSfx = new GreenfootSound("countdownsfx.mp3");  
        countdownSfx.setVolume(100);
        countdownSfx.play();

        setBackground(background);
        
        MusicManager.playThreeMusic(); // Play stage three music
        
        prepare();
        
        setPaintOrder(FadeOverlay.class, LevelNum.class, BarFrame.class, HealthBar.class);
    }

    public void act()
    {
        if (isFading && fadeOverlay != null && fadeOverlay.isFadeComplete()) 
        {
            Greenfoot.setWorld(new FightWorldFour());
        }
        
        if (!animationFinished) 
        {
            playCountdownAnimation(); // Handle the countdown animation
        } 
        else if (!objectsSpawned) // Ensure objects are added only once
        {
            spawnObjects(); 
    
            objectsSpawned = true; // Set the flag to prevent re-spawning
        }
        
        if (playerHealthBar != null && mainPlayer != null) 
        {
            playerHealthBar.setHealth(mainPlayer.getHealth());
        }
    
        if (enemyHealthBar != null && kennyEnemy != null) 
        {
            enemyHealthBar.setHealth(kennyEnemy.getHealth());
            
            if (kennyEnemy.getHealth() <= 0) 
            {
                clearLevel(3); // Mark level 3 as cleared
            }
        }
    }

    private void playCountdownAnimation()
    {
        if (delayCounter < frameDelay) 
        {
            delayCounter++; // Increment the delay counter
            return; // Wait until delay is met
        }
    
        delayCounter = 0; // Reset the delay counter
    
        if (countdownFrame <= 64) 
        {
            String frameNumber = "";
            if (countdownFrame < 10) 
            {
                frameNumber = "0" + countdownFrame;
            } 
            else 
            {
                frameNumber = Integer.toString(countdownFrame);
            }
    
            String fileName = "countdown_" + frameNumber + ".png";
    
            // Combine the countdown image with the background
            GreenfootImage background = new GreenfootImage("stage3.png"); // Stage 3 background
            GreenfootImage countdownOverlay = new GreenfootImage(fileName);
            countdownOverlay.scale(600, 400);
            
            LevelNum levelNum = new LevelNum("lvlThree.png");
            addObject(levelNum, 300, 53);
            
            background.drawImage(countdownOverlay, 
                                 (getWidth() - countdownOverlay.getWidth()) / 2, 
                                 (getHeight() - countdownOverlay.getHeight()) / 2 + 20);
            setBackground(background);
    
            countdownFrame++;
        } 
        else 
        {
            animationFinished = true; // Mark the animation as finished
        }
    }

    private void prepare()
    {
        // Initialize health bars
        playerHealthBar = new HealthBar(100, 183, 40, true);
        enemyHealthBar = new HealthBar(100, 184, 40, false);

        // Add health bars
        addObject(playerHealthBar, 181, 45); // Player health bar on the left
        addObject(enemyHealthBar, 420, 45); // Enemy health bar on the right

        // Add platforms
        Platform platform1 = new Platform();
        addObject(platform1, 450, 240);

        Platform platform2 = new Platform();
        addObject(platform2, 160, 240);
        
        BarFrame barBar = new BarFrame("healthFrame.png"); 
        addObject(barBar, 130, 48); 
        
        BarFrame kennyFrame = new BarFrame("kennyFrame.png"); // Update with Kenny's health bar frame
        addObject(kennyFrame, 474, 48);
    }
    
    private void spawnObjects()
    {
        mainPlayer = new MainPlayer();
        kennyEnemy = new Kenny(mainPlayer); // Spawn Kenny instead of Ichigo

        addObject(mainPlayer, 200, 350);
        addObject(kennyEnemy, 400, 350);
    
        objectsSpawned = true; // Set the flag to prevent re-spawning
    }
    
    public void startFadeOut() 
    {
        if (!isFading) 
        {
            isFading = true;
            fadeOverlay = new FadeOverlay();
            addObject(fadeOverlay, getWidth() / 2, getHeight() / 2);
            fadeOverlay.startFadeOut();
        }
    }
    
    private void clearLevel(int level)
    {
        HighScoreManager.updateHighScore("LEVEL", level); // Update high score for player
    }
}
