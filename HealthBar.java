import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HealthBar extends Actor
{
    private int maxHealth;
    private int currentHealth;
    private int width;
    private int height;
    private Color backgroundColor = Color.WHITE;
    private Color foregroundColor = Color.RED;

    public HealthBar(int maxHealth, int width, int height)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.width = width;
        this.height = height;
        updateImage();
    }

    public void setHealth(int health)
    {
        this.currentHealth = Math.max(0, Math.min(maxHealth, health)); // Clamp between 0 and maxHealth
        updateImage();
    }

    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);

        // Draw background (black bar)
        image.setColor(backgroundColor);
        image.fillRect(0, 0, width, height);

        // Draw foreground (red bar for health)
        int healthWidth = (int) ((double) currentHealth / maxHealth * width);
        image.setColor(foregroundColor);
        image.fillRect(0, 0, healthWidth, height);

        setImage(image);
    }
}
