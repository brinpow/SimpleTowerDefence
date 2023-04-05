package project.CommonInterfaces;

import project.Game.GameState;
import project.Game.Path;
import project.Game.WaveManager;

public interface LevelManager {
    void update();
    void dispose();
    void setBackground(Drawable background);
    void addPath(Path path);
    Path getPath(int index);
    void setWaveManager(WaveManager waveManager);
    GameState getGameState();
    String getLevelName();
    int getTimePassed();
}
