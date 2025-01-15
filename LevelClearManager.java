/**
 * Write a description of class LevelClearManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelClearManager  
{
    private static boolean[] isLevelClear = new boolean[4];
    
    public static boolean isLevelClear(int level) 
    {
        if (level >= 0 && level < isLevelClear.length) {
            return isLevelClear[level];
        }
        return false;
    }

    public static void setLevelClear(int level, boolean isClear) 
    {
        if(level >= 0 && level < isLevelClear.length)
        {
            isLevelClear[level] = isClear;
        }
    }
}
