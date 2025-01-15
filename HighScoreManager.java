import java.util.HashMap;

public class HighScoreManager
{
    private static HashMap<String, Integer> highScores = new HashMap<>();

    public static void updateHighScore(String levelName, int level)
    {
        if (!highScores.containsKey(levelName) || highScores.get(levelName) < level)
        {
            highScores.put(levelName, level);
        }
    }

    public static int getHighScore(String levelName)
    {
        return highScores.getOrDefault(levelName, 0);
    }
}
