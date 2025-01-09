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

    // Attack animation variables
    private boolean isAttacking = false;
    private int attackFrameIndex = 0;

    // Jump animation variables
    private boolean isJumping;
    private int jumpFrameIndex;
    
    private int health = 100; //PLAYER HEALTH *****
    
    public MainPlayer()
    {
        velocity = 0;
        currentFrame = 0;
        animationCounter = 0;

        idleFrames = loadFrames("playerIdle_", 4);
        runFrames = loadFrames("run_", 4);
        jumpFrames = loadFrames("jump_", 6);
        attackFrames = loadFrames("attack_", 8);

        facingRight = true; // Default direction
        setImage(idleFrames[0]);
    }

    public void act()
    {
        fall();
        if (Greenfoot.isKeyDown("space") && isOnSolidGround())
        {
            jump();
            Greenfoot.playSound("jumpSfx.mp3");
        }

        move();
        animate();
    }

    private GreenfootImage[] loadFrames(String baseName, int count)
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++)
        {
            frames[i] = new GreenfootImage(baseName + (i + 1) + ".png");
        }
        return frames;
    }

    private void jump()
    {
        velocity = -18;
        isJumping = true;
        jumpFrameIndex = 0;
    }
    
    public void fall()
    {
        setLocation(getX(), getY() + velocity);

        if (isOnSolidGround())
        {
            velocity = 0;
        }
        else
        {
            velocity += gravity;
        }
    }
    
    public void move()
    {
        int y = getY();
        int x = getX();

        if (Greenfoot.isKeyDown("a")) // Move left
        {
            x -= 3;
            if (facingRight)
            {
                facingRight = false;
            }
        }

        if (Greenfoot.isKeyDown("d")) // Move right
        {
            x += 3;
            if (!facingRight)
            {
                facingRight = true;
            }
        }

        setLocation(x, y);
    }

    public boolean isOnSolidGround()
    {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
    
        // Check if the player is standing on a platform
        Actor platform = getOneObjectAtOffset(0, imageHeight / 2, Platform.class);
        if (platform != null)
        {
            // Snap to the top of the platform
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - imageHeight / 2);
            return true;
        }
    
        // Check if the player is on the ground (at 370)
        if (getY() >= 370 - imageHeight / 2)
        {
            setLocation(getX(), 370 - imageHeight / 2); // Snap to ground height
            return true;
        }
    
        return false;
    }

    
    private void animate()
    {
        animationCounter++;

        if (Greenfoot.isKeyDown("p") && !isAttacking)
        {
            isAttacking = true;
            attackFrameIndex = 0;
        }

        if (isAttacking)
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(attackFrames[attackFrameIndex]));
                attackFrameIndex++;

                if (attackFrameIndex >= attackFrames.length)
                {
                    isAttacking = false;
                    attackFrameIndex = 0;
                    fireProjectile();
                }

                animationCounter = 0;
            }
        }
        else if (isJumping)
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(jumpFrames[jumpFrameIndex]));
                jumpFrameIndex++;
                if (jumpFrameIndex >= jumpFrames.length)
                {
                    isJumping = false;
                    jumpFrameIndex = 0;
                }
                animationCounter = 0;
            }
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d"))
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(runFrames[currentFrame]));
                currentFrame = (currentFrame + 1) % runFrames.length;
                animationCounter = 0;
            }
        }
        else
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(idleFrames[currentFrame]));
                currentFrame = (currentFrame + 1) % idleFrames.length;
                animationCounter = 0;
            }
        }
    }

    private GreenfootImage getFlippedFrame(GreenfootImage frame)
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight)
        {
            image.mirrorHorizontally();
        }
        return image;
    }

    private void fireProjectile()
    {
        int x;
        int y = getY() - 20; // Slightly higher than the player's position
        
        if (facingRight) {
            x = getX() + 40; // Offset to the right
        } else {
            x = getX() - 40; // Offset to the left
        }
        
        Greenfoot.playSound("shootSfx.mp3");
        Projectile projectile = new Projectile(facingRight);
        getWorld().addObject(projectile, x, y);

    }
    
    public void takeDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            health = 0;
            Greenfoot.setWorld(new GameOverScreen());
        }
    }
    
    public int getHealth()
    {
        return health;
    }
}