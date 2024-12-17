import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionText extends Actor
{
    public InstructionText(String text)
    {
        //Adding the details for the instructions text that will be displayed.
        GreenfootImage textImage = new GreenfootImage(text, 24, Color.BLACK, new Color(0, 0, 0, 0)); // 24 is font size, adjust if needed
        setImage(textImage);
    }
}
