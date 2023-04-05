package project.Game;

import java.io.DataInput;
import java.io.IOException;

public interface WaveLoader {
    void load(DataInput in, int initTimePassed) throws IOException;
}
