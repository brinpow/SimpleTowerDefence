package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.*;

import project.CommonInterfaces.*;
import project.Point;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WaveLoaderImplTest {
    @Test
    void testConstructorThrowsWhenInputStreamThrows() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenThrow(new IOException());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(IOException.class, () -> {
            WaveLoader wm = new WaveLoaderImpl(mock(WaveManager.class));
            wm.load(in, 0);
        });
    }

    @Test
    void testConstructorThrowsWhenVillainTypeIncorrect() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenReturn(1);
            when(in.readUTF()).thenReturn("?");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(GameException.class, () -> {
            WaveLoader wm = new WaveLoaderImpl(mock(WaveManager.class));
            wm.load(in, 0);
        });
    }

    @Test
    void testUpdate() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenReturn(1);
            when(in.readUTF()).thenReturn("b1");
            WaveManager wm = new WaveManagerImpl(in, 0);
            ListInterface<Villain> villains = new GdxList<>();
            ArrayList<Path> paths = new ArrayList<>();
            paths.add(mock(Path.class));
            paths.add(mock(Path.class));
            Villain.VillainFactory factory = new VillainFactoryImpl(mock(Drawer.class));
            when(paths.get(1).getPoint(0)).thenReturn(new Point(0, 0));
            wm.update(villains, paths, factory);
            var it = villains.iterator();
            assertTrue(it.hasNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tests that check if data is read correctly
    @Test
    void testFinished() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenReturn(1);
            when(in.readUTF()).thenReturn("b1");
            WaveManager wm = new WaveManagerImpl(in, 0);
            ListInterface<Villain> villains = new GdxList<>();
            ArrayList<Path> paths = new ArrayList<>();
            paths.add(mock(Path.class));
            paths.add(mock(Path.class));
            Villain.VillainFactory factory = new VillainFactoryImpl(mock(Drawer.class));
            when(paths.get(1).getPoint(0)).thenReturn(new Point(0, 0));
            wm.update(villains, paths, factory);
            assertTrue(wm.finished());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNotFinished() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenReturn(1);
            when(in.readUTF()).thenReturn("b1");
            WaveManager wm = new WaveManagerImpl(in, 0);
            assertFalse(wm.finished());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInitTimePassed() {
        DataInput in = mock(DataInput.class);
        try {
            when(in.readInt()).thenReturn(1);
            when(in.readUTF()).thenReturn("b1");
            WaveManager wm = new WaveManagerImpl(in, 1);
            assertTrue(wm.finished());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}