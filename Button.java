import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootSound selectSound = new GreenfootSound("menuSelectSound2.mp3");
    private Runnable action; 
    private GreenfootImage buttonImage;
    public Button(Runnable action, String imageName)
    {
        this.action = action;
        buttonImage = new GreenfootImage(imageName); //requirement when calling Button
        setImage(buttonImage);
    }
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
        {
            selectSound.play(); //play when clicked
            if(action != null)
            {
                action.run();
            }
        }
    }
}
