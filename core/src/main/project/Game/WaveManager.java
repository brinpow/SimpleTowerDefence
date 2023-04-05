package project.Game;

import java.util.List;

public interface WaveManager {
    interface Wave {
        void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory);
        boolean finished();
    }

    void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory);
    void addWave(Wave wave);
    boolean finished();
    int getWaveNr();
}
