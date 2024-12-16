import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
  
    private final int gravity = 1;
    private int velocity;
    
    private GreenfootImage[] idleFrames;
    private GreenfootImage[] runFrames;
    
    private int currentFrame;
    private int animationCounter;
    
    private boolean facingRight;
    
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
        
        runFrames = new GreenfootImage[4];
        for (int i = 0; i < runFrames.length; i++)
        {
            runFrames[i] = new GreenfootImage("run_" + (i + 1) + ".png");
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

        if (Greenfoot.isKeyDown("a")) // Move left
        {
            x -= 3;
            if (!facingRight) // Flip image if necessary
            {
                flipDirection();
            }
        }

        if (Greenfoot.isKeyDown("d")) // Move right
        {
            x += 3;
            if (facingRight) // Flip image if necessary
            {
                flipDirection();
            }
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

        if (animationCounter >= 6) // Adjust this value to control animation speed
        {
            if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d")) // Running animation
            {
                currentFrame = (currentFrame + 1) % runFrames.length; // Loop through run frames
                setImage(runFrames[currentFrame]); // Set the current frame
            }
            else // Idle animation
            {
                currentFrame = (currentFrame + 1) % idleFrames.length; // Loop through idle frames
                setImage(idleFrames[currentFrame]); // Set the current frame
            }

            animationCounter = 0;
        }
    }
    
    private void flipDirection()
    {
        facingRight = !facingRight;

        // Flip all frames (idle and run)
        for (GreenfootImage frame : idleFrames)
        {
            frame.mirrorHorizontally();
        }
        for (GreenfootImage frame : runFrames)
        {
            frame.mirrorHorizontally();
        }
    }
    
}
