import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
  
    private final int gravity = 1;
    private int velocity;
    
    private GreenfootImage[] idleFrames;
    private GreenfootImage[] runFrames;
    private GreenfootImage[] jumpFrames;
    
    private int currentFrame;
    private int animationCounter;
    
    private boolean facingRight;
    
    private boolean isJumping; // Track player jump animation
    private int jumpFrameIndex; // Track the current frame of jump

    
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
        
        jumpFrames = new GreenfootImage[6];
        for (int i = 0; i < jumpFrames.length; i++)
        {
            jumpFrames[i] = new GreenfootImage("jump_" + (i + 1) + ".png");
        }
        
        // Flip jump frames if initially not facing right
        if (!facingRight)
        {
            for (GreenfootImage frame : jumpFrames)
            {
                frame.mirrorHorizontally();
            }
        }
        
        facingRight = true; 
        
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
        velocity = -18; // Apply upward velocity
        isJumping = true; // Start jump animation
        jumpFrameIndex = 0; // Reset jump animation frame
    }


    
    public void move()
    {
        int y = getY();
        int x = getX();
    
        if (Greenfoot.isKeyDown("a")) // Move left
        {
            x -= 3;
            if (facingRight) // Flip image if necessary
            {
                flipDirection();
            }
        }
    
        if (Greenfoot.isKeyDown("d")) // Move right
        {
            x += 3;
            if (!facingRight) // Flip image if necessary
            {
                flipDirection();
            }
        }
    
        setLocation(x, y);
    }


    
    public void fall()
    {
        // Update player's y position by adding the current velocity to the Y coord
        setLocation(getX(), getY() + velocity); 
        
        //check if player is near ground(50 px above bottom);
        if(getY() > getWorld().getHeight() - 50)
        {
            velocity = 0; 
            //if player on ground, set velocity to 0
        }
        else
        {
            velocity += gravity;
            //if player in the air, increase velocity by gravity. (to simulate acceleration);
        }
    }
    
    private void animate()
    {
        animationCounter++;
    
        if (isJumping) // Play jump animation
        {
            if (animationCounter >= 6) // Adjust to control jump animation speed
            {
                GreenfootImage currentJumpFrame = new GreenfootImage(jumpFrames[jumpFrameIndex]); // Get current frame
                
                // Flip frame dynamically if not facing right
                if (facingRight)
                {
                    currentJumpFrame.mirrorHorizontally();
                }
    
                setImage(currentJumpFrame); // Set the current jump frame
                
                jumpFrameIndex++; // Move to the next frame
    
                if (jumpFrameIndex >= jumpFrames.length) // End jump animation
                {
                    isJumping = false; // Stop the jump animation
                    jumpFrameIndex = 0; // Reset frame index
                }
    
                animationCounter = 0; // Reset animation counter
            }
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d")) // Running animation
        {
            if (animationCounter >= 6) // Control running animation speed
            {
                GreenfootImage currentRunFrame = new GreenfootImage(runFrames[currentFrame]); // Get current frame
    
                // Flip frame dynamically if not facing right
                if (!facingRight)
                {
                    currentRunFrame.mirrorHorizontally();
                }
    
                setImage(currentRunFrame); // Set the current run frame
                
                currentFrame = (currentFrame + 1) % runFrames.length; // Loop through run frames
                animationCounter = 0;
            }
        }
        else // Idle animation
        {
            if (animationCounter >= 6) // Control idle animation speed
            {
                GreenfootImage currentIdleFrame = new GreenfootImage(idleFrames[currentFrame]); // Get current frame
                
                // Flip frame dynamically if not facing right
                if (!facingRight)
                {
                    currentIdleFrame.mirrorHorizontally();
                }
    
                setImage(currentIdleFrame); // Set the current idle frame
                
                currentFrame = (currentFrame + 1) % idleFrames.length; // Loop through idle frames
                animationCounter = 0;
            }
        }
    }

    
    private void flipDirection()
    {
        facingRight = !facingRight; // Toggle the facing direction
    }


    
}
