import greenfoot.*;
import greenfoot.GreenfootImage;


public class Button extends Actor {
    private Runnable action;
    private GreenfootImage buttonImage;
    private GreenfootImage hoverImage;
 Scarlett
    
    public Button(Runnable action, String imageName, String hoverName)
    {
        this.action = action;
        buttonImage = new GreenfootImage(imageName); //requirement when calling Button
        hoverImage = new GreenfootImage(hoverName); 
        
        hoverImage.scale((int)(hoverImage.getWidth()*6/5), (int)(hoverImage.getHeight()*6/5));


    private GreenfootSound selectSound = new GreenfootSound("menuSelectSound.mp3");
    
    public Button(Runnable action, String imageName, String hoverimgName)
    {
        this.action = action;

        //Load the button image
        buttonImage = new GreenfootImage(imageName);
        hoverImage = new GreenfootImage(hoverimgName); 
        
        //enlarge button image when hovered over
        hoverImage.scale((int)(hoverImage.getWidth()*6/5), (int)(hoverImage.getHeight()*6/5));
        
        // Set combined image as the actor's image
 main
        setImage(buttonImage);
    }
    
    //check if user clicked on (this) obeject. 
    public void act() {
        if (Greenfoot.mouseClicked(this))
        {
            selectSound.play();
            if (action != null)
            {
                action.run();
            }
        }
Scarlett
        
 main
        if (Greenfoot.mouseMoved(this))
        {
            setImage(hoverImage); // Set hover image when mouse is over the button
        } 
        else if (Greenfoot.mouseMoved(null))
        {
            setImage(buttonImage); // Set normal image when mouse is not over the button
        }
 Scarlett

        
 main
    }
}
