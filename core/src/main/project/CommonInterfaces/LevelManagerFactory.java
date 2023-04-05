package project.CommonInterfaces;

public interface LevelManagerFactory {
    LevelManager defaultLevelManager(String levelName, ScreenManager screenManager);
}
