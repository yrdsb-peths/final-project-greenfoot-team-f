import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
  
    private final int gravity = 1;
    private int velocity;
    
    private GreenfootImage[] idleFrames;
    private int currentFrame;
    private int animationCounter;
    
    public MainPlayer()
    {
        velocity = 0; 
        
        
        currentFrame = 0; 
        animationCounter = 0;
        
        idleFrames = new GreenfootImage[4];
        
        for(int i = 0; i < idleFrames.length; i++)
        {
            idleFrames[i] = new GreenfootImage("playerIdle_" + (i + 1) + ".png");
        }
        
        setImage(idleFrames[0]); 
    }
    
    
    public void act()
    {
        fall();
        if(Greenfoot.isKeyDown("space") && getY() > getWorld().getHeight() - 50)
        {
            jump(); 
        }
        
        move(); 
        
        animate(); 
    }
    
    private void jump()
    {
        velocity =- 20; 
    }
    
    public void move()
    {
        int y = getY();
        int x = getX(); 
        if(Greenfoot.isKeyDown("a"))
        {
            x -= 3;
        }
        
        if(Greenfoot.isKeyDown("d"))
        {
            x += 3;
        }
        setLocation(x, y); 
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
    
    private void animate()
    {
        animationCounter++;
        if (animationCounter >= 10) //animation speed
        {
            currentFrame = (currentFrame + 1) % idleFrames.length; // Loop through frames
            setImage(idleFrames[currentFrame]); // Set the current frame
            animationCounter = 0;
        }
    }
    
    
}
