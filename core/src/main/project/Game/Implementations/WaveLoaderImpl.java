package project.Game.Implementations;

import project.Game.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaveLoaderImpl implements WaveLoader {
    private final WaveManager waveManager;
    private static int timeToSkip;

    private static class WaveImpl implements WaveManager.Wave {
        private static class WaveMember {
            int delay;
            int pathIndex;
            Villain.VillainType type;
        }

        private final ArrayList<WaveMember> villains;
        int villainIndex = 0;

        WaveImpl(DataInput in) throws IOException {
            int nrOfVillains = in.readInt();
            villains = new ArrayList<>();
            for(int i = 0; i < nrOfVillains; i++) {
                int delay = in.readInt();
                int pathIndex = in.readInt();
                Villain.VillainType type;
                try {
                    type = Villain.VillainType.valueOf((in.readUTF()));
                } catch (IllegalArgumentException e) {
                    throw new GameException("Invalid villain type in level file");
                }
                if(timeToSkip < delay) {
                    delay -= timeToSkip;
                    WaveMember toAdd = new WaveMember();
                    toAdd.delay = delay;
                    if (toAdd.delay <= 0)
                        throw new GameException("Non-positive delay in level file");
                    toAdd.pathIndex = pathIndex;
                    toAdd.type = type;
                    villains.add(toAdd);
                }
                else
                    timeToSkip -= delay;
            }
        }

        public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {
            try {
                WaveMember v = villains.get(villainIndex);
                v.delay--;
                if(v.delay == 0) {
                    villainList.add(factory.produce(v.type, paths.get(v.pathIndex)));
                    villainIndex++;
                }
            }
            catch (IndexOutOfBoundsException e) {
                throw new GameException("Invalid path index");
            }
        }

        public boolean finished() {
            return villainIndex >= villains.size();
        }
    }

    WaveLoaderImpl(WaveManager waveManager) {
        this.waveManager = waveManager;
    }

    @Override
    public void load(DataInput in, int initTimePassed) throws IOException {
        int nrOfWaves = in.readInt();
        timeToSkip = initTimePassed;
        for(int i = 0; i < nrOfWaves; i++)
            waveManager.addWave(new WaveImpl(in));
    }
}
