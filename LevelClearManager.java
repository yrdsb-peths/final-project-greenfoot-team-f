import java.util.HashSet;

//This class manages the amount of levels that are cleared.
public class LevelClearManager
{
    private static HashSet<Integer> clearedLevels = new HashSet<>();

    public static void clearLevel(int level)
    {
        clearedLevels.add(level);
    }

    public static boolean isLevelCleared(int level)
    {
        return clearedLevels.contains(level);
    }

    public static void resetLevels()
    {
        clearedLevels.clear(); // Clear all cleared levels
    }
}
