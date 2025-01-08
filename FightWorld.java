import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
/**
 * Write a description of class FightWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FightWorld extends World
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
    private Kisuke kisukeEnemy;
    
    public FightWorld()
    {    
        super(600, 400, 1);

        backgroundMusic = new GreenfootSound("stageOneMusic.mp3");
        countdownSfx = new GreenfootSound("countdownsfx.mp3");  
        
        countdownSfx.setVolume(100);
        countdownSfx.play();
        
    
        backgroundMusic.setVolume(50);
        backgroundMusic.playLoop();
        
        setBackground("stage_1alt.png");
        
        prepare();
    }
    
    public void act()
    {
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
    
        if (enemyHealthBar != null && kisukeEnemy != null) 
        {
            enemyHealthBar.setHealth(kisukeEnemy.getHealth());
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
            GreenfootImage background = new GreenfootImage("stage_1alt.png");
            GreenfootImage countdownOverlay = new GreenfootImage(fileName);
            background.drawImage(countdownOverlay, 
                                 (getWidth() - countdownOverlay.getWidth()) / 2, 
                                 (getHeight() - countdownOverlay.getHeight()) / 2);
            setBackground(background);
    
            countdownFrame++;
        } 
        else 
        {
            animationFinished = true; // Mark the animation as finished
        }
    }

    
    public void stopped()
    {
        // Pause music when the world is stopped
        backgroundMusic.pause();
    }
    
    public void started()
    {
        // Resume music when the world starts
        backgroundMusic.playLoop();
    }

    private void prepare()
    {
        // Initialize health bars
        playerHealthBar = new HealthBar(100, 250, 30, true);
        enemyHealthBar = new HealthBar(100, 250, 30, false);

        // Add health bars
        addObject(playerHealthBar, 150, 45); // Player health bar on the left
        addObject(enemyHealthBar, 450, 45); // Enemy health bar on the right

        // Add platforms
        Platform platform1 = new Platform();
        addObject(platform1, 450, 260);

        Platform platform2 = new Platform();
        addObject(platform2, 160, 260);
    }
    
    private void spawnObjects()
    {
        mainPlayer = new MainPlayer();
        kisukeEnemy = new Kisuke(mainPlayer);

        addObject(mainPlayer, 200, 350);
        addObject(kisukeEnemy, 400, 350);

        objectsSpawned = true; // Set the flag to prevent re-spawning
    }
    
    

}
