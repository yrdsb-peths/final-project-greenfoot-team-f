import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
  
    private final int gravity = 1;
    private int velocity;
    
    public MainPlayer()
    {
        velocity = 0; 
        setImage("playerIdle_1.png"); 
        
    }
    
    
    public void act()
    {
        fall();
    }
    
    public void fall()
    {
        setLocation(getX(), getY() + velocity); 
        if(getY() > getWorld().getHeight() - 50)
        {
            velocity = 0; 
        }
        else
        {
            velocity += gravity;
        }
    }
}
