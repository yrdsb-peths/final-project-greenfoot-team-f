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
    private GreenfootImage hoverImage;
    
    public Button(Runnable action, String imageName, String hoverName)
    {
        this.action = action;
        buttonImage = new GreenfootImage(imageName); //requirement when calling Button
        hoverImage = new GreenfootImage(hoverName); 
        
        hoverImage.scale((int)(hoverImage.getWidth()*6/5), (int)(hoverImage.getHeight()*6/5));

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
        if (Greenfoot.mouseMoved(this))
        {
            setImage(hoverImage); // Set hover image when mouse is over the button
        } 
        else if (Greenfoot.mouseMoved(null))
        {
            setImage(buttonImage); // Set normal image when mouse is not over the button
        }
    }
}
