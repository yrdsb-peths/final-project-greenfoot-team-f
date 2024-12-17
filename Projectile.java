import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectile extends Actor
{
    private int speed;

    public Projectile(boolean facingRight)
    {
        setImage("blueArrow.png"); // Set the projectile image
        this.speed = facingRight ? 8 : -8; // Set direction based on facingRight
        if (!facingRight) 
        {
            getImage().mirrorHorizontally(); // Flip the image for left direction
        }
    }

    public void act()
    {
        setLocation(getX() + speed, getY()); // Move the projectile
        if (isAtEdge()) // Remove projectile if it goes off-screen
        {
            getWorld().removeObject(this);
        }
    }
}
