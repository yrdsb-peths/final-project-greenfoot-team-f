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
        
        scrollX = 0;
        counter = 0;
        // Instruction button
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 499,371);
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 499,350);

        addObject(new Button(this:: goMenu, "backButton.png", "backButton.png"), 35,51);
        //Fight/start game button
        addObject(new Button(this:: goFightStage, "fight.png", "fight.png"), 310,190);
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
}
