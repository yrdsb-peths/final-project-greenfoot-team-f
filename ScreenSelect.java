import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScreenSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScreenSelect extends World
{
    private GreenfootImage background;
    private int scrollX;
    private int counter;
    
    /**
     * Constructor for objects of class ScreenSelect.
     * 
     */
    public ScreenSelect()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);

    
        background = new GreenfootImage("instructionScreenBgBleach.png");
        
        addObject(new Button(this:: goLvlOne, "clearLevelOne.png", "clearLevelOne.png"), 150,200);
        
        if (LevelClearManager.isLevelClear(1)) 
        {
            addObject(new Button(this:: goLvlTwo, "clearLevelTwo.png", "clearLevelOne.png"), 250, 200);

        }
        
        if (LevelClearManager.isLevelClear(2)) 
        {
            addObject(new Button(this:: goLvlThree, "clearLevelThree.png", "clearLevelThree.png"), 350, 200);
        }
    
        if (LevelClearManager.isLevelClear(3)) 
        {
            addObject(new Button(this:: goLvlFour, "clearLevelThree.png", "clearLevelThree.png"), 450, 200);
        }
                
<<<<<<< HEAD

    
        background = new GreenfootImage("instructionScreenBgBleach.png");

=======
>>>>>>> parent of 6bee8b9 (Merge branch 'main' into origin/Nicholas)
        setBackground(background);
        
        scrollX = 0;
        counter = 0;
<<<<<<< HEAD
    
        addObject(new Button(this::goLvlOne, "lvlOne.png", "lvlOne.png"), 150, 200);
    
        // Dynamically add buttons for cleared levels
        if (LevelClearManager.isLevelCleared(1))
        {
            addObject(new Button(this::goLvlTwo, "lvlTwo.png", "lvlTwo.png"), 250, 200);

        }
        
        if (LevelClearManager.isLevelCleared(2))
        {

            addObject(new Button(this:: goLvlThree, "clearLevelThree.png", "clearLevelThree.png"), 350, 200);

            addObject(new Button(this::goLvlThree, "lvlThree.png", "lvlThree.png"), 350, 200);

        }
        
        if (LevelClearManager.isLevelCleared(3))
        {

            addObject(new Button(this:: goLvlFour, "clearLevelThree.png", "clearLevelThree.png"), 450, 200);

            addObject(new Button(this::goLvlFour, "lvlFour.png", "lvlFour.png"), 450, 200);

        }
    
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 101, 371);
        
        addObject(new Button(this::goHighScore, "highScoreButton.png", "highScoreButton.png"), 499, 371);
        
        addObject(new Button(this::goMenu, "backButton.png", "backButton.png"), 35, 51);
        
    }
=======
        // Instruction button
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 101,371);
        addObject(new Button(this::goHighScore, "highScoreButton.png", "highScoreButton.png"), 499,371);
>>>>>>> parent of 6bee8b9 (Merge branch 'main' into origin/Nicholas)

        addObject(new Button(this:: goMenu, "backButton.png", "backButton.png"), 35,51);
        //Fight/start game button
        //addObject(new Button(this:: goFightStage, "fight.png", "fight.png"), 310,180);
    }
    
    public void goMenu()
    {
         Greenfoot.setWorld(new MenuScreen()); 
    }
    
    public void stopped()
    {
        // pause music when the world stopped
        MusicManager.pauseMenuMusic(); 
    }
    
    public void goHighScore()
    {
        int highestLevel = HighScoreManager.getHighScore("LEVEL"); // Example to fetch the high score
        Greenfoot.setWorld(new HighScoreScreen(highestLevel));
    }
    
    public void act()
    {
        scrollBackground();
    }
    
    public void scrollBackground()
    {
        counter++; //Increase the counter
        
        //Update scroll position every 5 frames
        if(counter >= 5)
        {
            scrollX += 2;
            counter = 0;
        }
        
        GreenfootImage scrollImage = new GreenfootImage(getWidth(), getHeight());
        int imageWidth = background.getWidth();
        
        int x = scrollX % imageWidth;
        
        //Drawing the image to cover the empty areas during the animation
        scrollImage.drawImage(background, -x, 0);
        scrollImage.drawImage(background, -x + imageWidth, 0);
        
        //Set the background to the scrolled image
        setBackground(scrollImage);
    }
    
    private void goInstructions()
    {
        Greenfoot.setWorld(new InstructionScreen());
    }
    
    private void goFightStage()
    {
        MusicManager.stopMenuMusic(); 
        Greenfoot.setWorld(new FightWorld()); 
    }
    
    private void goLvlOne()
    {
        Greenfoot.setWorld(new FightWorld());
    }
    
    private void goLvlTwo()
    {
        Greenfoot.setWorld(new FightWorldTwo());
    }
    
    private void goLvlThree()
    {
        Greenfoot.setWorld(new FightWorldThree());
    }
    
    private void goLvlFour()
    {
        Greenfoot.setWorld(new FightWorldFour());
    }
}
