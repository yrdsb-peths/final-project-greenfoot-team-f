import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class healthBarFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class healthBarFrame extends Actor
{
    /**
     * Act - do whatever the healthBarFrame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GreenfootImage healthFrame = new GreenfootImage("healthFrame.png");
        healthFrame.scale(312,80);
    }
}
