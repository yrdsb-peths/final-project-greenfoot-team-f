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
    private int countdownTimer = 180;
    private GreenfootImage countdownImage;
    public FightWorld()
    {    
        super(600, 400, 1);

        backgroundMusic = new GreenfootSound("stageOneMusic.mp3");

        // Uncomment to play background music when not in the initial world
        // backgroundMusic.playLoop();
        backgroundMusic.setVolume(50);

        setBackground("stage_1.png");
        
        prepare();
    }
    
    public void act()
    {
        if(countdownTimer > 0)
        {
            countdownStart();
        }
        else
        {
            if(!backgroundMusic.isPlaying())
            {
                backgroundMusic.playLoop();
                
                MainPlayer mainPlayer = new MainPlayer();
                addObject(mainPlayer, 200, 350);
                
                Kisuke kisukeEnemy = new Kisuke(mainPlayer);
                addObject(kisukeEnemy, 400, 350); // Position Kisuke on the stage
            }
        }
    }
    
    private void countdownStart()
    {
        if(countdownTimer > 120)
        {
            countdownImage = new GreenfootImage("three.png");
        }
        else if(countdownTimer > 60)
        {
            countdownImage = new GreenfootImage("two.png");
        }
        else if(countdownTimer > 0)
        {
            countdownImage = new GreenfootImage("one.png");
        }
        else
        {
            countdownImage = new GreenfootImage("fight.png");
        }
        
        //Display the countdown image
        getBackground().drawImage(countdownImage, getWidth() / 2 - countdownImage.getWidth() / 2, getHeight() / 2 - countdownImage.getHeight() / 2);
        
        //Decrease the countdown timer
        countdownTimer--;
        
        if(countdownTimer == 0)
        {
            setBackground("stage_1.png");
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
        MainPlayer mainPlayer = new MainPlayer();

        // Add Kisuke enemy to the stage
        Kisuke kisukeEnemy = new Kisuke(mainPlayer);
        
        kisukeEnemy.setFacingRight(false);
        
        //add platform
        Platform platform = new Platform();
        addObject(platform, 450, 260); 
    }
    
}
