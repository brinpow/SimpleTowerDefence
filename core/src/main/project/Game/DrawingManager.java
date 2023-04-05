package project.Game;

import project.CommonInterfaces.Drawable;

public interface DrawingManager {
    void update(int waveNumber, Drawable background);
    void dispose();
}
