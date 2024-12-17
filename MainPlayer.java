import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
  
    private final int gravity = 1;
    private int velocity;
    
    private GreenfootImage[] idleFrames;
    private GreenfootImage[] runFrames;
    private GreenfootImage[] jumpFrames;
    private GreenfootImage[] attackFrames;
    
    private int currentFrame;
    private int animationCounter;
    
    private boolean facingRight;
    
    //attack ani variables
    private boolean isAttacking = false;
    private int attackFrameIndex = 0; 
    
    //jump ani variables
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
        
        attackFrames = new GreenfootImage[8];
        for (int i = 0; i < attackFrames.length; i++)
        {
            attackFrames[i] = new GreenfootImage("attack_" + (i + 1) + ".png");
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
        //main character always starts facing right
        
        //set idle when spawned / created
        setImage(idleFrames[0]); 
    }
    
    
    public void act()
    {
        fall();
        if(Greenfoot.isKeyDown("space") && getY() > getWorld().getHeight() - 50)
        {
            jump();
            Greenfoot.playSound("jumpSfx.mp3");
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
    
        // Check if the player pressed "P" for attack
        if (Greenfoot.isKeyDown("p") && !isAttacking)
        {
            isAttacking = true; // Start attack animation
            attackFrameIndex = 0; // Reset attack frame index
        }
    
        // Play attack animation
        if (isAttacking)
        {
            if (animationCounter >= 6) // Control attack animation speed
            {
                GreenfootImage currentAttackFrame = new GreenfootImage(attackFrames[attackFrameIndex]); // Get current frame
                
                // Flip attack frame dynamically if not facing right
                if (!facingRight)
                {
                    currentAttackFrame.mirrorHorizontally();
                }
    
                setImage(currentAttackFrame); // Set the current attack frame
                
                attackFrameIndex++; // Move to the next attack frame
                
                if (attackFrameIndex >= attackFrames.length) // End attack animation
                {
                    isAttacking = false; // Stop attack animation
                    attackFrameIndex = 0; // Reset frame index
                }
                
                animationCounter = 0; // Reset animation counter
            }
        }
        // If not attacking, proceed with other animations
        else if (isJumping)
        {
            if (animationCounter >= 6)
            {
                GreenfootImage currentJumpFrame = new GreenfootImage(jumpFrames[jumpFrameIndex]);
                if (!facingRight) currentJumpFrame.mirrorHorizontally();
                setImage(currentJumpFrame);
                jumpFrameIndex++;
                if (jumpFrameIndex >= jumpFrames.length) { isJumping = false; jumpFrameIndex = 0; }
                animationCounter = 0;
            }
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d"))
        {
            if (animationCounter >= 6)
            {
                GreenfootImage currentRunFrame = new GreenfootImage(runFrames[currentFrame]);
                if (!facingRight) currentRunFrame.mirrorHorizontally();
                setImage(currentRunFrame);
                currentFrame = (currentFrame + 1) % runFrames.length;
                animationCounter = 0;
            }
        }
        else
        {
            if (animationCounter >= 6)
            {
                GreenfootImage currentIdleFrame = new GreenfootImage(idleFrames[currentFrame]);
                if (!facingRight) currentIdleFrame.mirrorHorizontally();
                setImage(currentIdleFrame);
                currentFrame = (currentFrame + 1) % idleFrames.length;
                animationCounter = 0;
            }
        }
    }


    
    private void flipDirection()
    {
        facingRight = !facingRight; // Toggle the facing direction
    }


    
}
