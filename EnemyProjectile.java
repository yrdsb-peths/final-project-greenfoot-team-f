import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyProjectile extends Actor 
{
    private int speed = 5;
    private boolean movingRight;

    public EnemyProjectile(String imageName, boolean movingRight) 
    {
        setImage(imageName);
        this.movingRight = movingRight;
    }

    public void act() 
    {
        if (movingRight) 
        {
            setLocation(getX() + speed, getY());
        } 
        else 
        {
            setLocation(getX() - speed, getY());
        }

        if (getX() < 0 || getX() > getWorld().getWidth()) 
        {
            getWorld().removeObject(this);
        }
    }
}
