import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyProjectile extends Actor 
{
    private int speed = 5;
    private boolean movingRight;

    public EnemyProjectile(String imageName, boolean movingRight) 
    {
        setImage(imageName); // Set the projectile image
        this.movingRight = movingRight;

        if (!movingRight) 
        {
            getImage().mirrorHorizontally(); // Flip the image for the left direction
        }
    }

    public void act() 
    {
        if (movingRight) 
        {
            setLocation(getX() + speed, getY()); // Move to the right
        } 
        else 
        {
            setLocation(getX() - speed, getY()); // Move to the left
        }

        if (isAtEdge()) // Remove projectile if it goes off-screen
        {
            getWorld().removeObject(this);
        }
    }
}
