import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ScreenSelect extends World
{
    private GreenfootImage background;
    private int scrollX;
    private int counter;

    public ScreenSelect()
    {
        super(600, 400, 1);
    
        background = new GreenfootImage("instructionScreenBgBleach.png");
        setBackground(background);
        
        addObject(new Button(this:: goLvlOne, "clearLevelOne.png", "clearLevelOne.png"), 150,200);
        
        addObject(new Button(this:: goEndingScreen, "viewEnding.png", "viewEnding.png"), 300, 350);
        
        addObject(new LevelNum("lockLevelTwo.png"), 250,200);
        
        addObject(new LevelNum("lockLevelThree.png"), 350,200);
        
        addObject(new LevelNum("lockLevelFour.png"), 450,200);
        
        addObject(new LevelNum("selectALevel.png"), 300,90);
        
        // Dynamically add buttons for cleared levels
        if (LevelClearManager.isLevelCleared(1))
        {
            addObject(new Button(this::goLvlTwo, "clearLevelTwo.png", "clearLevelTwo.png"), 250, 200);
            
            addObject(new LevelNum("lockLevelThree.png"), 350,200);
        }
        
        if (LevelClearManager.isLevelCleared(2))
        {
            addObject(new Button(this:: goLvlThree, "clearLevelThree.png", "clearLevelThree.png"), 350, 200);
            
            addObject(new LevelNum("lockLevelFour.png"), 450,200);
        }
        
        if (LevelClearManager.isLevelCleared(3))
        {
            addObject(new Button(this:: goLvlFour, "clearLevelFour.png", "clearLevelFour.png"), 450, 200);
        }
    
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 101, 371);
        
        addObject(new Button(this::goHighScore, "highScoreButton.png", "highScoreButton.png"), 499, 371);
        
        addObject(new Button(this::goMenu, "backButton.png", "backButton.png"), 35, 51);
        
    }

    public void goMenu()
    {
        Greenfoot.setWorld(new MenuScreen());
    }

    public void stopped()
    {
        MusicManager.pauseMenuMusic();
    }

    public void goHighScore()
    {
        int highestLevel = HighScoreManager.getHighScore("LEVEL");
        Greenfoot.setWorld(new HighScoreScreen(highestLevel));
    }

    public void act()
    {
        scrollBackground();
    }

    public void scrollBackground()
    {
        counter++;
        if (counter >= 5)
        {
            scrollX += 2;
            counter = 0;
        }

        GreenfootImage scrollImage = new GreenfootImage(getWidth(), getHeight());
        int imageWidth = background.getWidth();

        int x = scrollX % imageWidth;

        scrollImage.drawImage(background, -x, 0);
        scrollImage.drawImage(background, -x + imageWidth, 0);

        setBackground(scrollImage);
    }

    private void goInstructions()
    {
        Greenfoot.setWorld(new InstructionScreen());
    }

    private void goLvlOne()
    {
        MusicManager.pauseMenuMusic();
        Greenfoot.setWorld(new FightWorld());
    }

    private void goLvlTwo()
    {
        MusicManager.pauseMenuMusic();
        Greenfoot.setWorld(new FightWorldTwo());
    }

    private void goLvlThree()
    {
        MusicManager.pauseMenuMusic();
        Greenfoot.setWorld(new FightWorldThree());
    }

    private void goLvlFour()
    {
        MusicManager.pauseMenuMusic();
        Greenfoot.setWorld(new FightWorldFour());
    }
    
    private void goEndingScreen()
    {
        MusicManager.pauseMenuMusic();
        Greenfoot.setWorld(new EndingScreen());
    }
}

