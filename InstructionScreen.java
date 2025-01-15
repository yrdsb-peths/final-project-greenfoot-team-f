import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    private GreenfootImage background;
    private Stack<World> screenHistory; //Stack to keep track of screen history
    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("better.png");
        
        GreenfootImage uryuImage = new GreenfootImage("uryuImage.png");
        uryuImage.scale(780, 400);
        background.drawImage(uryuImage, (getWidth() - uryuImage.getWidth()) / 2 + 170, (getHeight() - uryuImage.getHeight()) / 2 + 20);
        
        GreenfootImage instructionText1 = new GreenfootImage("instructionText1.png");
        instructionText1.scale(300, 300);
        instructionText1.setTransparency(230);
        background.drawImage(instructionText1, (getWidth() - instructionText1.getWidth()) / 2 - 120, (getHeight() - instructionText1.getHeight()) / 2);
        
        setBackground(background);
        //button that proceeds to stage
        addObject(new Button(this:: goScreenSelect, "MenuButton.png", "MenuButton.png"), 70,30);
        
        Button nextButton = new Button(this::nextInstruction, "nextButton.png", "nextButton.png");
        addObject(nextButton, 550, 360);
    }

    public void screenBreakdown()
    {
        removeObjects(getObjects(Button.class));
        
        GreenfootImage background = new GreenfootImage("better.png");
                
        GreenfootImage screenBreakdown = new GreenfootImage("screenBreakdown.png");
        screenBreakdown.scale(500, 350);
        background.drawImage(screenBreakdown, (getWidth() - screenBreakdown.getWidth()) / 2 + 40, (getHeight() - screenBreakdown.getHeight()) / 2 + 20);
        
        GreenfootImage kisukeImage = new GreenfootImage("kisuke.png");
        kisukeImage.scale(450, 300);
        background.drawImage(kisukeImage, (getWidth() - kisukeImage.getWidth()) / 2 - 170, (getHeight() - kisukeImage.getHeight()) / 2 + 50);
        
        GreenfootImage hitsugayaImage = new GreenfootImage("hitsugayaImage.png");
        hitsugayaImage.scale(260, 400);
        background.drawImage(hitsugayaImage, (getWidth() - hitsugayaImage.getWidth()) / 2 + 220, (getHeight() - hitsugayaImage.getHeight()) / 2 + 110);
        
        GreenfootImage labels = new GreenfootImage("label.png");
        labels.scale(390, 120);
        background.drawImage(labels, (getWidth() - labels.getWidth()) / 2 - 109, (getHeight() - labels.getHeight()) / 2 - 133);
        
        GreenfootImage platformNote = new GreenfootImage("platformNote.png");
        platformNote.scale(230, 180);
        background.drawImage(platformNote, (getWidth() - platformNote.getWidth()) / 2 - 20, (getHeight() - platformNote.getHeight()) / 2 + 100);
        
        GreenfootImage enemiesNote = new GreenfootImage("enemiesNote.png");
        enemiesNote.scale(230, 180);
        background.drawImage(enemiesNote, (getWidth() - enemiesNote.getWidth()) / 2 + 10, (getHeight() - enemiesNote.getHeight()) / 2);
        
        addObject(new Button(this:: goScreenSelect, "MenuButton.png", "MenuButton.png"), 70,30);
        
        Button prevButton = new Button(this::nextInstruction, "backButton.png", "backButton.png");
        addObject(prevButton, 50, 360);
        
        setBackground(background);
    }
    
    public void nextInstruction()
    {
        removeObjects(getObjects(Button.class));
        
        GreenfootImage background = new GreenfootImage("better.png");
                
        GreenfootImage ichigoImage = new GreenfootImage("ichigoImage.png");
        ichigoImage.scale(700, 480);
        background.drawImage(ichigoImage, (getWidth() - ichigoImage.getWidth()) / 2 - 100, (getHeight() - ichigoImage.getHeight()) / 2 - 30);
        
        GreenfootImage instructionText2 = new GreenfootImage("instructionText2.png");
        instructionText2.scale(300, 300);
        instructionText2.setTransparency(230);
        background.drawImage(instructionText2, (getWidth() - instructionText2.getWidth()) / 2 + 170, (getHeight() - instructionText2.getHeight()) / 2);
        
        addObject(new Button(this:: goScreenSelect, "MenuButton.png", "MenuButton.png"), 70,30);
        
        Button prevButton = new Button(this::prevInstruction, "backButton.png", "backButton.png");
        addObject(prevButton, 50, 360);
        
        Button nextButton = new Button(this::screenBreakdown, "nextButton.png", "nextButton.png");
        addObject(nextButton, 550, 360);
        
        setBackground(background);
    }
    
    public void prevInstruction()
    {
        removeObjects(getObjects(Button.class));
        
        GreenfootImage background = new GreenfootImage("better.png");
        
        GreenfootImage uryuImage = new GreenfootImage("uryuImage.png");
        uryuImage.scale(780, 400);
        background.drawImage(uryuImage, (getWidth() - uryuImage.getWidth()) / 2 + 170, (getHeight() - uryuImage.getHeight()) / 2 + 20);
        
        GreenfootImage instructionText1 = new GreenfootImage("instructionText1.png");
        instructionText1.scale(300, 300);
        instructionText1.setTransparency(230);
        background.drawImage(instructionText1, (getWidth() - instructionText1.getWidth()) / 2 - 120, (getHeight() - instructionText1.getHeight()) / 2);
        
        addObject(new Button(this:: goScreenSelect, "MenuButton.png", "MenuButton.png"), 70,30);
        
        Button nextButton = new Button(this::nextInstruction, "nextButton.png", "nextButton.png");
        addObject(nextButton, 550, 360);
        
        setBackground(background);
    }
    
    public void goFightStage()
    {
        Greenfoot.setWorld(new FightWorld()); 
    }
    
    public void goScreenSelect()
    {
        Greenfoot.setWorld(new ScreenSelect());
    }
}
