package project.Game.Implementations;

import project.Game.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaveManagerImpl implements WaveManager {
    private final ArrayList<Wave> waves;
    int waveIndex = 0;

    WaveManagerImpl(DataInput in, int initTimePassed) throws IOException {
            waves = new ArrayList<>();
            WaveLoader waveLoader = new WaveLoaderImpl(this);
            waveLoader.load(in, initTimePassed);
    }

    public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {
        if(finished())
            throw new GameException("Update on finished WaveManager");
        else {
            if(waves.get(waveIndex).finished())
                waveIndex++;
            waves.get(waveIndex).update(villainList, paths, factory);
        }
    }

    public boolean finished() {
        return waves.get(waves.size() - 1).finished();
    }

    public int getWaveNr() {
        return waveIndex + 1;
    }

    public void addWave(Wave wave) {
        waves.add(wave);
        if(wave.finished())
            waveIndex++;
    }
}
