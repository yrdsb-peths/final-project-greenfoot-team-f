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
        
        // Reset cleared levels on game reset
        LevelClearManager.resetLevels();
        
        scrollX = 0;
        counter = 0;

        addObject(new Button(this::goLvlOne, "lvlOne.png", "lvlOne.png"), 150, 265);

        // Add buttons dynamically for cleared levels
        if (LevelClearManager.isLevelCleared(1))
        {
            addObject(new Button(this::goLvlTwo, "lvlTwo.png", "lvlTwo.png"), 250, 265);
        }
        if (LevelClearManager.isLevelCleared(2))
        {
            addObject(new Button(this::goLvlThree, "lvlThree.png", "lvlThree.png"), 350, 265);
        }
        if (LevelClearManager.isLevelCleared(3))
        {
            addObject(new Button(this::goLvlFour, "lvlFour.png", "lvlFour.png"), 450, 265);
        }

        // Instruction button
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 499, 371);
        addObject(new Button(this::goHighScore, "highScoreButton.png", "highScoreButton.png"), 499, 350);

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

