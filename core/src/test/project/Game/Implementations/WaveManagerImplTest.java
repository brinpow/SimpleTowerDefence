package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.ListInterface;
import project.Game.Path;
import project.Game.Villain;
import project.Game.WaveManager;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WaveManagerImplTest {
    @Test
    void testConstructorThrows() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenThrow(new IOException());
            assertThrows(IOException.class, () -> {WaveManager wm = new WaveManagerImpl(in, 0);});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testWaveNumbersStartWithOne() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            assertEquals(1, wm.getWaveNr());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWaveNumberUpdate() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            wm.addWave(new WaveManager.Wave() {
                @Override
                public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {

                }

                @Override
                public boolean finished() {
                    return true;
                }
            });
            wm.addWave(mock(WaveManager.Wave.class));
            wm.update(null, null, null);
            assertEquals(2, wm.getWaveNr());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNotFinished() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            wm.addWave(new WaveManager.Wave() {
                @Override
                public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {

                }

                @Override
                public boolean finished() {
                    return false;
                }
            });
            wm.update(null, null, null);
            assertFalse(wm.finished());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFinished() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            wm.addWave(new WaveManager.Wave() {
                private int x = 0;
                @Override
                public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {
                    x++;
                }

                @Override
                public boolean finished() { return x > 0;}
            });
            wm.update(null, null, null);
            assertTrue(wm.finished());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            wm.addWave(new WaveManager.Wave() {
                @Override
                public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {
                    villainList.add(factory.produce(Villain.VillainType.b1, paths.get(0)));
                }

                @Override
                public boolean finished() {
                    return false;
                }
            });
            ListInterface<Villain> villainList = new GdxList<>();
            List<Path> paths = new ArrayList<>();
            paths.add(mock(Path.class));
            Villain.VillainFactory f = mock(Villain.VillainFactory.class);
            Villain v = mock(Villain.class);
            when(f.produce(Villain.VillainType.b1, paths.get(0))).thenReturn(v);
            wm.update(villainList, paths, f);
            var it = villainList.iterator();
            assertTrue(it.hasNext());
            Villain v2 = it.next();
            assertSame(v2, v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSkipEmptyWavesAtTheBeginning() {
        try {
            WaveManager wm = new WaveManagerImpl(mock(DataInput.class), 0);
            for(int i = 0; i < 3; i++) {
                wm.addWave(new WaveManager.Wave() {
                    @Override
                    public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {

                    }

                    @Override
                    public boolean finished() {
                        return true;
                    }
                });
            }
            wm.addWave(new WaveManager.Wave() {
                @Override
                public void update(ListInterface<Villain> villainList, List<Path> paths, Villain.VillainFactory factory) {

                }

                @Override
                public boolean finished() {
                    return false;
                }
            });
            assertEquals(4, wm.getWaveNr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}