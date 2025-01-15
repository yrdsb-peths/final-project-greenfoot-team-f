import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HitsuPhaseTwo extends Actor implements Enemy 
{
    private final int gravity = 1; // Gravity value for downward acceleration
    private int velocity = 0; // Current vertical velocity
    private boolean isJumping = false; // Indicates if HitsuPhaseTwo is currently jumping
    private int jumpFrameIndex = 0; // Tracks the current frame of jump animation

    private int speed = 1; // Movement speed
    private int attackCooldown = 0; // Cooldown for attacks
    private int jumpCooldown = 0; // Cooldown for jumps

    private GreenfootImage[] idleFrames;
    private GreenfootImage[] walkFrames;
    private GreenfootImage[] attackFrames;
    private GreenfootImage[] jumpFrames;
    private int idleFrameIndex = 0;
    private int walkFrameIndex = 0;
    private int attackFrameIndex = 0;

    private int animationDelay = 0;
    private final int animationSpeed = 6;

    private int walkAnimationDelay = 0;
    private final int walkAnimationSpeed = 3;

    private int idleCounter = 0;
    private final int idleDuration = 120;

    private boolean facingRight = false;
    private boolean isAttacking = false;
    private boolean isIdle = false;

    private MainPlayer player;
    private int health = 120; // Increased health for Phase Two

    private GreenfootSound attackSound = new GreenfootSound("hitsuAttack.mp3");

    public HitsuPhaseTwo(MainPlayer player) 
    {
        this.player = player;

        // Load sprites for Phase Two
        idleFrames = loadFrames("hitsuIdleTwo_", 11); // 11 frames (00 to 10)
        walkFrames = loadFrames("hitsuRunTwo_", 6);  // 6 frames (00 to 05)
        attackFrames = loadFrames("hitsuAttackTwo_", 9); // 9 frames (00 to 08)
        jumpFrames = loadFrames("hitsuJumpTwo_", 7);  // 7 frames (00 to 06)

        attackSound.setVolume(80);

        setImage(idleFrames[0]); // Set initial image to the first idle frame
    }

    public void act() 
    {
        if (isJumping) 
        {
            fall();
            animate();
        } 
        else if (isIdle) 
        {
            handleIdle();
        } 
        else 
        {
            handleJump();
            if (!isJumping) 
            {
                fall();
                followPlayer();
                handleAttack();
                animate();

                if (!isAttacking && Greenfoot.getRandomNumber(150) < 1) 
                {
                    enterIdleState();
                }
            }
        }
    }

    private void enterIdleState() 
    {
        isIdle = true;
        idleCounter = idleDuration;
    }

    private void handleIdle() 
    {
        idleCounter--;

        if (idleCounter % animationSpeed == 0) 
        {
            idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
            setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
        }

        if (idleCounter <= 0) 
        {
            isIdle = false;
        }
    }

    private GreenfootImage[] loadFrames(String baseName, int count) 
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) 
        {
            String formattedIndex = String.format("%02d", i);
            frames[i] = new GreenfootImage(baseName + formattedIndex + ".png");
        }
        return frames;
    }

    public void followPlayer() 
    {
        if (!isAttacking && !isJumping && !isIdle) 
        {
            int playerX = player.getX();
            int deltaX = playerX - getX();

            if (deltaX < 0) 
            {
                facingRight = false;
                setLocation(getX() - speed, getY());
            } 
            else if (deltaX > 0) 
            {
                facingRight = true;
                setLocation(getX() + speed, getY());
            }
        }
    }

    private void handleJump() 
    {
        if (!isJumping && jumpCooldown <= 0 && isOnSolidGround()) 
        {
            if (Greenfoot.getRandomNumber(120) < 1) 
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
        velocity = -18;
        isJumping = true;
        jumpFrameIndex = 0;
        jumpCooldown = 30;
    }

    private void fall() 
    {
        setLocation(getX(), getY() + velocity);

        if (isOnSolidGround()) 
        {
            velocity = 0;
            isJumping = false;
        } 
        else 
        {
            velocity += gravity;
        }
    }

    private void handleAttack() 
    {
        if (!isAttacking && attackCooldown <= 0) 
        {
            if (Greenfoot.getRandomNumber(100) < 5) 
            {
                isAttacking = true;
                attackFrameIndex = 0;
            }
        } 
        else if (attackCooldown > 0) 
        {
            attackCooldown--;
        }
    }

    public void performAttack() 
    {
        int x = getX();
        int y = getY() - 20;

        if (facingRight) 
        {
            x += 40;
        } 
        else 
        {
            x -= 40;
        }

        EnemyProjectile projectile = new EnemyProjectile("hitsuProjectileTwo.png", facingRight, 30);
        attackSound.play();
        getWorld().addObject(projectile, x, y);
    }

    private void animate() 
    {
        animationDelay++;

        if (animationDelay >= animationSpeed) 
        {
            if (isAttacking) 
            {
                if (attackFrameIndex < attackFrames.length) 
                {
                    setImage(flipIfNeeded(attackFrames[attackFrameIndex]));

                    if (attackFrameIndex == 5) 
                    {
                        performAttack();
                    }

                    attackFrameIndex++;

                    if (attackFrameIndex == attackFrames.length) 
                    {
                        isAttacking = false;
                        attackCooldown = 60;
                    }
                }
            } 
            else if (isJumping) 
            {
                if (jumpFrameIndex < jumpFrames.length) 
                {
                    setImage(flipIfNeeded(jumpFrames[jumpFrameIndex]));
                    jumpFrameIndex++;
                }
            } 
            else if (Math.abs(player.getX() - getX()) > 5) 
            {
                walkAnimationDelay++;
                if (walkAnimationDelay >= walkAnimationSpeed) 
                {
                    walkFrameIndex = (walkFrameIndex + 1) % walkFrames.length;
                    setImage(flipIfNeeded(walkFrames[walkFrameIndex]));
                    walkAnimationDelay = 0;
                }
            } 
            else 
            {
                idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
                setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
            }

            animationDelay = 0;
        }
    }

    private GreenfootImage flipIfNeeded(GreenfootImage frame) 
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight) 
        {
            image.mirrorHorizontally();
        }
        return image;
    }

    public void setFacingRight(boolean facingRight) 
    {
        this.facingRight = facingRight;
        setImage(flipIfNeeded(getImage()));
    }

    public boolean isOnSolidGround() 
    {
        int imageHeight = getImage().getHeight();

        Actor platform = getOneObjectAtOffset(0, imageHeight / 2, Platform.class);
        if (platform != null) 
        {
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - imageHeight / 2);
            return true;
        }

        if (getY() >= 370 - imageHeight / 2) 
        {
            setLocation(getX(), 370 - imageHeight / 2);
            return true;
        }

        return false;
    }

    public int getHealth() 
    {
        return health;
    }

    public void takeDamage(int damage) 
    {
        health -= damage;
        if (health <= 0) 
        {
            health = 0;
    
            // Stop current stage music
            MusicManager.stopFourMusicTwo();
    
            // Trigger the fade-out transition via the world
            if (getWorld() instanceof FightWorldFive) 
            {
                FightWorldFive currentWorld = (FightWorldFive) getWorld();
                currentWorld.startFadeOut(); // Use FightWorldFive's fade-out logic
            }
            
            LevelClearManager.clearLevel(4);
            
            // Remove HitsuPhaseTwo from the world
            getWorld().removeObject(this);
        }
    }

}
