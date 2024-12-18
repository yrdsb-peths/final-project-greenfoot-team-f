import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Kisuke extends Actor
{
    private final int gravity = 1;
    private int velocity = 0;
    private boolean isJumping = false;
    private boolean facingRight = true; // Default direction is right
    private int jumpFrameIndex = 0;
    private int attackCooldown = 0; // Counter for attack cooldown
    private int jumpCooldown = 0;   // Counter for jump cooldown
    private int animationCounter = 0; // Animation speed controller

    private int speed = 2; // Movement speed

    private GreenfootImage[] idleFrames;
    private GreenfootImage[] walkFrames;
    private GreenfootImage[] jumpFrames;
    private GreenfootImage[] attackFrames;

    private int idleFrameIndex = 0;
    private int walkFrameIndex = 0;
    private int attackFrameIndex = 0;

    private boolean isAttacking = false;

    private MainPlayer player;

    public Kisuke(MainPlayer player)
    {
        this.player = player;

        // Load all animation frames
        idleFrames = loadFrames("kisukeIdle_", 6);     // 6 idle frames
        walkFrames = loadFrames("kisukeWalk_", 4);     // 4 walking frames
        jumpFrames = loadFrames("kisukeJump_", 7);     // 7 jumping frames
        attackFrames = loadFrames("kisukeAttack_", 10); // 10 attack frames

        setImage(idleFrames[0]); // Default to first idle frame
    }

    public void act()
    {
        fall();            // Handle gravity
        followPlayer();    // Follow and flip towards the player
        handleJump();      // Random jump logic
        handleAttack();    // Random attack logic
        animate();         // Manage animation states
    }

    private GreenfootImage[] loadFrames(String baseName, int count)
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++)
        {
            frames[i] = new GreenfootImage(baseName + i + ".png");
        }
        return frames;
    }

    private void followPlayer()
    {
        if (!isAttacking && !isJumping) // Follow player only if not attacking or jumping
        {
            int playerX = player.getX();

            // Flip direction based on player's position
            if (playerX < getX() && facingRight)
            {
                facingRight = false;
            }
            else if (playerX > getX() && !facingRight)
            {
                facingRight = true;
            }

            // Move towards the player
            int deltaX = playerX - getX();
            if (Math.abs(deltaX) > 2)
            {
                setLocation(getX() + (int) Math.signum(deltaX) * speed, getY());
            }
        }
    }

    private void handleJump()
    {
        if (!isJumping && jumpCooldown <= 0) // Ready to jump
        {
            if (Greenfoot.getRandomNumber(100) < 5) // 5% chance to jump each frame
            {
                jump();
            }
        }
        else if (jumpCooldown > 0)
        {
            jumpCooldown--;
        }
    }

    private void jump()
    {
        velocity = -18; // Jump velocity
        isJumping = true;
        jumpFrameIndex = 0;
    }

    private void fall()
    {
        setLocation(getX(), getY() + velocity); // Apply gravity

        if (getY() > getWorld().getHeight() - 50) // If on the ground
        {
            velocity = 0;
            setLocation(getX(), getWorld().getHeight() - 50); // Snap to ground
            isJumping = false;
            jumpCooldown = 60; // Set jump cooldown
        }
        else
        {
            velocity += gravity; // Apply gravity
        }
    }

    private void handleAttack()
    {
        if (!isAttacking && attackCooldown <= 0)
        {
            if (Greenfoot.getRandomNumber(100) < 5) // 5% chance to attack
            {
                isAttacking = true;
                attackFrameIndex = 0;
            }
        }
        else if (isAttacking)
        {
            playAttackAnimation();
        }
        else
        {
            attackCooldown--;
        }
    }

    private void playAttackAnimation()
    {
        if (attackFrameIndex < attackFrames.length)
        {
            setImage(getFlippedFrame(attackFrames[attackFrameIndex]));
            attackFrameIndex++;
        }
        else
        {
            performAttack();
            isAttacking = false;
            attackCooldown = 60; // Cooldown for next attack
            setImage(idleFrames[0]); // Return to idle
        }
    }

    private void performAttack()
    {
        if (intersects(player))
        {
            player.takeDamage(10); // Call player's takeDamage method
        }
    }

    private void animate()
    {
        animationCounter++;

        if (isAttacking)
        {
            // Attack animation handled in playAttackAnimation
            return;
        }

        if (isJumping)
        {
            if (jumpFrameIndex < jumpFrames.length && animationCounter >= 6)
            {
                setImage(getFlippedFrame(jumpFrames[jumpFrameIndex]));
                jumpFrameIndex++;
                animationCounter = 0;
            }
        }
        else if (Math.abs(player.getX() - getX()) > 2) // Walking animation
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(walkFrames[walkFrameIndex]));
                walkFrameIndex = (walkFrameIndex + 1) % walkFrames.length;
                animationCounter = 0;
            }
        }
        else // Idle animation
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(idleFrames[idleFrameIndex]));
                idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
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
}
